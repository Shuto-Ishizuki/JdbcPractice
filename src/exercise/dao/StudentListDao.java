package exercise.dao;

import exercise.dto.StudentListDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentListDao {
    // JDBCドライバの相対パスを定義（接続先：MySQL8.0.34.0）
    // ※接続先のバージョンによってパスは変わる（MySQL8.0系以前の場合は「com.mysql.jdbc.Driver」）
    private String driverName = "com.mysql.cj.jdbc.Driver";

    // 接続先のデータベースを定義
    private String jdbcUrl = "jdbc:mysql://localhost/udemy_jdbc?characterEncoding=UTF-8&serverTimezone=JST";

    // 接続するユーザー名を定義
    private String userId = "root";

    // 接続するユーザーのパスワードを定義
    private String userPass = "root";

    // "コンマ"を定数としてを定義
    private static String COMMA = ",";

    // 全生徒の情報を取得して、List<StudentListDto>型に格納した結果を返すメソッド
    public List<StudentListDto> selectStudentInfoFromUzuzStudent() {
        // ① JDBCドライバをロードする
        loadJdbcDriver(driverName);

        // ② データベース接続のための変数を宣言
        // ResultSet型：SQL抽出結果の格納用変数
        ResultSet resultSet = null;

        // 発行するSQL文を生成（SELECT文）
        StringBuffer buf = new StringBuffer();
        buf.append("SELECT        ");
        buf.append("STUDENT_NAME, ");
        buf.append("GENDER,       ");
        buf.append("AGE,          ");
        buf.append("CAREER_MON,   ");
        buf.append("BRANCH_ID,    ");
        buf.append("COURSE_ID     ");
        buf.append("FROM          ");
        buf.append("uzuz_student  ");

        // List<StudentInfoDto>型：抽出データ（1行 = テーブルの1レコード）の格納用変数
        List<StudentListDto> studentList = new ArrayList<StudentListDto>();

        // close()が必要なオブジェクトを利用するため、try-with-resourcesを定義する
        try (
            // Connection型：DB接続情報の格納用変数。DBとの接続を確立する
            Connection connection = DriverManager.getConnection(jdbcUrl, userId, userPass);
            // PreparedStatement型：SQL発行用オブジェクトの格納用変数。 引数に発行するSQLをセットする
            PreparedStatement preparedStatement = connection.prepareStatement(buf.toString());
        ){
            // DBにSQL文を送信し、抽出結果をResultSet型変数に格納する
            resultSet = preparedStatement.executeQuery();

            // ResultSet型変数から、1レコード分のデータをDTOクラスに格納する
            while(resultSet.next()){
                // StudentInfoDtoクラスをインスタンス化
                StudentListDto dto = new StudentListDto();

                // "名前"を取得
                dto.setStudentName(resultSet.getString("STUDENT_NAME"));

                // "性別"を取得
                int genderId = resultSet.getInt("GENDER");
                String gender = "";
                if(genderId == 1) {
                    gender = "男性";
                }else {
                    gender = "女性";
                }
                dto.setGender(gender);

                // "年齢"を取得
                dto.setAge(resultSet.getInt("AGE"));

                // "職歴"を取得
                int careerMon = resultSet.getInt("CAREER_MON");
                String career = "";
                if(careerMon == 0){
                    career = "職歴なし";
                }
                else if(careerMon < 12){
                    career = String.valueOf(careerMon) + "ヶ月";
                }
                else if(careerMon % 12 == 0){
                    career = careerMon / 12 + "年";
                }else{
                    career = careerMon / 12 + "年" + careerMon % 12 + "ヶ月";
                }
                dto.setCareerMon(career);

                // "登録支店"を取得
                int branchId = resultSet.getInt("BRANCH_ID");
                String branchName = selectBranchNameFromBranchByBranchID(branchId);
                dto.setBranchName(branchName);

                // "受講口座"を取得
                int courseId = resultSet.getInt("COURSE_ID");
                String courseName = "受講なし";
                // "course_id"が"null"の場合は、intの値が初期値"0"のままになる
                // そのため、"course_id"が"0"ではない場合に"course_name"を取得する
                if(courseId != 0) {
                    courseName = selectCourseNameFromCourseByCourseID(courseId);
                }
                dto.setCourseName(courseName);

                // リストに1レコードを格納
                studentList.add(dto);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        // 抽出データを持つListを返す
        return studentList;
    }

    // JDBCドライバをロードするメソッド
    private void loadJdbcDriver(String driverName) {
        try {
            Class.forName(driverName);
        }catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    // branchテーブルから"branch_name"を取得するメソッド
    private String selectBranchNameFromBranchByBranchID(int branchId) {
        // "branch_name"の格納用変数
        String branchName = "";

        // 発行するSQL文を生成（SELECT文）
        StringBuffer buf = new StringBuffer();
        buf.append("SELECT      ");
        buf.append("BRANCH_NAME ");
        buf.append("FROM        ");
        buf.append("branch      ");
        buf.append("WHERE       ");
        buf.append("BRANCH_ID = ");
        buf.append("?           ");

        // close()が必要なオブジェクトを利用するため、try~with/resourcesを定義する
        try (
            // Connection型：DB接続情報の格納用変数。DBとの接続を確立する
            Connection connection = DriverManager.getConnection(jdbcUrl, userId, userPass);
            // PreparedStatement型：SQL発行用オブジェクトの格納用変数。 引数に発行するSQLをセットする
            PreparedStatement preparedStatement = connection.prepareStatement(buf.toString());
        ){
            // 引数にプレースホルダの番号と値をセットする
            preparedStatement.setInt(1, branchId);

            // DBにSQL文を送信し、抽出結果をResultSet型変数に格納する
            ResultSet resultSet = preparedStatement.executeQuery();

            // ResultSet型変数から、対象のレコードを取得する
            while(resultSet.next()){
                branchName = resultSet.getString("BRANCH_NAME");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        // "branch_name"を返す
        return branchName;
    }

    // courseテーブルから"course_name"を取得するメソッド
    private String selectCourseNameFromCourseByCourseID(int courseId) {
        // "course_name"の格納用変数
        String courseName = "";

        // 発行するSQL文を生成（SELECT文）
        StringBuffer buf = new StringBuffer();
        buf.append("SELECT      ");
        buf.append("COURSE_NAME ");
        buf.append("FROM        ");
        buf.append("course      ");
        buf.append("WHERE       ");
        buf.append("COURSE_ID = ");
        buf.append("?           ");

        // close()が必要なオブジェクトを利用するため、try~with/resourcesを定義する
        try (
                // Connection型：DB接続情報の格納用変数。DBとの接続を確立する
                Connection connection = DriverManager.getConnection(jdbcUrl, userId, userPass);
                // PreparedStatement型：SQL発行用オブジェクトの格納用変数。 引数に発行するSQLをセットする
                PreparedStatement preparedStatement = connection.prepareStatement(buf.toString());
        ){
            // 引数にプレースホルダの番号と値をセットする
            preparedStatement.setInt(1, courseId);

            // DBにSQL文を送信し、抽出結果をResultSet型変数に格納する
            ResultSet resultSet = preparedStatement.executeQuery();

            // ResultSet型変数から、対象のレコードを取得する
            while(resultSet.next()){
                courseName = resultSet.getString("COURSE_NAME");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        // "course_name"を返す
        return courseName;
    }
}

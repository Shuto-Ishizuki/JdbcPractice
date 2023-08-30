package exercise.business_logic;

import exercise.dao.StudentsCountByCourseDao;
import exercise.dto.StudentsCountByCourseDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentsCountByCourseBL {
    // 書き出し用ファイルのパス情報を定数として定義（パス："C:\Users\H1017363\workfile\JdbcPractice"）
    final String FILE_PATH = "C:\\Users\\H1017363\\workfile\\JdbcPractice\\student_info.csv";

    // 改行を行うメソッドを定数として定義
    final String NEW_LINE = System.getProperty("line.separator");

    // 定数として"コンマ"を宣言
    private final String COMMA = ",";

    // 受講者数の情報を抽出して、CSVに出力した後に、コマンドラインに表示するメソッド
    public void extractNumberOfStudents() {
        // StudentsCountByCourseDaoクラスをインスタンス化
        StudentsCountByCourseDao dao = new StudentsCountByCourseDao();
        // データベースへの接続を実施して、抽出した生徒の情報をList<StudentsCountByCourseDto>型に格納する
        List<StudentsCountByCourseDto> extractedDtoList = dao.selectNumberOfStudentsFromUzuzStudent();
        // File型：書き出し用ファイル格納用変数
        File file = new File(FILE_PATH);

        try (
            // FileWriter型：引数に書き出し用ファイルをセットし、ファイルを読み込む
            // 引数 true：ファイルへの追加書き込みを許可する
            FileWriter fileWriter = new FileWriter(file , true);
            ){

            // ファイルの先頭行に列の見出しを書き込む
            fileWriter.write(NEW_LINE);
            fileWriter.write("#--集計結果--");
            fileWriter.write(NEW_LINE);
            fileWriter.write("支店名");
            fileWriter.write(COMMA);
            fileWriter.write("受講講座");
            fileWriter.write(COMMA);
            fileWriter.write("受講者数");
            fileWriter.write(NEW_LINE);

            // 抽出した受講者数の情報が存在した場合
            if(extractedDtoList != null){
                // Listを1行ずつ（テーブルの1レコードずつ）繰り返し処理する
                for(int i = 0; i < extractedDtoList.size(); i++){
                    // 1レコードのカラムを変数に格納
                    String branchName = extractedDtoList.get(i).getBranchName();
                    String courseName = extractedDtoList.get(i).getCourseName();
                    int numberOfStudents = extractedDtoList.get(i).getNumberOfStudents();

                    // "courseName"が"受講なし"ではないレコードを対象とする
                    if(!courseName.equals("受講なし")) {
                        // 1レコードのカラムをcsvの1行に書き込む（int型はそのまま書き込むと"ASCII"に変換されるため、String型に変換する）
                        fileWriter.write(branchName);
                        fileWriter.write(COMMA);
                        fileWriter.write(courseName);
                        fileWriter.write(COMMA);
                        fileWriter.write(String.valueOf(numberOfStudents));
                        fileWriter.write(NEW_LINE);

                        // 1レコードのカラムをStringBuffer型に格納し、1レコードをコマンドラインに表示
                        StringBuffer rsbuf = new StringBuffer();
                        rsbuf.append(branchName);
                        rsbuf.append(COMMA);
                        rsbuf.append(courseName);
                        rsbuf.append(COMMA);
                        rsbuf.append(numberOfStudents);
                        System.out.println(rsbuf.toString());
                    }
                }
            } else {
                System.out.println("[INFO]該当のユーザー情報を取得できませんでした" ) ;
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
}

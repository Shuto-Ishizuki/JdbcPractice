package dao;

import java.sql.*;


/**----------------------------------------------------------------------*
 *■■■Sample4_02_1_Common_DAOクラス■■■
 *概要：DAO（uzuz_memberテーブル）
 *----------------------------------------------------------------------**/
public class Sample4_02_1_Common_DAO {

	//-------------------------------------------
	//データベースへの接続情報
	//-------------------------------------------

	//JDBCドライバの相対パス
	//※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	//※データベース名が「test_db」でない場合は該当の箇所を変更してください
	String JDBC_URL    = "jdbc:mysql://localhost/udemy_jdbc?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";

	//接続するユーザー名
	//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
	String USER_ID     = "root";

	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	String USER_PASS   = "root";


	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------

	/**----------------------------------------------------------------------*
	 *■selectMemberInfoメソッド
	 *概要　：「uzuz_member」テーブルのレコードを1行抽出する
	 *引数　：抽出対象ID
	 *戻り値：抽出データ（Sample4_02_1_Common_DTO型）
	 *----------------------------------------------------------------------**/
	public Sample4_02_1_Common_DTO selectMemberInfo(int pk){

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

		//抽出データ（Sample4_02_1_Common_DTO型）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		Sample4_02_1_Common_DTO dto = null;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT        ");
			buf.append("   id     ,    ");
			buf.append("   name   ,    ");
			buf.append("   gender ,    ");
			buf.append("   age    ,    ");
			buf.append("   course      ");
			buf.append(" FROM          ");
			buf.append("   uzuz_member ");
			buf.append(" WHERE         ");
			buf.append("   id = ?      ");  //第1パラメータ

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setInt(1, pk);

			//SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
			rs = ps.executeQuery();

			//ResultSetオブジェクトから1レコード分のデータをDTOに格納
			if(rs.next()){
				dto = new Sample4_02_1_Common_DTO();
				dto.setId(     rs.getInt(    "id"     ) );
				dto.setName(   rs.getString( "name"   ) );
				dto.setGender( rs.getString( "gender" ) );
				dto.setAge(    rs.getInt(    "age"    ) );
				dto.setCourse( rs.getString( "course" ) );
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//ResultSetオブジェクトの接続解除
			if (rs != null) {    //接続が確認できている場合のみ実施
				try {
					rs.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		//抽出データを戻す
		return dto;
	}

	/**----------------------------------------------------------------------*
	 *■insertMemberInfoメソッド
	 *概要　：「uzuz_member」テーブルにレコードを1行追加する
	 *引数　：追加データ（Sample4_02_1_Common_DTO型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	public boolean insertMemberInfo(Sample4_02_1_Common_DTO dto){

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true ;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（INSERT）
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT INTO uzuz_member ( ");
			buf.append("   id     ,                ");
			buf.append("   name   ,                ");
			buf.append("   gender ,                ");
			buf.append("   age    ,                ");
			buf.append("   course                  ");
			buf.append(" ) VALUES (                ");
			buf.append("   ?,                      ");  //第1パラメータ
			buf.append("   ?,                      ");  //第2パラメータ
			buf.append("   ?,                      ");  //第3パラメータ
			buf.append("   ?,                      ");  //第4パラメータ
			buf.append("   ?                       ");  //第5パラメータ
			buf.append(" )                         ");

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setInt(    1, dto.getId()     );  //第1パラメータ：更新データ（ID）
			ps.setString( 2, dto.getName()   );  //第2パラメータ：更新データ（名前）
			ps.setString( 3, dto.getGender() );  //第3パラメータ：更新データ（性別）
			ps.setInt(    4, dto.getAge()    );  //第4パラメータ：更新データ（年齢）
			ps.setString( 5, dto.getCourse() );  //第5パラメータ：更新データ（コース）

			//SQL文の送信＆戻り値として追加件数を取得
			int insCount = ps.executeUpdate();

			//SQL実行結果を表示
			System.out.println("[INFO]" + insCount + "行追加しました") ;

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false ;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if(isSuccess){
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		//実行結果を戻す
		return isSuccess ;

	}


	/**----------------------------------------------------------------------*
	 *■updateMemberInfoメソッド
	 *概要　：「uzuz_member」テーブルのレコードを1行更新する
	 *引数　：更新データ（Sample4_02_1_Common_DTO型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	public boolean updateMemberInfo(Sample4_02_1_Common_DTO dto){

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true ;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（UPDATE）
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE uzuz_member ");
			buf.append("  SET               ");
			buf.append("   name   = ? ,     ");  //第1パラメータ
			buf.append("   gender = ? ,     ");  //第2パラメータ
			buf.append("   age    = ? ,     ");  //第3パラメータ
			buf.append("   course = ?       ");  //第4パラメータ
			buf.append("  WHERE             ");
			buf.append("   id     = ?       ");  //第5パラメータ

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString( 1, dto.getName()   );  //第1パラメータ：更新データ（名前）
			ps.setString( 2, dto.getGender() );  //第2パラメータ：更新データ（性別）
			ps.setInt(    3, dto.getAge()    );  //第3パラメータ：更新データ（年齢）
			ps.setString( 4, dto.getCourse() );  //第4パラメータ：更新データ（コース）
			ps.setInt(    5, dto.getId()     );  //第5パラメータ：更新データ（ID）

			//SQL文の送信＆戻り値として更新件数を取得
			int updCount = ps.executeUpdate();

			//SQL実行結果を表示
			System.out.println("[INFO]" + updCount + "行更新しました") ;

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false ;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if(isSuccess){
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		//実行結果を戻す
		return isSuccess ;

	}


	/**----------------------------------------------------------------------*
	 *■deleteMemberInfoメソッド
	 *概要　：「uzuz_member」テーブルから指定のレコードを1行削除する
	 *引数　：削除対象ID
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	public boolean deleteMemberInfo(int pk){

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true ;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（DELETE）
			StringBuffer buf = new StringBuffer();
			buf.append(" DELETE FROM uzuz_member ");
			buf.append("  WHERE id = ?           ");  //第1パラメータ

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setInt( 1, pk );  //第1パラメータ：削除対象ID

			//SQL文の送信＆戻り値として削除件数を取得
			int delCount = ps.executeUpdate();

			//SQL実行結果を表示
			System.out.println("[INFO]" + delCount + "行削除しました") ;

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false ;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if(isSuccess){
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		//実行結果を戻す
		return isSuccess ;

	}
}
package dao;

/**----------------------------------------------------------------------*
 *■■■Sample4_02_1_Sel_BusinessLogicクラス■■■
 *概要：ビジネスロジック（ユーザーの情報抽出）
 *----------------------------------------------------------------------**/
public class Sample4_02_1_Sel_BusinessLogic {

	/**----------------------------------------------------------------------*
	 *■extractionメソッド
	 *概要　：対象のユーザー情報を抽出し、コマンドライン上に表示する
	 *引数　：対象のユーザーID
	 *戻り値：なし
	 *----------------------------------------------------------------------**/
	public void extract (int targetUserId) {

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆指定のIDと合致するデータを抽出するよう依頼
		Sample4_02_1_Common_DAO dao = new Sample4_02_1_Common_DAO();
		Sample4_02_1_Common_DTO extractedDto = dao.selectMemberInfo(targetUserId);

		//-------------------------------------------
		//抽出したユーザー情報をコマンドライン上に表示
		//-------------------------------------------
		if(extractedDto != null){
			System.out.println("**************取得結果***************") ;
			System.out.println("*ID："              + extractedDto.getId()     ) ;
			System.out.println("*名前："            + extractedDto.getName()   ) ;
			System.out.println("*性別(M:男,F:女)：" + extractedDto.getGender() ) ;
			System.out.println("*年齢："            + extractedDto.getAge()    ) ;
			System.out.println("*コース："          + extractedDto.getCourse() ) ;
			System.out.println("*************************************") ;
		} else {
			System.out.println("[INFO]該当のユーザー情報を取得できませんでした" ) ;
		}

	}
}
package select_multiple_data;

import java.util.List;

/**----------------------------------------------------------------------*
 *■■■Sample4_02_2_Sel_BusinessLogicクラス■■■
 *概要：ビジネスロジック（全ユーザーの情報抽出）
 *----------------------------------------------------------------------**/
public class Sample4_02_2_Sel_BusinessLogic {

	//定数
	private final String COMMA = ","; //コンマ

	/**----------------------------------------------------------------------*
	 *■extractionAllメソッド
	 *概要　：全ユーザーの情報を抽出し、コマンドライン上に表示する
	 *引数　：なし
	 *戻り値：なし
	 *----------------------------------------------------------------------**/
	public void extractAll () {

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆データを抽出するよう依頼
		Sample4_02_2_Common_DAO dao = new Sample4_02_2_Common_DAO();
		List<Sample4_02_2_Common_DTO> extractedDtoList = dao.selectMemberInfoAll();

		//-------------------------------------------
		//抽出したユーザー情報をコマンドライン上に表示
		//-------------------------------------------
		if(extractedDtoList != null){

			for(int i = 0 ; i < extractedDtoList.size() ; i++){

				//1レコード分のデータを取得＆加工（各カラムをコンマ綴りで結合）
				StringBuffer rsbuf = new StringBuffer();
				rsbuf.append(extractedDtoList.get(i).getId());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getName());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getGender());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getAge());
				rsbuf.append(COMMA);
				rsbuf.append(extractedDtoList.get(i).getCourse());

				//加工作成した1レコード分のデータを表示
				System.out.println(rsbuf.toString());

			}
		} else {
			System.out.println("[INFO]該当のユーザー情報を取得できませんでした" ) ;
		}

	}
}
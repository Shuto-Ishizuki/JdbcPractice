package dao;

/**----------------------------------------------------------------------*
 *■■■Sample4_02_1_Upd_Mainクラス■■■
 *概要：メイン（ユーザーの情報更新）
 *----------------------------------------------------------------------**/
public class Sample4_02_1_Upd_Main {
	public static void main (String[] args) {

		//コマンドラインから更新するユーザーの情報を受け取りDTOに格納
		Sample4_02_1_Common_DTO subscribeDto = new Sample4_02_1_Common_DTO();
		subscribeDto.setId(     Integer.parseInt(args[0])  );
		subscribeDto.setName(   args[1]                    );
		subscribeDto.setGender( args[2]                    );
		subscribeDto.setAge(    Integer.parseInt(args[3])  );
		subscribeDto.setCourse( args[4]                    );

		//ビジネスロジック（ユーザーの登録）クラスのインスタンス化＆メソッド起動
		Sample4_02_1_Upd_BusinessLogic blUpd = new Sample4_02_1_Upd_BusinessLogic();
		blUpd.update( subscribeDto );

	}
}

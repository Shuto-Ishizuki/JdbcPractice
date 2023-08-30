package dao;

/**----------------------------------------------------------------------*
 *■■■Sample4_02_1_Sel_Mainクラス■■■
 *概要：メイン（ユーザーの情報抽出）
 *----------------------------------------------------------------------**/
public class Sample4_02_1_Sel_Main {
	public static void main (String[] args) {

		//コマンドラインから情報抽出したいユーザーのIDを受け取る
		int extractionId = 1;

		//ビジネスロジック（ユーザーの情報抽出）クラスのインスタンス化＆メソッド起動
		Sample4_02_1_Sel_BusinessLogic blSel = new Sample4_02_1_Sel_BusinessLogic();
		blSel.extract( extractionId );
	}
}

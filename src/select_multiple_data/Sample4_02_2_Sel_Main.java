package select_multiple_data;

/**----------------------------------------------------------------------*
 *■■■Sample4_02_2_Sel_Mainクラス■■■
 *概要：メイン（ユーザーの情報抽出）
 *----------------------------------------------------------------------**/
public class Sample4_02_2_Sel_Main {
	public static void main (String[] args) {

		//ビジネスロジック（ユーザーの情報抽出）クラスのインスタンス化＆メソッド起動
		Sample4_02_2_Sel_BusinessLogic blSel = new Sample4_02_2_Sel_BusinessLogic();
		blSel.extractAll();

	}
}

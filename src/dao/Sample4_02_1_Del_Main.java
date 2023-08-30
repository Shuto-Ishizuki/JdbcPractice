package dao;

/**----------------------------------------------------------------------*
 *■■■Sample4_02_1_Del_Mainクラス■■■
 *概要：メイン（ユーザーの登録解除）
 *----------------------------------------------------------------------**/
public class Sample4_02_1_Del_Main {
	public static void main (String[] args) {

		//コマンドラインから登録解除したいユーザーのIDを受け取る
		int unsubscribeId = Integer.parseInt(args[0]);

		//ビジネスロジック（ユーザーの登録解除）クラスのインスタンス化＆メソッド起動
		Sample4_02_1_Del_BusinessLogic blDel = new Sample4_02_1_Del_BusinessLogic();
		blDel.unsubscribe( unsubscribeId );

	}
}

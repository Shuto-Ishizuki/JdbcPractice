package exercise.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileSample2 {

	public static void main(String[] args) {

		//定数の定義
		final String FILE_PATH = "C:\\WorkSpace\\WriteFileSample.csv";      //書き出し用ファイルのパス情報
		final String NEW_LINE  = System.getProperty("line.separator");    //改行

	    try{
	        //書き出し用ファイルの読み込み
	    	File file = new File( FILE_PATH );

	        //書き出し用ファイルをFileWriterにセット（既存データへの追加）
	        FileWriter fileWriter = new FileWriter(file , true);

	        //書き出しを実行（既存データへの追加）
	        fileWriter.write( NEW_LINE );
	        fileWriter.write( NEW_LINE );
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("【サンプル】FileWriterを用いた書き出し（既存データへの追加）");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("FileWriterのインスタンス化時、コンストラクタ第2引数にtrueを設定すると追加書き出しの設定となります。");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("無指定、falseの場合は上書きとなります。");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("---------------- WriteFileSample2 END ----------------");

	        //書き出し用ファイルを閉じる
	        fileWriter.close();

	      }catch(IOException e){
	        System.out.println(e);
	      }
	}
}

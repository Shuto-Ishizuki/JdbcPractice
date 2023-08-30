package exercise.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileSample1 {

	public static void main(String[] args) {

		//定数の定義
		final String FILE_PATH = "C:\\WorkSpace\\WriteFileSample.csv";    //書き出し用ファイルのパス情報	"C:\Users\H1017363\workfile\JdbcPractice"
		final String NEW_LINE  = System.getProperty("line.separator");    //改行
		final String COMMA     = ",";                                     // コンマ

	    try{
	        //書き出し用ファイルの読み込み
	    	File file = new File( FILE_PATH );

	        //書き出し用ファイルをFileWriterにセット
	        FileWriter fileWriter = new FileWriter(file);

	        //書き出しを実行
	        fileWriter.write("【サンプル】FileWriterを用いた書き出し");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("このファイルはFileWriterによる書き出し先ファイルです。");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("このファイルの格納ディレクトリおよびファイル名は定数「FILE_PATH」の指定値と一致している必要があります。");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("FileWriter使用時はIOExceptionで例外処理をする必要があります。");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("FileWriter使用後は必ずcloseでファイルへの接続を解除しましょう。");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("CSVファイルは本来下記のようにコンマを使って表データを表現する役割で使用します。");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("名前");
	        fileWriter.write( COMMA );
	        fileWriter.write("年齢");
	        fileWriter.write( COMMA );
	        fileWriter.write("性別");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("モコ");
	        fileWriter.write( COMMA );
	        fileWriter.write("5");
	        fileWriter.write( COMMA );
	        fileWriter.write("メス");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("タロー");
	        fileWriter.write( COMMA );
	        fileWriter.write("7");
	        fileWriter.write( COMMA );
	        fileWriter.write("オス");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("グレース");
	        fileWriter.write( COMMA );
	        fileWriter.write("4");
	        fileWriter.write( COMMA );
	        fileWriter.write("オス");
	        fileWriter.write( NEW_LINE );
	        fileWriter.write( NEW_LINE );
	        fileWriter.write("---------------- WriteFileSample1 END ----------------");

	        //書き出し用ファイルを閉じる
	        fileWriter.close();

	      }catch(IOException e){
	        System.out.println(e);
	      }
	}
}

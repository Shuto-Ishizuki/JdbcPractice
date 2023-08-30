package select_multiple_data;

/**----------------------------------------------------------------------*
 *■■■Sample4_02_2_Common_DTOクラス■■■
 *概要：DTO（uzuz_memberテーブル）
 *----------------------------------------------------------------------**/
public class Sample4_02_2_Common_DTO {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int    id     ;  //ID
	private String name   ;  //名前
	private String gender ;  //性別（男：M／女：F）
	private int    age    ;  //年齢
	private String course ;  //コース

	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：id）
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	//getter/setter（対象フィールド：name）
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	//getter/setter（対象フィールド：gender）
	public  String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}

	//getter/setter（対象フィールド：age）
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	//getter/setter（対象フィールド：course）
	public String getCourse() {return course;}
	public void setCourse(String course) {this.course = course;}
}
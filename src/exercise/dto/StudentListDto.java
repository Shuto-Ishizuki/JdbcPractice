package exercise.dto;

public class StudentListDto {
    // 名前
    private String studentName;
    // 性別
    private String  gender;
    // 年齢
    private int age;
    // 職歴
    private String career;
    // 支店名
    private String branchName;
    // 受講講座
    private String courseName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCareerMon() {
        return career;
    }

    public void setCareerMon(String career) {
        this.career = career;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

package exercise.main;

import exercise.business_logic.StudentListBl;
import exercise.business_logic.StudentsCountByCourseBL;

public class StudentInfoMain {
    public static void main(String args[]) {
        // ① 全生徒の情報をCSVに書き込み
        StudentListBl studentListBl = new StudentListBl();
        studentListBl.extractAllStudent();

        // ② 受講者数の情報をCSVに追加書き込み
        StudentsCountByCourseBL studentsCountByCourseBL = new StudentsCountByCourseBL();
        studentsCountByCourseBL.extractNumberOfStudents();
    }
}

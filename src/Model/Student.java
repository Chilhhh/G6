package Model;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-19-12:44
 * @Description: entity class of student
 */
public class Student {
    private int studentID;
    private String studentName;
    private String studentCourse;
    private String studentPassword;
    private String studentEmail;

    public Student() {
    }

    public Student(int studentID, String studentName, String studentCourse, String studentPassword, String studentEmail) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentCourse = studentCourse;
        this.studentPassword = studentPassword;
        this.studentEmail = studentEmail;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}

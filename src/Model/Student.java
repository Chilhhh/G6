package Model;
import java.util.ArrayList;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-19-12:44
 * @Description: entity class of student
 */
public class Student {
    private String studentID;
    private String studentName;
    private ArrayList<Course> courses;
    private String studentPassword;
    private String studentEmail;

    public Student() {
    }

    public Student(String studentID, String studentName, String studentCourse, String studentPassword, String studentEmail) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.courses = new ArrayList<Course>();
        this.studentPassword = studentPassword;
        this.studentEmail = studentEmail;
    }

    public Student(String studentName, int studentID, String studentPassword, String studentEmail) {
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
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

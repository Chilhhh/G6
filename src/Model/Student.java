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
    private ArrayList<Course> Stucourses;
    private String studentPassword;
    private String studentEmail;

    private String studentGender;

    private String studentAge;
    public Student() {
        this.Stucourses = new ArrayList<>();
    }

    public Student(String studentID, String studentName, String studentCourse, String studentPassword, String studentEmail) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.Stucourses = new ArrayList<>();
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

    public void addStudentCourse(Course course) {
        this.Stucourses.add(course);
    }

    public void removeStudentCourse(Course course) {
        this.Stucourses.remove(course);
    }

    public ArrayList<Course> getStudentCourses() {
        return this.Stucourses;
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

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    public double calculateAverageScore() {
        double totalScore = 0;
        int totalCredit = 0;
        for (Course course : Stucourses) {
            totalScore += course.getCourseScore() * course.getCourseCredit();
            totalCredit += course.getCourseCredit();
        }
        return totalScore / totalCredit;
    }

    public double calculateTotalCredit() {
        int totalCredit = 0;
        for (Course course : Stucourses) {
            totalCredit += course.getCourseCredit();
        }
        return totalCredit;
    }
}
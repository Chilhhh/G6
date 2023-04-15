package Model;
/**
 * @Author: Zongyou Yang
 * @Date: 2023-03-28-12:44
 * @Description: entity class of student
 */

public class Course {
    private String courseName;
    private String courseTeacher;
    private int courseTerm;
    private int courseCredit;
    private int courseScore;
    private String courseDescribe;
    private String courseID;

    public Course() {
    }

    public Course(String courseID, String courseName, String courseTeacher, int courseTerm, int courseCredit, int courseScore, String courseDescribe) {

        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseTerm = courseTerm;
        this.courseCredit = courseCredit;
        this.courseScore = courseScore;
        this.courseDescribe = courseDescribe;
        this.courseID=courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public int getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(int courseTerm) {
        this.courseTerm = courseTerm;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(int courseScore) {
        this.courseScore = courseScore;
    }

    public String getCourseDescribe() {
        return courseDescribe;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID){
        this.courseID=courseID;
    }
    public void setCourseDescribe(String courseDescribe) {
        this.courseDescribe = courseDescribe;
    }
}

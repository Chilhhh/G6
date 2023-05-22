package Model;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
  //  private BufferedImage studentImage = setDefaultImage();
    private String studentGender;
    private String studentPhone;


    private String studentCompetition;
    private String studentActiviy;
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
    public Student(String studentID,  String studentPassword) {
        this.studentID= studentID;
        this.studentPassword = studentPassword;
    }

    public Student(String studentName, int studentID, String studentPassword, String studentEmail) {
    }



    public String getStudentActiviy() {
        return studentActiviy;
    }

    public void setStudentActiviy(String studentActiviy) {
        this.studentActiviy = studentActiviy;
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

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentCompetition() {
        return studentCompetition;
    }

    public void setStudentCompetition(String studentCompetition) {
        this.studentCompetition = studentCompetition;
    }

    public String getStudentActivity() {
        return studentActiviy;
    }

    public void setStudentActivity(String studentActiviy) {
        this.studentActiviy = studentActiviy;
    }

//    public BufferedImage getStudentImage() {
//
//        return studentImage;
//    }
//
//    public void setStudentImage(BufferedImage studentImage) {
//        this.studentImage = studentImage;
//    }

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

//    private BufferedImage setDefaultImage() {
//        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.getGraphics();
//        g.setColor(Color.GRAY);
//        g.fillRect(0, 0, 100, 100);
//        g.dispose();
//        return image;
//    }
//    public File saveImagetoFile(){
//        BufferedImage image = getStudentImage();
//        File file = new File("output.png");
//        try {
//            ImageIO.write(image, "png", file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", Stucourses=" + Stucourses +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", studentCompetition='" + studentCompetition + '\'' +
                ", studentActiviy='" + studentActiviy + '\'' +
                ", studentAge='" + studentAge + '\'' +
                '}';
    }
}
package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class testYZYv1 {
    public static void main(String[] args) {
        String studentName = null, studentPassword = null, studentEmail = null;
        int studentID = 0;
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            /*read a txt file*/
            File file = new File("导入文件.txt");
            Scanner scanner = new Scanner(file);
            String line;
            String courseID, courseName, courseTeacher, courseDescribe;
            int courseTerm, courseCredit, courseScore;

            // Read each line of the file
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                // If the line starts with "Name:", it is the student's name
                if (line.startsWith("Name:")) {
                    studentName = line.substring(5);
                }
                // If the line starts with "StudentID:", it is the student's ID
                else if (line.startsWith("StudentID:")) {
                    studentID = Integer.parseInt(line.substring(10));
                }
                // If the line starts with "Password:", it is the student's password
                else if (line.startsWith("Password:")) {
                    studentPassword = line.substring(9);
                }
                // If the line starts with "Mail:", it is the student's email
                else if (line.startsWith("Mail:")) {
                    studentEmail = line.substring(5);
                }
                // If the line starts with "Course", it is a course
                else if (line.startsWith("Course")) {
                    // Read the course information
                    courseID = scanner.nextLine().substring(10);
                    courseName = scanner.nextLine().substring(12);
                    courseTeacher = scanner.nextLine().substring(15);
                    courseTerm = Integer.parseInt(scanner.nextLine().substring(11));
                    courseCredit = Integer.parseInt(scanner.nextLine().substring(13));
                    courseScore = Integer.parseInt(scanner.nextLine().substring(12));
                    courseDescribe = scanner.nextLine().substring(16);

                    // Create a new Course object and add it to the courses ArrayList
                    Course course = new Course(courseID, courseName, courseTeacher, courseTerm, courseCredit, courseScore, courseDescribe);
                    courses.add(course);
                }
            }

            // Create a new Student object with the information read from the file
            Student student = new Student(studentName, studentID, studentPassword, studentEmail);


            // Print the student's information and the courses they are taking
            System.out.println("Student Information:");
            System.out.println("Name: " + student.getStudentName());
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Password: " + student.getStudentPassword());
            System.out.println("Email: " + student.getStudentEmail());
            System.out.println("Courses:");
            for (Course course : student.getCourses()) {
                System.out.println(course.getCourseName() + " - " + course.getCourseTeacher());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}

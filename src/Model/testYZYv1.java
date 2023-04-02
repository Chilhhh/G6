package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class testYZYv1 {
    public static void main(String[] args) throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        try {
            File file = new File("info.txt");
            Scanner scanner = new Scanner(file);

            String line;
            Student student = null;
            Course course = null;
            boolean isStudent = false;
            boolean isCourse = false;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.equals("Student")) {
                    student = new Student();
                    isStudent = true;
                    isCourse = false;
                } else if (line.equals("Course")) {
                    course = new Course();
                    isCourse = true;
                    isStudent = false;
                }
                if (isStudent) {
                    String[] parts = line.split(":");
                    switch (parts[0].trim()) {
                        case "Name":
                            student.setStudentName(parts[1].trim());
                            break;
                        case "StudentID":
                            student.setStudentID(parts[1].trim());
                            break;
                        case "Password":
                            student.setStudentPassword(parts[1].trim());
                            break;
                        case "Mail":
                            student.setStudentEmail(parts[1].trim());
                            students.add(student);
                            break;
                        default:
                            break;
                    }
                } else if (isCourse) {
                    String[] parts = line.split(":");
                    switch (parts[0].trim()) {
                        case "courseID":
                            course.setCourseID(parts[1].trim());
                            break;
                        case "CourseName":
                            course.setCourseName(parts[1].trim());
                            break;
                        case "courseTeacher":
                            course.setCourseTeacher(parts[1].trim());
                            break;
                        case "courseTerm":
                            course.setCourseTerm(Integer.parseInt(parts[1].trim()));
                            break;
                        case "courseCredit":
                            course.setCourseCredit(Integer.parseInt(parts[1].trim()));
                            break;
                        case "courseScore":
                            course.setCourseScore(Integer.parseInt(parts[1].trim()));
                            break;
                        case "courseDescribe":
                            course.setCourseDescribe(parts[1].trim());
                            courses.add(course);
                            break;
                        default:
                            break;
                    }
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Print the loaded students and courses
        for (Student student : students) {
            System.out.println("Student Name: " + student.getStudentName());
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Student Password: " + student.getStudentPassword());
            System.out.println("Student Email: " + student.getStudentEmail());
            System.out.println();
        }

        for (Course course : courses) {
            System.out.println("Course ID: " + course.getCourseID());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Course Teacher: " + course.getCourseTeacher());
            System.out.println("Course Term: " + course.getCourseTerm());
            System.out.println("Course Credit: " + course.getCourseCredit());
            System.out.println("Course Score: " + course.getCourseScore());
            System.out.println("Course Description: " + course.getCourseDescribe());
            System.out.println();
        }
        pdfProducer.pdfProducer(courses);

    }

}

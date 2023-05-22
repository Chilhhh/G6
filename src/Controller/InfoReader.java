package Controller;

import DAO.JSONUtils;
import Model.Course;
import Model.Courses;
import Model.Student;
import Model.Students;
import View.Display_Table_Panel;
import View.menuPage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.View;

/**
 * @Author: Zongyou Yang
 * @Date: 2023-03-28-12:44
 * @Description: entity class of student
 */
public class InfoReader {

    Students students;
    Courses courses;

    public InfoReader() {

        students = new Students();
        courses =  new Courses();
    }

    public Display_Table_Panel readit( menuPage jFrame) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setDialogTitle("Open Folder");
        //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
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
                                break;
                            case "Gender":
                                student.setStudentGender(parts[1].trim());
                                break;
                            case "Age":
                                student.setStudentAge(parts[1].trim());
                                students.addStudent(student);
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
                                courses.addCourse(course);
                                student.addStudentCourse(course);
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
        } else {
            System.out.println("Open command cancelled by user.");
        }
        JSONUtils.createJSONFile(students.getStudents(),students.getJsonfilePath());
        JSONUtils.createJSONFile(courses.getCourses(),courses.getJsonfilePath());
        // Print the loaded students and courses
        for (Student student : students.getStudents()) {
            System.out.println("Student Name: " + student.getStudentName());
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Student Password: " + student.getStudentPassword());
            System.out.println("Student Email: " + student.getStudentEmail());
            System.out.println("Student Courses: "+ student.getStudentCourses());
            System.out.println("Student Gender: "+ student.getStudentGender());
            System.out.println("Student Age: "+ student.getStudentAge());
            System.out.println();
        }

        for (Course course : courses.getCourses()) {
            System.out.println("Course ID: " + course.getCourseID());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Course Teacher: " + course.getCourseTeacher());
            System.out.println("Course Term: " + course.getCourseTerm());
            System.out.println("Course Credit: " + course.getCourseCredit());
            System.out.println("Course Score: " + course.getCourseScore());
            System.out.println("Course Description: " + course.getCourseDescribe());
            System.out.println();
        }
        //testYZYtem gui = new testYZYtem(students, courses);
        Display_Table_Panel gui = new Display_Table_Panel(students.getStudents(), courses.getCourses(),jFrame);
        return gui;
    }









}

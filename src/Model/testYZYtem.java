package Model;



import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


/**
 * @Author: Zongyou Yang
 * @Date: 2023-03-28-12:44
 * @Description: entity class of student
 */

public class testYZYtem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel,panelBottom;
    private JTable studentTable, courseTable,studentAvgTable;


    private JButton addCourseButton, deleteCourseButton;
    private JTextField courseIDField, courseNameField, courseTeacherField, courseTermField, courseCreditField,
            courseScoreField, courseDescriptionField;
    private JLabel courseIDLabel, courseNameLabel, courseTeacherLabel, courseTermLabel, courseCreditLabel,
            courseScoreLabel, courseDescriptionLabel;
    private JDialog addCourseDialog;
    JLayeredPane layeredPane = new JLayeredPane();
    public testYZYtem(ArrayList<Student> students, ArrayList<Course> courses) {
        setTitle("Student and Course Information");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 204, 210)); //设置背景颜色

        panelBottom = new JPanel();
        panelBottom.setLayout(new BorderLayout());
        panelBottom.setBackground(new Color(255, 204, 210)); //设置背景颜色




        // Create student table
        String[] studentColumnNames = { "Name", "ID", "Password", "Email" };
        DefaultTableModel studentModel = new DefaultTableModel(studentColumnNames, 0);
        for (Student student : students) {
            Object[] rowData = { student.getStudentName(), student.getStudentID(),
                    student.getStudentPassword(), student.getStudentEmail() };
            studentModel.addRow(rowData);
        }
        studentTable = new JTable(studentModel);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        panel.add(studentScrollPane, BorderLayout.WEST);

        // Create course table
        String[] courseColumnNames = { "ID", "Name", "Teacher", "Term", "Credit", "Score", "Description" };
        DefaultTableModel courseModel = new DefaultTableModel(courseColumnNames, 0);
        for (Course course : courses) {
            Object[] rowData = { course.getCourseID(), course.getCourseName(), course.getCourseTeacher(),
                    course.getCourseTerm(), course.getCourseCredit(), course.getCourseScore(),
                    course.getCourseDescribe() };
            courseModel.addRow(rowData);
        }
        courseTable = new JTable(courseModel);
        JScrollPane courseScrollPane = new JScrollPane(courseTable);
        panel.add(courseScrollPane, BorderLayout.CENTER);

        //add(panel);
        //setVisible(true);


        // calculate average score and total credit
        String[] AvgColumnNames = { "Average_score", "total_credit" };
        DefaultTableModel AvgModel = new DefaultTableModel(AvgColumnNames, 0);
        for (Student student : students) {
            double averageScore = student.calculateAverageScore();
            Object[] rowData = { averageScore, student.calculateTotalCredit() };
            AvgModel.addRow(rowData);

        }
        studentAvgTable = new JTable(AvgModel);
        JScrollPane studentAvgPane = new JScrollPane(studentAvgTable);
        panel.add(studentAvgPane, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);



        //add course
        addCourseButton = new JButton("Add Course");
        addCourseButton.setBounds(100, 100, 100, 50);
        addCourseButton.addActionListener(e -> {
            addCourseDialog = new JDialog(this, "Add Course", true);
            addCourseDialog.setSize(400, 400);
            addCourseDialog.setLocationRelativeTo(null);
            addCourseDialog.setLayout(new BorderLayout());
            addCourseDialog.setResizable(false);
            addCourseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            addCourseDialog.setVisible(true);
        });
        panelBottom.add(addCourseButton, BorderLayout.SOUTH);
        //delete course
        deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.setBounds(100, 100, 100, 50);
        deleteCourseButton.addActionListener(e -> {
            int row = courseTable.getSelectedRow();
            if (row != -1) {
                courseModel.removeRow(row);
            }
        });
        panelBottom.add(deleteCourseButton, BorderLayout.NORTH);

        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setBounds(100, 100, 100, 50);
        courseIDField = new JTextField();
        courseIDField.setBounds(100, 100, 100, 50);
        courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(100, 100, 100, 50);
        courseNameField = new JTextField();
        courseNameField.setBounds(100, 100, 100, 50);
        courseTeacherLabel = new JLabel("Course Teacher:");
        courseTeacherLabel.setBounds(100, 100, 100, 50);
        courseTeacherField = new JTextField();
        courseTeacherField.setBounds(100, 100, 100, 50);
        courseTermLabel = new JLabel("Course Term:");
        courseTermLabel.setBounds(100, 100, 100, 50);
        courseTermField = new JTextField();
        courseTermField.setBounds(100, 100, 100, 50);
        courseCreditLabel = new JLabel("Course Credit:");
        courseCreditLabel.setBounds(100, 100, 100, 50);
        courseCreditField = new JTextField();
        courseCreditField.setBounds(100, 100, 100, 50);
        courseScoreLabel = new JLabel("Course Score:");
        courseScoreLabel.setBounds(100, 100, 100, 50);
        courseScoreField = new JTextField();
        courseScoreField.setBounds(100, 100, 100, 50);
        courseDescriptionLabel = new JLabel("Course Description:");
        courseDescriptionLabel.setBounds(100, 100, 100, 50);
        courseDescriptionField = new JTextField();
        courseDescriptionField.setBounds(100, 100, 100, 50);
        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.setBounds(100, 100, 100, 50);
        addCourseButton.addActionListener(e -> {
            String courseID = courseIDField.getText();
            String courseName = courseNameField.getText();
            String courseTeacher = courseTeacherField.getText();
            String courseTerm = courseTermField.getText();
            String courseCredit = courseCreditField.getText();
            String courseScore = courseScoreField.getText();
            String courseDescription = courseDescriptionField.getText();
            Object[] rowData = { courseID, courseName, courseTeacher, courseTerm, courseCredit, courseScore,
                    courseDescription };
            courseModel.addRow(rowData);
            addCourseDialog.dispose();
        });
        addCourseDialog.add(courseIDLabel);
        addCourseDialog.add(courseIDField);
        addCourseDialog.add(courseNameLabel);
        addCourseDialog.add(courseNameField);
        addCourseDialog.add(courseTeacherLabel);
        addCourseDialog.add(courseTeacherField);
        addCourseDialog.add(courseTermLabel);
        addCourseDialog.add(courseTermField);
        addCourseDialog.add(courseCreditLabel);
        addCourseDialog.add(courseCreditField);
        addCourseDialog.add(courseScoreLabel);
        addCourseDialog.add(courseScoreField);
        addCourseDialog.add(courseDescriptionLabel);
        addCourseDialog.add(courseDescriptionField);
        addCourseDialog.add(addCourseButton);




        //panel.add(panelBottom, BorderLayout.EAST);





        //sort the table
        studentTable.setAutoCreateRowSorter(true);
        courseTable.setAutoCreateRowSorter(true);
        studentAvgTable.setAutoCreateRowSorter(true);
        // Make GUI more beautiful and rich
        studentTable.setRowHeight(30);
        courseTable.setRowHeight(30);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        courseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        studentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        courseTable.setFont(new Font("Arial", Font.PLAIN, 16));
        studentAvgTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        studentAvgTable.setFont(new Font("Arial", Font.PLAIN, 16));
        studentAvgTable.setRowHeight(30);
    }




}

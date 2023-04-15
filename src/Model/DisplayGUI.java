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

public class DisplayGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTable studentTable, courseTable,studentAvgTable;
    JLayeredPane layeredPane = new JLayeredPane();
    public DisplayGUI(ArrayList<Student> students, ArrayList<Course> courses) {
        setTitle("Student and Course Information");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 204)); //设置背景颜色
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

        add(panel);
        setVisible(true);



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

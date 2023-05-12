package View;

import Model.Course;
import Model.Student;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 * @Author: Zongyou Yang
 * @Date: 2023-03-28-12:44
 * @Description: entity class of student
 */

public class Display_Table_Panel extends JLayeredPane {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTable studentTable, courseTable,studentAvgTable;
    private menuPage mainJFrame=null;
    JLayeredPane layeredPane = new JLayeredPane();
    public Display_Table_Panel(ArrayList<Student> students, ArrayList<Course> courses,menuPage jFrame) {
       // setTitle("Student and Course Information");
       // setSize(800, 600);
      //  setLocationRelativeTo(null);
        mainJFrame=jFrame;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 204)); //设置背景颜色

        JButton goBackJButton = new JButton("Go Back");
        goBackJButton.setBounds( 200,  50, 100, 30);
        goBackJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickGoBackJButton();
            }
        });
        panel.add(goBackJButton,BorderLayout.NORTH);



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
        panel.add(studentScrollPane);

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
        panel.add(courseScrollPane, BorderLayout.WEST);
       // panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBounds(0, 0, 800,600);




        String[] AvgColumnNames = { "Average_score", "total_credit" };
        DefaultTableModel AvgModel = new DefaultTableModel(AvgColumnNames, 0);
        for (Student student : students) {
            double averageScore = student.calculateAverageScore();
            Object[] rowData = { averageScore, student.calculateTotalCredit() };
            AvgModel.addRow(rowData);

        }
        studentAvgTable = new JTable(AvgModel);
        JScrollPane studentAvgPane = new JScrollPane(studentAvgTable);
        panel.add(studentAvgPane, BorderLayout.CENTER);

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


        this.add(panel, (Integer)2);
        setVisible(true);
    }

    public void clickGoBackJButton() {
        //调回原大小
        mainJFrame.setSize(500, 500);
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.comeBack();
    }


}

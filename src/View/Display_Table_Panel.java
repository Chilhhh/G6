package View;

import Model.Course;
import Model.Student;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


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

        panel.setSize(1000, 600);
        JButton goBackJButton = new JButton("Go Back");
        //goBackJButton.setBounds( 200,  50, 500, 30);
        goBackJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickGoBackJButton();
            }
        });
        //panel.add(goBackJButton,BorderLayout.NORTH);



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
        //panel.add(studentScrollPane, BorderLayout.WEST);

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
        //panel.add(courseScrollPane, BorderLayout.CENTER);
       // panel.setLayout(null);





        String[] AvgColumnNames = { "Average_score", "total_credit" };
        DefaultTableModel AvgModel = new DefaultTableModel(AvgColumnNames, 0);
        for (Student student : students) {
            double averageScore = student.calculateAverageScore();
            Object[] rowData = { averageScore, student.calculateTotalCredit() };
            AvgModel.addRow(rowData);

        }
        studentAvgTable = new JTable(AvgModel);
        JScrollPane studentAvgPane = new JScrollPane(studentAvgTable);






        // Create a panel to hold both components
        JPanel tablePanel = new JPanel(new GridLayout(1, 2));
        //JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.add(studentScrollPane);
        tablePanel.add(studentAvgPane);

        //panel.add(tablePanel, BorderLayout.CENTER);

        //panel.add(studentScrollPane, BorderLayout.EAST);
        //panel.add(courseScrollPane, BorderLayout.CENTER);


        JPanel avgPanel =new JPanel(new BorderLayout());
        avgPanel.add(tablePanel, BorderLayout.CENTER);
        avgPanel.add(goBackJButton,BorderLayout.SOUTH);
        avgPanel.setOpaque(false);

        panel.add(courseScrollPane, BorderLayout.NORTH);
        panel.add(avgPanel, BorderLayout.CENTER);


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

        panel.setOpaque(false);
        panel.setBounds(0, 0, 1000,600);
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

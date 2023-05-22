package View;

import Model.Course;
import Model.Student;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;



public class Display_Table_Panel extends JLayeredPane  {

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

        panel.setSize(1200, 800);
        JButton goBackJButton = new JButton("Go Back");
        //goBackJButton.setBounds( 200,  50, 500, 30);
        goBackJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickGoBackJButton();
            }
        });
        //panel.add(goBackJButton,BorderLayout.NORTH);


        JPanel pieChartPanel = new JPanel();
        JButton visualizeButton = new JButton("Visual Analysis of Scores");
        // add an action listener to the button to show the pie chart panel when clicked

        DefaultPieDataset dataset = new DefaultPieDataset();

        // Loop through the courses array list to get each course's score
        ArrayList<Course> sortedCourses = new ArrayList<>(courses);
        Collections.sort(sortedCourses, Comparator.comparingDouble(Course::getCourseScore).reversed());

        // Loop through the sorted courses to add them to the dataset
        for (Course course : sortedCourses) {
            dataset.setValue(course.getCourseName(), course.getCourseScore());
        }

        // Create a JFreeChart object using the dataset
        JFreeChart chart = ChartFactory.createPieChart("Course Scores", dataset, false, true, true);


        //Customize the appearance of the chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
        plot.setLabelGap(0.02);
        plot.setIgnoreZeroValues(true);
        plot.setIgnoreNullValues(true);
        //plot.setLabelBackgroundPaint(new Color(211, 227, 105));





        // Create a ChartPanel object using the chart
        ChartPanel chartPanel = new ChartPanel(chart);

        // Add the ChartPanel to the pieChartPanel
        pieChartPanel.add(chartPanel);

        // Set the pieChartPanel to visible
        pieChartPanel.setVisible(true);




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
        JPanel tablePanel = new JPanel(new GridLayout(2, 1));
        //JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.add(studentScrollPane);
        tablePanel.add(studentAvgPane);

        //panel.add(tablePanel, BorderLayout.CENTER);

        //panel.add(studentScrollPane, BorderLayout.EAST);
        //panel.add(courseScrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(goBackJButton);
        buttonPanel.add(visualizeButton);


        JPanel avgPanel =new JPanel(new BorderLayout());
        avgPanel.add(tablePanel, BorderLayout.CENTER);
        avgPanel.add(buttonPanel,BorderLayout.SOUTH);
        avgPanel.setOpaque(false);

        pieChartPanel.setSize(300, 100); // Set the width and height to 300 pixels
        //pieChartPanel.setLocation(800, 0); // Set the location of the pie chart panel


        panel.add(courseScrollPane, BorderLayout.NORTH);
        panel.add(avgPanel, BorderLayout.CENTER);
        panel.add(pieChartPanel, BorderLayout.EAST);



        pieChartPanel.setVisible(true);

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
        panel.setBounds(0, 0, 1200,830);
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

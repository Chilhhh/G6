package Service;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Container;// 容器

import java.awt.event.ActionListener;// 动作监听
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;// 动作事件

import javax.swing.JFrame;// 窗体
import javax.swing.JComboBox;// 下拉框列表
import javax.swing.ComboBoxModel;// 下拉框列表模型
import javax.swing.DefaultComboBoxModel;// 默认下拉框列表模型
import javax.swing.JButton;// 按钮
import javax.swing.JOptionPane;// 选择 窗格
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.JSONUtils;
import Model.Course;
import Model.Students;
import com.alibaba.fastjson.JSON;
import com.itextpdf.layout.Document;

import Model.Student;

public class pdfGUI extends JPanel {
    // 声明窗体，面板及控件

    public static void pdfGUI() throws Exception {
        Students students = new Students();
        students.setStudents( (ArrayList<Student>)JSON.parseArray(JSONUtils. readJSONFile(students.getJsonfilePath()), Student.class));


        JFrame jframe;
        JLabel jlabel;
        GridBagLayout gridbag;
        GridBagConstraints constraints;
        JPasswordField jpfield1;
        JButton jbutton1, jbutton2, jbutton3;
        JPanel jpanel;
        jframe = new JFrame();
        jlabel = new JLabel();
        jpfield1 = new JPasswordField();
        gridbag = new GridBagLayout();
        jbutton1 = new JButton();
        jbutton2 = new JButton();
        jbutton3 = new JButton();
        jframe.setTitle("Print");
            /**
             * 
             * 设置JPanel背景
             * 
             */

            jpanel = new JPanel() {

                @Override

                protected void paintComponent(Graphics g) {

                    super.paintComponent(g);
                    //ImageIcon img = new ImageIcon(GUITest.class.getResource("ddmbg.jpg"));
                    //img.paintIcon(this, g, 0, 0);
                }

            };
            // 为JLabel,JButton初始化文本

            jlabel.setText("简历模板");
            jbutton1.setText("预览");
            jbutton2.setText("取消");
            jbutton3.setText("打印");
            // 设置显示位置及屏幕大小500*400

            jframe.setBounds(450, 240, 400, 240);
            // jpanel采用GridBagLayout布局管理器

            jpanel.setOpaque(false);
            jpanel.setLayout(gridbag);
            // 初始化用户名label，并添加该控件到画板

            constraints = getGridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(10, 0, 10, 0), 0, 0);
            gridbag.setConstraints(jlabel, constraints);
            jpanel.add(jlabel);
            // 初始化用户名文本框，并添加该组件到画板

            
            JComboBox<String> comboBox=new JComboBox<>();
            comboBox.addItem("career");// 下拉框列表添加内容。Item（条款）
            comboBox.addItem("school");// 下拉框列表添加内容。Item（条款）
            
            comboBox.setBounds(15,10,100,20);// 下拉框的坐标、尺寸。绝对布局
            jpanel.add(comboBox);

            comboBox.setEditable(true);// 列表框可编辑
        
            // 初始化打印按钮，并添加该控件到画板


            jbutton3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(comboBox.getSelectedItem().equals("career")){
                        try {
                            pdfProducer_career.pdfProducer(students.getStudents());
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    else {
                        try {
                            pdfProducer_school.pdfProducer(students.getStudents());
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(null,
                    "Print successfully"
                    );
                }

            });
            
            constraints = getGridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(10, 0, 10, 0), 0, 0);
            gridbag.setConstraints(jbutton3, constraints);
            jpanel.add(jbutton3);

            // 初始化预览按钮

            jbutton1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(comboBox.getSelectedItem().equals("career")){
                        try {
                            pdfProducer_career.pdfProducer(students.getStudents());
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    else {
                        try {
                            pdfProducer_school.pdfProducer(students.getStudents());
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    String filePath = "demo.pdf";
 
                    // open the file
                    /* 
                    Document document = new Document();
                    try {
                        document.setFile(filePath);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } 
                    document.dispose();
                    */

                }
            });

            constraints = getGridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(10, 0, 10, 0), 0, 0);
            gridbag.setConstraints(jbutton1, constraints);
            jpanel.add(jbutton1);

            // 初始化取消按钮

            jbutton2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                    //jpanel.setVisible(false);
                }

            });

            constraints = getGridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(10, 0, 10, 0), 0, 0);
            gridbag.setConstraints(jbutton2, constraints);
            jpanel.add(jbutton2);
            // 添加画板到窗体

            jframe.add(jpanel);
            // 窗体初始化完成
            jframe.setVisible(true);
    

        }

        
    

    private static GridBagConstraints getGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight,
                double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady) {

            return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets,
                    ipadx, ipady);
        
    }
}
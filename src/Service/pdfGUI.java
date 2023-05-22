package Service;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Container;// ����

import java.awt.event.ActionListener;// ��������
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;// �����¼�

import javax.swing.JFrame;// ����
import javax.swing.JComboBox;// �������б�
import javax.swing.ComboBoxModel;// �������б�ģ��
import javax.swing.DefaultComboBoxModel;// Ĭ���������б�ģ��
import javax.swing.JButton;// ��ť
import javax.swing.JOptionPane;// ѡ�� ����
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
    // �������壬��弰�ؼ�

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
             * ����JPanel����
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
            // ΪJLabel,JButton��ʼ���ı�

            jlabel.setText("����ģ��");
            jbutton1.setText("Ԥ��");
            jbutton2.setText("ȡ��");
            jbutton3.setText("��ӡ");
            // ������ʾλ�ü���Ļ��С500*400

            jframe.setBounds(450, 240, 400, 240);
            // jpanel����GridBagLayout���ֹ�����

            jpanel.setOpaque(false);
            jpanel.setLayout(gridbag);
            // ��ʼ���û���label������Ӹÿؼ�������

            constraints = getGridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(10, 0, 10, 0), 0, 0);
            gridbag.setConstraints(jlabel, constraints);
            jpanel.add(jlabel);
            // ��ʼ���û����ı��򣬲���Ӹ����������

            
            JComboBox<String> comboBox=new JComboBox<>();
            comboBox.addItem("career");// �������б�������ݡ�Item�����
            comboBox.addItem("school");// �������б�������ݡ�Item�����
            
            comboBox.setBounds(15,10,100,20);// ����������ꡢ�ߴ硣���Բ���
            jpanel.add(comboBox);

            comboBox.setEditable(true);// �б��ɱ༭
        
            // ��ʼ����ӡ��ť������Ӹÿؼ�������


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

            // ��ʼ��Ԥ����ť

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

            // ��ʼ��ȡ����ť

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
            // ��ӻ��嵽����

            jframe.add(jpanel);
            // �����ʼ�����
            jframe.setVisible(true);
    

        }

        
    

    private static GridBagConstraints getGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight,
                double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady) {

            return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets,
                    ipadx, ipady);
        
    }
}
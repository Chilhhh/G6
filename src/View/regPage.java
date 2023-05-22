package View;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import static Controller.LoginController.Reg;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-19-10:16
 * @Description:
 */
public class regPage extends JLayeredPane {
    LoginPage mainJFrame;
    public regPage(LoginPage loginPage) {

        this.mainJFrame = loginPage;
            setSize(300,250);//设计窗体的大小
        //设置窗体的长宽各为300、250  让其显示在左上方的300、300处
            JLabel studentnum_Label=new JLabel("studentnum :"); //实例化JLabel对象
                studentnum_Label.setForeground(Color.GRAY);
                studentnum_Label.setFont(new Font("Arial", Font.BOLD, 20));
        //设置JLabel的字体颜色为黑色，字体格式为粗体，20号字
            JLabel password_label=new JLabel("password :");
                password_label.setForeground(Color.GRAY);
                password_label.setFont(new Font("Arial", Font.BOLD, 20));
                //设置JLabel的字体颜色为黑色，字体格式为粗体，20号字
            JLabel Reconfirm_label=new JLabel("Reconfirm :");
                Reconfirm_label.setForeground(Color.GRAY);
                Reconfirm_label.setFont(new Font("Arial", Font.BOLD, 20));
                //设置JLabel的字体颜色为黑色，字体格式为粗体，20号字
        JTextField userName_Field=new JTextField(15);//实例化用户名文本框
        userName_Field.setBackground(new Color(58, 57, 57));
        JPasswordField password_Field=new JPasswordField(15);//实例化密码文本框
        password_Field.setBackground(new Color(58, 57, 57));
        JPasswordField repassword_Field=new JPasswordField(15);//实例化密码文本框
        repassword_Field.setBackground(new Color(58, 57, 57));
        password_Field.setEchoChar('*');//设置密码隐藏制度

        JButton confirm_Button=new JButton("confirm\n");
        confirm_Button.setFont(new Font("Arial", Font.BOLD, 20));
        confirm_Button.setForeground(new Color(131, 129, 129));
        confirm_Button.setContentAreaFilled(false);
        confirm_Button.setFocusPainted(false);

        JButton Reset_Button=new JButton("Reset");
        Reset_Button.setFont(new Font("Arial", Font.BOLD, 20));
        Reset_Button.setForeground(new Color(131, 129, 129));
        Reset_Button.setContentAreaFilled(false);
        Reset_Button.setFocusPainted(false);

        ImageIcon icon=new ImageIcon("resources/img/logo.png");//生成icon图片
        icon.setImage(icon.getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));
        JButton Logo=new JButton(icon);
        //设置按钮的提示信息
        JButton Back_Buntton = new JButton("Back\n");

        
        userName_Field.setBorder(null);//除去边框
        password_Field.setBorder(null);//除去边框
        repassword_Field.setBorder(null);//除去边框

        Back_Buntton.setFont(new Font("Arial", Font.BOLD, 20));
        Back_Buntton.setContentAreaFilled(false);
        Back_Buntton.setFocusPainted(false);
        Back_Buntton.setForeground(new Color(131, 129, 129));

        Logo.setBorderPainted(false);//不打印边框
        Logo.setBorder(null);//除去边框
        Logo.setFocusPainted(false);//除去焦点的框
        Logo.setContentAreaFilled(false);//除去默认的背景填充

        //this.setLayout(new BorderLayout(30,5));
            setVisible(true);
            //获取一个容器
            JPanel Show_panel=new JPanel();
//        将用户名、密码的Jlabel和用户名JTextField文本框、密码JPasswordField密码框以及确定JButton、快速注册JButton添加到container容器里面
            Show_panel.add(studentnum_Label);
            Show_panel.add(password_label);
            Show_panel.add(Reconfirm_label);
            Show_panel.add(repassword_Field);
            Show_panel.add(userName_Field);
            Show_panel.add(password_Field);
            Show_panel.add(confirm_Button);
            Show_panel.add(Reset_Button);
            Show_panel.add(Logo);

            //设置窗体的长宽各为300、250  让其显示在左上方的300、250处
            //setBounds(0,0,800,600);
            Show_panel.setBounds(0,0,800,600);
            Show_panel.setLayout(null);
             Show_panel.setOpaque(false);
            this.add(Show_panel,new Integer(1));  //将容器添加到窗体中
             this.add(new Carousel(800,600),new Integer(0));
            //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            studentnum_Label.setBounds(70, 50, 150, 30);
            password_label.setBounds(70, 90, 150, 30);
            Reconfirm_label.setBounds(70, 130, 180, 30);
            userName_Field.setBounds(250, 50, 200, 30);
            password_Field.setBounds(250, 90, 200, 30);
             repassword_Field.setBounds(250, 130, 200, 30);

             confirm_Button.setBounds(280,440,200,30);
             Reset_Button.setBounds(280,480,200,30);
             Back_Buntton.setBounds(280, 520, 200, 30);

             Logo.setBounds(600,5,190,100);

        Back_Buntton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickGoBackJButton();
            }
        });
        Show_panel.add(Back_Buntton);

        confirm_Button.addActionListener(new ActionListener() {//对确认按钮添加监听事件
                @SuppressWarnings("deprecation")
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    if((password_Field.getText().trim()).equals(repassword_Field.getText().trim())){
                    int username= Integer.parseInt(userName_Field.getText().trim());
                    String Password=password_Field.getText().trim();
                    try {
                        if(Reg(username,Password)==true){
                            JOptionPane.showMessageDialog(null, "registered successfully\n");
                            new LoginPage();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "fail to register: this student already exist!\n");
                            repassword_Field.setText("");
                            password_Field.setText("");
                            userName_Field.setText("");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                        JOptionPane.showMessageDialog(null, "fail to register:The two passwords must be the same\n");
                    }
                }


            });
        Reset_Button.addActionListener(new ActionListener() {//对重置按钮添加监听事件
                @SuppressWarnings("deprecation")
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    repassword_Field.setText("");
                    password_Field.setText("");
                    userName_Field.setText("");

                }
            });
        }
    public void clickGoBackJButton() {
        //调回原大小
        mainJFrame.setSize(801, 600);
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.comeBack();
    }


}


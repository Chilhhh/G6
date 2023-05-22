package View;

import Util.verificationCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import static Controller.LoginController.isCorrect;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-05-13-13:15
 * @Description:
 */
public class LoginPage_Oppanel extends JPanel {
    LoginPage mainJFrame;
    static int s=0;
    public LoginPage_Oppanel(LoginPage mainJFrame) {
        //  this.setBounds(0,0,100, 300);
        this.mainJFrame =  mainJFrame;
        verificationCode vcode = new verificationCode();

        setBackground(Color.BLACK);
        // Font font = new Font("Arial", Font.PLAIN, 20);
        //setTitle("please log in\n");
        setBackground(Color.WHITE);//设置背景颜色
        JLabel studentnum_Label = new JLabel("student num :"); //实例化JLabel对象
        studentnum_Label.setForeground(Color.GRAY);
        studentnum_Label.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel password_Label = new JLabel("password :");
        password_Label.setForeground(Color.GRAY);
        password_Label.setFont(new Font("Arial", Font.BOLD, 20));


        JLabel vcode_Label = new JLabel("security code :");
        vcode_Label.setForeground(Color.GRAY);
        vcode_Label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField userField = new JTextField(15);//实例化用户名文本框
        userField.setBackground(new Color(58, 57, 57));
        userField.setForeground(Color.GRAY);
        JPasswordField passwordField = new JPasswordField(15);//实例化密码框
        passwordField.setBackground(new Color(58, 57, 57));
        passwordField.setForeground(Color.GRAY);
        JTextField VC_Field = new JTextField(4);//实例化验证码框
        VC_Field.setBackground(new Color(58, 57, 57));
        VC_Field.setForeground(Color.GRAY);
        passwordField.setEchoChar('*');//将输入密码框中的密码以*显示出来
        JButton Login_Buntton = new JButton("log in\n");
        JButton FastReg_Button = new JButton("Fast Registration");
//        Login_Buntton.setBackground(Color.BLACK);
//        Login_Buntton.setForeground(Color.GRAY);//设置登录按钮填充色
        //设置按钮字体为黑色
        Login_Buntton.setFont(new Font("Arial", Font.BOLD, 20));
        Login_Buntton.setContentAreaFilled(false);
        Login_Buntton.setFocusPainted(false);
        Login_Buntton.setForeground(new Color(131, 129, 129));
//        FastReg_Button.setBackground(Color.BLACK);//设置快速登录按钮填充色
        FastReg_Button.setForeground(new Color(131, 129, 129));
        FastReg_Button.setFont(new Font("Arial", Font.BOLD, 15));
        FastReg_Button.setContentAreaFilled(false);
        FastReg_Button.setFocusPainted(false);
        setVisible(true);//使窗体可视化
        setLayout(new BorderLayout(30, 5));

        ImageIcon icon=new ImageIcon("resources/img/logo.png");//生成icon图片
        icon.setImage(icon.getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));
        JButton jb=new JButton(icon);

        //设置按钮的提示信息



        userField.setBorder(null);//除去边框

        passwordField.setBorder(null);//除去边框

        VC_Field.setBorder(null);//除去边框



        jb.setBorderPainted(false);//不打印边框
        jb.setBorder(null);//除去边框
        jb.setFocusPainted(false);//除去焦点的框
        jb.setContentAreaFilled(false);//除去默认的背景填充

        //添加按钮

        this.setBounds(0,0,800, 600);


        this.setOpaque(false);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        this.add(studentnum_Label);
        this.add(password_Label);
        this.add(userField);
        this.add(passwordField);
        this.add(Login_Buntton);
        this.add(FastReg_Button);

        this.add(vcode_Label);
        this.add(VC_Field);
        this.add(vcode);
        this.add(jb);







//        将用户名、密码的Jlabel和用户名JTextField文本框、密码JPasswordField密码框以及确定JButton、快速注册JButton添加到container容器里面                         //


        //this.setBounds(300, 300, 800, 600);//设置窗体的长宽各为300、300  让其显示在左上方的300、300处

        jb.setBounds(600,5,190,100);
        studentnum_Label.setBounds(70, 50, 150, 18);
        password_Label.setBounds(70, 90, 150, 18);
        userField.setBounds(250, 50, 200, 30);
        passwordField.setBounds(250, 90, 200, 30);
        vcode_Label.setBounds(70, 130, 150, 18);
        VC_Field.setBounds(250, 130, 200, 30);
        Login_Buntton.setBounds(280, 490, 200, 30);
        FastReg_Button.setBounds(280, 450, 200, 30);
        vcode.setBounds(475, 125, 80, 30);




        FastReg_Button.addActionListener(new ActionListener() {//对快速注册按钮添加监听事件
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    mainJFrame.changeToregPage();//进入都到注册窗体中
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });


        Login_Buntton.addActionListener(new ActionListener() {//对登录按钮添加监听事件

            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                try {
                    if (isCorrect(Integer.parseInt(userField.getText().trim()), new String(passwordField.getPassword())) && s == 1) {//是否匹配
                        JOptionPane.showMessageDialog(null, "login successfully\n");
                        new menuPage();//进入到下一个页面
                        mainJFrame.setVisible(false);//将当前窗体设置为不可见

                    } else if (isCorrect(Integer.parseInt(userField.getText().trim()), new String(passwordField.getPassword())) && s == 0) {
                        JOptionPane.showMessageDialog(null, "The verification code is incorrectly entered");
                    } else {
                        JOptionPane.showMessageDialog(null, "The login fails because the user name, password, or verification code is incorrectly entered. ");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        VC_Field.addFocusListener(new FocusListener() {

                                      @Override
                                      public void focusGained(FocusEvent e) {

                                      }

                                      @Override
                                      public void focusLost(FocusEvent e) {
                                          try {
                                              if (VC_Field.getText() == null) {
                                                  s = 0;
                                              } else if (vcode.getCode() == null) {

                                                  s = 1;
                                              } else if (vcode.getCode().equals(VC_Field.getText())) {

                                                  s = 1;
                                              } else {
                                                  s = 0;
                                              }
                                          } catch (Exception ex) {
                                          }
                                      }

                                  }
        );


    }

}

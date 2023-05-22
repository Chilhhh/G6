package View;

import Util.verificationCode;
import com.alee.laf.WebLookAndFeel;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import java.awt.*;//导入awt包
import javax.swing.*;//导入swing包

import java.awt.event.ActionListener;//导入awt包中的监听器事件包
import java.awt.event.ActionEvent;//导入awt包中的ActionEvent事件包
import java.awt.event.*;//导入awt包中的ActionEvent事件包
import java.io.IOException;

import static Controller.LoginController.isCorrect;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-19-10:14
 * @Description:
 */
public class LoginPage extends JFrame {

    static int s=0;
    public LoginPage() {

        verificationCode vcode = new verificationCode();


       // Font font = new Font("Arial", Font.PLAIN, 20);
        setTitle("please log in\n");
        setBackground(Color.WHITE);//设置背景颜色
        JLabel studentnum_Label = new JLabel("student num"); //实例化JLabel对象
        studentnum_Label.setForeground(Color.WHITE);
        JLabel password_Label = new JLabel("password");
        password_Label.setForeground(Color.WHITE);
        JLabel Forget_label = new JLabel("Forget your username/password?");
        Forget_label.setForeground(Color.WHITE);
        JLabel vcode_Label = new JLabel("security code");
        vcode_Label.setForeground(Color.WHITE);
        JTextField userField = new JTextField(15);//实例化用户名文本框
        JPasswordField passwordField = new JPasswordField(15);//实例化密码框
        JTextField VC_Field = new JTextField(4);//实例化验证码框
        passwordField.setEchoChar('*');//将输入密码框中的密码以*显示出来
        JButton Login_Buntton = new JButton("log in\n");
        JButton FastReg_Button = new JButton("Fast Registration");
        Login_Buntton.setBackground(Color.GRAY);//设置登录按钮字体颜色
        FastReg_Button.setForeground(Color.GRAY);//设置快速登录按钮填充色
        setVisible(true);//使窗体可视化
        setLayout(new BorderLayout(30, 5));


        JPanel pane = new JPanel();//获取一个容器
        pane.setBounds(0,0,800, 600);
        pane.setOpaque(false);
        pane.setLayout(null);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(new Carousel(800,600),new Integer(0));
        layeredPane.add(pane,new Integer(1));
        add(layeredPane,BorderLayout.CENTER);






        this.getContentPane().setBackground(Color.WHITE);//设置窗体填充色
//        将用户名、密码的Jlabel和用户名JTextField文本框、密码JPasswordField密码框以及确定JButton、快速注册JButton添加到container容器里面                         //
        pane.add(studentnum_Label);
        pane.add(password_Label);
        pane.add(userField);
        pane.add(passwordField);
        pane.add(Login_Buntton);
        pane.add(FastReg_Button);
        pane.add(Forget_label);
        pane.add(vcode_Label);
        pane.add(VC_Field);
        pane.add(vcode);
        setBounds(300, 300, 800, 600);//设置窗体的长宽各为300、300  让其显示在左上方的300、300处


        //this.add(new Label(" login to use"), BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        studentnum_Label.setBounds(10, 40, 100, 18);
        password_Label.setBounds(10, 80, 100, 18);
        userField.setBounds(150, 40, 200, 30);
        passwordField.setBounds(150, 80, 200, 30);
        vcode_Label.setBounds(10, 120, 120, 18);
        VC_Field.setBounds(150, 120, 200, 30);
        Login_Buntton.setBounds(150, 180, 200, 30);
        FastReg_Button.setBounds(150, 220, 200, 30);
        Forget_label.setBounds(375, 85, 340, 30);
        vcode.setBounds(375, 115, 80, 30);
        FastReg_Button.addActionListener(new ActionListener() {//对快速注册按钮添加监听事件
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new regPage();//进入都到注册窗体中
                pane.setVisible(false);
                //this.close();
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

    public static void main(String[] args) {
        //WebLookAndFeel.install();
        new LoginPage();





    }

}
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
public class regPage extends JFrame {
    public regPage() {
            setSize(300,250);//设计窗体的大小
            JLabel userName_label=new JLabel("studentnum"); //实例化JLabel对象
            JLabel password_label=new JLabel("password");
            JLabel Reconfirm_label=new JLabel("Reconfirm password");
            JTextField userName_Field=new JTextField(15);//实例化用户名文本框
            JPasswordField password_Field=new JPasswordField(15);//实例化密码文本框
            JPasswordField repassword_Field=new JPasswordField(15);//实例化密码文本框
        password_Field.setEchoChar('*');//设置密码隐藏制度
            JButton confirm_Button=new JButton("confirm\n");
            JButton Reset_Button=new JButton("Reset");

            this.setLayout(new BorderLayout(30,5));
            setVisible(true);
            //获取一个容器
            Container m=new JPanel();
//        将用户名、密码的Jlabel和用户名JTextField文本框、密码JPasswordField密码框以及确定JButton、快速注册JButton添加到container容器里面
            m.add(userName_label);
            m.add(password_label);
            m.add(Reconfirm_label);
            m.add(repassword_Field);
            m.add(userName_Field);
            m.add(password_Field);
            m.add(confirm_Button);
            m.add(Reset_Button);

            //设置窗体的长宽各为300、250  让其显示在左上方的300、250处
            setBounds(300,250,500,350);
            m.setLayout(null);
            this.add(m,BorderLayout.CENTER);  //将容器添加到窗体中
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            userName_label.setBounds(10,40,150,38);
            password_label.setBounds(10,80,150,38);
            Reconfirm_label.setBounds(5,120,150,38);
            userName_Field.setBounds(170,40,200,38);
            password_Field.setBounds(170,80,200,38);
             repassword_Field.setBounds(170,120,180,38);
             confirm_Button.setBounds(180,160,70,30);
             Reset_Button.setBounds(100,160,70,30);

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


}


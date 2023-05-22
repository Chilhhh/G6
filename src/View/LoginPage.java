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
JLayeredPane layeredPane_Main;
JPanel pane;
    Carousel loginPage_back;
    LoginPage_Oppanel loginPage_opp;
    static int s=0;
    public LoginPage() {
        loginPage_opp = new LoginPage_Oppanel(this);

        layeredPane_Main = new JLayeredPane();
        loginPage_back = new Carousel(800, 600);
        layeredPane_Main.add(loginPage_back, new Integer(0));
        layeredPane_Main.add(loginPage_opp, new Integer(1));
        this.getContentPane().add(layeredPane_Main);

        //  this.setContentPane(layeredPane_Main);


        //this.getContentPane().setBackground(Color.BLACK);//设置窗体填充色
//        将用户名、密码的Jlabel和用户名JTextField文本框、密码JPasswordField密码框以及确定JButton、快速注册JButton添加到container容器里面                         //


        this.setBounds(300, 300, 800, 600);//设置窗体的长宽各为300、300  让其显示在左上方的300、300处
        this.setTitle("Stu helper");//设置窗体标题

        this.setIconImage(new ImageIcon("resources/img/icon.jpg").getImage());//设置窗体的图标
        //this.add(new Label(" login to use"), BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setLocationRelativeTo(null);


    }

    public void changeToregPage() throws IOException {
        this.setContentPane(new regPage(this));
        this.setSize(802, 600);
    }
    public void comeBack(){
        this.setBounds(0,0,799, 600);
        this.setLocationRelativeTo(null);

        this.setContentPane(layeredPane_Main);
    }
    public static void main(String[] args) {
        //WebLookAndFeel.install();
        new LoginPage();

    }

}
package View;

import Controller.InfoReader;

import java.awt.*;//导入awt包
import java.io.IOException;
import javax.swing.*;//导入swing包

import static java.lang.Thread.sleep;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-04-08-16:43
 * @Description:
 */
public class menuPage extends JFrame {
    MainPage_OPPanel mainPage_opPanel = new MainPage_OPPanel(this);
    MainPage_BGPanel mainPage_bgPanel = new MainPage_BGPanel();
    JLayeredPane layeredPane_Main;
    public menuPage() throws InterruptedException {
   this.setBounds(0,0,1114, 617);
   this.setLocationRelativeTo(null);
   this.setLayout(new BorderLayout());



        layeredPane_Main = new JLayeredPane();
        layeredPane_Main.add(mainPage_bgPanel,new Integer(0));
        layeredPane_Main.add(mainPage_opPanel,new Integer(1));


        /*JPanel Cardpanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        Cardpanel.setLayout(cardLayout);

        Cardpanel.add(mypanel);
        Cardpanel.add(layeredPane_Main);*/

       this.add(  layeredPane_Main,BorderLayout.CENTER);//将卡片面板加入到窗体中
     /* Panel p2 = new Panel();
        this.add(p2,BorderLayout.SOUTH);//切换按钮
*/

/*

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                switch (command){
                    case "上一张":
                        cardLayout.previous(Cardpanel);
                        break;
                    case "下一张":
                        cardLayout.next(Cardpanel);
                        break;
                    case "第一张":
                        cardLayout.first(Cardpanel);
                        break;
                    case "最后一张":
                        cardLayout.last(Cardpanel);
                        break;
                    case "第三张":
                        cardLayout.show(Cardpanel,"第三张");
                        break;
                }
            }
        };

        Button b1 = new Button("上一张");
        Button b2 = new Button("下一张");
        Button b3 = new Button("第一张");
        Button b4 = new Button("最后一张");
        Button b5 = new Button("第三张");
        b1.addActionListener(listener);
        b2.addActionListener(listener);
        b3.addActionListener(listener);
        b4.addActionListener(listener);
        b5.addActionListener(listener);

        //7.把5个按钮添加到p2中
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);


*/
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changeToTimeLine() throws IOException {
        this.setContentPane(new TimeLineDisplayJPanel(this));

    }
    public void changeToDispaly() throws IOException {
        this.setContentPane((new InfoReader()).readit(this));
        this.setSize(1200, 850);
    }
    public void  changeToFocusLearning(){
        this.setContentPane(new FocusLearningJPanel(this));
    }
    public void comeBack(){
        this.setBounds(0,0,1114, 917);
        this.setLocationRelativeTo(null);
        this.setContentPane(layeredPane_Main);
    }



    public static void main(String[] args) throws InterruptedException {
        new menuPage();
        }


}

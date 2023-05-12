package View;
import java.awt.*;//导入awt包
import java.net.URL;
import javax.swing.*;//导入swing包

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-29-17:19
 * @Description:
 */
public class MainPage_BGPanel extends JPanel{
    ImageIcon icon;
    Image img;
    public MainPage_BGPanel() {
      this.setBounds(0,0,1114, 617);
            //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
            icon=new ImageIcon("resources/img/MainPage.png");
            img=icon.getImage();


        JLabel Title_Label = new JLabel("Main page",JLabel.CENTER);
        Title_Label.setForeground(new Color(0xFF00FDBF, true));
        Title_Label.setFont(new Font("Serif", Font.BOLD, 90));
        add(Title_Label,BorderLayout.NORTH);

        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

        }





}

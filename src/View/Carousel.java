package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-04-08-22:47
 * @Description:
 */
public class Carousel extends JPanel {
    MyJPanel mp;
    int index;
    int w=800;
    int h=600;
    ImageIcon[] imgs = {
            new ImageIcon("resources/img/1.jpg"),
            new ImageIcon("resources/img/2.jpg"),
            new ImageIcon("resources/img/3.jpg"),
            //new ImageIcon("resources/img/4.jpg"),
         //   new ImageIcon("resources/img/5.jpg"),
          //  new ImageIcon("resources/img/6.jpg"),
          //  new ImageIcon("resources/img/7.jpg"),
           // new ImageIcon("resources/img/8.jpg"),
    };
    public Carousel (int w,int h) {
        mp = new MyJPanel();
        this.add(mp);
        this.setBounds(0,0,w, h);
        Timer timer = new Timer(3000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.repaint();
            }
        });
        timer.start();
    }
    class MyJPanel extends JPanel{


        @Override
        public void paint(Graphics g) {
            this.setBounds(0,0,w, h);
            super.paint(g);
            g.drawImage(imgs[index%imgs.length].getImage(), 1, 1,this);
            index++;
        }
    }


}


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
            new ImageIcon("resources/img/background/background (1).jpg"),
           // new ImageIcon("resources/img/background/background (2).jpg"),
            //new ImageIcon("resources/img/background/background (3).jpg"),
//            new ImageIcon("img/background/background (4).jpg"),
//            new ImageIcon("img/background/background (5).jpg"),
//            new ImageIcon("img/background/background (6).jpg"),
//            new ImageIcon("img/background/background (7).jpg"),
//            new ImageIcon("img/background/background (8).jpg"),
//            new ImageIcon("img/background/background (9).jpg"),
//            new ImageIcon("img/background/background (10).jpg"),
//            new ImageIcon("img/background/background (11).jpg"),
            new ImageIcon("resources/img/background/background (12).jpg"),
//            new ImageIcon("img/background/background (13).jpg"),
//            new ImageIcon("img/background/background (14).jpg"),
//            new ImageIcon("img/background/background (15).jpg"),
//            new ImageIcon("img/background/background (16).jpg"),
//            new ImageIcon("img/background/background (17).jpg"),
           new ImageIcon("resources/img/background/background (18).jpg"),
//            new ImageIcon("img/background/background (19).jpg"),
//            new ImageIcon("img/background/background (20).jpg"),
//            new ImageIcon("img/background/background (21).jpg"),
//            new ImageIcon("img/background/background (22).jpg"),
//            new ImageIcon("img/background/background (23).jpg"),
            new ImageIcon("resources/img/background/background (24).jpg"),
//            new ImageIcon("img/background/background (25).jpg"),
//            new ImageIcon("img/background/background (26).jpg"),
//            new ImageIcon("img/background/background (27).jpg"),
//            new ImageIcon("img/background/background (28).jpg"),
//            new ImageIcon("img/background/background (29).jpg"),
//            new ImageIcon("img/background/background (30).jpg"),
//            new ImageIcon("img/background/background (31).jpg"),
//            new ImageIcon("img/background/background (32).jpg"),
            new ImageIcon("resources/img/background/background (33).jpg"),
//            new ImageIcon("img/background/background (34).jpg"),
//            new ImageIcon("img/background/background (35).jpg"),
//            new ImageIcon("img/background/background (36).jpg"),
//            new ImageIcon("img/background/background (37).jpg"),
//            new ImageIcon("img/background/background (38).jpg"),
            new ImageIcon("resources/img/background/background (39).jpg"),
//            new ImageIcon("img/background/background (40).jpg"),
//            new ImageIcon("img/background/background (41).jpg"),
//            new ImageIcon("img/background/background (42).jpg"),
//            new ImageIcon("img/background/background (43).jpg"),
//            new ImageIcon("img/background/background (44).jpg"),
            new ImageIcon("resources/img/background/background (45).jpg"),
//            new ImageIcon("img/background/background (46).jpg"),
//            new ImageIcon("img/background/background (47).jpg"),
//            new ImageIcon("img/background/background (48).jpg"),
//            new ImageIcon("img/background/background (49).jpg"),
//            new ImageIcon("img/background/background (50).jpg"),
//            new ImageIcon("img/background/background (51).jpg"),
//            new ImageIcon("img/background/background (52).jpg"),
//            new ImageIcon("img/background/background (53).jpg"),
            new ImageIcon("resources/img/background/background (54).jpg"),
//            new ImageIcon("img/background/background (55).jpg"),
//            new ImageIcon("img/background/background (56).jpg"),
//            new ImageIcon("img/background/background (57).jpg"),
//            new ImageIcon("img/background/background (58).jpg"),
//            new ImageIcon("img/background/background (59).jpg"),
           new ImageIcon("resources/img/background/background (60).jpg"),
//            new ImageIcon("img/background/background (61).jpg"),
//            new ImageIcon("img/background/background (62).jpg"),
//            new ImageIcon("img/background/background (63).jpg"),
//            new ImageIcon("img/background/background (64).jpg"),
            new ImageIcon("resources/img/background/background (65).jpg"),
            new ImageIcon("resources/img/background/background (66).jpg"),
            new ImageIcon("resources/img/background/background (67).jpg"),
            new ImageIcon("resources/img/background/background (68).jpg"),
            new ImageIcon("resources/img/background/background (69).jpg"),
            new ImageIcon("resources/img/background/background (70).jpg"),

         new ImageIcon("resources/img/background/background (71).jpg"),
new ImageIcon("resources/img/background/background (72).jpg"),
new ImageIcon("resources/img/background/background (73).jpg"),
new ImageIcon("resources/img/background/background (74).jpg"),
new ImageIcon("resources/img/background/background (75).jpg"),
new ImageIcon("resources/img/background/background (76).jpg"),

           new ImageIcon("resources/img/background/background (77).jpg"),
  new ImageIcon("resources/img/background/background (78).jpg"),
    new ImageIcon("resources/img/background/background (79).jpg"),
        new ImageIcon("resources/img/background/background (80).jpg"),
            new ImageIcon("resources/img/background/background (81).jpg"),
            new ImageIcon("resources/img/background/background (82).jpg"),
            new ImageIcon("resources/img/background/background (83).jpg"),
           new ImageIcon("resources/img/background/background (84).jpg"),
        new ImageIcon("resources/img/background/background (85).jpg"),
            new ImageIcon("resources/img/background/background (86).jpg"),
            new ImageIcon("resources/img/background/background (87).jpg"),
            new ImageIcon("resources/img/background/background (88).jpg"),
            new ImageIcon("resources/img/background/background (89).jpg"),
            new ImageIcon("resources/img/background/background (90).jpg"),
           new ImageIcon("resources/img/background/background (91).jpg"),
     new ImageIcon("resources/img/background/background (92).jpg"),
            new ImageIcon("resources/img/background/background (93).jpg"),
            new ImageIcon("resources/img/background/background (94).jpg"),
            new ImageIcon("resources/img/background/background (95).jpg"),
            new ImageIcon("resources/img/background/background (96).jpg"),
            new ImageIcon("resources/img/background/background (97).jpg"),
            new ImageIcon("resources/img/background/background (98).jpg"),
            new ImageIcon("resources/img/background/background (99).jpg"),
            new ImageIcon("resources/img/background/background (100).jpg"),
            new ImageIcon("resources/img/background/background (101).jpg"),
            new ImageIcon("resources/img/background/background (102).jpg"),
            new ImageIcon("resources/img/background/background (103).jpg"),
            new ImageIcon("resources/img/background/background (104).jpg"),
            new ImageIcon("resources/img/background/background (105).jpg"),
            new ImageIcon("resources/img/background/background (106).jpg"),
            new ImageIcon("resources/img/background/background (107).jpg"),
            new ImageIcon("resources/img/background/background (108).jpg"),
            new ImageIcon("resources/img/background/background (109).jpg"),
            new ImageIcon("resources/img/background/background (110).jpg"),
            new ImageIcon("resources/img/background/background (111).jpg"),
            new ImageIcon("resources/img/background/background (112).jpg"),
            new ImageIcon("resources/img/background/background (113).jpg"),
            new ImageIcon("resources/img/background/background (114).jpg"),
            new ImageIcon("resources/img/background/background (115).jpg"),
            new ImageIcon("resources/img/background/background (116).jpg"),
            new ImageIcon("resources/img/background/background (117).jpg"),
            new ImageIcon("resources/img/background/background (118).jpg"),
            new ImageIcon("resources/img/background/background (119).jpg"),
            new ImageIcon("resources/img/background/background (120).jpg"),
            new ImageIcon("resources/img/background/background (121).jpg"),
            new ImageIcon("resources/img/background/background (122).jpg"),
            new ImageIcon("resources/img/background/background (123).jpg"),
           new ImageIcon("resources/img/background/background (124).jpg"),
            new ImageIcon("resources/img/background/background (125).jpg")















           /* new ImageIcon("TmPAqEIOfFeHbtbe0e6F0t16gAwvTYy4NtxGM8WvfOXUyYbSoO2VqVw_280.PNG"),
            new ImageIcon("resources/img/3.PNG"),*/
            //new ImageIcon("resources/img/4.jpg"),
         //   new ImageIcon("resources/img/5.jpg"),
          //  new ImageIcon("resources/img/6.jpg"),
          //  new ImageIcon("resources/img/7.jpg"),
           // new ImageIcon("resources/img/8.jpg"),
    };


    public Carousel (int w,int h) {
        mp = new MyJPanel();
        mp.setBackground(Color.BLACK);
        this.add(mp);
        this.setBackground(Color.BLACK);
        this.setBounds(0,0,w, h);
        for(int i=0;i<imgs.length;i++){
            imgs[i].setImage(imgs[i].getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        }
        Timer timer = new Timer(100,new ActionListener() {
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


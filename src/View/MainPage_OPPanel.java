package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.awt.event.MouseEvent;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-29-19:00
 * @Description:
 */
public class MainPage_OPPanel extends JPanel {
    private menuPage mainJFrame;     //主窗体


    public MainPage_OPPanel(menuPage jFrame) {
        this.mainJFrame = jFrame;

        this.setBounds(0, 0, 1114, 617);
        this.setOpaque(false);
        this.setLayout(null);
        AddSubPage_Button();
    }

    public void AddSubPage_Button() {

//        //新加
//        String currentID = String.valueOf(LoginController.username);
//        UserIcons iconArray = new UserIcons();
//        UserIcon currentUserIcon = new UserIcon("default");
//        ImageIcon icon;
//        Path path = Paths.get("src/Data/UserIcons.json");
//        //System.out.println("存在:"+ Files.exists(path));
//        if(!Files.exists(path)){
//            currentUserIcon.setDefaultIcon("default");
//            icon = new ImageIcon(Objects.requireNonNull(SetInfo.base64ToImage(currentUserIcon.getStudentImage())));
//        }
//        else {
//            try {
//                List<UserIcon> a = JSON.parseArray(JSONUtils.readJSONFile("src/Data/UserIcons.json"), UserIcon.class);
//                int size = a.size();
//                for (UserIcon userIcon : a) {
//                    iconArray.addIcon(userIcon);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if(iconArray.getIconByID(currentID)==null){
//                currentUserIcon.setDefaultIcon("default");
//                icon = new ImageIcon(Objects.requireNonNull(SetInfo.base64ToImage(currentUserIcon.getStudentImage())));
//            }
//            else {
//                icon = new ImageIcon(Objects.requireNonNull(SetInfo.base64ToImage(iconArray.getIconByID(currentID))));
//            }
//        }




        JButton infoButton = new JButton("user info");
//        JLabel infoLabel = new JLabel(icon);
//        infoLabel.setBounds(new Rectangle(990, 5, 100, 80));
//        infoLabel.setOpaque(false);
//        infoLabel.setVisible(true);
//        infoButton.add(infoLabel);


        //infoButton.setIcon(icon);//磊哥写
        infoButton.setBounds(new Rectangle(990, 5, 100, 80));
        infoButton.setContentAreaFilled(false);
        infoButton.setForeground(new Color(0xFF111111, true));
        infoButton.setFont(new Font("Serif", Font.BOLD, 20));
        infoButton.setMargin(new Insets(0, 0, 0, 0));//将边框外的上下左右空间设置为0
        infoButton.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        infoButton.setIcon(new ImageIcon("resources/img/user.png"));

        //Icon icon=new ImageIcon("resources/img/Course_Overview.png");

        JButton[] SubPage_Button= new JButton[4];
        for(int i=0;i<3;i++){
            SubPage_Button[i]=new JButton();
            SubPage_Button[i].setBounds(new Rectangle(140+215*i, 340, 200, 80));

            SubPage_Button[i].setMargin(new Insets(0, 0, 0, 0));//将边框外的上下左右空间设置为0
            SubPage_Button[i].setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
            SubPage_Button[i].setBorderPainted(false);//不打印边框
            SubPage_Button[i].setBorder(null);//除去边框
            SubPage_Button[i].setFocusPainted(false);//除去焦点的框
            SubPage_Button[i].setContentAreaFilled(false);//除去默认的背景填充
            SubPage_Button[i].setOpaque(false);//设置控件是否透明
            this.add(SubPage_Button[i]);
        }


        SubPage_Button[3]=new JButton();
        SubPage_Button[3].setBounds(new Rectangle(140+215*3, 340, 200, 80));
        SubPage_Button[3].setMargin(new Insets(0, 0, 0, 0));//将边框外的上下左右空间设置为0
        SubPage_Button[3].setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        SubPage_Button[3].setBorderPainted(false);//不打印边框
        SubPage_Button[3].setBorder(null);//除去边框
        SubPage_Button[3].setFocusPainted(false);//除去焦点的框
        SubPage_Button[3].setContentAreaFilled(false);//除去默认的背景填充
        SubPage_Button[3].setOpaque(false);//设置控件是否透明
        this.add(SubPage_Button[3]);
        this.add(infoButton);


        SubPage_Button[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标离开按钮时，设置新的图标
                ((JButton) e.getSource()).setIcon(null);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                new tomaClock();
              //  mainJFrame.changeToFocusLearning();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标进入按钮时，设置新的图标
                SubPage_Button[0].setIcon(new ImageIcon("resources/img/focuson_learning.png")  );
            }
        });

        SubPage_Button[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标离开按钮时，设置新的图标
                ((JButton) e.getSource()).setIcon(null);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    mainJFrame.changeToDispaly();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标进入按钮时，设置新的图标
                SubPage_Button[1].setIcon(new ImageIcon("resources/img/Information_management.png")  );
            }
        });

        SubPage_Button[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标离开按钮时，设置新的图标
                ((JButton) e.getSource()).setIcon(null);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // 鼠标离开按钮时，设置新的图标
                try {
                    mainJFrame.changeToTimeLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标进入按钮时，设置新的图标
                SubPage_Button[2].setIcon(new ImageIcon("resources/img/Course_Overview.png")  );
            }
        });

        SubPage_Button[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标离开按钮时，设置新的图标
                ((JButton) e.getSource()).setIcon(null);
            }
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    pdfGUI.pdfGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标进入按钮时，设置新的图标
                SubPage_Button[3].setIcon(new ImageIcon("resources/img/Resume.png")  );
            }
        });
        infoButton.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    mainJFrame.changeToSetinfo();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
    }
}

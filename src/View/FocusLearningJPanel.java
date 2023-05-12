package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-04-15-10:39
 * @Description:
 */
public class FocusLearningJPanel extends JLayeredPane {
    JPanel OpjPanel = new JPanel();
   int jPanelWidth = 800;
    int jPanelHeight = 600;
    menuPage mainJFrame;
    public FocusLearningJPanel(menuPage mainJFrame) {
        this.mainJFrame = mainJFrame;
       // this.init();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(new Carousel(jPanelWidth,jPanelHeight ),new Integer(0));
        //layeredPane.add(OpjPanel,new Integer(1));
        add(layeredPane);

    }
    public void init(){
        OpjPanel.setBounds(0,0,800,600);
        OpjPanel.setBackground(new Color(0,0,0,0));
        OpjPanel.setLayout(null);
        OpjPanel.add(new tomaClock(),BorderLayout.CENTER);
        OpjPanel.setOpaque(false);
        OpjPanel.setLayout(null);
        JButton goBackJButton = new JButton("go back");
        goBackJButton.setBounds(jPanelWidth - 200, jPanelHeight - 50, 100, 30);
        goBackJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickGoBackJButton();
            }
        });
        OpjPanel.add(goBackJButton);

    }
    public void clickGoBackJButton() {
        //调回原大小
        mainJFrame.setSize(500, 500);
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.comeBack();
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(new FocusLearningJPanel(null));
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    }





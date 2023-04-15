import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

// import javafx.event.*;
// import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        // PlayFrame playFrame = new PlayFrame();
        PlayFrame.createAndShowGUI();
        // Application.launch(VideoPlayer.class,"video.mp4");
    }
}

class PlayFrame {
    static boolean flag = true;
    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
        // JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建登录按钮
        JButton videoJButton = new JButton("login");
        videoJButton.setBounds(10, 80, 80, 25);
        frame.getContentPane().add(videoJButton);
        videoJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == true) {
                    flag = false;
                    Application.launch(VideoPlayer2.class,"video.mp4");
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            PlayStage playStage = new PlayStage(new Stage());
                            playStage.initPlay();
                        }
                    });
                    // PlayStage playStage = new PlayStage(new Stage());
                    // playStage.initPlay();
                }
                // if (flag == true) {
                //     flag = false;
                //     Application.launch(VideoPlayer.class,"video.mp4");
                // } else {
                //     PlayStage playStage = new PlayStage(new Stage());
                //     playStage.initPlay();    
                // }
                // Application.launch(VideoPlayer.class,"video.mp4");
                
            }
        });

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
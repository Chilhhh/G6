package Model;
/**
 * @Author: Li Peizhe
 */

import javafx.application.Application;
import javafx.scene.media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class TimeLineDisplay {
    private ImageIcon backgroundPicture;
    private InfoReader infoReader;


    private int jFrameWidth;
    private int jFrameHeight;
    private JFrame jFrame;
    private JLayeredPane jLayeredPane;


    private JPanel backgroundJPanel;
    private JLabel backgroundJLabel;


    private JPanel timeLineJpanel;
    private JPanel semesterJPanel;
    private JButton[] semesterJButton;
    private JPanel lessonTableJPanel;
    private JPanel[] lessonListJPanel;




    public TimeLineDisplay(InfoReader infoReader) {
        preLoad(infoReader);
        integrate();
    }


    public void preLoad(InfoReader infoReader) {
        this.infoReader = infoReader;

        backgroundPicture = new ImageIcon("pictures/background.png");
        if (backgroundPicture == null) {
            System.out.println("background picture is not found");
        }

        String videoPath = "videos/video.mp4";
        Media media = new Media(new File(videoPath).toURI().toString());
        if (media == null) {
            System.out.println("video is not found");
        }
    }

    public void integrate() {
        setMainFrame();
        setBackgroundFrame();
        setTimeLineFrame();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void setMainFrame() {
        jFrameWidth = backgroundPicture.getIconWidth();
        jFrameHeight = backgroundPicture.getIconHeight();

        jFrame = new JFrame("TimeLine");
        jFrame.setSize(jFrameWidth + 14, jFrameHeight + 30);//the size of TimeLine window
        jFrame.setLocationRelativeTo(null);//the window will appear at the center of your screen

        jLayeredPane = new JLayeredPane();
        jFrame.add(jLayeredPane);
    }

    public void setBackgroundFrame() {
        backgroundJPanel = new JPanel(new GridLayout(1,1));
        backgroundJPanel.setBounds(0, 0, jFrameWidth, jFrameHeight);
        jLayeredPane.add(backgroundJPanel, (Integer)1);

        backgroundJLabel = new JLabel(backgroundPicture);
        backgroundJPanel.add(backgroundJLabel);
    }

    public void setTimeLineFrame() {
        timeLineJpanel = new JPanel();
        timeLineJpanel.setLayout(null);
        timeLineJpanel.setOpaque(false);
        timeLineJpanel.setBounds(0, 0, jFrameWidth, jFrameHeight);
        jLayeredPane.add(timeLineJpanel, (Integer)2);

        semesterJPanel = new JPanel(new GridLayout(1,8));
        semesterJPanel.setOpaque(false);
        semesterJPanel.setBounds(25, 20, jFrameWidth - 50, 30);
        timeLineJpanel.add(semesterJPanel);

        semesterJButton = new JButton[8];
        for (int i = 0; i <= 7; i++) {
            String string = "semester " + (i + 1);
            semesterJButton[i] = new JButton(string);
            semesterJPanel.add(semesterJButton[i]);
        }

        lessonTableJPanel = new JPanel(new GridLayout(1, 8, 6, 6));
        lessonTableJPanel.setOpaque(false);
        lessonTableJPanel.setBounds(28, 70, jFrameWidth - 56, jFrameHeight - 100);
        timeLineJpanel.add(lessonTableJPanel);

        lessonListJPanel = new JPanel[8];
        for (int i = 0; i <= 7; i++) {
            lessonListJPanel[i] = new JPanel(new GridLayout(10, 1, 5, 5));
            lessonListJPanel[i].setOpaque(false);
            lessonTableJPanel.add(lessonListJPanel[i]);
        }

        for (Course course : infoReader.getCourses()) {
            lessonListJPanel[course.getCourseTerm()].add(addLessonJButton(course));
        }
    }

    public JButton addLessonJButton(Course course) {
        JButton lessonJButton = new JButton(course.getCourseName());

        lessonJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicklessonJButton(course);
            }
        });

        return lessonJButton;
    }

    public void clicklessonJButton(Course course) {
        JDialog jDialog = new JDialog(jFrame, course.getCourseName());
        jDialog.setSize(500, 250);
        jDialog.setLocationRelativeTo(null);

        JPanel HeadingAndIntroJPanel = new JPanel(new GridLayout(2,1));
        jDialog.add(HeadingAndIntroJPanel, BorderLayout.NORTH);

        JLabel headingJLabel = new JLabel(course.getCourseName());
        headingJLabel.setHorizontalAlignment(JLabel.CENTER);
        HeadingAndIntroJPanel.add(headingJLabel);

        JLabel introJLabel = new JLabel(course.getCourseDescribe());
        introJLabel.setHorizontalAlignment(JLabel.CENTER);
        HeadingAndIntroJPanel.add(introJLabel);

        Object[][] content = {
                {"ID", course.getCourseID()},
                {"Name", course.getCourseName()},
                {"Teacher", course.getCourseTeacher()},
                {"Term", course.getCourseTerm()},
                {"Credit", course.getCourseCredit()},
                {"Score", course.getCourseScore()}
        };
        String[] strings = {"", ""};
        JTable contentJTable = new JTable(content, strings);
        jDialog.add(contentJTable, BorderLayout.CENTER);

        JButton videoJButton = new JButton("Watch the video");
        videoJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.launch(VideoPlayer.class,"video.mp4");
            }
        });
        jDialog.add(videoJButton, BorderLayout.SOUTH);

        jDialog.setVisible(true);
        jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}

package View;
/**
 * @Author: Li Peizhe
 */

import DAO.JSONUtils;
import Model.Course;
import Model.Courses;
import Util.VideoPlayer;
import com.alibaba.fastjson.JSON;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class TimeLineDisplayJPanel extends JLayeredPane{
    private Font font = new Font("Arial", Font.PLAIN, 20);

    private ImageIcon backgroundPicture;//用于显示背景图片的Icon

    private int jPanelWidth;//TimeLine界面宽度
    private int jPanelHeight;//TimeLine界面高度
    private menuPage mainJFrame;//主JFrame
    private Courses courses;//课程信息

    private JFrame testJFrame;//主JFrame



    private JPanel backgroundJPanel;//下层用于放置背景图片的JPanel
    private JLabel backgroundJLabel;//用于放置背景图片的JLabel


    private JPanel timeLineJPanel;//上层用于放置TimeLine的JPanel
    private JPanel semesterJPanel;//用于横向排列学期JButton的JPanel
    private JButton[] semesterJButton;//表示学期的JButton数列
    private JPanel lessonTableJPanel;//用于横向排列lessonListJPanel的JPanel
    private JPanel[] lessonListJPanel;//用于纵向排列课程JButton的JPanel数列
    private boolean setLessonListVisible[];//用于控制某学期对应的lessonListJPanel是否显示
    private JButton showHideJButton;//统一控制课程显示的JButton
    private JButton goBackJButton;//返回主界面的JButton
    private String showHide;//showHideJButton上的字符

    /**
     * 获取jPanelHeight数值
     * @return int
     */
    public int getjPanelHeight() {
        return jPanelHeight;
    }

    /**
     * 获取jPanelWidth数值
     * @return int
     */
    public int getjPanelWidth() {
        return jPanelWidth;
    }

    /**
     * 构造方法1
     * @param
     */
    public TimeLineDisplayJPanel( menuPage jFrame) throws IOException {
        //预载入获取mainJFrame infoReader和背景图片
        courses = new Courses();
        courses.setCourses( (ArrayList<Course>)JSON.parseArray(JSONUtils. readJSONFile(courses.getJsonfilePath()), Course.class));

        //获取背景图片，表现为一个ImageIcon
        backgroundPicture = new ImageIcon("resources/img/background-course.png");
        if (backgroundPicture == null) {
            System.out.println("background picture is not found");
        }

        //获取背景图片的宽和高
        jPanelWidth = backgroundPicture.getIconWidth();
        jPanelHeight = backgroundPicture.getIconHeight();


        this.mainJFrame = jFrame;
        mainJFrame.setSize(this.getjPanelWidth() + 14, this.getjPanelHeight() + 30);//the size of TimeLine window
        mainJFrame.setLocationRelativeTo(null);



        //搭建GUI
        integrate();
    }

    /**
     * 用于测试的构造方法
     */
    public TimeLineDisplayJPanel() throws IOException {
        //预载入获取mainJFrame infoReader和背景图片
        courses = new Courses();
        courses.setCourses( (ArrayList<Course>)JSON.parseArray(JSONUtils. readJSONFile(courses.getJsonfilePath()), Course.class));

        //获取背景图片，表现为一个ImageIcon
        backgroundPicture = new ImageIcon("resources/img/background-course.png");
        if (backgroundPicture == null) {
            System.out.println("background picture is not found");
        }

        //获取背景图片的宽和高
        jPanelWidth = backgroundPicture.getIconWidth();
        jPanelHeight = backgroundPicture.getIconHeight();

        JFrame jFrame = new JFrame("test");
        this.testJFrame = jFrame;
        testJFrame.setSize(this.getjPanelWidth() + 14, this.getjPanelHeight() + 30);//the size of TimeLine window
        testJFrame.setLocationRelativeTo(null);

        //搭建GUI
        integrate();

        testJFrame.setContentPane(this);
        testJFrame.setVisible(true);
        testJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * 整合各部分JPanel搭建方法
     */
    public void integrate() {
        setBackgroundJPanel();
        setTimeLineJPanel();
    }

    /**
     * 背景JPanel的搭建方法
     */
    public void setBackgroundJPanel() {
        //设置背景JPanel的布局 位置 大小
        backgroundJPanel = new JPanel(new GridLayout(1,1));
        backgroundJPanel.setBounds(0, 0, jPanelWidth, jPanelHeight);

        //将backgroundJPanel加入到jLayeredPane的第1层
        this.add(backgroundJPanel, (Integer)1);

        //new一个JLabel并加入backgroundPicture
        backgroundJLabel = new JLabel(backgroundPicture);

        //将backgroundJLabel加入到backgroundJPanel
        backgroundJPanel.add(backgroundJLabel);
    }

    /**
     * TimeLineJPanel的搭建方法
     */
    public void setTimeLineJPanel() {
        //设置timeLineJPanel布局为空，设置为透明，设置位置和大小
        timeLineJPanel = new JPanel();
        timeLineJPanel.setLayout(null);
        timeLineJPanel.setOpaque(false);
        timeLineJPanel.setBounds(0, 0, jPanelWidth, jPanelHeight);

        //将timeLineJPanel加入到jLayeredPane的第2层
        this.add(timeLineJPanel, (Integer)2);

        //设置semesterJPanel布局为1行8列的GridLayout，设置为透明，设置位置和大小
        semesterJPanel = new JPanel(new GridLayout(1,8));
        semesterJPanel.setOpaque(false);
        semesterJPanel.setBounds(25, 20, jPanelWidth - 50, 30);

        //将semesterJPanel加入到timeLineJPanel
        timeLineJPanel.add(semesterJPanel);

        //new一个JButton数列表示8个学期
        semesterJButton = new JButton[8];

        //new一个boolean数列用来控制每个学期的课程是否显示
        setLessonListVisible = new boolean[8];

        //生成每个学期的JButton
        for (int i = 0; i <= 7; i++) {
            int j = i;

            //默认每个学期的课程都不显示
            setLessonListVisible[i] = false;

            //设置每个学期的JButton显示的字符，设置为无边框
            String string = "semester " + (i + 1);
            semesterJButton[i] = new JButton(string);
            semesterJButton[i].setBorderPainted(false);
            semesterJButton[i].setFont(font);

            //添加事件监听器
            semesterJButton[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //点击学期JButton后触发的方法
                    clickSemesterJButton(j);
                }
            });

            //将semesterJButton依次添加进semesterJPanel
            semesterJPanel.add(semesterJButton[i]);
        }

        //设置每个学期JButton的背景颜色
        semesterJButton[0].setBackground(new Color(235, 53, 38));
        semesterJButton[1].setBackground(new Color(235, 123, 44));
        semesterJButton[2].setBackground(new Color(235, 221, 39));
        semesterJButton[3].setBackground(new Color(164, 235, 39));
        semesterJButton[4].setBackground(new Color(39, 235, 192));
        semesterJButton[5].setBackground(new Color(39, 186, 235));
        semesterJButton[6].setBackground(new Color(39, 98, 235));
        semesterJButton[7].setBackground(new Color(136, 39, 235));

        //设置lessonTableJPanel的布局为1行8列的GridLayout，设置为透明，设置位置和大小
        lessonTableJPanel = new JPanel(new GridLayout(1, 8, 6, 6));
        lessonTableJPanel.setOpaque(false);
//        lessonTableJPanel.setBackground(Color.red);
        lessonTableJPanel.setBounds(28, 70, jPanelWidth - 56, jPanelHeight - 140);

        //将lessonTableJPanel加入到timeLineJPanel
        timeLineJPanel.add(lessonTableJPanel);

        //new一个JPanel数列并依次设置lessonListJPanel
        lessonListJPanel = new JPanel[8];
        for (int i = 0; i <= 7; i++) {
            //设置课程列JPanel的布局为10行1列的GridLayout，设置为透明，设置位置和大小
            lessonListJPanel[i] = new JPanel(new GridLayout(10, 1, 5, 5));
            lessonListJPanel[i].setOpaque(false);
            lessonListJPanel[i].setVisible(setLessonListVisible[i]);

            //将lessonListJPanel依次添加进semesterJPanel
            lessonTableJPanel.add(lessonListJPanel[i]);
        }

        //将InfoReader类读到的课程信息通过generateLessonJButton方法依次生成表示课程的JButton并加入到lessonListJPanel
        for (Course course : courses.getCourses()) {

            JButton jLessonButton = generateLessonJButton(course);
            switch (course.getCourseTerm()) {
                case 0:
                    jLessonButton.setForeground(new Color(235, 53, 38, 255));
                    break;
                case 1:
                    jLessonButton.setForeground(new Color(235, 123, 44, 255));
                    break;
                case 2:
                    jLessonButton.setForeground(new Color(235, 221, 39, 255));
                    break;
                case 3:
                    jLessonButton.setForeground(new Color(164, 235, 39, 255));
                    break;
                case 4:
                    jLessonButton.setForeground(new Color(39, 235, 192, 255));
                    break;
                case 5:
                    jLessonButton.setForeground(new Color(39, 186, 235, 255));
                    break;
                case 6:
                    jLessonButton.setForeground(new Color(39, 98, 235, 255));
                    break;
                case 7:
                    jLessonButton.setForeground(new Color(136, 39, 235, 255));
                    break;
                default:
                    break;
            }
            //course.getCourseTerm()用来获取课程的学期，generateLessonJButton(course)生成表示课程的JButton
            lessonListJPanel[course.getCourseTerm()].add(jLessonButton);
        }

        //添加返回主界面按钮
        goBackJButton = new JButton("Go Back");
        goBackJButton.setBounds(jPanelWidth - 200, jPanelHeight - 50, 100, 30);
        goBackJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickGoBackJButton();
            }
        });
        timeLineJPanel.add(goBackJButton);

        //添加统一控制课程显示的按钮
        showHide = "Show";
        showHideJButton = new JButton(showHide);
        showHideJButton.setBounds(jPanelWidth - 400, jPanelHeight - 50, 100, 30);
        showHideJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickShowHideJButton();
            }
        });
        timeLineJPanel.add(showHideJButton);
    }

    /**
     * 用来根据课程信息生成表示课程的JButton的方法
     * @param course
     * @return JButton
     */
    public JButton generateLessonJButton(Course course) {
        //new一个JButton并显示课程名称，设置JButton背景透明，设置JButton无边框
        JButton lessonJButton = new JButton(course.getCourseName());
        lessonJButton.setContentAreaFilled(false);
        lessonJButton.setBorderPainted(false);
        Font font1 = new Font("Arial", Font.BOLD, 20);
        lessonJButton.setFont(font1);

        //添加事件监听器
        lessonJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击课程JButton后触发的方法
                clickLessonJButton(course);
            }
        });

        return lessonJButton;
    }

    /**
     * 点击课程JButton后触发的方法
     * 用于显示课程的主要信息
     * @param course
     */
    public void clickLessonJButton(Course course) {
        //new一个子窗口，以主JFrame为父窗口，设置子窗口的大小，设置生成位置为屏幕中央
        JDialog jDialog = new JDialog(mainJFrame, course.getCourseName());
        jDialog.setSize(900, 500);
        jDialog.setLocationRelativeTo(null);

        //new一个JPanel为HeadingAndIntroJPanel用来放置课程名称和简介，布局为2行1列的GridLayout
        JPanel HeadingAndIntroJPanel = new JPanel(new GridLayout(2,1));
        //将HeadingAndIntroJPanel添加到jDialog的NORTH位置
        jDialog.add(HeadingAndIntroJPanel, BorderLayout.NORTH);

        //new一个JLabel为headingJLabel用来显示课程名称，并设置文本水平居中
        JLabel headingJLabel = new JLabel(course.getCourseName());
        headingJLabel.setHorizontalAlignment(JLabel.CENTER);
        //将headingJLabel添加到HeadingAndIntroJPanel
        HeadingAndIntroJPanel.add(headingJLabel);
        Font fontHeading = new Font("Arial", Font.BOLD, 40);
        headingJLabel.setFont(fontHeading);

        //new一个JTextField为introJLabel用来显示课程简介，并设置文本水平居中
        JTextArea introJTextArea = new JTextArea(course.getCourseDescribe());
        introJTextArea.setFont(font);
        introJTextArea.setEditable(false);
        introJTextArea.setAlignmentX(CENTER_ALIGNMENT);
        introJTextArea.setLineWrap(true);
        introJTextArea.setWrapStyleWord(true);
        HeadingAndIntroJPanel.add(introJTextArea);
//        JLabel introJLabel = new JLabel(course.getCourseDescribe());
//        introJLabel.setHorizontalAlignment(JLabel.CENTER);
//        //将introJLabel添加到HeadingAndIntroJPanel
//        HeadingAndIntroJPanel.add(introJLabel);
//        introJLabel.setFont(font);

        //将课程信息存放到content
        Object[][] content = {
                {"ID", course.getCourseID()},
                {"Name", course.getCourseName()},
                {"Teacher", course.getCourseTeacher()},
                {"Term", course.getCourseTerm()},
                {"Credit", course.getCourseCredit()},
                {"Score", course.getCourseScore()}
        };

        //课程信息表格的表头为空
        String[] strings = {"", ""};

        //new一个JTable为contentJTable展示课程信息表格
        JTable contentJTable = new JTable(content, strings);
        contentJTable.setFont(font);
        contentJTable.setRowHeight(30);
        //将contentJTable添加到jDialog的CENTER位置
        jDialog.add(contentJTable, BorderLayout.CENTER);


        //new一个JButton为videoJButton
        JButton videoJButton = new JButton("Watch the video");
        videoJButton.setFont(font);
        //添加事件监听器，点击开始播放视频介绍视频
        videoJButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.launch(VideoPlayer.class,"resources/video/video.mp4");
            }
        });
        //将contentJTable添加到jDialog的SOUTH位置
        jDialog.add(videoJButton, BorderLayout.SOUTH);

        //将jDialog设置为可见，关闭设置为DISPOSE_ON_CLOSE
        jDialog.setVisible(true);
        jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * 点击学期JButton后触发的方法
     * 用于修改课程是否显示
     * @param i
     */
    public void clickSemesterJButton(int i) {
        //将当前显示状态反转
        if (setLessonListVisible[i]) {
            setLessonListVisible[i] = false;
        }
        else {
            setLessonListVisible[i] = true;
        }

        //更新课程可视状态
        lessonListJPanel[i].setVisible(setLessonListVisible[i]);
    }

    /**
     * 点击goBackJButton后触发的方法
     * 返回主界面
     */
    public void clickGoBackJButton() {
        //调回原大小
        mainJFrame.setSize(500, 500);
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.comeBack();
    }

    /**
     * 点击showHideJButton后触发的方法
     * 统一转换所有课程显示状态
     */
    public void clickShowHideJButton() {
        if (showHide.equals("Show")) {
            showHide = "Hide";

            for (int i = 0; i <= 7; i++) {
                setLessonListVisible[i] = true;
                lessonListJPanel[i].setVisible(setLessonListVisible[i]);
            }
        }
        else {
            showHide = "Show";
            for (int i = 0; i <= 7; i++) {
                setLessonListVisible[i] = false;
                lessonListJPanel[i].setVisible(setLessonListVisible[i]);
            }
        }

        showHideJButton.setText(showHide);
    }

}

package View;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-04-09-13:41
 * @Description:
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class tomaClock extends JFrame {
    private static final int WIDTH = 650;
    private static final int HEIGHT = 400;

    private int totalTimeInSeconds;
    private int remainingTimeInSeconds;
    private int startTimeInSeconds;

    private Timer countdownTimer;
    private JLabel timerLabel;
    private JSpinner hoursSpinner;
    private JSpinner minutesSpinner;
    private JSpinner secondsSpinner;
    private CustomTimerPanel timerPanel;

    public tomaClock() {
        setTitle("Custom Countdown Timer");
        setSize(WIDTH, HEIGHT);
      // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();

        // create the timer panel
        timerPanel = new CustomTimerPanel();
        add(panel, BorderLayout.CENTER);
        add(timerPanel, BorderLayout.SOUTH);

        // create the timer label
        timerLabel = new JLabel("00:00:00");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        timerLabel.setFont(timerLabel.getFont().deriveFont(30f));
        timerLabel.setPreferredSize(new Dimension(200, 50));
        panel.add(timerLabel);

        // create the hours spinner
        hoursSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
        hoursSpinner.setPreferredSize(new Dimension(80, 30));
        panel.add(hoursSpinner);

        // create the minutes spinner
        minutesSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        minutesSpinner.setPreferredSize(new Dimension(80, 30));
        panel.add(minutesSpinner);

        // create the seconds spinner
        secondsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        secondsSpinner.setPreferredSize(new Dimension(80, 30));
        panel.add(secondsSpinner);

        // create the start button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startCountdown();
            }
        });
        panel.add(startButton);

        // create the reset button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetCountdown();
            }
        });
        panel.add(resetButton);

        setVisible(true);
    }

    private class CustomTimerPanel extends JPanel {
        public CustomTimerPanel() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(300, 300));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // calculate the remaining time in percentage of the total time
            double remainingTimeInPercentage = (double) remainingTimeInSeconds / totalTimeInSeconds;

            // calculate the angle for the remaining time in percentage
            double angle = remainingTimeInPercentage * 360;

            // calculate the center point of the panel
            int centerX = getWidth() / 2;
            int centerY = getHeight() /2;
            // calculate the radius of the panel
            int radius = Math.min(getWidth(), getHeight()) / 2 - 20;

            // draw the background circle
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

            // draw the remaining time circle
            g2d.setColor(Color.BLUE);
            g2d.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, -90, (int) -angle);

            // draw the border circle
            g2d.setColor(Color.BLACK);
            g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        }
    }

    private void startCountdown() {
        int hours = (int) hoursSpinner.getValue();
        int minutes = (int) minutesSpinner.getValue();
        int seconds = (int) secondsSpinner.getValue();

        startTimeInSeconds = hours * 3600 + minutes * 60 + seconds;
        totalTimeInSeconds = startTimeInSeconds;
        remainingTimeInSeconds = startTimeInSeconds;

        timerLabel.setText(getTimeString(remainingTimeInSeconds));

        countdownTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remainingTimeInSeconds--;
                timerLabel.setText(getTimeString(remainingTimeInSeconds));
                timerPanel.repaint();

                if (remainingTimeInSeconds == 0) {
                    countdownTimer.stop();
                    JOptionPane.showMessageDialog(null, "time out！");


                }
            }
        });
        countdownTimer.start();
    }

    private void resetCountdown() {
        countdownTimer.stop();
        remainingTimeInSeconds = totalTimeInSeconds;
        timerLabel.setText(getTimeString(remainingTimeInSeconds));
        timerPanel.repaint();
    }

    private String getTimeString(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }



}

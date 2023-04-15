package Model;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;

/**
 * @Author: Zongyou Yang
 * @Date: 2023-03-28-12:44
 * @Description: entity class of student
 */
public class testYZYtem2 extends JFrame  {

    public static void main(String[] args) {
        testYZYtem2 jf=new testYZYtem2();
        jf.setSize(500,500);
        jf.setLayout(null);
        JButton input=new JButton("点击导入文本");
        input.setBounds(0,0,150,50);
        jf.add(input);
        JTextPane jtp=new JTextPane();
        jtp.setBounds(60,80,350,350);
        jf.add(jtp);
        jf.setVisible(true);

        input.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent  e) {
                JFileChooser fd = new JFileChooser();
                fd.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
                fd.showOpenDialog(null);
                File f = fd.getSelectedFile();
                if (f != null) {
                    String filepath = f.getPath().trim();
                    //读取本地txt
                    StringBuffer buffer = new StringBuffer();
                    BufferedReader bf;
                    try {
                        bf = new BufferedReader(new FileReader(filepath));
                        String s = null;
                        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
                            buffer.append(s.trim());
                        }
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }//通过地址，缓冲数组读入本地数据

                    String xml = buffer.toString();
                    jtp.setText(xml);

                }
            }
        });

    }

}
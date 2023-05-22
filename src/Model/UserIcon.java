package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
public class UserIcon {
    private String studentImage;
    private String studentID;

    public UserIcon(String studentID, String studentImage) {
        this.studentID = studentID;
        this.studentImage = studentImage;
    }

    public UserIcon(String studentID){
        this.studentID = studentID;
        setDefaultIcon(studentID);
    }

    public void setDefaultIcon(String studentID) {
        this.studentID = studentID;
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);//Default image
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 100, 100);
        g.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        this.studentImage= Base64.getEncoder().encodeToString(bytes);
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

}

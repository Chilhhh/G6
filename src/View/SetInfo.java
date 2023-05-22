package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Objects;

import DAO.JSONUtils;
import Model.*;
import Model.UserIcons;

import com.alibaba.fastjson.JSON;

public class SetInfo extends JLayeredPane implements ActionListener {
    private JLabel idLabel, nameLabel, passwordLabel, emailLabel, imageLabel, imagetipLabel, phoneLabel,
            competitionLabel, activityLabel;
    private JTextArea competitionArea, activityArea;
    private JTextField idField, emailField, phoneField,nameFieldNotNull;
    private NameField nameFieldNull;
    private JPasswordField passwordField;
    private JButton updateButton, resetButton, uploadImageButton;
    private Student stucopy;
    private UserIcons iconArray;
    menuPage menuPage;
    JPanel panel;

    public SetInfo(Student student, menuPage menuPage) {
        this.menuPage = menuPage;
        stucopy = student;
        String currentID = stucopy.getStudentID();
        nullpointerchecker();
        Path path = Paths.get("src/Data/UserIcons.json");
        this.iconArray=new UserIcons();



        panel = new JPanel();
        panel.setBounds(0,0,800,600);
       this.add(panel,new Integer(1));
        if(!Files.exists(path)){
                try {
                    JSONUtils.createJSONFile(iconArray.getIcons(), "src/Data/UserIcons.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        else {
            try {
                List<UserIcon> a = JSON.parseArray(JSONUtils.readJSONFile("src/Data/UserIcons.json"), UserIcon.class);
                int size = a.size();
                for (UserIcon userIcon : a) {
                    iconArray.addIcon(userIcon);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        boolean exist =iconArray.isIconExist(currentID);
        if(!exist) iconArray.addIcon(new UserIcon(currentID));



        panel.setLayout(null);


        panel.setSize(1170,800);
        JButton Back_Buntton = new JButton("Back\n");


        Back_Buntton.setFont(new Font("Arial", Font.BOLD, 20));
        Back_Buntton.setContentAreaFilled(false);
        Back_Buntton.setFocusPainted(false);
        Back_Buntton.setForeground(new Color(131, 129, 129));
        Back_Buntton.setBounds(0, 0, 100, 50);
        Back_Buntton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickGoBackJButton();
            }
        });


        panel.add(Back_Buntton);

        BufferedImage img =base64ToImage(iconArray.getIconByID(currentID));
        imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(img)));
        imageLabel.setBounds(100, 200, 300, 300);
        panel.add(imageLabel);

        uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBounds(170, 600, 150, 30);
        panel.add(uploadImageButton);
        uploadImageButton.addActionListener(this);

        imagetipLabel = new JLabel("will be save at 100*100");
        imagetipLabel.setBounds(200, 950, 200, 30);
        panel.add(imagetipLabel);

        idLabel = new JLabel("ID:");
        idLabel.setBounds(500, 200, 100, 30);
        panel.add(idLabel);

        idField = new JTextField(10);
        idField.setBounds(600, 200, 150, 30);
        panel.add(idField);
        idField.setText(student.getStudentID());
        idField.setEditable(false);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(500, 250, 100, 30);
        panel.add(nameLabel);


        nameFieldNotNull = new JTextField(10);
        nameFieldNotNull.setBounds(600, 250, 150, 30);
        nameFieldNull = new NameField("SET ONLY ONCE",10);
        nameFieldNull.setBounds(600, 250, 150, 30);
        if(student.getStudentName().equals("")) {
            panel.add(nameFieldNull);
        }
        else {
            panel.add(nameFieldNotNull);
            nameFieldNotNull.setText(student.getStudentName());
            nameFieldNotNull.setEditable(false);
        }
        nameFieldNull.getDocument().addDocumentListener(new NameFieldListener());


        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(500, 300, 100, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(10);
        passwordField.setBounds(600, 300, 150, 30);
        panel.add(passwordField);
        passwordField.setText(student.getStudentPassword());

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(500, 350, 100, 30);
        panel.add(emailLabel);

        emailField = new JTextField(10);
        emailField.setBounds(600, 350, 150, 30);
        panel.add(emailField);
        emailField.setText(student.getStudentEmail());

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(500, 400, 100, 30);
        panel.add(phoneLabel);

        phoneField = new JTextField(10);
        phoneField.setBounds(600, 400, 150, 30);
        panel.add(phoneField);
        phoneField.setText(student.getStudentPhone());

        updateButton = new JButton("Update");
        updateButton.setBounds(500,600,80, 30);
        updateButton.addActionListener(this);
        panel.add(updateButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(600,600,80, 30);
        resetButton.addActionListener(this);
        panel.add(resetButton);

        competitionLabel = new JLabel("Student's competition experience:");
        competitionLabel.setBounds(800, 150, 300, 30);
        panel.add(competitionLabel);

        competitionArea = new JTextArea(3, 30);
        competitionArea.setBounds(800, 200, 300, 100);
        panel.add(competitionArea);
        competitionArea.setText(student.getStudentCompetition());

        activityLabel = new JLabel("Student's activity experience:");
        activityLabel.setBounds(800, 300, 300, 30);
        panel.add(activityLabel);

        activityArea = new JTextArea(3, 30);
        activityArea.setBounds(800, 350, 300, 100);
        panel.add(activityArea);
        activityArea.setText(student.getStudentActivity());





    }

    private class NameField extends JTextField {
        private String placeholder;
        private boolean drawPlaceholder = true;

        public NameField(String placeholder, int columns) {
            this.placeholder = placeholder;
            this.setColumns(columns);
        }
        public NameField(int columns) {
            this.setColumns(columns);
        }

        public void setPlaceholder(String holder) {
            placeholder = holder;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (drawPlaceholder) {
                g.setColor(Color.GRAY);
                g.drawString(placeholder, 10, getHeight() / 2 + 5);
            }
        }
        private void updateDrawState() {
            // 根据文本内容更新绘制状态
            drawPlaceholder = getText().isEmpty();
            repaint();
        }
    }

    private class NameFieldListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            nameFieldNull.updateDrawState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            nameFieldNull.updateDrawState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            nameFieldNull.updateDrawState();
        }
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateInfo();
        } else if (e.getSource() == resetButton) {
            int resetConfirm = JOptionPane.showConfirmDialog(this, "All your changes will not be saved!",
                    "Reset Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resetConfirm == JOptionPane.YES_OPTION) {
                resetInfo();
            }
        } else if (e.getSource() == uploadImageButton) {
            setImageFile();
        }
    }

    private void updateInfo() { // first check pw then check email and phone and lastly save competition and activity and name
        if (!stucopy.getStudentPassword().equals(String.valueOf(passwordField.getPassword()))) {
            if (stucopy.getStudentPassword()
                    .equals(JOptionPane.showInputDialog(this, "Please verify your original password:",
                            "You are trying to change your password!", JOptionPane.WARNING_MESSAGE))) {
                stucopy.setStudentPassword(String.valueOf(passwordField.getPassword()));
                if (saveEmail() && savePhone()&&saveName()) {
                    stucopy.setStudentCompetition(competitionArea.getText());
                    stucopy.setStudentActivity(activityArea.getText());
                    JOptionPane.showMessageDialog(this, "Information updated!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong password!", "Error", JOptionPane.ERROR_MESSAGE);
                resetInfo();
            }
        } else if (!stucopy.getStudentEmail().equals(emailField.getText())) {
            if (saveEmail() && savePhone()&&saveName()) {
                stucopy.setStudentCompetition(competitionArea.getText());
                stucopy.setStudentActivity(activityArea.getText());
                JOptionPane.showMessageDialog(this, "Information updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (!stucopy.getStudentPhone().equals(phoneField.getText())) {
            if (savePhone()&&saveName()) {
                stucopy.setStudentCompetition(competitionArea.getText());
                stucopy.setStudentActivity(activityArea.getText());
                JOptionPane.showMessageDialog(this, "Information updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (!stucopy.getStudentCompetition().equals(competitionArea.getText())||!stucopy.getStudentActivity().equals(activityArea.getText())) {
            stucopy.setStudentCompetition(competitionArea.getText());
            stucopy.setStudentActivity(activityArea.getText());
            JOptionPane.showMessageDialog(this, "Information updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (saveName()) {
            stucopy.setStudentCompetition(competitionArea.getText());
            stucopy.setStudentActivity(activityArea.getText());
            JOptionPane.showMessageDialog(this, "Information updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "No changes!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetInfo() {
        idField.setText(stucopy.getStudentID());
        nameFieldNull.setText(stucopy.getStudentName());
        passwordField.setText(stucopy.getStudentPassword());
        emailField.setText(stucopy.getStudentEmail());
        phoneField.setText(stucopy.getStudentPhone());
        competitionArea.setText(stucopy.getStudentCompetition());
        activityArea.setText(stucopy.getStudentActivity());
    }

    private void setImageFile() {
        JFileChooser chooser = new JFileChooser();
        BufferedImage image = null;
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setDialogTitle("Please choose a image");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image(*.jpg,*.png,*.bmp,*.gif,*.wbmp,*.ico)",
                "jpg", "png", "bmp", "gif", "wbmp", "ico");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        int state = chooser.showOpenDialog(this);
        if (state == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (isImage(file)) {
                String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                try {
                    image = ImageIO.read(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Reading file error,please try again!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                BufferedImage resizedImage = new BufferedImage(100, 100, Objects.requireNonNull(image).getType());
                Graphics2D g = resizedImage.createGraphics();
                AffineTransform at = AffineTransform.getScaleInstance(
                        (double) resizedImage.getWidth() / image.getWidth(),
                        (double) resizedImage.getHeight() / image.getHeight());
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawRenderedImage(image, at);
                g.dispose();
                iconArray.setIconByID(stucopy.getStudentID(),imageToBase64(resizedImage, extension));
                JOptionPane.showMessageDialog(this, "Image uploaded successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                imageLabel.setIcon(new ImageIcon(Objects.requireNonNull(base64ToImage(iconArray.getIconByID(stucopy.getStudentID())))));
                try {
                    JSONUtils.createJSONFile(iconArray.getIcons(), "src/Data/UserIcons.json");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (state == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(this, "Something went wrong,please try again!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private boolean isImage(File file) {
        try {
            ImageIO.read(file);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isEmail(String email) {
        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(regex);
    }

    private boolean saveEmail() {
        if (isEmail(emailField.getText())) {
            stucopy.setStudentEmail(emailField.getText());
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email address!", "Error", JOptionPane.ERROR_MESSAGE);
            emailField.setText(stucopy.getStudentEmail());
            return false;
        }
    }

    private boolean savePhone() {
        if(phoneField.getText().isEmpty()) return false;
        else if (phoneField.getText().length() == 11) {
            stucopy.setStudentPhone(phoneField.getText());
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Invalid phone number!Must be thirteen", "Error", JOptionPane.ERROR_MESSAGE);
            phoneField.setText(stucopy.getStudentPhone());
            return false;
        }
    }

    private boolean saveName(){
            if(nameFieldNull.getText().equals(stucopy.getStudentName())) return false;
            stucopy.setStudentName(nameFieldNull.getText());
            nameFieldNull.setText(stucopy.getStudentName());
            if(!nameFieldNull.getText().isEmpty()) {
                nameFieldNull.setEditable(false);
            }
            return true;
    }
    public static BufferedImage base64ToImage(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        ByteArrayInputStream bais =  new ByteArrayInputStream(bytes);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String imageToBase64(BufferedImage image, String format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, format, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }
    public void clickGoBackJButton() {
        //调回原大小
        menuPage.setSize(1114, 617);
        menuPage.setLocationRelativeTo(null);
        menuPage.comeBack();
    }

    private void nullpointerchecker(){
        if(stucopy.getStudentEmail()==null){
            stucopy.setStudentEmail("");
        }

        if (stucopy.getStudentPhone()==null){
            stucopy.setStudentPhone("");
        }

        if (stucopy.getStudentCompetition()==null){
            stucopy.setStudentCompetition("");
        }

        if (stucopy.getStudentActivity()==null){
            stucopy.setStudentActivity("");
        }
        if(stucopy.getStudentName()==null){
            stucopy.setStudentName("");
        }
        if(stucopy.getStudentCompetition()==null){
            stucopy.setStudentCompetition("");
        }
        if(stucopy.getStudentActivity()==null) {
            stucopy.setStudentActivity("");
        }
    }
}

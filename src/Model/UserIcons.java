package Model;

import java.util.ArrayList;

public class UserIcons {
    private ArrayList<UserIcon> UserIcons;

    public UserIcons() {
        this.UserIcons =new ArrayList<>();
    }

    public void addIcon(UserIcon userIcon) {
        this.UserIcons.add(userIcon);
    }

    public void removeIcon(UserIcon userIcon) {
        this.UserIcons.remove(userIcon);
    }

    public ArrayList<UserIcon> getIcons() {
        return UserIcons;
    }

    public String getIconByID(String studentID) {
        for (UserIcon userIcon : UserIcons) {
            if (userIcon.getStudentID().equals(studentID)) {
                return userIcon.getStudentImage();
            }
        }
        return null;
    }

    public boolean isIconExist(String studentID) {
        for (UserIcon userIcon : UserIcons) {
            if (userIcon.getStudentID().equals(studentID)) {
                return true;
            }
        }
        return false;
    }
    public void setIconByID(String studentID, String studentImage) {
        for (UserIcon userIcon : UserIcons) {
            if (userIcon.getStudentID().equals(studentID)) {
                userIcon.setStudentImage(studentImage);
            }
        }
    }
}

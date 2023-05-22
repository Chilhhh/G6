package Controller;

import DAO.JSONUtils;
import Model.Student;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-19-10:08
 * @Description:
 */
public class LoginController {
    private int username;
    private String password;

    public LoginController(int username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean isCorrect(int username,String Password) throws IOException {
        List<Student> stu=  JSON.parseArray(JSONUtils.readJSONFile("src\\Data\\student.json"),Student.class);
        for (Student s:stu){
            if (Integer.parseInt (s.getStudentID())==username){
                if (s.getStudentPassword().equals(Password)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean Reg(int username,String Password) throws IOException {
        List<Student> stu=  JSON.parseArray(JSONUtils.readJSONFile("src\\Data\\student.json"),Student.class);
        for (Student s:stu){
            if (Integer.parseInt (s.getStudentID())==username){
                return false;//已经存在
            }
        }
        Student s=new Student(String.valueOf(username),Password);
        stu.add(s);
        JSONUtils.createJSONFile(stu,"src\\Data\\student.json");
        return true;
    }
}

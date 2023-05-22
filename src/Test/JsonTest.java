package Test;


import DAO.JSONUtils;
import Model.Course;
import Model.Student;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-19-17:26
 * @Description:
 */




public class JsonTest {


    public static void main(String[] args) {
        ArrayList<Student> aa=new ArrayList<>();
       Student s0=new Student("11111","zzaa");
        Student s1=new Student("12321","zfsfsa");
        Student s2=new Student("12431","zfssfsea");
        aa.add(s0);
        aa.add(s1);
        aa.add(s2);

        String filename = "src\\Data\\student.json";
        try {
            JSONUtils.createJSONFile(aa, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
           List<Student> a=  JSON.parseArray(JSONUtils.readJSONFile("src\\Data\\student.json"),Student.class);
            Student adw=a.get(0);
            System.out.println(adw.getStudentID());
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Course course=new Course();
//        course.setCourseID("123");
//        course.setCourseName("123");
//        String filename1 = "src\\Data\\course.json";
//        try {
//            JSONUtils.createJSONFile(course, filename1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//    Student student=new Student();
//    student.setStudentID("123");
//    student.setStudentName("123");
//    String filename2 = "src\\Data\\student.json";
//        try {
//            JSONUtils.createJSONFile(student, filename2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}}

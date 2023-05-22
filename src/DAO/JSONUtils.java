package DAO;
import Model.Course;
import com.alibaba.fastjson.JSON;
import java.io.FileWriter;
import java.io.IOException;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-03-19-17:18
 * @Description:
 */
public class JSONUtils {
    /**
     * @description: 将JSON字符串写入文件,传入普通类，生成JSON文件
     * @param data
     * @param filename
     * @return: void
     * @throws IOException
     */
    public static void createJSONFile(Object data, String filename) throws IOException {
        String jsonString = JSON.toJSONString(data);
        System.out.println(jsonString);
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();
        System.out.println("JSON file " + filename + " created successfully.");
    }


    public static String readJSONFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String jsonString = "";
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString += line;
        }
        reader.close();
        return jsonString;
    }

    // 例子
    public static void main(String[] args) throws IOException {
       Course course=new Course();
         course.setCourseID("123");
            course.setCourseName("123");

        String filename = "example.json";
        createJSONFile( course, filename);
    }
}

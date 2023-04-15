package Model;

import java.util.ArrayList;
/**
 * @Author: Zongyou Yang
 * @Date: 2023-03-28-12:44
 * @Description: entity class of student
 */
public class testYZYv2 {
    public static void main(String[] args) {
        InfoReader infoReader = new InfoReader();
        //infoReader.readit("src/Model/Info.txt");
        infoReader.readit();
        //infoReader.readit("Info.txt");
        System.out.println(infoReader.getStudents());
        System.out.println(infoReader.getCourses());
        System.out.println();
        //TimeLineDisplay timeLineDisplay = new TimeLineDisplay(infoReader);
    }

}

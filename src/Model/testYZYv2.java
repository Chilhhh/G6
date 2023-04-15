package Model;

import java.util.ArrayList;

public class testYZYv2 {
    public static void main(String[] args) {
        InfoReadertem infoReadertem = new InfoReadertem();
        //infoReader.readit("src/Model/Info.txt");
        infoReadertem.readit();
        //infoReader.readit("Info.txt");
        System.out.println(infoReadertem.getStudents());
        System.out.println(infoReadertem.getCourses());
        System.out.println();
        //TimeLineDisplay timeLineDisplay = new TimeLineDisplay(infoReader);
    }





}

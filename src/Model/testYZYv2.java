package Model;

import java.util.ArrayList;

public class testYZYv2 {
    public static void main(String[] args) {
        InfoReader infoReader = new InfoReader();
        infoReader.readit("src/Model/Info.txt");
        System.out.println(infoReader.getStudents());
        System.out.println(infoReader.getCourses());
        System.out.println();
    }





}

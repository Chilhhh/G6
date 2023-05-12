package Model;

import java.util.ArrayList;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-04-15-16:10
 * @Description:
 */
public class Students {
    private ArrayList<Student> Students;
    private String JsonfilePath;

    public  Students() {
        this.Students = new ArrayList<>();
        this.JsonfilePath = "src/Data/Students.json";
    }

    public void addStudent(Student student) {
        this.Students.add(student);
    }
    public void removeStudent(Student student) {
        this.Students.remove(student);

    }

    public ArrayList<Student> getStudents() {
        return Students;
    }

    public String getJsonfilePath() {
        return JsonfilePath;
    }

    public void setJsonfilePath(String jsonfilePath) {
        JsonfilePath = jsonfilePath;
    }

    public void setStudents(ArrayList<Student> students) {
        Students = students;
    }

    @Override
    public String toString() {
        return "Students{" +
                "Students=" + Students +
                '}';
    }
}

package Model;

import java.util.ArrayList;

/**
 * @Author: Zhouhe Zhang
 * @Date: 2023-04-15-16:10
 * @Description:
 */
public class Courses {
    private ArrayList<Course> courses;
    private String JsonfilePath;
    public Courses() {
        this.courses =new ArrayList<>();
       this.JsonfilePath = "src/Data/Courses.json";
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getJsonfilePath() {
        return JsonfilePath;
    }

    public void setJsonfilePath(String jsonfilePath) {
        JsonfilePath = jsonfilePath;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courses=" + courses +
                '}';
    }
}

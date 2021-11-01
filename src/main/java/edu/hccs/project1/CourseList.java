package edu.hccs.project1;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class CourseList {
    private ArrayList<Course> courseList = null;

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public CourseList() {
        courseList = new ArrayList<>();
    }

    public ArrayList<Course> addCourse(Course course){
        getCourseList().add(course);
        return courseList;
    }

    @Override
    public String toString() {
        return "{" + courseList +
                '}';
    }
}

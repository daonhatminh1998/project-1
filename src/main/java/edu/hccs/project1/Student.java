package edu.hccs.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class Student {
    private int id;
    private String first_name;
    private String email;
    private String gender;
    @Autowired
    private CourseList course = new CourseList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CourseList getCourse() {
        return course;
    }

    public void setCourse(CourseList course) {
        this.course = course;
    }

    public Student(int id, String first_name, String email, String gender, CourseList course) {
        this.id = id;
        this.first_name = first_name;
        this.email = email;
        this.gender = gender;
        this.course = course;
    }

    public Student() {
    }

    public int getPoint(String grade){
        int point = 0;
        for(Course course : course.getCourseList()) {
            if(grade.trim().compareTo("A") == 0) {
                point = 4;
            }
            else if(grade.trim().compareTo("B") == 0) {
                point = 3;
            }
            else if(grade.trim().compareTo("C") == 0) {
                point = 2;
            }
            else if(grade.trim().compareTo("D") == 0) {
                point = 1;
            }
            else {
                point = 0;
            }
        }
        return point;
    }

    public double calculateGPA(){
        int totalCreditHours = 0;
        int totalPoint = 0;
        double gpa = 0;
        if(course.getCourseList().isEmpty())
            return gpa =0 ;
        for(Course course : course.getCourseList())
        {
            totalCreditHours += course.getCreditHours();
            int point = getPoint(course.getGrade())*course.getCreditHours();
            totalPoint += point;
        }
        gpa = (double) totalPoint/totalCreditHours;
//        System.out.println(gpa);
        return gpa;

        }

    @Override
    public String toString() {
        return '\n'+ "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", course=" + course +
                '}';
    }

}

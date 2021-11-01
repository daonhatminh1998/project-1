package edu.hccs.project1;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class StudentList {
    private ArrayList<Student> studentList = null;

    public StudentList() {
        studentList = new ArrayList<>();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> list) {
        studentList = list;
    }

    public ArrayList<Student> addStudent(Student student) {
        getStudentList().add(student);
        return studentList;
    }

    public void search(String search) {
        int check = 0;
        System.out.println("\n" + "Search by name or by course no: " + search);
        for (Student e : studentList) {
            if (e.getFirst_name().trim().equalsIgnoreCase(search.trim())) {
                System.out.println(e);
                check++;
            }
            for (int i = 0; i < e.getCourse().getCourseList().size(); i++) {
                if (e.getCourse().getCourseList().get(i).getCourseNo().trim().equalsIgnoreCase(search.trim())) {
                    System.out.println(e);
                    check++;
                }
            }
        }
        if (check == 0) {
            System.out.println("Not found!!");
        }
    }
}


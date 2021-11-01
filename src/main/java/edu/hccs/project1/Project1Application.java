package edu.hccs.project1;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;

@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) throws ParseException {
		ConfigurableApplicationContext context =  SpringApplication.run(Project1Application.class, args);

		StudentList studentList = new StudentList();

		studentList.setStudentList(parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student_course.json"));
		studentList.search("Aida  ");
		studentList.search("CS12 ");

		for (Student e : studentList.getStudentList()) {
			if(!e.getCourse().getCourseList().isEmpty()){
				System.out.println("GPA of" +e.getFirst_name()+" : "+e.calculateGPA());
			}
			else System.out.println("Can not calculate GPA of "+e.getFirst_name());
		}
	}


	public static ArrayList<Student> parseJSOn(String url) throws ParseException {
		StudentList studentList = new StudentList();

		Client client = Client.create();
		WebResource webResource = client.resource(url);

		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		if (clientResponse.getStatus() != 200) {
			throw new RuntimeException("Failed" + clientResponse.toString());
		}
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

		Iterator<Object> it =  jsonArray.iterator();

		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			JSONArray courseList = (JSONArray) jsonObject.get("course");
			CourseList coursesList = new CourseList();

			if(courseList != null) {
				Iterator<Object> a = courseList.iterator();
				while (a.hasNext()) {
					JSONObject courseObject = (JSONObject) a.next();
					Course course = new Course((courseObject.get("courseNo").toString()),courseObject.get("grade").toString(), Integer.parseInt(courseObject.get("creditHours").toString()));
					coursesList.addCourse(course);
				}
			}
			Student student = new Student(Integer.parseInt(jsonObject.get("id").toString()), jsonObject.get("first_name").toString(), jsonObject.get("email").toString(), jsonObject.get("gender").toString(), coursesList);
			studentList.addStudent(student);
		}
		System.out.println(studentList.getStudentList());
		return studentList.getStudentList();
	}

}

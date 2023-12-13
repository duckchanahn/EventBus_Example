/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in MyungJi University 
 */

package Components.Student;

import Components.Course.CourseComponent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentComponent {
	protected ArrayList<Student> vStudent;
	
	public StudentComponent(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Student>();
		while (bufferedReader.ready()) {
			String stuInfo = bufferedReader.readLine();
			if (!stuInfo.equals("")) this.vStudent.add(new Student(stuInfo));
		}
		bufferedReader.close();
	}
	public ArrayList<Student> getStudentList() {
		return vStudent;
	}
	public void setvStudent(ArrayList<Student> vStudent) {
		this.vStudent = vStudent;
	}
	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			if (((Student) this.vStudent.get(i)).match(sSID)) return true;
		}
		return false;
	}
	public int indexCourse(String studentId) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			if(this.vStudent.get(i).match(studentId)) {return i;}
		}
		return -1;
	}
	public String signupCourse(String message) {
		int index = -1;
		for (int i = 0; i < this.vStudent.size(); i++) {
			if(this.vStudent.get(i).match(message.split(" ")[0])) index = i;
		}
		if (message.split(" ").length == 2) {
			this.vStudent.get(index).getCompletedCourses().add(message.split(" ")[1]);
			return "success";
		} else if (message.split(" ")[2].equals("-1")) {
			return "This course is unregistered.";
		} else if (this.vStudent.get(index).checkPreCourse(message.split(" ")[2])) {
			this.vStudent.get(index).getCompletedCourses().add(message.split(" ")[1]);
			return "success";
		} else {
			return "선수과목 미이수";
		}
	}
}

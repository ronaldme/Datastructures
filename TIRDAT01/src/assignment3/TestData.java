package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestData {

	private ArrayList<Course> courses;
	private Student s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
	private Course v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15;
	
	public ArrayList<Course> AddToAdministration(Administration adm) {
		courses = new ArrayList<Course>();
		createCourses();
		createStudents();
		
		adm.addStudent(s1.getStudentNumber(), s1);
		adm.addStudent(s2.getStudentNumber(), s2);
		adm.addStudent(s3.getStudentNumber(), s3);
		adm.addStudent(s4.getStudentNumber(), s4);
		adm.addStudent(s5.getStudentNumber(), s5);
		adm.addStudent(s6.getStudentNumber(), s6);
		adm.addStudent(s7.getStudentNumber(), s7);
		adm.addStudent(s8.getStudentNumber(), s8);
		adm.addStudent(s9.getStudentNumber(), s9);
		adm.addStudent(s10.getStudentNumber(), s10);
		
		return courses;
	}
	
	/**
	 * Create courses for testing
	 */
	private void createCourses() {
		v1 = new Course("TIRDAT01", "123", 1, 5);
		v2 = new Course("TIRDAT01", "123", 1, 7);
		v3 = new Course("TIRDAT01", "123", 1, 7);
		v4 = new Course("TIRDAT01", "123", 1, 8);
		
		v5 = new Course("TIRDAT02", "222", 2, 6);
		v6 = new Course("TIRDAT02", "222", 2, 9);
		
		v7 = new Course("INFPRJ1", "300", 3, -1);
		v8 = new Course("INFPRJ1", "300", 3, 4);
		v9 = new Course("INFPRJ1", "300", 3, 6);
		
		v10 = new Course("MDEV1", "444", 3, 5);
		v11 = new Course("MDEV1", "444", 3, 9);
		
		v12 = new Course("PRES3", "660", 4, 3);
		v13 = new Course("PRES3", "660", 4, 5);
		v14 = new Course("PRES3", "660", 4, 7);

		v15 = new Course("INFP4", "777", 4, 4);
		
		courses.add(v1);
		courses.add(v2);
		courses.add(v3);
		courses.add(v4);
		courses.add(v5);
		courses.add(v6);
		courses.add(v7);
		courses.add(v8);
		courses.add(v9);
		courses.add(v10);
		courses.add(v11);
		courses.add(v12);
		courses.add(v13);
		courses.add(v14);
		courses.add(v15);
	}

	/**
	 * Create students for testing
	 */
	private void createStudents() {
		LinkedList<Course> list = new LinkedList<Course>();
		list.add(v1);
		list.add(v9);
		list.add(v10);
		
		s1 = new Student("1", "Ronald Meijboom", "m", "INF3M", "Informatica", list);
		
		s2 = new Student("2", "Joey Koster", "m", "INF3M", "Informatica");
		s2.addCourse(v2);
		s2.addCourse(v6);

		s3 = new Student("3", "Katinka Vlaar", "v", "IND1P", "Media");
		s3.addCourse(v5);
		s3.addCourse(v1);
		
		s4 = new Student("4", "Chantal van de Olv", "v", "IND1P", "Media");
		s4.addCourse(v4);
		s4.addCourse(v9);
		s4.addCourse(v10);

		s5 = new Student("5", "Leo Raek", "m", "IND1R", "Rechten");
		s5.addCourse(v5);
		s5.addCourse(v3);

		s6 = new Student("6", "Karel Langepotten", "m", "IND1R", "Rechten");
		s6.addCourse(v6);
		
		s7 = new Student("7", "Carl Sem", "m", "IND1R", "Media");
		s7.addCourse(v7);
		s7.addCourse(v11);

		s8 = new Student("8", "Nicky Jalendeel", "v", "IND1R", "Elektro");
		s8.addCourse(v8);
		s8.addCourse(v9);
		s8.addCourse(v12);
		
		s9 = new Student("9", "Jan Nieuwendijk", "m", "INF2M", "Media");
		s9.addCourse(v8);
		s9.addCourse(v1);
		s9.addCourse(v13);
		
		s10 = new Student("10", "Kees de Meer", "m", "TEN32", "Kunst");
		s10.addCourse(v13);
		s10.addCourse(v14);
		s10.addCourse(v15);
	}
}
package assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Administration {
	
	private HashMap<String, Student> studenten;
	
	public Administration() {
		studenten = new HashMap<String, Student>();
	}
	
	/**
	 * Prints all students that completed this course
	 */
	public ArrayList<String> printStudendsCourseCompleted(String modulecode) {
		ArrayList<String> studentsCompleted = new ArrayList<String>();
		
		for(Entry<String, Student> s : studenten.entrySet()) {
			for(Course v : s.getValue().getCompletedCourses()) {
				if(v.getCourseCode().equals(modulecode)) {
					studentsCompleted.add(s.getValue().getName());
				}
			}
		}
		
		return studentsCompleted;
	}
	
	/**
	 * Calculate the average grade of this course
	 */
	public double averageGrade(String modulecode) {
		double averageGrade = 0;
		int count = 0;
		
		for(Entry<String, Student> s : studenten.entrySet()) {
			for(Course vak : s.getValue().getCourses()) {
				if(vak.getCourseCode().equals(modulecode)) {
					averageGrade += vak.getGrade();
					count++;
				}
			}
		}
		
		if(count != 0)
			averageGrade = averageGrade / count;
		
		return averageGrade;
	}
	
	/**
	 * Prints all courses this student has completed
	 */
	public ArrayList<Course> completedCourses(Student s) {
		ArrayList<Course> completedCourses = new ArrayList<Course>();
		
		for(Course c : s.getCompletedCourses()) {
			completedCourses.add(c);			
		}
		
		return completedCourses;
	}
	
	/**
	 * Prints all courses that a student needs to finish
	 */
	public ArrayList<Course> incompletedCourses(Student s) {
		ArrayList<Course> completedCourses = new ArrayList<Course>();
		
		for(Course c : s.getCourses()) {
			if(c.getGrade() < 6) {
				completedCourses.add(c);
			}
		}
		
		return completedCourses;
	}
	
	/**
	 * Return a list with students that are following this direction
	 * @param studyDirection
	 */
	public ArrayList<String> studyDirectionStudent(String studyDirection) {
		ArrayList<String> list = new ArrayList<String>();
		
		for(Entry<String, Student> s : studenten.entrySet()) {
			if(s.getValue().getStudyDirection().equals(studyDirection)) {
				list.add(s.getValue().getName());
			}
		}
		
		return list;
	}
	
	/**
	 * Return a list with students that are in this class
	 * @param studyDirection
	 */
	public ArrayList<String> studentsInClass(String schoolClass) {
		ArrayList<String> list = new ArrayList<String>();

		for(Entry<String, Student> s : studenten.entrySet()) {
			if(s.getValue().getSchoolClass().equals(schoolClass)) {
				list.add(s.getValue().getName());
			}
		}
		
		return list;
	}
	
	/**
	 * Clear the HashMap
	 */
	public void ClearStudents() {
		studenten.clear();
	}
	
	/**
	 * Add multiple students to the HashMap
	 */
	public void AddStudents(ArrayList<Student> studenten) {
		studenten.addAll(studenten);
	}
	
	
	/**
	 * Add a student to the HashMap
	 */
	public void addStudent(String key, Student value) {
		studenten.put(key, value);
	}
	
	/**
	 * Delete this student from the HashMap by value
	 */
	public void removeStudent(Student value) {
		studenten.remove(value);
	}
	
	/**
	 * Removes a student from the HashMap by key
	 */
	public void removeStudent(String key) {
		studenten.remove(key);
	}
	
	/** 
	 * Removes a student from the HashMap by name
	 * @param name
	 */
	public void removeStudentByName(String name) {
		for(Entry<String, Student> s : studenten.entrySet()) {
			 if(s.getValue().getName().equals(name)) {
				 studenten.remove(s.getValue().getStudentNumber());
				 return;
			 }
		 }
	}
	
	/**
	 * Get the student by student number
	 * @param key
	 * @return Student
	 */
	public Student getStudentByNumber(String key) {
		return studenten.get(key);
	}
	
	/**
	 * Get the student by name
	 * @param name
	 * @return Student
	 */
	public Student getStudentByName(String name) {
		for(Entry<String, Student> s : studenten.entrySet()) {
			if(s.getValue().getName() == name) {
				return s.getValue();
			}
		}
		
		return null;
	}
	
	/**
	 * Return a LinkedList<Student> with all the students from the HashMap
	 * @return LinkedList<Student>
	 */
	public LinkedList<Student> getAllStudents() {
		LinkedList<Student> list = new LinkedList<Student>();
		
		for(Entry<String, Student> s : studenten.entrySet()) {
			list.add(s.getValue());
		}
		
		return list;
	}
}
package assignment3;

import java.util.LinkedList;
import java.util.List;

public class Student {
	private String studentNumber;
	private String fullName;
	private String gender;
	private String schoolClass;
	private String studyDirection;
	private LinkedList<Course> courses;
	
	public Student() {
		this.courses = new LinkedList<Course>();
	}
	
	/**
	 * Add data to the student
	 * @param studentNumber
	 * @param name
	 * @param gender
	 * @param schoolClass
	 * @param studyDirection
	 */
	public Student(String studentNumber, String name, String gender, String schoolClass, String studyDirection) {
		this.studentNumber = studentNumber; 
		this.fullName = name;
		setGender(gender);
		this.schoolClass = schoolClass;
		this.studyDirection = studyDirection;
		this.courses = new LinkedList<Course>();
	}
	
	/**
	 * Add all data to the student including multiple courses
	 * @param studentNumber
	 * @param name
	 * @param gender
	 * @param schoolClass
	 * @param studyDirection
	 * @param courses
	 */
	public Student(String studentNumber, String name, String gender, String schoolClass, String studyDirection, List<Course> courses) {
		this.studentNumber = studentNumber; 
		this.fullName = name;
		setGender(gender);
		this.schoolClass = schoolClass;
		this.studyDirection = studyDirection;
		this.courses = new LinkedList<Course>();
		this.courses.addAll(courses);
	}

	/**
	 * Get all courses
	 * @return LinkedList<Course>
	 */
	public LinkedList<Course> getCourses() {
		return this.courses;
	}
	
	/**
	 * Add a course
	 * @param v
	 */
	public void addCourse(Course v) {
		if(!courses.contains(v)) {
			courses.add(v);
		}
	}
	
	/**
	 * Get average grade from courses with a grade above or equal to 6
	 * @return double
	 */
	public double averageCompletedCourses() {
		double average = 0;
		int count = 0;
		
		for(Course v : courses) {
			if(v.getGrade() >= 6) {
				average += v.getGrade();
				count++;
			}
		}
		
		// Prevent null division
		if(count != 0) {
			average /= count;
		}
		return average;
	}
	
	/**
	 * Get all completed courses from a specific year
	 * Use 0 to get completed courses from all years
	 * @param year
	 * @return ArrayList<Course>
	 */
	public LinkedList<Course> getCompletedCourses(int year) {
		LinkedList<Course> vakkenList = new LinkedList<Course>();
		
		if(year == 0) {
			return getCompletedCourses();
		} else {
			for(Course v : courses) {
				if(v.getYear() == year && v.getGrade() >= 6) {
					vakkenList.add(v);
				}
			}	
		}
		
		return vakkenList;
	}
	
	/**
	 * Get all completed courses
	 * @return ArrayList<Course>
	 */
	public LinkedList<Course> getCompletedCourses() {
		LinkedList<Course> vakkenList = new LinkedList<Course>();
		
		for(Course v : courses) {
			if(v.getGrade() >= 6) {
				vakkenList.add(v);
			}
		}
		
		return vakkenList;
	}
	
	/**
	 * Get the student number
	 * @return String
	 */
	public String getStudentNumber() {
		return studentNumber;
	}

	/**
	 * Set the student number
	 * @param studentNumber
	 */
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	/**
	 * Get the name of this student
	 * @return String
	 */
	public String getName() {
		return fullName;
	}

	/**
	 * Set the name of this student.
	 * @param name
	 */
	public void setName(String name) {
		this.fullName = name;
	}

	/**
	 * Get the gender of this student
	 * @return String
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Get the gender of this student
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender.toLowerCase();
	}

	/**
	 * Get the school class
	 * @return String
	 */
	public String getSchoolClass() {
		return schoolClass;
	}

	/**
	 * Set the class
	 * @param schoolClass
	 */
	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}

	/**
	 * Get the study direction
	 * @return String
	 */
	public String getStudyDirection() {
		return studyDirection;
	}

	/**
	 * Set the study direction
	 * @param studyDirection
	 */
	public void setStudyDirection(String studyDirection) {
		this.studyDirection = studyDirection;
	}
		
	/**
	 * Override the equals method to check if the Student objects are the same.
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		if(this == obj)
		return true;
		if((obj == null) || (obj.getClass() != this.getClass()))
		return false;
		
		Student test = (Student)obj;
		return (this.studentNumber != null && this.studentNumber.equals(test.studentNumber));
	}
	
	/**
	 * Override the hashCode function
	 * @return int
	 */
	public int hashCode() {
		 int hash = 7;
		 hash = 31 * hash;
		 hash = 31 * hash + (this.studentNumber == null ? 0 : this.studentNumber.hashCode());
		 return hash;
	}
}

package assignment3;

/**
* 	int grade (-1 through 10, where -1 meaning student has not followed this course)
* 	int year (1 for first year)
*/
public class Course {
	private String courseName;
	private String courseCode;
	private int grade;
	private int year;

	public Course() { }
	
	/**
	 * Create a new course
	 * @param courseCode
	 * @param year
	 * @param grade
	 */
	public Course(String courseName, String courseCode, int year, int grade) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.year = year;
		setGrade(grade);
	}
	
	/**
	 * Get the name of this course
	 * @return String
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	/**
	 * Set the name of this course
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Get the code of this course
	 * @return String
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Set the code of this course
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Get the grade of this course
	 * @return int
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * Set the grade of this course. Validation
	 * is done in the Gui class.
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * Get the year this course is given
	 * @return int
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year this course is given
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Override the equals method to check if the Course objects are the same.
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		if(this == obj)
		return true;
		if((obj == null) || (obj.getClass() != this.getClass()))
		return false;
		
		// object must be Course
		Course test = (Course)obj;
		return this.year == test.year && this.grade == test.grade && 
				(this.courseCode.equals(test.courseCode) || 
				(this.courseCode != null && this.courseCode.equals(test.courseCode))) && (this.courseName.equals(test.courseName) || 
				(this.courseName != null && this.courseName.equals(test.courseName)));
	}
	
	/**
	 * Override the hashCode function
	 * @return int
	 */
	public int hashCode() {
		 int hash = 7;
		 hash = 31 * hash + grade + year;
		 hash = 31 * hash + (this.courseCode == null ? 0 : this.courseCode.hashCode()) + 
				 		(this.courseName == null ? 0 : this.courseName.hashCode());
		 return hash;
	}
}

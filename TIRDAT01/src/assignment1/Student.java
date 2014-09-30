package assignment1;

public class Student {

	private int studentNumber;
	private int age;
	private String name;
	private String gender;
	
	public Student() { }
	
	public Student(int studentNumber, int age, String name, String gender) {
		this.studentNumber = studentNumber;
		this.age = age;
		this.name = name;
		setGender(gender);
	}
	
	/**
	 * Get the student number
	 * @return int
	 */
	public int getStudentNumber() {
		return this.studentNumber;
	}

	/**
	 * Set the student number
	 * @param studentNumber
	 */
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	/**
	 * Get the name of the student
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the student
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the age of the student
	 * @return int
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Set the age of the student
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Get the gender of this student
	 * @return String
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 * Set the gender of this student
	 * @param gender
	 */
	public void setGender(String gender) {
		if(gender.toLowerCase().equals("m") || gender.toLowerCase().equals("f")) {
			this.gender = gender;
		} else {
			System.out.println("Please provide a valid gender: f (emale) or m (ale)");
		}		
	}

	/**
	 * Print information about this student
	 */
	public void printStudent() {
		System.out.println("Student: \t" + this.name);
		System.out.println("StudentNumber: \t" + this.studentNumber);
		System.out.println("Age: \t" + this.age);
		System.out.println("Gender: \t" + this.gender);
		System.out.println("");
	}
}

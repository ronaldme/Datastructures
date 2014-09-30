package assignment2;

public class Student {

	private int studentNumber;
	private int age;
	private String name;
	private String gender;
	
	public Student() { }

	public Student(int studentNumber, String name, int age, String gender) {
		this.studentNumber = studentNumber;
		this.name = name;
		this.age = age;
		setGender(gender);
	}
	
	/**
	 * Get the student number
	 * @return int
	 */
	public int getStudentNumber() {
		return studentNumber;
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
		return name;
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
		return age;
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
		return gender;
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
		System.out.println("Studentnummer: \t" + this.studentNumber);
		System.out.println("Leeftijd: \t" + this.age);
		System.out.println("Geslacht: \t" + this.gender);
		System.out.println();
	}
}

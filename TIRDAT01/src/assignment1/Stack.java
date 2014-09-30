package assignment1;

public class Stack {
	private StudentData studentData;
	private int size;
	
	public Stack() {
		studentData = new StudentData(); 
	}
	
	/**
	 * Push a student on the stack.
	 * @param s
	 */
	public void push(Student s) {
		if(studentData == null) {
			studentData = new StudentData();
		}
		
		if(studentData.value == null) {
			studentData.value = s;
		} else {
			StudentData currentStudent = new StudentData();
			currentStudent.value = s;
			currentStudent.node = studentData;
			studentData = currentStudent;
		}
		size++;
	}

	/**
	 * Pop a student from the stack.
	 * @return Student
	 */
	public Student pop() {
		if(studentData == null) return null;
		
		Student s = studentData.value;
		StudentData currentStudent = studentData;
		studentData = currentStudent.node;
		size--;
		
		return s;
	}

	/**
	 * Check if student is on the stack
	 * @return boolean
	 */
	public boolean peek(Student s) {
		if(studentData == null) return false;
		
		StudentData currentStudent = studentData;
		for (int i = 0; i < this.size; i++) {
			if(currentStudent.value.getStudentNumber() == s.getStudentNumber()) {
				return true;
			}
			currentStudent = currentStudent.node;
		}
		
		return false;
	}
	
	/**
	 * Get the stack size
	 * @return int
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Print the stack
	 */
	public void printStack() {
		StudentData currentStudent = studentData;
		
		for(int i = 0; i < this.size; i++) {
			currentStudent.value.printStudent();
			currentStudent = currentStudent.node;
		}
	}

	/**
	 * Print all men from the stack
	 */
	public void printMen() {
		StudentData currentStudent = studentData;
		
		for (int i = 0; i < this.size; i++) {
			if(currentStudent.value.getGender().equals("m")) {
				currentStudent.value.printStudent();
			}
			currentStudent = currentStudent.node;
		}
	}

	/**
	 * Print all women from the stack
	 */
	public void printWomen() {
		StudentData currentStudent = studentData;
		
		for (int i = 0; i < this.size; i++) {
			if(currentStudent.value.getGender().equals("f")) {
				currentStudent.value.printStudent();
			}
			currentStudent = currentStudent.node;
		}
	}

	/**
	 * Contains a Student value and a node pointing to the next data object.
	 */
	private class StudentData {
		private Student value;
		private StudentData node;
	}
}
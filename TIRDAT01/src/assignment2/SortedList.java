package assignment2;

/**
 * SortedList class
 * Students are sorted with their student number
 */
public class SortedList {

	private Node sortedListNode;
	private int size;

	public SortedList() {
		sortedListNode = new Node();
	}

	/**
	 * Pushes a student on the SortedList
	 * @param s
	 * @return boolean
	 */
	public boolean push(Student s) {
		// List is empty
		if (sortedListNode.value == null) {
			sortedListNode.value = s;
			sortedListNode.next = new Node();
			size++;

			return true;
		}
			
		// Inserted student must be inserted before first student in list
		if (s.getStudentNumber() < sortedListNode.value.getStudentNumber()) {
				Node currentStudent = new Node(s, sortedListNode);
				sortedListNode = currentStudent;
				size++;

			return true;
		}
				
		Node previousStudent = sortedListNode;
		Node currentStudent = sortedListNode.next;

		for(int i = 0; i < this.size; i++) {
			if(previousStudent.value.getStudentNumber() == s.getStudentNumber()) {
				// cannot insert because student has the same index as student in list
				return false;
			}
			
			if(currentStudent.next != null && s.getStudentNumber() < currentStudent.value.getStudentNumber()) {
				// Student will be inserted between two students ( [student] -> [new student] -> [student] )
				Node tempStudent = new Node(s, currentStudent);
				previousStudent.next = tempStudent;
				size++;

				return true;
			}
				currentStudent = currentStudent.next;
				previousStudent = previousStudent.next;
		}
	
		// Add Student to the end of the list
		previousStudent.value = s;
		previousStudent.next = new Node();
		size++;
		return true;
	}
	
	/**
	 * Get the size of the list
	 * @return int
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Get the first Student from the SortedList and return that Student
	 * @return Student
	 */
	public Student head() {
		if(size > 0) {
			Student firstStudent = sortedListNode.value;
			sortedListNode = sortedListNode.next;
			size--;
			
			return firstStudent;
		}
		return null;
	}

	/**
	 * Get the last Student from the SortedList and return that Student
	 * @return Student
	 */
	public Student tail() {
		if(size > 0) {
			Node currentStudent = sortedListNode;
			
			for(int i = 0; i < this.size; i++) {
				if(currentStudent.next != null) {
					currentStudent = currentStudent.next;
				}
			}
			
			Student tempStudent = currentStudent.value;
			currentStudent.value = null;
			size--;
			
			return tempStudent;
		}
		
		return null;
	}

	/**
	 * Delete Student s from the SortedList
	 * Returns the deleted Student
	 * @param s
	 * @return Student
	 */
	public Student pop(Student s) {
		if(size > 0) {
			Node currentStudent = sortedListNode.next;
			Node previousStudent = sortedListNode;

			if (previousStudent.value.equals(s)) {
				Student tempStudent = sortedListNode.value;
				sortedListNode = sortedListNode.next;
				size--;
				return tempStudent;
			}

			for (int i = 0; i < this.size; i++) {
				if (currentStudent.value != null && currentStudent.value.equals(s)) {
					Student st = currentStudent.value;
					previousStudent.next = currentStudent.next;
					
					size--;
					return st;
				}

				currentStudent = currentStudent.next;
				previousStudent = previousStudent.next;
			}
		}
		return null;
	}

	/**
	 * Deletes student at a specific index from the SortedList
	 * @param index
	 * @return Student
	 */
	public Student pop(int index) {
		if(size > 0) {
			Node currentStudent = sortedListNode.next;
			Node previousStudent = sortedListNode;

			if (previousStudent.value.getStudentNumber() == index) {
				Student tempStudent = sortedListNode.value;
				sortedListNode = sortedListNode.next;
				size--;
				return tempStudent;
			}

			for (int i = 0; i < this.size; i++) {
				if (currentStudent.value != null && currentStudent.value.getStudentNumber() == index) {
					Student st = currentStudent.value;
					previousStudent.next = currentStudent.next;
					
					size--;
					return st;
				}

				currentStudent = currentStudent.next;
				previousStudent = previousStudent.next;
			}
		}
		return null;
	}

	/**
	 * Return true if student is in the SortedList
	 * @param s
	 * @return boolean
	 */
	public boolean peek(Student s) {
		Node currentStudent = sortedListNode;

		for (int i = 0; i < this.size; i++) {
			if (currentStudent.value.getStudentNumber() == s.getStudentNumber()) {
				return true;
			} 
			currentStudent = currentStudent.next;	
		}

		return false;
	}

	/**
	 * Print the students in the SortedList
	 */
	public void printLinkedList() {
		Node currentStudent = sortedListNode;

		for(int i = 0; i < this.size; i++) {
			currentStudent.value.printStudent();
			currentStudent = currentStudent.next;
		}
	}

	/**
	 * Print all men that are in the SortedList
	 */
	public void printMen() {
		Node currentStudent = sortedListNode;

		for(int i = 0; i < this.size; i++) {
			if (currentStudent.value.getGender().equals("m")) {
				currentStudent.value.printStudent();
			}
			currentStudent = currentStudent.next;
		}
	}

	/**
	 * Print all women that are in the SortedList
	 */
	public void printWomen() {
		Node currentStudent = sortedListNode;

		for(int i = 0; i < this.size; i++) {
			if (currentStudent.value.getGender().equals("f")) {
				currentStudent.value.printStudent();
			}
			currentStudent = currentStudent.next;
		}
	}

	/**
	 * Node class that contains a student a next Node
	 */
	private class Node {
		
		public Node() { }
		
		public Node(Student value, Node next) { 
			this.value = value;
			this.next = next;
		}
		
		private Student value;
		private Node next;
	}
}
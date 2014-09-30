package assignment2;

public class List {
	private Node listNode;
	private int size;

	public List() {
		listNode = new Node(null, 0, null);
	}

	/**
	 * Pushes a student in the list at a specific index.
	 * Return true if succeeded
	 * @param s
	 * @param index
	 * @return boolean
	 */
	public boolean push(Student s, int index) {
		// Insert student before first element in list
		if (index < listNode.index) {
			Node currentStudent = new Node(s, index, listNode);
			listNode = currentStudent;
			size++;
			return true;
		}
		
		Node previousStudent = listNode;
		Node currentStudent = listNode.next;
		
		for(int i = 0; i < this.size; i++) {
			if(previousStudent.index == index || currentStudent.index == index) {
				// cannot insert because student has the same index as student in list
				return false;
			}
			
			// insert student between two elements
			if (currentStudent.index > index && previousStudent.index != index) {
				Node studentTemp = new Node(s, index, currentStudent);
				previousStudent.next = studentTemp;
				size++;
				return true;
			}
			currentStudent = currentStudent.next;
			previousStudent = previousStudent.next;
		}	

		// insert student at the end of the list
		if (previousStudent.value == null) {
			previousStudent.value = s;
			previousStudent.index = index;
			previousStudent.next = new Node(null, 0, null);
			size++;
			return true;
		}
		
		return false;
	}

	/**
	 * Get the size of the list
	 * @return int
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Get the first Student from the List and return that Student
	 * @return Student
	 */
	public Student head() {
		if(size > 0) {
			Student s = listNode.value;
			listNode = listNode.next;
			size--;
			
			return s;
		}
		return null;
	}

	/**
	 * Get the last Student from the List and return that Student
	 * @return
	 */
	public Student tail() {
		if(size <= 0) {
			return null;
		}

		Node currentStudent = listNode;
		
		for(int i = 0; i < this.size; i++) {
			if(currentStudent.next.value != null) {
				currentStudent = currentStudent.next;
			}
		}
		
		Student s = currentStudent.value;
		currentStudent.value = null;
		size--;
		
		return s;
	}

	/**
	 * Deletes a student at a specific index
	 * @param index
	 * @return Student
	 */
	public Student pop(int index) {
		// Pop first student from the list
		if(listNode.index == index) {
			Student s = listNode.value;
			listNode = listNode.next;
			size--;
			return s;
		}
		
		Node currentStudent = listNode.next;
		Node previousStudent = listNode;
		
		for(int i = 0; i < this.size; i++) {
			if (currentStudent.index == index) {
				Student st = currentStudent.value;
				previousStudent.next = currentStudent.next;
				size--;
				return st;
			}
			currentStudent = currentStudent.next;
			previousStudent = previousStudent.next;
		}
		
		return null;
	}

	/**
	 * Return true if student is in the list
	 * @param s
	 * @return boolean
	 */
	public boolean peek(Student s) {
		Node currentStudent = listNode;
		
		for(int i = 0; i < this.size; i++) {
			if(currentStudent.value.getStudentNumber() == s.getStudentNumber()) {
				return true;
			}
			currentStudent = currentStudent.next;
		}
		
		return false;
	}

	/**
	 * Print the students in the list
	 */
	public void printList() {
		Node currentStudent = listNode;

		for(int i = 0; i < this.size; i++) {
			currentStudent.value.printStudent();
			currentStudent = currentStudent.next;
		}
	}

	/**
	 * Print all men that are in the list
	 */
	public void printMen() {
		Node currentStudent = listNode;

		for(int i = 0; i < this.size; i++) {
			if (currentStudent.value.getGender().equals("m")) {
				currentStudent.value.printStudent();
			}
			currentStudent = currentStudent.next;
		}
	}

	/**
	 * Print all women that are in the list
	 */
	public void printWomen() {
		Node currentStudent = listNode;

		for(int i = 0; i < this.size; i++) {
			if (currentStudent.value.getGender().equals("f")) {
				currentStudent.value.printStudent();
			}
			currentStudent = currentStudent.next;
		}
	}

	/**
	 * Node class that contains a student a next and the index
	 */
	private class Node {
		
		public Node(Student value, int index, Node next) { 
			this.value = value;
			this.index = index;
			this.next = next;
		}
		
		private Student value;
		private Node next;
		private int index;
	}
}
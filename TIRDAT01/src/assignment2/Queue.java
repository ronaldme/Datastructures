package assignment2;

public class Queue {

	private int size;
	private Node queueNode;

	public Queue() {
		queueNode = new Node();
	}
	
	/**
	 * Pushes a student in the Queue
	 * @param s
	 * @return boolean
	 */
	public boolean push(Student s) {
		Node currentNode = queueNode;
		
		for(int i = 0; i < this.size; i++) {
			currentNode = currentNode.next;
		}
		currentNode.value = s;
		currentNode.next = new Node();
		size++;
		
		return true;
	}
	
	/**
	 * Get the size of the Queue
	 * @return int
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Pop the first  student from the Queue
	 * @return student
	 */
	public Student pop() {
		if (size > 0) {
			Student s = queueNode.value;
			queueNode = queueNode.next;
			size--;

			return s;
		} 
		
		return null;
	}

	/**
	 * Return true if student is in the Queue
	 * @param s
	 * @return boolean
	 */
	public boolean peek(Student s) {
		Node currentNode = queueNode;

		for(int i = 0; i < this.size; i++) {
			if (currentNode.value.getStudentNumber() == s.getStudentNumber()) {
				return true;
			}
			currentNode = currentNode.next;
		}

		return false;
	}

	/**
	 * Print the Queue
	 */
	public void printQueue() {
		Node currentNode = queueNode;

		for(int i = 0; i < this.size; i++) {
			currentNode.value.printStudent();
			currentNode = currentNode.next;
		}
	}

	/**
	 * Print all men that are in the Queue
	 */
	public void printMen() {
		Node currentNode = queueNode;

		for(int i = 0; i < this.size; i++) {
			if (currentNode.value.getGender().equals("m")) {
				currentNode.value.printStudent();
			}
			currentNode = currentNode.next;
		}
	}

	/**
	 * Print all women that are in the Queue
	 */
	public void printWomen() {
		Node currentNode = queueNode;

		for(int i = 0; i < this.size; i++) {
			if (currentNode.value.getGender().equals("f")) {
				currentNode.value.printStudent();
			}
			currentNode = currentNode.next;
		}
	}

	/**
	 * Node class that contains a Student and a next
	 */
	private class Node {
		private Student value;
		private Node next;
	}
}
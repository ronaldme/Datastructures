package assignment2;

public class App {
	private static Student s1, s2, s3, s4, s5, s6, s7, s8;

	public static void main(String[] args) {
		createStudents();
		
		ExecuteList();
		ExecuteQueue();
		ExecuteStack();
	}

	private static void ExecuteStack() {
		SortedList sortedList = new SortedList();
		sortedList.push(s4);
		sortedList.push(s7);
		sortedList.push(s5);
		sortedList.tail();
		sortedList.pop(9000);
		sortedList.push(s7);
		sortedList.push(s1);
		sortedList.push(s7);
		System.out.println("-------------");
		sortedList.printLinkedList();
		System.out.println(sortedList.peek(s4));
		System.out.println("Size: " + sortedList.size());
	}

	private static void ExecuteQueue() {
		Queue queue = new Queue();
		queue.push(s1);
		queue.push(s6);
		queue.pop();
		queue.pop();
		queue.pop();
		queue.push(s3);
		queue.push(s2);
		queue.push(s5);
		System.out.println("Queue size: " + queue.size());
		System.out.println();
		queue.printQueue();
	}

	private static void ExecuteList() {
		List list = new List();
		list.push(s4, 4);
		list.push(s5, 1);
		list.push(s1, 16);
		list.push(s7, 5);
		list.tail();
		list.tail();
		list.tail();
		list.head();
		list.head();
		list.push(s3, 111);
		list.push(s4, 77);
		list.push(s1, 56);
		list.push(s2, 44);
		list.printList();
	}
	
	private static void createStudents() {
		s1 = new Student(1, "Tess", 23, "m");
		s2 = new Student(2, "Lin", 22, "m");
		s3 = new Student(3, "Coalindo", 20, "f");
		s4 = new Student(4, "Davinci", 24, "f");
		s5 = new Student(5, "Cruise", 19, "m");

		s6 = new Student();
		s6.setName("Faraday");
		s6.setGender("m");
		s6.setAge(118);
		s6.setStudentNumber(10);

		s7 = new Student();
		s7.setName("Coni");
		s7.setGender("m");
		s7.setAge(29);
		s7.setStudentNumber(11);

		s8 = new Student();
		s8.setName("Harold");
		s8.setGender("m");
		s8.setAge(25);
		s8.setStudentNumber(12);
	}
}

package assignment1;

public class App {
	private static Student s1, s2, s3, s4, s5, s6, s7, s8;

	public static void main(String[] args) {
		createStudents();
		
		Stack stack = new Stack();
		stack.push(s1);
		stack.push(s2);
		stack.pop();
		stack.push(s3);
		
		stack.printMen();
		System.out.println("Stacksize: " + stack.size());
		System.out.println("--------------------");

		stack.push(s3);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.push(s5);
		
		stack.printStack();
		System.out.println("Stacksize: " + stack.size());
	}

	private static void createStudents() {
		s1 = new Student();
		s1.setName("Ronald");
		s1.setGender("m");
		s1.setAge(23);
		s1.setStudentNumber(012345);

		s2 = new Student();
		s2.setName("Karel");
		s2.setGender("m");
		s2.setAge(22);
		s2.setStudentNumber(123456);

		s3 = new Student();
		s3.setName("Katinka");
		s3.setGender("f");
		s3.setAge(20);
		s3.setStudentNumber(234567);

		s4 = new Student();
		s4.setName("Lin");
		s4.setGender("f");
		s4.setAge(24);
		s4.setStudentNumber(345678);

		s5 = new Student();
		s5.setName("Leo");
		s5.setGender("m");
		s5.setAge(19);
		s5.setStudentNumber(456789);

		s6 = new Student();
		s6.setName("Faraday");
		s6.setGender("m");
		s6.setAge(177);
		s6.setStudentNumber(5678901);

		s7 = new Student();
		s7.setName("Newton");
		s7.setGender("m");
		s7.setAge(29);
		s7.setStudentNumber(6789012);

		s8 = new Student();
		s8.setName("Einstein");
		s8.setGender("m");
		s8.setAge(25);
		s8.setStudentNumber(7890123);
	}
}

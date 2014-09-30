package assignment3;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTf, studentNrTf, classTf, studyDirTf, courseCodeTf, yearTf, courseNameTf;
	private JRadioButton rBMale, rBFemale;
	private JTextArea textArea;
	private JComboBox<String> cBStudentDelete, cbStudentAdd, cbCourses, cbStudent, cBStudyDirection, cBClasses, cbCoursesAdd, cbGrade;
	private JButton btnAddCourse, btnStudInClass, btnCompleted, btnShowCompleted, btnAverage, btnAddAuto, btnRemoveStud, btnAdd,
					btnNotCompleted, btnStudy, bInfo, btnCreateCourse;
	
	// Keeping track of the Courses so we don't add courses we already created
	private ArrayList<Course> uniqueCourses = new ArrayList<Course>();
	private ArrayList<String> uniqueClasses = new ArrayList<String>();

	private ArrayList<String> studyDirs = new ArrayList<String>();
	private ArrayList<String> schoolClasses = new ArrayList<String>();
	
	private Administration adm = new Administration();
	private InputHelper inputHelper = new InputHelper();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Gui().setVisible(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create a student
	 */
	private void createStudent(String gender) 
	{
		btnAddAuto.setEnabled(false);
		Student s = new Student(studentNrTf.getText(), nameTf.getText(), gender, classTf.getText(), studyDirTf.getText());
		
		if(!adm.getAllStudents().contains(s)) {
			adm.addStudent(s.getStudentNumber(), s);
			cBStudentDelete.addItem(s.getStudentNumber());
			cbStudentAdd.addItem(s.getStudentNumber());
			cbStudent.addItem(s.getStudentNumber());
			if(!studyDirs.contains(s.getStudyDirection())) {
				cBStudyDirection.addItem(s.getStudyDirection());
				studyDirs.add(s.getStudyDirection());
			}
			if(!uniqueClasses.contains(s.getSchoolClass())) {
				cBClasses.addItem(s.getSchoolClass());
			}
		} else  {
			errorDialog("Deze student bestaat al", "Toevoegen student");
		}
	}
	
	/**
	 * Create a new course
	 * @param selectedItem
	 */
	private void createCourse() {
		if(inputHelper.CorrectInputCourse(courseNameTf, courseCodeTf, yearTf)) {
			btnAddAuto.setEnabled(false);
			Course c = new Course(courseNameTf.getText(), courseCodeTf.getText(), Integer.parseInt(yearTf.getText()), Integer.parseInt(cbGrade.getSelectedItem().toString()));
			if(!uniqueCourses.contains(c)) {
				cbCoursesAdd.addItem(c.getCourseCode() + " jaar: " + c.getYear() + " cijfer: " + c.getGrade());
				if(!uniqueClasses.contains(c.getCourseName())) {
					cbCourses.addItem(c.getCourseName());
				}
				uniqueCourses.add(c);
			}
			else {
				errorDialog("Bevat dit vak met dezelfde data al!", "Toevoegen vak");
			} 
			
		} else {
			errorDialog("Voeg geldige waardes in. Code (3 cijfers) Jaar (0-10) Naam (Max 10 tekens)", "Toevoegen vak");
		} 
	}
	
	private void addCourseToStudent() {
		if(cbStudentAdd.getSelectedItem() != null && cbCoursesAdd.getSelectedItem() != null) {
			Student s = adm.getStudentByNumber(cbStudentAdd.getSelectedItem().toString());
			String course = cbCoursesAdd.getSelectedItem().toString();
			
			String subStrCourseCode = course.substring(0, course.indexOf(" "));
			int year = Integer.parseInt(course.substring(10, course.indexOf(" cijfer: ")));
			int grade = Integer.parseInt(course.substring(20));

			// iterate over all courses and find the one selected
			for(Course c : uniqueCourses) {
				if(c.getCourseCode().equals(subStrCourseCode) && c.getYear() == year && c.getGrade() == grade) {
					if(!s.getCourses().contains(c)) {
						s.addCourse(c);
					}
				}
			}
		}
	}

	/**
	 * Delete a student
	 */
	private void deleteStudent() {
		if(cBStudentDelete.getSelectedItem() != null) {
			String s = (String)cBStudentDelete.getSelectedItem();
			Student stud = adm.getStudentByNumber((String)cBStudentDelete.getSelectedItem());
			cBStudentDelete.removeItem(s);
			cbStudentAdd.removeItem(s);
			cbStudent.removeItem(s);
			adm.removeStudent(s);
			
			if(adm.studyDirectionStudent(stud.getStudyDirection()).size() <= 0) {
				cBStudyDirection.removeItem(stud.getStudyDirection());
			}
			if(adm.studentsInClass(stud.getSchoolClass()).size() <= 0) {
				cBClasses.removeItem(stud.getSchoolClass());
			}	
		}		
	}
	
	/**
	 * Show a error dialog with a custom message / title
	 * @param message
	 * @param title
	 */
	private void errorDialog(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Auto adding some students with courses
	 */
	private void createStudentsAutomatic() {
		btnAddAuto.setEnabled(false);
		TestData a = new TestData();
		uniqueCourses = a.AddToAdministration(adm);
		
		LinkedList<Student> list = adm.getAllStudents();
		//HashMap<String, Course> hashMap = new HashMap<String, Course>();
		ArrayList<String> courseList = new ArrayList<String>();
		
		for(Student s : list) {
			cbStudentAdd.addItem(s.getStudentNumber());
			cBStudentDelete.addItem(s.getStudentNumber());
			cbStudent.addItem(s.getStudentNumber());
			
			String studyDir = s.getStudyDirection();
			if(!studyDirs.contains(studyDir)) {
				cBStudyDirection.addItem(studyDir);
				studyDirs.add(studyDir);
			}
			
			String schoolClass = s.getSchoolClass();
			if(!schoolClasses.contains(schoolClass)) {
				cBClasses.addItem(schoolClass);
				schoolClasses.add(schoolClass);
			}
			for(Course c : s.getCourses()) {
				String courseName = c.getCourseCode() + " jaar: " + c.getYear() + " cijfer: " + c.getGrade();
				if(!courseList.contains(courseName)) {
					courseList.add(courseName);
				}
				if(!uniqueClasses.contains(c.getCourseName())) {
					uniqueClasses.add(c.getCourseName());
				}
			}
		}
		Collections.sort(courseList.subList(0, courseList.size()));
		
		// Adding all unique courses to the courses combobox
		for(String s : courseList) {
			cbCoursesAdd.addItem(s);
		}
		
		Collections.sort(uniqueClasses.subList(0, uniqueClasses.size()));
		for(String s : uniqueClasses) {
			cbCourses.addItem(s);			
		}
	}
	
	/**
	 * Create the frame and adding all the elements
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 786);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("TIRDAT01 - Ronald Meijboom");
			
		// Labels
		JLabel lblName = new JLabel("Naam:");
		lblName.setBounds(10, 14, 57, 14);
		contentPane.add(lblName);
		
		JLabel lblStudentNumber = new JLabel("Student nummer:");
		lblStudentNumber.setBounds(10, 39, 109, 14);
		contentPane.add(lblStudentNumber);
		
		JLabel lblNewLabel = new JLabel("Geslacht");
		lblNewLabel.setBounds(10, 64, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Klas:");
		lblNewLabel_1.setBounds(10, 89, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblStudierichting = new JLabel("Studierichting:");
		lblStudierichting.setBounds(10, 114, 100, 14);
		contentPane.add(lblStudierichting);
		
		JLabel lblStudent = new JLabel("Student:");
		lblStudent.setBounds(17, 224, 62, 14);
		contentPane.add(lblStudent);
		
		JLabel lblVak = new JLabel("Vak code:");
		lblVak.setBounds(277, 41, 69, 14);
		contentPane.add(lblVak);
		
		JLabel lblJaar = new JLabel("Jaar: ");
		lblJaar.setBounds(277, 66, 46, 14);
		contentPane.add(lblJaar);
		
		JLabel lblCijfer = new JLabel("Cijfer:");
		lblCijfer.setBounds(277, 97, 46, 14);
		contentPane.add(lblCijfer);
		
		JLabel lblName_1 = new JLabel("Naam:");
		lblName_1.setBounds(277, 14, 57, 14);
		contentPane.add(lblName_1);
		
		JLabel lblAutoStudenten = new JLabel("Automatisch studenten / vakken toevoegen");
		lblAutoStudenten.setBounds(277, 319, 245, 20);
		contentPane.add(lblAutoStudenten);
		
		JLabel lblVak_1 = new JLabel("Vak: ");
		lblVak_1.setBounds(18, 256, 57, 14);
		contentPane.add(lblVak_1);
		
		// Text fields
		nameTf = new JTextField();
		nameTf.setBounds(120, 11, 119, 20);
		contentPane.add(nameTf);
		nameTf.setColumns(10);
		
		studentNrTf = new JTextField();
		studentNrTf.setBounds(120, 36, 119, 20);
		contentPane.add(studentNrTf);
		studentNrTf.setColumns(10);
		
		classTf = new JTextField();
		classTf.setColumns(10);
		classTf.setBounds(120, 86, 119, 20);
		contentPane.add(classTf);
		
		studyDirTf = new JTextField();
		studyDirTf.setColumns(10);
		studyDirTf.setBounds(120, 111, 119, 20);
		contentPane.add(studyDirTf);
		
		courseCodeTf = new JTextField();
		courseCodeTf.setBounds(358, 38, 148, 20);
		contentPane.add(courseCodeTf);
		courseCodeTf.setColumns(10);
		
		yearTf = new JTextField();
		yearTf.setColumns(10);
		yearTf.setBounds(358, 63, 148, 20);
		contentPane.add(yearTf);
		
		courseNameTf = new JTextField();
		courseNameTf.setColumns(10);
		courseNameTf.setBounds(358, 11, 148, 20);
		contentPane.add(courseNameTf);

		// JComboBoxes
		cbStudentAdd = new JComboBox<String>();
		cbStudentAdd.setBounds(89, 221, 178, 20);
		contentPane.add(cbStudentAdd);
		
		cbCourses = new JComboBox<String>();
		cbCourses.setBounds(17, 397, 140, 20);
		contentPane.add(cbCourses);
		
		cBStudentDelete = new JComboBox<String>();
		cBStudentDelete.setBounds(10, 319, 150, 20);
		contentPane.add(cBStudentDelete);
		
		cBStudyDirection = new JComboBox<String>();
		cBStudyDirection.setBounds(382, 397, 140, 20);
		contentPane.add(cBStudyDirection);
		
		cBClasses = new JComboBox<String>();
		cBClasses.setBounds(382, 462, 140, 20);
		contentPane.add(cBClasses);
		
		cbStudent = new JComboBox<String>();
		cbStudent.setBounds(198, 397, 148, 20);
		contentPane.add(cbStudent);
		
		cbCoursesAdd = new JComboBox<String>();
		cbCoursesAdd.setBounds(89, 252, 178, 20);
		contentPane.add(cbCoursesAdd);

		// Seperators
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(259, 0, 8, 185);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 306, 534, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 184, 534, 2);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(259, 306, 13, 80);
		contentPane.add(separator_4);

		// Scrollpane and Text area
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 554, 514, 209);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setWrapStyleWord(true);
		
		
		// Buttons
		btnAdd = new JButton("Maak student");
		btnAdd.setBounds(10, 139, 133, 23);
		contentPane.add(btnAdd);
		
		btnShowCompleted = new JButton("Behaald door");
		btnShowCompleted.setBounds(19, 462, 138, 23);
		contentPane.add(btnShowCompleted);

		btnRemoveStud = new JButton("Verwijder student");
		btnRemoveStud.setBounds(10, 350, 150, 23);
		contentPane.add(btnRemoveStud);
		
		btnAddCourse = new JButton("Voeg vak toe aan student");
		btnAddCourse.setBounds(325, 221, 181, 23);
		contentPane.add(btnAddCourse);
		
		btnAddAuto = new JButton("Automatisch toevoegen");
		btnAddAuto.setBounds(325, 350, 197, 23);
		contentPane.add(btnAddAuto);
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 384, 534, 2);
		contentPane.add(separator);
		
		btnAverage = new JButton("Gemiddelde");
		btnAverage.setBounds(17, 428, 140, 23);
		contentPane.add(btnAverage);
		
		btnCompleted = new JButton("Behaald");
		btnCompleted.setBounds(198, 428, 154, 23);
		contentPane.add(btnCompleted);
		
		btnStudy = new JButton("Studierichting");
		btnStudy.setBounds(382, 428, 140, 23);
		contentPane.add(btnStudy);
		
		btnStudInClass = new JButton("Studenten");
		btnStudInClass.setBounds(382, 493, 140, 23);
		contentPane.add(btnStudInClass);
		
		btnNotCompleted = new JButton("Niet behaald");
		btnNotCompleted.setBounds(198, 462, 154, 23);
		contentPane.add(btnNotCompleted);
		
		bInfo = new JButton("Toon info");
		bInfo.setBounds(198, 496, 154, 23);
		contentPane.add(bInfo);
		
		btnCreateCourse = new JButton("Maak vak");
		btnCreateCourse.setBounds(270, 139, 109, 23);
		contentPane.add(btnCreateCourse);
		
		// Radio buttons
		rBMale = new JRadioButton("m");
		rBMale.setSelected(true);
		rBMale.setBounds(120, 60, 46, 23);
		contentPane.add(rBMale);
		
		rBFemale = new JRadioButton("v");
		rBFemale.setBounds(163, 60, 46, 23);
		contentPane.add(rBFemale);
		
		cbGrade = new JComboBox<String>();
		cbGrade.setBounds(358, 94, 148, 20);
		contentPane.add(cbGrade);
		
		for(int i = -1; i <= 10; i++) {
			cbGrade.addItem(Integer.toString(i));
		}
		
		Buttons();
	}

	/**
	 * Create the button listeners
	 */
	public void Buttons() {
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourseToStudent();
			}
		});
				
		btnStudInClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cBClasses.getSelectedItem() != null) {
					ArrayList<String> list = adm.studentsInClass(cBClasses.getSelectedItem().toString());
					
					
					if(list.size() > 0) {
						textArea.append("In de klas: " + cBClasses.getSelectedItem().toString() + " zitten:\n");
						for(String s : list) {
							textArea.append(s + "\n");
						}
					
						textArea.append("\n");
					}
				}
			}
		});
		
		btnStudy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cBStudyDirection.getSelectedItem() != null) {
					ArrayList<String> list = adm.studyDirectionStudent(cBStudyDirection.getSelectedItem().toString());
					
					if(list.size() > 0) {
						textArea.append(cBStudyDirection.getSelectedItem().toString() + " wordt door de volgende studenten gevolgd: \n");
						for(String s : list) {
							textArea.append(s + "\n");
						}
						textArea.append("\n");
					}
				}
			}
		});
		
		bInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbStudent.getSelectedItem() != null) {
					Student s = adm.getStudentByNumber(cbStudent.getSelectedItem().toString());
				
					textArea.append("Naam: " + s.getName() + "\n");
					textArea.append("Klas: " + s.getSchoolClass() + "\n");
					textArea.append("Studentnummer: " + s.getStudentNumber() + "\n");
					textArea.append("Studierichting: " + s.getStudyDirection() + "\n");
					textArea.append("Geslacht: " + s.getGender() + "\n\n");
					
					if(s.getCourses().size() > 0) {
						textArea.append("Heeft de volgende vakken:  " + "\n");
						for(Course c : s.getCourses())
							textArea.append(c.getCourseName() + " met cijfer: " + c.getGrade() + "\n");
						
						textArea.append("\n");
					}
				}
			}
		});
		
		btnNotCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbStudent.getSelectedItem() != null) {
					
					Student s = adm.getStudentByNumber(cbStudent.getSelectedItem().toString());
					ArrayList<Course> notCompleted = adm.incompletedCourses(s);
					
					if(notCompleted.size() > 0) {					
						textArea.append(s.getName() + " niet behaalde vakken:\n");
						
						for(Course course : notCompleted) {
							textArea.append(course.getCourseName() + "\n");
						}
						textArea.append("\n");
					} else {
						if(s.getCourses().size() > 0) 
							textArea.append("Alles behaald! \n\n");
					}
				}
			}
		});
		
		btnCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbStudent.getSelectedItem() != null) {
					
					Student s = adm.getStudentByNumber(cbStudent.getSelectedItem().toString());
					ArrayList<Course> completedCoursesStudent = adm.completedCourses(s);
					
					if(completedCoursesStudent.size() > 0) {
						textArea.append(s.getName() + " behaalde vakken:\n");
						for(Course course : completedCoursesStudent) {
							textArea.append(course.getCourseName() + "\n");
						}
							textArea.append("\n");
					}
				}
			}
		});
		
		btnShowCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbCourses.getSelectedItem() != null) {
					String courseCode = null;
					
					for(Course c : uniqueCourses) {
						if(c.getCourseName().equals(cbCourses.getSelectedItem().toString())) {
							courseCode = c.getCourseCode();
							break;
						}
					}
					
					ArrayList<String> students = adm.printStudendsCourseCompleted(courseCode);
				
					for(String s : students) {
						textArea.append("Student: " + s + " completed this course with code: " + courseCode + "!" + "\n");
					}
				
					if(students.size() > 0) {
						textArea.append("\n");
					}
				}
			}
		});
		
		btnAverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbCourses.getSelectedItem() != null) {
					if(adm.getAllStudents().size() > 0) {
						String courseCode = null;
						
						for(Course c : uniqueCourses) {
							if(c.getCourseName().equals(cbCourses.getSelectedItem().toString())) {
								courseCode = c.getCourseCode();
								break;
							}
						}
						
						double average = adm.averageGrade(courseCode);
						textArea.append("Average grade: " + String.format("%1$,.2f", average) + "\n\n");
					}	
				}	
			}
		});
		
		btnAddAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createStudentsAutomatic();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inputHelper.CorrectInputStudent(nameTf, studentNrTf, studyDirTf, rBMale, rBFemale )) {	
					String gender = null;
					if(rBMale.isSelected()) { 
						gender = "m";
					} else {
						gender = "f";
					}
					createStudent(gender);
				} else {
					errorDialog("Voeg geldige waardes in", "Toevoegen student");
				}
			} 
		});
		
		btnRemoveStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStudent();
			}
		});
		
		btnCreateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createCourse();
			}
		});
	}
}
package assignment3;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class InputHelper {
	
	/**
	 * Check the user input for adding a student
	 * @return boolean
	 */
	public boolean CorrectInputStudent(JTextField nameStudent, JTextField studentNumber, JTextField studyDir, JRadioButton male, JRadioButton female) {
		
		if(nameStudent.getText().length() > 0 & studentNumber.getText().length() > 0 &&
				(male.isSelected() || female.isSelected()) && studyDir.getText().length() > 0) {
			
			if(male.isSelected() && female.isSelected()) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check the user input for adding a course to the student
	 * @return
	 */
	public boolean CorrectInputCourse(JTextField nameCourse, JTextField courseCode, JTextField year) {
		
		int yr = 0;
		if(year.getText().length() > 0) {
			try {
				yr = Integer.parseInt(year.getText());
			} catch(NumberFormatException e) {
				return false;
			}
		}
		
		if(courseCode.getText().length() == 3 && yr >= 0 && yr <= 10 && nameCourse.getText() != null
				&& nameCourse.getText().length() <= 10) {
			return true;
		}
		
		return false;
	}
}
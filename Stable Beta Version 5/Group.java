package pcage;
/**
 * Represents a Group of students.
 * @author Skylar Chan
 */
public class Group {
	private int clementeCurrent = 0, clementeLimit, femaleCurrent = 0, currentSize = 0, groupNum;
	Student[] students;
	public Group(int size, int cLim, int group) {
		students = new Student[size];
		clementeLimit = cLim;
		groupNum = group;
	}
	/**
	 * Sets the size of this Group object. Also empties this Group.
	 * @param s The number of students that this Group can hold. 
	 */
	public void setSize(int s) {
		students = new Student[s];
	}
	
	/**
	 * Returns true if the specified Student can sit in this specified Group. If there are no seats left in this Group, this Student is a Clemente student and the Clemente limit has been reached, or the Student has sat with any of the other students in this Group, returns false.
	 * @param s The Student who could be seated in this Group
	 * @return
	 */
	public boolean isOpen(Student s) {
		/*return true if:
		 * there are seats available
		 * if student is clemente and the limit has not been reached
		 * if student has sat with others in group 
		*/
		if(currentSize>=students.length) {return false;}//keep this line
		if(s.getSchool().equals("Roberto W. Clemente")&&clementeCurrent>=clementeLimit) {return false;}
		/*for(Student other:students) {
			if(s.hasSatWith(other)) {return false;}
		}*/
		return true;
	}
	
	/**
	 * @Return a string representation of this group. 
	 */
	public String toString() {
		String out="";
		for(Student s:students) {
			out+=s+"\t";
		}
		return out;
	}
	
	/**
	 * Adds the specified Student to this Group, updating the group's current size and Clemente and female count if applicable. Independent from the isOpen method.
	 * @param s
	 */
	public void add(Student s) {
		if(s.getClass().equals("Roberto W. Clemente")) {
			clementeCurrent++;
		}
		if(s.getGender().equals("F")) {
			femaleCurrent++;
		}
		for(int i=0; i<currentSize; i++) {
			Student stew = students[i];
			stew.addExclude(s);
			s.addExclude(stew);
		}
		students[currentSize] = s;
		currentSize++;
	}
	
	/**
	 * Sets this Groups number to the specified integer, and gives a seat to each Student in the group.
	 * @param gn The number to be assigned to this Group.
	 */
	public void setGroupNum(int gn) {
		groupNum = gn;
		for(int i=0; i<students.length; i++) {
			students[i].setLocation(groupNum+Character.toString ((char) (i+65)));//group num plus capital letter
		}
	}
	
	/**
	 * @return The maximum size of this group.
	 */
	public int getMaxSize() {
		return students.length;
	}
}

package pcage;

import java.util.ArrayList;

public class Student implements Comparable<Student>{
	private String session;
	private String first;
	private String last;
	private String gender;
	private String program;
	private String school;
	
	/** Constructs a student from an array list of Strings.
	 * If this data is invalid, the constructor throws an exception to be caught by the client class.
	 */
	Student(ArrayList<String> arr) throws Exception {
		session = arr.get(0);
		String[] parts = arr.get(1).split(", ");
		last = parts[0];
		String[] fir = parts[1].split(" ");
		first = fir[0];
		gender = arr.get(2);
		program = arr.get(3);
		school = arr.get(4);
		if(false) {
		    throw new Exception();
		}
		/*TODO complete constructor
		Throw an exception if the data in arr is invalid
		Valid data:
		Session: first character 1,2,N,n
		Gender: M,F,m,f
		Program: S,G,H,s,g,h
		*/
	}

	public String getSession() {
		return session;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getGender() {
		return gender;
	}

	public String getProgram() {
		return program;
	}

	public String getSchool() {
		return school;
	}
	/**
	 * Compares students by alphabetical order.
	 * @param other The other student to be compared to.
	 */
	public int compareTo(Student other) {
		int out = this.last.compareTo(other.last);
		if(out!=0) {
			return out;
		}
		return this.first.compareTo(other.first);
	}
	
	/**
	 * Returns a String representation of a student.
	 * @return A String in the format session, last, first, gender, program, school 
	 */
	public String toString() {
		return session+", "+last+" "+first+", "+gender+", "+program+", "+school;
	}
	
	public String[] getData() {
		String[] out = {session, first+" "+last,gender,program,school};
		return out;
	}
}

package pcage;

import java.util.ArrayList;

public class Student implements Comparable<Student>{
	private String session;
	private String first;
	private String last;
	private String gender;
	private String program;
	private String school;
	
	Student(ArrayList<String> arr) {
		session = arr.get(0);
		String[] parts = arr.get(1).split(", ");
		last = parts[0];
		String[] fir = parts[1].split(" ");
		first = fir[0];
		gender = arr.get(2);
		program = arr.get(3);
		school = arr.get(4);
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
}

package pcage;
/**
 * An object to represent a student coming into the Summer Math sessions.
 * @author Skylar
 */
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Student {
	 private SimpleStringProperty session, first, last, gender, program, school, seat;//simple string property needed to be compatible with table
	 private ArrayList<Student> exclude = new ArrayList<Student>();

	/** Constructs a student from an array list of Strings.
	 * If this data is invalid, the constructor throws an exception to be caught by the client class.
	 * Valid data:
	 * Gender: M,F,m,f
		Program: S,G,H,s,g,h
	 */
	Student(ArrayList<String> arr) throws Exception {
		
		String sess = arr.get(0);
		if ((sess.charAt(0) != '1') && (sess.charAt(0) != '2')){
			sess = "None";
		} else {
			sess = Character.toString((sess.charAt(0)));
		}
		session = new SimpleStringProperty(sess);
		
		String[] parts = arr.get(1).split(", ");
		last = new SimpleStringProperty(parts[0]);
		String[] fir = parts[1].split(" ");
		first = new SimpleStringProperty(fir[0]);
		
		String gen = arr.get(2);
		gender = new SimpleStringProperty(gen);
		if (Character.toLowerCase(gen.charAt(0)) == 'f') {
			gender.set("F");
		} else if(Character.toLowerCase(gen.charAt(0)) == 'm') {
			gender.set("M");
		} else {
			throw new Exception();
		}
			
		String pro = arr.get(3);
		program = new SimpleStringProperty();
		if (Character.toLowerCase(pro.charAt(0)) == 's') {
			program.set("SMCS");
		} else if (Character.toLowerCase(pro.charAt(0)) == 'h') {
			program.set("Humanities");
		} else if (Character.toLowerCase(pro.charAt(0)) == 'g') {
			program.set("Global");
		} else {
			throw new Exception();
		}
		
		school = new SimpleStringProperty();
		school.set(arr.get(4));
		
		seat = new SimpleStringProperty();
	}
	
	public Student(Student other) {//copies fields except for exclusion
		this.session = new SimpleStringProperty(""+other.session.get());
		this.first = new SimpleStringProperty(""+other.first.get());
		this.last = new SimpleStringProperty(""+other.last.get());
		this.gender = new SimpleStringProperty(""+other.gender.get());
		this.program = new SimpleStringProperty(""+other.program.get());
		this.school = new SimpleStringProperty(""+other.school.get());
		this.seat = new SimpleStringProperty(""+other.seat.get());
		
	}
	
	public boolean equals(Object other) {
		if(other instanceof Student&&other!=null) {
			Student compare = (Student) other;
			if(this.toString().equals(compare.toString())) {
				return true;
			}
		}
		return false;
	}

	public String getSession() {return session.get();}
	public String getFirst() {return first.get();}
	public String getLast() {return last.get();}
	public String getGender() {	return gender.get();}
	public String getProgram() {return program.get();}
	public String getSchool() {return school.get();}
	public void setLocation(String s) {seat.set(s);}
	public void addExclude(Student other) {exclude.add(other);}
	public void clearExclude() {exclude.clear();	}
	public String getLocation() {return seat.get();}
	/**
	 * Returns a String representation of a student.
	 * @return A String in the format session, first last, seat, gender, program, school 
	 */
	public String toString() {return session.get()+","+first.get()+" "+last.get()+","+seat.get()+","+gender.get()+","+program.get()+","+school.get();}
	
	/**
	 * Returns true if the specified Student has previously sat with this Student.
	 * @param other the Student that is to be checked with the Student
	 * @return
	 */
	public boolean hasSatWith(Student other) {
		for(Student s:exclude) {
			if(s.equals(other)) {
				return true;
			}
		}
		return false;
	}
	
}
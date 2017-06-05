package pcage;
/**
 * An object to sort the Students from the input file.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StudentSorter {
	private ArrayList<Student> myStudents = new ArrayList<Student>();//temp list for sorting 
	private ArrayList<Student> students;//reference in program
	public StudentSorter(ArrayList<Student> s) {
		students = s;
	}
	
	public ArrayList<ArrayList<Student>> sort() {
		ArrayList<ArrayList<Student>> out = new ArrayList<ArrayList<Student>>(5);
		for(int i=0;i<5;i++) {
			ArrayList<Student> x = mySort();
			out.add(x);
		}
		for(ArrayList<Student> s:out) {
			System.out.println(s);
		}
		return out;
	}
	
	/**
	 * Sorts this Sorter's Students according to Mr. Gordon's requirements.
	 * @return an ArrayList of Students with their seats. Does not sort the students in order of their group, as that is managed by the StudentTable.
	 */	
	public ArrayList<Student> mySort() {//will need to output an array of arraylists :P
		try {
			myStudents.clear();
			for(Student stew:students) {
				stew.clearExclude();
				stew.setLocation("");
				myStudents.add(new Student(stew));
			}
			myStudents.trimToSize();
			//set clemente limit
			int clemente = 0;
			for(Student s:myStudents) {
				if(s.getSchool().equals("Roberto W. Clemente")) {
					clemente++;
				}
			}
			int clementeLimit;
			if(clemente<myStudents.size()/2) {
				clementeLimit = 2;//default limit
			} else if(clemente>myStudents.size()*3/4) {
				clementeLimit = 6;//i.e, no maximum
			} else {
				clementeLimit = 3;//a higher limit
			}
			//System.out.println("clementeLimit = " + clementeLimit);
			
			//Create appropriate number of Groups, each of a size of four
			ArrayList<Group> groups = new ArrayList<Group>();
			for(int group = 0; group<myStudents.size()/4; group++) {//groups are zero indexed!!
				groups.add(new Group(4,clementeLimit,group));
				//System.out.println("Group "+group);
			}
			int lastWholeGroupIndex = groups.size()-1;
			//deal with leftover students
			int leftover = myStudents.size()%4;
			if(leftover==3) {
				groups.add(new Group(3,clementeLimit,groups.size()+1));
				System.out.println("New group of 3");
				lastWholeGroupIndex++;
			} else {
				//if(leftover==0)
					//System.out.println("wow, no leftovers!");
				for(int i=1; i<=leftover; i++) {
					groups.get(groups.size()-i).setSize(5);
					lastWholeGroupIndex--;
					//System.out.println("Extra group "+i);
				}
			}
			//System.out.println("Group Size "+groups.size());
			//for(Group g:groups) {
				//System.out.println(g.getMaxSize());
			//}

			//split students by gender, shuffle them, and add them back together
			ArrayList<Student> boys = new ArrayList<Student>(), girls = new ArrayList<Student>();
			for(Student s:myStudents) {
				if(s.getGender().equals("boy")) {
					boys.add(s);
				} else {
					girls.add(s);
				}
			}
			Collections.shuffle(boys);
			Collections.shuffle(girls);
			myStudents.clear();
			myStudents.addAll(boys);
			myStudents.addAll(girls);
			
			//sort the students. Currently only works for one day.
			//for(Student s:students) {
				//System.out.println(s);
			//}
			for(Student s:myStudents) {
				//find the next placeable group
				int x = 0;
				while(!groups.get(x).isOpen(s)) {//must refine this so that students get placed correctly!
					//System.out.println("Group "+x+"isn't open for "+s);
					x++;
				}
				//give them an id based on their position
				groups.get(x).add(s);
				//System.out.println(g);
				//drop the student in
			}
			
			//shuffle the groups and reorder them
			shuffleArray(groups, lastWholeGroupIndex);
			int id=1;
			for(Group g:groups) {
				g.setGroupNum(id++);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//sort the students
		ArrayList<Student> out = new ArrayList<Student>();
		for(Student s:myStudents) {
			out.add(new Student(s));
		}
		out.trimToSize();
		return out;
	}
	
	private void shuffleArray(ArrayList<Group> groups, int last) {
		Random rnd = ThreadLocalRandom.current();
	    for (int i = last; i > 0; i--) {
	    	int index = rnd.nextInt(i + 1);
	    	Group g = groups.get(index);
	    	groups.set(index, groups.get(i));
	    	groups.set(i, g);
	    }
	}
	
}

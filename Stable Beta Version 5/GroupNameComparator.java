package pcage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GroupNameComparator implements Comparator<String> {

	public int compare(String s1, String s2) {
		//a crazy long regex expression to split alphabetical and numeric characters
		String[] parts1 = s1.split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");
		String[] parts2 = s2.split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");
		Integer a = Integer.parseInt(parts1[0]);
		Integer b = Integer.parseInt(parts2[0]);
		if(a.equals(b)) {
			return parts1[1].compareTo(parts2[1]);
		}
		return a.compareTo(b);
	}
	public static void main(String args[]) {
		GroupNameComparator gnc = new GroupNameComparator();
		ArrayList<String> q = new ArrayList<String>();
		q.add("1A");
		q.add("9C");
		q.add("9B");
		q.add("10A");
		Collections.sort(q,gnc);
		for(String s:q) {
			System.out.println(s);
		}
	}
}
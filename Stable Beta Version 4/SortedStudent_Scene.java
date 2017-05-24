package pcage;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Represents the scene where sorting, viewing, and saving the sorted student list occurs.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class SortedStudent_Scene extends Scene{
	ArrayList<Student> students;
	TextArea list = new TextArea();
	Button resort, switchView, save;
	/**
	 * @param bp The border pane that this scene is to be attached to
	 * @param arr The reference to the running Program object's student list
	 */
	public SortedStudent_Scene(BorderPane bp, ArrayList<Student> arr) {
		super(bp,551,400);//maybe use images instead and have tooltips
		students = arr;
		resort = new Button("Sort");
		switchView = new Button("Switch View");
		save = new Button("Save as Excel");
		resort.setFont(new Font("Times New Roman",24));
		switchView.setFont(new Font("Times New Roman",24));
		save.setFont(new Font("Times New Roman",24));
		list.setFont(new Font("Times New Roman",24));
		resort.setOnAction(e->ButtonClicked(e));
		switchView.setOnAction(e->ButtonClicked(e));
		save.setOnAction(e->ButtonClicked(e));
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(8);
		hbox.getChildren().addAll(resort,switchView,save);
		hbox.setAlignment(Pos.CENTER);
		bp.setBottom(hbox);
		list.setEditable(false);
		ScrollPane sp = new ScrollPane();
		sp.setContent(list);
		sp.setFitToHeight(true);
		sp.setFitToWidth(true);
		bp.setCenter(sp);
		sort();
		showStudents();
	}
	public void ButtonClicked(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==resort) {
			sort();
		} else if(obj==switchView) {
			//switch the view of the list
		} else if(obj==save) {
			save();
		}
	}
	/**
	 * Sorts the stored list of students by the client's criteria. Currently sorts students randomly.
	 */	
	//TODO implement
	private void sort() {
		Collections.shuffle(students);
		showStudents();
		/* The actual sort method
		 ArrayList<Student> tmp = new ArrayList<Student>();
		 for(Student s:students) {
		 	tmp.add(s);
		 }
		 Student[][] table = new Student[4][8];
		 for(int seat =0; i<Math.min(32,students.size()); seat++) {
		 	int r = seat%4;
		 	int c = seat%8;
		 	int pass = 0;
		 	boolean valid = false;
		 	ArrayList<Student> putBack = new ArrayList<Student>();
		 	
		 	while(table[r][c]==null) {
		 		Student s = tmp.remove(Math.random()*tmp.size());
		 		boolean valid = true;
		 		if(
		 			(r!=0 && s.hasSatNextTo(table[r+1][c]) && s.hasGroupedWith(table[r+1][c])) ||
		 			(c!=0 && s.hasSatNextTo(table[r][c+1]) && s.hasGroupedWith(table[r][c+1]))
		 		) {
		 		valid = false;
		 		}
		 		if(valid||pass>1000) {
		 			s.addSatNextTo(table[r+1][c]);
		 			s.addSatNextTo(table[1][c+1]);
		 			s.addGroupedWith(table[r+1][c]);
		 			s.addGroupedWith(table[r][c+1]);
		 			table[r][c] = s;
		 			while(putBack.size()>0) {
		 				tmp.add(putBack.remove(putBack.size()-1));
		 			}
		 		} else {
		 			if(tmp.size()>0) {
		 				putBack.add(s);
		 			} else {
		 				pass++;
		 				while(putBack.size()>0) {
		 					tmp.add(putBack.remove(putBack.size()-1));
		 				}
		 			}
		 		}
		 	}
		 }
		 	Until all the students that can be added to the seats can be done so:
		 	0. Until this seat is filled,
		 	1. pull a student from tmp
		 	Method: check if the student is valid.
		 	2. if the student is valid, fill the seat. Go to step 4
		 	3. else, put the student in putBack. Go back to step 1, unless tmp is empty.
		 	3.5. If tmp is empty, increase pass by 1. Go to step 4
		 	4. put all students in tmp. Go back to step 0, unless pass is over 1000.
		 	5. If pass is over 1000, pick a random student and fill the seat.
		 	
		 	Method:
		 	1. if the student is not in the first column and they have sat or worked with the person on their left, that student is invalid.
		 	2. if the student is not in the first row and they have sat or worked with the person in front of them, that student is invalid.
		 	3. otherwise, the student is valid
		 		 
		 Student[][] benches = new Student[7][5];
		 int bench = 0;
		 for(bench; bench < tmp.size()%4; bench++) {
		 	int r=bench;
		 	int c=bench % 4;
		 	int pass = 0;
		 	boolean valid = true;
		 	ArrayList<Student> putBack = new ArrayList<Student>();
		 	while(bench[r][c]==null) {
			 	Student s = tmp.remove(Math.random()*tmp.size());
		 		boolean valid = true;
		 		if(r!=0 && s.hasSatNextTo(table[r+1][c]) && s.hasGroupedWith(table[r+1][c])) {
		 			valid = false;
		 		}
		 		if(valid||pass>1000) {
		 			s.addSatNextTo(table[r+1][c]);
		 			s.addGroupedWith(table[r+1][c]);
		 			bench[r][c] = s;
		 			while(putBack.size()>0) {
		 				tmp.add(putBack.remove(putBack.size()-1));
		 			}
		 		} else {
		 			if(tmp.size()>0) {
		 				putBack.add(s);
		 			} else {
		 				pass++;
		 				while(putBack.size()>0) {
		 					tmp.add(putBack.remove(putBack.size()-1));
		 				}
		 			}
		 		}
		 	}
		 	if(tmp.size()>0) {
			 	if(tmp.size()==3)) {
			 		bench++;
			 		int column=0;
			 		while(tmp.size()>0) {
			 			benches[bench][column] = tmp.remove(0);
			 			column++;
			 		}
			 	} else {
			 		//put remaining students with benches

			 		}
			 	}
			 }
		}
		 //put students in a certain format
		 * 
		 */ 
	}
	
	//TODO implement
		private void showStudents() {
			list.clear();
			for(Student s:students) {
				addToList(s+"\n");			
			}
		}
		
	/**
	 * Saves the text in the output field to an Excel file
	 */
	private void save() {
		String saveTo = Program.getFilePath();
		saveTo = saveTo.substring(0, saveTo.lastIndexOf("\\")+1)+"test.xlsx";
		SheetWriter sw;
		try {
			sw = new SheetWriter(saveTo);
			sw.addSheet("Students");
			sw.addRow();
			sw.addCell("Session");
			sw.addCell("Name");
			sw.addCell("Gender");
			sw.addCell("Program");
			sw.addCell("School");
			for(Student s:students) {
				String[] data = s.getData();
				sw.addRow();
				for(String item: data) {
					sw.addCell(item);
				}
			}
			sw.save();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("File Saved");
			alert.setHeaderText(null);
			alert.setContentText("The student list has been saved at "+saveTo+"\n\nThe program will now close.");//get css file for this
			/*DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(
			   getClass().getResource("/application.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");*/
			alert.showAndWait();
			Platform.exit();
		} catch (IOException e) {
			//Pop up: can't save file!
			e.printStackTrace();
		}
	}
	
	private void addToList(String s) {
		list.setText(list.getText()+s);
	}
}

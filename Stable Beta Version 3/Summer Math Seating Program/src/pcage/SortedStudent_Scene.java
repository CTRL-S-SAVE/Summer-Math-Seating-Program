package pcage;
/**
 * Represents the scene where sorting, viewing, and saving the sorted student list occurs.
 */
import java.util.ArrayList;
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
		resort = new Button("Resort");
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
	private void showStudents() {
		list.clear();
		for(Student s:students) {
			addToList(s+"\n");			
		}
	}
	private void sort() {
		Collections.shuffle(students);
		showStudents();
	}
	/**
	 * Saves the text in the output field to an Excel file
	 */
	
	private void save() {
		/*
		ExcelWriter ew = new ExcelWriter(filePath);
		for(int sheet = 0; sheet<5; i++) {
			ew.addSheet();//creates a new sheet and advances to it
			for(int row = 0; row<somenum; row++) {
				ew.nextRow();//creates a new row and advances to it
				for(int column = 0; column<somenum; column++) {
					ew.nextCell();//creates a new cell and advances to it
					ew.setCell(String contents);//sets the content of the cell
				}
			}
		}
		ew.save();//save the file
		*/
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("File Saved");
		alert.setHeaderText(null);
		alert.setContentText("The student list has been saved at "+/*File path+*/"\n\nThe program will now close.");//get css file for this
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(
		   getClass().getResource("/application.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
		Platform.exit();
	}
	
	private void addToList(String s) {
		list.setText(list.getText()+s);
	}
}

package pcage;
/**
 * Represents the scene where sorting, viewing, and saving the sorted student list occurs.
 */
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SortedStudent_Scene extends Scene{
	private ArrayList<Student> students;
	private ArrayList<ArrayList<Student>> output;	
	private Button resort, save, Mon,Tue,Wed,Thu,Fri;
	private String[] days = {"Mon","Tue","Wed","Thu","Fri"};
	private StudentSorter sorter;
	private BorderPane display = new BorderPane();
	private StudentTable table;
	private int day = 0;
	private String session;
	private Stage parent;
	
	/**
	 * @param bp The border pane that this scene is to be attached to
	 * @param arr The reference to the running Program object's student list
	 */
	public SortedStudent_Scene(BorderPane bp, ArrayList<Student> arr) {
		super(bp,800,600);//maybe use images instead and have tooltips
		session = arr.get(0).getSession();
		students = arr;
		resort = new Button("Sort");
		save = new Button("Save to Excel");
		Mon = new Button(days[0]);
		Tue = new Button(days[1]);
		Wed = new Button(days[2]);
		Thu = new Button(days[3]);
		Fri = new Button(days[4]);
		
		Button[] list = {resort, save, Mon,Tue,Wed,Thu,Fri};
		for(Button b:list) {
			b.setFont(new Font("Times New Roman",24));
			b.setOnAction(e->ButtonClicked(e));
		}
		
		Tooltip tooltip = new Tooltip("Click to sort the students again");
		resort.setTooltip(tooltip);
		for(int i=2; i<list.length; i++) {
			Button b = list[i];
			tooltip = new Tooltip("Viewing "+days[day]+"\nClick to view "+b.getText());		
			b.setTooltip(tooltip);
		}
				
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(8);
		hbox.getChildren().addAll(resort,save);
		hbox.setAlignment(Pos.CENTER);
		bp.setBottom(hbox);
		
		HBox hungrybox = new HBox();//Jiggs!
		hungrybox.setPadding(new Insets(10));
		hbox.setSpacing(8);
		hungrybox.getChildren().addAll(Mon,Tue,Wed,Thu,Fri);
		hungrybox.setAlignment(Pos.CENTER);
		display.setTop(hungrybox);
		
		sorter = new StudentSorter(students);
		output = sorter.sort();
		
		table = new StudentTable(output.get(day));
		
		display.setCenter(table);
		
		bp.setCenter(display);		

	}
	public void ButtonClicked(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==resort) {
			try {
				output = sorter.sort();
				table.update(output.get(day));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(obj==save) {
			save();
		} else {
			switchView(((Button)obj).getText());
		}
	}
	
	private void switchView(String button) {
		switch(button) {
		case "Mon":
			day=0;
			break;
		case "Tue":
			day=1;
			break;
		case "Wed":
			day=2;
			break;
		case "Thu":
			day=3;
			break;
		case "Fri":
			day=4;
			break;
		}
		//System.out.println(day);
		table.update(output.get(day));
	}
	
	/**
	 * Saves the text in the output field to an Excel file
	 */
	private void save() {
		String saveTo = Program.getFilePath();
		saveTo = saveTo.substring(0, saveTo.lastIndexOf("\\")+1)+"Summer Math Groups Session "+session+".xlsx";
		SheetWriter sw;
		try {
			sw = new SheetWriter(saveTo);
			for(int i=0;i<5;i++) {
				switchView(days[i]);
				ObservableList<TableColumn> temp = table.getColumns();
				sw.addSheet(days[i]);
				sw.addRow();
				for(TableColumn tc:temp) {
					sw.addCell(tc.getText());
				}
				for(int k=0; k<table.getItems().size(); k++) {
					sw.addRow();
					for(TableColumn col:temp) {
						sw.addCell((String) col.getCellData(k));
					}
				}
			}
			sw.save();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("File Saved");
			alert.setHeaderText(null);
			alert.setContentText("The student list has been saved at "+saveTo+"\n\nThe program will now close.");
			alert.showAndWait();
			Platform.exit();
		} catch (IOException e) {
			//Pop up: can't save file!
			//e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("File Not Saved!");
			alert.setHeaderText(null);
			alert.setContentText("The file could not be saved.\nCheck the file "+saveTo+" and see if it is open or used by another process.");
			alert.showAndWait();
		}
	}

}

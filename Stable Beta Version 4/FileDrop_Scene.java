package pcage;
/**
 * Represents the scene where the user drops in the Excel file to be read.
 * @author Skylar Chan
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class FileDrop_Scene extends Scene {
	Label label = new Label("Please drop in an Excel file");
	/**
	 * Represents the button to be clicked to advance to the next scene.
	 */
	Button click = new Button("Click here to continue");
	/**
	 * Represents the students listed in the Excel file. This list's owner is the running Program object.
	*/
	ArrayList<Student> students = new ArrayList<Student>();
	/**
	 * @param bp The border pane that this scene is to be attached to
	 * @param arr The reference to the running Program object's student list
	 */	
	public FileDrop_Scene(BorderPane bp, ArrayList<Student> arr) {
		super(bp,551,400);
		students = arr;
		BorderPane myPane = new BorderPane();
		label.setFont(new Font("Times New Roman",40));
		label.setAlignment(Pos.CENTER);
		label.setWrapText(true);
		myPane.setCenter(label);
		click.setFont(new Font("Times New Roman",24));
		click.setVisible(false);
		click.setOnAction(e->ButtonClicked(e));
		addEvents();
		bp.setTop(myPane);
		bp.setCenter(click);
	}

	public void ButtonClicked(ActionEvent e) {
		if (e.getSource()==click)
			Program.switchScene(students);
	}	

	private void addEvents() {
		setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        
        // Dropping over surface
        setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    File file = db.getFiles().get(0);
                    filePath = file.getAbsolutePath();
                    if(!isExcel(filePath)) {
                    	popUpError("The file you dropped in is not an Excel file!");
                    } else {
                    	if(scan(filePath)) {
                        	label.setText("File selected:\n"+filePath);
                        	label.setFont(new Font("Times New Roman",24));
                        	click.setVisible(true);
                        	Program.setFilePath(filePath);
                        } else {
                        	click.setVisible(false);
                        	label.setText("Please drop in the Excel file you wish to sort");
                        	label.setFont(new Font("Times New Roman",40));
                        	popUpError("The Excel file you dropped in is not valid!");
                        }
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });		
	}
	
	private boolean isExcel(String fileName) {
		//only reads file.type, not .type, .file, type, or file
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0 && fileName.substring(fileName.lastIndexOf(".")+1).equals("xlsx")) {
        	return true;
        }
        return false;
    }
	
	/** Scans the file denoted by filePath, adding students to a list in the Program class (not implemented). If an error is found, terminates and returns false
	 * @Param filePath The directory of the Excel
	 * @Return True if the program was scanned without errors.
	 */
	private boolean scan(String filePath) {
	    SheetReader sr = null;
	    try {
			sr = new SheetReader(filePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    //int row = 1;
	    try {
	    	sr.next();//ignore first row since it's not a student
	    } catch(Exception e) {
	    	return false;
	    }
	    while(sr.hasNext()) {
	    	//row++;
	    	ArrayList<String> tmp = sr.next();
	    	if(!tmp.isEmpty()) {
	    		try {
	 	            Student s = new Student(tmp);
	 	            students.add(s);
	 	        } catch(Exception e) {
	 	            return false;
	 	       }
	    	}
	    }
	    return true;
	}
	
	/**
	 * Creates an error window with the specified error text.
	 * @param text The message to be displayed in the error window
	 */
	private void popUpError(String text) {
		Alert alert = new Alert(AlertType.ERROR,text);
		alert.show();
	}
	
}

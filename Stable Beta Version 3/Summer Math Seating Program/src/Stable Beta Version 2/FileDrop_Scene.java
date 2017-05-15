package pcage;
/**
 * Represents the scene where the user drops in the Excel file to be read.
 * @author Skylar Chan
 */
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class FileDrop_Scene extends Scene {
	Label label = new Label("Please drop in an Excel file");
	/**
	 * Represents the button to be clicked to advance to the next scene.
	 */
	Button click;
	/**
	 * Creates the scene for the file drop step with the specified MainStage.
	 * @param m The MainStage that displays the scene.
	 */
	public FileDrop_Scene(BorderPane bp) {
		super(bp,551,400);
		BorderPane myPane = new BorderPane();
		label.setFont(new Font("Times New Roman",40));
		label.setAlignment(Pos.CENTER);
		label.setWrapText(true);
		myPane.setCenter(label);
		click = new Button("Click here to continue");
		click.setFont(new Font("Times New Roman",24));
		click.setVisible(false);
		click.setOnAction(e->ButtonClicked(e));
		addEvents();
		bp.setTop(myPane);
		bp.setCenter(click);
	}

	public void ButtonClicked(ActionEvent e) {
		if (e.getSource()==click)
			Program.switchScene("SelectSession_Scene");
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
                    if(getFileExtension(filePath).equals("xlsx")) {
                    	label.setText("File selected:\n"+filePath);
                    	label.setFont(new Font("Times New Roman",24));
                    	click.setVisible(true);
                    	Program.setFilePath(filePath);
                    } else {
                    	click.setVisible(false);
                    	label.setText("Please drop in an Excel file");
                    	label.setFont(new Font("Times New Roman",40));
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });		
	}
	
	private static String getFileExtension(String fileName) {
        //String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	public String getLabelText() {
		return label.getText();
	}
	
}

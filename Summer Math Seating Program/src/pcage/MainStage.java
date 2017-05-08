package pcage;
/** Represents the main GUI of the program.
 * @author Skylar Chan 
 */
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainStage extends Stage {
	/**
	 * Represents the BorderPane currently shown in the GUI.
	 */
	BorderPane currentPane;
	/**
	 * Represents the scene currently shown in the GUI.
	 */
	Scene currentScene;
	String filePath;
	/**
	 * Creates a GUI that will perform the functions requested by the client (Mr. Gordon).
	 */
	public MainStage() {
		setTitle("Summer Math Program");
		switchScene("FileDrop_Scene");
		getIcons().add(new Image("CTRL-S logo.png"));
		setAlwaysOnTop(true);
		show();
	}
	/**
	 * Replaces the current scene with the new scene specified. This is to advance the program as the user provides input.
	 * @param scene The scene to be switched to.
	 */
	public void switchScene(String scene) {
		currentPane = new BorderPane();
		switch(scene) {
		case("FileDrop_Scene"):
			currentScene = new FileDrop_Scene(this);
			break;
		case("SelectSession_Scene"):
			currentScene = new SelectSession_Scene(this);
			break;
		}
		String css = this.getClass().getResource("/bootstrap2.css").toExternalForm();
		currentScene.getStylesheets().clear();
		currentScene.getStylesheets().add(css);
		setScene(currentScene);
	}
	/**
	 * Gets the Border Pane currently shown in the GUI.
	 * @return The Border Pane currently shown in the GUI.
	 */
	public BorderPane getBorderPane() {
		return currentPane;
	}
	/**
	 * Sets the file path of the Excel file to be read.
	 * @param fp The string representing the file path of the Excel file to be read.
	 */
	public void setFilePath(String fp) {
		filePath = fp;
	}
	/**
	 * Gets the file path of the Excel file to be read.
	 * @return The string representing the file path of the Excel file to be read.
	 */
	public String getFilePath() {
		return filePath;
	}

}

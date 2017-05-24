package pcage;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Program extends Application {
    
    private static BorderPane currentPane;
    private static Scene currentScene;
    private static Stage stage;
    private static String filePath;
    private static int sceneNum = 0;
    private ArrayList<Student> students = new ArrayList<Student>();
	
	public void start(Stage s) {
	    stage = s;
	    stage.setTitle("Summer Math Program");
		stage.getIcons().add(new Image("CTRL-S logo.png"));
		switchScene(students);
		stage.show();
		System.out.println(this);
	}
	
	/**
	 * Displays the next scene of the Program window. Also passes the reference to the list of students that is to be sorted.
	 * @param arr The reference of the list of students to be sorted
	 */
	public static void switchScene(ArrayList<Student> arr) {
		sceneNum++;
		currentPane = new BorderPane();
		switch(sceneNum) {
		case(1):
			currentScene = new FileDrop_Scene(currentPane, arr);
			break;
		case(2):
			currentScene = new SelectSession_Scene(currentPane, arr);
			break;
		case(3):
			currentScene = new SortedStudent_Scene(currentPane, arr);
			break;
		}
		stage.setScene(currentScene);
		
		/*String css = Program.class.getResource("/application.css").toExternalForm();
		currentScene.getStylesheets().clear();
		currentScene.getStylesheets().add(css);
		//Takes too long!!!*/
	}
	
	public static void main(String[] args) {
        Application.launch(args);
    }
	
	public static void setFilePath(String fp) {
		filePath = fp;
	}
	public static String getFilePath() {
		return filePath;
	}
	
}
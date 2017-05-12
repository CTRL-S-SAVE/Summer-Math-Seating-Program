package pcage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Program extends Application {
    
    //transition from MainStage to here
    
    private static BorderPane currentPane;
    private static Scene currentScene;
    private static Stage stage;
    private static String filePath;
	
	public void start(Stage s) {
	    stage = s;
	    stage.setTitle("Summer Math Program");
		stage.getIcons().add(new Image("CTRL-S logo.png"));
		switchScene("FileDrop_Scene");
		stage.show();
	}
	
	public static void switchScene(String scene) {
		currentPane = new BorderPane();
		switch(scene) {
		case("FileDrop_Scene"):
			currentScene = new FileDrop_Scene(currentPane);
			break;
		case("SelectSession_Scene"):
			currentScene = new SelectSession_Scene(currentPane);
			break;
		}
		stage.setScene(currentScene);
		/*String css = this.getClass().getResource("/bootstrap2.css").toExternalForm();
		currentScene.getStylesheets().clear();
		currentScene.getStylesheets().add(css);
		Takes too long!!!*/
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
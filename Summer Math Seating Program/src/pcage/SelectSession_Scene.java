package pcage;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SelectSession_Scene extends Scene {
	MainStage parent;
	Button sesh1, sesh2;
	Label filePath;
	
	public SelectSession_Scene(MainStage m) {
		super(m.getBorderPane(),551,400);
		parent = m;
		sesh1 = new Button("Sort session 1");
		sesh2 = new Button("Sort session 2");
		filePath = new Label(m.getFilePath());
		sesh1.setOnAction(e -> btncode(e));
        sesh2.setOnAction(e -> btncode(e));
		m.getBorderPane().setTop(sesh1);
		m.getBorderPane().setBottom(sesh2);
		m.getBorderPane().setCenter(filePath);
	}
	
	public void btncode(ActionEvent e) {
		System.out.println(e.getSource());
	}
}

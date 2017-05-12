package pcage;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SelectSession_Scene extends Scene {
	Button sesh1, sesh2;
	Label filePath;
	
	public SelectSession_Scene(BorderPane bp) {
		super(bp,551,400);
		sesh1 = new Button("Sort session 1");
		sesh2 = new Button("Sort session 2");
		filePath = new Label(Program.getFilePath());
		sesh1.setOnAction(e -> btncode(e));
        sesh2.setOnAction(e -> btncode(e));
		bp.setTop(sesh1);
		bp.setBottom(sesh2);
		bp.setCenter(filePath);
	}
	
	public void btncode(ActionEvent e) {
		System.out.println(e.getSource());
	}
}

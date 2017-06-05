package pcage;
/**The table where sorted students are viewed before saving.
 * @author Skylar Chan
 */
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTable extends TableView {
	TableColumn firstNameCol = new TableColumn("First Name");
	TableColumn lastNameCol = new TableColumn("Last Name");
	TableColumn seatCol = new TableColumn("Seat");
	TableColumn genCol = new TableColumn("Gender");
	TableColumn schoolCol = new TableColumn("Middle School");
	
	@SuppressWarnings("unchecked")
	public StudentTable(ArrayList<Student> students) {
		setEditable(false);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("first"));
		firstNameCol.setStyle("-fx-font-size:16;");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("last"));
		lastNameCol.setStyle("-fx-font-size:16;");
		seatCol.setCellValueFactory(new PropertyValueFactory<Student,String>("location"));
		seatCol.setStyle("-fx-font-size:16;");
		seatCol.setComparator(new GroupNameComparator());
		genCol.setCellValueFactory(new PropertyValueFactory<Student,String>("gender"));
		genCol.setStyle("-fx-font-size:16;");
		schoolCol.setCellValueFactory(new PropertyValueFactory<Student,String>("school"));
		schoolCol.setStyle("-fx-font-size:16;");
		getColumns().addAll(lastNameCol, firstNameCol, seatCol, genCol, schoolCol);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		update(students);
	}
	
	/**
	 * Updates this Table's contents with the specified list of Students.
	 * @param students The list of students to be viewed.
	 */
	public void update(ArrayList<Student> students) {
		TableColumn sortcolumn = null;
        SortType st = null;
        if (!getSortOrder().isEmpty()) {
            sortcolumn = (TableColumn) getSortOrder().get(0);
            st = sortcolumn.getSortType();
        }
		ObservableList<Student> data = FXCollections.observableArrayList();
		for(Student s:students) {
			data.add(s);
		}
		setItems(data);
		if (sortcolumn!=null) {
            getSortOrder().add(sortcolumn);
            sortcolumn.setSortType(st);
            sortcolumn.setSortable(true); // This performs a sort
        }
	}
	
	
}

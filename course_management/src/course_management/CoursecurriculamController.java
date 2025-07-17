package course_management;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CoursecurriculamController implements Initializable {

    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, Integer> noColumn;
    @FXML
    private TableColumn<Course, Integer> semesterColumn;
    @FXML
    private TableColumn<Course, String> nameColumn;
    @FXML
    private TableColumn<Course, Integer> creditColumn;
    @FXML
    private TableColumn<Course, String> codeColumn;

    private ObservableList<Course> courseList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up column bindings
        noColumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));

        // Load data from database
        loadCourseData();
    }

    private void loadCourseData() {
        String url = "jdbc:mysql://localhost:3306/course_management";
        String user = "root";
        String password = "1234"; // your MySQL password here

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM course_curriculum";
            ResultSet rs = stmt.executeQuery(query);

            int count = 1;
            while (rs.next()) {
                int semester = rs.getInt("semester");
                String courseName = rs.getString("course_name");
                int credit = rs.getInt("credit");
                String courseCode = rs.getString("course_code");

                Course course = new Course(count, semester, courseName, credit, courseCode);
                courseList.add(course);
                count++;
            }
            courseTable.setItems(courseList);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

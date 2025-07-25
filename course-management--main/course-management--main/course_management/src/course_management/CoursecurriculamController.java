package course_management;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    @FXML
    private Button home2;
    @FXML
    private Button profile2;
    @FXML
    private Button coursecurriculum2;
    @FXML
    private Button cr2;
    @FXML
    private Button cp2;
    @FXML
    private Button log2;

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

    @FXML
    private void homeh2(ActionEvent event) throws IOException {
          Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("main_course.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void profilep2(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void coursrcurriculumc2(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("coursecurriculam.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void crc2(ActionEvent event) throws IOException {
    Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("courseregistration.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void cpcp2(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("changepassword.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void logout2(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }
}

package course_management;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CourseregistrationController implements Initializable {

    @FXML
    private TableView<Registration> registrationTable;  // <-- Added @FXML here!
    @FXML
    private TableColumn<Registration, String> crsem;
    @FXML
    private TableColumn<Registration, String> crccode;
    @FXML
    private TableColumn<Registration, String> crsection;
    @FXML
    private TextField insem;
    @FXML
    private TextField inccode;
    @FXML
    private TextField insection;

    private ObservableList<Registration> registrationList = FXCollections.observableArrayList();

    // Database info
    private final String DB_URL = "jdbc:mysql://localhost:3306/course_management";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "1234";

    // Must be set after successful login
    public static int currentUserId = 0;

    @FXML
    private Button h3;
    @FXML
    private Button p3;
    @FXML
    private Button cr3;
    @FXML
    private Button courser3;
    @FXML
    private Button cp3;
    @FXML
    private Button logout3;
    @FXML
    private Button addbuttonc;
    @FXML
    private Button deletebutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crsem.setCellValueFactory(new PropertyValueFactory<>("semester"));
        crccode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        crsection.setCellValueFactory(new PropertyValueFactory<>("section"));

        registrationTable.setItems(registrationList);

        loadRegistrations();
    }

    @FXML
    private void addbuttonnnnn(ActionEvent event) {
        String semester = insem.getText().trim();
        String courseCode = inccode.getText().trim();
        String section = insection.getText().trim();

        if (semester.isEmpty() || courseCode.isEmpty() || section.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill in all fields.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // Check if the course exists in the course_curriculum table
            String checkSql = "SELECT COUNT(*) FROM course_curriculum WHERE semester = ? AND course_code = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, semester);
            checkStmt.setString(2, courseCode);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                showAlert(Alert.AlertType.ERROR, "Course Not Found",
                        "The course code entered does not exist in the course curriculum for the specified semester.");
                return;
            }

            // Insert into registrations
            String sql = "INSERT INTO registrations (user_id, semester, course_code, section) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUserId);
            pstmt.setString(2, semester);
            pstmt.setString(3, courseCode);
            pstmt.setString(4, section);
            pstmt.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Course registered successfully!");

            // Clear fields
            insem.clear();
            inccode.clear();
            insection.clear();

            // Reload table
            loadRegistrations();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to register course.");
        }
    }

    private void loadRegistrations() {
        registrationList.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT semester, course_code, section FROM registrations WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUserId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String semester = rs.getString("semester");
                String courseCode = rs.getString("course_code");
                String section = rs.getString("section");

                registrationList.add(new Registration(semester, courseCode, section));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load registrations.");
        }
    }

    @FXML
    private void delatebuttonccc(ActionEvent event) {
        Registration selected = registrationTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a course to delete.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM registrations WHERE user_id = ? AND semester = ? AND course_code = ? AND section = ? LIMIT 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUserId);
            pstmt.setString(2, selected.getSemester());
            pstmt.setString(3, selected.getCourseCode());
            pstmt.setString(4, selected.getSection());
            pstmt.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Deleted", "Course registration deleted.");
            loadRegistrations();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to delete registration.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void home3(ActionEvent event) throws IOException {
          Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("main_course.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void profile3(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void crc3(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("coursecurriculam.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void courserr3(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("courseregistration.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void cpc3(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("changepassword.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void logoutt3(ActionEvent event) throws IOException {
          Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    public static class Registration {
        private final String semester;
        private final String courseCode;
        private final String section;

        public Registration(String semester, String courseCode, String section) {
            this.semester = semester;
            this.courseCode = courseCode;
            this.section = section;
        }

        public String getSemester() {
            return semester;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getSection() {
            return section;
        }
    }
}

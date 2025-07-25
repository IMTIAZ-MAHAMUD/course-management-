package course_management;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ChangepasswordController implements Initializable {

    @FXML
    private Button cpupdate;
    @FXML
    private Button h4;
    @FXML
    private Button p4;
    @FXML
    private Button cr4;
    @FXML
    private Button cp4;
    @FXML
    private Button logout4;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label messageLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Optional: Initialization logic
    }

    @FXML
    private void passupdate(ActionEvent event) {
        String username = usernameField.getText().trim();
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            messageLabel.setText("New passwords do not match.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Check if user exists and old password is correct
            String checkSql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            checkStmt.setString(2, oldPassword);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Update to new password
                String updateSql = "UPDATE users SET password = ? WHERE username = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, username);
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    messageLabel.setText("Password updated successfully.");
                    messageLabel.setStyle("-fx-text-fill: green;");
                    oldPasswordField.clear();
                    newPasswordField.clear();
                    confirmPasswordField.clear();
                } else {
                    messageLabel.setText("Failed to update password.");
                }
            } else {
                messageLabel.setText("Incorrect username or old password.");
            }
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void home4(ActionEvent event) throws IOException {
        loadScene(event, "main_course.fxml");
    }

    @FXML
    private void profile4(ActionEvent event) throws IOException {
        loadScene(event, "profile.fxml");
    }

    @FXML
    private void coursec4(ActionEvent event) throws IOException {
        loadScene(event, "coursecurriculam.fxml");
    }

    @FXML
    private void coursrr4(ActionEvent event) throws IOException {
        loadScene(event, "courseregistration.fxml");
    }

    @FXML
    private void changp4(ActionEvent event) throws IOException {
        loadScene(event, "changepassword.fxml");
    }

    @FXML
    private void lg4(ActionEvent event) throws IOException {
        loadScene(event, "FXMLDocument.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }
}

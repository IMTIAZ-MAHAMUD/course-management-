/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package course_management;

import java.io.IOException;
import java.net.URL;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SignupController implements Initializable {

    @FXML
    private Button l1;
    @FXML
    private Label c1;
    @FXML
    private Label pss1;
    @FXML
    private Button s1;
    @FXML
    private TextField usrname;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private Label warn2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signup(ActionEvent event) {
        
         String username = usrname.getText();
    String emailVal = email.getText();
    String pass = password.getText();

    if (username.isEmpty() || emailVal.isEmpty() || pass.isEmpty()) {
        warn2.setText("Please fill all fields");
        return;
    }

    // You should hash the password in production; for simplicity, store as plain text (not recommended)

    String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        stmt.setString(2, emailVal);
        stmt.setString(3, pass);

        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            warn2.setText("Signup successful! Please login.");
        } else {
            warn2.setText("Signup failed. Try again.");
        }

    } catch (java.sql.SQLIntegrityConstraintViolationException e) {
        warn2.setText("Username already exists.");
    } catch (Exception e) {
        warn2.setText("Error: " + e.getMessage());
        e.printStackTrace();
    }
        
    }

    @FXML
    private void gotologin(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
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
 *
 * @author USER
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button l1;
    @FXML
    private Label c1;
    @FXML
    private TextField u1;
    @FXML
    private PasswordField p1;
    @FXML
    private Button s1;
    @FXML
    private Label usr1;
    @FXML
    private Label pss1;
    @FXML
    private Label warn;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginto(ActionEvent event) throws IOException {
        
        
         String username = u1.getText();
    String pass = p1.getText();

    if (username.isEmpty() || pass.isEmpty()) {
        warn.setText("Please enter username and password.");
        return;
    }

    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

    try (Connection conn = DBConnection.getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        stmt.setString(2, pass);

        java.sql.ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // User authenticated
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Main_course.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            Window window = ((Node) event.getSource()).getScene().getWindow();
            window.hide();

        } else {
            warn.setText("Invalid username or password");
        }

    } catch (Exception e) {
        warn.setText("Error: " + e.getMessage());
        e.printStackTrace();
    }
        
        
    }

    @FXML
    private void signuphere(ActionEvent event) throws IOException {
        
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
        
        
        
    }
    
}

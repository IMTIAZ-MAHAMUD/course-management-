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
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProfileController implements Initializable {

    @FXML
    private Button home1;
    @FXML
    private Button profile1;
    @FXML
    private Button coursecurriculum1;
    @FXML
    private Button courseregistration1;
    @FXML
    private Button password1;
    @FXML
    private Button logout1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void homeh1(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("main_course.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void profilep1(ActionEvent event) {
    }

    @FXML
    private void coursecurriculumc1(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("coursecurriculam.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
        
    }

    @FXML
    private void courseregistrationcr1(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("courseregistration.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void passwordp1(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("changepassword.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void logoutl1(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }
    
}

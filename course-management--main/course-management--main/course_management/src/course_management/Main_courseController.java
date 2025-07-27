/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package course_management;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Main_courseController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button profbtn;
    @FXML
    private Button ccbtn;
    @FXML
    private Button crbtn;
    @FXML
    private Button cpbtn;
    @FXML
    private Button logoutbtn;

    
    @FXML
    private ImageView sliderImage;

    private ArrayList<Image> images;
    private int index = 0;
    @FXML
    private AnchorPane forslider;
    @FXML
    private Pane slidingPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       images = new ArrayList<>();

        // Load images (ensure these paths exist in /resources/img/)
        images.add(new Image(getClass().getResource("/course_management/img/1.png").toExternalForm()));
        images.add(new Image(getClass().getResource("/course_management/img/2.png").toExternalForm()));
        images.add(new Image(getClass().getResource("/course_management/img/3.png").toExternalForm()));

        // Set initial image
        sliderImage.setImage(images.get(index));

        // Timeline for sliding
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            index = (index + 1) % images.size();
            sliderImage.setImage(images.get(index));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    

    @FXML
    private void prof(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void cc(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("coursecurriculam.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void cr(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("courseregistration.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void cp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("changepassword.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Window window = ((Node) event.getSource()).getScene().getWindow();
        window.hide();
    }
    
    public class SlidePanelController {

    @FXML
    private Pane slidingPane;

    @FXML
    public void initialize() {
        slideIn();

        // Automatically slide out after 3 seconds
        Timeline delay = new Timeline(new KeyFrame(Duration.seconds(3), e -> slideOut()));
        delay.play();
    }

    private void slideIn() {
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(500), slidingPane);
        slideIn.setFromX(-200);
        slideIn.setToX(0);
        slideIn.play();
    }

    private void slideOut() {
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(500), slidingPane);
        slideOut.setFromX(0);
        slideOut.setToX(-200);
        slideOut.play();
    }
}
    
    
}

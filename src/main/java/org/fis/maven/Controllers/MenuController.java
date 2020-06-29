package org.fis.maven.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label id;

    @FXML
    public void registerButton(){
        try {
            Stage stage = (Stage) id.getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Register.fxml"));
            stage.setTitle("Register");
            stage.setScene(new Scene(parent, 600,600));
            stage.show();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @FXML
    public void loginAdministrator(){
        //login admin
    }

    @FXML
    public void loginClient(){
        try {
            Stage stage = (Stage) id.getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("LoginClient.fxml"));
            stage.setTitle("Login Client");
            stage.setScene(new Scene(parent, 600,600));
            stage.show();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @FXML
    public void loginDriver(){
        try {
            Stage stage = (Stage) id.getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("LoginDriver.fxml"));
            stage.setTitle("Login Driver");
            stage.setScene(new Scene(parent, 600,600));
            stage.show();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
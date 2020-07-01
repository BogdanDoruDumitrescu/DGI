package org.fis.maven.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.maven.Exceptions.UserPasswordIncorrect;
import org.fis.maven.Models.User;
import org.fis.maven.Services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class LoginClientController {
    @FXML
    Label id;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label error;

    @FXML
    public void initialize(){
        error.setText("");
        UserService.loadUser();
    }

    @FXML
    public void butonBack()
    {
        try
        {
            Stage stage=(Stage)id.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
            stage.setTitle("Menu");
            stage.setScene(new Scene(ceva,600,600));
            stage.show();

        }catch(IOException e) {
            System.out.println(e);
        }
    }


    public void login() {
        try {
            User current = UserService.checkCredentials(usernameField.getText(), UserService.encodePassword(passwordField.getText()), "Client");
            if (current != null) {
                error.setText("");
                try {
                    current.setLogged(true);
                    Stage stage = (Stage) id.getScene().getWindow();
                    Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("ClientPage.fxml"));
                    stage.setTitle("Client Page");
                    stage.setScene(new Scene(ceva, 800, 600));
                    stage.show();

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else
                throw new UserPasswordIncorrect();
        } catch (UserPasswordIncorrect e) {
            error.setText("Incorrect username or password or account is not confirmed!");
        }
    }
}

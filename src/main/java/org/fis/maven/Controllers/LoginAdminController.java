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
import org.fis.maven.Models.Admin;
import org.fis.maven.Services.AdminService;

import java.io.IOException;

public class LoginAdminController {
    @FXML
    TextField idField;
    @FXML
    PasswordField passField;
    @FXML
    Label error;

    @FXML
    public void initialize(){
        error.setText("");
        AdminService.loadAdmins();
    }

    @FXML
    public void back()
    {
        try
        {
            Stage stage=(Stage)idField.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
            stage.setTitle("Menu");
            stage.setScene(new Scene(ceva,600,600));

        }catch(IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void login(){
        boolean flag = false;

        for(Admin i: AdminService.getA()){
            if(i.getUsername().equals(idField.getText())&&i.getPassword().equals(AdminService.encodePassword(passField.getText()))){
                flag = true;
                break;
            }
        }

        if(flag){
            try
            {
                Stage stage=(Stage)idField.getScene().getWindow();
                Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("AdminMenu.fxml"));
                stage.setTitle("Admin Menu");
                stage.setScene(new Scene(ceva,600,600));
                stage.show();

            }catch(Exception e) {
                System.out.println(e);
            }
        }else {
            try{
                throw new UserPasswordIncorrect();
            }catch (UserPasswordIncorrect e){
                error.setText("Username or password is incorrect!");
            }
        }
    }
}

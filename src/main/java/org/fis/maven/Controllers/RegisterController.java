package org.fis.maven.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.maven.Exceptions.AlreadyExists;
import org.fis.maven.Exceptions.EmptyField;
import org.fis.maven.Exceptions.LowAmount;
import org.fis.maven.Exceptions.NegativeAmount;
import org.fis.maven.Models.Admin;
import org.fis.maven.Models.User;
import org.fis.maven.Services.AdminService;
import org.fis.maven.Services.UserService;

import java.io.IOException;

public class RegisterController {
    @FXML
    TextField nameField;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField mailField;
    @FXML
    ChoiceBox role;
    @FXML
    TextField creditField;
    @FXML
    Label error;
    @FXML
    TextField cityField;

    public void initialize(){
        role.getItems().clear();
        role.getItems().addAll("Admin", "Driver", "Client");
        role.setValue("Driver");
        AdminService.loadAdmins();
        UserService.loadUser();
        error.setText("");
    }

    public void backButton()
    {
        try
        {
            Stage stage=(Stage)role.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
            stage.setTitle("Menu");
            stage.setScene(new Scene(ceva,600,600));

        }catch(IOException e) {
            System.out.println(e);
        }
    }

    public void signUp() {
        try {
            if (role.getValue().equals("Admin")) {
                for (Admin i : AdminService.getA()) {
                    if (i.getUsername().equals(usernameField.getText())) {
                        throw new AlreadyExists();
                    }
                }

                if(nameField.getText().length()==0||usernameField.getText().length()==0||passwordField.getText().length()==0||mailField.getText().length()==0)
                    throw new EmptyField();

                AdminService.getA().add(new Admin(nameField.getText(), usernameField.getText(), AdminService.encodePassword(passwordField.getText()), mailField.getText()));
                AdminService.writeAdmins();
                error.setText("Done!");
            } else {
                for(User i: UserService.getU()){
                    if(i.getUsername().equals(usernameField.getText())){
                        throw new AlreadyExists();
                    }
                }

                try {
                    if(nameField.getText().length()==0||usernameField.getText().length()==0||passwordField.getText().length()==0||mailField.getText().length()==0||cityField.getText().length()==0||creditField.getText().length()==0)
                        throw new EmptyField();
                    if(Integer.parseInt(creditField.getText())<0)
                        throw new NegativeAmount();
                    User user = new User(nameField.getText(), usernameField.getText(), UserService.encodePassword(passwordField.getText()), mailField.getText(), role.getValue().toString(), Integer.parseInt(creditField.getText()), false, cityField.getText());
                    UserService.getU().add(user);
                    if (role.getValue().equals("Driver")) {
                        user.setStatus("Available");
                    }
                    UserService.writeUser();
                    error.setText("Done!");
                }catch (NegativeAmount e){
                    error.setText("The amount is below 0!");
                }catch (NumberFormatException e){
                    error.setText("Credit must be an integer!");
                }catch (Exception e){
                    error.setText("One or more fields are empty!");
                }
            }
        }catch (EmptyField e) {
            error.setText("One or more fields are empty!");
        } catch(Exception e){
            error.setText("Account already exists!");
        }
    }
}

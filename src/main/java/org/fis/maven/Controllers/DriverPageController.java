package org.fis.maven.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.maven.Exceptions.InsufficientMoney;
import org.fis.maven.Models.User;
import org.fis.maven.Services.UserService;

import java.io.IOException;

public class DriverPageController {
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private Label statusLabel;
    @FXML
    private Label creditLabel;
    @FXML
    private TextField moneyField;
    @FXML
    private Label error;

    private User current;

    @FXML
    public void initialize() {
        current = LoginDriverController.getCurrent();

        choiceBox.getItems().addAll("Available", "Working");
        choiceBox.setValue("Available");

        creditLabel.setText(String.valueOf(current.getCredit()));
        statusLabel.setText(current.getStatus());

        error.setText("");
    }

    @FXML
    public void logout() {
        current.setLogged(false);

        try {
            Stage stage = (Stage) choiceBox.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
            stage.setTitle("Menu");
            stage.setScene(new Scene(ceva, 600, 600));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void withdrawButton() {
        try {
            if (current.getCredit() - Integer.parseInt(moneyField.getText()) >= 0) {
                current.setCredit(current.getCredit() - Integer.parseInt(moneyField.getText()));
                UserService.writeUser();

                this.initialize();
            } else {
                throw new InsufficientMoney();
            }
        }catch (InsufficientMoney e){
            error.setText("Insufficient money!");
        }
    }

    @FXML
    public void setButton() {
        current.setStatus(choiceBox.getValue().toString());
        UserService.writeUser();

        this.initialize();
    }

    @FXML
    public void races() {
    }

    public void requests(){
    }
}
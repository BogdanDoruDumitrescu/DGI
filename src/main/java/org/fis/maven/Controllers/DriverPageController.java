package org.fis.maven.Controllers;

import com.sun.org.apache.xpath.internal.operations.Neg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.maven.Exceptions.InsufficientMoney;
import org.fis.maven.Exceptions.NegativeAmount;
import org.fis.maven.Models.User;
import org.fis.maven.Services.UserService;

import java.io.IOException;

public class DriverPageController {
    @FXML
    ChoiceBox choiceBox;
    @FXML
    Label statusLabel;
    @FXML
    Label creditLabel;
    @FXML
    TextField moneyField;
    @FXML
    Label error;

    private static User current;

    @FXML
    public void initialize() {
        current = LoginDriverController.getCurrent();

        choiceBox.getItems().clear();
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

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void withdrawButton() {
        try {
            if(Integer.parseInt(moneyField.getText())<0) {
                throw new NegativeAmount();
            }
            if (current.getCredit() - Integer.parseInt(moneyField.getText()) >= 0) {
                current.setCredit(current.getCredit() - Integer.parseInt(moneyField.getText()));
                UserService.writeUser();

                this.initialize();
            } else {
                throw new InsufficientMoney();
            }
        }catch (InsufficientMoney e){
            error.setText("Insufficient money!");
        }catch (NegativeAmount e){
            error.setText("Amount must be positive!");
        }catch (Exception e){
            error.setText("Amount must be integer!");
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
        try {
            Stage stage = (Stage) choiceBox.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("MyRaces.fxml"));
            stage.setTitle("My Races");
            stage.setScene(new Scene(ceva, 600, 600));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void requests(){
        try {
            Stage stage = (Stage) choiceBox.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Pending.fxml"));
            stage.setTitle("Login or Register Menu");
            stage.setScene(new Scene(ceva, 600, 600));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static User getCurrent() {
        return current;
    }

    public static void setCurrent(User current) {
        DriverPageController.current = current;
    }
}
package org.fis.maven.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.maven.Exceptions.NegativeAmount;
import org.fis.maven.Models.Race;

import java.io.IOException;

public class AdminSetPriceController {
    @FXML
    private TextField priceField;
    @FXML
    private Label error;

    @FXML
    public void back() {
        try {
            Stage stage = (Stage) priceField.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("AdminMenu.fxml"));
            stage.setTitle("Admin Menu");
            stage.setScene(new Scene(ceva, 600, 600));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void setPrice() {
        try {
            if(Integer.parseInt(priceField.getText())<0){
                throw new NegativeAmount();
            }
            Race.setPricePerKm(Integer.parseInt(priceField.getText()));
            error.setText("");
        }catch (NegativeAmount e){
            error.setText("The amount is below 0!");
        }catch (Exception e){
            error.setText("Price must be an integer!");
        }

    }
}

package org.fis.maven.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.maven.Models.Race;

import java.io.IOException;

public class AdminSetPriceController {
    @FXML
    private TextField priceField;

    @FXML
    public void back() {
        try {
            Stage stage = (Stage) priceField.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
            stage.setTitle("Menu");
            stage.setScene(new Scene(ceva, 600, 600));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void setPrice() {

    }
}

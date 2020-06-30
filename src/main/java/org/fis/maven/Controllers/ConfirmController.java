package org.fis.maven.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.fis.maven.Models.User;

import java.io.IOException;


public class ConfirmController {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> mailColumn;
    @FXML
    private TableColumn<User, Boolean> confirmedColumn;

    public void back(){
        try {
            Stage stage = (Stage) table.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("AdminMenu.fxml"));
            stage.setTitle("Admin Menu");
            stage.setScene(new Scene(ceva, 600, 600));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void confirm(){


    }


}

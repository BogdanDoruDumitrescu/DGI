package org.fis.maven.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.fis.maven.Models.Race;
import org.fis.maven.Models.User;
import org.fis.maven.Services.UserService;

import java.io.IOException;
import java.util.ArrayList;


public class ConfirmController {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> mailColumn;
    @FXML
    private TableColumn<User, Boolean> confirmedColumn;

    public void initialize(){
        UserService.loadUser();
        ArrayList<User> neconfirmati = new ArrayList<>();

        for(User i:UserService.getU()){
            if(!i.isConfirmed()){
                neconfirmati.add(i);
            }
        }

        table.setItems(FXCollections.observableArrayList(neconfirmati));
        mailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("mail"));
        confirmedColumn.setCellValueFactory(new PropertyValueFactory<User, Boolean>("confirmed"));
        table.getSelectionModel().select(0);
    }

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
        table.getSelectionModel().getSelectedItem().setConfirmed(true);

        UserService.writeUser();
        this.initialize();
    }


}

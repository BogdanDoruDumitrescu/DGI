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
import org.fis.maven.Services.RaceService;

import java.io.IOException;
import java.util.ArrayList;

public class PendingController {

    @FXML
    private User current;
    @FXML
    TableView<Race> table;
    @FXML
    TableColumn<Race, Integer> kmColumn;
    @FXML
    TableColumn<Race, Integer> priceColumn;
    @FXML
    TableColumn<Race, String> clientColumn;
    @FXML
    TableColumn<Race, String> statusColumn;

    public PendingController(){
        current = LoginDriverController.getCurrent();
    }

    @FXML
    public void initialize(){
        ArrayList<Race> curse = new ArrayList<>();

        RaceService.loadRaces();
        for (Race i : RaceService.getR()) {
            if (i.getDriverUserName().equals(current.getUsername())) {
                if (i.getState().equals("Pending")) {
                    curse.add(i);
                }
            }
        }

        table.setItems(FXCollections.observableArrayList(curse));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("clientUserName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("state"));
        kmColumn.setCellValueFactory(new PropertyValueFactory<Race, Integer>("km"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Race, Integer>("totalPrice"));

        table.getSelectionModel().select(0);
    }

    @FXML
    public void backButton(){
        try {
            Stage stage = (Stage) table.getScene().getWindow();
            Parent ceva = FXMLLoader.load(getClass().getClassLoader().getResource("DriverPage.fxml"));
            stage.setTitle("Driver Page");
            stage.setScene(new Scene(ceva, 600, 600));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void acceptButton(){
        table.getSelectionModel().getSelectedItem().setState("In Progress");
        RaceService.writeRace();
        this.initialize();
    }
}

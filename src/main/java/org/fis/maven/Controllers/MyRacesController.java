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
import org.fis.maven.Services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class MyRacesController {
    @FXML
    private static User current;
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

    @FXML
    public void initialize() {
        current = LoginDriverController.getCurrent();

        ArrayList<Race> curse = new ArrayList<>();

        RaceService.loadRaces();

        for (Race i : RaceService.getR()) {
            if (i.getDriverUserName().equals(current.getUsername())) {
                if (i.getState().equals("In Progress") || i.getState().equals("Done")) {
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
    public void finish() {
        if(!table.getSelectionModel().getSelectedItem().getState().equals("Done")) {
            table.getSelectionModel().getSelectedItem().setState("Done");
            current.setCredit(current.getCredit() + table.getSelectionModel().getSelectedItem().getTotalPrice());

            for (User i : UserService.getU()) {
                if (i.getUsername().equals(table.getSelectionModel().getSelectedItem().getClientUserName())) {
                    i.setCredit(i.getCredit() - table.getSelectionModel().getSelectedItem().getTotalPrice());
                }
            }

            UserService.writeUser();
            RaceService.writeRace();

            this.initialize();
            table.refresh();
        }
    }

    @FXML
    public void back() {
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

    public static User getCurrent() {
        return current;
    }

    public static void setCurrent(User current) {
        MyRacesController.current = current;
    }
}

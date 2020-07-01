package org.fis.maven.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.fis.maven.Models.Race;
import org.fis.maven.Models.User;
import org.fis.maven.Services.RaceService;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class RequestControllerTest extends ApplicationTest {
    private RequestController controller;

    @Before
    public void setUp(){
        controller = new RequestController();

        controller.error = new Label();
        controller.kmField = new TextField();
        controller.table = new TableView<>();
        controller.nameColumn = new TableColumn<>();
        controller.priceLabel = new Label();
        controller.totalLabel = new Label();
    }

    @Test
    public void calcul(){
        Race.setPricePerKm(20);
        controller.kmField.setText("10");

        controller.calcul();

        assertEquals(200,Integer.parseInt(controller.totalLabel.getText()));
    }

    @Test
    public void send(){
        User u1 = new User("", "", "", "", "", 0, false, "");
        User u2 = new User("", "", "", "", "", 250, false, "");

        Race.setPricePerKm(20);
        controller.kmField.setText("10");

        controller.table.getSelectionModel().select(u1);
        ClientPageController.setCurrent(u2);

        controller.send();

        assertEquals(1, RaceService.getR().size());
    }

    @Test
    public void insufficientMoney(){
        User u1 = new User("", "", "", "", "", 0, false, "");
        User u2 = new User("", "", "", "", "", 100, false, "");

        Race.setPricePerKm(20);
        controller.kmField.setText("10");

        controller.table.getSelectionModel().select(u1);
        ClientPageController.setCurrent(u2);

        controller.send();

        assertEquals("Insufficient Money!", controller.error.getText());
    }

    @Test
    public void negativeKM(){
        User u1 = new User("", "", "", "", "", 0, false, "");
        User u2 = new User("", "", "", "", "", 100, false, "");

        Race.setPricePerKm(20);
        controller.kmField.setText("-10");

        controller.table.getSelectionModel().select(u1);
        ClientPageController.setCurrent(u2);

        controller.send();

        assertEquals("KM amount is under 0!", controller.error.getText());
    }

    @Test
    public void notIntegerKM(){
        User u1 = new User("", "", "", "", "", 0, false, "");
        User u2 = new User("", "", "", "", "", 100, false, "");

        Race.setPricePerKm(20);
        controller.kmField.setText("sth");

        controller.table.getSelectionModel().select(u1);
        ClientPageController.setCurrent(u2);

        controller.send();

        assertEquals("KM amount must be integer!", controller.error.getText());
    }

    @Test
    public void calculNegativeKM(){
        Race.setPricePerKm(20);
        controller.kmField.setText("-10");

        controller.calcul();

        assertEquals("KM amount is under 0!", controller.error.getText());
    }

    @Test
    public void calculNotInteger(){
        Race.setPricePerKm(20);
        controller.kmField.setText("sth");

        controller.calcul();

        assertEquals("KM amount must be integer!", controller.error.getText());
    }
}

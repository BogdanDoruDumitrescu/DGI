package org.fis.maven.Controllers;

import javafx.scene.control.*;
import org.fis.maven.Models.User;
import org.fis.maven.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class ClientPageControllerTest extends ApplicationTest {
    private ClientPageController controller;

    @Before
    public void setUp(){
        controller = new ClientPageController();
        controller.error = new Label();
        controller.moneyField = new TextField();
        controller.creditLabel = new Label();
        controller.driverColumn = new TableColumn<>();
        controller.kmColumn = new TableColumn<>();
        controller.logoutButton = new Button();
        controller.priceColumn = new TableColumn<>();
        controller.statusColumn = new TableColumn<>();
        controller.tableID = new TableView<>();
        controller.userLabel = new Label();
    }

    @Test
    public void logOut(){
        User u = new User("", "", "", "", "", 0, false, "");
        u.setLogged(true);

        ClientPageController.setCurrent(u);

        controller.logOut();

        assertFalse(ClientPageController.getCurrent().isLogged());
    }

    @Test
    public void addMoney(){
        UserService.loadUser();
        UserService.getU().clear();
        User u = new User("name", "username", "pass", "mail", "Client", 0, true, "Timisoara");
        ClientPageController.setCurrent(u);

        controller.moneyField.setText("50");

        controller.addMoney();

        assertEquals(50, ClientPageController.getCurrent().getCredit());
    }
}

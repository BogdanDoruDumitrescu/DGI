package org.fis.maven.Controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.fis.maven.Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DriverPageControllerTest extends ApplicationTest {
    private DriverPageController controller;

    @Before
    public void setUp(){
        controller = new DriverPageController();
        controller.choiceBox = new ChoiceBox();
        controller.statusLabel = new Label();
        controller.creditLabel = new Label();
        controller.moneyField = new TextField();
        controller.error = new Label();
    }

    @Test
    public void logout(){
        User u = new User("name", "user", "pass", "mail", "Driver", 0, true, "Arad");
        u.setLogged(true);

        DriverPageController.setCurrent(u);

        controller.logout();

        assertFalse(u.isLogged());
    }

    @Test
    public void withdraw(){
        User u = new User("name", "user", "pass", "mail", "Driver", 100, true, "Arad");
        DriverPageController.setCurrent(u);

        controller.moneyField.setText("50");

        controller.withdrawButton();

        assertEquals(50, u.getCredit());

    }

    @Test
    public void withdrawInsufficientMoney(){
        User u = new User("name", "user", "pass", "mail", "Driver", 100, true, "Arad");
        DriverPageController.setCurrent(u);

        controller.moneyField.setText("150");

        controller.withdrawButton();

        assertEquals("Insufficient money!", controller.error.getText());
    }

    @Test
    public void withdrawNegativeAmount(){
        User u = new User("name", "user", "pass", "mail", "Driver", 100, true, "Arad");
        DriverPageController.setCurrent(u);

        controller.moneyField.setText("-150");

        controller.withdrawButton();

        assertEquals("Amount must be positive!", controller.error.getText());
    }

    @Test
    public void withdrawNotInteger(){
        User u = new User("name", "user", "pass", "mail", "Driver", 100, true, "Arad");
        DriverPageController.setCurrent(u);

        controller.moneyField.setText("sth");

        controller.withdrawButton();

        assertEquals("Amount must be integer!", controller.error.getText());
    }
}

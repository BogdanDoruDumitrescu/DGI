package org.fis.maven.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fis.maven.Models.User;
import org.fis.maven.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class LoginDriverControllerTest extends ApplicationTest {
    private LoginDriverController controller;

    @Before
    public void setUp(){
        controller = new LoginDriverController();
        controller.error = new Label();
        controller.passField = new PasswordField();
        controller.idField = new TextField();
    }

    @Test
    public void login(){
        User u = new User("user", "user",UserService.encodePassword("user"), "user", "Driver", 100, true, "Timisoara");
        UserService.loadUser();
        UserService.getU().add(u);

        controller.idField.setText("user");
        controller.passField.setText("user");

        controller.login();

        assertEquals("",controller.error.getText());
    }

    @Test
    public void loginIncorrect(){
        User u = new User("user", "user",UserService.encodePassword("user"), "user", "Driver", 100, true, "Timisoara");
        UserService.loadUser();
        UserService.getU().add(u);

        controller.idField.setText("user");
        controller.passField.setText("pass");

        controller.login();

        assertEquals("Incorrect username or password or account is not confirmed!",controller.error.getText());
    }
}

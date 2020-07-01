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

public class LoginClientControllerTest extends ApplicationTest {
    private LoginClientController controller;

    @Before
    public void setUp(){
        controller = new LoginClientController();
        controller.id = new Label();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.error = new Label();
    }

    @Test
    public void login(){
        User u = new User("", "user", UserService.encodePassword("user"), "", "Client", 0, true, "");
        UserService.loadUser();
        UserService.getU().add(u);

        controller.usernameField.setText("user");
        controller.passwordField.setText("user");

        controller.login();

        assertEquals("",controller.error.getText());
    }

    @Test
    public void loginIncorrect(){
        User u = new User("", "user", UserService.encodePassword("user"), "", "Client", 0, true, "");
        UserService.loadUser();
        UserService.getU().add(u);

        controller.usernameField.setText("user");
        controller.passwordField.setText("pass");

        controller.login();

        assertEquals("Incorrect username or password or account is not confirmed!",controller.error.getText());
    }
}

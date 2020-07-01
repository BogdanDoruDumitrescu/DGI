package org.fis.maven.Controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fis.maven.Models.Admin;
import org.fis.maven.Models.User;
import org.fis.maven.Services.AdminService;
import org.fis.maven.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class RegisterControllerTest extends ApplicationTest {
    private RegisterController controller;

    @Before
    public void setUp(){
        controller = new RegisterController();
        controller.nameField = new TextField();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.mailField = new TextField();
        controller.role = new ChoiceBox();
        controller.cityField = new TextField();
        controller.creditField = new TextField();
        controller.error = new Label();
    }

    @Test
    public void registerAdmin(){
        AdminService.loadAdmins();

        controller.usernameField.setText("admin");
        controller.nameField.setText("admin");
        controller.mailField.setText("mail");
        controller.passwordField.setText("admin");
        controller.role.setValue("Admin");

        controller.signUp();

        assertEquals(1, AdminService.getA().size());
    }

    @Test
    public void registerAdminTwice(){
        AdminService.loadAdmins();

        controller.usernameField.setText("admin");
        controller.nameField.setText("admin");
        controller.mailField.setText("mail");
        controller.passwordField.setText("admin");
        controller.role.setValue("Admin");

        controller.signUp();
        controller.signUp();

        assertEquals(1, AdminService.getA().size());
    }

    @Test
    public void registerAdminBlank(){
        AdminService.loadAdmins();

        controller.role.setValue("Admin");

        controller.signUp();
        controller.signUp();

        assertEquals("One or more fields are empty!", controller.error.getText());
    }

    @Test
    public void registerUser(){
        UserService.loadUser();

        controller.usernameField.setText("user");
        controller.nameField.setText("user");
        controller.mailField.setText("mail");
        controller.passwordField.setText("user");
        controller.role.setValue("Client");
        controller.cityField.setText("city");
        controller.creditField.setText("0");

        controller.signUp();

        assertEquals(1, UserService.getU().size());
    }

    @Test
    public void registerUserTwice(){
        UserService.loadUser();

        controller.usernameField.setText("user");
        controller.nameField.setText("user");
        controller.mailField.setText("mail");
        controller.passwordField.setText("user");
        controller.role.setValue("Client");
        controller.cityField.setText("city");
        controller.creditField.setText("0");

        controller.signUp();
        controller.signUp();

        assertEquals(1, UserService.getU().size());
    }

    @Test
    public void registerUserBlank(){
        UserService.loadUser();

        controller.role.setValue("Client");

        controller.signUp();

        assertEquals("One or more fields are empty!", controller.error.getText());
    }

    @Test
    public void registerUserNotInteger(){
        UserService.loadUser();

        controller.usernameField.setText("user");
        controller.nameField.setText("user");
        controller.mailField.setText("mail");
        controller.passwordField.setText("user");
        controller.role.setValue("Client");
        controller.cityField.setText("city");
        controller.creditField.setText("sth");

        controller.signUp();

        assertEquals("Credit must be an integer!", controller.error.getText());
    }
}

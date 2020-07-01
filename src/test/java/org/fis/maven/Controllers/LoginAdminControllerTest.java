package org.fis.maven.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fis.maven.Models.Admin;
import org.fis.maven.Services.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class LoginAdminControllerTest extends ApplicationTest {
    private LoginAdminController controller;

    @Before
    public void setUp(){
        controller = new LoginAdminController();
        controller.idField = new TextField();
        controller.passField = new PasswordField();
        controller.error = new Label();
    }

    @Test
    public void login(){
        Admin a = new Admin("name","user", AdminService.encodePassword("user"),"mail");
        AdminService.loadAdmins();
        AdminService.getA().add(a);

        controller.idField.setText("user");
        controller.passField.setText("user");

        controller.login();

        assertEquals("",controller.error.getText());
    }

    @Test
    public void loginIncorrect(){
        Admin a = new Admin("name","user", AdminService.encodePassword("user"),"mail");
        AdminService.loadAdmins();
        AdminService.getA().add(a);

        controller.idField.setText("user");
        controller.passField.setText("pass");

        controller.login();

        assertEquals("Username or password is incorrect!",controller.error.getText());
    }
}

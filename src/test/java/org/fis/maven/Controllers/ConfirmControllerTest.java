package org.fis.maven.Controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.fis.maven.Models.User;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

public class ConfirmControllerTest extends ApplicationTest {
    private ConfirmController controller;

    @Before
    public void setUp(){
        controller = new ConfirmController();
        controller.table = new TableView<>();
        controller.mailColumn = new TableColumn<>();
        controller.confirmedColumn = new TableColumn<>();
    }

    @Test
    public void confirm(){
        User u = new User("", "", "", "", "", 0, false, "");

        controller.table.getSelectionModel().select(u);

        controller.confirm();

        assertTrue(u.isConfirmed());
    }
}

package org.fis.maven.Controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.fis.maven.Models.Race;
import org.fis.maven.Models.User;
import org.fis.maven.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class MyRacesControllerTest extends ApplicationTest {
    private MyRacesController controller;

    @Before
    public void setUp(){
        controller = new MyRacesController();
        controller.table = new TableView<>();
        controller.clientColumn = new TableColumn<>();
        controller.priceColumn = new TableColumn<>();
        controller.kmColumn = new TableColumn<>();
        controller.statusColumn = new TableColumn<>();
    }

    @Test
    public void finish(){
        Race r = new Race("driver","user",0,10,"In Progress");
        controller.table.getSelectionModel().select(r);

        User u1 = new User("", "driver", "", "", "", 0, false, "");
        User u2 = new User("", "user", "", "", "", 0, false, "");

        UserService.loadUser();
        UserService.getU().add(u2);

        MyRacesController.setCurrent(u1);

        controller.finish();

        assertEquals("Done",r.getState());
        assertEquals(10,u1.getCredit());
        assertEquals(-10,u2.getCredit());
    }
}

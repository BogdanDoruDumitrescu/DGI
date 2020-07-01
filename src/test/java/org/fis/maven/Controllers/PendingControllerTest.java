package org.fis.maven.Controllers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.fis.maven.Models.Race;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class PendingControllerTest extends ApplicationTest {
    private PendingController controller;

    @Before
    public void setUp(){
        controller = new PendingController();
        controller.table = new TableView<>();
        controller.kmColumn = new TableColumn<>();
        controller.priceColumn = new TableColumn<>();
        controller.clientColumn = new TableColumn<>();
        controller.statusColumn = new TableColumn<>();
    }

    @Test
    public void accept(){
        Race r = new Race("driver","user",0,0,"sth");
        controller.table.getSelectionModel().select(r);

        controller.acceptButton();

        assertEquals("In Progress", r.getState());
    }
}

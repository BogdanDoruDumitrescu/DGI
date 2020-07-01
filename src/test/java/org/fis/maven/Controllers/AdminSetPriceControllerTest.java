package org.fis.maven.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.fis.maven.Models.Race;
import org.fis.maven.Services.RaceService;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class AdminSetPriceControllerTest extends ApplicationTest {
    private AdminSetPriceController controller;

    @Before
    public void setUp(){
        controller = new AdminSetPriceController();
        controller.priceField = new TextField();
        controller.error = new Label();

        Race.setPricePerKm(0);
    }

    @Test
    public void changePrice(){
        controller.priceField.setText("20");

        controller.setPrice();

        assertEquals(20, Race.getPricePerKm());
    }
}

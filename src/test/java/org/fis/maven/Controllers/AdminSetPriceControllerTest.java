package org.fis.maven.Controllers;

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
        Race.setPricePerKm(0);
    }

    @Test
    public void changePrice(){
        Race.setPricePerKm(20);
        assertEquals(20, Race.getPricePerKm());
    }
}

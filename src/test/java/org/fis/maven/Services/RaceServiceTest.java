package org.fis.maven.Services;

import org.fis.maven.Models.Race;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RaceServiceTest extends RaceService{
    @BeforeClass
    public static void setupClass(){
        RaceService.setPath("src/test/resources/Race.json");
        RaceService.loadRaces();
    }

    @Before
    public void setUp(){
        RaceService.loadRaces();
    }

    @Test
    public void loadWrite(){
        RaceService.getR().add(new Race("driver", "client", 0 ,0, "sth"));
        RaceService.writeRace();
        RaceService.getR().clear();
        RaceService.loadRaces();

        assertEquals(1,RaceService.getR().size());
    }

    @After
    public void clean(){
        RaceService.getR().clear();
        RaceService.writeRace();
    }
}

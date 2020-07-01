package org.fis.maven.Services;

import org.fis.maven.Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserServiceTest extends ApplicationTest {
    @BeforeClass
    public static void setupClass(){
        UserService.setPath("src/test/resources/Users.json");
        UserService.loadUser();
    }

    @Before
    public void setUp(){
        UserService.loadUser();
    }

    @Test
    public void loadWrite(){
        User u = new User("", "", "", "", "", 0, false, "");
        UserService.getU().add(u);
        UserService.writeUser();
        UserService.getU().clear();
        UserService.loadUser();

        assertEquals(1, UserService.getU().size());
    }

    @After
    public void clean(){
        UserService.getU().clear();
        UserService.writeUser();
    }

    @Test
    public void encode(){
        String res = UserService.encodePassword("sth");
        assertEquals(res, UserService.encodePassword("sth"));
    }

    @Test
    public void checkCredentials(){
        User u = new User("", "u", "u", "", "", 0, true, "");
        UserService.getU().add(u);

        assertTrue(UserService.checkCredentials("u","u"));

    }

    @After
    public void clean2(){
        UserService.getU().clear();
    }
}

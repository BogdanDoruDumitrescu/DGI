package org.fis.maven.Services;

import org.fis.maven.Models.Admin;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class AdminServiceTest extends ApplicationTest {
    @BeforeClass
    public static void setupClass(){
        AdminService.setPath("src/test/resources/Admins.json");
        AdminService.loadAdmins();
    }

    @Before
    public void setUp(){
        AdminService.loadAdmins();
    }

    @Test
    public void loadWrite(){
        Admin a = new Admin("name","username","password","mail");

        AdminService.getA().add(a);
        AdminService.writeAdmins();
        AdminService.getA().clear();
        AdminService.loadAdmins();

        assertEquals(1, AdminService.getA().size());
    }

    @After
    public void clean(){
        AdminService.getA().clear();
        AdminService.writeAdmins();
    }

    @Test
    public void encode(){
        String res = AdminService.encodePassword("sth");

        assertEquals(res, AdminService.encodePassword("sth"));
    }
}

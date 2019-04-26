/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Passbook;
import connect.DBConnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author minht
 */
public class PassbookDAOTest {

    public PassbookDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        DBConnect.getConnecttion().setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        DBConnect.getConnecttion().rollback();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void TestPassbookExisted() {
        PassbookDAO passbookDAO = new PassbookDAO();
        boolean found = passbookDAO.isPassbookExistedId(4);
        assertEquals(found, true);
    }

    @Test
    public void TestPassbookNotExisted() {
        PassbookDAO passbookDAO = new PassbookDAO();
        boolean found = passbookDAO.isPassbookExistedId(0);
        assertEquals(found, false);
    }

    @Test
    public void TestInsertPassbook() {
        PassbookDAO passbookDAO = new PassbookDAO();

        Passbook passbook = new Passbook(0, 1, 1, 1, Calendar.getInstance().getTime(), 1, 10000000, "OPEN");
        boolean inserted = passbookDAO.insertPassbook(passbook);
        assertEquals(inserted, true);
    }
    
    @Test
    public void TestGetPassbookById() {
        PassbookDAO passbookDAO = new PassbookDAO();
        Passbook passbook = passbookDAO.getPassbookById(4);
        assertEquals(passbook.getId(), 4);
    }
}

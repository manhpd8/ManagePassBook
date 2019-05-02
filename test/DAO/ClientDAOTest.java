/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.ClientDAO;
import Model.Client;
import connect.DBConnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phạm Đức Mạnh
 */
public class ClientDAOTest {
    
    public ClientDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    //3 testase
    @Test
    public void isClientExistedId(){
        ClientDAO clientDAO = new ClientDAO();
        assertEquals(clientDAO.isClientExistedId(1), true);
    }
    
    @Test
    public void isClientExistedIdCard(){
        ClientDAO clientDAO = new ClientDAO();
        assertEquals(clientDAO.isClientExisted("123456789"),true);
    }
    @Test
    public void insertUserTrue(){
        try {
            
            Calendar calendar = Calendar.getInstance();
            Connection con = DBConnect.getConnecttion();
            con.setAutoCommit(false);
            ClientDAO clientDAO = new ClientDAO();
            clientDAO.con = con;
            Client cl = new Client();
            cl.setAddress("adress");
            cl.setFirstname("test first name");
            cl.setGender("MALE");
            cl.setIdCard("23456");
            cl.setLastName("test last name");
            cl.setPhone("345676876");
            cl.setDateOfBirth(calendar.getTime());
            assertEquals(clientDAO.insertUser(cl),true);
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

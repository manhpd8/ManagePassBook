/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.PassbookType;
import connect.DBConnect;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author minht
 */
public class PassbookTypeDAOTest {
    
    public PassbookTypeDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass(){
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() throws SQLException {
       // DBConnect.getConnecttion().setAutoCommit(false);
    }
    
    @After
    public void tearDown() throws SQLException {
        //DBConnect.getConnecttion().rollback();
    }
    // 1 testcase
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestGetAllPassbookType() {
        PassbookTypeDAO passbookTypeDAO = new PassbookTypeDAO();
        List<PassbookType> list_passbook_type = passbookTypeDAO.getAllPassbookType();
        Assert.assertEquals(2, list_passbook_type.size());
    }
}

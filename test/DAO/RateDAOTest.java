/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Rate;
import connect.DBConnect;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author minht
 */
public class RateDAOTest {
    
    public RateDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        //DBConnect.getConnecttion().setAutoCommit(false);
    }
    
    @After
    public void tearDown() throws SQLException {
        //DBConnect.getConnecttion().rollback();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestRateExistedId() {
        RateDAO rateDAO = new RateDAO();
        assertEquals(rateDAO.isRateExistedId(1),true);
        assertEquals(rateDAO.isRateExistedId(2),true);
        assertEquals(rateDAO.isRateExistedId(3),true);
        assertEquals(rateDAO.isRateExistedId(4),true);
        assertEquals(rateDAO.isRateExistedId(5),true);
        assertEquals(rateDAO.isRateExistedId(6),true);
        assertEquals(rateDAO.isRateExistedId(7),true);
        assertEquals(rateDAO.isRateExistedId(8),true);
        assertEquals(rateDAO.isRateExistedId(9),true);
        assertEquals(rateDAO.isRateExistedId(10),true);
        
    }
    
    @Test
    public void TestRateNotExistedId() {
        RateDAO rateDAO = new RateDAO();
        assertEquals(rateDAO.isRateExistedId(11),false);
        assertEquals(rateDAO.isRateExistedId(0),false);
    }
    
    @Test
    public void TestRateByPeriod() {
        RateDAO rateDAO = new RateDAO();
        Rate rate = rateDAO.getRateByPeriod(0);
        Rate actual = new Rate(1,"KKH",0.2,0);
        assertEquals(rate.getId(), actual.getId());
        assertEquals(rate.getCode(),actual.getCode());
        assertEquals(rate.getPeriod(), actual.getPeriod());
        assertEquals(rate.getRate(),actual.getRate(),0.01);
    }
    
    @Test
    public void TestGetAllRate() {
        RateDAO rateDAO = new RateDAO();
        assertEquals(rateDAO.getAllRate().size(),10);
    }
    
    
}

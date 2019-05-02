/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.AccountDAO;
import Model.Account;
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
public class accountDAOTest {
    
    public accountDAOTest() {
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
    //5 testcase
    @Test
    public void checkLoginTrue(){
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setPassWord("admin");
        account.setUserName("admin");
        assertTrue(accountDAO.checkLogin(account)!= null);
    }
    
    @Test
    public void checkLoginWrongPass(){
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setPassWord("admin");
        account.setUserName("sreg");
        assertFalse(accountDAO.checkLogin(account)!= null);
    }
    
    @Test
    public void checkLoginWrongUsername(){
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setPassWord("rrerqreg");
        account.setUserName("admin");
        assertFalse(accountDAO.checkLogin(account)!= null);
    }
    
    @Test
    public void checkLoginNoUsername(){
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setPassWord("");
        account.setUserName("sreg");
        assertFalse(accountDAO.checkLogin(account)!= null);
    
    }
    
    @Test
    public void checkLoginNOPass(){
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setPassWord("admin");
        account.setUserName("");
        assertFalse(accountDAO.checkLogin(account)!= null);
    }
    
}

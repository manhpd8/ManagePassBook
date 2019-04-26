/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.PassbookController;
import Model.PassbookType;
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
public class PassbookControllerTest {
    
    public PassbookControllerTest() {
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
    
    @Test
    public void TestValidateMinAmountRequired1() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_TRUOC, 999000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired2() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_TRUOC, 1000000),true);
    }
    
    @Test
    public void TestValidateMinAmountRequired3() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_SAU, 999000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired4() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_SAU, 1000000),true);
    }
    
    @Test
    public void TestValidateMinMoneyRequire51() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_LINH_HOAT, 999000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired6() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_LINH_HOAT, 1000000),true);
    }
    
    @Test
    public void TestValidateMinAmountRequired7() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.CO_KY_HAN_LAI_SUAT_THA_NOI, 999000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired8() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.CO_KY_HAN_LAI_SUAT_THA_NOI, 1000000),true);
    }
    @Test
    public void TestValidateMinAmountRequired9() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.GUI_GOP_DINH_KY, 99000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired10() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.GUI_GOP_DINH_KY, 100000),true);
    }
    @Test
    public void TestValidateMinAmountRequired11() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_AN_SINH, 99000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired12() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_AN_SINH, 100000),true);
    }
    @Test
    public void TestValidateMinAmountRequired13() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_HOC_DUONG, 99000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired14() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_HOC_DUONG, 100000),true);
    }    
    
    @Test
    public void TestValidateMinAmountRequired15() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_HUU_TRI, 99000),false);
    }
    
    @Test
    public void TestValidateMinAmountRequired16() {
        PassbookController passbookController = new PassbookController();
        assertEquals(passbookController.validateMinAmountRequired(PassbookType.TIET_KIEM_HUU_TRI, 100000),true);
    }
}

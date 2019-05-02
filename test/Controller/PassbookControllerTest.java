/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.OpenPassbookController;
import Model.PassbookType;
import connect.DBConnect;
import java.sql.SQLException;
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
 * @author minht
 */
public class PassbookControllerTest {
    
    public PassbookControllerTest() {
    }
    OpenPassbookController controller;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        controller = new OpenPassbookController();
        try {
            DBConnect.getConnecttion().setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(PassbookControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            DBConnect.getConnecttion().rollback();
        } catch (SQLException ex) {
            Logger.getLogger(PassbookControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    // 16 testcase

    @Test
    public void TestValidateMaKH_Trong(){
        controller.view.getjTextMaKH().setText("");
        try {
            controller.validateMaKH();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Mã khách hàng không được để trống");
        }
    }
    
    
    @Test
    public void TestValidateMaKH_KhongPhaiSo(){
        controller.view.getjTextMaKH().setText("nhom7");
        try {
            controller.validateMaKH();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Mã khách hàng không phải là số");
        }
    }
    
    
    @Test
    public void TestValidateMaKH_KhongTonTai(){
        controller.view.getjTextMaKH().setText("5");
        try {
            controller.validateMaKH();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Mã khách hàng không tồn tại");
        }
    }
    
    @Test
    public void TestValidateMaKH_OK(){
        controller.view.getjTextMaKH().setText("1");
        try {
            int maKH = controller.validateMaKH();
            assertEquals(maKH, 1);
        } catch (Exception ex) {
            fail();
        }
    }
    
    @Test
    public void TestValidateSoTienGui_Trong(){
        controller.view.getjTextMoney().setText("");
        try {
            controller.validateSoTienGui();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Số tiền không được để trống");
        } 
    }
    
    @Test
    public void TestValidateSoTienGui_KhongHopLe(){
        controller.view.getjTextMoney().setText("-100000");
        try {
            controller.validateSoTienGui();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Số tiền nhập vào chứa ký tự không hợp lệ");
        } 
    }
    
    @Test
    public void TestValidateSoTienGui_KhongLaBoi1000(){
        controller.view.getjTextMoney().setText("1231213");
        try {
            controller.validateSoTienGui();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Số tiền phải là bội của 1000đ");
        } 
    }
    
    @Test
    public void TestValidateSoTienGui_ItHonYeuCau(){
        controller.view.getjTextMoney().setText("90000");
        PassbookType passbookType = (PassbookType)controller.view.getjCBPassbookType().getSelectedItem();
        try {
            int money = controller.validateSoTienGui();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Số tiền gửi phải lớn hơn hoặc bằng " +controller.getMinAmount(passbookType.getCode())+ "đ");
        } 
    }
    
    @Test
    public void TestValidateSoTienGui_OK(){
        controller.view.getjTextMoney().setText("1000000");
        PassbookType passbookType = (PassbookType)controller.view.getjCBPassbookType().getSelectedItem();
        try {
            int money = controller.validateSoTienGui();
            assertEquals(money, 1000000);
        } catch (Exception ex) {
            fail();
        } 
    }
    
    @Test 
    public void TestValidateOpenDate_LonHonThoiGianHienTai() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        controller.view.getjDateOpen().setDate(calendar.getTime());
        try {
            controller.validateOpenDate();
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"Ngày mở sổ không hợp lệ");
        } 
        
    }
    
    @Test
    public void TestValidateOpenDate_OK(){
        try {
            Calendar calendar = Calendar.getInstance();
            controller.view.getjDateOpen().setDate(calendar.getTime());
            controller.validateOpenDate();
            calendar.add(Calendar.DATE, -1);
            controller.view.getjDateOpen().setDate(calendar.getTime());
            controller.validateOpenDate();            
        } catch (Exception ex) {
            fail();
        } 
    }
    
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 999999
        Kiểu sổ: Có kỳ hạn tính lãi trước
    */
    @Test
    public void TestValidateMinAmountRequired1() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_TRUOC, 999999),false);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 1000000
        Kiểu sổ: Có kỳ hạn tính lãi trước
    */
    @Test
    public void TestValidateMinAmountRequired2() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_TRUOC, 1000000),true);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 999999
        Kiểu sổ: Có kỳ hạn tính lãi sau
    */
    @Test
    public void TestValidateMinAmountRequired3() {
        assertEquals(controller.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_SAU, 999999),false);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 1000000
        Kiểu sổ: Có kỳ hạn tính lãi sau
    */
    @Test
    public void TestValidateMinAmountRequired4() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.CO_KY_HAN_TINH_LAI_SAU, 1000000),true);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 999999
        Kiểu sổ: Tiết kiệm linh hoạt
    */
    @Test
    public void TestValidateMinMoneyRequire51() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_LINH_HOAT, 999999),false);
    }
        /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 1000000
        Kiểu sổ: Tiết kiệm linh hoạt
    */
    @Test
    public void TestValidateMinAmountRequired6() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_LINH_HOAT, 1000000),true);
    }
    
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 999999
        Kiểu sổ: Có kỳ hạn lãi suất thả nổi
    */
    @Test
    public void TestValidateMinAmountRequired7() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.CO_KY_HAN_LAI_SUAT_THA_NOI, 999999),false);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 1000000
        Kiểu sổ: Có kỳ hạn lãi suất thả nổi
    */
    @Test
    public void TestValidateMinAmountRequired8() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.CO_KY_HAN_LAI_SUAT_THA_NOI, 1000000),true);
    }
    
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 99999
        Kiểu sổ: Gửi góp định kỳ
    */
    @Test
    public void TestValidateMinAmountRequired9() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.GUI_GOP_DINH_KY, 99999),false);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 100000
        Kiểu sổ: Gửi góp định kỳ
    */
    @Test
    public void TestValidateMinAmountRequired10() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.GUI_GOP_DINH_KY, 100000),true);
    }
    
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 99999
        Kiểu sổ: Tiết kiệm an sinh
    */    
    @Test
    public void TestValidateMinAmountRequired11() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_AN_SINH, 99999),false);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 100000
        Kiểu sổ: Tiết kiệm an sinh
    */  
    @Test
    public void TestValidateMinAmountRequired12() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_AN_SINH, 100000),true);
    }
    
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 99999
        Kiểu sổ: Tiết kiệm học đường
    */
    @Test
    public void TestValidateMinAmountRequired13() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_HOC_DUONG, 99999),false);
    }
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 100000
        Kiểu sổ: Tiết kiệm học đường
    */
    @Test
    public void TestValidateMinAmountRequired14() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_HOC_DUONG, 100000),true);
    }    
    
    /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 99999
        Kiểu sổ: Tiết kiệm hưu trí
    */
    @Test
    public void TestValidateMinAmountRequired15() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_HUU_TRI, 99999),false);
    }
        /*
        Kiểm tra số tiền tối thiểu yêu cầu với đầu vào
        Số tiền: 100000
        Kiểu sổ: Tiết kiệm hưu trí
    */
    @Test
    public void TestValidateMinAmountRequired16() {
        
        assertEquals(controller.validateMinAmountRequired(PassbookType.TIET_KIEM_HUU_TRI, 100000),true);
    }
}

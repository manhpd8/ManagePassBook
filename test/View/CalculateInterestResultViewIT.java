/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DTO.PassBookSearch;
import Utility.StringUtility;
import java.sql.Timestamp;
import java.util.Calendar;
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
public class CalculateInterestResultViewIT {
    
    public CalculateInterestResultViewIT() {
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

    /**
     * Test of loadData method, of class CalculateInterestResultView.
     */


    /**
     * Test of TinhLai method, of class CalculateInterestResultView.
     */
    public void testTinhLai() {
        
//        PassBookSearch pbs = new PassBookSearch();
//        pbs.setGoc(1000000);
//        pbs.setKyhan(12);
//        pbs.setLaiXuat(6.8);
//        pbs.setLoaiSo("CKH");
//        Calendar cal=Calendar.getInstance();
//        cal.add(Calendar.MONTH, -12);
//        Timestamp time=new Timestamp(cal.getTimeInMillis());
//        pbs.setNgayMo(time);
//        
//        CalculateInterestResultView instance = new CalculateInterestResultView();
//        double expResult = 68029;
//        double result = instance.TinhLai(pbs);
//        assertEquals(expResult, result,1);
//        
//        System.out.println("Không kỳ hạn 12 tháng, lãi xuất 0.2%/năm(tính làm 360 ngày) , 1 năm 360 ngày, tg gửi đc 12 tháng =365 ngày, tính 365 ngày không kỳ hạn với gốc 10m");
//        pbs.setLoaiSo("KKH");
//        expResult = pbs.getGoc()*((double)Utility.StringUtility.LAI_XUAT_KKH/100)*((double) pbs.getSoNgayTinhDenHienTai()/360);
//        result = instance.TinhLai(pbs);
//        assertEquals(expResult, result,1);
//        
//        System.out.println("Gốc nhỏ hơn 0, thời gian gửi đc 12 tháng");
//        pbs.setGoc(0);
//        expResult=0;
//        result = instance.TinhLai(pbs);
//        assertEquals(expResult, result,1);
//        
//        System.out.println("Có kỳ hạn 12 tháng, lãi xuất ");
        
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    @Test
    public void testGocBiAm(){
        System.out.println("Gốc nhỏ hơn 0, thời gian gửi đc 12 tháng");
        PassBookSearch pbs = new PassBookSearch();
        pbs.setGoc(-1);
        pbs.setKyhan(12);
        pbs.setLaiXuat(6.8);
        pbs.setLoaiSo("CKH");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -12);
        Timestamp time=new Timestamp(cal.getTimeInMillis());
        pbs.setNgayMo(time);
        
        CalculateInterestResultView instance = new CalculateInterestResultView();
        double expResult = 0;
        double result = instance.TinhLai(pbs);
        assertEquals(expResult, result,1);
    }
    @Test
    public void testKhongKyHan(){
        System.out.println("Không kỳ hạn 12 tháng, lãi xuất 0.2%/năm(tính làm 360 ngày) , 1 năm 360 ngày, tg gửi đc 12 tháng =365 ngày, tính 365 ngày không kỳ hạn với gốc 10m");
        PassBookSearch pbs = new PassBookSearch();
        pbs.setGoc(10000000);
        pbs.setKyhan(12);
        pbs.setLaiXuat(6.8);
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -12);
        Timestamp time=new Timestamp(cal.getTimeInMillis());
        pbs.setNgayMo(time);
        
        CalculateInterestResultView instance = new CalculateInterestResultView();
        
        pbs.setLoaiSo("KKH");
        double expResult = pbs.getGoc()*((double)Utility.StringUtility.LAI_XUAT_KKH/100)*((double) pbs.getSoNgayTinhDenHienTai()/360);
        double result = instance.TinhLai(pbs);
        assertEquals(expResult, result,1);
    }
    @Test
    public void testCoKyHanBangKyHan(){
        System.out.println("Có kỳ hạn 12 tháng. lãi trùng với kỳ hạn, lai xuat 6.8%/nam , 1 năm 360 ngày ");
        PassBookSearch pbs = new PassBookSearch();
        pbs.setGoc(10000000);
        pbs.setKyhan(12);
        pbs.setLaiXuat(6.8);
        pbs.setLoaiSo("CKH");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -12);
        cal.add(Calendar.DATE, +5);
        Timestamp time=new Timestamp(cal.getTimeInMillis());
        pbs.setNgayMo(time);
        
        CalculateInterestResultView instance = new CalculateInterestResultView();
        double expResult = ((double) pbs.getGoc()) * ((double) pbs.getLaiXuat() / 100);
        double result = instance.TinhLai(pbs);
        assertEquals(expResult, result,1);
    }
    @Test
    public void testCoKyHanQuaHan(){
         System.out.println("Có kỳ hạn 12 tháng. lãi trùng với kỳ hạn, lai xuat 6.8%/nam , 1 năm 360 ngày => tính với tg 12 tháng thì là 365 ngày => dư ra 5 ngày tính lãi kkh");
        PassBookSearch pbs = new PassBookSearch();
        pbs.setGoc(-1);
        pbs.setKyhan(12);
        pbs.setLaiXuat(6.8);
        pbs.setLoaiSo("CKH");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -12);
        Timestamp time=new Timestamp(cal.getTimeInMillis());
        pbs.setNgayMo(time);
        
        CalculateInterestResultView instance = new CalculateInterestResultView();
         double laidaohan = ((double) pbs.getGoc()) * ((double) pbs.getLaiXuat() / 100);
         double laiquahan = ((double) pbs.getGoc() + laidaohan) * (StringUtility.LAI_XUAT_KKH  / 100) * ((double) (pbs.getSoNgayTinhDenHienTai()- pbs.getKyhan() * 30) / 360);
        
        double expResult = laidaohan+laiquahan;
        double result = instance.TinhLai(pbs);
        assertEquals(expResult, result,1);
    }
    @Test
    public void testCoKyHanTruocHan(){
        System.out.println("Có kỳ hạn 12 tháng. lãi tính trước kỳ hạn 1 tháng, lai xuat se bi chuyen ve KKH là 0.2%/năm/360ngay");
        PassBookSearch pbs = new PassBookSearch();
        pbs.setGoc(10000000);
        pbs.setKyhan(12);
        pbs.setLaiXuat(6.8);
        pbs.setLoaiSo("CKH");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, -11);
        Timestamp time=new Timestamp(cal.getTimeInMillis());
        pbs.setNgayMo(time);
        
        CalculateInterestResultView instance = new CalculateInterestResultView();
        double expResult = pbs.getGoc()*((double)Utility.StringUtility.LAI_XUAT_KKH/100)*((double) pbs.getSoNgayTinhDenHienTai()/360);
        double result = instance.TinhLai(pbs);
        assertEquals(expResult, result,1);
    }
    
    

    /**
     * Test of main method, of class CalculateInterestResultView.
     */

    
}
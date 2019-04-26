/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PassbookDAO;
import Model.Passbook;
import Model.PassbookType;

/**
 *
 * @author minht
 */
public class PassbookController {
    PassbookDAO passbookDAO;

    public PassbookController() {
        passbookDAO = new PassbookDAO();
    }
    
    public boolean insertPassbook(Passbook passbook) {
        if(passbook == null) return false;
        return passbookDAO.insertPassbook(passbook);
    }
    
    public boolean validateMinAmountRequired(String passbookTypeCode,int amount){
        return amount >= getMinAmount(passbookTypeCode);
    }
    
    public int getMinAmount(String passbookTypeCode) {
         if(passbookTypeCode.equals(PassbookType.CO_KY_HAN_TINH_LAI_SAU)) {
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.CO_KY_HAN_TINH_LAI_TRUOC)){
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_LINH_HOAT)) {
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.CO_KY_HAN_LAI_SUAT_THA_NOI)) {
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.GUI_GOP_DINH_KY)){
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_AN_SINH)) {
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_HOC_DUONG)) {
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_HUU_TRI)) {
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.KHONG_KY_HAN)) {
            return 1000000;
        }
        return 0;
    }
}

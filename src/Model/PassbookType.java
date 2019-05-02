/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author minht
 */
public class PassbookType {
    public static final String KHONG_KY_HAN = "KKH";
    public static final String CO_KY_HAN_TINH_LAI_SAU = "CKH";
    public static final String CO_KY_HAN_TINH_LAI_TRUOC="CKHTLT";
    public static final String TIET_KIEM_LINH_HOAT = "TKLH";
    public static final String CO_KY_HAN_LAI_SUAT_THA_NOI ="CKHTN";
    public static final String GUI_GOP_DINH_KY ="GGDK";
    public static final String TIET_KIEM_AN_SINH ="TKAS";
    public static final String TIET_KIEM_HOC_DUONG ="TKHD";
    public static final String TIET_KIEM_HUU_TRI ="TKHT";
    
    int id;
    String code;
    String name;

    public PassbookType(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}

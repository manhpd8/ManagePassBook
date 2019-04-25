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
public class Period {
    public static final int KKH = 0;
    public static final int CKH_1T = 1;
    public static final int CKH_2T = 2;
    public static final int CKH_3T = 3;
    public static final int CKH_6T = 6;
    public static final int CKH_9T = 9;
    public static final int CKH_12T = 12;
    public static final int CKH_13T = 13;
    public static final int CKH_18T = 18;
    public static final int CKH_24T = 24;
    
    int period;

    public Period(int period) {
        this.period = period;
    }
    
    public String toString() {
        if(period < 0) return "";
        else if(period == 0) {
            return "Không kỳ hạn";
        }
        if(period % 12 ==0) {
            return period/12 + " năm";
        }
        return period + " tháng";
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    
}

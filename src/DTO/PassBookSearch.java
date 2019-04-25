/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author cogia_grk2dkj
 */
public class PassBookSearch implements Serializable{
    int SoTaiKhoan,kyhan,MaSOTietKiem;
    double Goc,LaiXuat;
    String CMTND,LoaiSo;
    Timestamp NgayMo;

   

    
    
    public PassBookSearch() {
        
    }

    public int getSoTaiKhoan() {
        return SoTaiKhoan;
    }

    public void setSoTaiKhoan(int SoTaiKhoan) {
        this.SoTaiKhoan = SoTaiKhoan;
    }

    public int getKyhan() {
        return kyhan;
    }

    public void setKyhan(int kyhan) {
        this.kyhan = kyhan;
    }

    public int getMaSOTietKiem() {
        return MaSOTietKiem;
    }

    public void setMaSOTietKiem(int MaSOTietKiem) {
        this.MaSOTietKiem = MaSOTietKiem;
    }

    public double getGoc() {
        return Goc;
    }

    public void setGoc(double Goc) {
        this.Goc = Goc;
    }

    public double getLaiXuat() {
        return LaiXuat;
    }

    public void setLaiXuat(double LaiXuat) {
        this.LaiXuat = LaiXuat;
    }

    public String getCMTND() {
        return CMTND;
    }

    public void setCMTND(String CMTND) {
        this.CMTND = CMTND;
    }

    public String getLoaiSo() {
        return LoaiSo;
    }

    public void setLoaiSo(String LoaiSo) {
        this.LoaiSo = LoaiSo;
    }

    public Timestamp getNgayMo() {
        return NgayMo;
    }

    public void setNgayMo(Timestamp NgayMo) {
        this.NgayMo = NgayMo;
    }

    

    
}

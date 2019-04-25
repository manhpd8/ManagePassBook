/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Account;
import DTO.PassBookSearch;
import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cogia_grk2dkj
 */
public class CalculateInterestDAO {
    private String querySearch="SELECT main.*,sub.idCard as CMTND, sub.id as STK,sub2.rate as LaiXuat, sub2.code_passbook_type as Loai, sub2.period_month as KyHan FROM manage_passbook.passbook main,client sub , interest_rate sub2 \n" +
" where sub.id=main.id_client and sub2.id=main.id_interest_rate and main.id_client=?;";
    private String querySeachCMTND="SELECT main.*,sub.idCard as CMTND, sub.id as STK,sub2.rate as LaiXuat, sub2.code_passbook_type as Loai, sub2.period_month as KyHan FROM manage_passbook.passbook main,client sub , interest_rate sub2 \n" +
" where sub.id=main.id_client and sub2.id=main.id_interest_rate and sub.idCard=?;";
   public ArrayList<PassBookSearch> search(String key,int TypeSearch){
    ArrayList<PassBookSearch> list=new ArrayList<>();
    // 0 : theo Số Tài khoản, 1: theo CMND.
       System.out.println("key:"+key+" , TypeSearch:"+TypeSearch);
       try {
           Connection conn=DBConnect.getConnecttion();
            PreparedStatement ps = null;
            if(TypeSearch==1) ps = (PreparedStatement) conn.prepareStatement(querySeachCMTND);
            if(TypeSearch==0) ps = (PreparedStatement) conn.prepareStatement(querySearch);
            ps.setInt(1, Integer.parseInt(key));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("have one");
                PassBookSearch pbs=new PassBookSearch();
                pbs.setCMTND(rs.getString("CMTND"));
                pbs.setGoc(rs.getDouble("money_value"));
                pbs.setKyhan(rs.getInt("KyHan"));
                pbs.setLaiXuat(rs.getDouble("LaiXuat"));
                pbs.setNgayMo(rs.getTimestamp("start_date"));
                pbs.setSoTaiKhoan(rs.getInt("id_client"));
                pbs.setMaSOTietKiem(rs.getInt("id"));
                pbs.setLoaiSo(rs.getString("Loai"));
                list.add(pbs);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    System.out.println("length: "+list.size());
    return list;
   }
}

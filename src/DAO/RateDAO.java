/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Rate;
import View.OpenPassbookView;
import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minht
 */
public class RateDAO {
    public boolean isRateExistedId(int id){
        try {
            Connection con = DBConnect.getConnecttion();
            String sql = "SELECT count(*) FROM manage_passbook.interest_rate where id= '" + id +"'";
            PreparedStatement ps;
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int amount = rs.getInt(1);
                if(amount >0) return true;
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
   
    public Rate getRateByPeriod(int period) {
        try {
            Connection connect = DBConnect.getConnecttion();
            String sql = "SELECT * FROM interest_rate WHERE period_month = '" + period +"'";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                return new Rate(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenPassbookView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Rate> getAllRate() {
        List<Rate> list_rate = new ArrayList<>();
        try {
            Connection connect = DBConnect.getConnecttion();
            String sql = "SELECT * FROM interest_rate";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                list_rate.add(new Rate(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenPassbookView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_rate;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Client;
import Model.Passbook;
import connect.DBConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minht
 */
public class PassbookDAO {
    public boolean isPassbookExistedId(int id){
        try {
            Connection con = DBConnect.getConnecttion();
            String sql = "SELECT count(*) FROM manage_passbook.passbook where id= '" + id +"'";
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
   
    
    public boolean insertPassbook(Passbook passbook) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "INSERT INTO `manage_passbook`.`passbook` (`id_client`, `id_interest_rate`, `id_passbook_type`, `start_date`, `id_staff`, `money_value`, `status`) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setInt(1, passbook.getId_client());
            ps.setInt(2, passbook.getId_interest_rate());
            ps.setInt(3, passbook.getId_passbook_type());
            ps.setDate(4, new Date(passbook.getStart_date().getTime()));
            ps.setInt(5, passbook.getId_staff());
            ps.setDouble(6, passbook.getMoney_value());
            ps.setString(7, passbook.getStatus());
            int count = ps.executeUpdate();
            if(count == 0) return false;
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Passbook getPassbookById(int id) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT `id_client`, `id_interest_rate`, `id_passbook_type`, `start_date`, `id_staff`, `money_value`, `status`"
                     + " FROM manage_passbook.passbook"
                     + " WHERE id= '" + id +"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                int id_client = rs.getInt("id_client");
                int id_interest_rate = rs.getInt("id_interest_rate");
                int id_passbook_type = rs.getInt("id_passbook_type");
                java.util.Date date = new java.util.Date(rs.getDate("start_date").getTime());
                int id_staff = rs.getInt("id_staff");
                double money_value = rs.getDouble("money_value");
                String status = rs.getString("status");
                return new Passbook(id,id_client,id_interest_rate,id_passbook_type,date,id_staff,money_value,status);
            }
            else {
                return null;
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

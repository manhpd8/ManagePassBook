/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Client;
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
 * @author Phạm Đức Mạnh
 */
public class ClientDAO {
    
        public boolean isClientExistedId(int id){
        try {
            Connection con = DBConnect.getConnecttion();
            String sql = "SELECT count(*) FROM manage_passbook.client where id= '" + id +"'";
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
    
    public boolean isClientExisted(String idCard){
        try {
            Connection con = DBConnect.getConnecttion();
            String sql = "SELECT count(*) FROM manage_passbook.client where idCard = '" + idCard +"'";
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
    
    public boolean insertUser(Client u) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "INSERT INTO `manage_passbook`.`client` (`firstName`, `lastname`, `dateOfBirth`, `gender`, `idCard`, `phone`, `address`) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, u.getFirstname());
            ps.setString(2, u.getLastName());
            ps.setDate(3, new Date(u.getDateOfBirth().getTime()));
            ps.setString(4, u.getGender());
            ps.setString(5, u.getIdCard());
            ps.setString(6, u.getPhone());
            ps.setString(7, u.getAddress());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Client getClientById(int id) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT `firstName`, `lastname`, `dateOfBirth`, `gender`, `idCard`, `phone`, `address`"
                     + " FROM manage_passbook.client"
                     + " WHERE id= '" + id +"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastname");
                java.util.Date date = new java.util.Date(rs.getDate("dateOfBirth").getTime());
                String gender = rs.getString("gender");
                String idCard = rs.getString("idCard");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                return new Client(id,firstName,lastName,date,gender,idCard,phone,address);
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

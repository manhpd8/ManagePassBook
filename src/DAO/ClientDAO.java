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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phạm Đức Mạnh
 */
public class ClientDAO {
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
            ps.setDate(3, new Date(u.getDateOfBirth().getYear(), u.getDateOfBirth().getMonth(), u.getDateOfBirth().getDay()));
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
}

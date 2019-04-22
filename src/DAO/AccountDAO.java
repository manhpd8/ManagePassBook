/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Account;
import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class AccountDAO {

    public AccountDAO() {
    }
    
    public Account checkLogin(Account acc){
        Connection con = DBConnect.getConnecttion();
        String sql = "select * from account where userName='" + acc.getUserName() + "' and passWord='" + acc.getPassWord() + "'";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account accRe = new Account();
                accRe.setId(rs.getInt("id"));
                accRe.setUserName(rs.getString("userName"));
                accRe.setPassWord(rs.getString("passWord"));
                con.close();
                return accRe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

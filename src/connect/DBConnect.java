/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author NguyenDang
 */
public class DBConnect {
    private static Connection cons = null;
    public static Connection getConnecttion() {
        if(cons == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cons = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/manage_passbook", "root", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cons;
    }
 
    public static void main(String[] args) {
        System.out.println(getConnecttion());
    }
    
}

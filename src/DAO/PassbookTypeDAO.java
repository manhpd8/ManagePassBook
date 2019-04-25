/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.PassbookType;
import Model.Rate;
import View.OpenPassbookView;
import connect.DBConnect;
import java.sql.Connection;
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
public class PassbookTypeDAO {
        public List<PassbookType> getAllPassbookType() {
        List<PassbookType> list_passbook_type = new ArrayList<>();
        try {
            Connection connect = DBConnect.getConnecttion();
            String sql = "SELECT * FROM passbook_type";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String code = resultSet.getString(2);
                String name = resultSet.getString(3);
                PassbookType type = new PassbookType(id, code, name);
                list_passbook_type.add(type);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OpenPassbookView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_passbook_type;
    }
}

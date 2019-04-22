/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import Model.Account;
import View.LoginView;

/**
 *
 * @author Phạm Đức Mạnh
 */
public class accountCotroller {
    private Account account;
    private AccountDAO accountDAO;
    public accountCotroller() {
        accountDAO = new AccountDAO();
    }
    
    public Account checkLogin(Account acc){
        if(acc != null){
            return accountDAO.checkLogin(acc);
        }
        return null;
    }
    
}

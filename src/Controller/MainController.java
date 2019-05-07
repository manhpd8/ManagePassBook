/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.LoginView;

/**
 *
 * @author Phạm Đức Mạnh
 */
public class MainController {
    static accountCotroller loginCotroller;
    public static void main(String[] args) {
        System.out.println("dfd");
        //loginCotroller = new accountCotroller();
        LoginView login= new LoginView();
        login.setTitle("Đăng nhập");
        login.setVisible(true);
    }
}

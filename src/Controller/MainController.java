/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.CPanel;

/**
 *
 * @author Phạm Đức Mạnh
 */
public class MainController {
    static accountCotroller loginCotroller;
    public static void main(String[] args) {
        System.out.println("dfd");
        //loginCotroller = new accountCotroller();
        CPanel cPanel= new CPanel();
        cPanel.setTitle("Hệ thống quản lý sổ tiết kiệm");
        cPanel.setVisible(true);
    }
}

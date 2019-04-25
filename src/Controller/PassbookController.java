/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PassbookDAO;
import Model.Passbook;

/**
 *
 * @author minht
 */
public class PassbookController {
    PassbookDAO passbookDAO;

    public PassbookController() {
        passbookDAO = new PassbookDAO();
    }
    
    public boolean insertPassbook(Passbook passbook) {
        if(passbook == null) return false;
        return passbookDAO.insertPassbook(passbook);
    }
    
}

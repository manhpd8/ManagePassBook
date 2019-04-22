/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClientDAO;
import Model.Client;
import Utility.StringUtility;

/**
 *
 * @author Phạm Đức Mạnh
 */
public class ClientController {
    private Client client;
    private ClientDAO clientDAO;
    
    public boolean addClient(Client cl){
        try {
            if(cl != null && !StringUtility.NullOrEmpty(cl.getIdCard()) && !clientDAO.isClientExisted(cl.getIdCard())){
                clientDAO.insertUser(cl);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public ClientController() {
        clientDAO = new ClientDAO();
    }
}

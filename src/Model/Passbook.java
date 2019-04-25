/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author minht
 */
public class Passbook {
    private int id;
    private int id_client;
    private int id_interest_rate;
    private int id_passbook_type;
    private Date start_date;
    private int id_staff;
    private double money_value;
    private String status;

    public Passbook() {
    }

    public Passbook(int id, int id_client, int id_interest_rate, int id_passbook_type, Date start_date, int id_staff, double money_value, String status) {
        this.id = id;
        this.id_client = id_client;
        this.id_interest_rate = id_interest_rate;
        this.id_passbook_type = id_passbook_type;
        this.start_date = start_date;
        this.id_staff = id_staff;
        this.money_value = money_value;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_interest_rate() {
        return id_interest_rate;
    }

    public void setId_interest_rate(int id_interest_rate) {
        this.id_interest_rate = id_interest_rate;
    }

    public int getId_passbook_type() {
        return id_passbook_type;
    }

    public void setId_passbook_type(int id_passbook_type) {
        this.id_passbook_type = id_passbook_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getId_staff() {
        return id_staff;
    }

    public void setId_staff(int id_staff) {
        this.id_staff = id_staff;
    }

    public double getMoney_value() {
        return money_value;
    }

    public void setMoney_value(double money_value) {
        this.money_value = money_value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

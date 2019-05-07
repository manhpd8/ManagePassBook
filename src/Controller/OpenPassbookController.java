/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClientDAO;
import DAO.PassbookDAO;
import DAO.PassbookTypeDAO;
import DAO.RateDAO;
import Model.Client;
import Model.Passbook;
import Model.PassbookType;
import Model.Period;
import Model.Rate;
import Utility.StringUtility;
import View.CPanel;
import View.OpenPassbookView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author minht
 */
public class OpenPassbookController {
    
    OpenPassbookView view;
    PassbookDAO passbookDAO;
    ClientDAO clientDAO;
    RateDAO rateDAO;
    PassbookTypeDAO passbookTypeDAO;
    private CPanel cPanel;

    public OpenPassbookController() {
        initObject();
        initView();
    }
    public void show() {
        if(view != null)
            view.setVisible(true);
        if(cPanel != null)
            cPanel.setVisible(false);
    }
    private void initObject() {
        passbookDAO = new PassbookDAO();
        clientDAO = new ClientDAO();
        rateDAO = new RateDAO();
        passbookTypeDAO = new PassbookTypeDAO();
    }
    private void initView() {
        view = new OpenPassbookView();
        if(view != null) {
            view.setTitle("Mở sổ tiết kiệm");
            loadDefaultViewValues();
            addEvents();
        }
    }
    private void loadDefaultViewValues() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(StringUtility.DATE_FORMAT);
        String currentDate = sdf.format(calendar.getTime());
        view.getjTextCreatedDate().setText(currentDate);
        view.getjTextDateInput().setText(currentDate);
        view.getjTextRate().setEditable(false);
        view.getjDateOpen().setDate(calendar.getTime());
        loadPassbookType();
        loadPeriod();
        updateRate();
        updatePeriodDate();
        refreshViewValues();
    }
    
    private void addEvents() {
        view.getBtnSave().addActionListener(e->btnSaveActionPerformed(e));
        view.getBtnBack().addActionListener(e->btnBackActionPerformed(e));
        view.getjTextMoney().addActionListener(e->jTextMoneyActionPerformed(e));
        view.getjBtnTimKH().addActionListener(e->jBtnTimKHActionPerformed(e));
        view.getjCBPassbookType().addActionListener(e->jCBPassbookTypeActionPerformed(e));
        view.getjCBPeriod().addActionListener(e->jCBPeriodActionPerformed(null));
    }
    
    public void setCPanel(CPanel cPanel) {
        this.cPanel = cPanel;
    }
    
    public boolean insertPassbook(Passbook passbook) {
        if(passbook == null) return false;
        return passbookDAO.insertPassbook(passbook);
    }
    
    public boolean validateMinAmountRequired(String passbookTypeCode,int amount){
        return amount >= getMinAmount(passbookTypeCode);
    }
    
    public int getMinAmount(String passbookTypeCode) {
         if(passbookTypeCode.equals(PassbookType.CO_KY_HAN_TINH_LAI_SAU)) {
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.CO_KY_HAN_TINH_LAI_TRUOC)){
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_LINH_HOAT)) {
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.CO_KY_HAN_LAI_SUAT_THA_NOI)) {
            return 1000000;
        }
        if(passbookTypeCode.equals(PassbookType.GUI_GOP_DINH_KY)){
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_AN_SINH)) {
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_HOC_DUONG)) {
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.TIET_KIEM_HUU_TRI)) {
            return 100000;
        }
        if(passbookTypeCode.equals(PassbookType.KHONG_KY_HAN)) {
            return 1000000;
        }
        return 0;
    }


    
    private void refreshViewValues() {
        view.getjTextMaKH().setText("");
        view.getjTextMoney().setText("");
        view.getjTextPeriodDate().setText(StringUtility.DATE_FORMAT);
        view.getjLabelCustomerName().setText("");
        view.getjCBPassbookType().setSelectedIndex(0);
        view.getjCBPeriod().setSelectedIndex(0);
        view.getjCBPeriod().setEnabled(false);
        view.getjDateOpen().setDate(Calendar.getInstance().getTime());
    }
    private void loadPassbookType() {
        List<PassbookType> passbook_types = passbookTypeDAO.getAllPassbookType();
        for(PassbookType type : passbook_types) {
             view.getjCBPassbookType().addItem(type);
        }  
    }

    private void loadPeriod() {
        List<Rate> list_rate = rateDAO.getAllRate();
        for(Rate rate : list_rate) {
            view.getjCBPeriod().addItem(new Period(rate.getPeriod()));
        }
    }
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        try {
            // Kiểm tra hợp lệ các trường dữ liệu
            view.getBtnSave().setEnabled(false);
            int maKH = validateMaKH();
            int soTien = validateSoTienGui();
            validateKiHan();
            validateOpenDate();
            // Lấy thông tin passbook        
            PassbookType passbookType = (PassbookType) view.getjCBPassbookType().getSelectedItem();
            Period period = (Period) view.getjCBPeriod().getSelectedItem();
            Rate rate = rateDAO.getRateByPeriod(period.getPeriod());
            Passbook passbook = new Passbook();
            passbook.setId_staff(1);
            passbook.setStart_date(view.getjDateOpen().getDate());
            passbook.setId_passbook_type(passbookType.getId());
            passbook.setId_interest_rate(rate.getId());
            passbook.setMoney_value(soTien);
            passbook.setStatus("OPEN");
            passbook.setId_client(maKH);
            if(this.insertPassbook(passbook) ) {
                JOptionPane.showMessageDialog(view, "Mở sổ thành công");
                refreshViewValues();
            }
            else {
                JOptionPane.showMessageDialog(view, "Mở sổ không thành công");
            }
        } catch (Exception ex) {
            //Logger.getLogger(OpenPassbookView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi!", JOptionPane.ERROR_MESSAGE);
        }
        view.getBtnSave().setEnabled(true);
    }                                       

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        view.setVisible(false);
        if(cPanel != null) cPanel.setVisible(true);

    }                                       

    private void jTextMoneyActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jBtnTimKHActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        view.getjLabelCustomerName().setText("");

        try {
            // kiểm tra trường mã khách hàng khi nhập
            int maKH = validateMaKH();
            Client client = clientDAO.getClientById(maKH);
            String tenKH = client.getFirstname().concat(" ").concat(client.getLastName()).trim();
            // cập nhập thông tin
            view.getjLabelCustomerName().setText(tenKH);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi!", JOptionPane.ERROR_MESSAGE);
        }
    }                                         

    private void jCBPassbookTypeActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        PassbookType passbookType = (PassbookType)view.getjCBPassbookType().getSelectedItem();
        if(passbookType.getCode().equals("KKH")) {
            view.getjCBPeriod().setSelectedIndex(0);
            view.getjCBPeriod().setEnabled(false);
        }
        else {
            view.getjCBPeriod().setEnabled(true);
        }
        
    }                                               

    private void jCBPeriodActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        updateRate();
        updatePeriodDate();
    }                                         

    private void updateRate() {
        Period period = (Period) view.getjCBPeriod().getSelectedItem();
        Rate rate = rateDAO.getRateByPeriod(period.getPeriod());
        view.getjTextRate().setText(String.valueOf(rate.getRate()).trim());        
    }

    private void updatePeriodDate() {
        Period period = (Period) view.getjCBPeriod().getSelectedItem();
        if(period.getPeriod() <= 0) {
            view.getjTextPeriodDate().setText(StringUtility.DATE_FORMAT);
        }
        else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(view.getjDateOpen().getDate());
            calendar.add(Calendar.DATE, period.getPeriod() * 30); // thêm số ngày = kì hạn * 30
            SimpleDateFormat sdf = new SimpleDateFormat(StringUtility.DATE_FORMAT);
            String periodDate = sdf.format(calendar.getTime());
            view.getjTextPeriodDate().setText(periodDate);
        }
    }
    
    private boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    private boolean isOnlyNumber(String text) {
        return text.matches("[0-9]+");
    }

    public void validateKiHan() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PassbookType type = (PassbookType)view.getjCBPassbookType().getSelectedItem();
        if(!type.getCode().equals("KKH")) {
            Period period = (Period) view.getjCBPeriod().getSelectedItem();
            if(period.getPeriod() == Period.KKH) {
                view.getjCBPeriod().requestFocus();
                throw new Exception("Kỳ hạn không hợp lệ");
            }
        }
//      else if(type.getCode().equals("KKH")) {
//            Period period = (Period) view.getjCBPeriod().getSelectedItem();
//            if(period.getPeriod() != Period.KKH) {
//                view.getjCBPeriod().requestFocus();
//                throw new Exception("Kỳ hạn không hợp lệ");
//            }
//       }
    }
    
    public int validateMaKH() throws Exception {
        String text = view.getjTextMaKH().getText().trim();
        if (isNullOrEmpty(text)) {
            view.getjTextMaKH().requestFocus();
            throw new Exception("Mã khách hàng không được để trống");
        } //        else if(text.length() > 8) {
        //            return "Mã khách hàng nhỏ hơn 8 ký tự";
        //        }
        else if (!isOnlyNumber(text)) {
            view.getjTextMaKH().requestFocus();
            throw new Exception("Mã khách hàng không phải là số");
        }
        try {
            int maKH = Integer.parseInt(text);
            // tìm kiếm khách hàng trong db
            if (!clientDAO.isClientExistedId(maKH)) {
                view.getjTextMaKH().requestFocus();
                throw new Exception("Mã khách hàng không tồn tại");
            }
            return maKH;
        }
        catch(NumberFormatException ex) {
            view.getjTextMaKH().requestFocus();
            throw new Exception("Định dạng số không hợp lệ");
        }
    }

    public int validateSoTienGui() throws Exception {
        String text = view.getjTextMoney().getText().trim();
        if (isNullOrEmpty(text)) {
            view.getjTextMoney().requestFocus();
            throw new Exception("Số tiền không được để trống");
        } else if (!isOnlyNumber(text)) {
             view.getjTextMoney().requestFocus();
            throw new Exception("Số tiền nhập vào chứa ký tự không hợp lệ");
        } else {
            try {
                int money = Integer.parseInt(text);
                if(money % 1000 != 0) {
                     view.getjTextMoney().requestFocus();
                    throw new Exception("Số tiền phải là bội của 1000đ");
                }
                PassbookType passbookType = (PassbookType)view.getjCBPassbookType().getSelectedItem();
                if(!this.validateMinAmountRequired(passbookType.getCode(), money)) {
                     view.getjTextMoney().requestFocus();
                    throw new Exception("Số tiền gửi phải lớn hơn hoặc bằng " +this.getMinAmount(passbookType.getCode())+ "đ");
                }
                return money;
            } catch(NumberFormatException ex) {
                 view.getjTextMoney().requestFocus();
                throw new Exception("Định dạng số tiền không hợp lệ");
            }
        }
    }

    public void validateOpenDate() throws Exception {
        Date date = view.getjDateOpen().getDate();
        Date current = Calendar.getInstance().getTime();
        
        if(date.after(current)) {
            view.getjDateOpen().requestFocus();
            throw new Exception("Ngày mở sổ không hợp lệ");
        }
    }
}

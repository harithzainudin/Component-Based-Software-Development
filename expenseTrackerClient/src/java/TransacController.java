/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import connection.SQLConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Shakir
 */
@ManagedBean
@RequestScoped
public class TransacController {

    private double amount;
    private String detail;
    private Date tarikh;
    private int bankid;
    private int transid;
    private Map<String, Integer> bankmap;
    private ArrayList<TransObject> TransList = new ArrayList<>();
    private String transakType;
    
    FacesContext context = FacesContext.getCurrentInstance();
    int userid = Integer.parseInt(context.getExternalContext().getSessionMap().get("userid").toString());
    
    //Getting Transaction List from Database
    public ArrayList getTranslist() {
        Statement st;
        try {
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();

            st = con.createStatement();
            String sql = "Select id,amount,tarikh,detail,transaktype from transaksi where user_id = " + userid + "and bank_id = " + bankid;
            ResultSet result;
            result = st.executeQuery(sql);
            while (result.next()) {
                TransObject holder = new TransObject(result.getInt("id"), result.getDouble("amount"), result.getDate("tarikh"), result.getString("detail"), result.getString("transaktype"));
                TransList.add(holder);
            }
            con.close();
            return TransList;

        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
            return TransList;
        }

    }
    
    //Adding transaction to the Database
    public String addTransaction() {
        Statement st;
        try {
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();

            if (tarikh != null) {
                java.sql.Date sTarikh = new java.sql.Date(tarikh.getTime());
                PreparedStatement stmt;
                stmt = con.prepareStatement("insert into transaksi (detail,tarikh,amount,user_id,bank_id,transaktype) values(?,?,?,?,?,?)");
                stmt.setString(1, detail);
                stmt.setDate(2, sTarikh);
                stmt.setDouble(3, amount);
                stmt.setInt(4, userid);
                stmt.setInt(5, bankid);
                stmt.setString(6, transakType);
                stmt.executeUpdate();
                con.close();
                return "Insert Succesfully";
            }
            return "Not Inserted Yet";

        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
            return "Insertion Fail";
        }
    }

    //Delete transaction from the Database
    public String deleteTrans() {
        Statement st;
        try {
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();
            
            if(transid != 0){
            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM TRANSAKSI WHERE ID = " + transid);
            stmt.executeUpdate();
            con.close();
            return "Delete Success!";
            }
            return "Idle";

        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
            return "Delete Failed!";
        }
    }

    //goto bank navigation action for navigation menu
    public void gotoBank() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("userid", userid);
        context.getExternalContext().redirect("bankView.xhtml");
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "loginView.xhtml";
    }
    
    //Setter and Getter
    public TransacController() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    public Map<String, Integer> getBankmap() {
        return bankmap;
    }

    public void setBankmap(Map<String, Integer> bankmap) {
        this.bankmap = bankmap;
    }

    public void setTransList(ArrayList<TransObject> TransList) {
        this.TransList = TransList;
    }

    public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }

    public String getTransakType() {
        return transakType;
    }

    public void setTransakType(String transakType) {
        this.transakType = transakType;
    }
    
    

}

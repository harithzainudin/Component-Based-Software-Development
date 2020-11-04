/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import connection.SQLConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Shakir
 */
@ManagedBean
@RequestScoped
public class BankController {

    private String bankname;
    private ArrayList<BankObject> banklist = new ArrayList<>();
    private String nameholder;
    private int idholder;
    
    FacesContext context = FacesContext.getCurrentInstance();
    int userid = Integer.parseInt(context.getExternalContext().getSessionMap().get("userid").toString());
    
    
    public BankController() {
    }

    //inserting bank name into database
    public String insertBankname() {
        Statement st;
        try {
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();

            PreparedStatement stmt;
            stmt = con.prepareStatement("insert into bank(bankname,user_id) values(?,?)");
            stmt.setString(1, bankname);
            stmt.setInt(2, userid);
            stmt.executeUpdate();
            con.close();
            return "Insert Succesfully";

        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
            return "Insertion Fail";
        }
    }

    //getting bank list from the database
    public ArrayList getBanklist() {
        Statement st;
        try {
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();

            st = con.createStatement();
            String sql = "Select id,bankname from bank where user_id = " + userid;
            ResultSet result;
            result = st.executeQuery(sql);
            if (result.next()) {
                BankObject holder = new BankObject(result.getInt("id"), result.getString("bankname"));
                banklist.add(holder);
            }
            con.close();
            return banklist;

        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return banklist;
    }

    //Deleting bank from the database
    public String deleteBank() {
        Statement st;
        try {
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM BANK WHERE ID = " + idholder);
            stmt.executeUpdate();
            con.close();
            return "Delete Success!";

        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
            return "Delete Failed!";
        }
    }

    //Editing bank from the database
    public String editBank() {
        Statement st;
        try {
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement("UPDATE BANK SET BANKNAME = '" + nameholder + "' WHERE ID = " + idholder);
            stmt.executeUpdate();
            con.close();
            return "Edit Success!";

        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
            return "Edit Failed!";
        }
    }
    
    //goto Transaction navigation action for navigation menu
    public void gotoTransaction() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("userid", userid);
        context.getExternalContext().redirect("transactionView.xhtml");
    }

    //setter and getter
    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getNameholder() {
        return nameholder;
    }

    public void setNameholder(String nameholder) {
        this.nameholder = nameholder;
    }

    public int getIdholder() {
        return idholder;
    }

    public void setIdholder(int idholder) {
        this.idholder = idholder;
    }
}

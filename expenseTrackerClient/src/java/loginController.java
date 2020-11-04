/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.sun.faces.facelets.util.Path.context;
import connection.SQLConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author spino
 */
@ManagedBean
@RequestScoped
public class loginController {
    private String username, password;
    private String tempUserID;

    public String loginAccount() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        Statement st;
        try{
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();
            
            if (username != null && password != null){
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND pass =?");
                ps.setString(1, getUsername());
                ps.setString(2, getPassword());
                ResultSet result;
                result = ps.executeQuery();
                if (result.next()) {
                    tempUserID = result.getString(1);
                    context.getExternalContext().getSessionMap().put("userid", tempUserID);
                    context.getExternalContext().redirect("bankView.xhtml");
                }
                con.close();
            }
            
            return "No register account/wrong password/wrong username";
            
        } catch (SQLException ex) {
            return String.valueOf(ex);
        }
        
    }

    
    public void gotoBankView() throws IOException{
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempUserID() {
        return tempUserID;
    }

    public void setTempUserID(String tempUserID) {
        this.tempUserID = tempUserID;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public loginController() {
    }
    
}

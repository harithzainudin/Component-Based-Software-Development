/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import connection.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author spino
 */

@ManagedBean
@RequestScoped
public class registerController {

    private String username, password;
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    public String registerAccount(){
        Statement st;
        try{
            st = SQLConnection.getInstance().getSQLConnection();
            Connection con = st.getConnection();
            
            if (username != null && password != null){
                PreparedStatement stmt;
                stmt = con.prepareStatement("insert into users (username, pass) values(?,?)");
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
                con.close();
            }
            return "Not register yet";
            
        } catch (SQLException ex) {
            return String.valueOf(ex);
        }
        
    }
    
    public registerController() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

package connection;

/**
 *
 * @author Shakir
 */

import java.sql.*;
/**
 *
 * @author Shakir
 */
public class SQLConnection {
    private static SQLConnection instance;
    private Connection connection;
    private Statement st = null;
    
    public Statement getSQLConnection(){
        
        String url = "jdbc:derby://localhost:1527/cbsedb";
        String username = "root";
        String pwd = "1234";
       
        try{
            connection = DriverManager.getConnection(url, username, pwd);
            st = connection.createStatement();
            System.out.println("Connect Successfully!");
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Connect Failed!");
        }
        return st;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public static SQLConnection getInstance() throws SQLException{
    if (instance == null){
        instance = new SQLConnection();
       }
   else if (instance.getConnection().isClosed()){
       instance = new SQLConnection();
    }
    return instance;
    }   
}
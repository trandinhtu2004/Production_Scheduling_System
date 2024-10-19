/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public abstract class DBContext <T>{
    protected Connection connection;
    public DBContext(){
        try {
            String user = "tutd";
            String pass = "123";
            String url;
            url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;"
                    + "databaseName=ProductionSchedulingSystem_DB;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract ArrayList<T> list();
    public abstract void insert(T model);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class Konekcija {
    private static Konekcija instance;
    private Connection connection;

    public static Konekcija getInstance() {
        if(instance==null){
            instance=new Konekcija();
        }
        return instance;
    }

    private Konekcija() {
        try {
            String url="jdbc:mysql://localhost:3306/prognoza";
            connection=DriverManager.getConnection(url,"root","");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Meteorolog;
import domen.Prognoza;
import domen.Region;
import domen.StavkaIzvestaja;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class Kontroler {
    private static Kontroler instance;
    private DBBroker db;

    public static Kontroler getInstance() {
        if(instance==null){
            instance=new Kontroler();
        }
        return instance;
    }

    private Kontroler() {
       db=new DBBroker();
    }

    public Meteorolog login(String username, String password) {
        return db.login(username,password);
    }

    public ArrayList<Region> vratiRegione() {
        return db.vratiRegione();
    }

    public boolean sacuvajPrognozu(Prognoza prognoza) {
        try {
            return db.sacuvajPrognozu(prognoza);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public ArrayList<StavkaIzvestaja> vratiIzvestaj(String dodatniUpit){
        return db.vratiIzvestaj(dodatniUpit);
    }

    public boolean postojiDanUBazi(int meteorologID, Date dan) {
        return db.postojiDanUBazi(meteorologID, dan);
    }
}

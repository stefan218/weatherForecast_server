/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Meteorolog;
import domen.Prognoza;
import domen.PrognozaRegion;
import domen.Region;
import domen.StavkaIzvestaja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class DBBroker {
    public Object vrati(){
        String upit="";
        try {
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            while(rs.next()){
                //rs.getString(1);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean cuvajIzmeniBrisi() throws SQLException{
        String upit="";
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            //ps.setString(1, ...);
            
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            return true;
            
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Meteorolog login(String username, String password) {
        String upit="SELECT * FROM METEOROLOG";
        try {
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            while(rs.next()){   
                Meteorolog meteorolog=new Meteorolog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                if(meteorolog.getUsername().equals(username) && meteorolog.getPassword().equals(password)){
                    return meteorolog;
                }
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Region> vratiRegione() {
        ArrayList<Region> listaRegiona=new ArrayList<>();
        
        String upit="SELECT * FROM REGION";
        try {
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            while(rs.next()){
                Region region=new Region(rs.getInt(1), rs.getString(2), rs.getString(3));
                listaRegiona.add(region);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRegiona;
    }

    public boolean sacuvajPrognozu(Prognoza prognoza) throws SQLException {
        String upit="INSERT INTO PROGNOZA VALUES(?,?,?,?)";
        int prognozaID=vratiPrognozaID();
        ++prognozaID;
        prognoza.setPrognozaID(prognozaID);
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            ps.setInt(1, prognoza.getPrognozaID());
            ps.setDate(2, new java.sql.Date(prognoza.getDan().getTime()));
            ps.setString(3, prognoza.getOpis());
            ps.setInt(4, prognoza.getMeteorolog().getMeteorologID());
            ps.executeUpdate();
            
            if(sacuvajPrognozuRegion(prognoza)){ 
                System.out.println("sacuvane prognoze za sve regione");
                
                Konekcija.getInstance().getConnection().commit();
                return true;
            }
            else{
                Konekcija.getInstance().getConnection().rollback();
                return false;
            }
      
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean sacuvajPrognozuRegion(Prognoza prognoza) throws SQLException{
        String upit="INSERT INTO PROGNOZAREGION VALUES(?,?,?,?,?,?)";
        int rb=0;
        try {
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            for(PrognozaRegion pr : prognoza.getListaPrognozaZaRegione()){
                ps.setInt(1, prognoza.getPrognozaID());
                ps.setInt(2, ++rb);
                ps.setDouble(3, pr.getTemperatura());
                ps.setString(4, pr.getMeteoAlarm());
                ps.setString(5, pr.getPojava());
                ps.setInt(6, pr.getRegion().getRegionID());
                
                ps.addBatch();
            }
       
            ps.executeBatch();
            Konekcija.getInstance().getConnection().commit();
            return true;
            
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int vratiPrognozaID() {
        String upit="SELECT MAX(PROGNOZAID) FROM PROGNOZA";
        int prognozaID=0;
        try {
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
             
            while(rs.next()){
                prognozaID=rs.getInt(1);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prognozaID;
    }

    public ArrayList<StavkaIzvestaja> vratiIzvestaj(String dodatniUpit) {
        ArrayList<StavkaIzvestaja> listaStavki=new ArrayList<>();
        
        String upit="SELECT CONCAT(M.IME, ' ',M.PREZIME) AS METEOROLOG, DAN, "
                + "GROUP_CONCAT(R.NAZIV, ':', PR.TEMPERATURA,'(',PR.METEOALARM, ' ,',PR.POJAVA, ')' SEPARATOR '; ') AS Prognoza_za_regione "
                + "FROM METEOROLOG AS M JOIN PROGNOZA P ON(M.METEOROLOGID=P.METEOROLOGID)"
                + "JOIN PROGNOZAREGION AS PR ON(P.PROGNOZAID=PR.PROGNOZAID)"
                + "JOIN REGION R ON (PR.REGIONID=R.REGIONID)"
                + dodatniUpit
                + "GROUP BY METEOROLOG, DAN";
        try {
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            while(rs.next()){
                StavkaIzvestaja stavka=new StavkaIzvestaja(rs.getString(1), rs.getDate(2), rs.getString(3));
                listaStavki.add(stavka);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaStavki;
    }


    public boolean postojiDanUBazi(int meteorologID, Date dan) {
        String upit="SELECT * FROM PROGNOZA";
        try {
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            while(rs.next()){
                int meteorologid=rs.getInt(4);
                Date dan1=rs.getDate(2);
                
                if(meteorologID==meteorologid && dan.equals(dan1)){
                    return true;
                }
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

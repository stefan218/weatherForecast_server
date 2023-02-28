/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Prognoza;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import konstante.Operacije;
import logika.Kontroler;

/**
 *
 * @author Stefan
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    Socket s;
    
    ObradaKlijentskihZahteva(Socket s) {
        this.s=s;
    }

    @Override
    public void run() {
        while(true){
            KlijentskiZahtev kz=primiZahtev();
            ServerskiOdgovor so=new ServerskiOdgovor();
            switch(kz.getOperacija()){
                case Operacije.LOGIN:
                    HashMap<Integer,String> mapa=(HashMap<Integer,String>) kz.getParametar();
                    String username=mapa.get(1);
                    String password=mapa.get(2);
                    
                    so.setOdgovor(Kontroler.getInstance().login(username, password));
                    break;
                case Operacije.VRATI_REGIONE:
                    so.setOdgovor(Kontroler.getInstance().vratiRegione());
                    break;
                case Operacije.SACUVAJ_PROGNOZU:
                    Prognoza prognoza=(Prognoza) kz.getParametar();
                    
                    so.setOdgovor(Kontroler.getInstance().sacuvajPrognozu(prognoza));
                    break;
                case Operacije.POSTOJI_DAN_U_BAZI:
                    HashMap<Integer,Object> mapa1=(HashMap<Integer,Object>) kz.getParametar();
                    int meteorologID=(int) mapa1.get(1);
                    Date dan=(Date) mapa1.get(2);
                    
                    so.setOdgovor(Kontroler.getInstance().postojiDanUBazi(meteorologID, dan));
                    break;
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

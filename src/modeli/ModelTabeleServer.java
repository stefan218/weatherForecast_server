/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.StavkaIzvestaja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Stefan
 */
public class ModelTabeleServer extends AbstractTableModel{

    ArrayList<StavkaIzvestaja> lista;
    String kolone[]={"Meteorolog", "Dan", "Prognoza za regione"};

    public ModelTabeleServer() {
        lista=new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIzvestaja stavka=lista.get(rowIndex);
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        
        switch(columnIndex){
            case 0:
                return stavka.getMeteorolog();
            case 1:
                return sdf.format(stavka.getDan());
            case 2:
                return stavka.getPrognozaZaRegione();
            default:
                return "default!";
        }
    }

    public ArrayList<StavkaIzvestaja> getLista() {
        return lista;
    }

    public void popuniListu(ArrayList<StavkaIzvestaja> listaUnos) {
        lista=listaUnos;
        fireTableDataChanged();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import modeli.ModelTabeleServer;
import niti.OsveziNit;
import niti.PokreniServer;

/**
 *
 * @author Stefan
 */
public class ServerskaForma extends javax.swing.JFrame {

    /**
     * Creates new form ServerskaForma
     */
    public ServerskaForma() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Serverska forma");
        tblIzvestaj.setModel(new ModelTabeleServer());
        OsveziNit nit=new OsveziNit(this);
        nit.start();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbDanOd = new javax.swing.JCheckBox();
        cbDanDo = new javax.swing.JCheckBox();
        txtDanOd = new javax.swing.JFormattedTextField();
        txtDanDo = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIzvestaj = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbDanOd.setText("Dan od(dd.MM.yyyy):");

        cbDanDo.setText("Dan do(dd.MM.yyyy):");

        txtDanOd.setText("28.01.2022");
        txtDanOd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDanOdActionPerformed(evt);
            }
        });

        txtDanDo.setText("29.01.2022");

        tblIzvestaj.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblIzvestaj);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbDanOd)
                                .addGap(18, 18, 18)
                                .addComponent(txtDanOd, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbDanDo)
                                .addGap(18, 18, 18)
                                .addComponent(txtDanDo))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDanOd)
                    .addComponent(txtDanOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDanDo)
                    .addComponent(txtDanDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDanOdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDanOdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDanOdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerskaForma().setVisible(true);
                PokreniServer nit=new PokreniServer();
                nit.start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbDanDo;
    private javax.swing.JCheckBox cbDanOd;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblIzvestaj;
    private javax.swing.JFormattedTextField txtDanDo;
    private javax.swing.JFormattedTextField txtDanOd;
    // End of variables declaration//GEN-END:variables

    public void popuniIzvestaj() {
        ModelTabeleServer mt=(ModelTabeleServer) tblIzvestaj.getModel();
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        String dodatniUpit="";
        
        if(cbDanOd.isSelected() && cbDanDo.isSelected()){
            try {
                Date danOd=sdf.parse(txtDanOd.getText());
                Date danDo=sdf.parse(txtDanDo.getText());
                
                dodatniUpit="WHERE DAN BETWEEN '"+new java.sql.Date(danOd.getTime())+"'AND '"
                        +new java.sql.Date(danDo.getTime())+"'";
                
                mt.popuniListu(Kontroler.getInstance().vratiIzvestaj(dodatniUpit));
            } catch (ParseException ex) {
                Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        if(!cbDanOd.isSelected() && !cbDanDo.isSelected()){
            mt.popuniListu(Kontroler.getInstance().vratiIzvestaj(dodatniUpit));
        }
        
        if(cbDanOd.isSelected() && !cbDanDo.isSelected()){
                 
            try {
                Date danOd = sdf.parse(txtDanOd.getText());
                
                dodatniUpit="WHERE DAN >= '"+new java.sql.Date(danOd.getTime())+"' ";
                
                mt.popuniListu(Kontroler.getInstance().vratiIzvestaj(dodatniUpit));
            } catch (ParseException ex) {
                Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
        }
        if(!cbDanOd.isSelected() && cbDanDo.isSelected()){
                 
            try {
                Date danDo = sdf.parse(txtDanDo.getText());
                
                dodatniUpit="WHERE DAN <= '"+new java.sql.Date(danDo.getTime())+"' ";
                
                mt.popuniListu(Kontroler.getInstance().vratiIzvestaj(dodatniUpit));
            } catch (ParseException ex) {
                Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
        }
        
    }
}

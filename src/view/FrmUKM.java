/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import object.ICoach2;
import object.IUKM;

/**
 *
 * @author Haris
 */
public class FrmUKM extends javax.swing.JFrame {
    IUKM obj6;
    ICoach2 obj3;
    DefaultTableModel model;

    /**
     * Creates new form FrmUKM
     */
    public FrmUKM() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try
        {
         Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1",1097);
         obj6 = (IUKM) myRegistry.lookup("log6");
         obj3 = (ICoach2) myRegistry.lookup("log3");
         
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Error 1 :"+ e);
        }
        displayData();
        displayDatacoach();
    }
    
//    public FrmUKM(String id) 
//      {
//        initComponents();
//        setLocationRelativeTo(this);
//        //btn_save.setEnabled(false);
//        try 
//        {
//            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1097);
//            obj6 = (IUKM) myRegistry.lookup("log6");
//        }
//        catch(Exception e)
//        {
//            JOptionPane.showMessageDialog(null,"Error 2: " + e);
//        }
//        this.getRecord(id);
//        }
    
//    private void getRecord(String id)
//    {
//        try{
//            obj6.setUKMID(Integer.parseInt(id));
//            ArrayList data = obj6.getRecord();
//            //txtid.setText((String)data.get(0));
//            txtid.setText(String.valueOf(data.get(0)));
//            txtukm.setText((String)data.get(1));
//            txtdesc.setText((String)data.get(2));
//            txtcoachid.setText((String)data.get(3));
//        }
//        catch(Exception ex) {
//            JOptionPane.showMessageDialog(null, "Data cant be displayed");
//        }
//    }
    
    private void add() //throws RemoteException 
    {
        try {   
            
            obj6.setUKMName(txtukm.getText());
            obj6.setDescription(txtdesc.getText());
            obj6.setCoachID(Integer.parseInt(txtcoachid.getText().trim()));
            String UKMName = (String)txtukm.getText();
            String Description = (String)txtukm.getText();
            String CoachID = (String)txtukm.getText();
            if(UKMName.equals("") || Description.equals("") || CoachID.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Data cant be empty");
            }
            
            else
                {
                    int i = obj6.doInsert();
                    if(i > 0) {
                    JOptionPane.showMessageDialog(null, "Data has been successfully added");
                    this.clear();
                    displayData();
                    displayDatacoach();
                    }
                    else
                    {
                      JOptionPane.showMessageDialog(null, "Data cant be added");      
                    }
                }
                    
            }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error 5: " + e);
        }           
   }
    
    private void update() {
        try {
            //obj3.setCoachID(Integer.parseInt(txtid.getText().trim()));
            obj6.setUKMID(Integer.parseInt(txtid.getText()));
            obj6.setUKMName(txtukm.getText().trim());
            obj6.setDescription(txtdesc.getText().trim());
            obj6.setCoachID(Integer.parseInt(txtcoachid.getText().trim()));
            int i = obj6.doUpdate();
            if(i > 0) {
                JOptionPane.showMessageDialog(null, "Data has been successfully updated");
                this.clear();
                displayData();
                displayDatacoach();
            }
            else {
                JOptionPane.showMessageDialog(null, "Data cant be updated");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error 3: " + e);
        }
    }
     private void delete() {
        try {
            obj6.setUKMID(Integer.parseInt(txtid.getText()));            
            int i = obj6.doDelete();
            if(i > 0) {
                JOptionPane.showMessageDialog(null, "Data has been successfully deleted");
                this.clear();
                displayData();
                displayDatacoach();
            }
            else {
                JOptionPane.showMessageDialog(null, "Data cant be deleted");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error 4: " + e);
        }
    }
     
     private void clear()
    {
        txtid.setText("");
        txtukm.setText("");
        txtdesc.setText("");
        txtcoachid.setText("");
    }
     
     private void displayData() {
         model = (DefaultTableModel)jTable1.getModel();
         model.setRowCount(0);
        try{
            ArrayList data = obj6.display();
            for(int i = 0;i < data.size()-1;i+=4)
            {
                //fac_code, fac_name, fac_email, fac_phone
                int UKMID = (Integer)data.get(i);
                //String sID = CoachID + "";
                String UKMName = (String)data.get(i+1);
                String Description = (String)data.get(i+2);
                int CoachID = (Integer)data.get(i+3);
                 

                String[] data_field = {String.valueOf(UKMID).trim(),UKMName.trim(),Description.trim(), String.valueOf(CoachID).trim()};
                
                model.addRow(data_field);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + ex.getMessage());
        }
    }
     
     private void displayDatacoach() {
         model = (DefaultTableModel)jTable2.getModel();
         model.setRowCount(0);
        try{
            ArrayList data = obj3.display();
            for(int i = 0;i < data.size()-1;i+=5)
            {
                //fac_code, fac_name, fac_email, fac_phone
                int CoachID = (Integer)data.get(i);
                String CoachName = (String)data.get(i+1);
                String CoachGender = (String)data.get(i+2);
                String CoachAddress = (String)data.get(i+3);
                String CoachPhoneNumber = (String)data.get(i+4);
                //String sID = CoachID + "";
                 

                String[] data_field = {String.valueOf(CoachID).trim(),CoachName.trim(),CoachGender.trim(),CoachAddress.trim(),CoachPhoneNumber.trim()};
                
                model.addRow(data_field);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + ex.getMessage());
        }
    }
     
//     private String getRecordcoach() {
//        int baris = jTable2.getSelectedRow();
//        model = (DefaultTableModel) jTable2.getModel();
//        String id = model.getValueAt(baris,0).toString();
//        return id;
//    }
     
//     private String getRecord() {
//        int baris = jTable1.getSelectedRow();
//        model = (DefaultTableModel) jTable1.getModel();
//        String id = model.getValueAt(baris,0).toString();
//        return id;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtukm = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdesc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtcoachid = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Delete = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnback = new javax.swing.JButton();

        jLabel2.setText("ID");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtid.setEditable(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel4.setText("UKM Name");

        jLabel5.setText("UKM ID");

        txtukm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtukmActionPerformed(evt);
            }
        });

        jLabel6.setText("Description");

        txtdesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescActionPerformed(evt);
            }
        });

        jLabel7.setText("Coach ID");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UKM ID", "UKM Name", "Description", "Coach ID"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtcoachid.setEditable(false);
        txtcoachid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoachidActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Coach ID", "Name", "Gender", "Address", "Phone Number"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtdesc, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                            .addComponent(txtukm)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(txtcoachid))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnsave)
                                .addGap(18, 18, 18)
                                .addComponent(btnupdate)
                                .addGap(26, 26, 26)
                                .addComponent(Delete))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtukm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addComponent(txtdesc)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcoachid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnupdate)
                            .addComponent(Delete)
                            .addComponent(btnsave))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnback)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtukmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtukmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtukmActionPerformed

    private void txtdescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescActionPerformed

    private void txtcoachidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoachidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoachidActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        int selectedRowIndex = jTable2.getSelectedRow();
        
        txtcoachid.setText(model.getValueAt(selectedRowIndex, 0).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel tableModel = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        txtid.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
        txtukm.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
        txtdesc.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
        txtcoachid.setText(tableModel.getValueAt(selectedRowIndex, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        this.add();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_DeleteActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        DashboardAdmin dsa = new DashboardAdmin();
        dsa.show();
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmUKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmUKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmUKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmUKM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmUKM().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtcoachid;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtukm;
    // End of variables declaration//GEN-END:variables
}

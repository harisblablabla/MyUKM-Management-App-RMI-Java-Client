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
import object.IPlace;
import object.ISchedule;
import object.IUKM;

/**
 *
 * @author Haris
 */
public class FrmSchedule extends javax.swing.JFrame {
    ISchedule obj4;
    IUKM obj6;
    IPlace obj5;
    DefaultTableModel model;

    /**
     * Creates new form FrmSchedule
     */
    public FrmSchedule() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try
        {
         Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1",1097);
         obj6 = (IUKM) myRegistry.lookup("log6");
         obj5 = (IPlace) myRegistry.lookup("log5");
         obj4 = (ISchedule) myRegistry.lookup("log4");
         
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Error 1 :"+ e);
        }
        displayData();
        displayDataplace();
        displayDataukm();
    }
    
    private void add() //throws RemoteException 
    {
        try {   
            
            obj4.setDay(cbday.getSelectedItem().toString());
            obj4.setStartTime(txtstarttime.getText());
            obj4.setEndTime(txtendtime.getText());
            obj4.setUKMPlaceID(Integer.parseInt(txtplaceid.getText().trim()));
            obj4.setUKMName(txtukmname.getText());;
            String Day = String.valueOf(cbday.getSelectedItem());
            String StartTime = (String)txtstarttime.getText();
            String EndTime = (String)txtendtime.getText();
            String UKMPlaceID = (String)txtplaceid.getText();
            String UKMName = (String)txtukmname.getText();
            if(StartTime.equals("") || EndTime.equals("") || UKMPlaceID.equals("") || UKMName.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Data cant be empty");
            }
            
            else
                {
                    int i = obj4.doInsert();
                    if(i > 0) {
                    JOptionPane.showMessageDialog(null, "Data has been successfully added");
                    this.clear();
                    displayData();
                    displayDataplace();
                    displayDataukm();
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
            obj4.setScheduleID(txtid.getText().trim());
            obj4.setDay(cbday.getSelectedItem().toString());
            obj4.setStartTime(txtstarttime.getText().trim());
            obj4.setEndTime(txtendtime.getText().trim());
            obj4.setUKMPlaceID(Integer.parseInt(txtplaceid.getText().trim()));
            obj4.setUKMName(txtukmname.getText().trim());
            int i = obj4.doUpdate();
            if(i > 0) {
                JOptionPane.showMessageDialog(null, "Data has been successfully updated");
                this.clear();
                displayData();
                displayDataplace();
                displayDataukm();
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
            obj4.setScheduleID((txtid.getText()));            
            int i = obj4.doDelete();
            if(i > 0) {
                JOptionPane.showMessageDialog(null, "Data has been successfully deleted");
                this.clear();
                displayData();
                displayDataplace();
                displayDataukm();
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
        cbday.setSelectedIndex(0);
        txtstarttime.setText("");
        txtendtime.setText("");
        txtplaceid.setText("");
        txtukmname.setText("");
    }
     
     private void displayData() {
         model = (DefaultTableModel)jTable1.getModel();
         model.setRowCount(0);
        try{
            ArrayList data = obj4.display();
            for(int i = 0;i < data.size()-1;i+=6)
            {
                //fac_code, fac_name, fac_email, fac_phone
                String ScheduleID = (String)data.get(i);
                //String sID = CoachID + "";
                String Day = (String)data.get(i+1);
                String StartTime = (String)data.get(i+2);
                String EndTime = (String)data.get(i+3);
                int UKMPlaceID = (int)data.get(i+4);
                String UKMName = (String)data.get(i+5);
                
                String[] data_field = {ScheduleID.trim(),Day.trim(),StartTime.trim(),EndTime.trim(),UKMPlaceID+"", UKMName.trim()};
                
                model.addRow(data_field);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + ex.getMessage());
        }
    }
     
     private void displayDataplace() {
         model = (DefaultTableModel)jtabplace.getModel();
         model.setRowCount(0);
        try{
            ArrayList data = obj5.display();
            for(int i = 0;i < data.size()-1;i+=2)
            {
                //fac_code, fac_name, fac_email, fac_phone 
                int UKMPlaceID = (Integer)data.get(i);
                String PlaceName = (String)data.get(i+1);
                //String sID = CoachID + "";
                 

                String[] data_field = {String.valueOf(UKMPlaceID).trim(),PlaceName.trim()};
                
                model.addRow(data_field);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + ex.getMessage());
        }
    }
     
     private void displayDataukm() {
         model = (DefaultTableModel)jtabukm.getModel();
         model.setRowCount(0);
        try{
            ArrayList data = obj6.displayinschedule();
            for(int i = 0;i < data.size()-1;i+=2)
            {
                //fac_code, fac_name, fac_email, fac_phone
                int UKMID = (Integer)data.get(i);                
                String UKMName = (String)data.get(i+1);
                
                String[] data_field = {String.valueOf(UKMID).trim(), UKMName.trim()};
                
                model.addRow(data_field);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtukmname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btndelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtplaceid = new javax.swing.JTextField();
        cbday = new javax.swing.JComboBox<>();
        txtstarttime = new javax.swing.JTextField();
        txtendtime = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabplace = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtabukm = new javax.swing.JTable();
        btnback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setText("End Time");

        txtid.setEditable(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        txtukmname.setEditable(false);
        txtukmname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtukmnameActionPerformed(evt);
            }
        });

        jLabel2.setText("ID");

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        jLabel5.setText("Day");

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        jLabel6.setText("Start Time");

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        jLabel3.setText("Place ID");

        jLabel7.setText("UKM Name");

        txtplaceid.setEditable(false);
        txtplaceid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtplaceidActionPerformed(evt);
            }
        });

        cbday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));

        txtstarttime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstarttimeActionPerformed(evt);
            }
        });

        txtendtime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtendtimeActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Day", "Start Time", "End Time", "Place ID", "UKM Name"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jtabplace.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Place"
            }
        ));
        jtabplace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabplaceMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtabplace);

        jtabukm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "UKM Name"
            }
        ));
        jtabukm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabukmMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtabukm);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnupdate)
                            .addComponent(btnsave)
                            .addComponent(btndelete))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtukmname, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(68, 68, 68)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtendtime, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                                .addComponent(txtstarttime))
                                            .addComponent(txtplaceid, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(903, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtstarttime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtendtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtplaceid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtukmname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnsave)
                        .addGap(18, 18, 18)
                        .addComponent(btnupdate)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(419, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtukmnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtukmnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtukmnameActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        this.add();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void txtplaceidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtplaceidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtplaceidActionPerformed

    private void txtstarttimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstarttimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstarttimeActionPerformed

    private void txtendtimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtendtimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtendtimeActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        txtid.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
        cbday.setSelectedItem(tableModel.getValueAt(selectedRowIndex, 1).toString());
        txtstarttime.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
        txtendtime.setText(tableModel.getValueAt(selectedRowIndex, 3).toString());
        txtplaceid.setText(tableModel.getValueAt(selectedRowIndex, 4).toString());
        txtukmname.setText(tableModel.getValueAt(selectedRowIndex, 5).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jtabplaceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabplaceMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jtabplace.getModel();
        int selectedRowIndex = jtabplace.getSelectedRow();
        
        txtplaceid.setText(model.getValueAt(selectedRowIndex, 0).toString());
    }//GEN-LAST:event_jtabplaceMouseClicked

    private void jtabukmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabukmMouseClicked
        // TODO add your handling code here:
        DefaultTableModel models = (DefaultTableModel)jtabukm.getModel();
        int selectedRowIndex = jtabukm.getSelectedRow();
        
        txtukmname.setText(model.getValueAt(selectedRowIndex, 1).toString());
    }//GEN-LAST:event_jtabukmMouseClicked

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here
        this.delete();
    }//GEN-LAST:event_btndeleteActionPerformed

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
//            java.util.logging.Logger.getLogger(FrmSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmSchedule().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbday;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jtabplace;
    private javax.swing.JTable jtabukm;
    private javax.swing.JTextField txtendtime;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtplaceid;
    private javax.swing.JTextField txtstarttime;
    private javax.swing.JTextField txtukmname;
    // End of variables declaration//GEN-END:variables
}

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

/**
 *
 * @author Haris
 */
public class FrmCoach extends javax.swing.JFrame {
    ICoach2 obj3;
    DefaultTableModel model;

    /**
     * Creates new form FrmCoach
     */
    public FrmCoach() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try
        {
         Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1",1097);
         obj3 = (ICoach2) myRegistry.lookup("log3");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Error 1 :"+ e);
        }
        displayData();
    }
    
    public FrmCoach(String id) 
      {
        initComponents();
        setLocationRelativeTo(this);
        //btn_save.setEnabled(false);
        try 
        {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1097);
            obj3= (ICoach2) myRegistry.lookup("log3");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error 2: " + e);
        }
        this.getRecord(id);
        }
    
    private void getRecord(String id)
    {
        try{
            obj3.setCoachID(Integer.parseInt(id));
            ArrayList data = obj3.getRecord();
            //txtid.setText((String)data.get(0));
            txtid.setText(String.valueOf(data.get(0)));
            txtcoachname.setText((String)data.get(1));
            String coachgender = ((String)data.get(2));
            cbcoachGender.addItem(coachgender);
            txtcoachaddress.setText((String)data.get(3));
            txtcoachphnnumber.setText((String)data.get(4));
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Data cant be displayed");
        }
    }
    
    private void add() //throws RemoteException 
    {
        try {   
            obj3.setCoachName(txtcoachname.getText());
            obj3.setCoachGender(cbcoachGender.getSelectedItem().toString());
            obj3.setCoachAddress(txtcoachaddress.getText());
            obj3.setCoachPhoneNumber(txtcoachphnnumber.getText());
            String CoachName = (String)txtcoachname.getText();
            String CoachGender = String.valueOf(cbcoachGender.getSelectedItem());
            String CoachAddress = (String)txtcoachaddress.getText();
            String CoachPhoneNumber = (String)txtcoachphnnumber.getText();
            if(CoachName.equals("") || CoachAddress.equals("") || CoachPhoneNumber.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Data cant be empty");
            }
            
            else
                {
                    int i = obj3.doInsert();
                    if(i > 0) {
                    JOptionPane.showMessageDialog(null, "Data has been successfully added");
                    this.clear();
                    displayData();
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
            obj3.setCoachName(txtcoachname.getText().trim());
            obj3.setCoachGender(cbcoachGender.getSelectedItem().toString());
            obj3.setCoachAddress(txtcoachaddress.getText().trim());
            obj3.setCoachPhoneNumber(txtcoachphnnumber.getText().trim());
            int i = obj3.doUpdate();
            if(i > 0) {
                JOptionPane.showMessageDialog(null, "Data has been successfully updated");
                this.clear();
                displayData();
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
            obj3.setCoachID(Integer.parseInt(txtid.getText()));            
            int i = obj3.doDelete();
            if(i > 0) {
                JOptionPane.showMessageDialog(null, "Data has been successfully deleted");
                this.clear();
                displayData();
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
        txtcoachname.setText("");
        cbcoachGender.setSelectedIndex(0);
        txtcoachaddress.setText("");
        txtcoachphnnumber.setText("");
    }
     
     private void displayData() {
         model = (DefaultTableModel)tblCoach.getModel();
         model.setRowCount(0);
        try{
            ArrayList data = obj3.display();
            for(int i = 0;i < data.size()-1;i+=5)
            {
                //fac_code, fac_name, fac_email, fac_phone
                int CoachID = (Integer)data.get(i);
                //String sID = CoachID + "";
                String CoachName = (String)data.get(i+1);
                String CoachGender = (String)data.get(i+2);
                String CoachAddress = (String)data.get(i+3);
                String CoachPhoneNumber = (String)data.get(i+4);
                

                String[] data_field = {String.valueOf(CoachID).trim(), CoachName.trim(),CoachGender.trim(), CoachAddress.trim(), CoachPhoneNumber.trim()};
                
                model.addRow(data_field);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + ex.getMessage());
        }
    }
     
     private String getRecord() {
        int baris = tblCoach.getSelectedRow();
        model = (DefaultTableModel) tblCoach.getModel();
        String id = model.getValueAt(baris,0).toString();
        return id;
    }
     
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtid = new javax.swing.JTextField();
        txtcoachname = new javax.swing.JTextField();
        cbcoachGender = new javax.swing.JComboBox<>();
        txtcoachaddress = new javax.swing.JTextField();
        txtcoachphnnumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCoach = new javax.swing.JTable();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtid.setEditable(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        txtcoachname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoachnameActionPerformed(evt);
            }
        });

        cbcoachGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cbcoachGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcoachGenderActionPerformed(evt);
            }
        });

        txtcoachaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoachaddressActionPerformed(evt);
            }
        });

        txtcoachphnnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoachphnnumberActionPerformed(evt);
            }
        });

        jLabel2.setText("ID");

        jLabel4.setText("Coach Name");

        jLabel5.setText("Gender");

        jLabel6.setText("Address");

        jLabel8.setText("Phone Number");

        tblCoach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Coach ID", "Coach Name", "Gender", "Address", "Phone Number"
            }
        ));
        tblCoach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCoachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCoach);

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcoachname, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbcoachGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnsave)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addComponent(txtcoachphnnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnupdate)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(txtcoachaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcoachname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbcoachGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnback)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcoachaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcoachphnnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsave)
                            .addComponent(btnupdate)
                            .addComponent(btndelete))))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtcoachnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoachnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoachnameActionPerformed

    private void cbcoachGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcoachGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcoachGenderActionPerformed

    private void txtcoachaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoachaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoachaddressActionPerformed

    private void txtcoachphnnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoachphnnumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoachphnnumberActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        this.add();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void tblCoachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCoachMouseClicked
        DefaultTableModel tableModel = (DefaultTableModel)tblCoach.getModel();
        int selectedRowIndex = tblCoach.getSelectedRow();
        
        txtid.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txtcoachname.setText(model.getValueAt(selectedRowIndex, 1).toString());
        cbcoachGender.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
        txtcoachaddress.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txtcoachphnnumber.setText(model.getValueAt(selectedRowIndex, 4).toString());
    }//GEN-LAST:event_tblCoachMouseClicked

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
//            java.util.logging.Logger.getLogger(FrmCoach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmCoach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmCoach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmCoach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmCoach().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbcoachGender;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCoach;
    private javax.swing.JTextField txtcoachaddress;
    private javax.swing.JTextField txtcoachname;
    private javax.swing.JTextField txtcoachphnnumber;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}

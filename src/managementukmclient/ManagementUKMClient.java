/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementukmclient;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import view.FrmChooseLogin;

/**
 *
 * @author Haris
 */
public class ManagementUKMClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        FrmChooseLogin lgn = new FrmChooseLogin();
//        lgn.show(true);
//        // TODO code application logic here
                try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            /*if ("Windows".equals(info.getName())) {
             * javax.swing.UIManager.setLookAndFeel(info.getClassName());
             * break;
             * }*/
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
         }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(FrmChooseLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
                new FrmChooseLogin().setVisible(true);
//                try{
//                    UIManager.setLookAndFeel("com.jtatto.plaf.acryl.AcrylLookAndFeel");
//                    SwingUtilities.updateComponentTreeUI(new FrmChooseLogin());
//                }
//                catch(Exception e){
//                    
//                }
//                //FrmChooseLogin.setUndercorated(true);
//                //FrmChooseLogin.getRootPane().setWindowDecorationStyle(HIDE_ON_CLOSE);
//                new FrmChooseLogin().setVisible(true);
//        
//    }
    
}
}

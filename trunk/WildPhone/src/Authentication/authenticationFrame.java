/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * authenticationFrame.java
 *
 * Created on 7-mag-2012, 10.55.03
 */
package Authentication;

import HomeUser.HomeUser;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author enrico
 */
public class authenticationFrame extends javax.swing.JFrame {

    /** Creates new form authenticationFrame */
    public authenticationFrame() {
        
        initComponents();        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        pswLabel = new javax.swing.JLabel();
        pswField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginButton.setText("Accedi");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Registrati");

        usernameLabel.setText("Username:");

        pswLabel.setText("Password:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pswLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pswField)
                            .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pswField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(pswLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(134, 134, 134)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(loginButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        
        String username;
        String psw;
        
        username = usernameField.getText();
        psw = pswField.getText();
        
        if((username.compareTo("") != 0) && (psw.compareTo("") != 0)){
            
            try{
                
                login(username,psw);
            
            }
            catch(XMPPException ex){
                
                System.out.println(ex.getMessage());
            }
            
        }
        
        
    }//GEN-LAST:event_loginButtonActionPerformed

   
    private void login(String username,String psw) throws XMPPException{
        
        this.xmppConnection = new XMPPConnection(server);
        this.xmppConnection.connect();
        try{
            
            this.xmppConnection.login(username, psw);
            new HomeUser(this.xmppConnection).setVisible(true);
        }
        catch(Exception ex){
            
            new errorForm("").setVisible(true);
            //JOptionPane.showMessageDialog(null, "Error Message", "WildPhone", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    
    
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(authenticationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(authenticationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(authenticationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(authenticationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new authenticationFrame().setVisible(true);
            }
        });
    }
    
    private final String server = "server di riferimento";
    private Connection xmppConnection;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField pswField;
    private javax.swing.JLabel pswLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}

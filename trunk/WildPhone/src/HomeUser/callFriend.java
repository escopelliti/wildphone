/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * callFriend.java
 *
 * Created on 12-giu-2012, 10.33.52
 */
package HomeUser;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.jingle.JingleManager;
import org.jivesoftware.smackx.jingle.JingleSession;
import org.jivesoftware.smackx.jingle.JingleSessionRequest;
import org.jivesoftware.smackx.jingle.listeners.JingleSessionRequestListener;
import org.jivesoftware.smackx.jingle.media.JingleMediaManager;
import org.jivesoftware.smackx.jingle.mediaimpl.jspeex.SpeexMediaManager;
import org.jivesoftware.smackx.jingle.nat.ICETransportManager;

/**
 *
 * @author ninux
 */
public class callFriend extends javax.swing.JFrame {
    
    private Connection xmppConnection;
    private JingleManager jm;
    private JingleSession incoming = null;
    private JingleSession outgoing = null;
    /** Creates new form callFriend */
    
    private void initialize() {
        
        JingleManager.setJingleServiceEnabled();
        ICETransportManager icetm0 = new ICETransportManager(xmppConnection, xmppConnection.getHost(), 3478);//prima 3478 NOTA il numero di porta è fisso// e deve essere in ascolto sul server(porta x UDP)
        List<JingleMediaManager> mediaManagers = new ArrayList<JingleMediaManager>();
        
//        mediaManagers.add(new JmfMediaManager(icetm0));
        mediaManagers.add(new SpeexMediaManager(icetm0));
        //mediaManagers.add(new ScreenShareMediaManager(icetm0));
        jm = new JingleManager(xmppConnection, mediaManagers);
        jm.addCreationListener(icetm0);

        jm.addJingleSessionRequestListener(new JingleSessionRequestListener() {
            @Override
            public void sessionRequested(JingleSessionRequest request) {

                if (incoming != null)
                    return;

                try {
                    // Accept the call
                    incoming = request.accept();
                    
                   

                    // Start the call
                    incoming.startIncoming();
                   
                    
                }
                catch (XMPPException e) {
                    
                    JOptionPane.showMessageDialog(null, "Problemi tecnici.", "WildPhone", JOptionPane.ERROR_MESSAGE); 
                }

            }
        });
        
    
    }
    
    public callFriend(Connection conn) {
        this.xmppConnection = conn;
        initialize();
        initComponents();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        riaggancia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        riaggancia.setText("Riaggancia");
        riaggancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riagganciaActionPerformed(evt);
            }
        });

        jLabel1.setText("In chiamata con:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(riaggancia)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(398, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(riaggancia)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void riagganciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riagganciaActionPerformed
           
            if (outgoing != null)
                    try {
                        outgoing.terminate();
                    }
                    catch (XMPPException e1) {
                        System.out.println("error: " + e1.getMessage());
                        //e1.printStackTrace();
                    }
                    finally {
                        outgoing = null;
                    }
                if (incoming != null)
                    try {
                        incoming.terminate();
                    }
                    catch (XMPPException e1) {
                        System.out.println("error: " + e1.getMessage());
                    }
                    finally {
                        incoming = null;
                    }
            
       
    }//GEN-LAST:event_riagganciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton riaggancia;
    // End of variables declaration//GEN-END:variables
}

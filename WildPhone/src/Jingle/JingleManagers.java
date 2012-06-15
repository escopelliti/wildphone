package Jingle;

import HomeUser.callFriend;
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
import org.jivesoftware.smackx.jingle.mediaimpl.sshare.ScreenShareMediaManager;
import org.jivesoftware.smackx.jingle.mediaimpl.jspeex.SpeexMediaManager;
import org.jivesoftware.smackx.jingle.nat.ICETransportManager;

public class JingleManagers{
    
    
    public JingleManagers(Connection xmppConnection){
        
        this.xmppConnection = xmppConnection;
        initializeCalling();
        waitCall();
    }
    

    
    public String getHost(){
        
        return this.xmppConnection.getHost();
    }
    
    private void initializeCalling() {
        
        JingleManager.setJingleServiceEnabled();
        ICETransportManager icetm0 = new ICETransportManager(xmppConnection, xmppConnection.getHost(), 3478);//prima 3478 NOTA il numero di porta è fisso// e deve essere in ascolto sul server(porta x UDP)
        List<JingleMediaManager> mediaManagers = new ArrayList<JingleMediaManager>();
        
//        mediaManagers.add(new JmfMediaManager(icetm0));
        mediaManagers.add(new SpeexMediaManager(icetm0));
        //mediaManagers.add(new ScreenShareMediaManager(icetm0));
        jm = new JingleManager(xmppConnection, mediaManagers);
        jm.addCreationListener(icetm0);
}
    
    private void initializeSharing(){
        
        JingleManager.setJingleServiceEnabled();
        ICETransportManager icetm0 = new ICETransportManager(xmppConnection, xmppConnection.getHost(), 3478);//prima 3478 NOTA il numero di porta è fisso// e deve essere in ascolto sul server(porta x UDP)
        List<JingleMediaManager> mediaManagers = new ArrayList<JingleMediaManager>();
        
//        mediaManagers.add(new JmfMediaManager(icetm0));
//        mediaManagers.add(new SpeexMediaManager(icetm0));
        mediaManagers.add(new ScreenShareMediaManager(icetm0));
        jm = new JingleManager(xmppConnection, mediaManagers);
        jm.addCreationListener(icetm0);
    }
    
    public void hangoutCall(){
        
        if (outgoing != null)
                    try {
                        outgoing.terminate();
                        
                    }
                    catch (XMPPException e1) {
                        JOptionPane.showMessageDialog(null, "Problemi tecnici.", "WildPhone", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    finally {
                        outgoing = null;
                        
                    }
            if (incoming != null)
                    try {
                        incoming.terminate();
                        
                    }
                    catch (XMPPException e1) {
                        JOptionPane.showMessageDialog(null, "Problemi tecnici.", "WildPhone", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    finally {
                        incoming = null; 
                        
                    }
            
            
    }
    
    private void waitCall(){
        
        jm.addJingleSessionRequestListener(new JingleSessionRequestListener() {
            @Override
            public void sessionRequested(JingleSessionRequest request) {

                if (incoming != null)
                    return;

                try {
                    incoming = request.accept();                   
                    incoming.startIncoming();
                    String initiator = incoming.getInitiator();
                    new callFriend(initiator.substring(0,initiator.indexOf("@"))).setVisible(true);
                }
                catch (XMPPException e) {
                    
                    JOptionPane.showMessageDialog(null, "Problemi tecnici.", "WildPhone", JOptionPane.ERROR_MESSAGE); 
                }

            }
        });
    }
    
    public void startCall(String jid){

        if (outgoing != null) return;
                try {
                    outgoing = jm.createOutgoingJingleSession(jid);                  
                    outgoing.startOutgoing(); 
                }
                catch (XMPPException e1) {
                    JOptionPane.showMessageDialog(null, "Problemi tecnici.", "WildPhone", JOptionPane.ERROR_MESSAGE);
                }
            
        }
   
    private Connection xmppConnection;
    private JingleManager jm;
    private JingleSession incoming = null;
    private JingleSession outgoing = null;
    
}

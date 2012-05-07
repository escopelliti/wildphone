/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeUser;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;

/**
 * present e il roster per gli utenti
 * @author leox
 */
public class RosterManager {
        private  Roster rs;
        private  Connection conn;
        //private final RosterEventsListener rosterEventListener;
    public RosterManager (Connection xmppconn){
        try{
            this.conn=xmppconn;
            this.rs=xmppconn.getRoster();
            
        }
        catch (AbstractMethodError e) {
            System.out.println("EXCEPTION:"+e.getMessage());
        }
    }
}

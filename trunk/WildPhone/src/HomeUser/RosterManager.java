/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeUser;

import java.util.ArrayList;
import java.util.Collection;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;

/**
 * present e il roster per gli utenti
 * @author leox
 */
public class RosterManager {
    
        private  Roster rs;
        private  Connection xmppconn;
        private Presence presence;
        
        private ArrayList users;
        //private  RosterEventsListener rosterEventListener;
        Collection<RosterEntry> entries;
        
    public RosterManager (Connection xmppconn){
        
        rs.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
        
        try{
            
            this.xmppconn=xmppconn;
            rs=xmppconn.getRoster();
            entries = rs.getEntries();
            users=(ArrayList)entries; //Casting di entries in un vettore
            //visualizzo nella shell gli utenti collegati
            System.out.println("Utenti Presenti: "+rs.getEntryCount());
            for(RosterEntry entry : entries){
                System.out.println("NAME: "+entry.getName()+"\nUSER: "+entry.getUser()+"\n\n");
            }
        }
        catch (AbstractMethodError e) {
            System.out.println("EXCEPTION:"+e.getMessage());
        }
        
        //Roster Listener
            rs.addRosterListener(new RosterListener() {

            @Override
            public void entriesAdded(Collection<String> addresses) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void entriesUpdated(Collection<String> addresses) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void entriesDeleted(Collection<String> addresses) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void presenceChanged(Presence presence) {
                //throw new UnsupportedOperationException("Not supported yet.");
                System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
            }
        });
    }
}

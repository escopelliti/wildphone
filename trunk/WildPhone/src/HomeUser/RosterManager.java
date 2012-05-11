/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeUser;

import java.util.Collection;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Presence;

/**
 * present e il roster per gli utenti
 * @author leox
 */
public class RosterManager {
    
        private  Roster rs;
        private  Connection xmppconn;
        private Presence presence;
        
        Collection<RosterEntry> entries;
        
    public RosterManager (Connection xmppconn){
        
        rs.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
        
        try{
            this.xmppconn=xmppconn;
            rs=xmppconn.getRoster();
//            entries = rs.getEntries();
//            //visualizzo nella shell gli utenti collegati
//            System.out.println("Utenti Presenti: "+rs.getEntryCount());
//            for(RosterEntry entry : entries){
//                System.out.println("NAME: "+entry.getName()+"\nUSER: "+entry.getUser()+"\n\n");
//            }
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
                //presence.setStatus(null);
                System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
            }
        });
    }
    
    public RosterGroup createGroup (String namegroup){
        RosterGroup group = rs.createGroup(namegroup);
        return group;
    }
    
    public void deleteGroup(){
        
    }
    
    public boolean addFriend(String name, String nickname){
        /* Aggiunge un amico alla propria lista
         * Passando il nome e un nickname che si vuole
         * per identificare l'utente.
         * il name deve essere del tipo"jsmith@example.com"
         * il metodo ritorna True se è stato possibile e False se non è stato
         * possibile inserire l'user.
         */
        try{
            if(!rs.contains(name)){  //Se non è presente nella lista amici
                rs.createEntry(name, nickname, null);
                return true;
            }
            else return false;
        }
        catch(XMPPException e){
            System.out.println("EXCEPTION:"+e);
            return false;
        }
        
    }
    
    public void deleteFriend(RosterEntry entry){
        try{
                rs.removeEntry(entry); // non capisco come potrebbe essere eliminato un user dalla lista
                //vedere un metodo del roster
        }
        catch(XMPPException e){
            System.out.println("EXCEPTION:"+e);
        }
  
    }
    
    public Collection<String> UserOnline(){
        Collection<String> cre=null ;
        for (RosterEntry entry : rs.getEntries())
        {       
                Presence thepresence = rs.getPresence(entry.getUser()+"@"+"server"+xmppconn.getServiceName());
                if(thepresence.isAvailable())
                    cre.add(entry.getUser());//metterei entry.getName() che mi restituisce il nome dato che nella HOME
        }                                   //non serve tutto il full jid ma solo il nome utente;
        return cre;
    }
    
    public Collection<String>  UserOffline(){
        Collection<String> cre=null ;
        for (RosterEntry entry : rs.getEntries())
        {       
                Presence thepresence = rs.getPresence(entry.getUser()/*+"@"+"server*/+"/Smack");
                if(!thepresence.isAway())//cosi credo che restituisci anche quelli che sono available 
                    cre.add(entry.getUser());//anche qui forse è meglio getName();
        }
        return cre;
    }
    
    public Presence GetPresence(String jid){
        /* get the presence of the select user with the jid's user.
         * the jid format is user@domain/resource
         */
        Presence pres = rs.getPresenceResource(jid);
        return pres;
        
    }
    
    public void SetPrecence(){
        /* set the precence at the select jid's user
         * the jid format is user@domain/resource
         */
        
    }
    
    public void SearchUser(){
        
    }
    
    public void searchGroup(){
        
    }

    
}

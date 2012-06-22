/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeUser;

import java.util.Collection;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smackx.search.UserSearchManager;

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
        
        try{
            this.xmppconn = xmppconn;
            rs = xmppconn.getRoster();
            this.presence = new Presence(Presence.Type.available);
            setStatus("Benvenuto su WildPhone.");
            setPresence(Mode.available);
            presence.setPriority(24);
            this.xmppconn.sendPacket(presence);            
//            entries = rs.getEntries();
//            //visualizzo nella shell gli utenti collegati
//            System.out.println("Utenti Presenti: "+rs.getEntryCount());
//            for(RosterEntry entry : entries){
//                System.out.println("NAME: "+entry.getName()+"\nUSER: "+entry.getUser()+"\n\n");
//            }
        }
        catch (AbstractMethodError e) {
            
            JOptionPane.showMessageDialog(null,"Problemi tecnici.", "WildPhone", JOptionPane.ERROR_MESSAGE);
        }
        
        //Roster Listener
//            rs.addRosterListener(new RosterListener() {
//
//            @Override
//            public void entriesAdded(Collection<String> addresses) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void entriesUpdated(Collection<String> addresses) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void entriesDeleted(Collection<String> addresses) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void presenceChanged(Presence presence) {
//                //throw new UnsupportedOperationException("Not supported yet.");
//                //presence.setStatus(null);
//                System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
//            }
//        });
    }
    
    public RosterGroup createGroup (String namegroup){
        RosterGroup group = rs.createGroup(namegroup);
        return group;
    }
    
    public void deleteGroup(){
        
    }
    
    public String getMode(String jid){
        
        return GetPresence(jid).getMode().toString();
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
            JOptionPane.showMessageDialog(null, "Si è verificato un problema.", "WildPhone", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    public String getStatus(String jid){
        
        Presence jidPresence = GetPresence(jid);
        return jidPresence.getStatus();
    }
    
    public void deleteFriend(RosterEntry entry){
        try{
            rs.removeEntry(entry); // non capisco come potrebbe essere eliminato un user dalla lista
                //vedere un metodo del roster
        }
        catch(XMPPException e){
            JOptionPane.showMessageDialog(null, "Si è verificato un problema.", "Wildphone", JOptionPane.ERROR_MESSAGE);
        }
  
    }
    
    public Vector getUserOnline(){
        
        Vector usersOnline = new Vector();
        for (RosterEntry entry : rs.getEntries())
        {       
                Presence thepresence = rs.getPresence(entry.getUser()/*+"@"+"server"+xmppconn.getServiceName()*/+"/Smack");
                if(thepresence.isAvailable()){
                    String jid = entry.getUser();
                    String username = jid.substring(0, jid.indexOf("@"));
                    String mode = null;
                    try{
                        mode = getMode(jid);
                    }
                    catch(Exception ex){
                        mode = "available";
                    }
                    finally{
                        usersOnline.add(username+" - "+getStatus(jid)+" - "+mode);
                    }
                }
        }
        return usersOnline;
    }
    
    public Vector getUserOffline(){
        
        Vector usersOffline = new Vector();
        for (RosterEntry entry : rs.getEntries())
        {       
                Presence thepresence = rs.getPresence(entry.getUser()/*+"@"+"server*/+"/Smack");
                if(!thepresence.isAvailable())
                    usersOffline.add(entry.getName());
        }
        return usersOffline;
    }
    
    public Presence GetPresence(String jid){
        /* get the presence of the select user with the jid's user.
         * the jid format is user@domain/resource
         */
        Presence pres = rs.getPresence(jid);
        return pres;
        
    }
    
    public void setStatus(String text){
        /* set the precence at the select jid's user
         * the jid format is user@domain/resource
         */
        this.presence.setStatus(text);
        this.xmppconn.sendPacket(presence);
    }
    
    public void setPresence(Mode mode){
        
        this.presence.setMode(mode);
        this.xmppconn.sendPacket(presence);
    }
    
    
    
    public void SearchUser(){
        
//        UserSearchManager us = new UserSearchManager(xmppconn);
        
    }
    
    public void searchGroup(){
        
    }

    
}

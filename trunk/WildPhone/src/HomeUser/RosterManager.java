/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeUser;

import java.util.Collection;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.RosterPacket;

/**
 * present e il roster per gli utenti
 * @author leox
 */
public class RosterManager {
    
        private  Roster rs=null;
        private  Connection xmppconn;
        private Presence presence;
        //Collection<RosterEntry> entries;
        
    public RosterManager (Connection xmppconn){
        
        try{
            this.xmppconn = xmppconn;
            rs = xmppconn.getRoster();
            this.presence = new Presence(Presence.Type.available);
            setStatus("Benvenuto su WildPhone.");  //In questo modo viene settato ad ogni avvio questo stato
            setPresence(Mode.available);
            rs.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
            presence.setPriority(24);
            
            this.xmppconn.sendPacket(presence); 
            
        }
        catch (AbstractMethodError e) {
            
            JOptionPane.showMessageDialog(null,"Problemi tecnici.", "WildPhone", JOptionPane.ERROR_MESSAGE);
        }   
    }
    
        public Roster getRoster(){
            return rs;
        }
    
////    public RosterGroup createGroup (String namegroup){
//        RosterGroup group = rs.createGroup(namegroup);
//        return group;
//    }
    
    
    public String getMode(String jid){
         /// Non CAPISCO CHE COSA SIGNIFICA
        String mode = null;
        try{
            mode = GetPresence(jid).getMode().toString();
        }
        catch(Exception ex){
            mode = "available";
        }
        return mode;
    }
    
    public boolean addFriend(String name, String nickname){
        /* Aggiunge un amico alla propria lista
         * Passando il nome e un nickname che si vuole
         * per identificare l'utente.
         * il name deve essere del tipo"jsmith@example.com"
         * il metodo ritorna True se è stato possibile e False se non è stato
         * possibile inserire l'user.
         */
        
////        RosterPacket rp = new RosterPacket();
////        rp.setType(IQ.Type.SET);
////        RosterPacket.Item item = new RosterPacket.Item(name, nickname);
////        item.setItemType(RosterPacket.ItemType.both);
////        item.setItemStatus(RosterPacket.ItemStatus.SUBSCRIPTION_PENDING);
////        rp.addRosterItem(item);
////        xmppconn.sendPacket(rp);
////
////        
////        return true;
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
    
    public void deleteFriend(String user){
        /* Eliminare un amico dato in input il suo indirizzo JID
         * tipo "jsmith@example.com"
         */
        RosterEntry entry = rs.getEntry(user);
        //Mando un pacchetto al server ejabberd in cui indico l'user da eliminare
        RosterPacket rp = new RosterPacket();
        rp.setType(IQ.Type.SET);
        RosterPacket.Item item = new RosterPacket.Item(entry.getUser(), entry.getName());
        item.setItemType(RosterPacket.ItemType.remove);
        rp.addRosterItem(item);
        xmppconn.sendPacket(rp);
//        try{
//            rs.removeEntry(entry); // non capisco come potrebbe essere eliminato un user dalla lista
//                //vedere un metodo del roster
//            
//        }
//        catch(XMPPException e){
//            JOptionPane.showMessageDialog(null, "Si è verificato un problema.", "Wildphone", JOptionPane.ERROR_MESSAGE);
//        }
  
    }
    
    public Vector getUserOnline(){
        
        Vector usersOnline = new Vector();
        for (RosterEntry entry : rs.getEntries())
        {       
                        System.out.println("Presence: " +rs.getPresence(entry.getUser()));
                //Presence thepresence = rs.getPresence(entry.getUser()/*+"@"+"server"+xmppconn.getServiceName()*/+"/Smack");
            Presence thepresence = rs.getPresence(entry.getUser()); 
            System.out.println("presene prelevate da: "+entry.getUser());
            System.out.println("entryes: "+rs.getEntries().toString());
            xmppconn.sendPacket(presence);
            if(thepresence.isAvailable()){
                    String jid = entry.getUser();
                    String username = jid.substring(0, jid.indexOf("@"));
                    String mode = getMode(jid);
                    usersOnline.add(username+" - "+getStatus(jid)+" - "+mode);                   
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
        //this.presence.setType(Presence.Type.available);
        //this.presence.setMode(Presence.Mode.available);
        this.xmppconn.sendPacket(presence);
    }

}

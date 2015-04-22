package HomeUser;

import java.util.Vector;
import javax.swing.JOptionPane;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.RosterPacket;


public class RosterManager {
    //Roster che gestisce vari metodi da e per interazione con il server XMPP
        private  Roster rs = null;
        private  Connection xmppconn;
        private Presence presence;
        //Collection<RosterEntry> entries;
        
        
        //Costruttore del RosterManager
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
            //metodo che da in output il roster corrispondente alla connessione
            return rs;
        }
    
    public String getMode(String jid){
         //metodo in grado di dare il tipo di presenza di un dato JID
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
        
        try{
            if(!rs.contains(name)){  //Se non è presente nella lista amici
                rs.createEntry(name, nickname, null);
                //xmppconn.sendPacket(new Presence(Presence.Type.available, "Cambiato", 128, Mode.available));
                return true;
            }
            return false;
        }
        catch(XMPPException e){
            JOptionPane.showMessageDialog(null, "Si è verificato un problema.", "WildPhone", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    public String getStatus(String jid){
        //Metodo per sapere lo stato scritto dall'utente
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
    }
    
    public Vector getUserOnline(){
        //Metodo per estrapolazione degli utenti online amici del richiedente
        Vector usersOnline = new Vector();
        for (RosterEntry entry : rs.getEntries())
        {       
            System.out.println("Presence: " +rs.getPresence(entry.getUser()));
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
        //Metodo per estrapolazione degli utenti offline amici del richiedente
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
        //Metodo simile a getMod solo che rilascia un 
        //oggetto Presence al contrario di getMod che rilascia stinga
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
        //metodo per il settaggio della propria presenza passandola da input
        this.presence.setMode(mode);
        //this.presence.setType(Presence.Type.available);
        //this.presence.setMode(Presence.Mode.available);
        this.xmppconn.sendPacket(presence);
    }

}

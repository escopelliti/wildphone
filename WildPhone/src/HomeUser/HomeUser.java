package HomeUser;

import Jingle.JingleManagers;
import Authentication.authenticationFrame;
import java.util.Collection;
import javax.swing.JOptionPane;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;

/**
 *
 * @author ninux
 */
public class HomeUser extends javax.swing.JFrame { //implement runnable
    
   // private Thread refresher;
    private Connection conn;
    private RosterManager rm;
//    private String[] list;
    /** Creates new form HomeUser */
    
    
    public HomeUser(Connection conn) throws InterruptedException {
        
        this.conn = conn;
        this.jingleManager = new JingleManagers(this.conn);
        
       initSystem();
       //RosterListener sta in ascolto del proprio roster, 
       //per eventuali mutamenti di stato,presenza oppure cancellazioni e update di user
       RosterListener rl = new RosterListener() {

            @Override
            public void entriesAdded(Collection<String> addresses) {
                //viene eseguito si è aggiunti da qualche user come amici
                 FriendsList.setListData(rm.getUserOnline());
                 System.out.println("Entry Aggiunta");
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void entriesUpdated(Collection<String> addresses) {
                //viene eseguito se si scatenato un cambiamento di stato,presence ecc di un amico aggiunto
                 FriendsList.setListData(rm.getUserOnline());
                 System.out.println("Entry Update");
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void entriesDeleted(Collection<String> addresses) {
                //viene eseguito se si è stati eliminati da un user amico
                 FriendsList.setListData(rm.getUserOnline());
                 System.out.println("Entry Delete"+addresses.toString());
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void presenceChanged(Presence presence) {
                //viene eseguito ogni qual volta un user cambia la propria presenza
                FriendsList.setListData(rm.getUserOnline());
                System.out.println("Entry Presence Change");
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
       //collega al proprio roster il rosterlistener messo in ascolto
       rm.getRoster().addRosterListener(rl);
       
//        String[] online = (String[]) rm.getUserOnline().toArray();
//        String[] offline = (String[]) rm.getUserOffline().toArray();
//        System.out.println("4");
//        list = new String[(online.length + offline.length + 3)];
//        list[1] = "UTENTI ONLINE";
//        int i = 2;
//        System.out.println("5");
//        for(int j = 0; j < list.length; j++) {
//            list[i] = online[j];
//            i++;
//        }
//        
//        list[i] = "UTENTI OFFLINE";
//        
//        for(int j = 0; j < list.length; j++) {
//            list[i] = offline[j];
//            i++;
//        }
        
        
        /* model della lista amici: new javax.swing.AbstractListModel() {
    String[] list;
    public int getSize() { return list.length; }
    public Object getElementAt(int i) { return list[i]; }
}*/
        
        initComponents();
        
    }
    
    private void initSystem() throws InterruptedException{
        
        String user = conn.getUser();
        username = user.substring(0, user.indexOf("@"));
        rm = new RosterManager(conn);
        //Thread.sleep(3000);
        status = rm.getStatus(user);
        mode = rm.getMode(user);
        //refresher = new Thread(this);
        //refresher.start();
    }
    
//    @Override
//    public void run(){
//        
//        while(true){
//            try{
//                refresher.sleep(12000);        //due minuti    
//                FriendsList.setListData(rm.getUserOnline());
//                System.out.println("utenti online"+ rm.getUserOnline().toString());
//        }catch(InterruptedException ex){
//            
//            JOptionPane.showMessageDialog(null, "Problemi tecnici. " + ex, "WildPhone", JOptionPane.ERROR_MESSAGE);
//        }
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        FriendsList = new javax.swing.JList();
        call = new javax.swing.JButton();
        Username = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        remove = new javax.swing.JButton();
        modeComboBox = new javax.swing.JComboBox();
        changeStatus = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        logout = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        contatti = new javax.swing.JMenu();
        add = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FriendsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(FriendsList);

        call.setText("Chiama");
        call.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                callActionPerformed(evt);
            }
        });

        Username.setText(username);

        statusField.setEditable(false);
        statusField.setText(status);
        statusField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statusFieldMouseClicked(evt);
            }
        });

        remove.setText("Elimina");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        modeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "available", "away", "dnd", "xa", "chat" }));
        modeComboBox.setSelectedItem(mode);
        modeComboBox.setToolTipText("");
        modeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeComboBoxActionPerformed(evt);
            }
        });

        changeStatus.setText("Cambia");
        changeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeStatusActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        logout.setText("Disconnetti");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jMenu1.add(logout);

        exit.setText("Esci");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        contatti.setText("Contatti");

        add.setText("Aggiungi");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        contatti.add(add);

        jMenuBar1.add(contatti);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(call)
                        .addGap(80, 80, 80)
                        .addComponent(remove))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(changeStatus)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusField)
                        .addComponent(changeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(call)
                    .addComponent(remove))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void callActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_callActionPerformed
    /*
     * Inizia una chiamata con un amico presente nella propria lista contatti
     */
    if(!FriendsList.isSelectionEmpty()){
        String username = FriendsList.getSelectedValue().toString();
        new callFriend(this.jingleManager,username.substring(0, username.indexOf(" "))).setVisible(true); 
    }
}//GEN-LAST:event_callActionPerformed

private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
    
    conn.disconnect(new Presence(Presence.Type.unavailable));
    System.exit(0);
}//GEN-LAST:event_exitActionPerformed

private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
    
    conn.disconnect(new Presence(Presence.Type.unavailable));
    this.dispose();
    new authenticationFrame().setVisible(true);
}//GEN-LAST:event_logoutActionPerformed

private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
    /*
     * Apre il frame che mi consente di aggiugere un nuovo amico alla mia lista
     * contatti
     */
    new addFriend(this.rm,this.conn.getHost()).setVisible(true);
}//GEN-LAST:event_addActionPerformed

private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed

    if(!FriendsList.isSelectionEmpty()){
        String toRemove = FriendsList.getSelectedValue().toString();
        Object[] opt = {"   Si   ", "   No   "};
        int ans = JOptionPane.showOptionDialog(null, "Sei sicuro di volerlo eliminare? "
                     , "Conferma - WildPhone",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     opt,
                     opt[0]);
        if(ans == 0) {
            //System.out.println(" da eliminare"+toRemove.substring(0, toRemove.indexOf(" "))+"@"+conn.getHost());
           this.rm.deleteFriend(toRemove.substring(0, toRemove.indexOf(" "))+"@"+conn.getHost());
        }
    }
}//GEN-LAST:event_removeActionPerformed

    private void statusFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusFieldMouseClicked
        
        statusField.setEditable(true);
    }//GEN-LAST:event_statusFieldMouseClicked

    private void modeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeComboBoxActionPerformed
        
        Mode mode = Mode.valueOf(modeComboBox.getSelectedItem().toString());
        this.rm.setPresence(mode);
    }//GEN-LAST:event_modeComboBoxActionPerformed

    private void changeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeStatusActionPerformed
        
        this.rm.setStatus(statusField.getText());
        this.statusField.setEditable(false);
    }//GEN-LAST:event_changeStatusActionPerformed

    private JingleManagers jingleManager;
    private String mode;
    private String status;
    private String username;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList FriendsList;
    private javax.swing.JLabel Username;
    private javax.swing.JMenuItem add;
    private javax.swing.JButton call;
    private javax.swing.JButton changeStatus;
    private javax.swing.JMenu contatti;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem logout;
    private javax.swing.JComboBox modeComboBox;
    private javax.swing.JButton remove;
    private javax.swing.JTextField statusField;
    // End of variables declaration//GEN-END:variables
}

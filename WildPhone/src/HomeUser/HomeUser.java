package HomeUser;

import javax.swing.JOptionPane;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.packet.Presence;

/**
 *
 * @author ninux
 */
public class HomeUser extends javax.swing.JFrame implements Runnable{
    
    private Thread refresher;
    private Connection conn;
    private RosterManager rm;
//    private String[] list;
    /** Creates new form HomeUser */
    
    
    public HomeUser(Connection conn) throws InterruptedException {
        
        this.conn = conn;
        
        String user = conn.getUser();
        username = user.substring(0, user.indexOf("@"));
        rm = new RosterManager(conn);
        Thread.sleep(10000);
        refresher = new Thread(this);
        refresher.start();
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
    
    @Override
    public void run(){
        
        while(true){
            try{
                refresher.sleep(2000);            
                FriendsList.setListData(rm.getUserOnline());
        }catch(InterruptedException ex){
            
            JOptionPane.showMessageDialog(null, "Problemi tecnici.", "ACES", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        FriendsList = new javax.swing.JList();
        call = new javax.swing.JButton();
        Username = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        remove = new javax.swing.JButton();
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

        remove.setText("Elimina");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
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
                        .addComponent(Username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(call)
                        .addGap(80, 80, 80)
                        .addComponent(remove)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(call)
                    .addComponent(remove))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void callActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_callActionPerformed
    /*
     * Inizia una chiamata con un amico presente nella propria lista contatti
     */
    new callFriend(conn).setVisible(true);
}//GEN-LAST:event_callActionPerformed

private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
    
    conn.disconnect(new Presence(Presence.Type.unavailable));
    System.exit(0);
}//GEN-LAST:event_exitActionPerformed

private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
    
    conn.disconnect(new Presence(Presence.Type.unavailable));
    this.dispose();
    //aprire una nuovo frame di login
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
//            this.rm.deleteFriend(toRemove);
        }
    }
}//GEN-LAST:event_removeActionPerformed

    private String username;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList FriendsList;
    private javax.swing.JLabel Username;
    private javax.swing.JMenuItem add;
    private javax.swing.JButton call;
    private javax.swing.JMenu contatti;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem logout;
    private javax.swing.JButton remove;
    private javax.swing.JTextField status;
    // End of variables declaration//GEN-END:variables
}

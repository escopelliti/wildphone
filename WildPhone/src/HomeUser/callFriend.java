package HomeUser;

import Jingle.JingleManagers;

public class callFriend extends javax.swing.JFrame {
    
    public callFriend(JingleManagers jingleManager,String username) {
  
        initComponents();
        this.jingleManager = jingleManager;
        this.jingleManager.startCall(username+"@"+jingleManager.getHost()+"/Smack");
        setLabels(username);
    }
    
    public callFriend(String username){
        
        initComponents();
        setLabels(username);
    }
    
    private void setLabels(String user){
        
        //immagine standard o logo dell'applicazione
        nameFriendLabel.setText(user);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hangoutButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nameFriendLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        hangoutButton.setText("Riaggancia");
        hangoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hangoutButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("In chiamata con:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(nameFriendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(hangoutButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(hangoutButton)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(nameFriendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hangoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hangoutButtonActionPerformed
           
        this.jingleManager.hangoutCall();
        this.dispose();
    }//GEN-LAST:event_hangoutButtonActionPerformed
    
    private JingleManagers jingleManager;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hangoutButton;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nameFriendLabel;
    // End of variables declaration//GEN-END:variables
}

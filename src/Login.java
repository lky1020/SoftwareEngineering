

import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private String name;
    private String password;
    
    public Login() {
        this.setTitle("Clinic Recording System");
        initComponents();
        setLocationRelativeTo(null);
        jtfName.grabFocus();
        
        //set the background to transparent
        jtfName.setBackground(new java.awt.Color(0, 0, 0, 1));
        jpfPassword.setBackground(new java.awt.Color(0, 0, 0, 1));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogin = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jtfName = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();
        jCheckBoxPassword = new javax.swing.JCheckBox();
        jButtonLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabelLogin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogin.setText("Login");

        jLabelPassword.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPassword.setText("Password");

        jLabelName.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelName.setText("Name");

        jtfName.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jtfName.setForeground(new java.awt.Color(153, 153, 153));
        jtfName.setToolTipText("");
        jtfName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jtfName.setOpaque(false);

        jpfPassword.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jpfPassword.setForeground(new java.awt.Color(153, 153, 153));
        jpfPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jpfPassword.setOpaque(false);

        jCheckBoxPassword.setText("Show Password");
        jCheckBoxPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPasswordActionPerformed(evt);
            }
        });

        jButtonLogin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpfPassword)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jCheckBoxPassword)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jCheckBoxPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonLogin)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPasswordActionPerformed
        // TODO add your handling code here:
        if(jCheckBoxPassword.isSelected()){
            jpfPassword.setEchoChar((char)0);
        }
        else{
            jpfPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBoxPasswordActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        // TODO add your handling code here:
        //Get Name & Password
        if((jtfName.getText()).isEmpty() && (jpfPassword.getPassword()).length == 0){
            
            JOptionPane.showMessageDialog(null, "Please Enter Name & Password !!!", "Error Name & Password", JOptionPane.ERROR_MESSAGE);
            
        }
        else if((jtfName.getText()).isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Please Enter Name !!!", "Error Name", JOptionPane.ERROR_MESSAGE);
            
        }
        else if((jpfPassword.getPassword()).length == 0){
            
            JOptionPane.showMessageDialog(null, "Please Enter Password !!!", "Error Password", JOptionPane.ERROR_MESSAGE);
            
        }
        else{
            
            name = jtfName.getText();
            password = jpfPassword.getText();
        
        }
        
    }//GEN-LAST:event_jButtonLoginActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JCheckBox jCheckBoxPassword;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JTextField jtfName;
    // End of variables declaration//GEN-END:variables
}

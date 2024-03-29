
package patient;

import main.Home;
import Class.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PatientModify extends javax.swing.JFrame {

    private PatientValidation validate = new PatientValidation();
    private List<Patient> patientOnHoldList = new ArrayList<>();
    private Patient oldPatient;
    private Patient patient;
    private String staffName;
    private javax.swing.JFrame frame;
    
    public PatientModify() {
        initComponents();
    }
    
    public PatientModify(String staffName, Patient patient, javax.swing.JFrame frame, List<Patient> patientOnHoldList) {
        initComponents();
        
        this.staffName = staffName;
        
        oldPatient = new Patient(patient.getIcNo(), patient.getIc(), patient.getName(), patient.getMobileNo(), patient.getDateCreated(), patient.getMedicalDescription());
        this.patient = patient;
        
        this.frame = frame;
        this.patientOnHoldList = patientOnHoldList;
        
        //set the background to transparent
        icOldTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        icNewTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        nameOldTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        nameNewTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        mobileNoOldTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        mobileNoNewTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        //Initialize patient old info
        icOldTextField.setText(patient.getIc());
        nameOldTextField.setText(patient.getName());
        mobileNoOldTextField.setText(patient.getMobileNo());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameTitle = new javax.swing.JLabel();
        icLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        mobileNoLabel = new javax.swing.JLabel();
        icOldTextField = new javax.swing.JTextField();
        nameOldTextField = new javax.swing.JTextField();
        mobileNoOldTextField = new javax.swing.JTextField();
        icArrowLabel = new javax.swing.JLabel();
        NameArrowLabel = new javax.swing.JLabel();
        mobileNoArrowLabel = new javax.swing.JLabel();
        icNewTextField = new javax.swing.JTextField();
        nameNewTextField = new javax.swing.JTextField();
        mobileNoNewTextField = new javax.swing.JTextField();
        modifyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Modify Patient Record");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        frameTitle.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        frameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        frameTitle.setText("Please Enter New Info for Modification");
        frameTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                frameTitleMouseDragged(evt);
            }
        });
        frameTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                frameTitleMousePressed(evt);
            }
        });

        icLabel.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        icLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        icLabel.setText("IC : ");

        nameLabel.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel.setText("Name : ");

        mobileNoLabel.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        mobileNoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mobileNoLabel.setText("Mobile No : ");

        icOldTextField.setEditable(false);
        icOldTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        icOldTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        nameOldTextField.setEditable(false);
        nameOldTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        nameOldTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        mobileNoOldTextField.setEditable(false);
        mobileNoOldTextField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        mobileNoOldTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        icArrowLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N

        NameArrowLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N

        mobileNoArrowLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N

        icNewTextField.setBackground(new java.awt.Color(60, 63, 65));
        icNewTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        icNewTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        nameNewTextField.setBackground(new java.awt.Color(60, 63, 65));
        nameNewTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        nameNewTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        mobileNoNewTextField.setBackground(new java.awt.Color(60, 63, 65));
        mobileNoNewTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        mobileNoNewTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        modifyButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        modifyButton.setText("Modify");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(icLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobileNoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(icOldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(icArrowLabel)
                        .addGap(18, 18, 18)
                        .addComponent(icNewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameOldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NameArrowLabel)
                        .addGap(18, 18, 18)
                        .addComponent(nameNewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mobileNoOldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mobileNoArrowLabel)
                        .addGap(18, 18, 18)
                        .addComponent(mobileNoNewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addComponent(frameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(frameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(icLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(icOldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(icArrowLabel)
                            .addComponent(icNewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameOldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(NameArrowLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameNewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mobileNoArrowLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mobileNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mobileNoOldTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mobileNoNewTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        // TODO add your handling code here:
        
        boolean modifySuccess = false;
        
        //IC only
        if(!icNewTextField.getText().equals("") && nameNewTextField.getText().equals("") && mobileNoNewTextField.getText().equals("")){
            
            if(validate.validateIc(icNewTextField.getText())){
                int icNo = Integer.parseInt(icNewTextField.getText().substring(10));
                
                patient.setIcNo(icNo);
                patient.setIc(icNewTextField.getText());
                
                modifySuccess = true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
            
            }
        }
        //Name only
        else if(icNewTextField.getText().equals("") && !nameNewTextField.getText().equals("") && mobileNoNewTextField.getText().equals("")){
            
            if(validate.validateName(nameNewTextField.getText())){
                
                patient.setName(nameNewTextField.getText());
                
                modifySuccess = true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            
            }
        }
        //Mobile no only
        else if(icNewTextField.getText().equals("") && nameNewTextField.getText().equals("") && !mobileNoNewTextField.getText().equals("")){
            
            if(validate.validateMobileNo(mobileNoNewTextField.getText())){
                
                patient.setMobileNo(mobileNoNewTextField.getText());
                
                modifySuccess = true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        //IC, Name
        else if(!icNewTextField.getText().equals("") && !nameNewTextField.getText().equals("") && mobileNoNewTextField.getText().equals("")){
            
            if(validate.validateIc(icNewTextField.getText()) && validate.validateName(nameNewTextField.getText())){
                int icNo = Integer.parseInt(icNewTextField.getText().substring(10));
                
                patient.setIcNo(icNo);
                patient.setIc(icNewTextField.getText());
                patient.setName(nameNewTextField.getText());

                modifySuccess = true;
                
            }else{
                
                if(validate.validateIc(icNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                
                }
                
                if(validate.validateName(nameNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        //IC, Mobile No
        else if(!icNewTextField.getText().equals("") && nameNewTextField.getText().equals("") && !mobileNoNewTextField.getText().equals("")){
            
            if(validate.validateIc(icNewTextField.getText()) && validate.validateMobileNo(mobileNoNewTextField.getText())){
                int icNo = Integer.parseInt(icNewTextField.getText().substring(10));
                
                patient.setIcNo(icNo);
                patient.setIc(icNewTextField.getText());
                patient.setMobileNo(mobileNoNewTextField.getText());
                
                modifySuccess = true;
                
            }else{
                
                if(validate.validateIc(icNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                
                }
                
                if(validate.validateMobileNo(mobileNoNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        //Name, Mobile No
        else if(icNewTextField.getText().equals("") && !nameNewTextField.getText().equals("") && !mobileNoNewTextField.getText().equals("")){
            
            if(validate.validateName(nameNewTextField.getText()) && validate.validateMobileNo(mobileNoNewTextField.getText())){
                
                patient.setName(nameNewTextField.getText());
                patient.setMobileNo(mobileNoNewTextField.getText());
                
                modifySuccess = true;
                
            }else{
                
                if(validate.validateName(nameNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(mobileNoNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        //IC, Name, Mobile No
        else if(!icNewTextField.getText().equals("") && !nameNewTextField.getText().equals("") && !mobileNoNewTextField.getText().equals("")){
            
            if(validate.validateIc(icNewTextField.getText()) && validate.validateName(nameNewTextField.getText()) && validate.validateMobileNo(mobileNoNewTextField.getText())){
                int icNo = Integer.parseInt(icNewTextField.getText().substring(10));
                
                patient.setIcNo(icNo);
                patient.setIc(icNewTextField.getText());
                patient.setName(nameNewTextField.getText());
                patient.setMobileNo(mobileNoNewTextField.getText());

                modifySuccess = true;
 
            }else{
                
                if(validate.validateIc(icNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                
                }
                
                if(validate.validateName(nameNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(mobileNoNewTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        //No Input
        else if(icNewTextField.getText().equals("") && nameNewTextField.getText().equals("") && mobileNoNewTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter IC / Name / Mobile No of Patient to modify !!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            
        }
        
        if(modifySuccess){
            Home home = new Home(staffName, oldPatient, patient, patientOnHoldList);
            home.setVisible(true);
            frame.dispose();
            this.dispose();
        }
        
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        
        JOptionPane.showMessageDialog(null, "Patient's Record Cancel Modify!!!", "Cancel Modification", JOptionPane.INFORMATION_MESSAGE);
        
        Home home = new Home(staffName, patientOnHoldList);
        home.setVisible(true);
        frame.dispose();
        this.dispose();
        
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    static int xx, yy;
    private void frameTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameTitleMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_frameTitleMousePressed

    private void frameTitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameTitleMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x-xx, y-yy);
    }//GEN-LAST:event_frameTitleMouseDragged

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(PatientModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientModify().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameArrowLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel frameTitle;
    private javax.swing.JLabel icArrowLabel;
    private javax.swing.JLabel icLabel;
    private javax.swing.JTextField icNewTextField;
    private javax.swing.JTextField icOldTextField;
    private javax.swing.JLabel mobileNoArrowLabel;
    private javax.swing.JLabel mobileNoLabel;
    private javax.swing.JTextField mobileNoNewTextField;
    private javax.swing.JTextField mobileNoOldTextField;
    private javax.swing.JButton modifyButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameNewTextField;
    private javax.swing.JTextField nameOldTextField;
    // End of variables declaration//GEN-END:variables
}

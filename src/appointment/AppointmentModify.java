/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointment;

import java.text.SimpleDateFormat;
import Class.Appointment;
import Class.AppointmentValidation;
import javax.swing.JOptionPane;
import main.Home;
/**
 *
 * @author KXian
 */
public class AppointmentModify extends javax.swing.JFrame {
    private Appointment oldAppointment;
    private Appointment appointment;
    private String staffName;
    private javax.swing.JFrame frame;
    private AppointmentValidation validate = new AppointmentValidation();
    
    SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
    /**
     * Creates new form AppointmentModify
     */
    public AppointmentModify() {
        initComponents();
    }
    
    public AppointmentModify(String staffName, Appointment appointment, javax.swing.JFrame frame){
        initComponents();
        
        this.staffName = staffName;
        
        oldAppointment = new Appointment(appointment.getIcNo(), appointment.getPatientName(), appointment.getPatientMobileNo(), appointment.getStaffIncharge(), appointment.getAppointmentDate());
        this.appointment = appointment;
        
        this.frame = frame;
        
        //set the background to transparent
        oldNameTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        newNameTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        oldDateTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        newDateChooser.setBackground(new java.awt.Color(0, 0, 0, 1));
        oldMobileNoTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        newMobileNoTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        //Initialize patient old info
        oldNameTextField.setText(appointment.getPatientName());
        oldDateTextField.setText(appointment.getAppointmentDate());
        oldMobileNoTextField.setText(appointment.getPatientMobileNo());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modifyTitleLabel = new javax.swing.JLabel();
        ICLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        mobileNumberLabel = new javax.swing.JLabel();
        oldNameTextField = new javax.swing.JTextField();
        oldDateTextField = new javax.swing.JTextField();
        oldMobileNoTextField = new javax.swing.JTextField();
        icArrowLabel1 = new javax.swing.JLabel();
        icArrowLabel2 = new javax.swing.JLabel();
        icArrowLabel3 = new javax.swing.JLabel();
        newNameTextField = new javax.swing.JTextField();
        newMobileNoTextField = new javax.swing.JTextField();
        cancelBtn1 = new javax.swing.JButton();
        modifyBtn = new javax.swing.JButton();
        newDateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        modifyTitleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        modifyTitleLabel.setText("Please Enter New Info For Modification");

        ICLabel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        ICLabel.setText("Appointment Date : ");

        nameLabel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        nameLabel.setText("Name : ");

        mobileNumberLabel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        mobileNumberLabel.setText("Mobile Number : ");

        oldNameTextField.setEditable(false);

        oldDateTextField.setEditable(false);

        oldMobileNoTextField.setEditable(false);

        icArrowLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N

        icArrowLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N

        icArrowLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrowRight.png"))); // NOI18N

        cancelBtn1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        cancelBtn1.setText("Cancel");
        cancelBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtn1ActionPerformed(evt);
            }
        });

        modifyBtn.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        modifyBtn.setText("Modify");
        modifyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyBtnActionPerformed(evt);
            }
        });

        newDateChooser.setDateFormatString("dd-MM-yyyy");
        newDateChooser.setDoubleBuffered(false);
        newDateChooser.setMaxSelectableDate(new java.util.Date(253370739701000L));
        newDateChooser.setMinSelectableDate(new java.util.Date(946659701000L));
        newDateChooser.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ICLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mobileNumberLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(oldNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(icArrowLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(newNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(oldDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(icArrowLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(newDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(oldMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(icArrowLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(newMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(modifyTitleLabel)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(modifyBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBtn1)
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(modifyTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ICLabel)
                        .addComponent(oldDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icArrowLabel1)
                    .addComponent(newDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(oldNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icArrowLabel3)
                    .addComponent(newNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mobileNumberLabel)
                        .addComponent(oldMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icArrowLabel2)
                    .addComponent(newMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn1)
                    .addComponent(modifyBtn))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtn1ActionPerformed
        Home home = new Home(staffName, oldAppointment, appointment);
        home.setVisible(true);
        frame.dispose();
        this.dispose();
    }//GEN-LAST:event_cancelBtn1ActionPerformed

    private void modifyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyBtnActionPerformed
        // TODO add your handling code here:

        boolean modifySuccess = false;

        //Patient Name Only
        if(!newNameTextField.getText().equals("") && newDateChooser.getDate()== null && newMobileNoTextField.getText().equals("")){
            
            if(validate.validateName(newNameTextField.getText())){
                
                appointment.setPatientName(newNameTextField.getText());
                
                modifySuccess = true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            
            }
        }
        //Date Only
        else if(newNameTextField.getText().equals("") && newDateChooser.getDate()!= null && newMobileNoTextField.getText().equals("")){

            appointment.setAppointmentDate(s.format(newDateChooser.getDate()));

            modifySuccess = true;
        }
        //Mobile number Only
        else if(newNameTextField.getText().equals("") && newDateChooser.getDate()== null && !newMobileNoTextField.getText().equals("")){

            if(validate.validateMobileNo(newMobileNoTextField.getText())){
                
                appointment.setPatientMobileNo(newMobileNoTextField.getText());
                
                modifySuccess = true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        //Name, Date
        else if(!newNameTextField.getText().equals("") && newDateChooser.getDate()!= null && newMobileNoTextField.getText().equals("")){
            if(validate.validateName(newNameTextField.getText())){
                
                appointment.setPatientName(newNameTextField.getText());
                appointment.setAppointmentDate(s.format(newDateChooser.getDate()));
                
                modifySuccess = true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            
            }

        }
        //Name, Mobile Number
        else if(!newNameTextField.getText().equals("") && newDateChooser.getDate()== null && !newMobileNoTextField.getText().equals("")){

            if(validate.validateName(newNameTextField.getText()) && validate.validateMobileNo(newMobileNoTextField.getText())){
                
                appointment.setPatientName(newNameTextField.getText());
                appointment.setPatientMobileNo(newMobileNoTextField.getText());
                
                
                modifySuccess = true;
                
            }else{
                if(validate.validateName(newNameTextField.getText()) != true){
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                }
                if(validate.validateMobileNo(newMobileNoTextField.getText()) != true){
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                }
            
            }
        }
        //Date, Mobile No
        else if(newNameTextField.getText().equals("") && newDateChooser.getDate()!= null && !newMobileNoTextField.getText().equals("")){

            if(validate.validateMobileNo(newMobileNoTextField.getText())){
                
                appointment.setPatientMobileNo(newMobileNoTextField.getText());
                appointment.setAppointmentDate(s.format(newDateChooser.getDate()));
                
                modifySuccess = true;
                
            }else{
                
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        //Name, Date, Mobile Number
        else if(!newNameTextField.getText().equals("") && newDateChooser.getDate()!= null && !newMobileNoTextField.getText().equals("")){

            if(validate.validateName(newNameTextField.getText()) && validate.validateMobileNo(newMobileNoTextField.getText())){
                
                appointment.setPatientName(newNameTextField.getText());
                appointment.setPatientMobileNo(newMobileNoTextField.getText());
                appointment.setAppointmentDate(s.format(newDateChooser.getDate()));
                
                modifySuccess = true;
                
            }else{
                if(validate.validateName(newNameTextField.getText()) != true){
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                }
                if(validate.validateMobileNo(newMobileNoTextField.getText()) != true){
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                }
            
            }
        }
        //No Input
        else if(newNameTextField.getText().equals("") && newDateChooser.getDate()== null && newMobileNoTextField.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Please Enter Name / Date / Mobile Number of Appointment to modify !!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);

        }

        if(modifySuccess){
            Home home = new Home(staffName, oldAppointment, appointment);
            home.setVisible(true);
            frame.dispose();
            this.dispose();
        }
    }//GEN-LAST:event_modifyBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AppointmentModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentModify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentModify().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ICLabel;
    private javax.swing.JButton cancelBtn1;
    private javax.swing.JLabel icArrowLabel1;
    private javax.swing.JLabel icArrowLabel2;
    private javax.swing.JLabel icArrowLabel3;
    private javax.swing.JLabel mobileNumberLabel;
    private javax.swing.JButton modifyBtn;
    private javax.swing.JLabel modifyTitleLabel;
    private javax.swing.JLabel nameLabel;
    private com.toedter.calendar.JDateChooser newDateChooser;
    private javax.swing.JTextField newMobileNoTextField;
    private javax.swing.JTextField newNameTextField;
    private javax.swing.JTextField oldDateTextField;
    private javax.swing.JTextField oldMobileNoTextField;
    private javax.swing.JTextField oldNameTextField;
    // End of variables declaration//GEN-END:variables
}
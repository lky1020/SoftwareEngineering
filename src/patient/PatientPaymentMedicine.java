
package patient;

import Class.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.Home;

public class PatientPaymentMedicine extends javax.swing.JFrame {

    private List<Patient> patientOnHoldList = new ArrayList<>();
    private Patient oldPatient;
    private Patient patient;
    private String staffName;
    private javax.swing.JFrame frame;
    private DefaultTableModel medicineAvailableModel;
    private DefaultTableModel medicineGivenModel;
    private List<Medicine> medicineAvailableList = new ArrayList<>();;
    private List<Medicine> medicineGivenList = new ArrayList<>();;
    private Medicine medicine;
    private Medicine medicineRemove;
    
    /**
     * Creates new form PatientPayment
     */
    public PatientPaymentMedicine() {
        initComponents();
    }

    public PatientPaymentMedicine(String staffName, Patient patient, javax.swing.JFrame frame, List<Patient> patientOnHoldList, List<Medicine> medicineAvailableList){
        initComponents();
        
        this.staffName = staffName;
        
        oldPatient = new Patient(patient.getIcNo(), patient.getIc(), patient.getName(), patient.getMobileNo(), patient.getDateCreated(), patient.getMedicalDescription());
        this.patient = patient;
        
        this.frame = frame;
        this.patientOnHoldList = patientOnHoldList;

        this.patientPaymentMedicineTitleLabel.setText(this.patientPaymentMedicineTitleLabel.getText() + this.patient.getName());
        
        //Set table model
        medicineAvailableModel = (DefaultTableModel)patientPaymentMedicineMedicineAvailableTable.getModel();
        medicineGivenModel = (DefaultTableModel)patientPaymentMedicineMedicineGivenTable.getModel();
        
        //Hardcode medicine available
        /*Medicine medicine1 = new Medicine("1001", "Panadol", 50, 10.00, "07-07-2023");
        medicineAvailableList.add(medicine1);
        Medicine medicine2 = new Medicine("1002", "Cough", 50, 12.00, "07-07-2021");
        medicineAvailableList.add(medicine2);
        Medicine medicine3 = new Medicine("1003", "Fewer", 50, 7.00, "21-06-2021");
        medicineAvailableList.add(medicine3);
        Medicine medicine4 = new Medicine("1004", "Sick", 50, 20.00, "20-11-2022");
        medicineAvailableList.add(medicine4);*/
        
        this.medicineAvailableList = medicineAvailableList;
        
        setMedicineAvailableModelRow(medicineAvailableList);
    }
    
    private void setMedicineAvailableModelRow(List<Medicine> medicineAvailableList){

        medicineAvailableModel.setRowCount(0);
        
        for(int i = 0; i < medicineAvailableList.size(); i++){
            
             medicineAvailableModel.addRow(new Object[]{medicineAvailableList.get(i).getId(), medicineAvailableList.get(i).getMedicineName(), String.format("%06.2f", medicineAvailableList.get(i).getUnitPrice())});
        
        }   
    }
    
    private void setMedicineGivenModelRow(List<Medicine> medicineGiven){

        medicineGivenModel.setRowCount(0);
        
        for(int i = 0; i < medicineGiven.size(); i++){
            
             medicineGivenModel.addRow(new Object[]{medicineGiven.get(i).getId(), medicineGiven.get(i).getMedicineName(), String.format("%06.2f", medicineGiven.get(i).getUnitPrice())});
        
        }   
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        patientPaymentMedicineTitleLabel = new javax.swing.JLabel();
        patientPaymentMedicineMedicineAvailableScrollPane = new javax.swing.JScrollPane();
        patientPaymentMedicineMedicineAvailableTable = new javax.swing.JTable();
        patientPaymentMedicineAddMedicineButton = new javax.swing.JButton();
        patientPaymentMedicineRemoveMedicineButton = new javax.swing.JButton();
        patientPaymentMedicineMedicineAvailableLabel = new javax.swing.JLabel();
        patientPaymentMedicineMedicineGivenLabel = new javax.swing.JLabel();
        patientPaymentMedicineMedicineGivenScrollPane = new javax.swing.JScrollPane();
        patientPaymentMedicineMedicineGivenTable = new javax.swing.JTable();
        patientPaymentMedicinePayButton = new javax.swing.JButton();
        patientPaymentMedicineCancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        patientPaymentMedicineTitleLabel.setFont(new java.awt.Font(".Heiti J", 1, 24)); // NOI18N
        patientPaymentMedicineTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        patientPaymentMedicineTitleLabel.setText("Medicine of ");

        patientPaymentMedicineMedicineAvailableTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Unit Price (RM)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        patientPaymentMedicineMedicineAvailableTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientPaymentMedicineMedicineAvailableTableMouseClicked(evt);
            }
        });
        patientPaymentMedicineMedicineAvailableScrollPane.setViewportView(patientPaymentMedicineMedicineAvailableTable);
        if (patientPaymentMedicineMedicineAvailableTable.getColumnModel().getColumnCount() > 0) {
            patientPaymentMedicineMedicineAvailableTable.getColumnModel().getColumn(0).setResizable(false);
            patientPaymentMedicineMedicineAvailableTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            patientPaymentMedicineMedicineAvailableTable.getColumnModel().getColumn(1).setResizable(false);
            patientPaymentMedicineMedicineAvailableTable.getColumnModel().getColumn(2).setResizable(false);
        }

        patientPaymentMedicineAddMedicineButton.setText("Add →");
        patientPaymentMedicineAddMedicineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientPaymentMedicineAddMedicineButtonActionPerformed(evt);
            }
        });

        patientPaymentMedicineRemoveMedicineButton.setText("← Remove");
        patientPaymentMedicineRemoveMedicineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientPaymentMedicineRemoveMedicineButtonActionPerformed(evt);
            }
        });

        patientPaymentMedicineMedicineAvailableLabel.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientPaymentMedicineMedicineAvailableLabel.setText("Medicine Available");
        patientPaymentMedicineMedicineAvailableLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        patientPaymentMedicineMedicineGivenLabel.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientPaymentMedicineMedicineGivenLabel.setText("Medicine Given");
        patientPaymentMedicineMedicineGivenLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        patientPaymentMedicineMedicineGivenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Unit Price (RM)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        patientPaymentMedicineMedicineGivenTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientPaymentMedicineMedicineGivenTableMouseClicked(evt);
            }
        });
        patientPaymentMedicineMedicineGivenScrollPane.setViewportView(patientPaymentMedicineMedicineGivenTable);
        if (patientPaymentMedicineMedicineGivenTable.getColumnModel().getColumnCount() > 0) {
            patientPaymentMedicineMedicineGivenTable.getColumnModel().getColumn(0).setResizable(false);
            patientPaymentMedicineMedicineGivenTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            patientPaymentMedicineMedicineGivenTable.getColumnModel().getColumn(1).setResizable(false);
            patientPaymentMedicineMedicineGivenTable.getColumnModel().getColumn(2).setResizable(false);
        }

        patientPaymentMedicinePayButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientPaymentMedicinePayButton.setText("Next");
        patientPaymentMedicinePayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientPaymentMedicinePayButtonActionPerformed(evt);
            }
        });

        patientPaymentMedicineCancelButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientPaymentMedicineCancelButton.setText("Cancel");
        patientPaymentMedicineCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientPaymentMedicineCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(patientPaymentMedicineTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(patientPaymentMedicinePayButton)
                        .addGap(18, 18, 18)
                        .addComponent(patientPaymentMedicineCancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientPaymentMedicineMedicineAvailableLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(patientPaymentMedicineMedicineAvailableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patientPaymentMedicineRemoveMedicineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(patientPaymentMedicineAddMedicineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientPaymentMedicineMedicineGivenScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(patientPaymentMedicineMedicineGivenLabel))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(patientPaymentMedicineTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patientPaymentMedicineMedicineAvailableLabel)
                            .addComponent(patientPaymentMedicineMedicineGivenLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(patientPaymentMedicineMedicineAvailableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(patientPaymentMedicineMedicineGivenScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(patientPaymentMedicineAddMedicineButton)
                                .addGap(47, 47, 47)
                                .addComponent(patientPaymentMedicineRemoveMedicineButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patientPaymentMedicinePayButton)
                            .addComponent(patientPaymentMedicineCancelButton))
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void patientPaymentMedicineAddMedicineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientPaymentMedicineAddMedicineButtonActionPerformed
        // TODO add your handling code here:
        if(medicine != null && !medicineGivenList.contains(medicine)){
            
            medicineGivenList.add(medicine);
            setMedicineGivenModelRow(medicineGivenList);
            
        }else if(medicineGivenList.contains(medicine)){
            
            JOptionPane.showMessageDialog(null, "Medicine Already Selected!!!", "Record Not Found", JOptionPane.ERROR_MESSAGE);
            
        }
        else{
            
            JOptionPane.showMessageDialog(null, "No Medicine Selected In Medicine Available!!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_patientPaymentMedicineAddMedicineButtonActionPerformed

    private void patientPaymentMedicineRemoveMedicineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientPaymentMedicineRemoveMedicineButtonActionPerformed
        // TODO add your handling code here:
        if(medicineRemove != null){
            
            medicineGivenList.remove(medicineRemove);
            setMedicineGivenModelRow(medicineGivenList);
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Medicine Selected In Medicine Given!!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_patientPaymentMedicineRemoveMedicineButtonActionPerformed

    private void patientPaymentMedicineMedicineAvailableTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPaymentMedicineMedicineAvailableTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) patientPaymentMedicineMedicineAvailableTable.getModel();
        int index = patientPaymentMedicineMedicineAvailableTable.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(index, 0).toString());
        String name = model.getValueAt(index, 1).toString();
        double unitPrice = Double.parseDouble(model.getValueAt(index, 2).toString());
        
        //Assign the medicine from medicineAvailableList
        for(int i = 0; i < medicineAvailableList.size(); i++){
            if(Integer.parseInt(medicineAvailableList.get(i).getId()) == id && medicineAvailableList.get(i).getMedicineName().equals(name) && medicineAvailableList.get(i).getUnitPrice() == unitPrice){
                medicine = medicineAvailableList.get(i);
            }
        }
    }//GEN-LAST:event_patientPaymentMedicineMedicineAvailableTableMouseClicked

    private void patientPaymentMedicineMedicineGivenTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPaymentMedicineMedicineGivenTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) patientPaymentMedicineMedicineGivenTable.getModel();
        int index = patientPaymentMedicineMedicineGivenTable.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(index, 0).toString());
        String name = model.getValueAt(index, 1).toString();
        double unitPrice = Double.parseDouble(model.getValueAt(index, 2).toString());
        
        //Assign the medicine from medicineAvailableList
        for(int i = 0; i < medicineGivenList.size(); i++){
            if(Integer.parseInt(medicineGivenList.get(i).getId()) == id && medicineGivenList.get(i).getMedicineName().equals(name) && medicineGivenList.get(i).getUnitPrice() == unitPrice){
                medicineRemove = medicineGivenList.get(i);
            }
        }
    }//GEN-LAST:event_patientPaymentMedicineMedicineGivenTableMouseClicked

    private void patientPaymentMedicinePayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientPaymentMedicinePayButtonActionPerformed
        // TODO add your handling code here:
        PatientPayment patientPayment = new PatientPayment(this.staffName, patient, this, this.patientOnHoldList, this.medicineGivenList);
        patientPayment.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_patientPaymentMedicinePayButtonActionPerformed

    private void patientPaymentMedicineCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientPaymentMedicineCancelButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Cancel Payment !!!", "Payment", JOptionPane.INFORMATION_MESSAGE);
        
        Home home = new Home(staffName, patientOnHoldList);
        home.setVisible(true);
        frame.dispose();
        this.dispose();
    }//GEN-LAST:event_patientPaymentMedicineCancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PatientPaymentMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientPaymentMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientPaymentMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientPaymentMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientPaymentMedicine().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton patientPaymentMedicineAddMedicineButton;
    private javax.swing.JButton patientPaymentMedicineCancelButton;
    private javax.swing.JLabel patientPaymentMedicineMedicineAvailableLabel;
    private javax.swing.JScrollPane patientPaymentMedicineMedicineAvailableScrollPane;
    private javax.swing.JTable patientPaymentMedicineMedicineAvailableTable;
    private javax.swing.JLabel patientPaymentMedicineMedicineGivenLabel;
    private javax.swing.JScrollPane patientPaymentMedicineMedicineGivenScrollPane;
    private javax.swing.JTable patientPaymentMedicineMedicineGivenTable;
    private javax.swing.JButton patientPaymentMedicinePayButton;
    private javax.swing.JButton patientPaymentMedicineRemoveMedicineButton;
    private javax.swing.JLabel patientPaymentMedicineTitleLabel;
    // End of variables declaration//GEN-END:variables
}

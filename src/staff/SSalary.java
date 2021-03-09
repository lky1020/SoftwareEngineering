/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;
import Class.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.Home;

/**
 *
 * @author user
 */
public class SSalary extends javax.swing.JFrame {

    private DefaultTableModel salaryModel;
    private String staffName;//to recrod the staff name
    private List<Salary> salaryList = new ArrayList<>();
    private Salary salary;
    private SalaryValidation validate;
     SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
   
    

    public SSalary(String staffName, javax.swing.JFrame frame) {
        initComponents();
        
       // salary = new Salary(salary.getId(), salary.getName(),salary.getDesignation(), salary.getMobileNo(), salary.getSalary());
      //  this.salary = salary;
        
        int id = 0;
        String myString= Integer.toString(id);
        double sly=0;
        String myString1=Double.toString(sly);
        // idTextField.setText(myString);
       // nameTextField.setText(salary.getName());
        //moTextField.setText(salary.getMobileNo());
        //salaryTextField.setText(myString1);
    }

    private SSalary() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        deTextField = new javax.swing.JTextField();
        moTextField = new javax.swing.JTextField();
        salaryTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Salary");

        jLabel2.setText("Id:");

        jLabel3.setText("Name:");

        jLabel4.setText("Designation");

        jLabel5.setText("MobileNo:");

        jLabel6.setText("jLabel6");

        jButton1.setText("Pay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Display");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(279, 279, 279))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(nameTextField)
                                    .addComponent(deTextField)
                                    .addComponent(salaryTextField)
                                    .addComponent(moTextField)
                                    .addComponent(idTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(deTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(moTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(salaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(11, 11, 11)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          if (idTextField.getText().equals("") && nameTextField.getText().equals("") && deTextField.getText().equals("") && moTextField.getText().equals("")&&salaryTextField.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Please Enter ID, Name and Mobile No of Staff !!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);

        } 
         else if (idTextField.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Please Enter Id of staff !!!", "Invalid ID", JOptionPane.ERROR_MESSAGE);

        } 
         else if (nameTextField.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Please Enter Name of staff !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);

        } 
         else if (deTextField.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Please Enter designation of staff !!!", "Designation", JOptionPane.ERROR_MESSAGE);
        } 
         else if (moTextField.getText().equals("")) 
         {

            JOptionPane.showMessageDialog(null, "Please Enter Mobile No of staff !!!", "Invalid Mobile No", JOptionPane.ERROR_MESSAGE);
        } 
          else if(salaryTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Enter salary of staff !!!", "Invalid Mobile No", JOptionPane.ERROR_MESSAGE);
                    }
           else {

            //validate input
            if (validate.validateID(idTextField.getText()) != true) {

                JOptionPane.showMessageDialog(null, "Invalid Id Format, Please Enter Again !!! \n Format : xxxxx", "Invalid Id Format", JOptionPane.ERROR_MESSAGE);

            } else if (validate.validateName(nameTextField.getText()) != true) {

                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);

            } else if (validate.validateDesignation(deTextField.getText()) != true) {

                JOptionPane.showMessageDialog(null, "Invalid Designation Format, Please Enter Again!!! \n Format :xxxxx", "Invalid Designation Format", JOptionPane.ERROR_MESSAGE);

            } else if (validate.validateMobileNo(moTextField.getText()) != true) {

                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);

            } 
         else {

           
                int id1 = Integer.parseInt(idTextField.getText());
                Date dateJoined = new Date();
                double Salary=Double.parseDouble(salaryTextField.getText());

                salary = new Salary(id1, nameTextField.getText(), deTextField.getText(),moTextField.getText(),Salary);

                int addRecords = JOptionPane.showConfirmDialog(null, "<html> <b>Sure to Add This Record ?</b> </html>\n" + salary, "Add Staff's Record", JOptionPane.YES_NO_OPTION);

                if (addRecords == 0) {

                    salaryList.add(salary);
                    saveStaffDataToFile();
                    JOptionPane.showMessageDialog(null, "Staff Records Updated !!!", "Records Updated", JOptionPane.INFORMATION_MESSAGE);
                    setsalaryModel(salaryList);

                } else {

                    JOptionPane.showMessageDialog(null, "Cancel Add Staff's Record", "Cancel Adding", JOptionPane.INFORMATION_MESSAGE);

                }

                clearSalaryModuleInputField();
          }
    }//GEN-LAST:event_jButton1ActionPerformed
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         if (salary != null) {

            for (int i = 0; i < salaryList.size(); i++) {

                if (salary.getId() == salaryList.get(i).getId()) {

                    int deleteRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Record Found. Sure to delete ?</b> </html>\n" + salaryList.get(i), "Staff's Record Found", JOptionPane.YES_NO_OPTION);

                    if (deleteRecord == 0) {

                        salaryList.remove(salaryList.get(i));
                        saveStaffDataToFile();
                        JOptionPane.showMessageDialog(null, "Staff Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                        setsalaryModel(salaryList);

                    } else {

                        JOptionPane.showMessageDialog(null, "Cancel Delete Staff's Record", "Cancel Delete", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }

        } else {

            JOptionPane.showMessageDialog(null, "No Record Selected !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton3ActionPerformed
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String filePath= "This PC:\\Document\\GitHub\\SoftwareEngineering\\salary.dat";
        File file = new File(filePath);
        
         try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            // get the first line
            // get the columns name from the first line
            // set columns name to the jtable model
            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setColumnIdentifiers(columnsName);
            
            // get lines from txt file
            Object[] tableLines = br.lines().toArray();
            
            // extratct data from lines
            // set data to jtable model
            for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
            
            
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void setsalaryModel(List<Salary> arrayList) {

        salaryModel.setRowCount(0);

        for (int i = 0; i < arrayList.size(); i++) {

            salaryModel.addRow(new Object[]{i + 1, arrayList.get(i).getId(), arrayList.get(i).getName(), arrayList.get(i).getDesignation(), arrayList.get(i).getMobileNo(),arrayList.get(i).getSalary()});

        }
    }
    
    private void saveStaffDataToFile() {
        try {

            File file = new File("salary.dat");
            System.out.println("***TRACE SAVE: " + file.getAbsolutePath());
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(salaryList);
            ooStream.close();

        } catch (FileNotFoundException ex) {

            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Cannot save to file", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void readstaffDataFromFile() {
        try {
            File file = new File("salary.dat");
            System.out.println("***TRACE READ: " + file.getAbsolutePath());
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
           salaryList = (ArrayList) (oiStream.readObject());
            oiStream.close();

        } catch (FileNotFoundException ex) {

            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Cannot read from file", "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(null, "Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }  
    
     public void clearSalaryModuleInputField() {
        //Clear Input Field
        idTextField.setText("");
       nameTextField.setText("");
        deTextField.setText("");
        moTextField.setText("");
        salaryTextField.getText().equals("");
    }
     
     static int xx, yy;
    private void frameTitleMousePressed(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        xx = evt.getX();
        yy = evt.getY();
    }                                       

    private void frameTitleMouseDragged(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x-xx, y-yy);
    } 
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
            java.util.logging.Logger.getLogger(SSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SSalary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField deTextField;
    private javax.swing.JTextField idTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField moTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField salaryTextField;
    // End of variables declaration//GEN-END:variables
}

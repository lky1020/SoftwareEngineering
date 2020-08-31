package main;


import Class.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.util.regex.*;
import patient.*;
import medicine.*;
import staff.staffModify;

public class Home extends javax.swing.JFrame {

    private SideBarListener sideBarListener = new SideBarListener();
    private DefaultTableModel patientModel;
    private DefaultTableModel staffModel;
    private DefaultTableModel medicineModel;
    private List<Patient> patientList = new ArrayList<>();
    private List<Patient> patientSearchList = new ArrayList<>();
    private List<Patient> patientOnHoldList = new ArrayList<>();
    private PatientValidation validate = new PatientValidation();
    private Patient patient;
    private Patient onHoldPatient;
    private Patient modifyPatient;
    private String patientName; //for medical description label
    private String staffName;//to recrod the staff name
    private List<Staff> staffList = new ArrayList<>();
    private List<Staff> staffSearchList = new ArrayList<>();
    private StaffValidation validate1 = new StaffValidation();
    private Staff modifyStaff;
    private Staff staff;
    private Medicine medicine;
    private Medicine modifyMedicine;
    private MedicineValidation mValidate = new MedicineValidation();
    private List<Medicine> medicineList = new ArrayList<>();
    private List<Medicine> medicineSearchList = new ArrayList<>();
    
    SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
    
    /**
     * Creates new form Home
     */
    public Home (){
        initialize();
    }
    
    public Home(String staffName) {
        initialize();
        this.staffName = staffName;
        greetLabel.setText(greetLabel.getText() + this.staffName);
    }
    
    public Home(String staffName, Patient patient, Patient modifyPatient, List<Patient> patientOnHoldList){
        initialize();
        
        this.staffName = staffName;
        greetLabel.setText(greetLabel.getText() + this.staffName);
        
        this.patient = patient;
        this.modifyPatient = modifyPatient;
        this.patientOnHoldList = patientOnHoldList;
        
        for(int i = 0; i < patientList.size(); i++){
            
            if(patient.getIc().equals(patientList.get(i).getIc()) && patient.getName().equals(patientList.get(i).getName()) && patient.getMobileNo().equals(patientList.get(i).getMobileNo())){
                
                patientList.set(i, this.modifyPatient);
                
            }
        }
        
        savePatientsDataToFile();
        JOptionPane.showMessageDialog(null, "Patient Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
        setPatientModelRow(patientList);
        
    }
    
    public Home(String staffName, List<Patient> patientOnHoldList) {
        initialize();
        this.staffName = staffName;
        greetLabel.setText(greetLabel.getText() + this.staffName);
        this.patientOnHoldList = patientOnHoldList;
    }
    
    public Home(String staffName, Medicine medicine, Medicine modifyMedicine){
        initialize();
        
        this.staffName = staffName;
        greetLabel.setText(greetLabel.getText() + this.staffName);
        
        this.medicine = medicine;
        this.modifyMedicine = modifyMedicine;
        
        for(int i = 0; i < medicineList.size(); i++){
            
            if(medicine.getId().equals(medicineList.get(i).getId())){
                
                medicineList.set(i, this.modifyMedicine);
                
            }
        }
        
        saveMedicineDataToFile();
        JOptionPane.showMessageDialog(null, "Medicine Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
        setMedicineModelRow(medicineList);
    }

    public Home(String staffName, Staff modifyStaff, Staff staff) {
     initialize();
        
        this.staffName = staffName;
        greetLabel.setText(greetLabel.getText() + this.staffName);
        
        this.staff = staff;
        this.modifyStaff = modifyStaff;

 
        for(int i = 0; i < staffList.size(); i++){

                  if(staff.getId()==(staffList.get(i).getId()) && staff.getName().equals(staffList.get(i).getName()) && staff.getDesignation().equals(staffList.get(i).getDesignation())&& staff.getMobileNo().equals(staffList.get(i).getMobileNo()))
                {
                staffList.set(i, this.modifyStaff);
                
        }
        
        saveStaffDataToFile();
        JOptionPane.showMessageDialog(null, "Patient Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
        setStaffModel(staffList);
    }
    }
    
    public void initialize(){
        initComponents();
        setLocationRelativeTo(null);
        patientHoverPanel.setBackground( new Color(2, 165, 249));
        patientBar.setBackground( new Color(63, 218, 234));
        
        Date d = new Date();
        dateLabel.setText(dateLabel.getText() + s.format(d));

        //Patient Module
        //set the background to transparent
        patientsModuleICTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        patientsModuleNameTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        patientsModuleMobileNoTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        showTime();
        
        patientsModuleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientName = patientsModuleMedicalDescriptionLabel.getText();

        //read and show Patient Records
        readPatientsDataFromFile();
        patientModel = (DefaultTableModel)patientsModuleTable.getModel();
        setPatientModelRow(patientList);
        
        //Appointment Module
        //set the background to transparent
        appointmentsModulePatientNameTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        appointmentsModulePatientMobileNoTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        appointmentsModuleStaffInchargeTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        //Medicine Module
        //set the background to transparent
        medicineModuleMedicineIDTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        medicineModuleMedicineNameTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        medicineModuleMedicineQuantityTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        medicineModuleMedicineUnitPriceTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        medicineModuleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //read and show Medicine Records
        readMedicineDataFromFile();
        medicineModel = (DefaultTableModel)medicineModuleTable.getModel();
        setMedicineModelRow(medicineList);
        
        //Staff Module
        //set the background to transparent
        staffModuleStaffIDTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        staffModuleStaffNameTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        staffModuleStaffDesignationTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        staffModuleStaffMobileNoTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        
    }
    
    public void showTime(){
        new Timer(0, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
                timeLabel.setText(time.format(d));
            }
            
        }).start();
    }
    
    private void setPatientModelRow(List<Patient> arrayList){

        patientModel.setRowCount(0);
        
        for(int i = 0; i < arrayList.size(); i++){
            
             patientModel.addRow(new Object[]{i + 1, arrayList.get(i).getIcNo(), arrayList.get(i).getIc(), arrayList.get(i).getName(), arrayList.get(i).getMobileNo(), arrayList.get(i).getDateCreated()});
        
        }   
    }
    
    private void savePatientsDataToFile(){
        try {
            
            File file = new File("patients.dat");
            System.out.println("***TRACE SAVE: " + file.getAbsolutePath());
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(patientList);
            ooStream.close();
            
          } catch (FileNotFoundException ex) {
              
            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
          } catch (IOException ex) {
              
            JOptionPane.showMessageDialog(null, "Cannot save to file", "ERROR", JOptionPane.ERROR_MESSAGE);
            
          }
    }
    
    private void readPatientsDataFromFile(){
        try {
            File file = new File("patients.dat");
            System.out.println("***TRACE READ: " + file.getAbsolutePath());
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            patientList = (ArrayList) (oiStream.readObject());
            oiStream.close();

        } catch (FileNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(null, "Cannot read from file", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } catch (ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public void clearPatientsModuleInputField(){
        //Clear Input Field
        patientsModuleICTextField.setText("");
        patientsModuleNameTextField.setText("");
        patientsModuleMobileNoTextField.setText("");
        patientsModuleDateCreatedDateChooser.setCalendar(null);
    }
    
    private void setMedicineModelRow(List<Medicine> arrayList){

        medicineModel.setRowCount(0);
        
        for(int i = 0; i < arrayList.size(); i++){
            medicineModel.addRow(new Object[]{i + 1, arrayList.get(i).getId(), arrayList.get(i).getMedicineName(), arrayList.get(i).getQuantity(), arrayList.get(i).getUnitPrice(), arrayList.get(i).getExpiredDate()});
        
        }   
    }
    
    private void saveMedicineDataToFile(){
        try {
            
            File file = new File("medicine.dat");
            System.out.println("***TRACE SAVE: " + file.getAbsolutePath());
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(medicineList);
            ooStream.close();
            
          } catch (FileNotFoundException ex) {
              
            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
          } catch (IOException ex) {
              
            JOptionPane.showMessageDialog(null, "Cannot save to file", "ERROR", JOptionPane.ERROR_MESSAGE);
            
          }
    }
    
    private void readMedicineDataFromFile(){
        try {
            File file = new File("medicine.dat");
            System.out.println("***TRACE READ: " + file.getAbsolutePath());
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            medicineList = (ArrayList) (oiStream.readObject());
            oiStream.close();

        } catch (FileNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(null, "Cannot read from file", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } catch (ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public void clearMedicineModuleInputField(){
        //Clear Input Field
        medicineModuleMedicineIDTextField.setText("");
        medicineModuleMedicineNameTextField.setText("");
        medicineModuleMedicineQuantityTextField.setText("");
        medicineModuleMedicineUnitPriceTextField.setText("");
        medicineModuleMedicineExpiredDateDateChooser.setCalendar(null);
    }
    
    public boolean validateIcNo(String icNo, String action){
        
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(icNo);
        
        if(action.equals("Delete")){
            
            if(matcher.matches()){

                return true;

            }else{
                JOptionPane.showMessageDialog(null, "Invalid IC No, Please Enter Again!!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return false;

            }
            
        }
        
        //Search
        if(matcher.matches() || icNo.equals("")){

             return true;

        }else{
            JOptionPane.showMessageDialog(null, "Invalid IC No, Please Enter Again!!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;

        }

    }

    public boolean validateDuplicatePatientList(String ic, String mobileNo){

        boolean noDuplicate = true;
        
        for(int i = 0; i < patientList.size(); i++){

            if(patientList.get(i).getIc().equals(ic) || patientList.get(i).getMobileNo().equals(mobileNo)){
                noDuplicate = false;
            }
        }
        
        return noDuplicate;
    }
    
    public boolean validateDuplicateMedicineList(String id){

        boolean noDuplicate = true;
        
        for(int i = 0; i < medicineList.size(); i++){

            if(medicineList.get(i).getId().equals(id)){
                noDuplicate = false;
            }
        }
        
        return noDuplicate;
    }
    
    private void setStaffModel(List<Staff> arrayList){

        staffModel.setRowCount(0);
        
        for(int i = 0; i < arrayList.size(); i++){
            
             staffModel.addRow(new Object[]{i + 1, arrayList.get(i).getId(),  arrayList.get(i).getName(),arrayList.get(i).getDesignation(),arrayList.get(i).getMobileNo(), arrayList.get(i).getDateJoined()});
        
        }   
    }
    
    private void saveStaffDataToFile(){
        try {
            
            File file = new File("staff.dat");
            System.out.println("***TRACE SAVE: " + file.getAbsolutePath());
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(staffList);
            ooStream.close();
            
          } catch (FileNotFoundException ex) {
              
            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
          } catch (IOException ex) {
              
            JOptionPane.showMessageDialog(null, "Cannot save to file", "ERROR", JOptionPane.ERROR_MESSAGE);
            
          }
    }
    
    private void readstaffDataFromFile(){
        try {
            File file = new File("staff.dat");
            System.out.println("***TRACE READ: " + file.getAbsolutePath());
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            staffList = (ArrayList) (oiStream.readObject());
            oiStream.close();

        } catch (FileNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(null, "Cannot read from file", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        } catch (ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
     public void clearStaffModuleInputField(){
        //Clear Input Field
        staffModuleStaffIDTextField.setText("");
        staffModuleStaffNameTextField.setText("");
        staffModuleStaffDesignationTextField.setText("");
        staffModuleStaffMobileNoTextField.setText("");
    }
     
     public boolean validateID(String id, String action){
        
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(id);
        
        if(action.equals("Delete")){
            
            if(matcher.matches()){

                return true;

            }else{
                JOptionPane.showMessageDialog(null, "Invalid ID No, Please Enter Again!!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return false;

            }
            
        }
        
        //Search
        if(matcher.matches() || id.equals("")){

             return true;

        }else{
            JOptionPane.showMessageDialog(null, "Invalid ID No, Please Enter Again!!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;

        }

    }
     
     public boolean validateDuplicateStaffList(String id, String mobileNo){

        boolean noDuplicate = true;
        
        for(int i = 0; i < staffList.size(); i++){

            if(id.equals(staffList.get(i).getId()) ){
                noDuplicate = false;
            }
        }
        
        return noDuplicate;
    }
    
    public void setBackground(boolean patient, boolean appointments, boolean medicine, boolean staff, boolean comingSoon){
        if(patient){
            
            patientHoverPanel.setBackground( new Color(2, 165, 249));
            patientBar.setBackground( new Color(63, 218, 234));
            
        }else{
            
            patientHoverPanel.setBackground( new Color(255, 255, 255));
            patientBar.setBackground( new Color(255, 255, 255));
            
        }
        
        if(appointments){
            
            appointmentsHoverPanel.setBackground( new Color(2, 165, 249));
            appointmentsBar.setBackground( new Color(63, 218, 234));
            
        }else{
            
            appointmentsHoverPanel.setBackground( new Color(255, 255, 255));
            appointmentsBar.setBackground( new Color(255, 255, 255));
            
        }
        
        if(medicine){
            
            medicineHoverPanel.setBackground( new Color(2, 165, 249));
            medicineBar.setBackground( new Color(63, 218, 234));
            
        }else{
            
            medicineHoverPanel.setBackground( new Color(255, 255, 255));
            medicineBar.setBackground( new Color(255, 255, 255));
            
        }
        
        if(staff){
            
            staffHoverPanel.setBackground( new Color(2, 165, 249));
            staffBar.setBackground( new Color(63, 218, 234));
            
        }else{
            
            staffHoverPanel.setBackground( new Color(255, 255, 255));
            staffBar.setBackground( new Color(255, 255, 255));
            
        }
        
        if(comingSoon){
            
            comingSoonHoverPanel.setBackground( new Color(2, 165, 249));
            comingSoonBar.setBackground( new Color(63, 218, 234));
            
        }else{
            
            comingSoonHoverPanel.setBackground( new Color(255, 255, 255));
            comingSoonBar.setBackground( new Color(255, 255, 255));
            
        }
    }
    
    static int xx, yy; 
    private class SideBarListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == patientBar){
                setBackground(true, false, false, false, false);
                
                patientsModule.setVisible(true);
                appointmentsModule.setVisible(false);
                medicineModule.setVisible(false);
                staffModule.setVisible(false);
            }
            
            if(e.getSource() == appointmentsBar){
                setBackground(false, true, false, false, false);
                
                patientsModule.setVisible(false);
                appointmentsModule.setVisible(true);
                medicineModule.setVisible(false);
                staffModule.setVisible(false);
            }
            
            if(e.getSource() == medicineBar){
                setBackground(false, false, true, false, false);
                
                patientsModule.setVisible(false);
                appointmentsModule.setVisible(false);
                medicineModule.setVisible(true);
                staffModule.setVisible(false);
            }
            
            if(e.getSource() == staffBar){
                setBackground(false, false, false, true, false);
                
                patientsModule.setVisible(false);
                appointmentsModule.setVisible(false);
                medicineModule.setVisible(false);
                staffModule.setVisible(true);
            }
            
            if(e.getSource() == comingSoonBar){
                setBackground(false, false, false, false, true);
                
                patientsModule.setVisible(false);
                appointmentsModule.setVisible(false);
                medicineModule.setVisible(false);
                staffModule.setVisible(false);
                comingSoonModule.setVisible(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JPanel panel = (JPanel)e.getSource(); 
            Component[] label = panel.getComponents();
            
            if(!label[0].getBackground().equals(new Color(2, 165, 249))){
                label[0].setBackground( new Color(2, 165, 248));
                panel.setBackground( new Color(63, 218, 234));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel panel = (JPanel)e.getSource();
            Component[] label = panel.getComponents();

            if(label[0].getBackground().equals(new Color(2, 165, 248))){
                label[0].setBackground( new Color(255, 255, 255));
                panel.setBackground( new Color(255, 255, 255));
            }
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

        topPanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        clinicLogo = new javax.swing.JLabel();
        closeLabel = new javax.swing.JLabel();
        greetLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        timeTitle = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        sidePanel = new javax.swing.JPanel();
        patientBar = new javax.swing.JPanel();
        patientLogo = new javax.swing.JLabel();
        patientLabel = new javax.swing.JLabel();
        patientHoverPanel = new javax.swing.JPanel();
        appointmentsBar = new javax.swing.JPanel();
        appointmentsLogo = new javax.swing.JLabel();
        appointmentsLabel = new javax.swing.JLabel();
        appointmentsHoverPanel = new javax.swing.JPanel();
        medicineBar = new javax.swing.JPanel();
        medicineLogo = new javax.swing.JLabel();
        medicineLabel = new javax.swing.JLabel();
        medicineHoverPanel = new javax.swing.JPanel();
        staffBar = new javax.swing.JPanel();
        staffLogo = new javax.swing.JLabel();
        staffLabel = new javax.swing.JLabel();
        staffHoverPanel = new javax.swing.JPanel();
        comingSoonBar = new javax.swing.JPanel();
        comingSoonLogo = new javax.swing.JLabel();
        comingSoonLabel = new javax.swing.JLabel();
        comingSoonHoverPanel = new javax.swing.JPanel();
        methodPanel = new javax.swing.JPanel();
        patientsModule = new javax.swing.JPanel();
        patientsModuleScrollPane = new javax.swing.JScrollPane();
        patientsModuleTable = new javax.swing.JTable();
        patientsModuleNameLabel = new javax.swing.JLabel();
        patientsModuleNameTextField = new javax.swing.JTextField();
        patientsModuleICLabel = new javax.swing.JLabel();
        patientsModuleICTextField = new javax.swing.JTextField();
        patientsModuleMobileNoLabel = new javax.swing.JLabel();
        patientsModuleMobileNoTextField = new javax.swing.JTextField();
        patientsModuleDateCreatedLabel = new javax.swing.JLabel();
        patientsModuleDateCreatedDateChooser = new com.toedter.calendar.JDateChooser();
        patientsModuleAddButton = new javax.swing.JButton();
        patientsModuleModifyButton = new javax.swing.JButton();
        patientsModuleSearchButton = new javax.swing.JButton();
        patientsModuleDeleteButton = new javax.swing.JButton();
        patientsModuleMedicalDescriptionLabel = new javax.swing.JLabel();
        patientsModuleMedicalDescriptionScrollPane = new javax.swing.JScrollPane();
        patientsModuleMedicalDescriptionTextArea = new javax.swing.JTextArea();
        patientsModuleDiagnoseButton = new javax.swing.JButton();
        patientsModuleOnHoldButton = new javax.swing.JButton();
        patientsModulePaymentButton = new javax.swing.JButton();
        appointmentsModule = new javax.swing.JPanel();
        appointmentsModuleScrollPane = new javax.swing.JScrollPane();
        appointmentsModuleTable = new javax.swing.JTable();
        appointmentsModulePatientMobileNoLabel = new javax.swing.JLabel();
        appointmentsModulePatientMobileNoTextField = new javax.swing.JTextField();
        appointmentsModulePatientNameLabel = new javax.swing.JLabel();
        appointmentsModulePatientNameTextField = new javax.swing.JTextField();
        appointmentsModuleStaffInchargeLabel = new javax.swing.JLabel();
        appointmentsModuleStaffInchargeTextField = new javax.swing.JTextField();
        appointmentsModuleAppointmentDateLabel = new javax.swing.JLabel();
        appointmentsModuleAppointmentDateDateChooser = new com.toedter.calendar.JDateChooser();
        appointmentsModuleAddButton = new javax.swing.JButton();
        appointmentsModuleModifyButton = new javax.swing.JButton();
        appointmentsModuleSearchButton = new javax.swing.JButton();
        appointmentsModuleDeleteButton = new javax.swing.JButton();
        medicineModule = new javax.swing.JPanel();
        medicineModuleScrollPane = new javax.swing.JScrollPane();
        medicineModuleTable = new javax.swing.JTable();
        medicineModuleMedicineNameLabel = new javax.swing.JLabel();
        medicineModuleMedicineNameTextField = new javax.swing.JTextField();
        medicineModuleMedicineIDLabel = new javax.swing.JLabel();
        medicineModuleMedicineIDTextField = new javax.swing.JTextField();
        medicineModuleMedicineQuantityLabel = new javax.swing.JLabel();
        medicineModuleMedicineQuantityTextField = new javax.swing.JTextField();
        medicineModuleMedicineExpiredDateLabel = new javax.swing.JLabel();
        medicineModuleMedicineExpiredDateDateChooser = new com.toedter.calendar.JDateChooser();
        medicineModuleAddButton = new javax.swing.JButton();
        medicineModuleModifyButton = new javax.swing.JButton();
        medicineModuleSearchButton = new javax.swing.JButton();
        medicineModuleDeleteButton = new javax.swing.JButton();
        medicineModuleMedicineUnitPriceLabel = new javax.swing.JLabel();
        medicineModuleMedicineUnitPriceTextField = new javax.swing.JTextField();
        staffModule = new javax.swing.JPanel();
        staffModuleScrollPane = new javax.swing.JScrollPane();
        staffModuleTable = new javax.swing.JTable();
        staffModuleStaffNameLabel = new javax.swing.JLabel();
        staffModuleStaffNameTextField = new javax.swing.JTextField();
        staffModuleStaffIDLabel = new javax.swing.JLabel();
        staffModuleStaffIDTextField = new javax.swing.JTextField();
        staffModuleStaffMobileNoLabel = new javax.swing.JLabel();
        staffModuleStaffMobileNoTextField = new javax.swing.JTextField();
        staffModuleStaffDateJoinedLabel = new javax.swing.JLabel();
        staffModuleStaffDateJoinedDateChooser = new com.toedter.calendar.JDateChooser();
        staffModuleAddButton = new javax.swing.JButton();
        staffModuleModifyButton = new javax.swing.JButton();
        staffModuleSearchButton = new javax.swing.JButton();
        staffModuleDeleteButton = new javax.swing.JButton();
        staffModuleStaffDesignationLabel = new javax.swing.JLabel();
        staffModuleStaffDesignationTextField = new javax.swing.JTextField();
        comingSoonModule = new javax.swing.JPanel();
        comingSoonModuleLogo = new javax.swing.JLabel();
        comingSoonModuleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topPanelMouseDragged(evt);
            }
        });
        topPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topPanelMousePressed(evt);
            }
        });

        logoPanel.setBackground(new java.awt.Color(2, 188, 249));

        clinicLogo.setFont(new java.awt.Font(".Heiti J", 1, 20)); // NOI18N
        clinicLogo.setForeground(new java.awt.Color(255, 255, 255));
        clinicLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clinicLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hospital.png"))); // NOI18N
        clinicLogo.setText("Klinik K.J Lim");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clinicLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clinicLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        closeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        closeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });

        greetLabel.setFont(new java.awt.Font(".Heiti J", 1, 24)); // NOI18N
        greetLabel.setForeground(new java.awt.Color(51, 51, 51));
        greetLabel.setText("Welcome, ");

        dateLabel.setFont(new java.awt.Font(".Heiti J", 1, 14)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(51, 51, 51));
        dateLabel.setText("Date: ");

        timeTitle.setFont(new java.awt.Font(".Heiti J", 1, 14)); // NOI18N
        timeTitle.setForeground(new java.awt.Color(51, 51, 51));
        timeTitle.setText("Time: ");

        timeLabel.setFont(new java.awt.Font(".Heiti J", 1, 14)); // NOI18N

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(greetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
                        .addComponent(timeTitle)
                        .addGap(0, 0, 0)
                        .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addComponent(closeLabel)
                .addGap(0, 0, 0))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(greetLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(closeLabel))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timeTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        sidePanel.setBackground(new java.awt.Color(255, 255, 255));

        patientBar.setBackground(new java.awt.Color(255, 255, 255));
        patientBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patientBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                patientBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                patientBarMouseExited(evt);
            }
        });

        patientLogo.setForeground(new java.awt.Color(255, 255, 255));
        patientLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        patientLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pulse.png"))); // NOI18N

        patientLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        patientLabel.setText("Patients");

        patientHoverPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout patientHoverPanelLayout = new javax.swing.GroupLayout(patientHoverPanel);
        patientHoverPanel.setLayout(patientHoverPanelLayout);
        patientHoverPanelLayout.setHorizontalGroup(
            patientHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        patientHoverPanelLayout.setVerticalGroup(
            patientHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout patientBarLayout = new javax.swing.GroupLayout(patientBar);
        patientBar.setLayout(patientBarLayout);
        patientBarLayout.setHorizontalGroup(
            patientBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientBarLayout.createSequentialGroup()
                .addComponent(patientHoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(patientLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(patientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        patientBarLayout.setVerticalGroup(
            patientBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(patientLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(patientLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(patientHoverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        appointmentsBar.setBackground(new java.awt.Color(255, 255, 255));
        appointmentsBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        appointmentsBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appointmentsBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appointmentsBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                appointmentsBarMouseExited(evt);
            }
        });

        appointmentsLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appointmentsLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/appointment.png"))); // NOI18N

        appointmentsLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        appointmentsLabel.setText("Appointments");

        appointmentsHoverPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout appointmentsHoverPanelLayout = new javax.swing.GroupLayout(appointmentsHoverPanel);
        appointmentsHoverPanel.setLayout(appointmentsHoverPanelLayout);
        appointmentsHoverPanelLayout.setHorizontalGroup(
            appointmentsHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        appointmentsHoverPanelLayout.setVerticalGroup(
            appointmentsHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout appointmentsBarLayout = new javax.swing.GroupLayout(appointmentsBar);
        appointmentsBar.setLayout(appointmentsBarLayout);
        appointmentsBarLayout.setHorizontalGroup(
            appointmentsBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentsBarLayout.createSequentialGroup()
                .addComponent(appointmentsHoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(appointmentsLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(appointmentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        appointmentsBarLayout.setVerticalGroup(
            appointmentsBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appointmentsLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(appointmentsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(appointmentsHoverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        medicineBar.setBackground(new java.awt.Color(255, 255, 255));
        medicineBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        medicineBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicineBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                medicineBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                medicineBarMouseExited(evt);
            }
        });

        medicineLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        medicineLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/drugs.png"))); // NOI18N

        medicineLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        medicineLabel.setText("Medicine");

        medicineHoverPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout medicineHoverPanelLayout = new javax.swing.GroupLayout(medicineHoverPanel);
        medicineHoverPanel.setLayout(medicineHoverPanelLayout);
        medicineHoverPanelLayout.setHorizontalGroup(
            medicineHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        medicineHoverPanelLayout.setVerticalGroup(
            medicineHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout medicineBarLayout = new javax.swing.GroupLayout(medicineBar);
        medicineBar.setLayout(medicineBarLayout);
        medicineBarLayout.setHorizontalGroup(
            medicineBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicineBarLayout.createSequentialGroup()
                .addComponent(medicineHoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(medicineLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(medicineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        medicineBarLayout.setVerticalGroup(
            medicineBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(medicineLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(medicineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(medicineHoverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        staffBar.setBackground(new java.awt.Color(255, 255, 255));
        staffBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        staffBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                staffBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                staffBarMouseExited(evt);
            }
        });

        staffLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        staffLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nurse.png"))); // NOI18N

        staffLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        staffLabel.setText("Staff");

        staffHoverPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout staffHoverPanelLayout = new javax.swing.GroupLayout(staffHoverPanel);
        staffHoverPanel.setLayout(staffHoverPanelLayout);
        staffHoverPanelLayout.setHorizontalGroup(
            staffHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        staffHoverPanelLayout.setVerticalGroup(
            staffHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout staffBarLayout = new javax.swing.GroupLayout(staffBar);
        staffBar.setLayout(staffBarLayout);
        staffBarLayout.setHorizontalGroup(
            staffBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffBarLayout.createSequentialGroup()
                .addComponent(staffHoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(staffLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(staffLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        staffBarLayout.setVerticalGroup(
            staffBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(staffLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(staffLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staffHoverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        comingSoonBar.setBackground(new java.awt.Color(255, 255, 255));
        comingSoonBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comingSoonBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comingSoonBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comingSoonBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                comingSoonBarMouseExited(evt);
            }
        });

        comingSoonLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comingSoonLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comingSoon.png"))); // NOI18N

        comingSoonLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        comingSoonLabel.setText("Coming Soon");

        comingSoonHoverPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout comingSoonHoverPanelLayout = new javax.swing.GroupLayout(comingSoonHoverPanel);
        comingSoonHoverPanel.setLayout(comingSoonHoverPanelLayout);
        comingSoonHoverPanelLayout.setHorizontalGroup(
            comingSoonHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        comingSoonHoverPanelLayout.setVerticalGroup(
            comingSoonHoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout comingSoonBarLayout = new javax.swing.GroupLayout(comingSoonBar);
        comingSoonBar.setLayout(comingSoonBarLayout);
        comingSoonBarLayout.setHorizontalGroup(
            comingSoonBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comingSoonBarLayout.createSequentialGroup()
                .addComponent(comingSoonHoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(comingSoonLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(comingSoonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        comingSoonBarLayout.setVerticalGroup(
            comingSoonBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(comingSoonLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(comingSoonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(comingSoonBarLayout.createSequentialGroup()
                .addComponent(comingSoonHoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(patientBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(appointmentsBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(medicineBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staffBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(comingSoonBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(patientBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(appointmentsBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(medicineBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(staffBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(comingSoonBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        methodPanel.setLayout(new java.awt.CardLayout());

        patientsModule.setBackground(new java.awt.Color(204, 204, 204));

        patientsModuleScrollPane.setBackground(new java.awt.Color(255, 255, 51));

        patientsModuleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "IC No", "IC", "Name", "Mobile No", "Date Created"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        patientsModuleTable.setColumnSelectionAllowed(true);
        patientsModuleTable.getTableHeader().setReorderingAllowed(false);
        patientsModuleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientsModuleTableMouseClicked(evt);
            }
        });
        patientsModuleScrollPane.setViewportView(patientsModuleTable);
        patientsModuleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (patientsModuleTable.getColumnModel().getColumnCount() > 0) {
            patientsModuleTable.getColumnModel().getColumn(0).setMinWidth(25);
            patientsModuleTable.getColumnModel().getColumn(0).setMaxWidth(45);
            patientsModuleTable.getColumnModel().getColumn(1).setMinWidth(40);
            patientsModuleTable.getColumnModel().getColumn(1).setMaxWidth(60);
            patientsModuleTable.getColumnModel().getColumn(2).setResizable(false);
            patientsModuleTable.getColumnModel().getColumn(2).setHeaderValue("IC");
            patientsModuleTable.getColumnModel().getColumn(3).setResizable(false);
            patientsModuleTable.getColumnModel().getColumn(4).setResizable(false);
            patientsModuleTable.getColumnModel().getColumn(5).setResizable(false);
        }

        patientsModuleNameLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        patientsModuleNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        patientsModuleNameLabel.setText("Name :");

        patientsModuleNameTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        patientsModuleNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        patientsModuleICLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        patientsModuleICLabel.setForeground(new java.awt.Color(51, 51, 51));
        patientsModuleICLabel.setText("IC :");

        patientsModuleICTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        patientsModuleICTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        patientsModuleMobileNoLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        patientsModuleMobileNoLabel.setForeground(new java.awt.Color(51, 51, 51));
        patientsModuleMobileNoLabel.setText("Mobile No : ");

        patientsModuleMobileNoTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        patientsModuleMobileNoTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        patientsModuleDateCreatedLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        patientsModuleDateCreatedLabel.setForeground(new java.awt.Color(51, 51, 51));
        patientsModuleDateCreatedLabel.setText("Date Created ; ");

        patientsModuleDateCreatedDateChooser.setDateFormatString("dd-MM-yyyy");
        patientsModuleDateCreatedDateChooser.setDoubleBuffered(false);
        patientsModuleDateCreatedDateChooser.setMaxSelectableDate(new java.util.Date(253370739701000L));
        patientsModuleDateCreatedDateChooser.setMinSelectableDate(new java.util.Date(946659701000L));
        patientsModuleDateCreatedDateChooser.setOpaque(false);

        patientsModuleAddButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientsModuleAddButton.setText("Add");
        patientsModuleAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsModuleAddButtonActionPerformed(evt);
            }
        });

        patientsModuleModifyButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientsModuleModifyButton.setText("Modify");
        patientsModuleModifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsModuleModifyButtonActionPerformed(evt);
            }
        });

        patientsModuleSearchButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientsModuleSearchButton.setText("Search");
        patientsModuleSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsModuleSearchButtonActionPerformed(evt);
            }
        });

        patientsModuleDeleteButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientsModuleDeleteButton.setText("Delete");
        patientsModuleDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsModuleDeleteButtonActionPerformed(evt);
            }
        });

        patientsModuleMedicalDescriptionLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        patientsModuleMedicalDescriptionLabel.setForeground(new java.awt.Color(51, 51, 51));
        patientsModuleMedicalDescriptionLabel.setText("Medical Description of ");
        patientsModuleMedicalDescriptionLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        patientsModuleMedicalDescriptionTextArea.setEditable(false);
        patientsModuleMedicalDescriptionTextArea.setColumns(20);
        patientsModuleMedicalDescriptionTextArea.setRows(5);
        patientsModuleMedicalDescriptionScrollPane.setViewportView(patientsModuleMedicalDescriptionTextArea);

        patientsModuleDiagnoseButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientsModuleDiagnoseButton.setText("Diagnose");
        patientsModuleDiagnoseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsModuleDiagnoseButtonActionPerformed(evt);
            }
        });

        patientsModuleOnHoldButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientsModuleOnHoldButton.setText("On Hold");
        patientsModuleOnHoldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsModuleOnHoldButtonActionPerformed(evt);
            }
        });

        patientsModulePaymentButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        patientsModulePaymentButton.setText("Payment");
        patientsModulePaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsModulePaymentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout patientsModuleLayout = new javax.swing.GroupLayout(patientsModule);
        patientsModule.setLayout(patientsModuleLayout);
        patientsModuleLayout.setHorizontalGroup(
            patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientsModuleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientsModuleLayout.createSequentialGroup()
                        .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patientsModuleLayout.createSequentialGroup()
                                .addComponent(patientsModuleMobileNoLabel)
                                .addGap(0, 0, 0)
                                .addComponent(patientsModuleMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(patientsModuleLayout.createSequentialGroup()
                                .addComponent(patientsModuleICLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(patientsModuleICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patientsModuleLayout.createSequentialGroup()
                                .addComponent(patientsModuleDateCreatedLabel)
                                .addGap(0, 0, 0)
                                .addComponent(patientsModuleDateCreatedDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(patientsModuleLayout.createSequentialGroup()
                                .addComponent(patientsModuleNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(patientsModuleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(101, Short.MAX_VALUE))
                    .addGroup(patientsModuleLayout.createSequentialGroup()
                        .addComponent(patientsModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(patientsModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(patientsModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(patientsModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
            .addGroup(patientsModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(patientsModuleMedicalDescriptionLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(patientsModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientsModuleScrollPane)
                    .addComponent(patientsModuleMedicalDescriptionScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientsModuleLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(patientsModuleOnHoldButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(patientsModuleDiagnoseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(patientsModulePaymentButton)))
                .addContainerGap())
        );
        patientsModuleLayout.setVerticalGroup(
            patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientsModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientsModuleICLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(patientsModuleICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(patientsModuleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientsModuleMobileNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleDateCreatedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleDateCreatedDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientsModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientsModuleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientsModuleMedicalDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientsModuleMedicalDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientsModuleDiagnoseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModuleOnHoldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsModulePaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        methodPanel.add(patientsModule, "card2");

        appointmentsModule.setBackground(new java.awt.Color(204, 204, 204));

        appointmentsModuleScrollPane.setBackground(new java.awt.Color(255, 255, 51));

        appointmentsModuleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "IC No", "Patient Name", "Patient Mobile No", "Staff Incharge", "Appointment Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        appointmentsModuleTable.setColumnSelectionAllowed(true);
        appointmentsModuleTable.getTableHeader().setReorderingAllowed(false);
        appointmentsModuleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appointmentsModuleTableMouseClicked(evt);
            }
        });
        appointmentsModuleScrollPane.setViewportView(appointmentsModuleTable);
        appointmentsModuleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (appointmentsModuleTable.getColumnModel().getColumnCount() > 0) {
            appointmentsModuleTable.getColumnModel().getColumn(0).setMinWidth(25);
            appointmentsModuleTable.getColumnModel().getColumn(0).setMaxWidth(45);
            appointmentsModuleTable.getColumnModel().getColumn(1).setResizable(false);
            appointmentsModuleTable.getColumnModel().getColumn(1).setPreferredWidth(25);
            appointmentsModuleTable.getColumnModel().getColumn(2).setResizable(false);
            appointmentsModuleTable.getColumnModel().getColumn(3).setResizable(false);
            appointmentsModuleTable.getColumnModel().getColumn(4).setResizable(false);
            appointmentsModuleTable.getColumnModel().getColumn(5).setResizable(false);
        }

        appointmentsModulePatientMobileNoLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        appointmentsModulePatientMobileNoLabel.setForeground(new java.awt.Color(51, 51, 51));
        appointmentsModulePatientMobileNoLabel.setText("Patient's Mobile No : ");

        appointmentsModulePatientMobileNoTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        appointmentsModulePatientMobileNoTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        appointmentsModulePatientNameLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        appointmentsModulePatientNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        appointmentsModulePatientNameLabel.setText("Patient's Name : ");

        appointmentsModulePatientNameTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        appointmentsModulePatientNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        appointmentsModuleStaffInchargeLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        appointmentsModuleStaffInchargeLabel.setForeground(new java.awt.Color(51, 51, 51));
        appointmentsModuleStaffInchargeLabel.setText("Staff Incharge : ");

        appointmentsModuleStaffInchargeTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        appointmentsModuleStaffInchargeTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        appointmentsModuleAppointmentDateLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        appointmentsModuleAppointmentDateLabel.setForeground(new java.awt.Color(51, 51, 51));
        appointmentsModuleAppointmentDateLabel.setText("Appointment Date : ");

        appointmentsModuleAppointmentDateDateChooser.setDateFormatString("dd-MM-yyyy");
        appointmentsModuleAppointmentDateDateChooser.setDoubleBuffered(false);
        appointmentsModuleAppointmentDateDateChooser.setMaxSelectableDate(new java.util.Date(253370739701000L));
        appointmentsModuleAppointmentDateDateChooser.setMinSelectableDate(new java.util.Date(946659701000L));
        appointmentsModuleAppointmentDateDateChooser.setOpaque(false);

        appointmentsModuleAddButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        appointmentsModuleAddButton.setText("Add");
        appointmentsModuleAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentsModuleAddButtonActionPerformed(evt);
            }
        });

        appointmentsModuleModifyButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        appointmentsModuleModifyButton.setText("Modify");
        appointmentsModuleModifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentsModuleModifyButtonActionPerformed(evt);
            }
        });

        appointmentsModuleSearchButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        appointmentsModuleSearchButton.setText("Search");
        appointmentsModuleSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentsModuleSearchButtonActionPerformed(evt);
            }
        });

        appointmentsModuleDeleteButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        appointmentsModuleDeleteButton.setText("Delete");
        appointmentsModuleDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentsModuleDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout appointmentsModuleLayout = new javax.swing.GroupLayout(appointmentsModule);
        appointmentsModule.setLayout(appointmentsModuleLayout);
        appointmentsModuleLayout.setHorizontalGroup(
            appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentsModuleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appointmentsModuleLayout.createSequentialGroup()
                        .addComponent(appointmentsModulePatientNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appointmentsModulePatientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(appointmentsModuleLayout.createSequentialGroup()
                        .addComponent(appointmentsModuleStaffInchargeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appointmentsModuleStaffInchargeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(appointmentsModuleLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(appointmentsModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(appointmentsModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appointmentsModuleLayout.createSequentialGroup()
                        .addComponent(appointmentsModulePatientMobileNoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appointmentsModulePatientMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(appointmentsModuleLayout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(appointmentsModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(appointmentsModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(appointmentsModuleLayout.createSequentialGroup()
                            .addComponent(appointmentsModuleAppointmentDateLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(appointmentsModuleAppointmentDateDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(appointmentsModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appointmentsModuleScrollPane))
        );
        appointmentsModuleLayout.setVerticalGroup(
            appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentsModuleLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointmentsModulePatientNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(appointmentsModulePatientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appointmentsModulePatientMobileNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(appointmentsModulePatientMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appointmentsModuleAppointmentDateDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(appointmentsModuleStaffInchargeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(appointmentsModuleStaffInchargeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(appointmentsModuleAppointmentDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointmentsModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appointmentsModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appointmentsModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appointmentsModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(appointmentsModuleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        methodPanel.add(appointmentsModule, "card3");

        medicineModule.setBackground(new java.awt.Color(204, 204, 204));

        medicineModuleScrollPane.setBackground(new java.awt.Color(255, 255, 51));

        medicineModuleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Medicine Name", "Quantity", "Unit Price (RM)", "Expired Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        medicineModuleTable.setColumnSelectionAllowed(true);
        medicineModuleTable.getTableHeader().setReorderingAllowed(false);
        medicineModuleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicineModuleTableMouseClicked(evt);
            }
        });
        medicineModuleScrollPane.setViewportView(medicineModuleTable);
        medicineModuleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (medicineModuleTable.getColumnModel().getColumnCount() > 0) {
            medicineModuleTable.getColumnModel().getColumn(0).setMinWidth(25);
            medicineModuleTable.getColumnModel().getColumn(0).setMaxWidth(45);
            medicineModuleTable.getColumnModel().getColumn(1).setResizable(false);
            medicineModuleTable.getColumnModel().getColumn(1).setPreferredWidth(25);
            medicineModuleTable.getColumnModel().getColumn(2).setResizable(false);
            medicineModuleTable.getColumnModel().getColumn(3).setResizable(false);
            medicineModuleTable.getColumnModel().getColumn(4).setResizable(false);
            medicineModuleTable.getColumnModel().getColumn(4).setHeaderValue("Unit Price (RM)");
            medicineModuleTable.getColumnModel().getColumn(5).setResizable(false);
        }

        medicineModuleMedicineNameLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        medicineModuleMedicineNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        medicineModuleMedicineNameLabel.setText("Medicine Name : ");

        medicineModuleMedicineNameTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        medicineModuleMedicineNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        medicineModuleMedicineNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineModuleMedicineNameTextFieldActionPerformed(evt);
            }
        });

        medicineModuleMedicineIDLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        medicineModuleMedicineIDLabel.setForeground(new java.awt.Color(51, 51, 51));
        medicineModuleMedicineIDLabel.setText("Medicine's ID : ");

        medicineModuleMedicineIDTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        medicineModuleMedicineIDTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        medicineModuleMedicineIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineModuleMedicineIDTextFieldActionPerformed(evt);
            }
        });

        medicineModuleMedicineQuantityLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        medicineModuleMedicineQuantityLabel.setForeground(new java.awt.Color(51, 51, 51));
        medicineModuleMedicineQuantityLabel.setText("Quantity : ");

        medicineModuleMedicineQuantityTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        medicineModuleMedicineQuantityTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        medicineModuleMedicineExpiredDateLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        medicineModuleMedicineExpiredDateLabel.setForeground(new java.awt.Color(51, 51, 51));
        medicineModuleMedicineExpiredDateLabel.setText("Expired Date : ");

        medicineModuleMedicineExpiredDateDateChooser.setDateFormatString("dd-MM-yyyy");
        medicineModuleMedicineExpiredDateDateChooser.setDoubleBuffered(false);
        medicineModuleMedicineExpiredDateDateChooser.setMaxSelectableDate(new java.util.Date(253370739701000L));
        medicineModuleMedicineExpiredDateDateChooser.setMinSelectableDate(new java.util.Date(946659701000L));
        medicineModuleMedicineExpiredDateDateChooser.setOpaque(false);

        medicineModuleAddButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        medicineModuleAddButton.setText("Add");
        medicineModuleAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineModuleAddButtonActionPerformed(evt);
            }
        });

        medicineModuleModifyButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        medicineModuleModifyButton.setText("Modify");
        medicineModuleModifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineModuleModifyButtonActionPerformed(evt);
            }
        });

        medicineModuleSearchButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        medicineModuleSearchButton.setText("Search");
        medicineModuleSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineModuleSearchButtonActionPerformed(evt);
            }
        });

        medicineModuleDeleteButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        medicineModuleDeleteButton.setText("Delete");
        medicineModuleDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineModuleDeleteButtonActionPerformed(evt);
            }
        });

        medicineModuleMedicineUnitPriceLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        medicineModuleMedicineUnitPriceLabel.setForeground(new java.awt.Color(51, 51, 51));
        medicineModuleMedicineUnitPriceLabel.setText("Unit Price (RM) : ");

        medicineModuleMedicineUnitPriceTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        medicineModuleMedicineUnitPriceTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout medicineModuleLayout = new javax.swing.GroupLayout(medicineModule);
        medicineModule.setLayout(medicineModuleLayout);
        medicineModuleLayout.setHorizontalGroup(
            medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicineModuleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(medicineModuleLayout.createSequentialGroup()
                        .addGroup(medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(medicineModuleLayout.createSequentialGroup()
                                .addComponent(medicineModuleMedicineIDLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(medicineModuleMedicineIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(medicineModuleLayout.createSequentialGroup()
                                .addComponent(medicineModuleMedicineUnitPriceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(medicineModuleMedicineUnitPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(medicineModuleLayout.createSequentialGroup()
                                .addComponent(medicineModuleMedicineNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(medicineModuleMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(medicineModuleMedicineQuantityLabel))
                            .addGroup(medicineModuleLayout.createSequentialGroup()
                                .addComponent(medicineModuleMedicineExpiredDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(medicineModuleMedicineExpiredDateDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(medicineModuleMedicineQuantityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(medicineModuleLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(medicineModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(medicineModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(medicineModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(medicineModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
            .addGroup(medicineModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(medicineModuleScrollPane))
        );
        medicineModuleLayout.setVerticalGroup(
            medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicineModuleLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medicineModuleMedicineIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medicineModuleMedicineIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineModuleMedicineNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medicineModuleMedicineNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineModuleMedicineQuantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineModuleMedicineQuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(medicineModuleMedicineExpiredDateDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineModuleMedicineExpiredDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(medicineModuleMedicineUnitPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(medicineModuleMedicineUnitPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 33, Short.MAX_VALUE)
                .addGroup(medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medicineModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicineModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(medicineModuleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        methodPanel.add(medicineModule, "card4");

        staffModule.setBackground(new java.awt.Color(204, 204, 204));

        staffModuleScrollPane.setBackground(new java.awt.Color(255, 255, 51));

        staffModuleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID", "Name", "Designation", "Mobile No", "Date Joined"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffModuleTable.getTableHeader().setReorderingAllowed(false);
        staffModuleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffModuleTableMouseClicked(evt);
            }
        });
        staffModuleScrollPane.setViewportView(staffModuleTable);
        staffModuleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (staffModuleTable.getColumnModel().getColumnCount() > 0) {
            staffModuleTable.getColumnModel().getColumn(0).setMinWidth(25);
            staffModuleTable.getColumnModel().getColumn(0).setMaxWidth(45);
            staffModuleTable.getColumnModel().getColumn(1).setResizable(false);
            staffModuleTable.getColumnModel().getColumn(1).setPreferredWidth(25);
            staffModuleTable.getColumnModel().getColumn(2).setResizable(false);
            staffModuleTable.getColumnModel().getColumn(4).setResizable(false);
            staffModuleTable.getColumnModel().getColumn(5).setResizable(false);
        }

        staffModuleStaffNameLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        staffModuleStaffNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffModuleStaffNameLabel.setText("Name : ");

        staffModuleStaffNameTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        staffModuleStaffNameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        staffModuleStaffIDLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        staffModuleStaffIDLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffModuleStaffIDLabel.setText("ID : ");

        staffModuleStaffIDTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        staffModuleStaffIDTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        staffModuleStaffMobileNoLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        staffModuleStaffMobileNoLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffModuleStaffMobileNoLabel.setText("Mobile No : ");

        staffModuleStaffMobileNoTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        staffModuleStaffMobileNoTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        staffModuleStaffDateJoinedLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        staffModuleStaffDateJoinedLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffModuleStaffDateJoinedLabel.setText("Date Joined : ");

        staffModuleStaffDateJoinedDateChooser.setDateFormatString("dd-MM-yyyy");
        staffModuleStaffDateJoinedDateChooser.setDoubleBuffered(false);
        staffModuleStaffDateJoinedDateChooser.setMaxSelectableDate(new java.util.Date(253370739701000L));
        staffModuleStaffDateJoinedDateChooser.setMinSelectableDate(new java.util.Date(946659701000L));
        staffModuleStaffDateJoinedDateChooser.setOpaque(false);

        staffModuleAddButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        staffModuleAddButton.setText("Add");
        staffModuleAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffModuleAddButtonActionPerformed(evt);
            }
        });

        staffModuleModifyButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        staffModuleModifyButton.setText("Modify");
        staffModuleModifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffModuleModifyButtonActionPerformed(evt);
            }
        });

        staffModuleSearchButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        staffModuleSearchButton.setText("Search");
        staffModuleSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffModuleSearchButtonActionPerformed(evt);
            }
        });

        staffModuleDeleteButton.setFont(new java.awt.Font(".Heiti J", 1, 18)); // NOI18N
        staffModuleDeleteButton.setText("Delete");
        staffModuleDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffModuleDeleteButtonActionPerformed(evt);
            }
        });

        staffModuleStaffDesignationLabel.setFont(new java.awt.Font(".Heiti J", 0, 18)); // NOI18N
        staffModuleStaffDesignationLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffModuleStaffDesignationLabel.setText("Designation : ");

        staffModuleStaffDesignationTextField.setFont(new java.awt.Font(".Heiti J", 0, 14)); // NOI18N
        staffModuleStaffDesignationTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout staffModuleLayout = new javax.swing.GroupLayout(staffModule);
        staffModule.setLayout(staffModuleLayout);
        staffModuleLayout.setHorizontalGroup(
            staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffModuleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staffModuleLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(staffModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(staffModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(staffModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(staffModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(staffModuleLayout.createSequentialGroup()
                        .addComponent(staffModuleStaffMobileNoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staffModuleStaffMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(staffModuleStaffDateJoinedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staffModuleStaffDateJoinedDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(staffModuleLayout.createSequentialGroup()
                        .addComponent(staffModuleStaffIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staffModuleStaffIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(staffModuleStaffNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staffModuleStaffNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(staffModuleStaffDesignationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staffModuleStaffDesignationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(staffModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(staffModuleScrollPane))
        );
        staffModuleLayout.setVerticalGroup(
            staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffModuleLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staffModuleStaffDesignationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(staffModuleStaffNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(staffModuleStaffNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(staffModuleStaffDesignationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(staffModuleStaffIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(staffModuleStaffIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(staffModuleStaffMobileNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(staffModuleStaffMobileNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(staffModuleStaffDateJoinedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(staffModuleStaffDateJoinedDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staffModuleAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffModuleModifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffModuleSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffModuleDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(staffModuleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        methodPanel.add(staffModule, "card5");

        comingSoonModule.setBackground(new java.awt.Color(204, 204, 204));

        comingSoonModuleLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comingSoonModuleLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/coming.png"))); // NOI18N

        comingSoonModuleLabel.setFont(new java.awt.Font(".Heiti J", 1, 48)); // NOI18N
        comingSoonModuleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comingSoonModuleLabel.setText("In Progress...");
        comingSoonModuleLabel.setToolTipText("");

        javax.swing.GroupLayout comingSoonModuleLayout = new javax.swing.GroupLayout(comingSoonModule);
        comingSoonModule.setLayout(comingSoonModuleLayout);
        comingSoonModuleLayout.setHorizontalGroup(
            comingSoonModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(comingSoonModuleLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(comingSoonModuleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
        );
        comingSoonModuleLayout.setVerticalGroup(
            comingSoonModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comingSoonModuleLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(comingSoonModuleLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comingSoonModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        methodPanel.add(comingSoonModule, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(methodPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(methodPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void staffBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffBarMouseExited
        // TODO add your handling code here:
        sideBarListener.mouseExited(evt);
    }//GEN-LAST:event_staffBarMouseExited

    private void staffBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffBarMouseEntered
        // TODO add your handling code here:
        sideBarListener.mouseEntered(evt);
    }//GEN-LAST:event_staffBarMouseEntered

    private void medicineBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicineBarMouseExited
        // TODO add your handling code here:
        sideBarListener.mouseExited(evt);
    }//GEN-LAST:event_medicineBarMouseExited

    private void medicineBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicineBarMouseEntered
        // TODO add your handling code here:
        sideBarListener.mouseEntered(evt);
    }//GEN-LAST:event_medicineBarMouseEntered

    private void appointmentsBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsBarMouseExited
        // TODO add your handling code here:
        sideBarListener.mouseExited(evt);
    }//GEN-LAST:event_appointmentsBarMouseExited

    private void appointmentsBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsBarMouseEntered
        // TODO add your handling code here:
        sideBarListener.mouseEntered(evt);
    }//GEN-LAST:event_appointmentsBarMouseEntered

    private void patientBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientBarMouseExited
        // TODO add your handling code here:
        sideBarListener.mouseExited(evt);
    }//GEN-LAST:event_patientBarMouseExited

    private void patientBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientBarMouseEntered
        // TODO add your handling code here:
        sideBarListener.mouseEntered(evt);
    }//GEN-LAST:event_patientBarMouseEntered

    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_topPanelMousePressed

    private void topPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x-xx, y-yy);
    }//GEN-LAST:event_topPanelMouseDragged

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        // TODO add your handling code here:
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_closeLabelMouseClicked

    private void patientBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientBarMouseClicked
        // TODO add your handling code here:
        sideBarListener.mouseClicked(evt);
    }//GEN-LAST:event_patientBarMouseClicked

    private void appointmentsBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsBarMouseClicked
        // TODO add your handling code here:
        sideBarListener.mouseClicked(evt);
    }//GEN-LAST:event_appointmentsBarMouseClicked

    private void medicineBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicineBarMouseClicked
        // TODO add your handling code here:
        sideBarListener.mouseClicked(evt);
    }//GEN-LAST:event_medicineBarMouseClicked

    private void staffBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffBarMouseClicked
        // TODO add your handling code here:
        sideBarListener.mouseClicked(evt);
    }//GEN-LAST:event_staffBarMouseClicked

    private void comingSoonBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comingSoonBarMouseEntered
        // TODO add your handling code here:
        sideBarListener.mouseEntered(evt);
    }//GEN-LAST:event_comingSoonBarMouseEntered

    private void comingSoonBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comingSoonBarMouseExited
        // TODO add your handling code here:
        sideBarListener.mouseExited(evt);
    }//GEN-LAST:event_comingSoonBarMouseExited

    private void comingSoonBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comingSoonBarMouseClicked
        // TODO add your handling code here:
        sideBarListener.mouseClicked(evt);
    }//GEN-LAST:event_comingSoonBarMouseClicked

    //To-do List: Validate all field, prevent duplicate data entered
    private void patientsModuleAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleAddButtonActionPerformed
        // TODO add your handling code here:

        if(patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") &&
            patientsModuleMobileNoTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter IC, Name and Mobile No of Patient !!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(patientsModuleICTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter IC of Patient !!!", "Invalid IC", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(patientsModuleNameTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Name of Patient !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(patientsModuleMobileNoTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Mobile No of Patient !!!", "Invalid Mobile No", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(patientsModuleDateCreatedDateChooser.getDate() != null){
            
            JOptionPane.showMessageDialog(null, "Date Created is System Generated Input !!! \nPlease Leave it Blank !!!", "Clear Date Created Input", JOptionPane.ERROR_MESSAGE);
            patientsModuleDateCreatedDateChooser.setDate(null);
        }
        else{
            
            //validate input
            if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
            
            }
            else if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                
            }
            else if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                
            }
            else if(validateDuplicatePatientList(patientsModuleICTextField.getText(), patientsModuleMobileNoTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Patient's Record Already Exist", "Patient's Record Exist", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                int icNo = Integer.parseInt(patientsModuleICTextField.getText().substring(10));
                Date dateCreated = new Date();

                patient = new Patient(icNo, patientsModuleICTextField.getText(), patientsModuleNameTextField.getText(), patientsModuleMobileNoTextField.getText(), s.format(dateCreated));

                int addRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Sure to Add This Record ?</b> </html>\n" + patient, "Add Patient's Record", JOptionPane.YES_NO_OPTION);

                if(addRecord == 0){

                    patientList.add(patient);
                    savePatientsDataToFile();
                    JOptionPane.showMessageDialog(null, "Patient Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                    setPatientModelRow(patientList);

                }else{
                    
                    JOptionPane.showMessageDialog(null, "Cancel Add Patient's Record", "Cancel Adding", JOptionPane.INFORMATION_MESSAGE);
                
                }
                
                clearPatientsModuleInputField();
            }

        }

    }//GEN-LAST:event_patientsModuleAddButtonActionPerformed
    
    private void patientsModuleDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleDeleteButtonActionPerformed
        // TODO add your handling code here:

        if(patient != null){

            for(int i = 0; i < patientList.size(); i++){

                if(patient.getIcNo() == patientList.get(i).getIcNo()){

                    int deleteRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Record Found. Sure to delete ?</b> </html>\n" + patientList.get(i), "Patient's Record Found", JOptionPane.YES_NO_OPTION);

                    if(deleteRecord == 0){

                         patientList.remove(patientList.get(i));
                           savePatientsDataToFile();
                            JOptionPane.showMessageDialog(null, "Patient Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                            setPatientModelRow(patientList);

                    }else{

                        JOptionPane.showMessageDialog(null, "Cancel Delete Patient's Record", "Cancel Delete", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Record Selected !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
        
        }

    }//GEN-LAST:event_patientsModuleDeleteButtonActionPerformed
    
    //To-do list: (ic && name) && (ic && mobile) && (name && mobile)
    private void patientsModuleSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleSearchButtonActionPerformed
        // TODO add your handling code here:
        patientsModuleMedicalDescriptionLabel.setText(patientName);
        patientsModuleMedicalDescriptionTextArea.setText("");
        
        //IC only
        if(!patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
 
            if(validate.validateIc(patientsModuleICTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Name Only
        else if(patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){

            if(validate.validateName(patientsModuleNameTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleNameTextField.getText().equals(patientList.get(i).getName())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Mobile No Only
        else if(patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Date Created Only
        else if(patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && !(patientsModuleDateCreatedDateChooser.getDate() == null)){
            
            if(validate.validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(s.format(patientsModuleDateCreatedDateChooser.getDate()).equals(patientList.get(i).getDateCreated())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Date Created Format, Please Enter Again!!! \n Format : dd-MM-yyyy", "Invalid Date Created Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //IC, Name, Mobile No
        else if(!patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateName(patientsModuleNameTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleNameTextField.getText().equals(patientList.get(i).getName()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        //IC, Name, Mobile No, Date Created
        else if(!patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && !(patientsModuleDateCreatedDateChooser.getDate() == null)){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateName(patientsModuleNameTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) && validate.validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleNameTextField.getText().equals(patientList.get(i).getName()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo()) && s.format(patientsModuleDateCreatedDateChooser.getDate()).equals(patientList.get(i).getDateCreated())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate())) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Date Created Format, Please Enter Again!!! \n Format : dd-MM-yyyy", "Invalid Date Created Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        //IC, Name
        else if(!patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateName(patientsModuleNameTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleNameTextField.getText().equals(patientList.get(i).getName())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }

            }
        }
        //IC, Mobile No
        else if(!patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        //Name, Mobile No
        else if(patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateName(patientsModuleNameTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleNameTextField.getText().equals(patientList.get(i).getName()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        else{

            String icNo = JOptionPane.showInputDialog("Please Enter IC No to Search Records (Empty to Search All): ");

            
            if(icNo == null || icNo.equals("")){

                setPatientModelRow(patientList);

            }else /*if(validateIcNo(icNo, "search"))*/{

                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(Integer.parseInt(icNo) == patientList.get(i).getIcNo()){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));
                        
                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();


                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
    }//GEN-LAST:event_patientsModuleSearchButtonActionPerformed

    private void patientsModuleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientsModuleTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) patientsModuleTable.getModel();
        int index = patientsModuleTable.getSelectedRow();

        int icNo = Integer.parseInt(model.getValueAt(index, 1).toString());
        String ic = model.getValueAt(index, 2).toString();
        String name = model.getValueAt(index, 3).toString();
        
        //set Medical Description of Patient
        for(int i = 0; i < patientList.size(); i++){
            if(patientList.get(i).getIcNo() == icNo && patientList.get(i).getIc() == ic && patientList.get(i).getName().equals(name)){
                patient = patientList.get(i);
                patientsModuleMedicalDescriptionLabel.setText(patientName + patientList.get(i).getName());
                patientsModuleMedicalDescriptionTextArea.setText(patientList.get(i).getMedicalDescription());
            }
        }
    }//GEN-LAST:event_patientsModuleTableMouseClicked

    private void patientsModuleDiagnoseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleDiagnoseButtonActionPerformed
        // TODO add your handling code here:
        if(patientOnHoldList.size() != 0){

            int diagnoseRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Start Diagnose this Patient ? </b> </html>\n" + patientOnHoldList.get(0), "Patient's Record", JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(diagnoseRecord == 0){
                //Create New Frame
                onHoldPatient = patientOnHoldList.get(0);
                patientOnHoldList.remove(0);
                        
                PatientDiagnose patientDiagnose = new PatientDiagnose(this.staffName, onHoldPatient, this, this.patientOnHoldList);
                patientDiagnose.setVisible(true);
                
                this.dispose();
                
            }else if(diagnoseRecord == 1){
                //Done nothing
                
                
            }else if(diagnoseRecord == 2){
                
                JOptionPane.showMessageDialog(null, "Patient " + patientOnHoldList.get(0).getName() + " Cancel On Hold !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
                patientOnHoldList.remove(0);
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "On Hold Patient Not Found !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }//GEN-LAST:event_patientsModuleDiagnoseButtonActionPerformed

    private void patientsModuleOnHoldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleOnHoldButtonActionPerformed
        // TODO add your handling code here:
        if(patient != null){
            int addOnHoldPatient = JOptionPane.showConfirmDialog(null, "<html> <b>Add Following Patient to On Hold ? </b> </html>\n" + patient, "Patient's Record", JOptionPane.YES_NO_OPTION);
        
            if(addOnHoldPatient == 0){
                
                if(patientOnHoldList.size() == 0){
                    
                    patientOnHoldList.add(patient);
                    
                }else{
                    boolean duplicateOnHoldPatient = false;
                
                    for(int i = 0; i < patientOnHoldList.size(); i++){

                        if(patient.getIc() == patientOnHoldList.get(i).getIc() || patient.getMobileNo() == patientOnHoldList.get(i).getMobileNo()){
                            
                            duplicateOnHoldPatient = true;
                            
                        }
                        
                    }
                    
                    if(duplicateOnHoldPatient == false){
                        //Assign the patient into the onHoldPatient for transfer to next frame after click diagnose
                        patientOnHoldList.add(patient);
                        JOptionPane.showMessageDialog(null, "Added to On Hold Patient", "Notice", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Patient Already On Hold !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
                    }    
                }

            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Patient Selected !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }//GEN-LAST:event_patientsModuleOnHoldButtonActionPerformed

    private void patientsModuleModifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleModifyButtonActionPerformed
        // TODO add your handling code here:
        if(patient != null){
            
            int modifyPatient = JOptionPane.showConfirmDialog(null, "<html> <b>Modify Following Patient's Record ? </b> </html>\n" + patient, "Patient's Record", JOptionPane.YES_NO_OPTION);
        
            if(modifyPatient == 0){
                PatientModify patientModify = new PatientModify(this.staffName, patient, this, this.patientOnHoldList);
                patientModify.setVisible(true);
                //this.dispose();
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Patient Selected !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        
    }//GEN-LAST:event_patientsModuleModifyButtonActionPerformed

    private void appointmentsModuleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentsModuleTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentsModuleTableMouseClicked

    private void appointmentsModuleAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsModuleAddButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentsModuleAddButtonActionPerformed

    private void appointmentsModuleModifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsModuleModifyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentsModuleModifyButtonActionPerformed

    private void appointmentsModuleSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsModuleSearchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentsModuleSearchButtonActionPerformed

    private void appointmentsModuleDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsModuleDeleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentsModuleDeleteButtonActionPerformed

    private void medicineModuleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicineModuleTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) medicineModuleTable.getModel();
        int index = medicineModuleTable.getSelectedRow();

        String id = model.getValueAt(index, 1).toString();
        String name = model.getValueAt(index, 2).toString();
        
        //set Medical Description of Patient
        for(int i = 0; i < medicineList.size(); i++){
            if(medicineList.get(i).getId().equals(id) && medicineList.get(i).getMedicineName().equals(name)){
                medicine = medicineList.get(i);
            }
        }
    }//GEN-LAST:event_medicineModuleTableMouseClicked

    private void medicineModuleAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineModuleAddButtonActionPerformed
        if(medicineModuleMedicineIDTextField.getText().equals("") && medicineModuleMedicineNameTextField.getText().equals("") &&
            medicineModuleMedicineQuantityTextField.getText().equals("") && medicineModuleMedicineUnitPriceTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Medicine ID, Name, Quantity and Unit Price !!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(medicineModuleMedicineIDTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Medicine ID !!!", "Invalid Medicine ID", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(medicineModuleMedicineNameTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Medicine Name !!!", "Invalid Medicine Name", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(medicineModuleMedicineQuantityTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Quantity of Medicine !!!", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(medicineModuleMedicineUnitPriceTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Unit Price of Medicine !!!", "Invalid Unit Price", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(medicineModuleMedicineExpiredDateDateChooser.getDate() == null){
            
            JOptionPane.showMessageDialog(null, "Please Enter Expired Date !!!", "Invalid Expired Date", JOptionPane.ERROR_MESSAGE);
        }
        else if(validateDuplicateMedicineList(medicineModuleMedicineIDTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Medicine's Record Already Exist", "Medicine's Record Exist", JOptionPane.ERROR_MESSAGE);
                
        }
        else{
            //validate input
            if(mValidate.validateMedicineID(medicineModuleMedicineIDTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid ID Format, Please Enter Again !!! \n Format : xxxx \n E.g : 0001", "Invalid ID Format", JOptionPane.ERROR_MESSAGE);
            
            }
            else{
                int quantity = Integer.parseInt(medicineModuleMedicineQuantityTextField.getText());
                double unitPrice = Double.parseDouble(medicineModuleMedicineUnitPriceTextField.getText());
                
                medicine = new Medicine(medicineModuleMedicineIDTextField.getText(), medicineModuleMedicineNameTextField.getText(), quantity, unitPrice, s.format(medicineModuleMedicineExpiredDateDateChooser.getDate()));

                int addRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Sure to Add This Record ?</b> </html>\n" + medicine, "Add Medicine's Record", JOptionPane.YES_NO_OPTION);

                if(addRecord == 0){

                    medicineList.add(medicine);
                    saveMedicineDataToFile();
                    JOptionPane.showMessageDialog(null, "Medicine Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                    setMedicineModelRow(medicineList);

                }else{
                    
                    JOptionPane.showMessageDialog(null, "Cancel Add Medicine's Record", "Cancel Adding", JOptionPane.INFORMATION_MESSAGE);
                
                }
                
                clearMedicineModuleInputField();
            }

        }
    }//GEN-LAST:event_medicineModuleAddButtonActionPerformed

    private void medicineModuleModifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineModuleModifyButtonActionPerformed
        // TODO add your handling code here:
        if(medicine != null){
            
            int modifyMedicine = JOptionPane.showConfirmDialog(null, "<html> <b>Modify Following Medicine's Record ? </b> </html>\n" + medicine, "Medicine's Record", JOptionPane.YES_NO_OPTION);
        
            if(modifyMedicine == 0){
                ModifyMedicine medModify = new ModifyMedicine(this.staffName, medicine, this);
                medModify.setVisible(true);
                //this.dispose();
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Medicine Selected !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_medicineModuleModifyButtonActionPerformed

    private void medicineModuleSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineModuleSearchButtonActionPerformed
        // TODO add your handling code here:
        patientsModuleMedicalDescriptionLabel.setText(patientName);
        patientsModuleMedicalDescriptionTextArea.setText("");
        
        //IC only
        if(!patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
 
            if(validate.validateIc(patientsModuleICTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Name Only
        else if(patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){

            if(validate.validateName(patientsModuleNameTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleNameTextField.getText().equals(patientList.get(i).getName())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Mobile No Only
        else if(patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Date Created Only
        else if(patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && !(patientsModuleDateCreatedDateChooser.getDate() == null)){
            
            if(validate.validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(s.format(patientsModuleDateCreatedDateChooser.getDate()).equals(patientList.get(i).getDateCreated())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Date Created Format, Please Enter Again!!! \n Format : dd-MM-yyyy", "Invalid Date Created Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //IC, Name, Mobile No
        else if(!patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateName(patientsModuleNameTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleNameTextField.getText().equals(patientList.get(i).getName()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        //IC, Name, Mobile No, Date Created
        else if(!patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && !(patientsModuleDateCreatedDateChooser.getDate() == null)){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateName(patientsModuleNameTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) && validate.validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleNameTextField.getText().equals(patientList.get(i).getName()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo()) && s.format(patientsModuleDateCreatedDateChooser.getDate()).equals(patientList.get(i).getDateCreated())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate())) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Date Created Format, Please Enter Again!!! \n Format : dd-MM-yyyy", "Invalid Date Created Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        //IC, Name
        else if(!patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateName(patientsModuleNameTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleNameTextField.getText().equals(patientList.get(i).getName())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }

            }
        }
        //IC, Mobile No
        else if(!patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateIc(patientsModuleICTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateIc(patientsModuleICTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        //Name, Mobile No
        else if(patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
            
            if(validate.validateName(patientsModuleNameTextField.getText()) && validate.validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleNameTextField.getText().equals(patientList.get(i).getName()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setPatientModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate.validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate.validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        else{

            String id = JOptionPane.showInputDialog("Please Enter Medicine ID to Search Records (Empty to Search All): ");

            
            if(id == null || id.equals("")){

                setMedicineModelRow(medicineList);

            }else {

                boolean recordFound = false;

                for(int i = 0; i < medicineList.size(); i++){

                    if(id.equals(medicineList.get(i).getId())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        medicineSearchList.add(medicineList.get(i));
                        
                    }
                }

                setMedicineModelRow(medicineSearchList);
                //clear all the patientSearchList's record
                medicineSearchList.clear();


                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_medicineModuleSearchButtonActionPerformed

    private void medicineModuleDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineModuleDeleteButtonActionPerformed
        if(medicine != null){

            for(int i = 0; i < medicineList.size(); i++){

                if(medicine.getId().equals(medicineList.get(i).getId())){

                    int deleteRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Record Found. Sure to delete ?</b> </html>\n" + medicineList.get(i), "MEdicine's Record Found", JOptionPane.YES_NO_OPTION);

                    if(deleteRecord == 0){

                         medicineList.remove(medicineList.get(i));
                           saveMedicineDataToFile();
                            JOptionPane.showMessageDialog(null, "Medicine Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                            setMedicineModelRow(medicineList);

                    }else{

                        JOptionPane.showMessageDialog(null, "Cancel Delete Medicine's Record", "Cancel Delete", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Record Selected !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
        
        }
    }//GEN-LAST:event_medicineModuleDeleteButtonActionPerformed

    private void staffModuleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffModuleTableMouseClicked
          // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) staffModuleTable.getModel();
        int index = staffModuleTable.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(index, 1).toString());
        //String id = model.getValueAt(index, 2).toString();
        String name = model.getValueAt(index, 2).toString();
        String designation=model.getValueAt(index,3).toString();
        
        //set Medical Description of Patient
        for(int i = 0; i < staffList.size(); i++){
            if(staffList.get(i).getId() == id && staffList.get(i).getName().equals(name)){
                staff = staffList.get(i);
            }
        }
    }//GEN-LAST:event_staffModuleTableMouseClicked

    private void staffModuleAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffModuleAddButtonActionPerformed
        if(staffModuleStaffIDTextField.getText().equals("") && staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("")&&staffModuleStaffMobileNoTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter ID, Name and Mobile No of Staff !!!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(staffModuleStaffIDTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Id of staff !!!", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            
        }
        else if(staffModuleStaffNameTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Name of staff !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            
        }
          else if(staffModuleStaffDesignationTextField.getText() .equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter designation of staff !!!", "Designation", JOptionPane.ERROR_MESSAGE);
        }
        else if(staffModuleStaffMobileNoTextField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Enter Mobile No of staff !!!", "Invalid Mobile No", JOptionPane.ERROR_MESSAGE);
            
        }
      
        else{
            
            //validate input
            if(validate1.validateID(staffModuleStaffIDTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid Id Format, Please Enter Again !!! \n Format : xxxxx", "Invalid Id Format", JOptionPane.ERROR_MESSAGE);
            
            }
            else if(validate1.validateName(staffModuleStaffNameTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                
            }
            else if(validate1.validateDesignation(staffModuleStaffDesignationTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid Designation Format, Please Enter Again!!! \n Format :xxxxx", "Invalid Designation Format", JOptionPane.ERROR_MESSAGE);
                
            }
            else if(validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                
            }
            else if(validateDuplicateStaffList(staffModuleStaffIDTextField.getText(), staffModuleStaffMobileNoTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Staff's Record Already Exist", "Patient's Record Exist", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                int id = Integer.parseInt(staffModuleStaffIDTextField.getText());
                Date dateJoined = new Date();

                staff = new Staff(id, staffModuleStaffNameTextField.getText(), staffModuleStaffDesignationTextField.getText(),staffModuleStaffMobileNoTextField.getText(),s.format(dateJoined));

                int addRecords = JOptionPane.showConfirmDialog(null, "<html> <b>Sure to Add This Record ?</b> </html>\n" + staff, "Add Staff's Record", JOptionPane.YES_NO_OPTION);

                if(addRecords == 0){

                    staffList.add(staff);
                    saveStaffDataToFile();
                    JOptionPane.showMessageDialog(null, "Staff Records Updated !!!", "Records Updated", JOptionPane.INFORMATION_MESSAGE);
                    //setStaffModel(staffList);

                }else{
                    
                    JOptionPane.showMessageDialog(null, "Cancel Add Staff's Record", "Cancel Adding", JOptionPane.INFORMATION_MESSAGE);
                
                }
                
                clearStaffModuleInputField();
            }

        }
    }//GEN-LAST:event_staffModuleAddButtonActionPerformed

    private void staffModuleModifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffModuleModifyButtonActionPerformed
         // TODO add your handling code here:
        if(staff != null){
            
            int modifyStaff = JOptionPane.showConfirmDialog(null, "<html> <b>Modify Following Staff's Record ? </b> </html>\n" + staff, "Patient's Record", JOptionPane.YES_NO_OPTION);
        
            if(modifyStaff == 0){
                staffModify staffModify = new staffModify(this.staffName, staff,this);
                staffModify.setVisible(true);
                //this.dispose();
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No staff Selected !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }//GEN-LAST:event_staffModuleModifyButtonActionPerformed

    private void staffModuleSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffModuleSearchButtonActionPerformed
         if(!staffModuleStaffIDTextField.getText().equals("") && staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("") &&staffModuleStaffMobileNoTextField.getText().equals("") && staffModuleStaffDateJoinedDateChooser.getDate() == null){
 
            if(validate1.validateID(staffModuleStaffIDTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffIDTextField.getText().equals(staffList.get(i).getId())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx", "Invalid Id Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Name Only
        else if(staffModuleStaffIDTextField.getText().equals("") && !staffModuleStaffNameTextField.getText().equals("")&&staffModuleStaffDesignationTextField.getText().equals("") &&staffModuleStaffMobileNoTextField.equals("") && staffModuleStaffDateJoinedDateChooser.getDate() == null){

            if(validate1.validateName(staffModuleStaffNameTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffNameTextField.getText().equals(staffList.get(i).getName())){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Mobile No Only
        else if(staffModuleStaffIDTextField.getText().equals("") && staffModuleStaffNameTextField.getText().equals("") &&!staffModuleStaffDesignationTextField.getText().equals("") &&staffModuleStaffMobileNoTextField.getText().equals("") && staffModuleStaffDateJoinedDateChooser.getDate() == null){
            
            if(validate1.validateDesignation(staffModuleStaffDesignationTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffDesignationTextField.getText().equals(staffList.get(i).getDesignation())){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
            }
        }

else if(staffModuleStaffIDTextField.getText().equals("") && staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("") &&!staffModuleStaffMobileNoTextField.getText().equals("") && staffModuleStaffDateJoinedDateChooser.getDate() == null){
            
            if(validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffMobileNoTextField.getText().equals(staffList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Date Created Only
      else if(staffModuleStaffIDTextField.getText().equals("") && staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("") &&staffModuleStaffMobileNoTextField.getText().equals("") && ! (staffModuleStaffDateJoinedDateChooser.getDate() == null)){
            
            
            if(validate1.validateDateJoined(s.format(staffModuleStaffDateJoinedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(s.format(staffModuleStaffDateJoinedDateChooser.getDate()).equals(staffList.get(i).getDateJoined())){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Date Created Format, Please Enter Again!!! \n Format : dd-MM-yyyy", "Invalid Date Created Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //IC, Name, Mobile No
        else if(!staffModuleStaffIDTextField.getText().equals("") &&!staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("") &&staffModuleStaffMobileNoTextField.getText().equals("") &&staffModuleStaffDateJoinedDateChooser.getDate() == null){
            
            if(validate1.validateID(staffModuleStaffIDTextField.getText()) && validate1.validateName(staffModuleStaffNameTextField.getText()) && validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffIDTextField.getText().equals(staffList.get(i).getId()) && staffModuleStaffNameTextField.getText().equals(staffList.get(i).getName()) && staffModuleStaffMobileNoTextField.getText().equals(staffList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the patientSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate1.validateID(staffModuleStaffIDTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid ID Format, Please Enter Again !!! \n Format : xxxxxx", "Invalid ID Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1.validateName(staffModuleStaffNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        //ID, Name, Mobile No, Date Joined
       else if(!staffModuleStaffIDTextField.getText().equals("") &&!staffModuleStaffNameTextField.getText().equals("") &&!staffModuleStaffDesignationTextField.getText().equals("") &&!staffModuleStaffMobileNoTextField.getText().equals("") &&!(staffModuleStaffDateJoinedDateChooser.getDate() == null)){
            
            if(validate1.validateID(staffModuleStaffIDTextField.getText()) && validate1.validateName(staffModuleStaffNameTextField.getText())&& validate1.validateDesignation(staffModuleStaffDesignationTextField.getText()) && validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText()) && validate1.validateDateJoined(s.format(staffModuleStaffDateJoinedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffIDTextField.getText().equals(staffList.get(i).getId()) && staffModuleStaffNameTextField.getText().equals(staffList.get(i).getName()) && staffModuleStaffDesignationTextField.getText().equals(staffList.get(i).getDesignation())&& staffModuleStaffMobileNoTextField.getText().equals(staffList.get(i).getMobileNo()) && s.format(staffModuleStaffDateJoinedDateChooser.getDate()).equals(staffList.get(i).getDateJoined())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate1.validateID(staffModuleStaffIDTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid ID Format, Please Enter Again !!! \n Format : xxxxxx", "Invalid ID Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1.validateName(staffModuleStaffNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
		if(validate1.validateDesignation(staffModuleStaffDesignationTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Designation Format, Please Enter Again!!! \n Format : xxxxx", "Invalid Designation Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1. validateDateJoined(s.format(staffModuleStaffDateJoinedDateChooser.getDate())) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Date Created Format, Please Enter Again!!! \n Format : dd-MM-yyyy", "Invalid Date Created Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        //IC, Name
    else if(!staffModuleStaffIDTextField.getText().equals("") &&!staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("") &&staffModuleStaffMobileNoTextField.getText().equals("") &&staffModuleStaffDateJoinedDateChooser.getDate() == null){
              
            if(validate1.validateID(staffModuleStaffIDTextField.getText()) && validate1.validateName(staffModuleStaffNameTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffIDTextField.getText().equals(staffList.get(i).getId()) && staffModuleStaffNameTextField.getText().equals(staffList.get(i).getName())){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate1.validateID(staffModuleStaffIDTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Id Format, Please Enter Again !!! \n Format : xxxxxx", "Invalid Id Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1.validateName(staffModuleStaffNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }

            }
        }
        //IC, Mobile No
 	 else if(!staffModuleStaffIDTextField.getText().equals("") &&staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("") &&!staffModuleStaffMobileNoTextField.getText().equals("") &&staffModuleStaffDateJoinedDateChooser.getDate() == null){
             
            if(validate1.validateID(staffModuleStaffIDTextField.getText()) && validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffIDTextField.getText().equals(staffList.get(i).getId()) && staffModuleStaffMobileNoTextField.getText().equals(staffList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate1.validateID(staffModuleStaffIDTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Id Format, Please Enter Again !!! \n Format : xxxxxx", "Invalid Id Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        //Name, Mobile No
            else if(staffModuleStaffIDTextField.getText().equals("") &&!staffModuleStaffNameTextField.getText().equals("") &&staffModuleStaffDesignationTextField.getText().equals("") &&!staffModuleStaffMobileNoTextField.getText().equals("") &&!(staffModuleStaffDateJoinedDateChooser.getDate() == null)){
            
            if(validate1.validateName(staffModuleStaffNameTextField.getText()) && validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(staffModuleStaffNameTextField.getText().equals(staffList.get(i).getName()) && staffModuleStaffMobileNoTextField.getText().equals(staffList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        staffSearchList.add(staffList.get(i));

                    }
                }

                setStaffModel(staffSearchList);
                //clear all the patientSearchList's record
                staffSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validate1.validateName(staffModuleStaffNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validate1.validateMobileNo(staffModuleStaffMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        else{

            String id = JOptionPane.showInputDialog("Please Enter id to Search Records (Empty to Search All): ");

            
            if(id == null || id.equals("")){

                setStaffModel(staffList);

            }else /*if(validateID(Id, "search"))*/{

                boolean recordFound = false;

                for(int i = 0; i < staffList.size(); i++){

                    if(Integer.parseInt(id) == staffList.get(i).getId()){
                        recordFound = true;

                        //Add to the staffSearchList for display purpose
                        staffSearchList.add(staffList.get(i));
                        
                    }
                }

                setStaffModel(staffSearchList);
                //clear all the staffSearchList's record
               staffSearchList.clear();


                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        
        
    }   
    }//GEN-LAST:event_staffModuleSearchButtonActionPerformed

    private void staffModuleDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffModuleDeleteButtonActionPerformed
        // TODO add your handling code here:
          if(staff != null){

            for(int i = 0; i < staffList.size(); i++){

                if(staff.getId() == staffList.get(i).getId()){

                    int deleteRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Record Found. Sure to delete ?</b> </html>\n" + staffList.get(i), "Staff's Record Found", JOptionPane.YES_NO_OPTION);

                    if(deleteRecord == 0){

                         staffList.remove(staffList.get(i));
                           saveStaffDataToFile();
                            JOptionPane.showMessageDialog(null, "Staff Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                            setStaffModel(staffList);

                    }else{

                        JOptionPane.showMessageDialog(null, "Cancel Delete Staff's Record", "Cancel Delete", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Record Selected !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
        
        }
    }//GEN-LAST:event_staffModuleDeleteButtonActionPerformed

    private void patientsModulePaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModulePaymentButtonActionPerformed
        // TODO add your handling code here:
        if(patient != null){

            for(int i = 0; i < patientList.size(); i++){

                if(patient.getIcNo() == patientList.get(i).getIcNo()){

                    int paymentRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Record Found. Proceed to Payment ?</b> </html>\n" + patientList.get(i), "Patient's Record Found", JOptionPane.YES_NO_OPTION);

                    if(paymentRecord == 0){

                         //Patients Payment
                         PatientPaymentMedicine patientPayment = new PatientPaymentMedicine(this.staffName, patientList.get(i), this, this.patientOnHoldList);
                         patientPayment.setVisible(true);
                         
                         this.dispose();
                
                this.dispose();

                    }else{

                        JOptionPane.showMessageDialog(null, "Cancel Patient's Payment", "Cancel Payment", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "No Record Selected !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
        
        }
    }//GEN-LAST:event_patientsModulePaymentButtonActionPerformed

    private void medicineModuleMedicineNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineModuleMedicineNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineModuleMedicineNameTextFieldActionPerformed

    private void medicineModuleMedicineIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineModuleMedicineIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineModuleMedicineIDTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
                //new Home("lky1020").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel appointmentsBar;
    private javax.swing.JPanel appointmentsHoverPanel;
    private javax.swing.JLabel appointmentsLabel;
    private javax.swing.JLabel appointmentsLogo;
    private javax.swing.JPanel appointmentsModule;
    private javax.swing.JButton appointmentsModuleAddButton;
    private com.toedter.calendar.JDateChooser appointmentsModuleAppointmentDateDateChooser;
    private javax.swing.JLabel appointmentsModuleAppointmentDateLabel;
    private javax.swing.JButton appointmentsModuleDeleteButton;
    private javax.swing.JButton appointmentsModuleModifyButton;
    private javax.swing.JLabel appointmentsModulePatientMobileNoLabel;
    private javax.swing.JTextField appointmentsModulePatientMobileNoTextField;
    private javax.swing.JLabel appointmentsModulePatientNameLabel;
    private javax.swing.JTextField appointmentsModulePatientNameTextField;
    private javax.swing.JScrollPane appointmentsModuleScrollPane;
    private javax.swing.JButton appointmentsModuleSearchButton;
    private javax.swing.JLabel appointmentsModuleStaffInchargeLabel;
    private javax.swing.JTextField appointmentsModuleStaffInchargeTextField;
    private javax.swing.JTable appointmentsModuleTable;
    private javax.swing.JLabel clinicLogo;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JPanel comingSoonBar;
    private javax.swing.JPanel comingSoonHoverPanel;
    private javax.swing.JLabel comingSoonLabel;
    private javax.swing.JLabel comingSoonLogo;
    private javax.swing.JPanel comingSoonModule;
    private javax.swing.JLabel comingSoonModuleLabel;
    private javax.swing.JLabel comingSoonModuleLogo;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel greetLabel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel medicineBar;
    private javax.swing.JPanel medicineHoverPanel;
    private javax.swing.JLabel medicineLabel;
    private javax.swing.JLabel medicineLogo;
    private javax.swing.JPanel medicineModule;
    private javax.swing.JButton medicineModuleAddButton;
    private javax.swing.JButton medicineModuleDeleteButton;
    private com.toedter.calendar.JDateChooser medicineModuleMedicineExpiredDateDateChooser;
    private javax.swing.JLabel medicineModuleMedicineExpiredDateLabel;
    private javax.swing.JLabel medicineModuleMedicineIDLabel;
    private javax.swing.JTextField medicineModuleMedicineIDTextField;
    private javax.swing.JLabel medicineModuleMedicineNameLabel;
    private javax.swing.JTextField medicineModuleMedicineNameTextField;
    private javax.swing.JLabel medicineModuleMedicineQuantityLabel;
    private javax.swing.JTextField medicineModuleMedicineQuantityTextField;
    private javax.swing.JLabel medicineModuleMedicineUnitPriceLabel;
    private javax.swing.JTextField medicineModuleMedicineUnitPriceTextField;
    private javax.swing.JButton medicineModuleModifyButton;
    private javax.swing.JScrollPane medicineModuleScrollPane;
    private javax.swing.JButton medicineModuleSearchButton;
    private javax.swing.JTable medicineModuleTable;
    private javax.swing.JPanel methodPanel;
    private javax.swing.JPanel patientBar;
    private javax.swing.JPanel patientHoverPanel;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JLabel patientLogo;
    private javax.swing.JPanel patientsModule;
    private javax.swing.JButton patientsModuleAddButton;
    private com.toedter.calendar.JDateChooser patientsModuleDateCreatedDateChooser;
    private javax.swing.JLabel patientsModuleDateCreatedLabel;
    private javax.swing.JButton patientsModuleDeleteButton;
    private javax.swing.JButton patientsModuleDiagnoseButton;
    private javax.swing.JLabel patientsModuleICLabel;
    private javax.swing.JTextField patientsModuleICTextField;
    private javax.swing.JLabel patientsModuleMedicalDescriptionLabel;
    private javax.swing.JScrollPane patientsModuleMedicalDescriptionScrollPane;
    private javax.swing.JTextArea patientsModuleMedicalDescriptionTextArea;
    private javax.swing.JLabel patientsModuleMobileNoLabel;
    private javax.swing.JTextField patientsModuleMobileNoTextField;
    private javax.swing.JButton patientsModuleModifyButton;
    private javax.swing.JLabel patientsModuleNameLabel;
    private javax.swing.JTextField patientsModuleNameTextField;
    private javax.swing.JButton patientsModuleOnHoldButton;
    private javax.swing.JButton patientsModulePaymentButton;
    private javax.swing.JScrollPane patientsModuleScrollPane;
    private javax.swing.JButton patientsModuleSearchButton;
    private javax.swing.JTable patientsModuleTable;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel staffBar;
    private javax.swing.JPanel staffHoverPanel;
    private javax.swing.JLabel staffLabel;
    private javax.swing.JLabel staffLogo;
    private javax.swing.JPanel staffModule;
    private javax.swing.JButton staffModuleAddButton;
    private javax.swing.JButton staffModuleDeleteButton;
    private javax.swing.JButton staffModuleModifyButton;
    private javax.swing.JScrollPane staffModuleScrollPane;
    private javax.swing.JButton staffModuleSearchButton;
    private com.toedter.calendar.JDateChooser staffModuleStaffDateJoinedDateChooser;
    private javax.swing.JLabel staffModuleStaffDateJoinedLabel;
    private javax.swing.JLabel staffModuleStaffDesignationLabel;
    private javax.swing.JTextField staffModuleStaffDesignationTextField;
    private javax.swing.JLabel staffModuleStaffIDLabel;
    private javax.swing.JTextField staffModuleStaffIDTextField;
    private javax.swing.JLabel staffModuleStaffMobileNoLabel;
    private javax.swing.JTextField staffModuleStaffMobileNoTextField;
    private javax.swing.JLabel staffModuleStaffNameLabel;
    private javax.swing.JTextField staffModuleStaffNameTextField;
    private javax.swing.JTable staffModuleTable;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeTitle;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}

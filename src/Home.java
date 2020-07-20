
import Class.Patient;
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

public class Home extends javax.swing.JFrame {

    private SideBarListener sideBarListener = new SideBarListener();
    private DefaultTableModel patientModel;
    private List<Patient> patientList = new ArrayList<>();
    private List<Patient> patientSearchList = new ArrayList<>();
    private Patient patient;
    private Patient onHoldPatient;
    private String patientName; //for medical description label
    
    SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
    
    /**
     * Creates new form Home
     */
    public Home(String username) {
        initComponents();
        setLocationRelativeTo(null);
        patientHoverPanel.setBackground( new Color(2, 165, 249));
        patientBar.setBackground( new Color(63, 218, 234));
        
        greetLabel.setText(greetLabel.getText() + username);
        
        Date d = new Date();
        dateLabel.setText(dateLabel.getText() + s.format(d));
        
        
        //set the background to transparent
        //patientsModuleICNoTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        patientsModuleICTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        patientsModuleNameTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        patientsModuleMobileNoTextField.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        showTime();
        
        patientsModuleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientName = patientsModuleMedicalDescriptionLabel.getText();
        //hardcode Patient Records
        /*patient = new Patient(1020, "001020-14-1020", "Lim Kah Yee", "012-6348561", "26-09-2012", "26-09-2012\nCough, Fewer\n");
        patientList.add(patient);
        patient = new Patient(3468, "011120-14-3468", "Wong Hui Ping", "017-3468520", "20-11-2010", "20-11-2010\nCough, Fewer\n");
        patientList.add(patient);
        patient = new Patient(5913, "940614-11-5913", "Tan Yan Kai", "012-3795013", "14-06-2018", "14-06-2018\nCough, Fewer\n");
        patientList.add(patient);
        patient = new Patient(4358, "861028-01-4358", "Loo Khai Sheng", "016-3468215", "25-12-2019", "25-12-2019\nCough, Fewer\n");
        patientList.add(patient);
        patient = new Patient(9245, "761023-14-9245", "Lee Wei Chian", "017-2803462", "07-07-2018", "07-07-2018\nCough, Fewer\n");
        patientList.add(patient);
        
        savePatientsDataToFile();*/

        //read and show Patient Records
        readPatientsDataFromFile();
        patientModel = (DefaultTableModel)patientsModuleTable.getModel();
        setModelRow(patientList);

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
    
    private void setModelRow(List<Patient> arrayList){

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
    
    public boolean validateIc(String ic){
        Pattern pattern = Pattern.compile("\\d{6}-\\d{2}-\\d{4}");
        Matcher matcher = pattern.matcher(ic);
        
        if(matcher.matches()){
            
            return true;
            
        }else{
            
            return false;
            
        }
    }
    
    public boolean validateName(String name){
        String regexName = "\\p{Upper}(\\p{Lower}+\\s?)";
        String patternName = "(" + regexName + "){2,3}";
        
        if(name.matches(patternName)){
            
            return true;
            
        }else{
            
            return false;
            
        }
    }
    
    public boolean validateMobileNo(String mobileNo){
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(mobileNo);
        
        if(matcher.matches()){
            
            return true;
            
        }else{
            
            return false;
            
        }
    }
    
    public boolean validateDateCreated(String dateCreated){
        Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Matcher matcher = pattern.matcher(dateCreated);
        
        if(matcher.matches()){
            
            return true;
            
        }else{
            
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
    
    
    static int xx, yy; 
    private class SideBarListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == patientBar){
                patientsModule.setVisible(true);
                appointmentsModule.setVisible(false);
                medicineModule.setVisible(false);
                staffModule.setVisible(false);
            }
            
            if(e.getSource() == appointmentsBar){
                patientsModule.setVisible(false);
                appointmentsModule.setVisible(true);
                medicineModule.setVisible(false);
                staffModule.setVisible(false);
            }
            
            if(e.getSource() == medicineBar){
                patientsModule.setVisible(false);
                appointmentsModule.setVisible(false);
                medicineModule.setVisible(true);
                staffModule.setVisible(false);
            }
            
            if(e.getSource() == staffBar){
                patientsModule.setVisible(false);
                appointmentsModule.setVisible(false);
                medicineModule.setVisible(false);
                staffModule.setVisible(true);
            }
            
            if(e.getSource() == comingSoonBar){
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
            label[0].setBackground( new Color(2, 165, 249));
            panel.setBackground( new Color(63, 218, 234));
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel panel = (JPanel)e.getSource();
            Component[] label = panel.getComponents();
            label[0].setBackground( new Color(255, 255, 255));
            panel.setBackground( new Color(255, 255, 255));
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
        appointmentsModule = new javax.swing.JPanel();
        appointmentsModuleLabel = new javax.swing.JLabel();
        medicineModule = new javax.swing.JPanel();
        medicineModuleLabel = new javax.swing.JLabel();
        staffModule = new javax.swing.JPanel();
        staffModuleLabel = new javax.swing.JLabel();
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
        timeLabel.setForeground(new java.awt.Color(0, 0, 0));

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
        patientLabel.setForeground(new java.awt.Color(0, 0, 0));
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
        appointmentsLabel.setForeground(new java.awt.Color(0, 0, 0));
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
        medicineLabel.setForeground(new java.awt.Color(0, 0, 0));
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
        staffLabel.setForeground(new java.awt.Color(0, 0, 0));
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
            .addGroup(staffBarLayout.createSequentialGroup()
                .addComponent(staffHoverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        comingSoonLabel.setForeground(new java.awt.Color(0, 0, 0));
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

        methodPanel.setBackground(new java.awt.Color(240, 240, 240));
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
                        .addGap(28, 28, 28)
                        .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patientsModuleLayout.createSequentialGroup()
                                .addComponent(patientsModuleDateCreatedLabel)
                                .addGap(0, 0, 0)
                                .addComponent(patientsModuleDateCreatedDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(patientsModuleLayout.createSequentialGroup()
                                .addComponent(patientsModuleNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(patientsModuleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(135, Short.MAX_VALUE))
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
                .addGroup(patientsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientsModuleScrollPane)
                    .addComponent(patientsModuleMedicalDescriptionScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientsModuleLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(patientsModuleOnHoldButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(patientsModuleDiagnoseButton)))
                .addContainerGap())
            .addGroup(patientsModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(patientsModuleMedicalDescriptionLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(patientsModuleOnHoldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        methodPanel.add(patientsModule, "card2");

        appointmentsModule.setBackground(new java.awt.Color(255, 51, 204));

        appointmentsModuleLabel.setFont(new java.awt.Font(".Heiti J", 1, 24)); // NOI18N
        appointmentsModuleLabel.setForeground(new java.awt.Color(51, 51, 51));
        appointmentsModuleLabel.setText("Appointments Module");
        appointmentsModuleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));

        javax.swing.GroupLayout appointmentsModuleLayout = new javax.swing.GroupLayout(appointmentsModule);
        appointmentsModule.setLayout(appointmentsModuleLayout);
        appointmentsModuleLayout.setHorizontalGroup(
            appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentsModuleLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(appointmentsModuleLabel)
                .addContainerGap(468, Short.MAX_VALUE))
        );
        appointmentsModuleLayout.setVerticalGroup(
            appointmentsModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appointmentsModuleLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(appointmentsModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        methodPanel.add(appointmentsModule, "card3");

        medicineModule.setBackground(new java.awt.Color(255, 255, 0));

        medicineModuleLabel.setFont(new java.awt.Font(".Heiti J", 1, 24)); // NOI18N
        medicineModuleLabel.setForeground(new java.awt.Color(51, 51, 51));
        medicineModuleLabel.setText("Medicine Module");
        medicineModuleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));

        javax.swing.GroupLayout medicineModuleLayout = new javax.swing.GroupLayout(medicineModule);
        medicineModule.setLayout(medicineModuleLayout);
        medicineModuleLayout.setHorizontalGroup(
            medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicineModuleLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(medicineModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(517, Short.MAX_VALUE))
        );
        medicineModuleLayout.setVerticalGroup(
            medicineModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicineModuleLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(medicineModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        methodPanel.add(medicineModule, "card4");

        staffModule.setBackground(new java.awt.Color(102, 255, 102));

        staffModuleLabel.setFont(new java.awt.Font(".Heiti J", 1, 24)); // NOI18N
        staffModuleLabel.setForeground(new java.awt.Color(51, 51, 51));
        staffModuleLabel.setText("Staff Module");
        staffModuleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));

        javax.swing.GroupLayout staffModuleLayout = new javax.swing.GroupLayout(staffModule);
        staffModule.setLayout(staffModuleLayout);
        staffModuleLayout.setHorizontalGroup(
            staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffModuleLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(staffModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(517, Short.MAX_VALUE))
        );
        staffModuleLayout.setVerticalGroup(
            staffModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffModuleLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(staffModuleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        methodPanel.add(staffModule, "card5");

        comingSoonModule.setBackground(new java.awt.Color(204, 204, 204));

        comingSoonModuleLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comingSoonModuleLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/coming.png"))); // NOI18N

        comingSoonModuleLabel.setFont(new java.awt.Font(".Heiti J", 1, 48)); // NOI18N
        comingSoonModuleLabel.setForeground(new java.awt.Color(0, 0, 0));
        comingSoonModuleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comingSoonModuleLabel.setText("In Progress...");
        comingSoonModuleLabel.setToolTipText("");

        javax.swing.GroupLayout comingSoonModuleLayout = new javax.swing.GroupLayout(comingSoonModule);
        comingSoonModule.setLayout(comingSoonModuleLayout);
        comingSoonModuleLayout.setHorizontalGroup(
            comingSoonModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(comingSoonModuleLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(comingSoonModuleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );
        comingSoonModuleLayout.setVerticalGroup(
            comingSoonModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comingSoonModuleLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(comingSoonModuleLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(comingSoonModuleLabel)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        methodPanel.add(comingSoonModule, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(methodPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
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
            
            JOptionPane.showMessageDialog(null, "Date Created is System Generated Input !!! \n Please Clear It !!!", "Clear Date Created Input", JOptionPane.ERROR_MESSAGE);
            
        }
        else{
            
            //validate input
            if(validateIc(patientsModuleICTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
            
            }
            else if(validateName(patientsModuleNameTextField.getText()) != true){
                
                JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                
            }
            else if(validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                
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
                    setModelRow(patientList);

                }else{
                    
                    JOptionPane.showMessageDialog(null, "Cancel Add Patient's Record", "Cancel Adding", JOptionPane.INFORMATION_MESSAGE);
                
                }
                
                clearPatientsModuleInputField();
            }

        }

    }//GEN-LAST:event_patientsModuleAddButtonActionPerformed
    
    private void patientsModuleDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleDeleteButtonActionPerformed
        // TODO add your handling code here:
        
        String icNo;
        
        do{
            
            icNo = JOptionPane.showInputDialog("Please Enter IC No to Delete Records : ");
            
        }while(!validateIcNo(icNo, "Delete"));

        
        boolean recordFound = false;
            
        for(int i = 0; i < patientList.size(); i++){
                
            if(Integer.parseInt(icNo) == patientList.get(i).getIcNo()){
               
                recordFound = true;
                int deleteRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Record Found. Sure to delete ?</b> </html>\n" + patientList.get(i), "Patient's Record Found", JOptionPane.YES_NO_OPTION);
                
                if(deleteRecord == 0){
                        
                     patientList.remove(patientList.get(i));
                       savePatientsDataToFile();
                        JOptionPane.showMessageDialog(null, "Patient Records Updated !!!", "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                        setModelRow(patientList);
                        
                }else{
                        
                    JOptionPane.showMessageDialog(null, "Cancel Delete Patient's Record", "Cancel Delete", JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        }
            
        if(recordFound != true){
            JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_patientsModuleDeleteButtonActionPerformed
    
    //To-do list: (ic && name) && (ic && mobile) && (name && mobile)
    private void patientsModuleSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleSearchButtonActionPerformed
        // TODO add your handling code here:
        patientsModuleMedicalDescriptionLabel.setText(patientName);
        patientsModuleMedicalDescriptionTextArea.setText("");
        
        //IC only
        if(!patientsModuleICTextField.getText().equals("") && patientsModuleNameTextField.getText().equals("") && patientsModuleMobileNoTextField.getText().equals("") && patientsModuleDateCreatedDateChooser.getDate() == null){
 
            if(validateIc(patientsModuleICTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setModelRow(patientSearchList);
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

            if(validateName(patientsModuleNameTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleNameTextField.getText().equals(patientList.get(i).getName())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setModelRow(patientSearchList);
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
            
            if(validateMobileNo(patientsModuleMobileNoTextField.getText())){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setModelRow(patientSearchList);
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
            
            if(validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(s.format(patientsModuleDateCreatedDateChooser.getDate()).equals(patientList.get(i).getDateCreated())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
            }
        }
        //IC, Name, Mobile No, Date Created
        else if(!patientsModuleICTextField.getText().equals("") && !patientsModuleNameTextField.getText().equals("") && !patientsModuleMobileNoTextField.getText().equals("") && !(patientsModuleDateCreatedDateChooser.getDate() == null)){
            
            if(validateName(patientsModuleNameTextField.getText()) && validateName(patientsModuleNameTextField.getText()) && validateMobileNo(patientsModuleMobileNoTextField.getText()) && validateDateCreated(s.format(patientsModuleDateCreatedDateChooser.getDate()))){
                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(patientsModuleICTextField.getText().equals(patientList.get(i).getIc()) && patientsModuleNameTextField.getText().equals(patientList.get(i).getName()) && patientsModuleMobileNoTextField.getText().equals(patientList.get(i).getMobileNo()) && s.format(patientsModuleDateCreatedDateChooser.getDate()).equals(patientList.get(i).getDateCreated())){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));

                    }
                }

                setModelRow(patientSearchList);
                //clear all the patientSearchList's record
                patientSearchList.clear();

                if(recordFound != true){
                     JOptionPane.showMessageDialog(null, "No Record Found !!!", "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }else{
                
                if(validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid IC Format, Please Enter Again !!! \n Format : xxxxxx-xx-xxxx", "Invalid IC Format", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validateName(patientsModuleNameTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name, Please Enter Again Entered !!!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                if(validateMobileNo(patientsModuleMobileNoTextField.getText()) != true){
                    
                    JOptionPane.showMessageDialog(null, "Invalid Mobile No Format, Please Enter Again!!! \n Format : xxx-xxxxxxx", "Invalid Mobile No Format", JOptionPane.ERROR_MESSAGE);
                    
                }
  
            }
        }
        else{

            String icNo = JOptionPane.showInputDialog("Please Enter IC No to Search Records (Empty to Search All): ");

            
            if(icNo == null || icNo.equals("")){

                setModelRow(patientList);

            }else /*if(validateIcNo(icNo, "search"))*/{

                boolean recordFound = false;

                for(int i = 0; i < patientList.size(); i++){

                    if(Integer.parseInt(icNo) == patientList.get(i).getIcNo()){
                        recordFound = true;

                        //Add to the patientSearchList for display purpose
                        patientSearchList.add(patientList.get(i));
                        
                    }
                }

                setModelRow(patientSearchList);
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
        if(onHoldPatient != null){
            
            int diagnoseRecord = JOptionPane.showConfirmDialog(null, "<html> <b>Start Diagnose this Patient ? </b> </html>\n" + onHoldPatient, "Patient's Record", JOptionPane.YES_NO_OPTION);
        
        }else{
            JOptionPane.showMessageDialog(null, "On Hold Patient Not Found !!!", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_patientsModuleDiagnoseButtonActionPerformed

    private void patientsModuleOnHoldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsModuleOnHoldButtonActionPerformed
        // TODO add your handling code here:
        int addOnHoldPatient = JOptionPane.showConfirmDialog(null, "<html> <b>Add Following Patient to On Hold ? </b> </html>\n" + patient, "Patient's Record", JOptionPane.YES_NO_OPTION);
        
        if(addOnHoldPatient == 0){
            //Assign the patient into the onHoldPatient for transfer to next frame after click diagnose
            onHoldPatient = patient;
            JOptionPane.showMessageDialog(null, "Added to On Hold Patient", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_patientsModuleOnHoldButtonActionPerformed

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
                new Home("lky1020").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel appointmentsBar;
    private javax.swing.JPanel appointmentsHoverPanel;
    private javax.swing.JLabel appointmentsLabel;
    private javax.swing.JLabel appointmentsLogo;
    private javax.swing.JPanel appointmentsModule;
    private javax.swing.JLabel appointmentsModuleLabel;
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
    private javax.swing.JLabel medicineModuleLabel;
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
    private javax.swing.JScrollPane patientsModuleScrollPane;
    private javax.swing.JButton patientsModuleSearchButton;
    private javax.swing.JTable patientsModuleTable;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel staffBar;
    private javax.swing.JPanel staffHoverPanel;
    private javax.swing.JLabel staffLabel;
    private javax.swing.JLabel staffLogo;
    private javax.swing.JPanel staffModule;
    private javax.swing.JLabel staffModuleLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeTitle;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}

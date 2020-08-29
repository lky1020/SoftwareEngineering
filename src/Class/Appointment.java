
package Class;

public class Appointment {
    private int icNo;
    private String patientName;
    private String patientMobileNo;
    private String staffIncharge;
    private String appointmentDate;

    public Appointment(){}
    public Appointment(int icNo, String patientName, String patientMobileNo, String staffIncharge, String appointmentDate){
        this.icNo = icNo;
        this.patientName = patientName;
        this.patientMobileNo = patientMobileNo;
        this.staffIncharge = staffIncharge;
        this.appointmentDate = appointmentDate;
    }
    
    public int getIcNo() {
        return icNo;
    }

    public void setIcNo(int icNo) {
        this.icNo = icNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMobileNo() {
        return patientMobileNo;
    }

    public void setPatientMobileNo(String patientMobileNo) {
        this.patientMobileNo = patientMobileNo;
    }

    public String getStaffIncharge() {
        return staffIncharge;
    }

    public void setStaffIncharge(String staffIncharge) {
        this.staffIncharge = staffIncharge;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    @Override 
    public String toString(){
        String str = "";
        
        str += "IC No: " + this.getIcNo() + "\n" +
               "Patient Name: " + this.getPatientName() + "\n" +
               "Patient Mobile No: " + this.getPatientMobileNo() + "\n" +
               "Staff Incharge: " + this.getStaffIncharge()+ "\n" +
               "Appintment Date: " + this.getAppointmentDate()+ "\n";
        
        return str;
    }
}

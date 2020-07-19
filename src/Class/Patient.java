
package Class;

import java.io.Serializable;

public class Patient implements Serializable {
    private int icNo;
    private String ic;
    private String name;
    private String mobileNo;
    private String dateCreated;
    private String medicalDescription;

    public Patient(){}
    
    public Patient(int icNo, String ic, String name, String mobileNo, String dateCreated) {
        this.icNo = icNo;
        this.ic = ic;
        this.name = name;
        this.mobileNo = mobileNo;
        this.dateCreated = dateCreated;
    }
    
    public Patient(int icNo, String ic, String name, String mobileNo, String dateCreated, String medicalDescription) {
        this.icNo = icNo;
        this.ic = ic;
        this.name = name;
        this.mobileNo = mobileNo;
        this.dateCreated = dateCreated;
        this.medicalDescription = medicalDescription;
    }

    public int getIcNo() {
        return icNo;
    }

    public void setIcNo(int icNo) {
        this.icNo = icNo;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getMedicalDescription() {
        return medicalDescription;
    }

    public void setMedicalDescription(String medicalDescription) {
        this.medicalDescription = medicalDescription;
    }
    
    @Override 
    public String toString(){
        String str = "";
        
        str += "IC No: " + this.getIcNo() + "\n" +
               "IC: " + this.getIc() + "\n" +
               "Name: " + this.getName() + "\n" +
               "Mobile No: " + this.getMobileNo() + "\n" +
               "Date Created: " + this.getDateCreated() + "\n";
        
        return str;
    }
}

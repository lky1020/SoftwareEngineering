
package Class;

import java.io.Serializable;

public class Staff implements Serializable{
    private int id;
    private String name;
    private String designation;
    private String mobileNo;
    private String dateJoined;

    public Staff(){}
    public Staff(int id, String name, String designation, String mobileNo, String dateJoined){
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.mobileNo = mobileNo;
        this.dateJoined = dateJoined;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }
    
    @Override 
    public String toString(){
        String str = "";
        
        str += "ID: " + this.getId()+ "\n" +
               "Staff Name: " + this.getName()+ "\n" +
               "Designation: " + this.getDesignation()+ "\n" +
               "Mobile No: " + this.getMobileNo() + "\n" +
               "Date Joined: " + this.getDateJoined()+ "\n";
        
        return str;
    }
}

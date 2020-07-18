
package Class;

public class Patient {
    public static int initialNo = 1;
    private int no;
    private int icNo;
    private String ic;
    private String name;
    private String mobileNo;
    private String dateCreated;

    public Patient(){
        no = initialNo++;
    }
    
    public Patient(int icNo, String ic, String name, String mobileNo, String dateCreated) {
        no = initialNo++;
        this.icNo = icNo;
        this.ic = ic;
        this.name = name;
        this.mobileNo = mobileNo;
        this.dateCreated = dateCreated;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
    
    
}

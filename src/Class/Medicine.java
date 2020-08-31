
package Class;

import java.io.Serializable;

public class Medicine implements Serializable{
    private String id;
    private String medicineName;
    private int quantity;
    private double unitPrice;
    private String expiredDate;

    public Medicine(){}
    public Medicine(String id, String medicineName, int quantity, double unitPrice, String expiredDate){
        this.id = id;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.expiredDate = expiredDate;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
    
    @Override 
    public String toString(){
        String str = "";
        
        str += "ID: " + this.getId()+ "\n" +
               "Medicine Name: " + this.getMedicineName()+ "\n" +
               "Quantity: " + this.getQuantity()+ "\n" +
               "Unit Price(RM): " + String.format("%.2f", this.getUnitPrice()) + "\n" +
               "Expired Date: " + this.getExpiredDate()+ "\n";
        
        return str;
    }
}

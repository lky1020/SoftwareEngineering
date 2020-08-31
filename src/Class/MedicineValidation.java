
package Class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicineValidation {
    
    public MedicineValidation(){};
    
    public boolean validateMedicineID(String medicineID){
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(medicineID);
        
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
    
}

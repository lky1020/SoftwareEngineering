
package Class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientValidation {
    
    public PatientValidation(){};
    
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author user
 */
public class SalaryValidation {
        public SalaryValidation(){};
        
        
          public boolean validateID(String id){
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(id);
        
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
           
            public boolean validateDesignation(String designation){
         String regexName = "\\w{5}";
        String patternName = regexName;
        
        if(designation.matches(patternName)){
            
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
    
            public boolean validateSalary(String salary){
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(salary);
        
        if(matcher.matches()){
            
            return true;
            
        }else{
            
            return false;
            
        }
    }  
}

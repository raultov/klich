package net.softforlife.klich.JSF.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtil {

	
    public static void showInfoMessage(String summary, String details) {  
        FacesContext context = FacesContext.getCurrentInstance();  
          
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, details));   
    }
    
    public static void showErrorMessage(String summary, String details) {  
        FacesContext context = FacesContext.getCurrentInstance();  
          
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, details));   
    } 
    
    public static void showWarningMessage(String summary, String details) { 

       FacesContext context = FacesContext.getCurrentInstance();  
          
       context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, details));   
    }
}
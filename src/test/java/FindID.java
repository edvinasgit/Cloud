package test.java;

import java.util.List;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FindID {
	private WebDriver driver;
    public String alldevid_on = null;
    public String alldevid_off = null;
    public String alldevid = null;
    public String devname = null;

    // four constructors
    public FindID() {
    
    }
    public FindID(WebDriver drv) {
       driver = drv;
    }
    public FindID(WebDriver drv, String name) {
       driver = drv;
       devname = name;
    }

   public String getOnlineID(){
    	 driver.findElements(By.className("device_on"));
    	List<WebElement> alldevices = driver.findElements(By.className("device_on"));
    		  for (WebElement element: alldevices)
    		  {
    			  if(element.getText().equals(devname))
    			  {
    				  alldevid_on = element.getAttribute("id");
    				  String[] onlyid;
    			      onlyid = alldevid_on.split("-");
    			      alldevid_on = onlyid[1];
    			  }
    		  }
   
    	return alldevid_on;
    }
   
    public String getOfflineID(){
    	List<WebElement> alldevices = driver.findElements(By.className("device_off"));
    		  for (WebElement element: alldevices)
    		  {
    			  if(element.getText().equals(devname))
    			  {
    				  alldevid_off = element.getAttribute("id");
    				  String[] onlyid;
    			      onlyid = alldevid_off.split("-");
    			      alldevid_off = onlyid[1];
    			  }
    		  }
    	return alldevid_off;
    }
    
    
	public String getID(){
    	List<WebElement> alldevices = driver.findElements(By.className("device_on"));
    		  for (WebElement element: alldevices)
    		  {
    			  if(element.getText().equals(devname))
    			  {
    				  alldevid = element.getAttribute("id");
    				  String[] onlyid;
    			      onlyid = alldevid.split("-");
    			      alldevid_on = onlyid[1];
    			  }
    		  }
    		  if(alldevid == null)
    		  {
    			  List<WebElement> alldevices_off = driver.findElements(By.className("device_off"));
    			  for (WebElement element: alldevices_off)
        		  {
        			  if(element.getText().equals(devname))
        			  {
        				  alldevid = element.getAttribute("id");
        				  String[] onlyid;
        			      onlyid = alldevid.split("-");
        			      alldevid = onlyid[1];
        			  }  
        		  } 	  
    		  }
    	return alldevid;
    }
   
    


}

package test.java;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


//import java.util.List;






//import org.apache.commons.logging.Log;

import org.testng.Assert;
//import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.apache.log4j.xml.DOMConfigurator;


public class Login {
	private WebDriver driver;
	
	
	
	
	 //-----------Global values---------------------------
	  String baseUrl = "https://security.eldes.lt";
	  String UserName = "edvinasg@gmail.com";
	  String UserPass = "edvinaseldes";
	  String BadUserName = "edvinasg@gmail.co";
	  String BadUserPass = "edvinaseldes";
	  Logger Fatal = LogManager.getLogger("FATAL");
	  Logger Debug = LogManager.getLogger("Debug");
	  Logger Error = LogManager.getLogger("ERROR");
	  Logger Info = LogManager.getLogger("Info");

	  //---------------------------------------------------
	  
    public Login() {
        
    }
    public Login(WebDriver drv) {
       driver = drv;
    }
	 
	
	// execute() when login method is needed without log and Failure possibility
	  public void execute() throws Exception { 
		try
		{
			    driver.get(baseUrl + "/en/user/login.html");
			    driver.findElement(By.id("UserLogin_username")).clear();
			    driver.findElement(By.id("UserLogin_username")).sendKeys(UserName);
			    driver.findElement(By.id("UserLogin_password")).clear();
			    driver.findElement(By.id("UserLogin_password")).sendKeys(UserPass);
			    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
			    try
			    {
			    	String str;
			    	WebDriverWait wait = new WebDriverWait(driver, 1);
			    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
			    	str = driver.findElement(By.className("modal-body")).getText();
			    	driver.findElement(By.cssSelector("input.modalBtn.ok")).click();
			    	System.out.println("ERROR: " + str); Error.info("ERROR: " + str);
			    	//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			    	//System.out.println(System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".jpg");
			    	//takeScreenShotOnFailure(null, null);
			    }
			    catch(Exception e){
			    }

			    }
		 catch(Exception e){
		 }
		 }
	 
  public void test() throws Exception { 
	 try
	  {
		   //driver = new FirefoxDriver();
		    //baseUrl = "https://security.eldes.lt";
		   // driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    System.out.println("Vidinis Login");
	    driver.get(baseUrl + "/en/user/login.html");
	    driver.findElement(By.id("UserLogin_username")).clear();
	    driver.findElement(By.id("UserLogin_username")).sendKeys(UserName);
	    driver.findElement(By.id("UserLogin_password")).clear();
	    driver.findElement(By.id("UserLogin_password")).sendKeys(UserPass);
	    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
	    
	    

	    //System.out.println("1)testLogin OK");
	    //Thread.sleep(20000);
	    //System.out.println("Praejo 20s");
	    try
	    {
	    	String str;
	    	WebDriverWait wait = new WebDriverWait(driver, 1);
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
	    	str = driver.findElement(By.className("modal-body")).getText();
	    	driver.findElement(By.cssSelector("input.modalBtn.ok")).click();
	    	System.out.println("ERROR: " + str); Error.info("ERROR: " + str);
	    	//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			//FileUtils.copyFile(scrFile, new File("D:\\testScreenShot.jpg"));
			//takeScreenShotOnFailure(null, null);
	    }
	    catch(Exception e){
	    }
	    
	    
	  }
	 
	  catch(Exception e)
	    {
		  	Fatal.info("Login: " + e);
	    	Assert.fail();
		  	
	    }
	  
	}
}

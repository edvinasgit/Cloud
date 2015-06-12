package test.java.CloudTest;
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
	//private static WebDriver driver;
	
	
	
	
	 //-----------Global values---------------------------
	  static String baseUrl = "https://security.eldes.lt";
	  static String UserName = "edvinasg@gmail.com";
	  static String UserPass = "edvinaseldes";
	  static String BadUserName = "edvinasg@gmail.co";
	  static String BadUserPass = "edvinas";
	  
	  
	  static Logger Fatal = LogManager.getLogger("FATAL");
	  static Logger Debug = LogManager.getLogger("Debug");
	  static Logger Error = LogManager.getLogger("ERROR");
	  static Logger Info = LogManager.getLogger("Info");

	  //---------------------------------------------------
	  
    public  Login() {
        
    }

	 
	
	// execute() when login method is needed without log and Failure possibility
	  public static void execute(WebDriver driver) throws Exception { 
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
	  
  private static void badUserPassTest(WebDriver driver){
	 // try
	  //{
		  	WebDriverWait wait = new WebDriverWait(driver, 10);
		  	driver.get(baseUrl + "/en/user/login.html");
		    driver.findElement(By.id("UserLogin_username")).clear();
		    driver.findElement(By.id("UserLogin_username")).sendKeys(BadUserName);
		    driver.findElement(By.id("UserLogin_password")).clear();
		    driver.findElement(By.id("UserLogin_password")).sendKeys(UserPass);
		    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).equals("Username or email is not a valid email addres.");
	    	/*driver.findElement(By.id("UserLogin_username")).clear();
		    driver.findElement(By.id("UserLogin_username")).sendKeys(UserName);
		    driver.findElement(By.id("UserLogin_password")).clear();
		    driver.findElement(By.id("UserLogin_password")).sendKeys(BadUserPass);
		    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).equals("Password is incorrect.");
	    	*/
	  //}
	  //catch(Exception e){
	  //}
  }
  
  public static void test(WebDriver driver) throws Exception { 
	 try
	  {
		   //driver = new FirefoxDriver();
		    //baseUrl = "https://security.eldes.lt";
		   // driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    System.out.println("Vidinis Login");
		   // badUserPassTest(driver);
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

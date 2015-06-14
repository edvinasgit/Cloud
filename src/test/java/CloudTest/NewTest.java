package test.java.CloudTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
//import java.util.List;


import org.apache.commons.io.FileUtils;
//import org.apache.commons.logging.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.apache.log4j.xml.DOMConfigurator;

import test.java.CloudTest.Login;
import test.java.CloudTest.tests.Control;



public class NewTest {
  private StringBuffer verificationErrors = new StringBuffer(); 
 
  
  //-----------Global values---------------------------
  public String baseUrl = "https://security.eldes.lt";
  String devname = "Edvinas Automatic Test";
  String UserName = "edvinasg@gmail.com";
  String UserPass = "edvinaseldes";
  String IMEI = "F102CD45A924F668EEFE5FAE38805F8F";
  Logger Fatal = LogManager.getLogger("FATAL");
  Logger Debug = LogManager.getLogger("Debug");
  Logger Error = LogManager.getLogger("ERROR");
  Logger Info = LogManager.getLogger("Info");  
  public WebDriver driver;

  //---------------------------------------------------
 
  
  @BeforeTest (groups = { "Dev", "Prod" })
  public void BeforeTest() throws Exception {
	  DOMConfigurator.configure("log4j.xml");
	  Info.info("####################################################################################");
	
  }	   

  @BeforeMethod (groups = { "Dev", "Prod" })
public void setUp(Method method) throws Exception {
	

    driver = new FirefoxDriver();
    //baseUrl = "https://security.eldes.lt";
   // driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    Info.info("----------------------------------------------------------------------------------");
    String testName = method.getName(); 
    System.out.println(testName + " Started");
    Info.info(testName + " Started");
    
   
   
   }

  
  public void login() throws Exception {
	
	  Login.execute(driver);
	  
	}
  
@Test(priority=1, groups = { "Prod" })
  public void testLogin() throws Exception {

	new Login(driver, "https://security.eldes.lt", "edvinasg@gmail.com", "edvinaseldes");
	Login.test(driver);
 
}

@Test(priority=1, groups = { "Dev"})
public void testLoginDev() throws Exception {
	
	new Login(driver, "https://dev.eldes.lt", "edvinasg@gmail.com", "nissan88");
	Login.test(driver);

}
 @Test(priority=3, groups = { "Dev", "Prod" })
 public void testAddDevice() throws Exception {
	 try
	 {
		 
	  		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	 	String str = null;
	 	if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
	 		login();
	 
	    driver.get(baseUrl + "/device/create");
	    driver.findElement(By.id("Device_crypted_imei")).clear();
	    driver.findElement(By.id("Device_crypted_imei")).sendKeys(IMEI);
	    System.out.println("Naujasis IMEI");
		driver.findElement(By.id("DeviceProfile_name")).clear();
		driver.findElement(By.id("DeviceProfile_name")).sendKeys(devname);
		driver.findElement(By.id("DeviceProfile_phone")).clear();
		driver.findElement(By.id("DeviceProfile_phone")).sendKeys("+37068771762");
		Thread.sleep(5000);      
		//WebDriverWait waitpromt = new WebDriverWait(driver, 15);
    	//waitpromt.until(ExpectedConditions.visibilityOfElementLocated(By.id("Device_crypted_imei_em_")));
	    if(!driver.findElement(By.id("Device_crypted_imei_em_")).isDisplayed())
	    {
	    	 System.out.println("Adding device");
		    driver.findElement(By.name("addDevice")).click();
		   
	    
		    WebDriverWait wait = new WebDriverWait(driver, 600);
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
	    	str = driver.findElement(By.className("modal-body")).getText();
	    	//System.out.println("ERROR: " + str);
	    	
		    driver.findElement(By.name("pin")).clear();
		    driver.findElement(By.name("pin")).sendKeys("1111");
		    driver.findElement(By.id("pinSubmit")).click();
		    Info.info("AddDevice Passed");
		    System.out.println("Pin Code entered");
	    	
	    }      
	    else
	    {
	    	str = driver.findElement(By.id("Device_crypted_imei_em_")).getText();
	    	Error.info("ERROR: " + str);
	    	Info.info("Device will be deleted automaticaly and test will be repeated");
	    	System.out.println("ERROR: " + str);
	    	System.out.println("Device will be deleted automaticaly and test will be repeated");
	    	driver.findElement(By.xpath("//*[@id='device-form']/div[4]/div[2]/button[1]")).click();
	    	testDeleteDevice();
	    	testAddDevice();
	    }
	 }
	 catch(Exception e)
     {
	  	Fatal.info("AddDevice: " + e);
	  	Assert.fail();
	  	
     }
	    
}

 @Test (priority=2, groups = { "Dev", "Prod" })
 public void testDeleteDevice() throws Exception {
	try
	{
	 String alldevid_on = null;
	 String alldevid_off = null;
	 	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
	 	{
	 		login();
	 	}

		   //FindID myID = new FindID(driver, devname);
		   
	 		FindID.driver = driver;
		   FindID.devname = devname;
		   
		   alldevid_on = FindID.getOnlineID();
		   alldevid_off = FindID.getOfflineID();
		
		   System.out.println("On " + alldevid_on);
		   System.out.println("Off " + alldevid_off);
		   System.out.println("Pries Unknown");
		   //Thread.sleep(5000); 
		  /* if (driver.findElement(By.cssSelector("div.col-md-1.tab_unknown ")).isDisplayed())
		   {
			   System.out.println("Unknown viduje");
			   driver.findElement(By.cssSelector("div.col-md-1.tab_unknown ")).click();
			   alldevid_on = myID.getOnlineID();
			   alldevid_off = myID.getOfflineID();
			   Error.info("Device is unknown and will be deleted");
			   System.out.println("Device is unknown and will be deleted");

			   
			   driver.findElement(By.xpath("//a[@href='#edit-"+alldevid_off+"']")).click();
			   driver.findElement(By.partialLinkText("DELETE")).click();
			   Alert alert = driver.switchTo().alert();
			   if (alert.getText().equals("Are you sure you want to delete " +  devname + "? EXPIRY TIME WILL BE LOST!"))
				   alert.accept();
			   else
			   {
				   System.out.println("ERROR: " + alert.getText());
				   Error.info("ERROR: " + alert.getText());
				   Assert.fail();
			   }
			   driver.findElement(By.cssSelector("div.col-md-1.tab_active_ss ")).click();
		   }*/
		   System.out.println("po Unknown");
		   System.out.println("On " + alldevid_on);
		   System.out.println("Off " + alldevid_off);
		   if (alldevid_off==null && alldevid_on ==null)
			  {
				Error.info("ERROR: You can't delete device which doesn't exixts. Device ID not found");
				System.out.println("ERROR: You can't delete device which doesn't exixts. Device ID not found");
				Assert.fail();
			  }
		   
		   if (alldevid_off!=null)
			  {  
			   	  System.out.println("Your Device is offline. This test expected online device");
				  Error.info("Your device is offline. This test expected online device");
				  Assert.fail();
			  }
		 
		   System.out.println("//a[@href='#edit-"+alldevid_on+"']");
		   
		   driver.findElement(By.xpath("//a[@href='#edit-"+alldevid_on+"']")).click();
		   driver.findElement(By.partialLinkText("DELETE")).click();
		   Alert alert = driver.switchTo().alert();
		   if (alert.getText().equals("Are you sure you want to delete " +  devname + "? EXPIRY TIME WILL BE LOST!"))
			   alert.accept();
		   else
		   {
			   System.out.println("ERROR: " + alert.getText());
			   Error.info("ERROR: " + alert.getText());
			   Assert.fail();
		   }
			 
	 }
	catch(Exception e)
	{
		Fatal.info("DeviceDelete: " + e);
    	Assert.fail();
	}
}

 @Test (priority=4, groups = { "Dev", "Prod" })
 public void testLogout() throws Exception {
	try
	{
		if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
			login();
	    driver.findElement(By.cssSelector("span > span > span")).click();
	    driver.findElement(By.xpath("//li[4]/a/span[2]")).click();
	 }
	
	 catch(Exception e)
     {
	  	Fatal.info("Logout: " + e);
    	Assert.fail();
     }
}
 
 @Test (priority=5, groups = { "Dev", "Prod" })
 public void testControl() throws Exception {
	 Thread.sleep(270000); 
	 new Control(driver);
	 Control.execute();  
	  
} 
     
   
   
// @Test (priority = 6)
 /*public void testRegister() throws Exception {
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	try
	{
		 //String a,b;
		//if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
			//login();
		
			driver.get(baseUrl + "/en/user/login.html");
		    driver.findElement(By.id("yt0")).click();
		    driver.findElement(By.id("UserRegistrationForm_username")).clear();
		    driver.findElement(By.id("UserRegistrationForm_username")).sendKeys("pedroandiro@gmail.com");
		    driver.findElement(By.id("UserRegistrationForm_password")).clear();
		    driver.findElement(By.id("UserRegistrationForm_password")).sendKeys("pedroandiro");
		    driver.findElement(By.id("UserRegistrationForm_verifyPassword")).clear();
		    driver.findElement(By.id("UserRegistrationForm_verifyPassword")).sendKeys("pedroandiro");
		    driver.findElement(By.id("UserRegistrationForm_agreeTerms")).click();
		   // driver.findElement(By.className("btn btn-success")).click();
		    driver.findElement(By.cssSelector("div.row-fluid.buttons > button.btn.btn-success")).click();
		    System.out.println("Viskas pavyko");
			
		    driver.get("http://delfi.lt");
		    //driver.findElement(By.id("gmail-sign-in")).click();
		    driver.findElement(By.id("account-chooser-add-account")).click();
		    driver.findElement(By.id("Email")).clear();
		    driver.findElement(By.id("Email")).sendKeys("pedroandiro");
		    driver.findElement(By.id("Passwd")).clear();
		    driver.findElement(By.id("Passwd")).sendKeys("edvinaseldes");
		   // driver.findElement(By.id("PersistentCookie")).click();
		    driver.findElement(By.id("signIn")).click();
		   // driver.findElement(By.id("cancel")).click();
		    driver.findElement(By.xpath("(//span[@name='cloud'])[2]")).click();
		    driver.findElement(By.linkText("https://security.eldes.lt/en/user/activation/activation/activkey/4b1593865f37735debe580b4f4051cad/email/pedroandiro%40gmail.com.html")).click();

		    
		    
			/*FindID myID = new FindID(driver, devname);
			System.out.println(myID.getID());
			
			driver.findElement(By.xpath("//a[@href='#control-"+myID.getOnlineID()+"']")).click();
			System.out.println("Find state: "+driver.findElement(By.xpath("//*[@id='controlsPanel-3606']/div/div/div[3]/div[1]/div/div/p")).getText());
			
			driver.findElement(By.xpath("//*[@id='controlsPanel-"+myID.getOnlineID()+"']/div/div/div[3]/div[2]/div/div/div[1]")).click();
			System.out.println("Paklikino");
			WebElement arm =driver.findElement(By.className("arm_disarm"));
			arm.findElement(By.className("armingBtnHolder disarm ng-scope"));
			System.out.println("Armed");
			//driver.findElement(By.className("arm_disarm"));
			System.out.println("Finded state: "+driver.findElement(By.xpath("//*[@id='controlsPanel-3606']/div/div/div[3]/div[1]/div/div/p")).getText());
			
	 }
	
	 catch(Exception e)
     {
	  	Fatal.info("Logout: " + e);
    	Assert.fail();
     }
} */
 

 
 @AfterMethod (groups = { "Dev", "Prod" })
 public void takeScreenShotOnFailure(ITestResult testResult, Method method) throws IOException 
 { 
	 String testName = method.getName(); 
 	  if (testResult.getStatus() == ITestResult.FAILURE) 
 	  {  
 		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
 		  FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".jpg"));
 		  System.out.println(System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".jpg");
 		  System.out.println(testName + " FAILED");
 		  Info.info(testName + " FAILED");
 		  
 	  }
 	  if (testResult.getStatus() == ITestResult.SUCCESS) 
 	  { 
 		  System.out.println(testName + " PASSED");
 		  Info.info(testName + " PASSED");
 		  
 	  }
 	  if (testResult.getStatus() == ITestResult.SKIP) 
 	  { 
 		  System.out.println(testName + " SKIPED");
 		  Info.info(testName + " SKIPED");
 		    
 	  }
 	        
 }
 
@AfterMethod (groups = { "Dev", "Prod" })
public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }

    
  }

}



package Suites;
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

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.apache.log4j.xml.DOMConfigurator;

import Tests.AddDevice;
import Tests.DeleteDevice;
import Tests.Login;
//import Tests.Control;
import Tests.Control2;
import Tests.Logout;
import Utility.FindID;

public class IntegrationTest_1 {
	private StringBuffer verificationErrors = new StringBuffer(); 


	//-----------Global values--------------------------------------------------------------------
	public String baseUrl = "https://security.eldes.lt";
	String devname = "Edvinas Automatic Test";
	String UserName = "pedroandiro@gmail.com";
	String UserPass = "pedroandiro";
	//String IMEI = "F102CD45A924F668EEFE5FAE38805F8F"; OLD
	String IMEI = "8D02 ECD2 898C AA1E 69A9 F3B4 9B44 488D";
	Logger Fatal = LogManager.getLogger("FATAL");
	Logger Debug = LogManager.getLogger("Debug");
	Logger Error = LogManager.getLogger("ERROR");
	Logger Info = LogManager.getLogger("Info");  
	public WebDriver driver;

	//----------------------------------------------------------------------------------------------

	@BeforeTest (alwaysRun = true)
	public void BeforeTest() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		Info.info("####################################################################################");
		Info.info("\t\t\t\t\t\tStatus");

	}	   

	@BeforeMethod ( groups = { "Prod" })
	public void setUpProd(Method method) throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://security.eldes.lt";
		driver.manage().window().maximize();
		Info.info("----------------------------------------------------------------------------------");
		String testName = method.getName(); 
		System.out.println(testName + " Started");
		//Info.info(testName + " Started");
	}

	@BeforeMethod ( groups = { "Dev" })
	public void setUpDev(Method method) throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://dev.eldes.lt";
		driver.manage().window().maximize();
		Info.info("----------------------------------------------------------------------------------");
		String testName = method.getName(); 
		System.out.println(testName + " Started");
		//Info.info(testName + " Started");
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://security.eldes.lt";
		driver.manage().window().maximize();
		new Login(driver, baseUrl, "pedroandiro@gmail.com", "pedroandiro");
		System.out.println("setUp");
		Info.info("----------------------------------------------------------------------------------");
		String testName = method.getName(); 
		System.out.println(testName + " Started");
		//Info.info(testName + " Started");
	}

	
	
	//Login-------------------------------------------------------------------------------------------------

	public void login() throws Exception {

		Login.execute(driver);

	}

	@Test(priority=1, groups = { "Prod" })
	public void testLogin() throws Exception {
		new Login(driver, baseUrl, "pedroandiro@gmail.com", "edvinaseldes");
		Login.test(driver);

	}

	@Test(priority=1, groups = { "Dev"})
	public void testLoginDev() throws Exception {

		new Login(driver, baseUrl, "edvinasg@gmail.com", "nissan88");
		Login.test(driver);

	}

	//AddDevice-------------------------------------------------------------------------------	
	@Test(priority=4, groups = { "Dev", "Prod" })
	public void testAddDevice() throws Exception {
		AddDevice.execute(driver);

	}

	//DeleteDevice-------------------------------------------------------------------------------

	@Test (priority=3, groups = { "Dev", "Prod" })
	public void testDeleteDevice() throws Exception {
		DeleteDevice.execute(driver);

	}

	//LOGOUT--------------------------------------------------------------------------------------
	@Test (priority=5, groups = { "Dev", "Prod" })
	public void testLogout() throws Exception {
		Logout.execute(driver);
	}

	//Control---------------------------------------------------------------------------------------
	@Test (priority=2, groups = { "Dev", "Prod" })
	public void testControl() throws Exception {
		//Thread.sleep(300000); 
		new Control2(driver);
		Control2.execute();  

	} 

	//-------------------------------------------------------------------------------------------------------------

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


	//------------------------------------------------------------------------------------------------------------
	@AfterMethod (alwaysRun = true)
	public void takeScreenShotOnFailure(ITestResult testResult, Method method) throws IOException 
	{ 
		String testName = method.getName(); 
		if (testResult.getStatus() == ITestResult.FAILURE) 
		{  
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".jpg"));
			System.out.println(System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".jpg");
			System.out.println(testName + " FAILED");
			Info.info(testName + "\t\t\t\tFAILED");

		}
		if (testResult.getStatus() == ITestResult.SUCCESS) 
		{ 
			System.out.println(testName + " PASSED");
			Info.info(testName + "\t\t\t\tPASSED");

		}
		if (testResult.getStatus() == ITestResult.SKIP) 
		{ 
			System.out.println(testName + " SKIPED");
			Info.info(testName + " SKIPED");

		}

	}

	@AfterMethod (alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}


	}

}



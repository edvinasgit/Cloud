package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Tests.Login;
import Utility.ExpectedObject;
import Utility.FindID;

public class Control2 {
	public static WebDriver driver;



	public Control2(WebDriver drv){
		driver =drv;
		FindID.driver = driver;
		FindID.devname = devname;
	}

	//-----------Global values---------------------------
	//private String baseUrl = "https://security.eldes.lt";
	static String devname = "Edvinas Automatic Test";
	static Logger Fatal = LogManager.getLogger("FATAL");
	static Logger Debug = LogManager.getLogger("Debug");
	static Logger Error = LogManager.getLogger("ERROR");
	static Logger Info = LogManager.getLogger("Info");
	static String myID;


	//------------------------------------------------------------------------------------

	public static void execute() throws Exception {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try
		{

			if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
				Login.execute(driver);
			myID = FindID.getOnlineID();
			By controller = By.xpath("//a[@href='#control-"+myID+"']");
			driver.findElement(controller).click();
			System.out.println("	Arm Started");
			armDisarm();
			Thread.sleep(1000);
			System.out.println("	Disarm Started");
			armDisarm();
			Thread.sleep(3000);
			System.out.println("	OutputControl ON Started");
			outputControl();
			Thread.sleep(2000);
			System.out.println("	OutputControl OFF Started");
			outputControl();
		}
		catch(Exception e)
		{
			//Fatal.info("Logout: " + e);
			System.out.println("Fatal  Control: " + e);
			Assert.fail();
		}
	}
	
	//BYPASS------------------------------------------------------------------------------------
	private static void bypass() throws Exception {
		try
		{
			//Info.info("	Bypass Started");
			System.out.println("	Bypass Started");
			By selectAllBypass = By.xpath("html/body/div[2]/div/div/form/div[1]/div");
			By okBypass = By.xpath("//input[@value='Ok']");

			driver.findElement(selectAllBypass).click();
			driver.findElement(okBypass).click();
			Info.info("Bypass\t\t\t\tPassed");
			System.out.println("	Bypass PASSED");
		}
		catch(Exception e)
		{
			//Fatal.info("	Bypass: " + e);
			System.out.println("Fatal  Bypass: " + e);
			Assert.fail();
		}
	}
	
	//ARM-DISARM------------------------------------------------------------------------------------
	private static boolean armDisarm() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try
		{
			String textA ="Arm";
			String textB = "Disarm";
			String expectedText;
			By bypass = By.className("modal-dialog");
			By arm_disarm = By.xpath("//*[@id='controlsPanel-"+myID+"']/div/div/div[3]/div[2]/div/div/div[1]");
			String state = driver.findElement(arm_disarm).getText();
			if(state.equals(textA))
				expectedText = textB;
			else expectedText =textA;
			driver.findElement(arm_disarm).click();
			boolean status = compare(arm_disarm, expectedText);
			
			try
			{
				if(driver.findElement(bypass).isDisplayed())
				{
					bypass();
					armDisarm();
					return false;
				}
			}
			catch(Exception e){}
			if(status == true)
			{
				Info.info("Arm/Disarm\t\t\t\tPASSED");
				System.out.println("	Arm/Disarm PASSED");
				return true;
			}
			else 
			{
				Assert.fail();
				return false;
			}

		}
		catch(Exception e)
		{
			//Fatal.info("Arm/Disarm: " + e);
			System.out.println("Fatal  Arm/Disarm: " + e);
			Assert.fail();
			return true;
		}
	}
	
	//OUTPUT-CONTROL------------------------------------------------------------------------------------
	private static boolean outputControl() throws Exception {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try
		{
			String textA ="on";
			String textB = "off";
			String expectedText;
			By on_off = By.xpath("//*[@id='controlsPanel-"+myID+"']/div/div[1]/div[2]/div[1]/div");
			String state = driver.findElement(on_off).getText();
			if(state.equals(textA))
				expectedText = textB;
			else expectedText =textA;
			driver.findElement(on_off).click();
			boolean status = compare(on_off, expectedText);
			if(status == true)
			{
				Info.info("Control ON/OFF\t\t\t\tPASSED");
				System.out.println("	Control ON/OFF PASSED");
				return true;
			}
			else 
			{
				Assert.fail();
				return false;
			}

		}
		catch(Exception e)
		{
			//Error.info("OutputControl: " + e); 
			System.out.println("Fatal  OutputControl: " + e);
			return false;
		}

	}
	
	//COPMARE------------------------------------------------------------------------------------
	private static boolean compare(By element, String expectedText) throws Exception {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(element, expectedText));
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Fatal  OutputControl: " + e);
			return false;
		}
	}

}

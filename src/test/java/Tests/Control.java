package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Tests.Login;
import Utility.ExpectedObject;
import Utility.FindID;

public class Control {
	public static WebDriver driver;



	public Control(WebDriver drv){
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


	//---------------------------------------------------

	public static void execute() throws Exception {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try
		{

			if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
				Login.execute(driver);
			myID = FindID.getOnlineID();
			By controller = By.xpath("//a[@href='#control-"+myID+"']");
			//System.out.println("klikinam");
			driver.findElement(controller).click();
			//Info.info("	Arm Started");
			System.out.println("	Arm Started");
			if(armDisarm()==false) //need bypass
			{	

				//System.out.println("bypassinam");
				bypass();
				Thread.sleep(1000);
				//System.out.println("arminam");
				armDisarm();
			}
			Thread.sleep(1000);
			//Info.info("	Disarm Started");
			System.out.println("	Disarm Started");
			armDisarm();

			Thread.sleep(2000);
			//Info.info("	OutputControl ON Started");
			System.out.println("	OutputControl ON Started");
			if (outputControl()==false)//ijungia outputa ir jei nepavyksta kartoja dar karta
			{
				System.out.println("Second try");
				if (outputControl()==false)
					Assert.fail();
			}

			Thread.sleep(1000);
			// Info.info("	OutputControl OFF Started");
			System.out.println("	OutputControl OFF Started");
			if (outputControl()==false)//isjungia outputa ir jei nepavyksta kartoja dar karta
				if (outputControl()==false)
					Assert.fail();

			System.out.println("5");
		}

		catch(Exception e)
		{
			//Fatal.info("Logout: " + e);
			System.out.println("Fatal  Control: " + e);
			Assert.fail();
		}
	}

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

	private static boolean armDisarm() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try
		{

			By bypass = By.className("modal-dialog");
			By arm_disarm = By.xpath("//*[@id='controlsPanel-"+myID+"']/div/div/div[3]/div[2]/div/div/div[1]");

			//System.out.println("Start Arminam");
			ExpectedObject EO = new ExpectedObject(arm_disarm,bypass,"Arm","Disarm");
			EO.apply(driver);
			//System.out.println("Arminam End");

			if (EO.status==2)
			{
				System.out.println("return false");
				return false;
			}
			else
			{
				Info.info("Arm/Disarm\t\t\t\tPASSED");
				System.out.println(" Arm/Disarm PASSED");
				return true;
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

	private static boolean outputControl() throws Exception {
		driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		try
		{

			By on_off = By.xpath("//*[@id='controlsPanel-"+myID+"']/div/div[1]/div[2]/div[1]/div");

			ExpectedObject EO = new ExpectedObject(on_off,null,"on","off" );
			EO.apply(driver);
			Info.info("OutputControl\t\t\t\tPASSED");
			System.out.println(" OutputControl PASSED");
			return true;
		}
		catch(Exception e)
		{
			//Error.info("OutputControl: " + e); 
			System.out.println("Fatal  OutputControl: " + e);
			return false;
		}

	}

}

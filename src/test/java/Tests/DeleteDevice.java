package Tests;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Suites.IntegrationTest_1;
import Tests.Login;
import Utility.FindID;



public class DeleteDevice {

	//-----------Global values---------------------------
		//public static String baseUrl = "https://security.eldes.lt";
		public static String devname = "Edvinas Automatic Test";
		//public static String UserName = "edvinasg@gmail.com";
		//public static String UserPass = "edvinaseldes";
		static String IMEI = "F102CD45A924F668EEFE5FAE38805F8F";

		static Logger Fatal = LogManager.getLogger("FATAL");
		static Logger Debug = LogManager.getLogger("Debug");
		static Logger Error = LogManager.getLogger("ERROR");
		static Logger Info = LogManager.getLogger("Info");
		static WebDriver driver;
	  //---------------------------------------------------
	 public static void execute(WebDriver driver) throws Exception { 
		 try
		 {
				String alldevid_on = null;
				String alldevid_off = null;
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
				{
					Login.execute(driver);
				}

				FindID.driver = driver;
				FindID.devname = devname;
				alldevid_on = FindID.getOnlineID();
				alldevid_off = FindID.getOfflineID();
				System.out.println("On " + alldevid_on);
				System.out.println("Off " + alldevid_off);
				
				if (alldevid_off==null && alldevid_on ==null)
				{
					//Error.info("ERROR: You can't delete device which doesn't exixts. Device ID not found");
					System.out.println("ERROR: You can't delete device which doesn't exixts. Device ID not found");
					Assert.fail();
				}

				if (alldevid_off!=null)
				{  
					System.out.println("Your Device is offline. This test expected online device");
					//Error.info("Your device is offline. This test expected online device");
					Assert.fail();
				}

				driver.findElement(By.xpath("//a[@href='#edit-"+alldevid_on+"']")).click();
				driver.findElement(By.partialLinkText("DELETE")).click();
				Alert alert = driver.switchTo().alert();
				if (alert.getText().equals("Are you sure you want to delete " +  devname + "? EXPIRY TIME WILL BE LOST!"))
					alert.accept();
				else
				{
					System.out.println("ERROR: " + alert.getText());
					//Error.info("ERROR: " + alert.getText());
					Assert.fail();
				}

			}
			catch(Exception e)
			{
				//Fatal.info("DeviceDelete: " + e);
				System.out.println("DeviceDelete: " + e);
				Assert.fail();
			}
		}
}
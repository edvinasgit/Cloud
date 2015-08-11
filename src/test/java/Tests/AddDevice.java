package Tests;
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



public class AddDevice {

	//-----------Global values---------------------------
	public static String baseUrl = "https://security.eldes.lt";
	public static String devname = "Edvinas Automatic Test";
	//public static String UserName = "edvinasg@gmail.com";
	//public static String UserPass = "edvinaseldes";
	static String IMEI = "8D02ECD2898CAA1E69A9F3B49B44488D";

	static Logger Fatal = LogManager.getLogger("FATAL");
	static Logger Debug = LogManager.getLogger("Debug");
	static Logger Error = LogManager.getLogger("ERROR");
	static Logger Info = LogManager.getLogger("Info");
	static WebDriver driver;
	//---------------------------------------------------
	public AddDevice() {
		
	}
	
	public static void execute(WebDriver driver) throws Exception { 
		try
		{

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			String str = null;
			if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
				Login.execute(driver);

			driver.get(baseUrl + "/device/create");
			driver.findElement(By.id("Device_crypted_imei")).clear();
			driver.findElement(By.id("Device_crypted_imei")).sendKeys(IMEI);
			driver.findElement(By.id("DeviceProfile_name")).clear();
			driver.findElement(By.id("DeviceProfile_name")).sendKeys(devname);
			driver.findElement(By.id("DeviceProfile_phone")).clear();
			driver.findElement(By.id("DeviceProfile_phone")).sendKeys("+37068771762");
			Thread.sleep(5000);      
			if(!driver.findElement(By.id("Device_crypted_imei_em_")).isDisplayed())
			{
				driver.findElement(By.name("addDevice")).click();


				WebDriverWait wait = new WebDriverWait(driver, 600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
				//str = driver.findElement(By.className("modal-body")).getText();
				//System.out.println("ERROR: " + str);

				driver.findElement(By.name("pin")).clear();
				driver.findElement(By.name("pin")).sendKeys("1111");
				driver.findElement(By.id("pinSubmit")).click();
				Info.info("Pin Code Passed");
				System.out.println("Pin Code Passed");
				WebDriverWait wait2 = new WebDriverWait(driver, 180);
				wait2.until(ExpectedConditions.visibilityOfElementLocated((By.className("device_on"))));
				//Info.info("AddDevice Passed");
				//System.out.println("AddDevice Passed");

			}      
			else
			{
				str = driver.findElement(By.id("Device_crypted_imei_em_")).getText();
				//Error.info("ERROR: " + str);
				//Info.info("Device will be deleted automaticaly and test will be repeated");
				System.out.println("ERROR: " + str);
				System.out.println("Device will be deleted automaticaly and test will be repeated");
				driver.findElement(By.xpath("//*[@id='device-form']/div[4]/div[2]/button[1]")).click();
				DeleteDevice.execute(driver);
				AddDevice.execute(driver);
			}
		}
		catch(Exception e)
		{
			//Fatal.info("AddDevice: " + e);
			System.out.println("Fatal  AddDevice: " + e);
			Assert.fail();

		}

	}
}
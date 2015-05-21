package tests;
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
import test.java.NewTest;
//import java.util.List;




import org.apache.commons.io.FileUtils;
//import org.apache.commons.logging.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.apache.log4j.xml.DOMConfigurator;





public class Control {

	  //-----------Global values---------------------------
	  private String baseUrl = "https://security.eldes.lt";
	  String devname = "Edvinas Automatic Test";
	  String UserName = "edvinasg@gmail.com";
	  String UserPass = "edvinaseldes";
	  String IMEI = "F102CD45A924F668EEFE5FAE38805F8F";
	  Logger Fatal = LogManager.getLogger("FATAL");
	  Logger Debug = LogManager.getLogger("Debug");
	  Logger Error = LogManager.getLogger("ERROR");
	  Logger Info = LogManager.getLogger("Info");
	  private WebDriver driver;
	  //---------------------------------------------------
	 public void execute() throws Exception {
		 try
		 {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 	String str = null;
		 	if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )

		 
		    driver.get(baseUrl + "/device/create");
		    driver.findElement(By.id("Device_crypted_imei")).clear();
		    driver.findElement(By.id("Device_crypted_imei")).sendKeys(IMEI);

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
		    	//testDeleteDevice();
		    	//testAddDevice();
		    }
		 }
		 catch(Exception e)
	     {
		  	Fatal.info("AddDevice: " + e);
		  	Assert.fail();
		  	
	     }
		    
	}
}

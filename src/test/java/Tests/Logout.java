package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

public class Logout {

	//-----------Global values---------------------------
	//public static WebDriver driver;

	static Logger Fatal = LogManager.getLogger("FATAL");
	static Logger Debug = LogManager.getLogger("Debug");
	static Logger Error = LogManager.getLogger("ERROR");
	static Logger Info = LogManager.getLogger("Info");


	//LOGOUT------------------------------------------------------------
	public static void execute(WebDriver driver) throws Exception { 
		try
		{
			if (driver.getTitle().equals("Cloud Security Login")|| driver.getTitle().equals("") )
				Login.execute(driver);
			driver.findElement(By.cssSelector("span > span > span")).click();
			driver.findElement(By.xpath("//li[4]/a/span[2]")).click();
		}

		catch(Exception e)
		{
			//Fatal.info("Logout: " + e);
			System.out.println("Fatal  Logout: " + e);
			Assert.fail();
		}
	}
}
package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

public class Login {

	//-----------Global values---------------------------
	public static String baseUrl; 
	public static String UserName;
	public static String UserPass;
	public static String BadUserName;
	public static String BadUserPass;
	public static WebDriver driver;


	static Logger Fatal = LogManager.getLogger("FATAL");
	static Logger Debug = LogManager.getLogger("Debug");
	static Logger Error = LogManager.getLogger("ERROR");
	static Logger Info = LogManager.getLogger("Info");


	//---------------------------------------------------

	public Login(WebDriver driver1, String baseUrl1, String UserName1, String UserPass1) {
		baseUrl = baseUrl1;
		UserName = UserName1;
		UserPass = UserPass1;
		driver = driver1;

	}
	//LOGIN------------------------------------------------------------
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

			}
			catch(Exception e){
			}

		}
		catch(Exception e){
		}
	}
	//---------------------------------------------------------------------------

	private static void badUserPassTest(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(baseUrl + "/en/user/login.html");
		driver.findElement(By.id("UserLogin_username")).clear();
		driver.findElement(By.id("UserLogin_username")).sendKeys(BadUserName);
		driver.findElement(By.id("UserLogin_password")).clear();
		driver.findElement(By.id("UserLogin_password")).sendKeys(UserPass);
		driver.findElement(By.cssSelector("button.btn.btn-success")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))).equals("Username or email is not a valid email addres.");

	}
	//--------------------------------------------------------------------------------

	public static void test(WebDriver driver) throws Exception { 
		try
		{
			driver.manage().window().maximize();
			driver.get(baseUrl + "/en/user/login.html");
			driver.findElement(By.id("UserLogin_username")).clear();
			driver.findElement(By.id("UserLogin_username")).sendKeys(UserName);
			driver.findElement(By.id("UserLogin_password")).clear();
			driver.findElement(By.id("UserLogin_password")).sendKeys(UserPass);
			driver.findElement(By.cssSelector("button.btn.btn-success")).click();
			WebDriverWait wait2 = new WebDriverWait(driver, 5);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("container")));
			try
			{
				String str;
				WebDriverWait wait = new WebDriverWait(driver, 1);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
				str = driver.findElement(By.className("modal-body")).getText();
				driver.findElement(By.cssSelector("input.modalBtn.ok")).click();
				System.out.println("ERROR: " + str); //Error.info("ERROR: " + str);

			}
			catch(Exception e){
			}


		}

		catch(Exception e)
		{
			//Fatal.info("Login: " + e);
			System.out.println("Fatal  Login: " + e);
			Assert.fail();

		}

	}
}

package test.java.CloudTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ExpectedObject implements ExpectedCondition<Boolean> {
private By element1, element2;
String state, textA, textB;

public int status;

	public ExpectedObject() {
		this(null, null, null, null);
	}
	public ExpectedObject(By element2, String textA, String textB) {
		this.element2 = element2;
		this.textA = textA;
		this.textB = textB;
	}
	public ExpectedObject(By element1, By element2, String textA, String textB) {
		this.element1 = element1;
		this.element2 = element2;
		this.textA = textA;
		this.textB = textB;

	}
	
	
	public Boolean apply(WebDriver input) {
		state = input.findElement(element1).getText();
		MyExpectedCondition mec = new MyExpectedCondition(element1,element2,state,textA,textB);
		WebDriverWait wait = new WebDriverWait(input, 120);
		input.findElement(element1).click();
		System.out.println("klikinam arm");
		
		wait.until(mec);
		System.out.println("Waitinam mec");
		status = mec.status;
	return true;
		
	}
	
}

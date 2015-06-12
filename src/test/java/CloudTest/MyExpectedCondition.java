package test.java.CloudTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

public class MyExpectedCondition implements ExpectedCondition<Boolean> {
private By element1, element2;
String state, textA, textB;

public int status;

	public MyExpectedCondition() {
		this(null, null, null, null,null);
	}
	public MyExpectedCondition(By element2, String state, String textA, String textB) {
		this.element2 = element2;
		this.textA = textA;
		this.textB = textB;
		this.state = state;
	}
	public MyExpectedCondition(By element1, By element2,String state, String textA, String textB) {
		this.element1 = element1;
		this.element2 = element2;
		this.textA = textA;
		this.textB = textB;
		this.state = state;
	}
	
	
	public Boolean apply(WebDriver input) {
		System.out.println("State is: " + state);
		
		String expectedText;
		System.out.println("Expected Condition Started");
		//state = input.findElement(this.element2).getText();
		System.out.println("Action state: " + input.findElement(this.element1).getText());
		//input.findElement(this.element2).click();
		System.out.println("Action is going");
		if(state.equals(textA))
			expectedText = textB;
		else expectedText =textA;
		System.out.println("Expected: " + expectedText );
		System.out.println("Get text"+input.findElement(this.element1).getText());
		try
		{
		if (input.findElement(this.element1).getText().equals(expectedText))
		{
			status = 1;
			System.out.println("Action Finished");
			return true;
		}
	
		else if(input.findElement(this.element2).isDisplayed()){
					status = 2;
					System.out.println("Mes ife 2");
					return true;
			
		}
		else {
			status = 0;
			System.out.println("nesulaukem nei to nei to");
			return false;	
		}
		}
		catch(Exception e)
	     {
			return false;	
	     }
		 
		
		}
	
}

package test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class MyExpectedCondition implements ExpectedCondition<Boolean> {
private By element1, element2;
String state;

public int status;

	public MyExpectedCondition() {
		this(null, null, null);
	}
	public MyExpectedCondition(By element1) {
		this(element1, null, null);
	}
	public MyExpectedCondition(By element1, By element2, String state) {
		this.element1 = element1;
		this.element2 = element2;
		this.state = state;
	}
	
	
	public Boolean apply(WebDriver input) {
		String expectedText;
		System.out.println("Neiejom i Ifa");
		if(state.equals("Arm"))
			expectedText = "Disarm";
		else expectedText ="Arm";
		
		if (input.findElement(this.element2).getText().equals(expectedText))
		{
			status = 1;
			System.out.println("Mes ife 1");
			return true;
		}
		
		else if (input.findElement(this.element1).isDisplayed()){
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
	
}

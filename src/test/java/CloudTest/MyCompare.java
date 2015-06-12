package test.java.CloudTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class MyCompare{
//private WebDriver driver;
private By element1, element2, element3;
private String mystate;
WebDriver input;
	public MyCompare() {
    
	}
	public MyCompare(By ele1) {
		element1 = ele1;
	}
	public MyCompare(By ele1, By ele2) {
		element1 = ele1;
		element2 = ele2;
	}
	
	public MyCompare(By ele1, By ele2, String state,WebDriver driver) {
		element1 = ele1;
		element2 = ele2;
		mystate = state;
		input = driver;
	}
	
	public boolean apply() {
		String str1, str2, str;
		Boolean element2text = false;
		System.out.println("compare");
		//if(mystate == "Arm")
		//	mystate ="Disarm";
		//else if (mystate == "Disarm")
			//mystate = "Arm";
		str = input.findElement(this.element2).getText();
		System.out.println("GT:"+str+";");
		System.out.println("MY:"+mystate+";");
		str1 = "Arm";
		str2 = "Arm";
		//System.out.println("Matau: "+input.findElement(this.element2).getText());
		if (str1 == "Disarm")
		{
			System.out.println("Ife:");
			element2text = true;
		}
		System.out.println("Neiejom i Ifa");
		return input.findElement(this.element1).isDisplayed() ||
		element2text;
		}
	
}

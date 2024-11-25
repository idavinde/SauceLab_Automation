package Pages;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Page {

	WebDriver driver;

	Base_Page(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void wait(WebElement e) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOf(e));

	}

	public void enterText(WebElement e, String s) {
		wait(e);
		e.sendKeys(s);

	}

	public void clickList(List<WebElement> e) {
		
		System.out.println(e);
		for (WebElement c : e) {
			
			wait(c);
			c.click();
		}
	}

	public void click(WebElement e) {
		wait(e);
		e.click();
	}

	public String getText(WebElement e) {

		wait(e);
		return e.getText();
	}

	public void textClear(WebElement e) {
		wait(e);
		e.clear();
	}
	
	public boolean isDisplayed(WebElement e) {
	
		try {
	        return e.isDisplayed();  // Check if cancel button is displayed
	    } catch (NoSuchElementException e1) {
	        return false; // Return false if the element is not present in the DOM
	    }
	}

	public void dropbox(WebElement e, String ss) {
		wait(e);
		Select s = new Select(e);
		s.selectByValue(ss);
	}
	

	public void scrollDown(WebElement e) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", e);

	}
	
	public String getAttribute(WebElement e) {
		
		wait(e);
		return e.getAttribute("value");
	}

}

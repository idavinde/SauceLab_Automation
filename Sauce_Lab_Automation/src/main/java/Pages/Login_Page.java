package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends Base_Page {

	public Login_Page(WebDriver driver) {

		super(driver);
	}

	@FindBy(id = "user-name")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement loginButton;
	
	@FindBy(xpath="//h3[contains(text(), \"Epic sadfac\")]")
	private WebElement error;
	
	@FindBy(xpath="//button[@class=\"error-button\"]")
	private WebElement cancelButton;
	
	public void enterusername(String s) {

		enterText(username, s);
	}

	public void enterpassword(String s) {
		enterText(password, s);
	}

	public void clickLogin() {
		click(loginButton);
	}
	
	public String getError() {
		
		return getText(error);
	}
	
	public void clickCancel() {
		
		click(cancelButton);
	}
	
	public void clearUser() {
		
		textClear(username);
		
	}
	
	public void clearPass() {
		
		textClear(password);
	}
	
	public boolean isDisplayed() {
		
		return isDisplayed(cancelButton);
	}

}

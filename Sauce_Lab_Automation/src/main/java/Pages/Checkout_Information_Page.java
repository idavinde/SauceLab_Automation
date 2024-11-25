package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout_Information_Page extends Base_Page {

	public Checkout_Information_Page(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(id="first-name")
	private WebElement firstName;
	
	@FindBy(id="last-name")
	private WebElement lastName;
	
	@FindBy(id="postal-code")
	private WebElement postalCode;
	
	@FindBy(id="cancel")
	private WebElement cancelBtn;
	
	@FindBy(id="continue")
	private WebElement continueBtn;
	
	@FindBy(className="title")
	private WebElement title;
	
	@FindBy(xpath="//h3")
	private WebElement errorText;
	
	public String getTitle() {
		
		return getText(title);
	}
	
	public void enterFirstName(String s) {

		enterText(firstName, s);
	}

	public void enterLastName(String s) {

		enterText(lastName, s);
	}

	public void enterPostalCode(String s) {

		enterText(postalCode, s);
	}
	
	public void clickContinueBtn() {
		
		click(continueBtn);
	}
	
	public String getErrorText() {
		
		return getText(errorText);
	}
	
	public void clickCancelBtn() {
		
		click(cancelBtn);
	}
	
	public String getFirstNameAttribute() {
		
		return getAttribute(firstName);
	}
	
public String getLastNameAttribute() {
		
		return getAttribute(lastName);
	}

public String getPostalCodeAttribute() {
	
	return getAttribute(postalCode);
}
}

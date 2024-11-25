package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout_Complete_Page extends Base_Page {
	
	public Checkout_Complete_Page(WebDriver driver){
		super(driver);
	}
	
	@FindBy(id="back-to-products")
	private WebElement backBtn;
	
	@FindBy(xpath="//h2")
	private WebElement header;
	
	public String getHeader() {
		return getText(header);
	}
	
	public void clickBackBtn() {
		
		click(backBtn);
	}
	
}

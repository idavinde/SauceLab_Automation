package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HamburgerMenu extends Base_Page {
	
	public HamburgerMenu(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement hamburg;
	
	@FindBy(id="logout_sidebar_link")
	private WebElement logout;
	
	public void clickHamburgerIcon() {
		click(hamburg);
	}
	
	public void clickLogout() {
		
		click(logout);
	}
}

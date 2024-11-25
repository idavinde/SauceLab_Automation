package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart_Page extends Base_Page {
	
	
	
	public Cart_Page(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath = "//button[text()=\"Remove\"]")
	private List<WebElement> removeBtn;
	
	@FindBy(css = "div.inventory_item_name")
	private List<WebElement> inventoryItemsName;
	
	@FindBy(css = "div.inventory_item_price")
	private List<WebElement> inventoryItemsPrice;
	
	@FindBy(id="continue-shopping")
	private WebElement continueShopping;
	
	@FindBy(id="checkout")
	private WebElement checkout;
	
	@FindBy(css = "a.shopping_cart_link")
	private WebElement shoppingCart;
	
	@FindBy(className="cart_quantity")
	private WebElement cartQuantity;
	
	@FindBy(className="title")
	private WebElement title;
	
	public String getTitle() {
		
		return getText(title);
	}
	
	public String getTotalItems() {
	
		return shoppingCart.getText();
	}
	
	public void clickRemoveBtn (){
		
		clickList(removeBtn);
	}
	
	public void clickContinueShopping() {
		
		click(continueShopping);
	}
	
	public void clickCheckout() {
		
		click(checkout);
	}
	
	public List<WebElement> getAllItemsNames() {
		
		return inventoryItemsName;
	}
	
	public void clickItemName(int index) {

		getAllItemsNames().get(index).click();
	}

	public List<WebElement> getAllItemsPrice() {

		return inventoryItemsPrice;
	}

	public void clickItemPrice(int index) {

		getAllItemsPrice().get(index).click();
	}
	

}

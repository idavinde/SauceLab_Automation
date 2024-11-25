package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product_Detail_Page extends Base_Page {

	public Product_Detail_Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.inventory_details_name")
	private WebElement inventoryDetailName;
	
	@FindBy(css="div.inventory_details_price")
	private WebElement inventoryDetailPrice;
	
	@FindBy(xpath="//button[@name=\"back-to-products\"]")
	private WebElement backToProduct;
	
	@FindBy(css = "a.shopping_cart_link")
	private WebElement shoppingCart;

	public String getItemNameText() {

		return getText(inventoryDetailName);
	}
	
	public String getItemPriceText() {
		
		return getText(inventoryDetailPrice);
	}
	
	public void clickBackToProduct() {
		
		click(backToProduct);
	}
	
	public void clickShoppingCart() {
		
		click(shoppingCart);
	}
}

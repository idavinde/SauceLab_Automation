package Pages;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product_Page extends Base_Page {

	public Product_Page(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//button[text()=\"Add to cart\"]")
	private List<WebElement> addToCart;

	@FindBy(xpath = "//button[text()=\"Remove\"]")
	private List<WebElement> remove;

	@FindBy(css = "div.inventory_item_name")
	private List<WebElement> inventoryItemsName;

	@FindBy(css = "div.inventory_item_price")
	private List<WebElement> inventoryItemsPrice;

	@FindBy(className = "product_sort_container")
	private WebElement dropBox;

	@FindBy(xpath = "//button[@data-test=\"add-to-cart-sauce-labs-bike-light\"]")
	private WebElement addToCartBikeLight;

	@FindBy(xpath = "//button[@data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")
	private WebElement addToCartShirt;

	@FindBy(css = "a.shopping_cart_link")
	private WebElement itemsAdded;

	@FindBy(css = "button#remove-sauce-labs-bike-light")
	private WebElement removeBikeLight;

	@FindBy(css = "button#remove-sauce-labs-bolt-t-shirt")
	private WebElement removeTShirt;

	@FindBy(css = "a.shopping_cart_link")
	private WebElement shoppingLink;

	@FindBy(className = "title")
	private WebElement title;

	public List<WebElement> getAllInventoryItemsName()  {

		return inventoryItemsName;
	}

	public void clickInventoryItemName(int i)  {
		
		getAllInventoryItemsName().get(i).click();
		
	}
	
	public List<WebElement> getAllInventoryItemsPrice()  {

		return inventoryItemsPrice;
	}
	

	public String getTitle() {

		return getText(title);
	}

	public void selectDropbox(String s) {

		dropbox(dropBox, s);

	}

	public void clickAddToCart2Items() {

		click(addToCartBikeLight);
		click(addToCartShirt);
	}

	public void removeItem() {

		click(removeBikeLight);
		click(removeTShirt);
	}

	public void removeMultiple() {
		clickList(remove);
	}

	public String getNoOfItemsAdded() {

		return getText(itemsAdded);
	}

	public void clickAddToCart() {

		clickList(addToCart);
	}

	public void clickShoppingLink() {

		click(shoppingLink);
	}

	public List<String> getDropboxInventoryName() {

		List<String> actualName = new LinkedList<>();

		for (int i = 0; i < inventoryItemsName.size(); i++) {

			actualName.add(getText(inventoryItemsName.get(i)));

		}

		return actualName;

	}

	public List<String> getDropboxInventoryPrice() {

		List<String> actualPrice = new LinkedList<>();

		for (int i = 0; i < inventoryItemsPrice.size(); i++) {

			actualPrice.add(getText(inventoryItemsPrice.get(i)));

		}

		return actualPrice;

	}

}

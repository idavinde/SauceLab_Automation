package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout_Overview_Page extends Base_Page {

	public Checkout_Overview_Page(WebDriver driver) {

		super(driver);
	}

	@FindBy(css = "div.inventory_item_price")
	private List<WebElement> itemsPrice;

	@FindBy(css = "div.summary_subtotal_label")
	private WebElement subtotal;

	@FindBy(css = "div.summary_tax_label")
	private WebElement tax;

	@FindBy(css = "div.summary_total_label")
	private WebElement totalPrice;

	@FindBy(id = "cancel")
	private WebElement cancelBtn;

	@FindBy(id = "finish")
	private WebElement finishBtn;

	@FindBy(className = "title")
	private WebElement title;

	public String getTitle() {

		return getText(title);
	}

	public List<WebElement> AllItemPrice() {

		return itemsPrice;
	}

	public String getItemPrice(int index) {

		return getText(AllItemPrice().get(index));
	}

	public String getSubtotal() {

		return getText(subtotal);
	}

	public String getTax() {

		return getText(tax);
	}

	public String getTotal() {

		return getText(totalPrice);
	}
	
	public void clickCancelBtn() {
		
		click(cancelBtn);
	}
	
	public void clickFinishBtn() {
		
		click(finishBtn);
	}

}

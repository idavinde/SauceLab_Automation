package TestCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Cart_Page;
import Pages.Checkout_Complete_Page;
import Pages.Checkout_Information_Page;
import Pages.Checkout_Overview_Page;
import Pages.HamburgerMenu;
import Pages.Login_Page;
import Pages.Product_Page;
import Utility.ReadConfig;

public class Checkout_Overview_TestCases extends BrowserLaunch {
	
	private Checkout_Information_TestCases checkoutInfoTest;
	private Checkout_Overview_Page checkoutOverview;
	@BeforeMethod
	public void LoginInfor() throws IOException, InterruptedException {
		checkoutOverview = new Checkout_Overview_Page(driver);
		
		checkoutInfoTest = new Checkout_Information_TestCases();
		checkoutInfoTest.driver = this.driver;
		checkoutInfoTest.LoginInform();
		checkoutInfoTest.enterValidInformation();
		
	}
	
	@Test
	public void checkBalance() {
		logger.info("------------------- Validate the total Price, subTotal price and Tax--------------------");
		int size = checkoutOverview.AllItemPrice().size();
		
		float priceTotal =0;
		
		for(int i=0;i<size;i++) {
			
			priceTotal = priceTotal + Float.valueOf(checkoutOverview.getItemPrice(i).substring(1));
			
		}
		String price= String.format("%.2f", priceTotal);
		
		Assert.assertEquals(checkoutOverview.getSubtotal() ,"Item total: $"+price);
		
		float caltax= (float) (priceTotal * (8.01/100)) ;
		String tax= String.format("%.2f", caltax);
		
		Assert.assertEquals(checkoutOverview.getTax() ,"Tax: $"+tax);
		
		float total = priceTotal + caltax;
		
		String grandtotal= String.format("%.2f", total);
		
		Assert.assertEquals(checkoutOverview.getTotal() ,"Total: $"+grandtotal);
	}
	
	
	@Test
	public void validateCancelBtn(){
		
		logger.info("Click On Finish Button");
		checkoutOverview.clickCancelBtn();
		
		Assert.assertEquals( new Product_Page(driver).getTitle(), "Products");
	}
	
	@Test
	public void validateFinishBtn() {
		
		logger.info("Click On Finish Button");
		checkoutOverview.clickFinishBtn();
		
		Assert.assertEquals(new Checkout_Complete_Page(driver).getHeader(), "Thank you for your order!");
	}
	
	@AfterMethod
	public void logout() {
		logger.info("Click on Hamburger");
		new HamburgerMenu(driver).clickHamburgerIcon();

		logger.info("Click one Logout button");
		new HamburgerMenu(driver).clickLogout();
	}
}
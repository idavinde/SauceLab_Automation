package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Utility.ReadConfig;
import Utility.Report;
import org.testng.annotations.Listeners;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

@Listeners(Utility.Report.class)
public class BrowserLaunch {

	 WebDriver driver;
	 public ExtentReports extent;
	public ExtentTest logger1;
	
	 
	 public static Logger logger;
	 
	@BeforeClass
	public void chromeSetup() {
		logger = Logger.getLogger("SauceLab");
		PropertyConfigurator.configure("Log4j.properties");
		System.setProperty("webdriver.chromedriver.driver", "/Users/honey/Downloads/chromedriver-mac-x64");
		
//		ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Run Chrome in headless mode
//        options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration (useful for headless mode)
//        options.addArguments("--window-size=1920x1080"); // Set the window size (important for headless mode)
//        options.addArguments("--no-sandbox"); // Disable sandboxing (useful for certain environments like Docker)
		driver = new ChromeDriver();
		Report.setDriver(driver);
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();

	}

	public void pageRefresh() {

		driver.navigate().refresh();

	}

	public void deleteCookies() {

		driver.manage().deleteAllCookies();
	}

	@AfterClass
	public void quit() {

		driver.quit();

	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public List<String> nameReverseOrder(int size, List<String> ll, int n) throws IOException {

		for (int i = 0; i < size; i++) {

			ll.add(new ReadConfig().getItemName(Integer.toString(i)));
		}

		if (n == 0) {
			Collections.sort(ll);
		} else {

			Collections.sort(ll, Collections.reverseOrder());
		}

		return ll;
	}

	public List<String> priceReverseOrder(int size, List<String> ll, int n) throws IOException {

		for (int i = 0; i < size; i++) {

			ll.add(new ReadConfig().getItemPrice(Integer.toString(i)));
		}

		Collections.sort(ll, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				// Remove the dollar sign and parse to double for comparison
				double num1 = Double.parseDouble(s1.replace("$", ""));
				double num2 = Double.parseDouble(s2.replace("$", ""));
				if (n == 0) {
					return Double.compare(num2, num1); // Descending order
				} else {
					return Double.compare(num1, num2);
				}
			}
		});

		return ll;
	}
	
	protected void handleFailure(String message ) {
	    logger1 = extent.createTest(message);
	    logger1.log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));

	    // Capture screenshot
	    String screenshotPath = Report.captureScreenshot(message);
	    if (screenshotPath != null) {
	        try {
	            logger1.fail("Screenshot is below:",
	                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    // Log the failure for debugging without interrupting the flow
	    
	}

}

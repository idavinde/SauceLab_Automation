package Utility;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.io.FileHandler;

public class Report extends TestListenerAdapter {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	private static WebDriver driver;
	
	   
	public static void setDriver(WebDriver driver) {
		Report.driver = driver;
	}

	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";

		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);// specify
																											// location
																											// of the
																											// report

		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		htmlReporter.config().setDocumentTitle("Sauce Demo Project"); // Tile of report
		htmlReporter.config().setReportName("Test Automation Report"); // name of the report

		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Davinder");

	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed
																							// information to the report
																							// with GREEN color
																							// highlighted
	}

	public void onTestFailure(ITestResult tr) {
		System.out.println("abc");
		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information
																							// to the report with GREEN
		if (driver == null) {
	        System.out.println("Driver is null in onTestFailure.");
	    } else {
	        System.out.println("Driver is available in onTestFailure.");
	    }																					// color highlighted
		
		Object expectedValue = tr.getTestContext().getAttribute("expected");
	    Object actualValue = tr.getTestContext().getAttribute("actual");

	    if (expectedValue != null && actualValue != null) {
	        // Log the expected and actual values in the Extent report
	        logger.fail("Expected: " + expectedValue + ", but found: " + actualValue);
	    } else {
	        logger.fail("Expected and actual values are not available in the test context.");
	    }
		

		String screenshotPath = captureScreenshot(tr.getName());
		 

		if (screenshotPath != null) {
			try {
				logger.fail("Screenshot is below:",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	public static String captureScreenshot(String testName) {
		if (driver != null) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + testName + "_" + timeStamp
					+ ".png";
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileHandler.copy(screenshot, new File(screenshotPath));
				return screenshotPath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}

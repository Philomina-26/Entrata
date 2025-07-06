package listener;


	import org.testng.ITestListener;

	import org.testng.ITestResult;

import utils.Screenshot;

import org.openqa.selenium.WebDriver;

	public class TestListener implements ITestListener {

	    @Override
	    public void onTestFailure(ITestResult result) {
	        Object testClass = result.getInstance();

	        if (testClass instanceof Base.EntrataBase) {
	            WebDriver driver = ((Base.EntrataBase) testClass).getDriver();  // Create getDriver() method in base class
	            String screenshotPath = Screenshot.captureScreenshot(driver, result.getName());
	            System.out.println("Screenshot saved: " + screenshotPath);
	        }
	    }
	}




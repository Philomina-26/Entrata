package Base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import extentReports.Extent;
import pages.firstcasepage;
import utils.ReadFile;

public class EntrataBase extends SeleniumBase{

	 private static final Logger logger = LogManager.getLogger(SeleniumBase.class);

	ReadFile rf = new ReadFile();
	String url = rf.urlvalue();

	@BeforeTest
	@Parameters("browser")
	public void launchDriver(String browser) throws InterruptedException
	{
		Setup(browser);	
		logger.info("Selected browser is opening..."+browser);
		callURL(url);
		logger.info("Given URL is opening..."+url);
	}

	//TO CALL URL
	public void callURL(String URL) throws InterruptedException {

		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		


	}





	@AfterTest
	public void teardown()
	{
		driverQuit();
		logger.info("Driver Quit after test");
		Extent.extentReports.flush();
	}

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}


}

package Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import extentReports.Extent;
import utils.ReadFile;

public class EntrataBase extends SeleniumBase{



	ReadFile rf = new ReadFile();
	String url = rf.urlvalue();

	@BeforeTest
	@Parameters("browser")
	public void launchDriver(String browser) throws InterruptedException
	{
		Setup(browser);	
		callURL(url);
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
		Extent.extentReports.flush();
	}


}

package pages;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import static extentReports.ExtentTestManager.getTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Base.SeleniumBase;

public class firstcasepage extends SeleniumBase{
	
    private static final Logger logger = LogManager.getLogger(firstcasepage.class);


	public firstcasepage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[@href='/']")
	private WebElement entrataLogo;

	@FindBy(xpath = "//a[contains(text(),'Company News')]")
	private WebElement companyNews;


	@FindBy(xpath = "//h1[@class='post-header_heading']")
	private WebElement newsTitle;


	public void verifyEntrata()
	{
		
		boolean logo = entrataLogo.isDisplayed();
		if(logo)
		{
			logger.info("Entrata homepage is displayed");
			getTest().log(Status.PASS,"Entrata Homepage is loaded successfully");
			
		}
		else
		{
			logger.error("Entrata homepage is not displayed");
			getTest().log(Status.FAIL,"Entrata Homepage is not loaded successfully");	
		}

	}

	public void companyNews()
	{
		String baseurl = driver.getCurrentUrl();
		Click(companyNews);
		logger.info("Clicked company news section");
		waitFOR(newsTitle);
		logger.info("Waiting for news headlines..");
		String titleofthenews = newsTitle.getText();
		System.out.println("The title of the current Company news is: "+titleofthenews);
		logger.info("News title is retrived.."+titleofthenews);
		String currenturl = driver.getCurrentUrl();

		try {

			Assert.assertNotEquals(baseurl, currenturl);
			logger.info("Baseurl and Currenturl is not be same.. ");
			getTest().log(Status.PASS,"Entrata Company news URL is different from Home URL");
		}
		catch(AssertionError e)
		{
			logger.error("Testcase failed due to.. "+e);
			getTest().log(Status.FAIL,"Entrata Company news URL is not different from Home UR");
			throw e;
		}


		System.out.println("The current URL is: "+currenturl);


		

	}
	
	public secondcasepage NavigatetoHomepage()
	{
		driver.navigate().back();
		logger.info("Navigating back to homepage..");
		return new secondcasepage(driver);
		
	}



}

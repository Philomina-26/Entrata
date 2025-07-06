package pages;

import static extentReports.ExtentTestManager.getTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Base.SeleniumBase;

public class firstcasepage extends SeleniumBase{

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
			getTest().log(Status.PASS,"Entrata Homepage is loaded successfully");
		}
		else
		{
			getTest().log(Status.FAIL,"Entrata Homepage is not loaded successfully");	
		}

	}

	public void companyNews()
	{
		String baseurl = driver.getCurrentUrl();
		Click(companyNews);
		waitFOR(newsTitle);
		String titleofthenews = newsTitle.getText();
		System.out.println("The title of the current Company news is: "+titleofthenews);
		String currenturl = driver.getCurrentUrl();

		try {

			Assert.assertNotEquals(baseurl, currenturl);
			getTest().log(Status.PASS,"Entrata Company news URL is different from Home URL");
		}
		catch(AssertionError e)
		{
			
			getTest().log(Status.FAIL,"Entrata Company news URL is not different from Home UR");
			throw e;
		}


		System.out.println("The current URL is: "+currenturl);


		

	}
	
	public secondcasepage NavigatetoHomepage()
	{
		driver.navigate().back();
		return new secondcasepage(driver);
		
	}



}

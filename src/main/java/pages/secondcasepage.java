package pages;

import static extentReports.ExtentTestManager.getTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Base.SeleniumBase;
import utils.ReadFile;

public class secondcasepage extends SeleniumBase{
	
	public secondcasepage(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = LogManager.getLogger(secondcasepage.class);
	
	ReadFile rf = new ReadFile();
	String url = rf.urlvalue();
	
	static WebDriverWait wait;
	
	static String theme = "Majestic";
	static String themeurl = "majestic";
	static String tomorrowdate = LocalDate.now().plusDays(1).toString();
	static String avail = "//span[@class='unit-title']/a/../following-sibling::div/span[contains(text(),'Available')]";
	
	@FindBy(xpath = "//nav[@role='navigation']//div[@role='button']/div[contains(text(),'Resources')]/..")
	private WebElement resources;
	
	@FindBy(xpath="//div[@role='button']//div[contains(text(),'Resources')]/../following-sibling::nav//a")
	private List<WebElement> resourceDropdowns;
	
	@FindBy(xpath="//h2[contains(text(),'Theme Gallery')]")
	private WebElement themegallerypage;
	
	@FindBy(xpath="//h2[@class='theme-title']")
	private List<WebElement> themeslist;
	
	@FindBy(xpath="//span[@class= 'value' and contains(text(),'(555) 123-8595')]")
	private WebElement phone;
	
	
	
	
	private static By themeselection = By.xpath("//h2[@class='theme-title' and contains(text(), '"+theme+"')]//following::a[contains(@href,'"+themeurl+"demo') and text()='desktop_mac']");
	private static By href = By.xpath("//h2[@class='theme-title' and contains(text(), '"+theme+"')]//following::a[contains(@href,'"+themeurl+"demo')]");
	
	
	public void resourcespage()
	{
		
		waitFOR(resources);
		logger.info("waiting for resources button");
		actionsclick(resources);
		logger.info("resources button is clicked");
		String selection = "theme-gallery";
		dropdown(selection,resourceDropdowns);
		logger.info("Selected option in dropdown is clicked");
		getTest().log(Status.PASS,selection+" dropdown from Entrata Resources is selected");
		waitFOR(themegallerypage);
		logger.info("waiting for themes gallery page");
		if(themegallerypage.isDisplayed())
		{
		
			logger.info(selection+" page is displayed");
			getTest().log(Status.PASS,selection+" page from Entrata Resources is displayed");
			System.out.println("There is totally "+themeslist.size()+" themes in Resources");
		}
		else
		{
			logger.error(selection+" page is not displayed");
			getTest().log(Status.FAIL,selection+" page from Entrata Resources is not displayed");
		}
		
	}
	
	public void themes() throws InterruptedException
	
	{
		
		String hrefValue = driver.findElement(href).getAttribute("href");
		

		
		driver.findElement(themeselection).click();
		logger.info("selecting any theme based on dynamic option");
		switchwindow(phone);
		logger.info("window switched");
		String currenturl = driver.getCurrentUrl();
		
		
		try {

			Assert.assertEquals(hrefValue, currenturl);
			logger.info("Actual url meets Expected url: Expection: "+hrefValue+" Actual:"+currenturl);
			getTest().log(Status.PASS,"Theme name: "+theme+" is selected and Window is switched");
		}
		catch(AssertionError e)
		{
			logger.error("Theme name: "+theme+" is selected and Window is not switched due to " +e);
			getTest().log(Status.FAIL,"Theme name: "+theme+" is selected and Window is not switched");
			throw e;
		}
		
		
	}
	
	public thirdcasepage NavigatetoHomepage()
	{
		
		driver.navigate().to(url);
		logger.info("Navigating back to home URL..s");
		return new thirdcasepage(driver);
		
		
	}
	
	
		
		
		
		
				
		
		

	}



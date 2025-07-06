package pages;

import static extentReports.ExtentTestManager.getTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

public class secondcasepage extends SeleniumBase{
	
	public secondcasepage(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	static WebDriverWait wait;
	
	static String theme = "Solo";
	static String themeurl = "solo";
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
	
	@FindBy(xpath="//button//span[text()='Menu']")
	private WebElement menu;
	
	@FindBy(xpath = "//div[@class='picker-field']//button")
	private WebElement datepicker;
	
	@FindBy(xpath = "//div[@role='application']//h6")
	private WebElement month;
	
	@FindBy(xpath="//button[@type='submit' and contains(text(),'Search')]")
	private WebElement searchbutton;
	
	@FindBy(xpath="//span[@class='unit-title']/a/../following-sibling::div/span[contains(text(),'Available')]")
	private List<WebElement> availability;
	
	
	private static By themeselection = By.xpath("//h2[@class='theme-title' and text() = '"+theme+"']//following::a[contains(@href,'"+themeurl+"demo') and text()='desktop_mac']");
	private static By href = By.xpath("//h2[@class='theme-title' and text() = '"+theme+"']//following::a[contains(@href,'"+themeurl+"demo')]");
	private static By date = By.xpath("//div[@role='application']//button[@data-date='"+tomorrowdate+"']");
	private static By apartnames = By.xpath(avail+"/../preceding-sibling::span[@class='unit-title']/a[@role='button']");
	private static By roomdetails = By.xpath(avail+"/preceding-sibling::span");
	private static By feedetails = By.xpath(avail+"/../parent::div/following-sibling::div//span[@class='fee-transparency-text']");
	
	public void resourcespage()
	{
		
		waitFOR(resources);
		actionsclick(resources);	
		String selection = "theme-gallery";
		dropdown(selection,resourceDropdowns);
		getTest().log(Status.PASS,selection+" dropdown from Entrata Resources is selected");
		waitFOR(themegallerypage);
		if(themegallerypage.isDisplayed())
		{
			getTest().log(Status.PASS,selection+" page from Entrata Resources is displayed");
			System.out.println("There is totally "+themeslist.size()+" themes in Resources");
		}
		else
		{
			getTest().log(Status.FAIL,selection+" page from Entrata Resources is not displayed");
		}
		
	}
	
	public void themes()
	
	{
		
		String hrefValue = driver.findElement(href).getAttribute("href");
		

		
		driver.findElement(themeselection).click();
		
		switchwindow(menu);
		
		String currenturl = driver.getCurrentUrl();
		
		
		try {

			Assert.assertEquals(hrefValue, currenturl);
			getTest().log(Status.PASS,"Theme name: "+theme+" is selected and Window is switched");
		}
		catch(AssertionError e)
		{
			
			getTest().log(Status.FAIL,"Theme name: "+theme+" is selected and Window is not switched");
			throw e;
		}
		
		
	}
	
	public void bookroom() throws InterruptedException
	{
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);"); // Scroll down 300 pixels
		


		//driver.findElement(By.xpath("//button[@id='pc_banner_close']")).click();
		waitFOR(datepicker);
		Click(datepicker);
		Thread.sleep(5000);
		
		waitFOR(month);
		System.out.println("Month value: "+month.getText());
		
		waitFOR(date);
		driver.findElement(date).click();
		
		Click(searchbutton);
		cloudfare();
		driver.switchTo().defaultContent();
		waitFOR(apartnames);
		System.out.println("All the available apartment names and their fee details as follows: ");
		List<WebElement>apartlist = driver.findElements(apartnames);
		List<WebElement>roomlist = driver.findElements(roomdetails);
		List<WebElement>feelist = driver.findElements(feedetails);
		
		for(int i=0;i<apartlist.size();i++)
		{
			String aparts = apartlist.get(i).getText();
			String rooms = roomlist.get(i).getText();
			String fees = feelist.get(i).getText();
			System.out.println("Apartment Name: "+aparts+" Room details: "+rooms+" Fee details: "+fees);
			
		}
		
		
		
		
				
		
		

	}

}

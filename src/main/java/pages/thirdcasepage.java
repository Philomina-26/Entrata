package pages;

import static extentReports.ExtentTestManager.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Base.SeleniumBase;
import utils.Constants;

public class thirdcasepage extends SeleniumBase {

	public thirdcasepage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = LogManager.getLogger(thirdcasepage.class);

	@FindBy(xpath = "//a[contains(@href,'demo') and contains(text(), 'Watch demo')]")
	private WebElement demo;

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstname;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement watchdemobutton;

	@FindBy(xpath = "//div[@id='ValidMsgFirstName']")
	private WebElement errormsg;
	
	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastname;
	
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@id='Company']")
	private WebElement Company;
	
	@FindBy(xpath = "//input[@id='Phone']")
	private WebElement Phone;
	
	@FindBy(xpath = "//select[contains(@id,'Unit_Count')]")
	private WebElement unitcount;
	
	@FindBy(xpath="//input[@id='Title']")
	private WebElement jobtitle;
	
	@FindBy(xpath="//select[@id='demoRequest']")
	private WebElement iam;
	
	

	public void clickdemo() {
		Click(demo);
		logger.info("Demo Form is clicked");
		waitFOR(firstname);
		logger.info("Waiting for the form");
		Click(watchdemobutton);
		logger.info("Clicking Watch demo button without filling any fields");
		waitFOR(errormsg);
		try {
			Assert.assertTrue(errormsg.getText().equals(Constants.errormsg),
					"Error message is displayed while clicking demo button without giving mandatory fields");
			logger.info("Actual condition meets the Expected Condition...Error message is displayed");
			getTest().log(Status.PASS,"Error message is displayed while clicking demo button without giving mandatory fields");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			logger.error("Actual condition does not meet the Expected Condition due to.."+e);
			getTest().log(Status.FAIL,"Error message is not displayed while clicking demo button without giving mandatory fields");
			throw e;
		}

	}
	
	public void fillingform(String fname,String lname,String mail,String company,String phone,String ucount,String jtitle,String im)
	{
		firstname.sendKeys(fname);
		logger.info("firstname is filled from excel");
		lastname.sendKeys(lname);
		logger.info("lastname is filled from excel");
		email.sendKeys(mail);
		logger.info("mail id is filled from excel");
		Company.sendKeys(company);
		logger.info("Company name is filled from excel");
		Phone.sendKeys(phone);
		logger.info("Phone number is filled from excel");
		SelectbyValue(unitcount,ucount);
		logger.info("Unit count is selected from selectByValue from excel");
		jobtitle.sendKeys(jtitle);
		logger.info("Job title is filled from excel");
		SelectbyValue(iam,im);
		logger.info("Iam section is selected from selectByValue from excel");
		
		getTest().log(Status.PASS,"All the fields are filled from Excel");
		
		
	}

}

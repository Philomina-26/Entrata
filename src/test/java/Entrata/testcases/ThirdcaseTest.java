package Entrata.testcases;

import static extentReports.ExtentTestManager.getTest;
import static extentReports.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.EntrataBase;

import pages.thirdcasepage;
import utils.Constants;
import utils.Exceldata;

public class ThirdcaseTest extends EntrataBase{
	
	
	

	@DataProvider(name = Constants.ExcelFileName)
	public Object[][] SupplyTestData() throws IOException {

		Object[][] data = Exceldata.getTestDataFromExcel(Constants.sheetName);
		return data;

	}
	
	
	
	@Test(dataProvider=Constants.ExcelFileName,priority = 3)
	public void verifyDemoform(String Firstname,String Lastname,String Email,String Company,String Phone,String Units, String Title,String Iam, Method method) throws InterruptedException
	{
		startTest(method.getName(),"Entrata Demo form page");
		
		thirdcasepage tcp = new thirdcasepage(driver);
		tcp.clickdemo();
		tcp.fillingform(Firstname,Lastname,Email,Company,Phone,Units,Title,Iam);
		
		getTest().log(Status.PASS,"Entrata Demo form Testcase passed");
		
		
	}

}

package Entrata.testcases;



import static extentReports.ExtentTestManager.getTest;
import static extentReports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.EntrataBase;
import listener.TestListener;
import pages.firstcasepage;

public class FirstcaseTest extends EntrataBase{

	
	@Test(priority=1)
	
	public void verifyEntrataHomepage(Method method)
	{
		startTest(method.getName(),"Entrata Homepage and Company news");
		
		firstcasepage fcp = new firstcasepage(driver);
		fcp.verifyEntrata();
		fcp.companyNews();
		fcp.NavigatetoHomepage();
		
		getTest().log(Status.PASS,"Entrata Homepage and Company news TestCase passed");
		
		
	}

	

}

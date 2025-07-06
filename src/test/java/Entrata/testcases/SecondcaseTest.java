package Entrata.testcases;

import static extentReports.ExtentTestManager.getTest;
import static extentReports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.EntrataBase;

import pages.secondcasepage;

public class SecondcaseTest extends EntrataBase{
	
	@Test(priority = 2)
	public void verifyResourcespage(Method method) throws InterruptedException
	{
		startTest(method.getName(),"Entrata Resources and Theme Gallery page");
		
		secondcasepage scp = new secondcasepage(driver);
		scp.resourcespage();
		scp.themes();
		scp.bookroom();
		
		
		getTest().log(Status.PASS,"Entrata Resources and Theme Gallery page Testcase passed");
		
		
	}


}

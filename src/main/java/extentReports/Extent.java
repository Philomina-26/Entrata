package extentReports;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.SeleniumBase;



public class Extent extends SeleniumBase{
	
	
	

	public static final ExtentReports extentReports = new ExtentReports();
	 
    public synchronized static ExtentReports createExtentReports() {
    	
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/extent-reports "+timeStamp+".html");
        reporter.config().setReportName("Entrata Test Report");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Website Test Automation Result");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Philomina G");
        return extentReports;
        
    }
	
	

}

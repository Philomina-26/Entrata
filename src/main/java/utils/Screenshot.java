package utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class Screenshot {
	
	

	

	    public static String captureScreenshot(WebDriver driver, String testName) {
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File dest = new File(screenshotPath);

	        try {
	            Files.createDirectories(dest.getParentFile().toPath());
	            FileUtils.copyFile(src, dest);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return screenshotPath;
	    }
	}




package Base;

import static extentReports.ExtentTestManager.getTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;


public class SeleniumBase {

	public static WebDriver driver;
	static int MaxWaitTime = 30;
	WebDriverWait wait;

	public WebDriver Setup(String browser) {
		try {
			switch (browser) {
			case "Chrome":
				return driver = new ChromeDriver();

			case "Firefox":
				return driver = new FirefoxDriver();

			case "Edge":
				return driver = new EdgeDriver();

			default:
				System.err.println("Driver is not defined");
				break;

			}

		} catch (NoSuchElementException e) {
			System.err.println("Element not found =>" + e.getMessage());
		} catch (WebDriverException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return null;

	}

	public void waitFOR(WebElement element)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(MaxWaitTime));
		try {
			element = wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

		}

		catch(Exception e)
		{
			System.out.println("Webdriver wait exception "+e.getMessage());
		}
	}

	public void waitFOR(By element)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(MaxWaitTime));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

		}

		catch(Exception e)
		{
			System.out.println("Webdriver wait exception "+e.getMessage());
		}
	}

	public void Click(WebElement element)
	{
		waitFOR(element);
		element.click();

	}

	public void dropdown(String str, List<WebElement> ele)
	{
		List<WebElement>dropdowns = ele;
		for(int i=0;i<dropdowns.size();i++)
		{
			String hrefValue = dropdowns.get(i).getAttribute("href");

			if(hrefValue.contains(str))
			{
				dropdowns.get(i).click();
				
			}
		}
	}

	public void actionsclick(WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element);

	}

	public void switchwindow(WebElement element)
	{
		String currentwindow = driver.getWindowHandle();
		Set<String> Allwindows =  driver.getWindowHandles();
		List<String> toList = new ArrayList<String>(Allwindows);
		for(String s:toList)
		{
			if(!s.equals(currentwindow))
			{
				driver.switchTo().window(s);
				waitFOR(element);
				try {
					boolean elementisvisible = element.isDisplayed();
					if(elementisvisible)
					{
						System.out.println("Window is switched and element is displayed");
					}
					else
					{
						System.out.println("Window is switched and element is not displayed");
					}
				}
				catch(Exception e)
				{
					System.out.println("Exception found during window switch "+e.getMessage());
				}
				break;
			}


		}
	}
	
	public void cloudfare()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Switch to the CAPTCHA iframe
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
		    By.xpath("//iframe[@title='Widget containing a Cloudflare security challenge']"))
		);

		// Wait for the checkbox inside the CAPTCHA to be clickable and click it
		wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//label[@class='ctp-checkbox-label']"))
		).click();

	}

	public void driverQuit()
	{
		driver.quit();
	}

	public void driverClose()
	{
		driver.close();
	}

}

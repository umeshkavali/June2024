package hoocks;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hoocks
{
	WebDriver driver;
	Properties p;
	
	@Before
	public void setup() throws IOException
	{
		driver = BaseClass.initializeBrowser();
		
		p = BaseClass.getProperties();
		driver.get(p.getProperty("appURL"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
	}
	
}

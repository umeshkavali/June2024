package factory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.LogManager;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseClass 
{
	static WebDriver driver;
	static Properties p;
	static Logger logger;
	
	
	public static WebDriver initializeBrowser() throws IOException
	{
		p = getProperties();
		String executionEnv = p.getProperty("execution_env");
		String browser = p.getProperty("browser").toLowerCase();
		String os = p.getProperty("os").toLowerCase();
		
		
		
		if(executionEnv.equalsIgnoreCase("local"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			// OS
			switch(os)
			{
			case "windows": capabilities.setPlatform(Platform.WINDOWS); break;
			case "mac": capabilities.setPlatform(Platform.MAC); break;
			case "linux": capabilities.setPlatform(Platform.LINUX); break;
			default: System.out.println("No Matchign OS");
			return null;
			}
			

			
			switch(browser.toLowerCase())
			{
			case "chrome":
				driver = new ChromeDriver();
				break;	
				
			case "firefox":
				driver = new FirefoxDriver();
				break;
				
			case "edge":
				driver = new EdgeDriver();
				break;
				default: System.out.println("No browser Matching");
				driver =  null;
			}
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
		
	}
	
	public static WebDriver getDriver()
	{
		
		return driver;
		
	}
	
	public static Properties getProperties() throws IOException
	{
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\config.properties");
		p = new Properties();
		p.load(file);
		return p;
		
	}
	
	public static Logger getLogger()
	{
		LogManager.getLogManager();
		return logger;		
	}
	
	public static String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		
	}
	
	public static String randomNumber()
	{
		String generatedString = RandomStringUtils.randomNumeric(5);
		return generatedString;
		
	}
	
	public static String randomAlphaNUmaric()
	{
		String str = RandomStringUtils.randomAlphabetic(5);
		String num = RandomStringUtils.randomNumeric(5);
		return str+num;
		
	}
}

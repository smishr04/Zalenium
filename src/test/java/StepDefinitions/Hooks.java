package StepDefinitions;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
	public static WebDriver driver;
	
	@Before("@Regression1")
	public void openBrowser(Scenario scenario) throws Exception {
		
		long threadId = Thread.currentThread().getId();
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println("Started in thread: " + threadId + ", in JVM: " + processName);
		

		String browserName= "chromeGrid";

		if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("Chrome")) {

			
			File file = new File("./Drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
			ChromeOptions options = new ChromeOptions();		
			options.addArguments("disable-infobars");	
			options.addArguments("window-size=1400,800");
			options.addArguments("headless");
			options.setExperimentalOption("useAutomationExtension", false);
			driver = new ChromeDriver(options);

		}

		else if (browserName.equalsIgnoreCase("IE")) {

			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			caps.setCapability("ignoreZoomSetting", true);
			System.setProperty("webdriver.ie.driver","./Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(caps);
		}
		else if (browserName.equalsIgnoreCase("chromeGrid")) {
			
			//DesiredCapabilities caps = DesiredCapabilities.chrome();
			
			ChromeOptions options = new ChromeOptions();
			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setPlatform(Platform.LINUX);
			caps.setVersion("");
			caps.setCapability("chromedriverVersion", "2.43");
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			caps.setCapability("zal:name", "Mytest");
			caps.setCapability("zal:build", "4");
			//caps.setCapability("zal:screenResolution","1280x720");
			//caps.setCapability("zal:idleTimeout","1");
			//caps.setCapability("zal:recordVideo",true);
			//caps.setCapability("zal:tz","Asia/Bangalore");
			 driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), caps);
			 
			
			
		}
		
			
		
	}	
		
@After("@Regression1")
public void closeBrowserAndEmbedScreenshot(Scenario scenario)
		throws Exception {	
//if (scenario.isFailed()) {
	
	try {
		TakesScreenshot TS = (TakesScreenshot) driver;

		// for the storing the screenshot in the project workspace
		File Location = TS.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Location, new File("./ErrorReports/"+ scenario.getName() + ".png"));				
		// for embedding the screenshot into cucumber report
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
						
	} catch (Exception e) {
	System.out.println("Unable to take screenshot :" + e);
	}

	//}
	driver.manage().deleteAllCookies();
	driver.quit();
}
}
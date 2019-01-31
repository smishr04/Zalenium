package StepDefinitions;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


	public class GlobalStepDefinition {
		
		public static  WebDriver _driver;		
		
		public void InvokeDriver(String URL) throws InterruptedException {
			_driver = Hooks.driver;	
			
			 if (_driver != null)
		        {
				   	_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   // Setting implicit Timeout
		            _driver.manage().window().maximize();
		            _driver.get(URL);
		        
		            
		        } 
			
		}

}

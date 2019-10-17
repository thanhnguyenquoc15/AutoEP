package scripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import lib.CaptureScreenshot;

public class BaseTest {

	 CaptureScreenshot cap;
	 WebDriver driver;
	 //1235678
	 
	 @BeforeMethod
	 public void setUp() {
		 cap = new CaptureScreenshot(driver);
	 }
	 @AfterMethod
	 public void tearDown(ITestResult testResult) throws IOException {
	        if (testResult.getStatus() == ITestResult.FAILURE) {
	            cap.takeScreenshot(testResult.getName());
	        }
	        driver.quit();
	    }
}

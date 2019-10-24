package scripts;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import bsh.ParseException;
import frame.EPLoginPage;
//import io.github.bonigarcia.wdm.WebDriverManager;
import lib.BrowserFactory;
import lib.ReadData;
import lib.Screenshot;

public abstract class HelperClass {

	protected Logger log = Logger.getLogger(this.getClass().getName());

	public abstract void performBeforeMethodOperation();

	public abstract void performAfterMethodOperation();

	public static WebDriver driver;
	//public static WebDriverManager driver;
	Screenshot screenshot;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.info("in @BeforeMethod");
		//log.info("Heyyy");

		HelperClass.driver = BrowserFactory.getDriver(ReadData.BROWSER);
		try {
//			driver.get("https://100.30.7.130");
			driver.get("https://" + ReadData.EPServer);
			Thread.sleep(1000);
		} catch (Exception e) {
			this.driver.close();
			e.printStackTrace();
		}
		EPLoginPage loginPage = PageFactory.initElements(driver, EPLoginPage.class);
		loginPage.loginEP(ReadData.EPUser, ReadData.EPPass);
		screenshot = new Screenshot(driver);

	}

	@BeforeClass
	public void beforeClass(){
	log.info("in @BeforeClass");
	}
	 
	@BeforeMethod (alwaysRun = true)
    public void beforeMethodClass(){
	log.info("in @BeforeMethod");
	performBeforeMethodOperation();
	}
//	 
//	@AfterMethod
//	public void close()
//	{
//	log.info("in @AfterMethod");	
////	this.driver.close();
//	}
	 
	@AfterClass
	public void afterClass(){
	log.info("in @AfterClass");
	}
	 
	@AfterMethod
	public void afterSuite(ITestResult testResult) throws IOException, ParseException, java.text.ParseException{
	log.info("in @AfterMethod"); 
	if (testResult.getStatus() == ITestResult.FAILURE) {
        screenshot.takeScreenshot(testResult.getName());
    }
	driver.quit();
	}

	
}

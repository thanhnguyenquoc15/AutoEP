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

import frame.EPLoginPage;
import lib.BrowserFactory;
import lib.CaptureScreenshot;
import lib.ReadData;

public abstract class HelperClass {

	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
    public abstract void performBeforeMethodOperation();
    public abstract void performAfterMethodOperation();
    CaptureScreenshot cap;

	public static WebDriver driver; 
	
	@BeforeSuite
	public void beforeSuite(ITestContext context){
	log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
	log.info("in @BeforeSuite"); 
	log.info("Heyyy");
	
	HelperClass.driver = BrowserFactory.getDriver(ReadData.BROWSER);		
	driver.get("https://" + ReadData.EPServer);	
	
	EPLoginPage loginPage = PageFactory.initElements(driver, EPLoginPage.class);

	loginPage.loginEP(ReadData.EPUser,ReadData.EPPass);
	
	cap = new CaptureScreenshot(driver);
		
	}
	 
	@BeforeClass
	public void beforeClass(){
	log.info("in @BeforeClass");
	}
	 
	@BeforeMethod (alwaysRun = true)
	public void beforeMethodClass(){
	log.info("in @BeforeMethod");
	performBeforeMethodOperation();
	
	

//	@BeforeMethod (alwaysRun = true)
//    public void appState () {
        //Web driver initialization
//    POMCommonFunctionObj = new POMCommonFunction();
//    POMRestMethodsObj= new POMRestMethods();
//        EnvSetup.setupBrowserInstance(true);
//		EPLoginPage = PageFactory.initElements(EnvSetup.WEBDRIVER, EPLoginPage.class);
//        EPLoginPage.appState();



//    }

	}
	 
	@AfterMethod
	public void close()
	{
	log.info("in @AfterMethod");	
	//this.driver.close();
	}
	@AfterSuite
	 public void tearDown(ITestResult testResult) throws IOException {
	        if (testResult.getStatus() == ITestResult.FAILURE) {
	            cap.takeScreenshot(testResult.getName());
	        }
	        driver.quit();
	    }
	 
	@AfterClass
	public void afterClass(){
	log.info("in @AfterClass");
	}
//	 
//	@AfterSuite
//	public void afterSuite() throws IOException{
//	log.info("in @AfterSuite"); 
//	driver.quit();
//	}
//	
	
	
	
	
	
}

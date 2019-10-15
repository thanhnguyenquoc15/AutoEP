package frame;

//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import scripts.HelperClass;

import org.apache.log4j.Logger;

public class EPLoginPage {
	
	// Define logger
	protected Logger log = Logger.getLogger(this.getClass().getName());
	WebDriver driver = HelperClass.driver;
	// Page Objects
	@FindBy(xpath="//input[@type=\"text\"]")
	public WebElement username;
	@FindBy(xpath="//input[@type=\"submit\"]")
	public WebElement submit;
	@FindBy(xpath="//input[@type=\"password\"]")
	public WebElement password;
	@FindBy(xpath="//input[@name='logon']")
	public WebElement login;
	
//	log.info("Launch Experience Portal Administrator URL");
		
	public void loginEP(String user, String pass) {
		log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
//		WebDriverWait wait = new WebDriverWait(driver, 20);	
//		wait.until(ExpectedConditions.visibilityOf(username));
		Thread.sleep(2000);
		log.info("enter EP's user");
		username.sendKeys(user);
		Thread.sleep(500);
		submit.click();
		log.info("enter EP's pass");
		password.sendKeys(pass);
		Thread.sleep(500);
		log.info("Login");
		login.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		}
	//method

}

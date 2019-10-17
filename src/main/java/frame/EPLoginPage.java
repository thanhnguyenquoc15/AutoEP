package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class EPLoginPage {
	
	// Define logger
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
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
		username.sendKeys(user);
		Thread.sleep(500);
		submit.click();
		password.sendKeys(pass);
		Thread.sleep(500);
		login.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		}
	//method

	
	
	
	
	
	
}

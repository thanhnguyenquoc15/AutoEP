package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import lib.BrowserFactory;
import lib.ReadData;

public class EPMServersPage extends scripts.HelperClass {

	@Override
	public void performBeforeMethodOperation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void performAfterMethodOperation() {
		// TODO Auto-generated method stub

	}
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	protected EPCommonFunction ComFuncObj = PageFactory.initElements(driver, EPCommonFunction.class);
	//WebDriver driver = HelperClass.driver;
	protected Logger log = Logger.getLogger(this.getClass().getName());
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);

	// Use buttons
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}
	
	// Alarm Code
	public WebElement alarmCode(String alarmCodeName) {
		WebElement alarmCode = driver.findElement(By.xpath("//a[text()=\"" + alarmCodeName + "\"]"));
		return alarmCode;
	}
	
	//TrapID
	public WebElement trapID() {
		WebElement trapID = driver.findElement(By.xpath("//*[text()=\"TrapID:\"]/../td[2]"));
		return trapID;
	}
	
	//Verify
	public WebElement verify() {
		WebElement verify = driver.findElement(By.xpath("//table[@class=\"errors\"]"));
		return verify;
	}
	
	

	public void config_Alarm_Code(String alarm_Code,
								  String trap_ID,								  
								  String message)
	{
		log.info("Config Alarm Code");
		ComFuncObj.switchEPMenu("EPM Servers");
		ComFuncObj.switchFrame("main");
		Button("Alarm Codes").click();
		ComFuncObj.wait(1);
		alarmCode(alarm_Code).click();
		ComFuncObj.wait(1);
		Button("Apply").click();
		ComFuncObj.wait(1);
		String test = trapID().getText().replaceAll("\\s+", "");
		log.info(test);
		log.info(trap_ID);
		ComFuncObj.wait(2);
		
		String returnMessage = verify().getText();
		log.info(returnMessage); 
		Assert.assertEquals(returnMessage,message,"Can not apply successfully");
	
		Assert.assertEquals(test,trap_ID,"Wrong Trap ID");
	}
	/*
	 * @author: tqnguyen@avaya.com
	 * 
	 * @last modified date:
	 * 
	 * @desc: fill in Group Name and Recipient Email Addresses using Group order
	 * Number
	 */
	public void configAlarmDelivery(String groupOrder, 
									String groupName, 
									String recipientEmails, 
									String verifyMessage,
									String message) {
		try {

			log.debug("Entering into Methos configAlarmDelivery");

			WebElement name = driver
					.findElement(By.xpath("//*[@id='alarmDeliveryConfigForm:displayNameGroup" + groupOrder + "']"));
			name.clear();
			name.sendKeys(groupName);
			WebElement emails = driver
					.findElement(By.xpath("//*[@id='alarmDeliveryConfigForm:addressesGroup" + groupOrder + "']"));
			emails.clear();
			emails.sendKeys(recipientEmails);

			Button("Apply").click();

			if (verifyMessage == "true") {
				String returnMessage = driver.findElement(By.xpath("//table[@class='errors']")).getText();
				log.info(returnMessage);
				Assert.assertTrue(returnMessage.contains(message));
			}
		} catch (Exception e) {
			log.info("fail to config alarm Delivery");
			log.info("Start to clean up");
		}
	}

}

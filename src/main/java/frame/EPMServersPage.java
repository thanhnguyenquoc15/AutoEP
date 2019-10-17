package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lib.BrowserFactory;
import lib.ReadData;
import scripts.HelperClass;



public class EPMServersPage extends scripts.HelperClass{


	@Override
	public void performBeforeMethodOperation() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void performAfterMethodOperation() {
		// TODO Auto-generated method stub
		
	}
	
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
//	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
//	WebDriver driver = HelperClass.driver;
	
	//Get button element in EPM Servers pages
	public WebElement button(String buttonName) {
		WebElement checkBoxAllRole = driver.findElement(By.xpath("//input[@value='" + buttonName + "']"));
		return checkBoxAllRole;
	}


	//fill in Group Name and Recipient Email Addresses using Group order Number
	public void configAlarmDelivery(int groupOrder, String groupName, String recipientEmails)
	{
		log.debug("Entering into Methos configAlarmDelivery");
		
		WebElement name = driver.findElement(By.xpath("//*[@id='alarmDeliveryConfigForm:displayNameGroup"+groupOrder+"']"));
		name.clear();
		name.sendKeys(groupName);
		WebElement emails = driver.findElement(By.xpath("//*[@id='alarmDeliveryConfigForm:addressesGroup"+groupOrder+"']"));
		emails.clear();
		emails.sendKeys(recipientEmails);
	}


	
}

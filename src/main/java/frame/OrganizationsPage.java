package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;

public class OrganizationsPage {
	
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	// Page Objects
	//method to choose organization
	public WebElement orgName(String oName) {
		WebElement orgName = driver.findElement(By.xpath("//a[text()=\"" + oName + "\"]"));
		return orgName;
	}
	
	//method to change password longevity for organization
	public WebElement penOrgName(String oName) {
		WebElement penOrgName = driver.findElement(
				By.xpath("//a[text()=\"" + oName + "\"]/../../td[4]/table/tbody/tr/td[3]/a"));
		return penOrgName;
	}
	
    //method to choose check box organization
	public WebElement checkboxOrgName(String oName) {
		WebElement checkboxOrgName = driver
				.findElement(By.xpath("//a[text()=\"" + oName + "\"]/../..//input[@type=\"checkbox\"]"));
		return checkboxOrgName;
	}
	
	//method to use buttons - Add/Delete/Help
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}

	//method - choose descending order to organization by name
	public WebElement nameDscOrg() {
		WebElement nameDscOrg = driver.findElement(By.xpath("//input[@id=\"organizationForm:orgsList:name_Dsc\"]"));
		return nameDscOrg;
	}

	//method - choose ascending order to organization by name
	public WebElement nameAscOrg() {
		WebElement nameAscOrg = driver.findElement(By.xpath("//input[@id=\"organizationForm:orgsList:name_Asc\"]"));
		return nameAscOrg;
	}
	
	//method - check all organizations
	public WebElement checkboxAllOrg() {
		WebElement checkboxAllOrg = driver.findElement(By.xpath("//input[@id=\"organizationForm:orgsList:allDeleted\"]"));
		return checkboxAllOrg;
	}
	
	//method - input name organization when create a new organization
	public WebElement inputNameOrg() {
		WebElement inputNameOrg = driver.findElement(By.xpath("//input[@id=\"organization:orgName\"]"));
		return inputNameOrg;
	}
	
	//method - select system password longevity for organization when create a new organization
	public WebElement sysPwdLongRole() {
		WebElement sysPwdLongRole = driver.findElement(By.xpath("//input[@id=\"organization:enforcePwdLongevity:0\"]"));
		return sysPwdLongRole;
	}

	//method - select custom password longevity for organization when create a new organization
	public WebElement cusPwdLongRole() {
		WebElement cusPwdLongRole = driver.findElement(By.xpath("//input[@id=\"organization:enforcePwdLongevity:1\"]"));
		return cusPwdLongRole;
	}

	//method - set days for custom password longevity for organization when create a new organization
	public WebElement dayPwdLongRole() {
		WebElement dayPwdLongRole = driver.findElement(By.xpath("//input[@id=\"organization:passwordLongevity\"]"));
		return dayPwdLongRole;
	}
	
	//method to select Zone for Organization	
	public WebElement selectZoneOrg(String zName) { 
		WebElement selectZoneOrg = driver.findElement(By.xpath("//label[text()=\"" + zName + "\"]/input"));
		return selectZoneOrg; 
	}
	  
	//method to set Reserved SIP Calls Zone (None/Minimum/Maximum) for Organization	
	public WebElement reservedSIPCallsZoneOrg(String zName, String option) { 
		WebElement reservedSIPCallsZoneOrg = driver.findElement(By.xpath("//span[text()=\"" + zName + "\"]/..//img[@alt=\"Collapse\"]/../../../../../../../..//input[@value=\"" + option + "\"]"));
		return reservedSIPCallsZoneOrg; 
	}
	
	//method to set Requested Zone for Organization	
	public WebElement requestedZoneOrg(String zName) { 
		WebElement requestedZoneOrg = driver.findElement(By.xpath("//span[text()=\"" + zName + "\"]/..//img[@alt=\"Collapse\"]/../../../../../../../..//input[@type=\"text\"]"));
		return requestedZoneOrg; 
	}
		
	//method - assign system for organization in change Password Longevity page
	public WebElement pwdLongevitySysOrg() {
		WebElement pwdLongevitySysOrg = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:0\"]"));
		return pwdLongevitySysOrg;
	}
		
	//method - assign custom for organization in change Password Longevity page
	public WebElement pwdLongevityCusOrg() {
		WebElement pwdLongevityCusOrg = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:1\"]"));
		return pwdLongevityCusOrg;
	}
		
	//method - set days for organization in change Password Longevity page
	public WebElement pwdLongevityOrg() {
		WebElement pwdLongevityOrg = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:passwordLongevity\"]"));
		return pwdLongevityOrg;
	}
	
	
}
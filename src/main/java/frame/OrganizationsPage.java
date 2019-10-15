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
	
	/*
	 ***********The WebElement of Organizations Page*********************
	 */
	// Choose organization
	public WebElement orgName(String oName) {
		WebElement orgName = driver.findElement(By.xpath("//a[text()=\"" + oName + "\"]"));
		return orgName;
	}
	
	// Change password longevity for organization
	public WebElement penOrgName(String oName) {
		WebElement penOrgName = driver.findElement(
				By.xpath("//a[text()=\"" + oName + "\"]/../../td[4]/table/tbody/tr/td[3]/a"));
		return penOrgName;
	}
	
    // Choose check box organization
	public WebElement checkboxOrgName(String oName) {
		WebElement checkboxOrgName = driver
				.findElement(By.xpath("//a[text()=\"" + oName + "\"]/../..//input[@type=\"checkbox\"]"));
		return checkboxOrgName;
	}
	
	// Use buttons - Add/Delete/Help
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}

	// Choose descending order to organization by name
	public WebElement nameDscOrg() {
		WebElement nameDscOrg = driver.findElement(By.xpath("//input[@id=\"organizationForm:orgsList:name_Dsc\"]"));
		return nameDscOrg;
	}

	// Choose ascending order to organization by name
	public WebElement nameAscOrg() {
		WebElement nameAscOrg = driver.findElement(By.xpath("//input[@id=\"organizationForm:orgsList:name_Asc\"]"));
		return nameAscOrg;
	}
	
	// Check all organizations
	public WebElement checkboxAllOrg() {
		WebElement checkboxAllOrg = driver.findElement(By.xpath("//input[@id=\"organizationForm:orgsList:allDeleted\"]"));
		return checkboxAllOrg;
	}
	
	/*
	 ***********The WebElement of Add Organizations Page*********************
	 */
	// Input name organization when create a new organization
	public WebElement inputNameOrg() {
		WebElement inputNameOrg = driver.findElement(By.xpath("//input[@id=\"organization:orgName\"]"));
		return inputNameOrg;
	}
	
	// Select system password longevity for organization when create a new organization
	public WebElement sysPwdLongRole() {
		WebElement sysPwdLongRole = driver.findElement(By.xpath("//input[@id=\"organization:enforcePwdLongevity:0\"]"));
		return sysPwdLongRole;
	}

	// Select custom password longevity for organization when create a new organization
	public WebElement cusPwdLongRole() {
		WebElement cusPwdLongRole = driver.findElement(By.xpath("//input[@id=\"organization:enforcePwdLongevity:1\"]"));
		return cusPwdLongRole;
	}

	// Set days for custom password longevity for organization when create a new organization
	public WebElement dayPwdLongRole() {
		WebElement dayPwdLongRole = driver.findElement(By.xpath("//input[@id=\"organization:passwordLongevity\"]"));
		return dayPwdLongRole;
	}
	
	// Select Zone for Organization	
	public WebElement selectZoneOrg(String zName) { 
		WebElement selectZoneOrg = driver.findElement(By.xpath("//label[text()=\"" + zName + "\"]/input"));
		return selectZoneOrg; 
	}
	  
	//  Set Reserved SIP Calls Zone (None/Minimum/Maximum) for Organization	
	public WebElement reservedSIPCallsZoneOrg(String zName, String option) { 
		WebElement reservedSIPCallsZoneOrg = driver.findElement(By.xpath("//span[text()=\"" + zName + "\"]/..//img[@alt=\"Collapse\"]/../../../../../../../..//input[@value=\"" + option + "\"]"));
		return reservedSIPCallsZoneOrg; 
	}
	
	// Set Requested Zone for Organization	
	public WebElement requestedZoneOrg(String zName) { 
		WebElement requestedZoneOrg = driver.findElement(By.xpath("//span[text()=\"" + zName + "\"]/..//img[@alt=\"Collapse\"]/../../../../../../../..//input[@type=\"text\"]"));
		return requestedZoneOrg; 
	}
	
	/*
	 ***********The WebElement of Change Password Longevity Organizations Page*********************
	 */
	// Assign system for organization in change Password Longevity page
	public WebElement pwdLongevitySysOrg() {
		WebElement pwdLongevitySysOrg = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:0\"]"));
		return pwdLongevitySysOrg;
	}
		
	// Assign custom for organization in change Password Longevity page
	public WebElement pwdLongevityCusOrg() {
		WebElement pwdLongevityCusOrg = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:1\"]"));
		return pwdLongevityCusOrg;
	}
		
	// Set days for organization in change Password Longevity page
	public WebElement pwdLongevityOrg() {
		WebElement pwdLongevityOrg = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:passwordLongevity\"]"));
		return pwdLongevityOrg;
	}
	
	
}
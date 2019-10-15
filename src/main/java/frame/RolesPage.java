package frame;

import org.apache.commons.math3.analysis.function.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;

import scripts.HelperClass;
import frame.RolesPage;

public class RolesPage {

//	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	WebDriver driver = HelperClass.driver;
//	driver = this.driver;
//	 
//	PageFactory.initElements(driver, this);
	
//	protected BrowserFactory BrowserFactory = PageFactory.initElements(driver, BrowserFactory.class);
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	// Page Objects

	/*
	 *********** The WebElement of Roles Page*********************
	 */
	// Choose roles
	public WebElement roleName(String rName) {
		WebElement roleName = driver.findElement(By.xpath("//a[text()=\"" + rName + "\"]"));
		return roleName;
	}

	// Change password longevity for roles
	public WebElement penRoleName(String rName) {
		WebElement penRoleName = driver.findElement(
				By.xpath("//a[text()=\"" + rName + "\"]/../../td[@class=\"centercolumn\"]/table/tbody/tr/td[3]/a"));
		return penRoleName;
	}

	// Choose check box roles
	public WebElement checkboxRoleName(String rName) {
		WebElement checkBoxRoleName = driver
				.findElement(By.xpath("//a[text()=\"" + rName + "\"]/../..//input[@type=\"checkbox\"]"));
		return checkBoxRoleName;
	}

	// Use buttons - Add/Delete/Help
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}

	// Choose descending order to role by name
	public WebElement nameDscRole() {
		WebElement nameDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Dsc\"]"));
		return nameDscRole;
	}

	// Choose ascending order to role by name
	public WebElement nameAscRole() {
		WebElement nameAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Asc\"]"));
		return nameAscRole;
	}

	// Choose descending order to role by type
	public WebElement typeDscRole() {
		WebElement typeDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Dsc\"]"));
		return typeDscRole;
	}

	// Choose ascending order to role by name
	public WebElement typeAscRole() {
		WebElement typeAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Asc\"]"));
		return typeAscRole;
	}

	// Check all roles
	public WebElement checkboxAllRole() {
		WebElement checkboxAllRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:allDeleted\"]"));
		return checkboxAllRole;
	}

	/*
	 *********** The WebElement of Add Roles Page*********************
	 */
	// Input name role 
	public WebElement inputNameRole() {
		WebElement inputNameRole = driver.findElement(By.xpath("//input[@id=\"role:roleName\"]"));
		return inputNameRole;
	}

	// Enable organization for role 
	public WebElement enableOrgRole(String value) {
		WebElement enableOrgRole = driver.findElement(By.xpath("//input[@value=\"" + value + "\"]"));
		return enableOrgRole;
	}

	// Select organization for role 
	public WebElement selectOrgRole() {
		WebElement selectOrgRole = driver.findElement(By.xpath("//select[@id=\"role:organizationSelect\"]"));
		return selectOrgRole;
	}

	// Select system/organization password longevity for role 
	public WebElement sysPwdLongRole() {
		WebElement sysPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:enforcePwdLongevity:0\"]"));
		return sysPwdLongRole;
	}

	// Select custom password longevity for role 
	public WebElement cusPwdLongRole() {
		WebElement cusPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:enforcePwdLongevity:1\"]"));
		return cusPwdLongRole;
	}

	// Set days for custom password longevity for role
	public WebElement dayPwdLongRole() {
		WebElement dayPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:passwordLongevity\"]"));
		return dayPwdLongRole;
	}

	// Select start with role for role when create a new role
	public WebElement startWithRole() {
		WebElement startWithRole = driver.findElement(By.xpath("//select[@id=\"role:startWith\"]"));
		return startWithRole;
	}

	// Expand all role when create a new role
	public WebElement expandLinkRole() {
		WebElement expandLinkRole = driver.findElement(By.xpath("//a[@id=\"roleEdit:expandlink\"]"));
		return expandLinkRole;
	}

	// Collapse all role when create a new role
	public WebElement collapseLinkRole() {
		WebElement collapseLinkRole = driver.findElement(By.xpath("//a[@id=\"roleEdit:collapselink\"]"));
		return collapseLinkRole;
	}

	/*
	 *********** The WebElement of Change Password Longevity Roles Page*********************
	 */
	// Assign system for role in change Password Longevity page
	public WebElement pwdLongevitySysRole() {
		WebElement pwdLongevitySysRole = driver
				.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:0\"]"));
		return pwdLongevitySysRole;
	}

	// Assign custom for role in change Password Longevity page
	public WebElement pwdLongevityCusRole() {
		WebElement pwdLongevityCusRole = driver
				.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:1\"]"));
		return pwdLongevityCusRole;
	}

	// Set days for role in change Password Longevity page
	public WebElement pwdLongevityRole() {
		WebElement pwdLongevityRole = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:passwordLongevity\"]"));
		return pwdLongevityRole;
	}

}

package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;

public class RolesPage {

	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	// Page Objects
	
	//method to choose roles
	public WebElement roleName(String rName) {
		WebElement roleName = driver.findElement(By.xpath("//a[text()=\"" + rName + "\"]"));
		return roleName;
	}
	
	//method to change password longevity for roles
	public WebElement penRoleName(String rName) {
		WebElement penRoleName = driver.findElement(
				By.xpath("//a[text()=\"" + rName + "\"]/../../td[@class=\"centercolumn\"]/table/tbody/tr/td[3]/a"));
		return penRoleName;
	}
	
    //method to choose check box roles
	public WebElement checkboxRoleName(String rName) {
		WebElement checkBoxRoleName = driver
				.findElement(By.xpath("//a[text()=\"" + rName + "\"]/../..//input[@type=\"checkbox\"]"));
		return checkBoxRoleName;
	}
	
	//method to use buttons - Add/Delete/Help
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}
	
	//method - choose descending order to role by name
	public WebElement nameDscRole() {
		WebElement nameDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Dsc\"]"));
		return nameDscRole;
	}
	
	//method - choose ascending order to role by name
	public WebElement nameAscRole() {
		WebElement nameAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Asc\"]"));
		return nameAscRole;
	}
	
	//method - choose descending order to role by type
	public WebElement typeDscRole() {
		WebElement typeDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Dsc\"]"));
		return typeDscRole;
	}	

	//method - choose ascending order to role by name
	public WebElement typeAscRole() {
		WebElement typeAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Asc\"]"));
		return typeAscRole;
	}

	//method - check all roles
	public WebElement checkboxAllRole() {
		WebElement checkboxAllRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:allDeleted\"]"));
		return checkboxAllRole;
	}

	//method - input name role when create a new role
	public WebElement inputNameRole() {
		WebElement inputNameRole = driver.findElement(By.xpath("//input[@id=\"role:roleName\"]"));
		return inputNameRole;
	}

	//method - enable organization for role when create a new role Yes/No
	public WebElement enableOrgRole(String value) {
		WebElement enableOrgRole = driver.findElement(By.xpath("//input[@value=\"" + value + "\"]"));
		return enableOrgRole;
	}

	//method - select organization for role when create a new role
	public WebElement selectOrgRole() {
		WebElement selectOrgRole = driver.findElement(By.xpath("//select[@id=\"role:organizationSelect\"]"));
		return selectOrgRole;
	}

	//method - select system/organization password longevity for role when create a new role
	public WebElement sysPwdLongRole() {
		WebElement sysPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:enforcePwdLongevity:0\"]"));
		return sysPwdLongRole;
	}

	//method - select custom password longevity for role when create a new role
	public WebElement cusPwdLongRole() {
		WebElement cusPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:enforcePwdLongevity:1\"]"));
		return cusPwdLongRole;
	}

	//method - set days for custom password longevity for role when create a new role
	public WebElement dayPwdLongRole() {
		WebElement dayPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:passwordLongevity\"]"));
		return dayPwdLongRole;
	}

	//method - select start with role for role when create a new role
	public WebElement startWithRole() {
		WebElement startWithRole = driver.findElement(By.xpath("//select[@id=\"role:startWith\"]"));
		return startWithRole;
	}

	//method - expand all role when create a new role
	public WebElement expandLinkRole() {
		WebElement expandLinkRole = driver.findElement(By.xpath("//a[@id=\"roleEdit:expandlink\"]"));
		return expandLinkRole;
	}
	
	//method - collapse all role when create a new role
	public WebElement collapseLinkRole() {
		WebElement collapseLinkRole = driver.findElement(By.xpath("//a[@id=\"roleEdit:collapselink\"]"));
		return collapseLinkRole;
	}

	//method - assign system for role in change Password Longevity page
	public WebElement pwdLongevitySysRole() {
		WebElement pwdLongevitySysRole = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:0\"]"));
		return pwdLongevitySysRole;
	}
	
	//method - assign custom for role in change Password Longevity page
	public WebElement pwdLongevityCusRole() {
		WebElement pwdLongevityCusRole = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:1\"]"));
		return pwdLongevityCusRole;
	}
	
	//method - set days for role in change Password Longevity page
	public WebElement pwdLongevityRole() {
		WebElement pwdLongevityRole = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:passwordLongevity\"]"));
		return pwdLongevityRole;
	}
	

	// Method to Delete Role
	public void nameDscRole_test() {
		log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			nameDscRole().click();
			Thread.sleep(500);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}	
	
	// Method to Delete All Role
	/*
	 * public void deleteAllRole() { log.info("Detele All Role");
	 * checkboxAllRole.click(); Button("Delete").click(); }
	 */	

} 

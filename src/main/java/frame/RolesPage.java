package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;

public class RolesPage {

	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	
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
	
	//Element to choose descending order to role by name
	WebElement nameDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Dsc\"]"));

	//Element to choose ascending order to role by name
	WebElement nameAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Asc\"]"));

	//Element to choose descending order to role by type
	WebElement typeDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Dsc\"]"));

	//Element to choose ascending order to role by name
	WebElement typeAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Asc\"]"));

	//Element to check all roles
	WebElement checkboxAllRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:allDeleted\"]"));

	//Element to input name role when create a new role
	WebElement inputNameRole = driver.findElement(By.xpath("//input[@id=\"role:roleName\"]"));

	//Element to enable organization for role when create a new role
	WebElement enableOrgRole = driver.findElement(By.xpath("//input[@id=\"role:orgEnable:0\"]"));

	//Element to disable organization for role when create a new role
	WebElement disableOrgRole = driver.findElement(By.xpath("//input[@id=\"role:orgEnable:1\"]"));

	//Element to select organization for role when create a new role
	WebElement selectOrgRole = driver.findElement(By.xpath("//select[@id=\"role:organizationSelect\"]"));

	//Element to select system/organization password longevity for role when create a new role
	WebElement sysPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:enforcePwdLongevity:0\"]"));

	//Element to select custom password longevity for role when create a new role
	WebElement cusPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:enforcePwdLongevity:1\"]"));

	//Element to set days for custom password longevity for role when create a new role
	WebElement dayPwdLongRole = driver.findElement(By.xpath("//input[@id=\"role:passwordLongevity\"]"));

	//Element to select start with role for role when create a new role
	WebElement startWithRole = driver.findElement(By.xpath("//select[@id=\"role:startWith\"]"));

	//Element to expand all role when create a new role
	WebElement expandLinkRole = driver.findElement(By.xpath("//a[@id=\"roleEdit:expandlink\"]"));
	
	//Element to collapse all role when create a new role
	WebElement collapseLinkRole = driver.findElement(By.xpath("//a[@id=\"roleEdit:collapselink\"]"));

	//Element to assign system for role in change Password Longevity page
	WebElement pwdLongevitySysRole = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:0\"]"));
	
	//Element to assign custom for role in change Password Longevity page
	WebElement pwdLongevityCusRole = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:enforcePwdLongevity:1\"]"));
	
	//Element to set days for role in change Password Longevity page
	WebElement pwdLongevityRole = driver.findElement(By.xpath("//input[@id=\"pwdLongevity:passwordLongevity\"]"));

	
	
	// Method to Delete Role
	public void deleteRole(String nameRole) {
		log.info("Detele Role");

		checkboxRoleName(nameRole).click();
		Button("Delete").click();
	}	
	
	// Method to Delete All Role
	public void deleteAllRole() {
		log.info("Detele Role");
		checkboxAllRole.click();
		//checkBoxRoleName(nameRole).click();
		Button("Delete").click();
	}	

} 

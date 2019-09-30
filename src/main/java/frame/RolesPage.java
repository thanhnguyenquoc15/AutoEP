package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;
import frame.RolesPage;

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
    //method to choose check boxs Dynamic
	public WebElement checkBoxRoleName(String rName) {
		WebElement checkBoxRoleName = driver
				.findElement(By.xpath("//a[text()=\"" + rName + "\"]/../..//input[@type=\"checkbox\"]"));
		return checkBoxRoleName;
	}
	//method to use buttons - Add/Delete/Help
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}
	//method to choose descending order to role by name
	public WebElement nameDscRole() {
		WebElement nameDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Dsc\"]"));
		return nameDscRole;
	}
	//method to choose ascending order to role by name
	public WebElement nameAscRole() {
		WebElement nameAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:name_Asc\"]"));
		return nameAscRole;
	}
	//method to choose descending order to role by type
	public WebElement typeDscRole() {
		WebElement typeDscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Dsc\"]"));
		return typeDscRole;
	}
	//method to choose ascending order to role by name
	public WebElement typeAscRole() {
		WebElement typeAscRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:type_Asc\"]"));
		return typeAscRole;
	}
	//method to check all roles
	public WebElement checkBoxAllRole() {
		WebElement checkBoxAllRole = driver.findElement(By.xpath("//input[@id=\"rolesList:roleList:allDeleted\"]"));
		return checkBoxAllRole;
	}
	
	
	// Method to Delete Role
	public void deleteRole(String nRole) {
		log.info("Detele Role");

		checkBoxRoleName(nRole).click();
		Button("Delete").click();
	}	
	
	// Method to Delete All Role
	public void deleteAllRole() {
		log.info("Detele Role");
		checkBoxAllRole().click();
		//checkBoxRoleName(nameRole).click();
		Button("Delete").click();
	}	
	
	// hello every body, today, i eo lam gi het

} 

package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lib.BrowserFactory;
import lib.ReadData;

public class UsersPage {
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	protected Logger log = Logger.getLogger(this.getClass().getName());
       //method to select User
	  public WebElement userName(String uName) {
		WebElement userName = driver.findElement(By.xpath("//a[text()=\"" + uName + "\"]"));
		return userName;
		}
	
       //method to choose check box Dynamic
	  public WebElement checkBoxUserName(String uName) {
			WebElement checkBoxUserName = driver.findElement(By.xpath("//a[text()=\"" + uName + "\"]/../..//input[@type=\"checkbox\"]"));
			return checkBoxUserName;
			}	
		//method to use buttons - Add/Delete/Help
	  public WebElement Button(String buttonName) {
			WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
			return Button;
		}
	  //Add User Pageo
	  public WebElement checkRoleUser(String rName) {
			WebElement checkRoleUser = driver.findElement(By.xpath("//input[@id=\"role_" + rName + "\"]"));
			return checkRoleUser;
		}
	  //name user
	  public WebElement user_Name() {
			WebElement user_Name = driver.findElement(By.xpath("//input[@id=\"userOpts:username\"]"));
			return user_Name;
		}
	  
	  //Method add user
	  public void add_new_user(String name,
			  				   String enable,
			  				   String role,
			  				   String pass,
			  				   String verify,
			  				   String enforce) {
			log.info("Add New User");
		    Button("Add").click();
		    user_Name().sendKeys(name);		//Name User
		    //enable.click();				//Enable User
		    //Set Role for User
		    String select[] = role.split("\\|");	//select[0]=deptadmin, select[1]=deptauditor
			for (int j = 0; j < select.length; j++)
			{
				checkRoleUser(select[j]).click();
			}
			
		   
		    
		    
		}
}

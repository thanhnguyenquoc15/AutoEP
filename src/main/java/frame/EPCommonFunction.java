package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lib.BrowserFactory;
import lib.ReadData;
		

public class EPCommonFunction {

	private String menuFrame = "menu";
	
	private String mainFrame = "main";
	
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	
	// select the page to handle from EPM Menu Bar	
		public void switchEPMenu(String page) {
			log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
			try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(menuFrame);
			Thread.sleep(500);
			driver.findElement(By.partialLinkText(page)).click();
			Thread.sleep(500);
			//add 2 lines to change to main frame 
			driver.switchTo().defaultContent();
			driver.switchTo().frame(mainFrame);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}				
			}
		
		public void switchFrame(String frame) {
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frame);
			
		}
	
	
}

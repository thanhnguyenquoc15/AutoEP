package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import lib.BrowserFactory;
import lib.ReadData;
		

public class EPCommonFunction {

	private String menuFrame = "menu";
	
	private String mainFrame = "main";
	
	private String headerFrame = "header";
	
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	
	
	
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
		
	/* 
	 * @author: tqnguyen@avaya.com
	 * @last modified date:
	 * @desc opens a modal window to display a message
	 * @param box: the selected box
	 * @param attribute: the attribute that would be selected in the box
	 * @return bool - success or failure
	 */
		public  void selectMultiElementsBox(WebElement box,String attribute) {
			try
			{
				Select select = new Select(box);
				select.selectByValue(attribute);
			}
			catch(Exception e)
			{
				log.info("fail to select "+ attribute);
			}
			
		}

}

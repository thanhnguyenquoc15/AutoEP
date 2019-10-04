package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
		
		public  void switchFrame(String frame) {
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frame);
			
		}
		
		/**
		 * This method is used to wait execution or process till user defined time.
		 *
		 * @param    secs time specified in seconds
		 * @return   Noting .
		 *
		 */
		public void wait(int secs)
		{
			// call a native sleep
			try
			{
				log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
				Thread.sleep(secs * 1000);
			} catch (Exception ex)
			{
				log.error("Exception occurred in {createDIR} method...");
			}
		}
		
		/**
		 This method is used to get selected dropdown option.
		 *
		 * @param   driver Selenium WebDriver object
		 * @param   element Dropdown/Combo box object
		 * @return   String - returns selected value from dropdown .
		 *
		 */
		public String getDropDownOption(WebDriver driver, WebElement element)
		{
			log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
			// GetText from the drop down list
			String selectedOption = new Select(element).getFirstSelectedOption().getText();
			// return selection option
			return (selectedOption);
		}


		/**
		 This method is used to select value from dropdown list.
		 *
		 * @param   driver Selenium WebDriver object
		 * @param   element Dropdown/Combo box object
		 * @param   sItem Item to select from dropdown
		 *
		 * @return  String - returns selected value from dropdown .
		 *
		 */
		public boolean selectDropDownOption(WebDriver driver, WebElement element, String sItem)
		{
			log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
			try
			{
				Select selectedOption = new Select(element);
				//selectedOption.selectByValue(sItem);
				selectedOption.selectByVisibleText(sItem);
				return true;
			} catch (Exception e)
			{
				//new ExceptionHandler(e,Thread.currentThread().getStackTrace()[1].getMethodName());
				return false;
			}
		}
		
		
		public void selectDropDownOption(WebElement element, String sItem)
		{
			log.info("---------------------------------------------------------------------------------------------------------------------------------------");
			log.info("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Select selectedOption = new Select(element);
			for (WebElement optionElement : selectedOption.getOptions())
			{
				//log.info(sItem+" "+optionElement.getText());
				if (optionElement.getText().toLowerCase().contains(sItem.toLowerCase().replaceAll("\"", "")))
				{
					sItem = optionElement.getText();
					log.info(sItem);
					break;
				}
			}
			selectedOption.selectByVisibleText(sItem);
			log.info("---------------------------------------------------------------------------------------------------------------------------------------");
		}

	
	//writing method
}

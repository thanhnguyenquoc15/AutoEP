package frame;

import java.io.File;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


import lib.BrowserFactory;
import lib.ReadData;

import lib.SSHCommand;

import scripts.HelperClass;

		

public class EPCommonFunction {

	private String menuFrame = "menu";
	
	private String mainFrame = "main";
	
	private String headerFrame = "header";
	
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	
	protected Logger log = Logger.getLogger(this.getClass().getName());

	
	
	
	

	//WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);

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
		/***
		 * Method to delete file.
		 *  @param file
		 */
		public void DeleteFile(String file)
		{
			File f1 = new File(file);
			boolean result = f1.delete();
			if (!result)
			{
				log.info("File deletion failed : " + file);
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

		
		//Method to select multidrop down option 
		public void selectMultiDropDownOption(WebElement element, String sItem)
		{
			log.info("---------------------------------------------------------------------------------------------------------------------------------------");
			log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
			// try{
			Select selectedOption = new Select(element);
			//selectedOption.selectByValue(sItem);
			selectedOption.deselectAll();
			selectedOption.selectByVisibleText(sItem);
		}
	

		/**
		 * This method is used to verify specified object exists or not.
		 *
		 * @param     driver Selenium WebDriver object
		 * @param     by Selenium By object which contains xpath/ID/Name details
		 * @param     secs Sepcify time in seconds to waitfor
		 *
		 * @return    WebElement object
		 *
		 */
		public WebElement eXISTs(By by, int secs)
		{
			log.info("---------------------------------------------------------------------------------------------------------------------------------------");
			ReadData.WEBDRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// Declare a dynamic element
			WebElement dynaElement = null;
			try
			{
				log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
				// Declare an explicit wait
				WebDriverWait wait1 = new WebDriverWait(ReadData.WEBDRIVER, secs);
				dynaElement = (new WebDriverWait(ReadData.WEBDRIVER,
				                                 secs)).until(ExpectedConditions.presenceOfElementLocated(by));
				ReadData.WEBDRIVER.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				return (dynaElement);
			} catch (Exception e)
			{
				ReadData.WEBDRIVER.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//log.debug ("Exception occurred in eXISTs method...");
				return dynaElement;
			}
		}
		
		// method to restart service
		public void restartServices() throws SQLException
		{
			log.info("---------------------------------------------------------------------------------------------------------------------------------------");
			log.info("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
			SSHCommand sshObj = new SSHCommand();
			sshObj.connect(ReadData.EPServer, ReadData.EPUser_SSH, ReadData.EPPass_SSH);
			//String actmq = "ps -ef | grep /opt/coreservices/activemq-4.0.1/bin/run.jar | grep -v grep | awk '{print $2}' | xargs kill -9";
			String cmd = "service vpms restart";
			String cmd1 = "service appserver restart";
			String cmd2 = "service mpp restart";
			log.info(cmd);
			sshObj.exec(cmd);
			sshObj.exec(cmd2);
			sshObj.exec(cmd2);
			wait(60);
		}


	//method to check log 



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
					select.deselectAll();
//					select.selectByValue(attribute);
					select.selectByVisibleText(attribute);
				}
				catch(Exception e)
				{
					log.info("fail to select "+ attribute);
				}
				
			}
		
	//writing method 2

}

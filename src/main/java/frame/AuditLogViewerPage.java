package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;


public class AuditLogViewerPage {


	protected Logger log = Logger.getLogger(this.getClass().getName());
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
//	WebDriver driver = HelperClass.driver;
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	
	
	
	private static String zonesBox = "auditLogViewerZone";
	private static String categoriesBox = "logOptionsCatNames";
	private static String actionsBox = "logOptionsActionNames";
	private static String lastXDays = "lastXDays";
	private static String lastXOptions = "lastXOptions";
	private static String searchKeys = "auditkeywords";

	private static String sortBy = "logOptionsSortBy";
	
	public WebElement elements(String elementsBox) {
		WebElement checkBox = driver.findElement(By.xpath("//*[@id='auditLogViewerForm:" + elementsBox + "']"));
		return checkBox;
	}
	
	//generate Audit Log Report using multiple parameters in the Audit Log Viewer Page 
	public void generateAuditLogs(	String zones, 
									String categories, 
									String actions,
									String searchKeywords,
									String sort,
									String predefinedValues,
									String last,
									String periodTimes,
									String startDate,
									String endDate)
		{
		try
		{
		ComFunc.selectMultiElementsBox(elements(zonesBox), zones);
		ComFunc.selectMultiElementsBox(elements(categoriesBox), categories);
		ComFunc.selectMultiElementsBox(elements(actionsBox), actions);
		elements(searchKeys).sendKeys(searchKeywords);
		elements(sortBy).sendKeys(sort);
		elements(lastXDays).clear();
		elements(lastXDays).sendKeys(last);
		elements(lastXOptions).sendKeys("Hours");
		elements("ok").click();
		}
		catch(Exception e)
		{
			log.info("fail to generate Audit Log ");
		}			
	}	
	
	
}

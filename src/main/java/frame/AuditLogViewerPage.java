package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;

public class AuditLogViewerPage {

	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);

	protected Logger log = Logger.getLogger(this.getClass().getName());
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	
	private static String zonesBox = "auditLogViewerZone";
	private static String categoriesBox = "logOptionsCatNames";
	private static String actionsBox = "logOptionsActionNames";

	
	private WebElement searchKeys = driver.findElement(By.xpath("//input[@id=\"auditLogViewerForm:auditkeywords\"]"));
	
	private static String sortBy = "logOptionsSortBy";
	
	public WebElement elementsBox(String elementsBox) {
		WebElement checkBox = driver.findElement(By.xpath("select[@id=\"auditLogViewerForm:" + elementsBox + "']"));
		return checkBox;
	}
	
	
	//generate Audit Log Report using multiple parameters in the Audit Log Viewer Page 
	public void generateAuditLogs(	String zones, 
									String categories, 
									String actions,
									String keywords,
									String sortBy,
									String predefinedValues,
									String lastXDays,
									String periodTimes,
									String startDate,
									String endDate)
		{
		ComFunc.selectMultiElementsBox(elementsBox(zonesBox), zones);
		ComFunc.selectMultiElementsBox(elementsBox(categoriesBox), categories);
		ComFunc.selectMultiElementsBox(elementsBox(actionsBox), actions);
		searchKeys.sendKeys(keywords);
		elementsBox(sortBy).sendKeys("");
		
		
		}
	
	
}

package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import lib.BrowserFactory;
import lib.ReadData;

public class AuditLogViewerPage {


	protected Logger log = Logger.getLogger(this.getClass().getName());
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	
	
	//generate Audit Log Report using multiple parameters in the Audit Log Viewer Page 
	public void generateAuditLogs(	String zones, 
									String categories, 
									String actions,
									String searchKeywords,
									String sortBy,
									String predefinedValues,
									String lastXDays,
									String periodTimes,
									String startDate,
									String endDate)
		{
		
	}
	
	
}

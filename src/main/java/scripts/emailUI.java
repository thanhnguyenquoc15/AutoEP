package scripts;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import bsh.ParseException;
import frame.EPCommonFunction;
import frame.EPLoginPage;
import frame.EPMServersPage;
import frame.RolesPage;
import frame.UsersPage;
import lib.ReadData;

public class emailUI extends HelperClass{

	protected EPLoginPage loginPage = PageFactory.initElements(driver, EPLoginPage.class);
	protected RolesPage rolepage = PageFactory.initElements(driver, RolesPage.class);
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	protected UsersPage userpage = PageFactory.initElements(driver, UsersPage.class);
	protected Logger log = Logger.getLogger(this.getClass().getName());
	protected EPMServersPage EPMServers = PageFactory.initElements(driver, EPMServersPage.class);
	
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void emailAddresses(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		
		log.info("Test Data is: "+ testData);
		//Access EPM Servers > Alarm Codes > Change Alarm Delivery Settings
		ComFunc.switchEPMenu("EPM Servers");
		EPMServers.button("Alarm Codes").click();
		EPMServers.button("Delivery Settings").click();
		
		
		//add multiple valid email addresses to each group and select Save.
		EPMServers.configAlarmDelivery(1, "1st Group", "nqthanh1@tma.com.vn");
		
		//+ Verify the email address list for each group has been updated.
		EPMServers.button("Apply").click();
		//Verify 
		
		
		//Access Audit Log Viewer + Verify an audit log entry exists for the changes.
		ComFunc.switchEPMenu("Audit Log Viewer");
		
		
		//Access EPM Servers > Alarm Codes > Change Alarm Delivery Settings,
		//remove at least one email address from each group and select Save.
		//Access Audit Log Viewer. + Verify an audit log entry exists for the changes.
		//Access EPM Servers > Alarm Codes > Change Alarm Delivery Settings, 
		//add a non-email address to each group and select Save. + Verify the non-email address entry is not retained.

		
		
	}
}
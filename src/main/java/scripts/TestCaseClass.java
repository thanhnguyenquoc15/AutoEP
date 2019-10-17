package scripts;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import bsh.ParseException;
import frame.AuditLogViewerPage;
import frame.EPCommonFunction;
import frame.EPLoginPage;
import frame.RolesPage;
import frame.SNMPPage;
import frame.UsersPage;
import lib.ReadData;


public class TestCaseClass extends HelperClass {
	

	private static final boolean fail = false;
	protected EPLoginPage loginPageObj ;
	protected RolesPage rolePageObj ;
	protected EPCommonFunction ComFuncObj ;
	protected UsersPage userPageObj ;
	protected SNMPPage snmpPageObj ;
	protected EPLoginPage loginPage;
	protected RolesPage rolePage;
	protected EPCommonFunction ComFunc;
	protected UsersPage userPage;
	protected AuditLogViewerPage auditPage;
	

	protected Logger log = Logger.getLogger(this.getClass().getName());

//aptureScreenshot cap = new CaptureScreenshot();

	public void performBeforeMethodOperation() {
		// TODO Auto-generated method stub
		loginPage = PageFactory.initElements(driver, EPLoginPage.class);
		rolePage = PageFactory.initElements(driver, RolesPage.class);
		ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
		userPage = PageFactory.initElements(driver, UsersPage.class);
		auditPage = PageFactory.initElements(driver, AuditLogViewerPage.class);
	}


	public void performAfterMethodOperation() {
		// TODO Auto-generated method stub
		
	}
	
	
//	@Override
//	public void performBeforeMethodOperation() {
//		// TODO Auto-generated method stub
//		loginPageObj = PageFactory.initElements(driver, EPLoginPage.class);
//		rolePageObj = PageFactory.initElements(driver, RolesPage.class);
//		ComFuncObj = PageFactory.initElements(driver, EPCommonFunction.class);
//		userPageObj = PageFactory.initElements(driver, UsersPage.class);
//		snmpPageObj = PageFactory.initElements(driver, SNMPPage.class);
//	}
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class, enabled = fail)
	public void demoTestCase(Hashtable testData) throws Exception,
											        ParseException,
											        IOException
	{
	log.info("Heyyy");
	log.info("Test Data is: "+ testData);


	ComFuncObj.switchEPMenu(testData.get("page").toString());
	
	log.info("Heyyy");
	}
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class, enabled = fail)
	public void deleteRolePage(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("roles: ");
		ComFuncObj.switchEPMenu("Roles");
		ComFuncObj.switchFrame("main");
		
//	    rolepage.deleteRole(testData.get("rName").toString());
		
		

		rolePageObj.nameDscRole_test();
		
		//rolePage.deleteRole(testData.get("rName").toString());

	}
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void add_new_user(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("roles: ");

		ComFuncObj.switchEPMenu("Users");
		ComFuncObj.switchFrame("main");
       	userPageObj.add_new_user(testData.get("uName").toString(),"",
       							 testData.get("Role").toString(),"","","");

		ComFunc.switchEPMenu("Users");
		ComFunc.switchFrame("main");
        
		userPage.add_new_user(testData.get("uName").toString(),"",
				testData.get("Role").toString(),"","","");
		userPage.Button("Save");
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(screenshot, new File("D:\\DATA\\auto\\eclipse\\workspace\\AutoEP\\Screenshot\\screenshot.jpg"));
		  System.out.print("Screenshot is captured and stored in your D: Drive");
	}

	
		

	
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class, enabled = fail)
	public void setSNMPv3(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("SNMP Page ");
		snmpPageObj.SNMP_authPriv(testData.get("securityName").toString(),
							  testData.get("authPro").toString(),
							  testData.get("authPass").toString(),
							  testData.get("privPro").toString(),
							  testData.get("privPass").toString(),
							  testData.get("SNMPserver").toString(),
							  testData.get("SNMPuser").toString(),
							  testData.get("SNMPpass").toString());
	}
}
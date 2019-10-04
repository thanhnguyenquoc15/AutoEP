package scripts;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import bsh.ParseException;
import frame.EPCommonFunction;
import frame.EPLoginPage;
import frame.RolesPage;
import frame.SNMPPage;
import frame.UsersPage;
import lib.ReadData;


public class TestCaseClass extends HelperClass {
	
	protected EPLoginPage loginPage = PageFactory.initElements(driver, EPLoginPage.class);
	protected RolesPage rolePageObj = PageFactory.initElements(driver, RolesPage.class);
	protected EPCommonFunction ComFuncObj = PageFactory.initElements(driver, EPCommonFunction.class);
	protected UsersPage userPageObj = PageFactory.initElements(driver, UsersPage.class);
	//protected SNMPPage snmpPageObj = PageFactory.initElements(driver, SNMPPage.class);
	protected Logger log = Logger.getLogger(this.getClass().getName());
	SNMPPage snmpObj = new SNMPPage();
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
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
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void deleteRolePage(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("roles: ");
		ComFuncObj.switchEPMenu("Roles");
		ComFuncObj.switchFrame("main");
//	    rolepage.deleteRole(testData.get("rName").toString());
		
		
		rolePageObj.nameDscRole_test();
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
	}
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void setSNMPv3(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("SNMP Page ");
		ComFuncObj.switchEPMenu("SNMP");
		ComFuncObj.switchFrame("main");
        
		snmpObj.set_SNMP_Agent_Settings_Version3(testData.get("securityName").toString(),
				testData.get("authPro").toString(),"","","");
	}
	

	}
	
	
	


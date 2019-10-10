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
	
	protected EPLoginPage loginPageObj ;
	protected RolesPage rolePageObj ;
	protected EPCommonFunction ComFuncObj ;
	protected UsersPage userPageObj ;
	protected SNMPPage snmpPageObj ;
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	
	@Override
	public void performBeforeMethodOperation() {
		// TODO Auto-generated method stub
		loginPageObj = PageFactory.initElements(driver, EPLoginPage.class);
		rolePageObj = PageFactory.initElements(driver, RolesPage.class);
		ComFuncObj = PageFactory.initElements(driver, EPCommonFunction.class);
		userPageObj = PageFactory.initElements(driver, UsersPage.class);
		snmpPageObj = PageFactory.initElements(driver, SNMPPage.class);
	}
	
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
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
import frame.UsersPage;
import lib.ReadData;


public class TestCaseClass extends HelperClass {
	
	protected EPLoginPage loginPage = PageFactory.initElements(driver, EPLoginPage.class);
	protected RolesPage rolepage = PageFactory.initElements(driver, RolesPage.class);
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	protected UsersPage userpage = PageFactory.initElements(driver, UsersPage.class);
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void demoTestCase(Hashtable testData) throws Exception,
											        ParseException,
											        IOException
	{
	log.info("Heyyy");
	log.info("Test Data is: "+ testData);


	ComFunc.switchEPMenu(testData.get("page").toString());
	
	log.info("Heyyy");
	}
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void deleteRolePage(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("roles: ");
		ComFunc.switchEPMenu("Roles");
		ComFunc.switchFrame("main");
//	    rolepage.deleteRole(testData.get("rName").toString());
		
		
		rolepage.deleteRole(testData.get("rName").toString());
	}
	
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void add_new_user(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("roles: ");
		ComFunc.switchEPMenu("Users");
		ComFunc.switchFrame("main");
        
		userpage.add_new_user(testData.get("uName").toString(),"",testData.get("Role").toString(),"","","");
	}

	
		
	}
	
	
	


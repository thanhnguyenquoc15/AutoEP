package scripts;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
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
import lib.SSHCommands;


public class TestCaseClass extends HelperClass {
	
	protected EPLoginPage loginPageObj ;
	protected RolesPage rolePageObj ;
	protected EPCommonFunction ComFuncObj ;
	protected UsersPage userPageObj ;
	protected SNMPPage snmpPageObj ;
	protected SSHCommands sshObj ;
	protected AuditLogViewerPage auditPageObj;
	
	protected Logger log = Logger.getLogger(this.getClass().getName());

	
	

	public void performBeforeMethodOperation() {
		// TODO Auto-generated method stub
		loginPageObj = PageFactory.initElements(driver, EPLoginPage.class);
		rolePageObj = PageFactory.initElements(driver, RolesPage.class);
		ComFuncObj = PageFactory.initElements(driver, EPCommonFunction.class);
		userPageObj = PageFactory.initElements(driver, UsersPage.class);
		auditPageObj = PageFactory.initElements(driver, AuditLogViewerPage.class);
		sshObj = PageFactory.initElements(driver, SSHCommands.class);
		snmpPageObj = PageFactory.initElements(driver, SNMPPage.class);
	}


	public void performAfterMethodOperation() {
		// TODO Auto-generated method stub
		
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
		
//		rolePageObj.deleteRole(testData.get("rName").toString());
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
	public void SNMP_authPriv(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
		log.info("Reading Input details...");
		log.info("Test Data : -" + testData);
		log.info("Login AAEP and config SNMP Agent Version 3");
		String auth_Pro[] = testData.get("authPro").toString().split("\\|");
		String priv_Pro[] = testData.get("privPro").toString().split("\\|");
		for (int i = 0; i < auth_Pro.length; i++) {
			for (int j = 0; j < priv_Pro.length; j++) {
				log.info("Enable SNMPv3; set Authentication to " + auth_Pro[i] + " and Privacy to " + priv_Pro[j]);
				snmpPageObj.set_SNMP_Agent_Settings_version3(testData.get("securityName").toString(),auth_Pro[i],testData.get("authPass").toString(),priv_Pro[j],testData.get("privPass").toString());
				
				log.info("Restart avpSNMPAgentSvc");
				String restartSNMP = "service avpSNMPAgentSvc restart";
				sshObj.sshShell(restartSNMP);
				ComFuncObj.wait(1);
				
				log.info("Login external SNMP server");
				String cmd = "cd /opt/Avaya/ExperiencePortal/VPMS/SNMP/";
				String pwd = "pwd";
				String get = "bash SendSNMPRequest -h " + ReadData.EPServer + " -v 3 -c " + testData.get("securityName").toString() + " -t GET -T " + auth_Pro[i] + " -A " + testData.get("authPass").toString() + " -R " + priv_Pro[j] + " -P " + testData.get("privPass").toString();
				String getnext = "bash SendSNMPRequest -h " + ReadData.EPServer + " -v 3 -c " + testData.get("securityName").toString() + " -t GETNEXT -T " + auth_Pro[i] + " -A " + testData.get("authPass").toString() + " -R " + priv_Pro[j] + " -P " + testData.get("privPass").toString();
				log.info("Negative SNMP");
				log.info("Execute SNMP get and getnext");
				sshObj.sshToHost(testData.get("SNMPserver").toString(),testData.get("SNMPuser").toString(),testData.get("SNMPpass").toString(),cmd,pwd,get,getnext);
				ComFuncObj.wait(1);
			}
		}
	}
}
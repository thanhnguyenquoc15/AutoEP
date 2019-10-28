package scripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

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
		log.info("Start avpSNMPAgentSvc");
		String startSNMP = "service avpSNMPAgentSvc start";
		sshObj.sshShell(startSNMP);
		ComFuncObj.wait(1);
		
		log.info("Reading Input details...");
		log.info("Test Data : -" + testData);
		log.info("Login AAEP and config SNMP Agent Version 3");
		String auth_Pro[] = testData.get("authPro").toString().split("\\|");
		String priv_Pro[] = testData.get("privPro").toString().split("\\|");
		String path = ReadData.userDir + "\\logEP\\Defaultsuite.log";
		String responseGET = ReadData.EPHostname;
		String responseGETNEXT = "The physical location of this node (e.g., 'telephone closet, 3rd floor').  If the location is unknown, the value is the zero-length string. Edit the file: CATALINA_HOME/shared/classes/snmpconfig.properties to set the actual Location";
		
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
				log.info("Execute SNMP GET, GETNEXT");
				sshObj.sshToHost(testData.get("SNMPserver").toString(),testData.get("SNMPuser").toString(),testData.get("SNMPpass").toString(),cmd,get,getnext);
				ComFuncObj.wait(1);
				try 
				{
					String verify = FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8); System.out.println(verify); log.info(verify);
					log.info("-------------------------------------------------------------------------");
					//assertTrue(verify.contains(responseGET));
					assertTrue(verify.contains(responseGETNEXT));
				}  
				catch (IOException e)
				{
				  e.printStackTrace(); 
				}			 
			}
		}
	}
	
	@Test
	public void Auth_cert_user() throws Exception,JSchException, SftpException
	{
		log.info("Login SSH and create a new auth certificate user");
		//sshObj.connect();
		//ComFuncObj.wait(1);
		String source = ReadData.userDir + "\\src\\main\\java\\data\\Upload\\openssl.conf";
		String destination = "/tmp/cer/";
		String passwordCer = "1_Abc_123";
		sshObj.sshShell("mkdir /tmp/cer/");
		ComFuncObj.wait(1);
		sshObj.upload(source, destination);
		ComFuncObj.wait(1);
		sshObj.sshShell("cd /tmp/cer/", "openssl req -new -x509 -subj \"/C=VN/ST=HCM/L=PN/O=TMA/OU=CORE/CN=ep219\" -days 1094 -newkey rsa:2048 -sha256 -keyout RootCA.private.key -out rootCA.crt -config openssl.conf",
				passwordCer, passwordCer,"openssl pkcs12 -export -out rootCA.pfx -inkey RootCA.private.key -in rootCA.crt", passwordCer, passwordCer, passwordCer);
		ComFuncObj.wait(1);
	}
	
	@Test
	public void Check_tomcat_of_appserver_can_install_laster_version() throws Exception,JSchException, SftpException
	{
		//String path = ReadData.userDir + "\\logEP\\Defaultsuite.log";
		log.info("Install EPM patch latser version");
		String EPM_Patch = ReadData.userDir + "\\src\\main\\java\\data\\Upload\\EPM_7.2.3.0.0464.tar.gz";
		String EPM_Patch_SSH = "/tmp/Upgrade/EPM/";
		String mkdir_Upgrade = "mkdir /tmp/Upgrade";
		String mkdir_EPM = "mkdir /tmp/Upgrade/EPM";
		
		ComFuncObj.wait(1);
		sshObj.sshShell(mkdir_Upgrade,mkdir_EPM);
		ComFuncObj.wait(1);
		sshObj.upload(EPM_Patch, EPM_Patch_SSH);		
	}
	
}
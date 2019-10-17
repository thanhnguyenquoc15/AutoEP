package scripts;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

import bsh.ParseException;
import frame.AuditLogViewerPage;
import frame.EPCommonFunction;
import frame.EPLoginPage;
import frame.EPMServersPage;
import frame.RolesPage;
import frame.UsersPage;
import lib.ReadData;
import lib.SSHCommands;

public class emailUI extends HelperClass{

	protected EPLoginPage loginPageObj;
	protected RolesPage rolePageObj;
	protected EPCommonFunction ComFuncObj;
	protected UsersPage userPageObj;
	protected EPMServersPage EPMServersObj;
	protected AuditLogViewerPage auditLogsObj;
	protected SSHCommands ssh;
	

	public  void performBeforeMethodOperation(){
	loginPageObj = PageFactory.initElements(driver, EPLoginPage.class);
	rolePageObj = PageFactory.initElements(driver, RolesPage.class);
	ComFuncObj = PageFactory.initElements(driver, EPCommonFunction.class);
	userPageObj = PageFactory.initElements(driver, UsersPage.class);
	EPMServersObj = PageFactory.initElements(driver, EPMServersPage.class);
	auditLogsObj = PageFactory.initElements(driver, AuditLogViewerPage.class);
	ssh = PageFactory.initElements(driver, SSHCommands.class);
	
	Logger log = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
    public void performAfterMethodOperation(){
    }
	
	
	
	/* 
	 * @author: tqnguyen@avaya.com
	 * @last modified date:
	 * @desc all step follow test case email Addresses on QC 
	 */
	@Test
	(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void emailAddresses(Hashtable testData) throws Exception,
    													  ParseException,
    													  IOException 
	{
//		performBeforeMethodOperation();
		log.info("Test Data is: "+ testData);
		//Access EPM Servers > Alarm Codes > Change Alarm Delivery Settings
		ComFuncObj.switchEPMenu("EPM Servers");
		EPMServersObj.button("Alarm Codes").click();
		EPMServersObj.button("Delivery Settings").click();
		
		
		//add multiple valid email addresses to each group and select Save.
		//EPMServersObj.configAlarmDelivery("1", "1st Group", "nqthanh1@tma.com.vn;","","");
		//EPMServersObj.configAlarmDelivery("2", "2nd Group", "nqthanh1@tma.com.vn;nqthanh3@tma.com.vn;nqthanh2@tma.com.vn;","","");
		
		//Verify the email address list for each group has been updated.
		
		//Verify 
		
		
		//Access Audit Log Viewer + Verify an audit log entry exists for the changes.
		ComFuncObj.switchEPMenu("Audit Log Viewer");
		//auditLogsObj.generateAuditLogs("Default", "Alarm", "Add", "", "Time: oldest first", "", "1", "Hours", "", "");
		
		//Access EPM Servers > Alarm Codes > Change Alarm Delivery Settings,
		ComFuncObj.switchEPMenu("EPM Servers");
		EPMServersObj.button("Alarm Codes").click();
		EPMServersObj.button("Delivery Settings").click();
		//EPMServersObj.configAlarmDelivery("2", "2st Group Edited", "nqthanh2@tma.com.vn","","");
		//remove at least one email address from each group and select Save.
		EPMServersObj.button("Apply").click();
		//Access Audit Log Viewer. + Verify an audit log entry exists for the changes.
		//auditLogsObj.generateAuditLogs("Default", "Alarm", "Add", "", "Time: oldest first", "", "1", "Hours", "", "");
		//Access EPM Servers > Alarm Codes > Change Alarm Delivery Settings, 
		//add a non-email address to each group and select Save. + Verify the non-email address entry is not retained.

		
		//SSHCommand.sshConnectShell("cd /tmp","pwd","service appserver status");

		//EPMServersObj.configAlarmDelivery("2", "2st Group Edited", "nqthanh2aaaaaaa","true","");

		
		
	}





	/*
	 * @author: tqnguyen@avaya.com
	 * 
	 * @last modified date:
	 * 
	 * @desc
	 */
	@Test(dataProvider = "dataMap", dataProviderClass = ReadData.class)
	public void SSHTesting(Hashtable testData) throws Exception, ParseException, IOException {
		
//		ssh.sshConnect("service appserver status && service vpms status");
//		ssh.sshConnect("service appserver status&&service vpms status");
//		ssh.sshConnectShell("cd /tmp&&lenhTaoCert");
		//ssh.sshConnectShell("service vpms status","cd /tmp","pwd");
//		ssh.exeCmd(channel, "cd /tmp");
//		ssh.exeCmd(channel, "pwd");

	}

}


package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import lib.BrowserFactory;
import lib.ReadData;
import lib.SSHCommands;

public class SNMPPage {

	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	protected EPCommonFunction ComFuncObj = PageFactory.initElements(driver, EPCommonFunction.class);
	protected SSHCommands sshObj = PageFactory.initElements(driver, SSHCommands.class);
	//protected test testObj = PageFactory.initElements(driver, test.class);

	protected Logger log = Logger.getLogger(this.getClass().getName());
	// Page Objects

	/*
	 ***********The methods of SNMP Page*********************
	 */
	// method - use buttons - Add/Delete/Help/Test/SNMP Agent Settings/SNMP Device Notification Settings
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}
	
	// method - Host Address SNMP
	public WebElement IpAddressSNMP(String IpAddress) {
		WebElement IpAddressSNMP = driver.findElement(By.xpath("//a[text()=\"" + IpAddress + "\"]"));
		return IpAddressSNMP;
	}
	
	// method - Check box Host Address SNMP
	public WebElement checkBoxIpAddressSNMP(String IpAddress) {
		WebElement checkBoxIpAddressSNMP = driver.findElement(By.xpath("//a[text()=\"" + IpAddress + "\"]/../..//input[@type=\"checkbox\"]"));
		return checkBoxIpAddressSNMP;
	}
	
	// method - Check box All Host Address SNMP
	public WebElement checkBoxAllSNMP() {
		WebElement checkBoxAllSNMP = driver.findElement(By.xpath("//input[@id=\"snmpTrapListForm:snmpTrapListDataTable:allSelected\"]"));
		return checkBoxAllSNMP;
	}
	

	/*
	 ***********The methods of SNMP Agent Settings*********************
	 */	 
	// method to enable SNMP Version 1/2c/3
	public WebElement enableSNMPVersion(String version) {
		WebElement enableSNMPVersion = driver.findElement(By.xpath("//input[@id=\"snmpAgent:enableSnmpVersion" + version + "Checkbox\"]"));
		return enableSNMPVersion;
	}

	// method to enter security name of SNMP Version 1Community / 2cCommunity
	public WebElement securityName(String version) {
		WebElement securityName = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion\"" + version + "\"CommunityNameInputText\"]"));
		return securityName;
	}

	// method to enter security name of SNMP Version 3
	public WebElement securityNamev3() {
		WebElement securityNamev3 = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3SecurityNameInputText\"]"));
		return securityNamev3;
	}

	// method - select Authentication Protocol
	public WebElement authProtocol() {
		WebElement authProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:snmpVersion3AuthenticationProtocolListbox\"]"));
		return authProtocol;
	}

	// method - enter Authentication Password
	public WebElement authPassword() {
		WebElement authPassword = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3AuthenticationPasswordInputText\"]"));
		return authPassword;
	}

	// method - select Privacy Protocol
	public WebElement privProtocol() {
		WebElement privProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:snmpVersion3PrivacyProtocolListbox\"]"));
		return privProtocol;
	}

	// method - enter Privacy Password
	public WebElement privPassword() {
		WebElement privPassword = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3PrivacyPasswordInputText\"]"));
		return privPassword;
	}

	// method - Allow All IP Addresses
	public WebElement allowAll() {
		WebElement allowAll = driver.findElement(By.xpath("//input[@id=\"snmpAgent:authorizeIpAddressesRadioButton:0\"]"));
		return allowAll;
	}

	// method - Allow Only the Following
	public WebElement allowOnly() {
		WebElement allowOnly = driver.findElement(By.xpath("//input[@id=\"snmpAgent:authorizeIpAddressesRadioButton:1\"]"));
		return allowOnly;
	}

	// method to enter IP Address/Hostname 1/2/3/4/5 in Authorized for SNMP Access table
	public WebElement IPAddress(String number) {
		WebElement IPAddress = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpAuthorizedIpAddress\"" + number + "\"InputText\"]"));
		return IPAddress;
	}

	// method - Transport Protocol
	public WebElement transProtocol() {
		WebElement transProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:transportProtocolListbox\"]"));
		return transProtocol;
	}

	// method - Default Port Number
	public WebElement defPort() {
		WebElement defPort = driver.findElement(By.xpath("//input[@id=\"snmpAgent:selectPortRadioButton:0\"]"));
		return defPort;
	}

	// method - Custom Port Number
	public WebElement cusPort() {
		WebElement cusPort = driver.findElement(By.xpath("//input[@id=\"snmpAgent:selectPortRadioButton:1\"]"));
		return cusPort;
	}

	// method - Custom Port Number
	public WebElement inputCusPort() {
		WebElement inputCusPort = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpSpecificPortNumberInputText\"]"));
		return inputCusPort;
	}
	
	/*
	 ***********The methods of Add SNMP Trap Configuration*********************
	 */
	//method - Enable SNMP Trap Yes/No
	public WebElement enableSNMPTrap(String value) {
		WebElement enableSNMPTrap = driver.findElement(By.xpath("//input[@value=\"" + value + "\"]"));
		return enableSNMPTrap;
	}
	
	//method - Device SNMP Trap
	public WebElement deviceSNMPTrap() {
		WebElement enableSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:deviceListbox\"]"));
		return enableSNMPTrap;
	}
	
	//method - Transport Protocol SNMP Trap
	public WebElement transportProtocolSNMPTrap() {
		WebElement transportProtocolSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:transportProtocolListbox\"]"));
		return transportProtocolSNMPTrap;
	}
	
	//method - Host Address SNMP Trap
	public WebElement hostAddressSNMPTrap() {
		WebElement hostAddressSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpIpAddressInputText\"]"));
		return hostAddressSNMPTrap;
	}
	
	//method - Port SNMP Trap
	public WebElement portSNMPTrap() {
		WebElement portSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpPortInputText\"]"));
		return portSNMPTrap;
	}
	
	//method - Notification Type SNMP Trap
	public WebElement notificationTypeSNMPTrap() {
		WebElement notificationTypeSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:notificationTypeListbox\"]"));
		return notificationTypeSNMPTrap;
	}
	
	//method - SNMP Version SNMP Trap
	public WebElement versionSNMPTrap() {
		WebElement versionSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:versionListbox\"]"));
		return versionSNMPTrap;
	}
	
	//method - Security Name SNMP Trap
	public WebElement securityNameSNMPTrap() {
		WebElement securityNameSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpCommunityNameInputText\"]"));
		return securityNameSNMPTrap;
	}
	
	//method - Authentication Protocol SNMP Trap
	public WebElement authProtocolSNMPTrap() {
		WebElement authProtocolSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:authenticationProtocolListbox\"]"));
		return authProtocolSNMPTrap;
	}
	
	//method - Authentication Password SNMP Trap
	public WebElement authPassSNMPTrap() {
		WebElement authPassSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpAuthenticationPasswordInputText\"]"));
		return authPassSNMPTrap;
	}
	
	//method - Notification Type SNMP Trap
	public WebElement privProtocolSNMPTrap() {
		WebElement privProtocolSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:privacyProtocolListbox\"]"));
		return privProtocolSNMPTrap;
	}
	
	//method - Notification Type SNMP Trap
	public WebElement privPassSNMPTrap() {
		WebElement privPassSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpPrivacyPasswordInputText\"]"));
		return privPassSNMPTrap;
	}
	
	
	
	
	// method to set SNMP Agent Settings
	public void SNMP_authPriv(String securityName, String authPro, String authPass, String privPro, String privPass,
							  String SNMPserver, String SNMPuser, String SNMPpass) throws Exception {
		String auth_Pro[] = authPro.split("\\|");
		String priv_Pro[] = privPro.split("\\|");
		for (int i = 0; i < auth_Pro.length; i++) {
			for (int j = 0; j < priv_Pro.length; j++) {
				log.info("Set SNMP Agent Settings with version 3");
				ComFuncObj.switchEPMenu("SNMP");
				ComFuncObj.switchFrame("main");
				Button("SNMP Agent Settings").click();
				enableSNMPVersion("3").click();
				if (enableSNMPVersion("3").isSelected()) {

				} else {
					enableSNMPVersion("3").click();
				}
				log.info("Enter Security Name");
				securityNamev3().clear();
				securityNamev3().sendKeys(securityName);
				ComFuncObj.wait(1);
				log.info("Select Authentication Protocol");
				ComFuncObj.selectDropDownOption(authProtocol(), auth_Pro[i]);
				ComFuncObj.wait(1);
				log.info("Enter Authentication Password");
				authPassword().clear();
				authPassword().sendKeys(authPass);
				ComFuncObj.wait(1);
				log.info("Select Privacy Protocol");
				ComFuncObj.selectDropDownOption(privProtocol(), priv_Pro[j]);
				ComFuncObj.wait(1);
				log.info("Enter Privacy Password");
				privPassword().clear();
				privPassword().sendKeys(privPass);
				ComFuncObj.wait(1);
				Button("Apply").click();
				ComFuncObj.wait(1);
				Button("Save").click();

				log.info("Restart avpSNMPAgentSvc");
				String restartSNMP = "service avpSNMPAgentSvc restart";
				sshObj.sshConnectExec(restartSNMP);
				ComFuncObj.wait(1);

				log.info("Login external SNMP server");
				String cmd = "cd /opt/Avaya/ExperiencePortal/VPMS/SNMP/";
				String pwd = "pwd";
				String get = "bash SendSNMPRequest -h " + ReadData.EPServer + " -v 3 -c " + securityName + " -t GET -T " + auth_Pro[i] + " -A " + authPass + " -R " + priv_Pro[j] + " -P " + privPass;
				String getnext = "bash SendSNMPRequest -h " + ReadData.EPServer + " -v 3 -c " + securityName + " -t GETNEXT -T " + auth_Pro[i] + " -A " + authPass + " -R " + priv_Pro[j] + " -P " + privPass;
				log.info("Negative SNMP");
				log.info("Execute SNMP get and getnext");
				//sshObj.sshConnectShell(cmd,pwd,get,getnext);
				sshObj.sshOtherConnect(SNMPserver,SNMPuser,SNMPpass,cmd,pwd,get,getnext);
				ComFuncObj.wait(1);
				//sshObj.exec(cmd);
				//ComFuncObj.wait(1);
				//sshObj.connectSftp().pwd();
				//log.info("Execute SNMP get and getnext");
				//sshObj.exec(get);
				//ComFuncObj.wait(3);
				//sshObj.exec(getnext);
				//ComFuncObj.wait(3);
				//sshObj.disconnect();
				//ComFuncObj.wait(1);
			}
		}
	}
	
}

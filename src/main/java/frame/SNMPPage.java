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
	 ***********The WebElement of SNMP Page*********************
	 */
	//Use buttons - Add/Delete/Help/Test/SNMP Agent Settings/SNMP Device Notification Settings
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}
	
	//Host Address SNMP
	public WebElement IpAddressSNMP(String IpAddress) {
		WebElement IpAddressSNMP = driver.findElement(By.xpath("//a[text()=\"" + IpAddress + "\"]"));
		return IpAddressSNMP;
	}
	
	//Check box Host Address SNMP
	public WebElement checkBoxIpAddressSNMP(String IpAddress) {
		WebElement checkBoxIpAddressSNMP = driver.findElement(By.xpath("//a[text()=\"" + IpAddress + "\"]/../..//input[@type=\"checkbox\"]"));
		return checkBoxIpAddressSNMP;
	}
	
	//Check box All Host Address SNMP
	public WebElement checkBoxAllSNMP() {
		WebElement checkBoxAllSNMP = driver.findElement(By.xpath("//input[@id=\"snmpTrapListForm:snmpTrapListDataTable:allSelected\"]"));
		return checkBoxAllSNMP;
	}
	

	/*
	 ***********The WebElement of SNMP Agent Settings*********************
	 */	 
	// Enable SNMP Version 1/2c/3
	public WebElement enableSNMPVersion(String version) {
		WebElement enableSNMPVersion = driver.findElement(By.xpath("//input[@id=\"snmpAgent:enableSnmpVersion" + version + "Checkbox\"]"));
		return enableSNMPVersion;
	}

	// Enter security name of SNMP Version 1Community / 2cCommunity
	public WebElement securityName(String version) {
		WebElement securityName = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion\"" + version + "\"CommunityNameInputText\"]"));
		return securityName;
	}

	// Enter security name of SNMP Version 3
	public WebElement securityNamev3() {
		WebElement securityNamev3 = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3SecurityNameInputText\"]"));
		return securityNamev3;
	}

	// Select Authentication Protocol
	public WebElement authProtocol() {
		WebElement authProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:snmpVersion3AuthenticationProtocolListbox\"]"));
		return authProtocol;
	}

	// Enter Authentication Password
	public WebElement authPassword() {
		WebElement authPassword = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3AuthenticationPasswordInputText\"]"));
		return authPassword;
	}

	// Select Privacy Protocol
	public WebElement privProtocol() {
		WebElement privProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:snmpVersion3PrivacyProtocolListbox\"]"));
		return privProtocol;
	}

	// Enter Privacy Password
	public WebElement privPassword() {
		WebElement privPassword = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3PrivacyPasswordInputText\"]"));
		return privPassword;
	}

	// Authorized for SNMP Access AllowAllIpAddressesItem/AllowSpecificIpAddresses
	public WebElement authorizedSNMPAccess(String value) {
		WebElement authorizedSNMPAccess = driver.findElement(By.xpath("//input[@value=\"" + value + "\"]"));
		return authorizedSNMPAccess;
	}

	// Enter IP Address/Hostname 1/2/3/4/5 in Authorized for SNMP Access table
	public WebElement IPAddress(String number) {
		WebElement IPAddress = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpAuthorizedIpAddress\"" + number + "\"InputText\"]"));
		return IPAddress;
	}

	// Transport Protocol
	public WebElement transProtocol() {
		WebElement transProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:transportProtocolListbox\"]"));
		return transProtocol;
	}

	// Port Number DefaultPortItem/CustomPortItem
	public WebElement portNumber(String value) {
		WebElement portNumber = driver.findElement(By.xpath("//input[@value=\"" + value + "\"]"));
		return portNumber;
	}

	// Custom Port Number
	public WebElement inputCusPort() {
		WebElement inputCusPort = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpSpecificPortNumberInputText\"]"));
		return inputCusPort;
	}
	
	/*
	 ***********The WebElement of Add SNMP Trap Configuration*********************
	 */
	// Enable SNMP Trap Yes/No
	public WebElement enableSNMPTrap(String value) {
		WebElement enableSNMPTrap = driver.findElement(By.xpath("//input[@value=\"" + value + "\"]"));
		return enableSNMPTrap;
	}
	
	// Device SNMP Trap
	public WebElement deviceSNMPTrap() {
		WebElement enableSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:deviceListbox\"]"));
		return enableSNMPTrap;
	}
	
	// Transport Protocol SNMP Trap
	public WebElement transportProtocolSNMPTrap() {
		WebElement transportProtocolSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:transportProtocolListbox\"]"));
		return transportProtocolSNMPTrap;
	}
	
	// Host Address SNMP Trap
	public WebElement hostAddressSNMPTrap() {
		WebElement hostAddressSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpIpAddressInputText\"]"));
		return hostAddressSNMPTrap;
	}
	
	// Port SNMP Trap
	public WebElement portSNMPTrap() {
		WebElement portSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpPortInputText\"]"));
		return portSNMPTrap;
	}
	
	// Notification Type SNMP Trap
	public WebElement notificationTypeSNMPTrap() {
		WebElement notificationTypeSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:notificationTypeListbox\"]"));
		return notificationTypeSNMPTrap;
	}
	
	// SNMP Version SNMP Trap
	public WebElement versionSNMPTrap() {
		WebElement versionSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:versionListbox\"]"));
		return versionSNMPTrap;
	}
	
	// Security Name SNMP Trap
	public WebElement securityNameSNMPTrap() {
		WebElement securityNameSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpCommunityNameInputText\"]"));
		return securityNameSNMPTrap;
	}
	
	// Authentication Protocol SNMP Trap
	public WebElement authProtocolSNMPTrap() {
		WebElement authProtocolSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:authenticationProtocolListbox\"]"));
		return authProtocolSNMPTrap;
	}
	
	// Authentication Password SNMP Trap
	public WebElement authPassSNMPTrap() {
		WebElement authPassSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpAuthenticationPasswordInputText\"]"));
		return authPassSNMPTrap;
	}
	
	// Notification Type SNMP Trap
	public WebElement privProtocolSNMPTrap() {
		WebElement privProtocolSNMPTrap = driver.findElement(By.xpath("//select[@id=\"snmpTrapForm:privacyProtocolListbox\"]"));
		return privProtocolSNMPTrap;
	}
	
	// Notification Type SNMP Trap
	public WebElement privPassSNMPTrap() {
		WebElement privPassSNMPTrap = driver.findElement(By.xpath("//input[@id=\"snmpTrapForm:snmpPrivacyPasswordInputText\"]"));
		return privPassSNMPTrap;
	}
	
	
	
	// method to set SNMP Agent Settings version 3
	public void set_SNMP_Agent_Settings_version3 (String securityName, String authPro, String authPass, String privPro, String privPass) throws Exception {
		log.info("Set SNMP Agent Settings with version 3");
		ComFuncObj.switchEPMenu("SNMP");
		ComFuncObj.switchFrame("main");
		Button("SNMP Agent Settings").click();
		enableSNMPVersion("3").click();
		if (enableSNMPVersion("3").isSelected()) {
			
		}
		else {
			enableSNMPVersion("3").click();
		}
		log.info("Enter Security Name");
		securityNamev3().clear();
		securityNamev3().sendKeys(securityName);
		ComFuncObj.wait(1);
		log.info("Select Authentication Protocol");
		ComFuncObj.selectDropDownOption(authProtocol(),authPro);
		ComFuncObj.wait(1);
		log.info("Enter Authentication Password");
		authPassword().clear();
		authPassword().sendKeys(authPass);
		ComFuncObj.wait(1);
		log.info("Select Privacy Protocol");
		ComFuncObj.selectDropDownOption(privProtocol(),privPro);
		ComFuncObj.wait(1);
		log.info("Enter Privacy Password");
		privPassword().clear();
		privPassword().sendKeys(privPass);
		ComFuncObj.wait(1);
		Button("Apply").click();
		ComFuncObj.wait(1);
		Button("Save").click();
	}

}

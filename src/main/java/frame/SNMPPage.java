package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;

public class SNMPPage {
	
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	// Page Objects
	
	//method to use buttons - Add/Delete/Help/Test/SNMP Agent Settings/SNMP Device Notification Settings
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}
	
	//method to enable SNMP Version 1/2c/3
	public WebElement enableSNMPVersion(String version) {
		WebElement enableSNMPVersion = driver.findElement(By.xpath("//input[@id=\"snmpAgent:enableSnmpVersion" + version + "Checkbox\"]"));
		return enableSNMPVersion;
	}
	
	//method to enter security name of SNMP Version 1Community / 2cCommunity
	public WebElement securityName(String version) {
		WebElement securityName = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion\"" + version + "\"CommunityNameInputText\"]"));
		return securityName;
	}
	
	//method to enter security name of SNMP Version 3
	public WebElement securityNamev3() {
		WebElement securityNamev3 = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3SecurityNameInputText\"]"));
		return securityNamev3;
	}
		
	//method - select Authentication Protocol
	public WebElement authProtocol() {
		WebElement authProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:snmpVersion3AuthenticationProtocolListbox\"]"));
		return authProtocol;
	}
		
	//method - enter Authentication Password
	public WebElement authPassword() {
		WebElement authPassword = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3AuthenticationPasswordInputText\"]"));
		return authPassword;
	}
	
	//method - select Privacy Protocol
	public WebElement privProtocol() {
		WebElement privProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:snmpVersion3PrivacyProtocolListbox\"]"));
		return privProtocol;
	}
		
	//method - enter Privacy Password
	public WebElement privPassword() {
		WebElement privPassword = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpVersion3PrivacyPasswordInputText\"]"));
		return privPassword;
	}
		
	//method - Allow All IP Addresses
	public WebElement allowAll() {
		WebElement allowAll = driver.findElement(By.xpath("//input[@id=\"snmpAgent:authorizeIpAddressesRadioButton:0\"]"));
		return allowAll;
	}
		
	//method - Allow Only the Following
	public WebElement allowOnly() {
		WebElement allowOnly = driver.findElement(By.xpath("//input[@id=\"snmpAgent:authorizeIpAddressesRadioButton:1\"]"));
		return allowOnly;
	}
		
	//method to enter IP Address/Hostname 1/2/3/4/5 in Authorized for SNMP Access table
	public WebElement IPAddress(String number) {
		WebElement IPAddress = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpAuthorizedIpAddress\"" + number + "\"InputText\"]"));
		return IPAddress;
	}
	
	//method - Transport Protocol
	public WebElement transProtocol() {
		WebElement transProtocol = driver.findElement(By.xpath("//select[@id=\"snmpAgent:transportProtocolListbox\"]"));
		return transProtocol;
	}
		
	//method - Default Port Number
	public WebElement defPort() {
		WebElement defPort = driver.findElement(By.xpath("//input[@id=\"snmpAgent:selectPortRadioButton:0\"]"));
		return defPort;
	}
		
	//method - Custom Port Number
	public WebElement cusPort() {
		WebElement cusPort = driver.findElement(By.xpath("//input[@id=\"snmpAgent:selectPortRadioButton:1\"]"));
		return cusPort;
	}
		
	//method - Custom Port Number
	public WebElement inputCusPort() {
		WebElement inputCusPort = driver.findElement(By.xpath("//input[@id=\"snmpAgent:snmpSpecificPortNumberInputText\"]"));
		return inputCusPort;
	}
		
	//method to set SNMP Agent Settings
	public void set_SNMP_Agent_Settings_Version3(String securityName, String authPro, String authPass, String privPro, String PrivPass) {
		log.info("Set SNMP Agent Settings with version 3");
		Button("SNMP Agent Settings").click();
		enableSNMPVersion("3").click();
		log.info("Enter Security Name");
		securityNamev3().sendKeys(securityName);
		log.info("Select Authentication Protocol");
		ComFunc.selectDropDownOption(authProtocol(), authPro);
		
		/*
		 * if (authPro == "None") {
		 * 
		 * if (authPassword.isEnabled()) { log.info("testcase pass"); } else {
		 * log.info("testcase failed"); }
		 * 
		 * } else { authPassword.sendKeys(authPass); }
		 * 
		 * Button("Delete").click();
		 */
	}
}

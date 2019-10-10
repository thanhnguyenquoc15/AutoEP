package frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import lib.BrowserFactory;
import lib.ReadData;

public class CertificatesPage {
	
	WebDriver driver = BrowserFactory.getDriver(ReadData.BROWSER);
	protected EPCommonFunction ComFunc = PageFactory.initElements(driver, EPCommonFunction.class);
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	// Page Objects
	
	//method - select Root Certificate tab
	public WebElement rootCerTab() {
		WebElement rootCerTab = driver.findElement(By.xpath("//*[@id=\"certificates_tablist_rootCertificate\"]"));
		return rootCerTab;
	}
	
	//method - select Trusted Certificate tab
	public WebElement trustedCerTab() {
		WebElement trustedCerTab = driver.findElement(By.xpath("//*[@id=\"certificates_tablist_trustedCertificates\"]"));
		return trustedCerTab;
	}
	
	//method - select Certificate tab
	public WebElement cerTab() {
		WebElement cerTab = driver.findElement(By.xpath("//*[@id=\"rootcertificateTabContainer_tablist_rootCertificateTab\"]"));
		return cerTab;
	}
	
	//method - select Certificate Signing Request tab
	public WebElement CSRTab() {
		WebElement CSRTab = driver.findElement(By.xpath("//*[@id=\"rootcertificateTabContainer_tablist_rootCertificateRequestTab\"]"));
		return CSRTab;
	}
	
	//method to use buttons - Disable Signing/Import/Delete/Security Settings/Install/Save/Continue/Cancel/Help
	public WebElement Button(String buttonName) {
		WebElement Button = driver.findElement(By.xpath("//input[@value=\"" + buttonName + "\"]"));
		return Button;
	}
	
	//method - upload Root Certificate
	public WebElement uploadRootCer() {
		WebElement uploadRootCer = driver.findElement(By.xpath("//input[@id=\"certificateForm:save\"]"));
		return uploadRootCer;
	}
	
	//method - generate Root Certificate
	public WebElement generateRootCer() {
		WebElement generateRootCer = driver.findElement(By.xpath("//input[@id=\"certificateForm:generate\"]"));
		return generateRootCer;
	}
	
	//method - open Help Root Certificate
	public WebElement helpRootCer() {
		WebElement helpRootCer = driver.findElement(By.xpath("//input[@id=\"certificateForm:help\"]"));
		return helpRootCer;
	}
	
	//method - export Root Certificate
	public WebElement exportRootCer() {
		WebElement exportRootCer = driver.findElement(By.xpath("//a[@href=\"/VoicePortal/webservices/GetSIPCert?test=great\"]"));
		return exportRootCer;
	}
	
	//method - generate Certificate Signing Request
	public WebElement generateCSR() {
		WebElement generateCSR = driver.findElement(By.xpath("//input[@id=\"certificateSigningRequestForm:generate\"]"));
		return generateCSR;
	}
	
	//method - open Help Certificate Signing Request
	public WebElement helpCSR() {
		WebElement helpCSR = driver.findElement(By.xpath("//input[@id=\"certificateSigningRequestForm:help\"]"));
		return helpCSR;
	}
	
	//method - export Certificate Signing Request
	public WebElement exportCSR() {
		WebElement exportCSR = driver.findElement(By.xpath("//*[@id=\"certificateSigningRequestForm\"]/table[1]/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr/td/a[2]"));
		return exportCSR;
	}
	
	//method - upload Trusted Certificate
	public WebElement uploadTrustedCer() {
		WebElement uploadTrustedCer = driver.findElement(By.xpath("//input[@id=\"trustedCertificatesForm:j_idt113\"]"));
		return uploadTrustedCer;
	}
	
	//method - open Help Trusted Certificate
	public WebElement helpTrustedCer() {
		WebElement helpTrustedCer = driver.findElement(By.xpath("//input[@id=\"trustedCertificatesForm:help\"]"));
		return helpTrustedCer;
	}
	
	//method - open Help Certificates Page
	public WebElement helpCerPage() {
		WebElement helpCerPage = driver.findElement(By.xpath("//input[@id=\"securitySettingsForm:help_readOnly\"]"));
		return helpCerPage;
	}
	
	//method - Enable Server Identity Validation Yes/No
	public WebElement enableServerIDValidation(String value) {
		WebElement enableServerIDValidation = driver.findElement(By.xpath("//input[@value=\"" + value + "\"]"));
		return enableServerIDValidation;
	}
	
}

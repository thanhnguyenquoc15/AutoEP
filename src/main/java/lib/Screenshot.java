package lib;
 
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import bsh.ParseException;
 
public class Screenshot {
 
    
    WebDriver driver;
 
    public Screenshot(WebDriver driver) {
        
        this.driver = driver;
    }
    public static final String PATH_TO_IMG = System.getProperty("user.dir") + File.separator + "Screenshot" + File.separator;
	private static final int year = 0;
    
    public void takeScreenshot(String filename) throws IOException, ParseException, java.text.ParseException {
        String fileName = filename + "-" + getTimeInFormat() + ".png";
        String directory = PATH_TO_IMG;
 
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + fileName));
    }
    
//    public String getTimeInFormat() {	
//		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).format(new Date(1));
//		return timeStamp;
//		}
    
    public String getTimeInFormat() throws java.text.ParseException {
    	
    	String pattern = "yyyy-MM-dd_hh.mm.ss";    
    	
		pattern = new SimpleDateFormat(pattern).format( new java.util.Date());
    	
		return pattern;
    	
    	
		
	}
    
}
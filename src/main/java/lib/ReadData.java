package lib;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;



public class ReadData {
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	
	public static final String userDir = "D:\\DATA\\auto\\AutoEP";
	public static final String testDataDir = "\\src\\main\\java\\data\\";
	
	
	private static final String EXCELFILELOCATION = userDir + testDataDir + "TestCaseData\\";
	
	private static final String DEFAULTPROPERTIES = userDir + testDataDir +"PropertiesFile\\default.properties";
	private static final String PROPERTIESFILE = userDir + testDataDir + "PropertiesFile\\" + getPropertyFile();
	
	public static final String EPServer = readPropertyValue("EPServer");
	
	public static final String EPUser = readPropertyValue("EPUser");
	
	public static final String EPPass = readPropertyValue("EPPass");
	
	public static final String BROWSER = readPropertyValue("BROWSER");
	
	
	
	public static void loadExcelFile(String fileName, String sheetName) throws IOException
	{		
		// Import excel sheet.
		File src=new File(EXCELFILELOCATION + fileName);
		// Load the file.
		FileInputStream finput = new FileInputStream(src);
		// Load the workbook
		workbook = new XSSFWorkbook(finput);
		// Load the sheet in which data is stored.
		sheet = workbook.getSheet(sheetName);
		finput.close();	
	}
	// read data from excel file 
	@DataProvider(name = "dataMap")
	private static Object[][] dataReader(ITestContext context) throws Exception
	{
		String dataFile  = context.getCurrentXmlTest().getParameter("dataFile");
		String dataSheet = context.getCurrentXmlTest().getParameter("dataSheet");
		if(sheet == null)
		{
			loadExcelFile(dataFile,dataSheet);
		}		
		int lastRowNum  = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] myDataMap = new Hashtable[lastRowNum][1];
		
// Read full excel data file		
		for (int i = 0; i < lastRowNum; i++)
		{
			Map<Object, Object> datamap = new Hashtable<Object, Object>();
			for (int j = 0;j < lastCellNum; j++) {
				try
				{
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());				
				}
				catch (Exception e)
				{
				datamap.put(sheet.getRow(0).getCell(j).toString(), "");	
				}
			}
			myDataMap[i][0] = datamap;			
		}		
		return myDataMap;
	}
	
//	public static Object[][] dataProvider(ITestContext context){
//		String testCaseName = context.getCurrentXmlTest().getName();
//		Hashtable[][] myTestdata = null;
//		
//		
//		return myTestdata;
//	}


	
	// Read data from Properties file using Value
	public static String readPropertyValue(String sKey)
	{
		String sValue;
		FileInputStream fs = null;
		try
		{
			fs = new FileInputStream(PROPERTIESFILE);
			Properties objConfigOther = new Properties();
			objConfigOther.load(fs);
			sValue = objConfigOther.getProperty(sKey);
		} catch (Exception e)
		{
			sValue = null;
		} finally
		{
			try
			{
				if (fs != null)
				{
					fs.close();
				}
			} catch (Exception e)
			{
			}
		}
		return sValue;
	}
	
	private static String getPropertyFile()
	{
		String sValue;
		FileInputStream fs = null;
		try
		{
			fs = new FileInputStream(DEFAULTPROPERTIES);
			Properties objConfigOther = new Properties();
			objConfigOther.load(fs);
			sValue = objConfigOther.getProperty("PROPERTY_FILE");
		} catch (Exception e)
		{
			sValue = null;
		} finally
		{
			try
			{
				if (fs != null)
				{
					fs.close();
				}
			} 
			catch (Exception e)
			{
			}
		}
		return sValue;
	}


	
}


package com.abhi.reddy.commons;

import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.abhi.reddy.report.Report;
import com.abhi.reddy.utility.Property;

/**
 * The Class Listners.
 */
public class Listners {
	
	/** The Property file. */
	private static String PropertyFile = "src\\main\\resources\\Data\\autoConfig.properties";
	
	/** The properties. */
	private static Properties properties;
	
	/** The Input file folder. */
	private static String InputFileFolder;
	
	/** The Input file. */
	private static String InputFile;
	
	/** The Excel file. */
	public static String ExcelFile;
	
	/** The Extent report. */
	private static String ExtentReport;

	/**
	 * Before suite.
	 */
	@BeforeSuite
	public void beforeSuite(){
		
		properties = Property.getProperties(PropertyFile);
		InputFileFolder = properties.getProperty("InputFileFolder").trim();
		InputFile = properties.getProperty("DriverInputFile").trim();
		ExcelFile = InputFileFolder + "\\" + InputFile;
		ExtentReport = properties.getProperty("ExtentReport").trim();
		Report.startReporter(ExtentReport);
		
	}
	
	/**
	 * After suite.
	 */
	@AfterSuite
	public void afterSuite(){
		
		Report.stopReporter();
	}
	
}

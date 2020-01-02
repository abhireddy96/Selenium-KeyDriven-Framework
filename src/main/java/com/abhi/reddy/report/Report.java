package com.abhi.reddy.report;

import org.testng.Reporter;

import com.abhi.reddy.commons.Action;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class Report.
 */
public class Report {

	/** The report. */
	public static ExtentReports report;
	
	/** The logger. */
	public static ExtentTest logger; 
	
	/** The perform. */
	public static Action perform;

	/**
	 * Start reporter.
	 *
	 * @param location the location
	 */
	public static void startReporter(String location){

		//Extent reporting
		report=new ExtentReports(location);
		perform=new Action();
	}

	/**
	 * Info.
	 *
	 * @param message the message
	 */
	public static void info(String message){

		logger.log(LogStatus.INFO,message);
		Reporter.log(message);
	}

	/**
	 * Error.
	 *
	 * @param message the message
	 */
	public static void error(String message){

		logger.log(LogStatus.ERROR,message);
		Reporter.log(message);
	}

	/**
	 * Fail.
	 *
	 * @param message the message
	 */
	public static void fail(String message){

		logger.log(LogStatus.FAIL,message);	
		Reporter.log(message);
	}

	/**
	 * Pass.
	 *
	 * @param message the message
	 */
	public static void pass(String message){

		logger.log(LogStatus.PASS,message);	
		Reporter.log(message);

	}

	/**
	 * Skip.
	 *
	 * @param message the message
	 */
	public static void skip(String message){

		logger.log(LogStatus.SKIP,message);	
		Reporter.log(message);

	}
	
	/**
	 * Capture.
	 *
	 * @param screenShotName the screen shot name
	 */
	public static void capture(String screenShotName){
		
		String screenshotPath;
		try {
			screenshotPath = perform.takeShot(screenShotName);
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotPath));
		} catch (Exception e) {
			Report.error("Screen shot not taken "+e.getClass().getSimpleName());
			e.printStackTrace();
		}
		
	}

	/**
	 * Stop reporter.
	 */
	public static void stopReporter(){

		report.endTest(logger);
		report.flush();
	}

	/**
	 * Start test case report.
	 *
	 * @param testCaseName the test case name
	 */
	public static void startTestCaseReport(String testCaseName){
		logger=report.startTest(testCaseName);	
	}

	/**
	 * Stop test case report.
	 */
	public static void stopTestCaseReport(){

		report.endTest(logger);
	}

  

}


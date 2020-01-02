package com.abhi.reddy.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;

import com.abhi.reddy.commons.Listners;
import com.abhi.reddy.commons.Locators;
import com.abhi.reddy.operations.Operations;
import com.abhi.reddy.report.Report;
import com.abhi.reddy.utility.ExcelUtils;

/**
 * The Class TestExecutor.
 */
public class TestExecutor extends Listners{

	/** The operation. */
	private static Operations operation;
	
	/** The Excel driver. */
	private static ExcelUtils ExcelDriver;

	
	/**
	 * Test suite driver.
	 */
	static void testSuiteDriver() {

		String TestCaseSheetName;
		String RunFlag;
		int inputRow, inputRowCount;

		ExcelDriver = new ExcelUtils();
		ExcelDriver.openExcelWorkbook(ExcelFile);

		inputRowCount = ExcelDriver.getRowCountOfSheet("TestSuite"); 
		
		for (inputRow = 2; inputRow <= inputRowCount + 1; inputRow++) {
			
			TestCaseSheetName = ExcelDriver.getCellCData("TestSuite", inputRow, 2);
			RunFlag = ExcelDriver.getCellCData("TestSuite", inputRow, 3);
			
			TestCaseSheetName = TestCaseSheetName.trim();
			RunFlag =RunFlag.toLowerCase().trim();

			if (RunFlag.equals("yes")) {
				operation = null;
				Report.startTestCaseReport(TestCaseSheetName);
				testCaseDriver(TestCaseSheetName);
				Report.stopTestCaseReport();

			} else {
				Report.startTestCaseReport(TestCaseSheetName);
				Report.skip("Test Case Skipped - Run Flag was not set");
				Report.stopTestCaseReport();
			}
		}
	}

	/**
	 * Test case driver.
	 *
	 * @param ExcelSheetName the excel sheet name
	 */
	private static void testCaseDriver(String ExcelSheetName) {
		
		int inputRow = 0, inputRowCount;
		String ActionKeyword;
		String ObjectLocator;
		String ArgumentValue;
		String Comment;
		String StepName = null;
		By by;

		try {
			operation = new Operations();
			inputRowCount = ExcelDriver.getRowCountOfSheet(ExcelSheetName);
			System.out.println("The row count is: " + inputRowCount);

			for (inputRow = 2; inputRow <= inputRowCount + 1; inputRow++) {

				ActionKeyword = "";
				ObjectLocator = "";
				ArgumentValue = "";
				Comment = "";
				StepName = "";
			    by = null;
                
			    StepName = ExcelDriver.getCellCData(ExcelSheetName, inputRow, 1).trim();
				ActionKeyword = ExcelDriver.getCellCData(ExcelSheetName, inputRow, 2).trim();
				ObjectLocator = ExcelDriver.getCellCData(ExcelSheetName, inputRow, 3).trim();
				ArgumentValue = ExcelDriver.getCellCData(ExcelSheetName, inputRow, 4).trim();
				Comment = ExcelDriver.getCellCData(ExcelSheetName, inputRow, 5).trim();

				if (!ObjectLocator.equals("")) {
					by = Locators.getLocatorBy(ObjectLocator);
				}

				if (ActionKeyword.equals("")) {
					Report.skip("Skipped - No Action Keyword");

				} else {
					try {

						operation.performAction(ActionKeyword,by, ArgumentValue,StepName);
						if (!Comment.equals(""))
							Report.pass(StepName+" : "+Comment);
						

					} 
					catch(UnhandledAlertException e){
						operation.performAction("acceptAlert",by, ArgumentValue,StepName);
						System.out.println(e);
					}
					catch(NoSuchElementException e){
						System.out.println(e);
					}
					catch(WebDriverException e){
						System.out.println(e);
					}
					catch (Exception e) {

						Report.fail("TestCase Exectuion suspeneded due to error at  :  "+StepName);
						operation.performAction("takeScreenShot",by, "FailureShot",StepName);
						Report.error("Exception : "+e.getClass().getName());
						System.out.println(e);
						return;

					}
					
				}

			}

		} catch (Exception e) {
		
			Report.fail("TestCase Exectuion suspeneded due to error line :  "+StepName);
			Report.error("Exception : "+e.getClass().getSimpleName());
		
		}
	}

}


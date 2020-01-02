package com.abhi.reddy.operations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.abhi.reddy.commons.Action;
import com.abhi.reddy.report.Report;

/**
 * The Class Operations.
 */
public class Operations {

	/** The perform. */
	public static Action perform;

	/**
	 * Instantiates a new operations.
	 */
	public Operations() {

		perform=new Action();
	}

	/**
	 * Perform action.
	 *
	 * @param actionName the action name
	 * @param by the by
	 * @param value the value
	 * @param stepName the step name
	 * @throws Exception the exception
	 */
	public void performAction(String actionName, By by, String value,String stepName) throws Exception { 

		actionName = actionName.trim();

		if (actionName.isEmpty()) {

			Report.info("Action Keyword not specified");
		}

		//-------------------BROWSER operations------------------
		
		if (actionName.equalsIgnoreCase("openbrowser")) {

			perform.startBrowser(value);
			//Report.pass(value.toUpperCase()+" browser opened");

		}

		if (actionName.equalsIgnoreCase("closeAllBrowser")) {

			perform.stopBrowser();

			//Report.pass("Closed all browsers");
		}

		if (actionName.equalsIgnoreCase("closeCurrentBrowser")) {

			perform.closeBrowser();

			//Report.pass("Closed current browser");
		}

		if (actionName.equalsIgnoreCase("navigateToUrl")) {

			perform.getUrl(value); 
			//Report.pass("Navigated to : "+value);

		}

		if (actionName.equalsIgnoreCase("navigateBack")) {

			perform.navigateBack();

			//Report.pass("Navigated back to previous window");
		}

		if (actionName.equalsIgnoreCase("navigateForward")) {

			perform.navigateForward();

			//Report.pass("Navigated forward to next window");

		}

		//-------------------ACTION operations------------------
		
		if (actionName.equalsIgnoreCase("click")) {

			perform.clickElement(by);
			//Report.pass(perform.getText(by)+" clicked");

		}
		
		if (actionName.equalsIgnoreCase("jsClick")) {

			perform.jsClickElement(by);
			//Report.pass(perform.getText(by)+" clicked");

		}

		if (actionName.equalsIgnoreCase("clear")) {

			perform.clear(by);

			//Report.pass("Text cleared");
		}

		if (actionName.equalsIgnoreCase("submit")) {

			perform.submit(by);

			//Report.pass("Data has been submitted");
		}

		if (actionName.equalsIgnoreCase("setText")) {

			perform.setText(by, value);

			//Report.pass("Text : "+value+" entered into field");
		}

		if (actionName.equalsIgnoreCase("selectitembytext")) {

			perform.selectItemInListByText(by, value);

			//Report.pass("Item : "+value+" is selected in list");

		}

		if (actionName.equalsIgnoreCase("selectitembyvalue")) {

			perform.selectItemInListByValue(by, value);

			//Report.pass("Item : "+value+" is selected in list");

		}
		
		if(actionName.equalsIgnoreCase("iterateElementsAndClick")){

			perform.selectAllItems(by);

			//Report.pass("All the elements are selected from list");
		}
		
		//-------------------GET and VERIFY operations------------------
		
		if (actionName.equalsIgnoreCase("gettext")) {

			Report.pass(stepName+" : Text is "+perform.getText(by));
		}
		
		if (actionName.equalsIgnoreCase("getTextboxText")) {

			if(perform.getTextboxText(by, value).equals("pass"))
				Report.pass(stepName+" : Text box text is "+value+" Verified");
			else
				Report.fail(stepName+" : Text box text - "+value+" verification failed");
		}

		if (actionName.equalsIgnoreCase("verifytext")) {

			if(perform.verifyText(by, value).equals("pass"))
				Report.pass(stepName+" : Text - "+value+" Verified");
			else
				Report.fail(stepName+" : Text - "+value+" verification failed");

		}

		if (actionName.equalsIgnoreCase("getTitle")) {

			Report.pass(stepName+" : Title is "+perform.getTitle());

		}

		if (actionName.equalsIgnoreCase("verifyTitle")) {

			if(perform.verifyTitle(value).equals("pass"))
				Report.pass(stepName+" : Title - "+value+" Verified");
			else
				Report.fail(stepName+" : Title - "+value+" verification failed");

		}

		if (actionName.equalsIgnoreCase("getUrl")) {

			Report.pass(stepName+" : Url is "+perform.getUrl());

		}

		if (actionName.equalsIgnoreCase("verifyUrl")) {

			if(perform.verifyUrl(value).equals("pass"))
				Report.pass(stepName+" : Url - "+value+" Verified");
			else
				Report.fail(stepName+" : Url - "+value+" verification failed");
		}


		if (actionName.equalsIgnoreCase("getstatus")) {

			Report.info(stepName+" : Status is "+perform.getStatus(by));

		}

		if (actionName.equalsIgnoreCase("getSelectedItem")) {

			Report.pass(stepName+" : Selected item is "+perform.getSelectedItem(by));
		}


		if (actionName.equalsIgnoreCase("getItemsCount")) {

			Report.pass(stepName+" : Item count is "+perform.getItemsCount(by));
		}


		//-------------------WINDOW and ALERT operation------------------
		
		if (actionName.equalsIgnoreCase("selectParentWindow")) {

			perform.selectParentWindow();

			Report.pass(stepName+" : Parent window selected");
		}

		if (actionName.equalsIgnoreCase("acceptAlert")) {

			perform.acceptAlert();

			Report.pass(stepName+" : Alert accepted");
		}

		if (actionName.equalsIgnoreCase("rejectAlert")) {

			perform.rejectAlert();

			Report.pass(stepName+" : Alert rejected");
		}

		if (actionName.equalsIgnoreCase("selectDefaultframe")) {

			perform.selectDefaultFrame();

			Report.pass(stepName+" : Default frame selected");
		}
		
		if (actionName.equalsIgnoreCase("selectFrame")) {

			perform.selectFrame(value);

			//Report.pass("Frame : "+value+" selected");
		}


		if (actionName.equalsIgnoreCase("isdisplayed")) {

			if(perform.isDisplayed(by).equals("pass"))
				Report.pass(stepName+" : Element is displayed");
			else
				Report.fail(stepName+" : Element is not displayed");

		}

		if (actionName.equalsIgnoreCase("isenabled")) {

			if(perform.isEnabled(by).equals("pass"))
				Report.pass(stepName+" : Element is enabled");
			else
				Report.fail(stepName+" : Element is not enabled");

		}

		//-------------------WAIT operation------------------
		
		if (actionName.equalsIgnoreCase("threadsleep")) {

			try {
				perform.waitForSeconds(Long.parseLong(value));
				System.out.println("Thread sleep used for "+value+" seconds");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (actionName.equalsIgnoreCase("waitTillElementIsVisible")) {

			perform.waitTillElementIsVisible(by, Long.valueOf(value));

			System.out.println("Waited till element is visible");
		}

		if (actionName.equalsIgnoreCase("waitTillElementIsClickable")) {

			perform.waitTillElementIsClickable(by, Long.valueOf(value));

			System.out.println("Waited till element is clickable");
		}

		if (actionName.equalsIgnoreCase("waitTillAllElementVisible")) {

			perform.waitTillAllElementsVisible(by, Long.valueOf(value));

			System.out.println("Waited till elements are visible");
		}
		
		//-------------------Other operation------------------
		
		if (actionName.equalsIgnoreCase("takeScreenShot")) {

			Report.capture(value);
		}
		
		if (actionName.equalsIgnoreCase("moveTo")) {

			perform.moveTo((WebElement) by);
		}
		

	}

}

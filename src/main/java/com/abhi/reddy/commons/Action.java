package com.abhi.reddy.commons;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Class Action.
 */
public class Action {

	/** The driver. */
	private static WebDriver driver;

	//-------------------BROWSER commands------------------
	
	/**
	 * Start browser.
	 *
	 * @param browsername the browsername
	 * @throws Exception the exception
	 */
	public void startBrowser(String browsername) throws Exception
	{
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","src\\main\\resources\\Driver\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "src\\main\\resources\\Driver\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else
		{

			throw new Exception("Unknow Browser Type = "+ browsername);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}	

	/**
	 * Stop browser.
	 *
	 * @throws Exception the exception
	 */
	public void stopBrowser() throws Exception{

		driver.quit();
	}

	/**
	 * Gets the url.
	 *
	 * @param url the url
	 * @return the url
	 * @throws Exception the exception
	 */
	public void getUrl(String url) throws Exception{

		driver.get(url);
	}

	/**
	 * Navigate back.
	 *
	 * @throws Exception the exception
	 */
	public void navigateBack() throws Exception{

		driver.navigate().back();
	}

	/**
	 * Navigate forward.
	 *
	 * @throws Exception the exception
	 */
	public void navigateForward() throws Exception{

		driver.navigate().forward();
	}

	/**
	 * Close browser.
	 *
	 * @throws Exception the exception
	 */
	public void closeBrowser() throws Exception{

		driver.close();
	}

	//-------------------WebElement commands------------------
	
	/**
	 * Sets the text.
	 *
	 * @param by the by
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void setText(By by, String value) throws Exception {

		driver.findElement(by).sendKeys(value);

	}

	/**
	 * Click element.
	 *
	 * @param by the by
	 * @throws Exception the exception
	 */
	public void clickElement(By by) throws Exception {

		driver.findElement(by).click();

	}
	
	/**
	 * Js click element.
	 *
	 * @param by the by
	 * @throws Exception the exception
	 */
	public void jsClickElement(By by) throws Exception {
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()",by);
	}

	/**
	 * Select item in list by text.
	 *
	 * @param by the by
	 * @param ItemVisibleText the item visible text
	 * @throws Exception the exception
	 */
	public void selectItemInListByText(By by, String ItemVisibleText) throws Exception{

		Select list;
		list = new Select(driver.findElement(by));
		list.selectByVisibleText(ItemVisibleText);

	}

	/**
	 * Select item in list by value.
	 *
	 * @param by the by
	 * @param itemValue the item value
	 * @throws Exception the exception
	 */
	public void selectItemInListByValue(By by, String itemValue) throws Exception{

		Select  list;
		list = new Select(driver.findElement(by));
		list.selectByValue(itemValue);

	}

	/**
	 * Select all items.
	 *
	 * @param by the by
	 * @throws Exception the exception
	 */
	public void selectAllItems(By by) throws Exception{
		List<WebElement> list = driver.findElements(by);
		for(WebElement each: list){
			each.click();
		}
	}
	
	/**
	 * Submit.
	 *
	 * @param by the by
	 * @throws Exception the exception
	 */
	public void submit(By by) throws Exception{

		driver.findElement(by).submit();
	}

	/**
	 * Clear.
	 *
	 * @param by the by
	 * @throws Exception the exception
	 */
	public void clear(By by) throws Exception{

		driver.findElement(by).clear();
	}

	//-------------------WINDOW and ALERT commands------------------
	
	/**
	 * Accept alert.
	 *
	 * @throws Exception the exception
	 */
	public void acceptAlert() throws Exception{

		driver.switchTo().alert().accept();
	}

	/**
	 * Reject alert.
	 *
	 * @throws Exception the exception
	 */
	public void rejectAlert() throws Exception{

		driver.switchTo().alert().dismiss();
	}

	/**
	 * Select default frame.
	 *
	 * @throws Exception the exception
	 */
	public void selectDefaultFrame() throws Exception{

		driver.switchTo().defaultContent();
	}
	
	/**
	 * Select parent window.
	 *
	 * @throws Exception the exception
	 */
	public void selectParentWindow() throws Exception{

		driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
	}
	
	/**
	 * Select frame.
	 *
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void selectFrame(String value) throws Exception{
		
		driver.switchTo().frame(value);
	}

	//-------------------GET and VERIFY commands------------------
	
	/**
	 * Gets the text.
	 *
	 * @param by the by
	 * @return the text
	 * @throws Exception the exception
	 */
	public String getText(By by) throws Exception{

		return driver.findElement(by).getText();

	}
	
	/**
	 * Gets the textbox text.
	 *
	 * @param by the by
	 * @param value the value
	 * @return the textbox text
	 * @throws Exception the exception
	 */
	public String getTextboxText(By by,String value) throws Exception{

		if (driver.findElement(by).getAttribute("value").equals(value)) 
			return "pass";
		else 
			return "error";
	}

	/**
	 * Verify text.
	 *
	 * @param by the by
	 * @param value the value
	 * @return the string
	 * @throws Exception the exception
	 */
	public String verifyText(By by,String value) throws Exception{

		if (driver.findElement(by).getText().equals(value)) 
			return "pass";
		else 
			return "error";

	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 * @throws Exception the exception
	 */
	public String getTitle() throws Exception{

		return driver.getTitle(); 
	}

	/**
	 * Verify title.
	 *
	 * @param value the value
	 * @return the string
	 * @throws Exception the exception
	 */
	public String verifyTitle(String value) throws Exception {

		if (driver.getTitle().equals(value)) 
			return "pass";
		else 
			return "error";

	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 * @throws Exception the exception
	 */
	public String getUrl() throws Exception{
		return driver.getCurrentUrl();
	}

	/**
	 * Verify url.
	 *
	 * @param value the value
	 * @return the string
	 * @throws Exception the exception
	 */
	public String verifyUrl(String value) throws Exception{

		if (driver.getTitle().equals(value))
			return "pass";
		else 
			return "error";
	}

	/**
	 * Gets the status.
	 *
	 * @param by the by
	 * @return the status
	 * @throws Exception the exception
	 */
	public String getStatus(By by) throws Exception{

		return  String.valueOf(driver.findElement(by).isSelected());

	}

	/**
	 * Gets the selected item.
	 *
	 * @param by the by
	 * @return the selected item
	 * @throws Exception the exception
	 */
	public String getSelectedItem(By by) throws Exception {

		Select list;
		WebElement element;

		element = driver.findElement(by);
		list = new Select(element);
		return list.getFirstSelectedOption().getText();
	}

	/**
	 * Gets the items count.
	 *
	 * @param by the by
	 * @return the items count
	 * @throws Exception the exception
	 */
	public String getItemsCount(By by) throws Exception {

		Select list;
		WebElement element;

		element = driver.findElement(by);
		list = new Select(element);

		return String.valueOf(list.getOptions().size());
	}

	/**
	 * Checks if is displayed.
	 *
	 * @param by the by
	 * @return the string
	 * @throws Exception the exception
	 */
	public String isDisplayed(By by) throws Exception {

		if(driver.findElement(by).isDisplayed())
			return "pass";
		else
			return "error";
	}

	/**
	 * Checks if is enabled.
	 *
	 * @param by the by
	 * @return the string
	 * @throws Exception the exception
	 */
	public String isEnabled(By by) throws Exception{

		if(driver.findElement(by).isEnabled())
			return "pass";
		else
			return "error";

	}

	//-------------------WAIT commands------------------

	/**
	 * Wait till element is visible.
	 *
	 * @param by the by
	 * @param timeoutSeconds the timeout seconds
	 */
	public void waitTillElementIsVisible( By by, long timeoutSeconds){

		WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	}

	/**
	 * Wait till all elements visible.
	 *
	 * @param by the by
	 * @param timeoutSeconds the timeout seconds
	 * @throws Exception the exception
	 */
	public void waitTillAllElementsVisible( By by, long timeoutSeconds) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));

	}

	/**
	 * Wait till element is clickable.
	 *
	 * @param by the by
	 * @param timeoutSeconds the timeout seconds
	 * @throws Exception the exception
	 */
	public void waitTillElementIsClickable( By by, long timeoutSeconds ) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(by));

	}
	
	/**
	 * Wait for seconds.
	 *
	 * @param seconds the seconds
	 */
	public void waitForSeconds(long seconds){
		try {
			Thread.sleep(seconds*1000L);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	//-------------------Other commands------------------
	
	/**
	 * Take shot.
	 *
	 * @param screenshotName the screenshot name
	 * @return the string
	 * @throws Exception the exception
	 */
	public String takeShot(String screenshotName) throws Exception{

		
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        System.out.println(driver);
        File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") +"/reports"+"/Screenshots/"+screenshotName+dateName+".png";
		System.out.println("ScreenShot stored at "+destination);
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		//Returns the captured file path
		return destination;
	}
	
	/**
	 * Move to.
	 *
	 * @param by the by
	 */
	public void moveTo(WebElement by){
		Actions action = new Actions(driver);
		action.moveToElement(by);
		action.click();
		action.build().perform();
	}
}
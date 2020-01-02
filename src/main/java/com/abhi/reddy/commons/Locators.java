package com.abhi.reddy.commons;

import org.openqa.selenium.By;


/**
 * The Class Locators.
 */
public class Locators {

	/**
	 * Gets the locator by.
	 *
	 * @param ElementLocatorString the element locator string
	 * @return the locator by
	 */
	public static By getLocatorBy(String ElementLocatorString){
		
		try{

			String[] LocateElement;

			ElementLocatorString = ElementLocatorString.trim();
			if(ElementLocatorString.isEmpty() || ! ElementLocatorString.contains(":=")){

				throw new Exception("Invalid Locator String");
			} 

			LocateElement = ElementLocatorString.split(":=");

			if(LocateElement[0].equalsIgnoreCase("id")){
				return By.id(LocateElement[1]);
			}

			if(LocateElement[0].equalsIgnoreCase("Class")){
				return By.className(LocateElement[1]);
			}

			if(LocateElement[0].equalsIgnoreCase("xPath")){
				return By.xpath(LocateElement[1]);
			}

			if(LocateElement[0].equalsIgnoreCase("css")){
				return By.cssSelector(LocateElement[1]);
			}

			if(LocateElement[0].equalsIgnoreCase("link")){
				return By.linkText(LocateElement[1]);
			}

			if(LocateElement[0].equalsIgnoreCase("partialLink")){
				return By.partialLinkText(LocateElement[1]);
			}

			if(LocateElement[0].equalsIgnoreCase("name")){
				return By.name(LocateElement[1]);
			}

			if(LocateElement[0].equalsIgnoreCase("tagname")){
				return By.tagName(LocateElement[1]);
			}

			throw new Exception("Invalid locator String...");

		} catch(Exception t){
			System.err.println(t.getMessage());
			return null;
		}
	}

}

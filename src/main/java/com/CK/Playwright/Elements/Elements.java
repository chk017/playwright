package com.CK.Playwright.Elements;

import com.CK.Playwright.BaseTest.BaseTest;
import com.CK.Playwright.Reporting.Reporting;

public class Elements {

	private String sElement;
	
	public Elements(String sXpath) {
		this.sElement = sXpath;
	}
	/**
	 * fill method is used to enter a value in the field. Before filling, system wait for the element, highlights the element, fills the value in the field and finally verifies the value. 
	 * @param value - value to enter in the field
	 * @param name - name of the field 
	 * @author chk017 - ChennakesavaRao Bachu 
	 */
	public void fill(String value, String name) {
//		BaseTest.page.waitForSelector(sElement);
		waitforElement(name);
		BaseTest.page.fill(sElement, value);
//		BaseTest.page.locator(sElement).highlight();
		verifyInputText(value, name);
		
	}
	
	/**
	 * click method is used to click on element. Before clicking, system wait for element, highlights the element and clicks on it
	 * @param name - name of the field
	 * @author chk017 - ChennakesavaRao Bachu  
	 */
	public void click(String name) {
		waitforElement(name);
//		BaseTest.page.locator(sElement).highlight();
		BaseTest.page.click(sElement);
		Reporting.pass("System successfuly clicked the element : <b>"+name+"</b>");
	}
	
	/**
	 * This method verifies the tile of the current web page is matching with title provided as parameter and prints in the report 
	 * @param sTitle - Expected title of the page
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public void verifyTitle(String sTitle) {
		
		if(sTitle.contains(BaseTest.page.title())){
			Reporting.pass("System successfully validates the Expected title : <b>"+sTitle+"</b>");
		}else {
			Reporting.fail("System failed to match the Expected title : <b>"+sTitle+"</b> whereas Actual title : "+BaseTest.page.title()+"</b>", true);	
		}
		
	}
	
	/**
	 * This method verifies the value in the field  is matching with the parameter value and prints in the report
	 * @param value - Expected value 
	 * @param name - name of the field
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public void verifyInputText(String value, String name) {

		if(BaseTest.page.locator(sElement).inputValue().contains(value)) {
			
			Reporting.pass("Value <b>"+value+"</b> is entered properly in the field <b>"+name);
		}else {
			
			Reporting.info("seems to be expected value : <b>"+value+"</b> is not entered properly in the field "+name+" , Actual : <b>" + BaseTest.page.locator(sElement).inputValue()+"</b>");
		}
	}
	

	/**
	 * this method used to get the text from the element
	 * @return - string
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public String textContent() {
		return BaseTest.page.textContent(sElement);
	}
	
	
	private void waitforElement(String name) {
//		System.out.println("waiting for element "+name);
		BaseTest.page.waitForSelector(sElement);
		BaseTest.lib.highlight(sElement);
		
	}

	/*
	 * private void waitforLocator(String name) { //
	 * System.out.println("waiting for element "+name);
	 * BaseTest.page.locator(sElement).waitFor(); BaseTest.lib.highlight(sElement);
	 * 
	 * }
	 */
	
	/**
	 * getElement method returns the xpath of element 
	 * @return - XPath of element
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public String getElement() {
		return sElement;
	}
	
	/**
	 * isElementPresent is a method to verify the visibility of element and returns true for element presence and returns false for element absence
	 * @return - boolean true or false
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public boolean isElementPresent() {
		boolean tOrf = false;
		waitforElement(sElement);
		tOrf = BaseTest.page.isVisible(sElement);
		return tOrf;
	}
	
	/**
	 * verifyElementPresent is a method to verify the visibility of element and prints in the reporting
	 * @param name - It is just name of the element 
	 * @author chk017 - ChennakesavaRao Bachu
	 */
	public void verifyElementPresent(String name) {
		if(isElementPresent()) {
			Reporting.pass("System successfully displays the Element <b>"+name+"</b>");
		}else {
			Reporting.fail("System failed to display the Element <b>"+name+"</b>", true);
		}
	}
}

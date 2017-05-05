package com.astro.testing.page;

import com.astro.testing.map.ObjectMap;
import com.astro.testing.test.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Common function libraries of Astro Site
 * 
 * @author Kaustav Saikia
 *
 */
public class Common extends BaseTest{

	/**
	 * Method to click Register button
	 * @throws Exception
	 */
	public Common clickOnRegisterButton() throws Exception{
		logger.log(LogStatus.INFO, "Waiting for Register Button to display");
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.ASTRO_REGISTER_ID));
		driver.findElement(Libraries.getLocator(ObjectMap.ASTRO_REGISTER_ID)).click();
		logger.log(LogStatus.INFO, "Clicked on Register Button");
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_FIRST_NAME_CSS));
		return this;
	}


	
}
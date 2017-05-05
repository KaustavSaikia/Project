package com.astro.testing.page;

import org.openqa.selenium.By;

import com.astro.testing.map.ObjectMap;
import com.astro.testing.test.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Reusable functions of Astro Home Page
 * 
 * @author Kaustav Saikia
 *
 */
public class HomePage extends BaseTest{

	/**
	 * Method to click on the Continue to Astro button
	 * @throws Exception
	 */
	public HomePage continueToAstro() throws Exception{
		Thread.sleep(4000);
		logger.log(LogStatus.INFO, "Waiting for Continue to Astro");
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.CONTINUE_TO_ASTRO_ID));
		driver.findElement(Libraries.getLocator(ObjectMap.CONTINUE_TO_ASTRO_ID)).click();
		logger.log(LogStatus.INFO, "Clicked Continue to Astro");
		return this;
	}

	/**
	 * Wait for the Astro home page to display
	 * @throws Exception
	 */
	public HomePage waitForHomePage() throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.HOME_PAGE_SITE_FOOTER_WRAPPER_CSS));
		logger.log(LogStatus.INFO, "Astro Home Page loaded");
		return this;
	}

	/**
	 * Method to get the total number of promotions
	 * @throws Exception
	 */
	public int getFlexNavigatorsCount() throws Exception{
		int count=0;
		count= driver.findElements(Libraries.getLocator(ObjectMap.HOME_PAGE_FLEX_NAV_CSS)).size();
		return count;
	}
	
	/**
	 * Method to click on the promotion banner Navigation dot
	 * @param flexNo
	 * @throws Exception
	 */
	public HomePage clickOnFlexNavNumber(String flexNo) throws Exception{
		logger.log(LogStatus.INFO, "Clicking Flex Nav no "+flexNo);
		driver.findElement(By.xpath(String.format(ObjectMap.HOME_PAGE_FLEX_NAV_DOT_XPATH.getEnumValue(), flexNo))).click();
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.HOME_PAGE_ACTIVE_FLEX_HEADER_XPATH));
		return this;
	}
	
	/**
	 * Method to click on the active promotion link
	 * @throws Exception
	 */
	public HomePage clickOnActiveFlexLink() throws Exception{
		logger.log(LogStatus.INFO, "Clicking on active promotion link");
		driver.findElement(Libraries.getLocator(ObjectMap.HOME_PAGE_ACTIVE_FLEX_LINK_XPATH)).click();
		return this;
	}
	
	/**
	 * Get the active promotion header message.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getActivePromotionHeaderMessage() throws Exception{
		String promotionMsg=driver.findElement(Libraries.getLocator(ObjectMap.HOME_PAGE_ACTIVE_FLEX_HEADER_XPATH)).getText();
		return promotionMsg;
	}

	/**
	 * Verify Active Promotion banner H2 message
	 * 
	 * @param expectedMessage
	 * @return boolean
	 * @throws Exception
	 */
	public boolean activePromotionHeaderMessage(String expectedPromotionHeaderMessage) throws Exception{
		boolean displayed=false;
		String promotionMsg=driver.findElement(Libraries.getLocator(ObjectMap.HOME_PAGE_ACTIVE_FLEX_HEADER_XPATH)).getText();
		if(promotionMsg.equalsIgnoreCase(expectedPromotionHeaderMessage)){
			displayed=true;
		}
		return displayed;
	}
	
	/**
	 * Method to click on the next button of the promotion banner
	 * @return
	 * @throws Exception
	 */
	public HomePage clickPromotionNextButton() throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.HOME_PAGE_FLEX_NAV_NEXT_CSS));
		driver.findElement(Libraries.getLocator(ObjectMap.HOME_PAGE_FLEX_NAV_NEXT_CSS)).click();
		logger.log(LogStatus.INFO, "Clicked Promotion Next button");
		return this;
	}
	
}
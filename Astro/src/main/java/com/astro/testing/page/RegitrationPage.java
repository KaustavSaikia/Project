package com.astro.testing.page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.astro.testing.map.ObjectMap;
import com.astro.testing.test.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Reusable functions of the Registration Page
 * 
 * @author Kaustav Saikia
 *
 */
public class RegitrationPage extends BaseTest{

	/**
	 * Enter First Name
	 * 
	 * @param firstName
	 * @throws Exception
	 */
	public RegitrationPage enterFirstName(String firstName) throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_FIRST_NAME_CSS));
		driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_FIRST_NAME_CSS)).sendKeys(firstName);
		logger.log(LogStatus.INFO, "First Name entered as "+firstName);
		return this;
	}

	/**
	 * Enter Last Name
	 * 
	 * @param LastName
	 * @throws Exception
	 */
	public RegitrationPage enterLastName(String LastName) throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_LAST_NAME_CSS));
		driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_LAST_NAME_CSS)).sendKeys(LastName);
		logger.log(LogStatus.INFO, "Last Name entered as "+LastName);
		return this;
	}

	/**
	 * Enter Astro ID
	 * 
	 * @param astroID
	 * @throws Exception
	 */
	public RegitrationPage enterAstroID(String astroID) throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_ASTRO_ID_CSS));
		driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_ASTRO_ID_CSS)).sendKeys(astroID);
		logger.log(LogStatus.INFO, "Astro ID entered as " +astroID);
		return this;
	}
	
	/**
	 * Enter Email Address
	 * 
	 * @param emailAddress
	 * @throws Exception
	 */
	public RegitrationPage enterEmailAddress(String emailAddress) throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_EMAIL_CSS));
		driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_EMAIL_CSS)).sendKeys(emailAddress);
		logger.log(LogStatus.INFO, "Email address entered as "+emailAddress);
		return this;
	}
	
	/**
	 * Verify if email Address error validation message 
	 * 
	 * @return boolean
	 */
	public boolean emailErrorMessageDisplayed() {
		boolean displayed=false;
		try{
			Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_EMAIL_VALIDATION_ERROR_XPATH));
			displayed=true;
		} catch(Exception e){}
		return displayed;
	}
	
	/**
	 * Get email address validation message
	 * 
	 * return errorMessage
	 * @throws Exception
	 */
	public String getEmailAddressError() throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_EMAIL_VALIDATION_ERROR_XPATH));
		String errorMsg=driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_EMAIL_VALIDATION_ERROR_XPATH)).getText();
		return errorMsg;
	}
	
	/**
	 * Clears the email address text box
	 * @throws Exception
	 */
	public RegitrationPage clearEmailAddress() throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_EMAIL_CSS));
		driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_EMAIL_CSS)).clear();
		logger.log(LogStatus.INFO, "Email address cleared");
		return this;
	}
	
	/**
	 * Enter Password in the password text box
	 * @param password
	 * @throws Exception
	 */
	public RegitrationPage enterPassword(String password) throws Exception{
		Thread.sleep(1000);
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_PASSWORD_CSS));
		driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_PASSWORD_CSS)).sendKeys(password);
		logger.log(LogStatus.INFO, "Password entered as "+password);
		return this;
	}

	/**
	 * Click on the I Agree Sign Up button
	 * 
	 * @throws Exception
	 */
	public RegitrationPage clickIAgreeSignUp() throws Exception{
		Thread.sleep(1000);
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_I_AGREE_SIGNUP_BTN_XPATH));
		driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_I_AGREE_SIGNUP_BTN_ID)).click();
		logger.log(LogStatus.INFO, "Clicked Sign Up button");
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_COMPLETE_MESSAGE_XPATH));
		return this;
	}

	/**
	 * Get Registration complete Message
	 * 
	 * @return message
	 * @throws Exception
	 */
	public String getRegistrationComletionMessage() throws Exception{
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.REGISTRATION_COMPLETE_MESSAGE_XPATH));
		String actualMessage=driver.findElement(Libraries.getLocator(ObjectMap.REGISTRATION_COMPLETE_MESSAGE_XPATH)).getText();
		return actualMessage;
	}
	
	/**
	 * Get the current date and time
	 * 
	 * @return String 
	 */
	public String getLocalDateTime(){
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddsshh");
		String text = date.format(formatter);
		return text;
	}
	/**
	 * Generate a Mailinator email address
	 * 
	 * @param email
	 * @return emailaddress
	 */
	public String generateEmailAddress(String email){
		String generatedEmail=email+getLocalDateTime()+"@mailinator.com";
		return generatedEmail;
	}
	
	/**
	 * Generate dynamic Astro ID
	 * @param astroID
	 * @return astroID
	 */
	public String generateAstroID(String astroID){
		String generatedAstroID=astroID+getLocalDateTime();
		return generatedAstroID;
	}
}

package com.astro.testing.test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.astro.testing.map.ObjectMap;
import com.astro.testing.page.Common;
import com.astro.testing.page.HomePage;
import com.astro.testing.page.Libraries;
import com.astro.testing.page.RegitrationPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 1. All above; plus </br>
 * 2. Determine the sequence of the promotion called “Watch Astro Without TV” is in</br>
 * &nbsp&nbsp&nbsp a. Ensure that this campaign is third in the sequence of all the hero banners. (In this case it should fail because it is third) </br>
 * &nbsp&nbsp&nbsp <b>NOTE: The campaign can change, please adapt to whichever campaign is present on the site.</b> </br>
 * &nbsp&nbsp&nbsp b. A separate script will verify that the campaign is not the last of the X number of available campaigns </br>
 * &nbsp&nbsp&nbsp &nbsp i. If so follow the targeted URL </br>
 * &nbsp&nbsp&nbsp &nbsp ii. Follow the “Sign up” path </br>
 * &nbsp&nbsp&nbsp &nbsp iii. Register for an AstroID </br>
 * &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp 1. Email address should prompt operator to enter a valid email (only request for email, other fields should be prepopulated) </br>
 * &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp 2. Please register a valid email from an email provider (eg. Gmail prior to setting this up) </br>
 * 
 * @author Kaustav Saikia
 *
 */
public class AstroQAAssignmentTwo extends BaseTest {
	HomePage home = new HomePage();
	Common common = new Common();
	RegitrationPage registration = new RegitrationPage();

	@Test(description = "Determine the sequence of the promotion called 'Watch Astro Without TV'is in third", groups = {"func" }, priority = 0)
	@Parameters({ "url" })
	public void TestPromotion(String url) throws Exception {
		logger = report.startTest("Verify Promotion Sequence");
		Libraries.launchSite(url);
		home.continueToAstro().waitForHomePage().clickOnFlexNavNumber("3");
		if (!home.activePromotionHeaderMessage("All Astro customers can now enjoy Astro GO, free")) {
			logger.log(LogStatus.FAIL, "Watch Astro Without TV promotion is not in 3rd sequence instead "
					+ home.getActivePromotionHeaderMessage() + " promotion is displayed in 3rd");
			Assert.assertTrue(false, "Watch Astro Without TV promotion is not in 3rd sequence instead "
					+ home.getActivePromotionHeaderMessage() + " promotion is displayed in 3rd");
		} else {
			logger.log(LogStatus.PASS, "Watch Astro Without TV promotion is in 3rd sequence");
			Assert.assertTrue(true, "Watch Astro Without TV promotion is in 3rd sequence");
		}
	}

	@Test(description = "Verify that the Astro GO campaign is not the last of the X number of available campaigns", groups = {"func" }, priority = 1)
	public void TestAstroGOCampaign() throws Exception {
		logger = report.startTest("Verify Astro GO Campaign");
		int totalCampaigns = home.getFlexNavigatorsCount();
		logger.log(LogStatus.INFO, "Total campaigns found" + totalCampaigns);
		home.clickOnFlexNavNumber(Integer.toString(totalCampaigns));
		logger.log(LogStatus.INFO, "Verifying last campaign");
		if (!home.activePromotionHeaderMessage("All Astro customers can now enjoy Astro GO, free")) {
			logger.log(LogStatus.PASS, "Watch Astro Without TV promotion is not the last campaign");
			Assert.assertTrue(true, "Watch Astro Without TV promotion is not the last campaign");
		} else {
			logger.log(LogStatus.FAIL, "Watch Astro Without TV promotion is the last campaign");
			Assert.assertTrue(false, "Watch Astro Without TV promotion is the last campaign");
		}
	}

	@Test(description = "Verify Sign Up and Register for AstroID if the Astro GO campaign is not the last one", dependsOnMethods = {"TestAstroGOCampaign" }, groups = { "func" }, priority = 2)
	@Parameters({ "fname", "lname", "astroid", "email", "password" })
	public void TestSignUpRegister(String firstName, String lastName, String astroID, String email, String password)
			throws Exception {
		String generatedAstroID, generatedEmail = "";
		logger = report.startTest("Verify Registration");
		int totalCampaigns = home.getFlexNavigatorsCount();
		for (int i = 1; i <= totalCampaigns; i++) {
			home.clickOnFlexNavNumber(Integer.toString(i));
			if (home.activePromotionHeaderMessage("All Astro customers can now enjoy Astro GO, free")) {
				home.clickOnActiveFlexLink();
				logger.log(LogStatus.INFO, "Clicked on the Astro Go Banner link");
				Libraries.waitForElement(Libraries.getLocator(ObjectMap.ASTRO_GO_PAGE_BANNER_TITLE_CSS));
				logger.log(LogStatus.INFO, "Astro Go Page displayed");
				break;
			}
		}
		common.clickOnRegisterButton();
		logger.log(LogStatus.INFO, "Fill Registration form");
		generatedAstroID = registration.generateAstroID(astroID);
		generatedEmail = registration.generateEmailAddress(email);
		registration.enterFirstName(firstName).enterLastName(lastName).enterAstroID(generatedAstroID)
				.enterEmailAddress("invalid").enterPassword("");
		if(registration.emailErrorMessageDisplayed()){
			logger.log(LogStatus.PASS,"Invalid email address error message displayed. Error message is "+registration.getEmailAddressError());
		} else {
			logger.log(LogStatus.PASS,"Invalid email address error message notdisplayed");
		}
		registration.clearEmailAddress().enterEmailAddress(generatedEmail).enterPassword(password).clickIAgreeSignUp();
		if (registration.getRegistrationComletionMessage().contains("Registration Completed")) {
			logger.log(LogStatus.PASS,
					"Registration success for AstroID [" + generatedAstroID + "] with Email Address [" + generatedEmail
							+ "]. Message displayed as [" + registration.getRegistrationComletionMessage() + "]");
			Assert.assertTrue(true);
		} else {
			logger.log(LogStatus.FAIL,
					"Registration failure for AstroID [" + generatedAstroID + "] with Email Address [" + generatedEmail
							+ "]. Message displayed as [" + registration.getRegistrationComletionMessage() + "]");
			Assert.assertTrue(false);
		}
	}
}

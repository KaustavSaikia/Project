package com.astro.testing.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.astro.testing.map.ObjectMap;
import com.astro.testing.page.HomePage;
import com.astro.testing.page.Libraries;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 1. To test the performance and loading time of a website </br>
 * &nbsp&nbsp&nbsp a. Load www.astro.com.my </br>
 * &nbsp&nbsp&nbsp b. Verify the page loads within .1 seconds – capture failure; but go to next step (skip any loading page that appears and go to main website) </br>
 * &nbsp&nbsp&nbsp c. Verify the page loads within 5 seconds </br>
 * &nbsp&nbsp&nbsp d. Verify the page loads completely without error </br>
 * &nbsp&nbsp&nbsp&nbsp&nbsp i. Hint; HTML may contain standard text within the footer that can be used as indicator </br>
 * &nbsp&nbsp&nbsp e. Ensure none of the links within this page results in a non-200 header response </br>
 * &nbsp&nbsp&nbsp&nbsp&nbsp i. iterate through all URLs including those not </br>
 * &nbsp&nbsp&nbsp f. For every step above capture the output and response times. </br>

 * @author Kaustav
 *
 */
public class AstroQAAssignmentOne extends BaseTest {
	
	HomePage home=new HomePage();
	@Test(description = "Load www.astro.com.my and verify the page loads within .1 seconds", groups = {"func" }, priority = 0)
	@Parameters({ "url" })
	public void TestAdPageLoad(String url) throws Exception {
		logger = report.startTest("Verify Landing Page Load");
		long startTime = Libraries.getTimeInMiliSecs();
		Libraries.launchSite(url);
		long endTime = Libraries.getTimeInMiliSecs();
		long time = (endTime - startTime) / 1000;
		System.out.println(time);
		if (time <= 1000) {
			logger.log(LogStatus.FAIL, "Page didn't load within 1 sec. It took " + time + " secs.");
			Assert.assertTrue(time <= 1000, "Page didn't load within 1 sec. It took " + time + " secs.");
		} else {
			logger.log(LogStatus.PASS, "Site Launched in " + time + " secs.");
		}
	}

	@Test(description = "Verify the page loads within 5 seconds", groups = { "func" }, priority = 1)
	public void TestHomePageLoad() throws Exception {
		logger = report.startTest("Verify Home Page Load");
		Libraries.waitForElement(Libraries.getLocator(ObjectMap.CONTINUE_TO_ASTRO_ID));
		home.continueToAstro();
		long startTime = Libraries.getTimeInMiliSecs();
		home.waitForHomePage();
		long endTime = Libraries.getTimeInMiliSecs();
		long time = (endTime - startTime) / 1000;
		if (time <= 5000) {
			logger.log(LogStatus.FAIL, "Home Page didn't load within 5 sec. It took " + time + " secs.");
			Assert.assertTrue(time <= 5000, "Home Page didn't load within 5 sec. It took " + time + " secs.");
		} else {
			logger.log(LogStatus.PASS, "Home Page launched in " + time + " secs.");
		}
	}

	@Test(description = "Ensure none of the links within this page results in a non-200 header response", groups = { "func" }, priority=2)
	public void TestHomePageLinks() throws Exception {
		logger = report.startTest("Verify Links Response Code");
		String URL, brokenURL = "";
		List<String> BrokenURLs = new ArrayList<String>();

		List<WebElement> links = Libraries.findAllLinks();
		logger.log(LogStatus.INFO, "Total " + links.size() + " links found in home page");
		logger.log(LogStatus.INFO, "Verifying links for 200 response code");
		for (WebElement Link : links) {
			URL = Link.getAttribute("href");
			int statusCode = Libraries.verifyURLStatus(URL);
			if (statusCode != 200) {
				BrokenURLs.add(URL);
				brokenURL += URL + ":" + statusCode + ", ";
			}
		}
		if (BrokenURLs.size() > 0) {
			System.out.println(brokenURL);
			logger.log(LogStatus.FAIL, BrokenURLs.size() + " URLs response status are not 200");
			logger.log(LogStatus.FAIL, "Broken Links with status codes are:- " + brokenURL);
			Assert.assertTrue(false, "200 status code not foud for " + BrokenURLs.size()
					+ " total links. The links are:- " + BrokenURLs);
		} else {
			logger.log(LogStatus.PASS, "All links are pass");
		}
	}

	@Test(description = "For every URL capture the output and response times", groups = { "func" }, priority=3)
	@Parameters({ "url" })
	public void TestURLsResponseTime(String url) throws Exception {
		logger = report.startTest("Verify Links Response Time");
		List<WebElement> links = Libraries.findAllLinks();
		logger.log(LogStatus.INFO, "Total " + links.size() + " links found in home page");

		logger.log(LogStatus.INFO, "Verifying All links reponse time one after another");
		for (WebElement Link : links) {
			long startTime = Libraries.getTimeInSecs();
			String URL = Link.getAttribute("href");
			int statusCode = Libraries.verifyURLStatus(URL);
			driver.navigate().to(URL);
			long endTime = Libraries.getTimeInSecs();
			long time = endTime - startTime;
			logger.log(LogStatus.PASS,
					URL + " -- status code is:- " + statusCode + " and loaded in " + time + " secs.");
			driver.navigate().to(url);
			Libraries.waitForElement(Libraries.getLocator(ObjectMap.HOME_PAGE_SITE_FOOTER_WRAPPER_CSS));
		}
	}
}
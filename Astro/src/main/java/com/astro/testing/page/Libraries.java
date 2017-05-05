package com.astro.testing.page;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.astro.testing.test.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Kaustav Saikia
 *
 */
public class Libraries extends BaseTest{
	protected static DateFormat dfTime = new SimpleDateFormat("hh:mm:ss a");
	protected static DateFormat dfDateTime = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
	
	public static long getTimeInSecs(){
		long secs=0;
		long miliSecs=System.currentTimeMillis();
		secs=TimeUnit.MILLISECONDS.toSeconds(miliSecs);
		return secs;
	}
	public static long getTimeInMiliSecs(){
		long miliSecs=System.currentTimeMillis();
		return miliSecs;
	}
	/**
	 * Launch site based on the URL provided
	 * 
	 * @param url
	 */
	public static void launchSite(String url){
		logger.log(LogStatus.INFO, "Launching Site");
		driver.get(url);
		logger.log(LogStatus.INFO, "Site launched "+url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/**
	 * Wait for element to display
	 * 
	 * @param locator
	 */
	public static void waitForElement(By locator) {
		logger.log(LogStatus.INFO, "Waiting for locator to display" +locator);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * Get the Response code of URL
	 * 
	 * @param URL
	 * @return reponseCode
	 */
	public static int verifyURLStatus(String URL) {
		HttpResponse response = null;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			response = client.execute(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.getStatusLine().getStatusCode();
	}
	
	/**
	 * Returns all the Links on a page
	 * 
	 * @return List
	 * @throws Exception
	 */
	public static List<WebElement> findAllLinks() throws Exception {
		
		
		List<WebElement> pageLinks = driver.findElements(By.tagName("a"));
		List<WebElement> finalPageLinks = new ArrayList();
		for (WebElement element : pageLinks) {
			if(element.getAttribute("href") != null){
				  finalPageLinks.add(element);
			  }	 
		  }
		return finalPageLinks;
	}
	
	/**
	 * Resolves the Enum to convert it to Selenium's native By type
	 * 
	 * @param elementName
	 *            Enum name, For ex: GOOGLE_SEARCH_ID
	 * @throws Exception
	 *             Throws exception if the Enum name does not end with any of
	 *             the predefined method locator type
	 */
	public static By getLocator(Object elementName) throws Exception {
		if (((Enum<?>) elementName).name().toString().endsWith("_ID")) {
			return By.id(elementName.toString());
		} else if (((Enum<?>) elementName).name().toString().endsWith("_NAME")) {
			return By.name(elementName.toString());
		} else if (((Enum<?>) elementName).name().toString().endsWith("_CLASS")) {
			return By.className(elementName.toString());
		} else if (((Enum<?>) elementName).name().toString().endsWith("_CSS")) {
			return By.cssSelector(elementName.toString());
		} else if (((Enum<?>) elementName).name().toString().endsWith("_LINK")) {
			return By.linkText(elementName.toString());
		} else if (((Enum<?>) elementName).name().toString().endsWith("_PLINK")) {
			return By.partialLinkText(elementName.toString());
		} else if (((Enum<?>) elementName).name().toString().endsWith("_TAG")) {
			return By.tagName(elementName.toString());
		} else if (((Enum<?>) elementName).name().toString().endsWith("_XPATH")) {
			return By.xpath(elementName.toString());
		}
		throw new Exception("Unable to handle the locator type: "
				+ elementName + ". Locator name should end with _ID/_NAME/"
				+ "_CLASS/_CSS/_LINK/_PLINK/_TAG/_XPATH");
	}
	
	/**
	 * Captures screenshot and stores in the project's Screenshot folder
	 * 
	 * @param driver
	 * @param screenshotName
	 * @return screenshotPath
	 */
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\Screenshots\\"+screenshotName+".png";
        File destination = new File(dest);
        try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}        
        return dest;
	}
	
	/**
	 * Get the current date and time
	 * 
	 * @return String 
	 */
	public static String getLocalDateTime(){
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddsshh");
		String text = date.format(formatter);
		return text;
	}
}
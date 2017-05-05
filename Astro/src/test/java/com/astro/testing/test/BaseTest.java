package com.astro.testing.test;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.astro.testing.page.Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Base Test class with the setup and teardown of tests
 * @author Kaustav Saikia
 *
 */
public class BaseTest {

	private static final Logger log = Logger.getLogger(BaseTest.class);
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger; 
	public static String myCurrentDir;
	
	@BeforeSuite(alwaysRun = true, groups = { "setUp" })
	@Parameters({ "browser", "reportName" })
	public void setUp(String browser, String reportName) throws Exception {
		myCurrentDir = System.getProperty("user.dir");
		report=new ExtentReports(myCurrentDir+"/test-output/"+reportName+".html", true);
		
		report.addSystemInfo("Host Name", "Astro")
        .addSystemInfo("Environment", "Astro Environment")
        .addSystemInfo("User Name", "Kaustav Saikia")
        .addSystemInfo("Browser",browser);
		
		report.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\ExtentReportConfig\\extent-config.xml"));
		
		launhBrowser(browser);
	}
	
	@AfterMethod(groups = { "results" })
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			String screenShotPath = Libraries.captureScreenshot(driver, result.getName()+Libraries.getLocalDateTime());
			logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addScreenCapture(screenShotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		report.endTest(logger);
	}
	
	@AfterSuite(alwaysRun = true, groups = { "teardown" })
	public void tearDown() {
		report.endTest(logger);
		report.flush();
		driver.quit();
	}
	/**
	 * Launch browser function to launch browser based on the browser parameter passed from the test xml file
	 * @param browser
	 */
	public void launhBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")){
			log.info("Launching Chrome browser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver(options);
			log.info("Chrome browser launced successfully");
		} else if(browser.equalsIgnoreCase("ie")) {
			log.info("Launching Chrome browser");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/src/test/resources/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("Chrome browser launced successfully");			
		}
	}

}
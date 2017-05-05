package com.astro.testing.test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AndroidMobile {
	AndroidDriver driver;
	Dimension size;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	@BeforeTest
    public void setUp() throws Exception {
        String appInfo="installed"; 
        if(appInfo.equalsIgnoreCase("Installed")){
        	alreadyInstlledAppCapabilities("MotoG"); // Need to provide the device to test on
        } else {
        	installTheAppCapabilities("", "");// Need to provide the device to test on
        }
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
	
	@Test(description = "Swip the Screens to display the Login Screen", priority = 0)
	public void TestSwipeToLaunchLogin() throws InterruptedException {
		System.out.println("Launched");
		for(int i=0;i<5;i++){
			swipeScreen();	
		}
	}
	
	
	@Test(description = "Enter Credentials", priority = 0)
	public void EnterCredentials() throws InterruptedException {
		System.out.println("Enter credentials");
		driver.findElement(By.id("com.astro.astroview:id/et_input")).sendKeys("testuser@gmail.com");
		//repeat the password
		driver.findElement(By.id("com.astro.astroview:id/btn_ftu_login")).click();
		//Verify the Home Page
	}
	
	public void swipeScreen() throws InterruptedException{
		//Get the size of the screen and swip accordingly
		size = driver.manage().window().getSize();
		System.out.println(size);
		int startx = (int) (size.width * 0.70);
		int endx = (int) (size.width * 0.30);
		int starty = size.height / 2;
		System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

		// Swipe from Left to Right.
		driver.swipe(endx, starty, startx, starty, 3000);
		Thread.sleep(2000);
	}
	
	public void alreadyInstlledAppCapabilities(String deviceName){
        capabilities.setCapability("deviceName", deviceName); 
        capabilities.setCapability("autoWebview", true);
		capabilities.setCapability("newCommandTimeout", 120);
        capabilities.setCapability("appPackage", "com.astro.astroview");
        capabilities.setCapability("appActivity", "com.astro.astroview.activities.LauncherActivity");
        capabilities.setCapability("appWaitActivity", "com.astro.astroview.activities.LauncherActivity");
	}
	
	public void installTheAppCapabilities(String deviceName, String apkPath){
        capabilities.setCapability("deviceName", deviceName); 
        capabilities.setCapability("autoWebview", true);
		capabilities.setCapability("newCommandTimeout", 120);
        capabilities.setCapability("app", apkPath);
	}
}

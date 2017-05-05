<b>Prerequisites: -</b>

1) Below software needs to be present in the system: -
    > Java 8 <br>
    > Maven <br>
    > Eclipse or similar iDEs <br>
    > Appium <br>

To run the test from Eclipse below plugins should be installed: -
    > TestNG Plugin (Test Framework)
    > Install Plugins Android Studio > https://marketplace.eclipse.org/content/android-development-tools-eclipse

Github project directory: -  https://github.com/KaustavSaikia/Project

<b><a href="https://github.com/KaustavSaikia/Project/tree/master/Astro%20Java%20Doc">Astro Java Doc </a></b> : - Cotains the java doc of the project with all the details of the functions / libraries. Download the folder and open index.html which has reference to all the packages and classes.

<b><a href="https://github.com/KaustavSaikia/Project/tree/master/Astro">Astro </a></b>: - QA Assignment Level 1 and QA Assignment Level 2 test automation codes are committed in this folder. The project framework is developed based on the Page Object Model with Objects, Pages and Test classes keppt seperate. 

Checkout / Download the project to your local system. 

The project is a maven project so please install all the prerequisites as mentioned above. 

Project Structure:- 

    Astro
      src/main/java 
        > com.astro.testing.map 
        >com.astro.testing.page 
      src/test/java
        > com.astro.testing.test 
      src/test/resources
        > Drivers
        > ExtentReportConfig
      Suite
        > QA_Assignment_Level_1.xml 
        > QA_Assignment_Level_2.xml

How to run the tests: - 

    Go to the folder "Suite" in the project.

    Right click on the below files one after another  
        > QA_Assignment_Level_1.xml 
        > QA_Assignment_Level_2.xml

    Go to Run As > Click on TestNG Suite

How / Where to check the execution reports: -

    One the execution is complete reports would be generated in the "test-output" folder of the project.

     Two type of reports would be generated: - 
        > TestNG Report 
            Default TestNG reports
        > Extent Report
            Extent Report names are parameterised and provided in the execution xml files
                 > AstroAssignment1.html
                 > AstroAssignment2.html

<b><a href="https://github.com/KaustavSaikia/Project/tree/master/Astro_Mobile">Astro_Mobile </a></b>: - QA Assignment Level 3 (Mobile) test codes committed.<br /> In this assignment download links for Android and iOS mobile were provided. There are some limitations to automate the already installed apps. The details are as below:- <br />
&nbsp; &nbsp; &nbsp;1) <b>iOS Mobile</b>: To auitomate iOS app we would need the app source code base. Apple don't allow directly to interat with the app, we need to sign the app with Apple developer profile and generate the .ipa file to perform automation. <br />
&nbsp; &nbsp; &nbsp;2) <b>Android app</b>: - <br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; i) To automate already installed app we need to find out the "appPackage" and "appActivity". We can find out these using App Info app (available in play store). I got the information for Astro Mobile app, the details are appPackage="com.astro.astroview" and appActivity= "com.astro.astroview.activities.LauncherActivity" but after launching the app its thwoing exception. Looks like debug is not enabled for the app for which I would need the Manifest file. <br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;ii) If team provide the debug android app I can automate it without any issue. </p>
<p>For the above reason I couldn't develop the test automation code for mobile. </p>
<p>I have create a class with the basic test code to run a test in Android mobile with the Appium server details so that you can review the code. If you run the test it will launch the app but will exit with an exception. </p>

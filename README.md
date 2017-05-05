<b>Prerequisites: -</b>

1) Below software needs to be present in the system: -
    > Java 8
    > Maven
    > Eclipse or similar iDEs
    > Appium

To run the test from Eclipse below plugins should be installed: -
    > TestNG Plugin (Test Framework)
    > Install Plugins Android Studio > https://marketplace.eclipse.org/content/android-development-tools-eclipse

Github project directory: -  https://github.com/KaustavSaikia/Project

<b>Astro Java Doc</b> : - Cotains the java doc of the project with all the details of the functions / libraries. Download the folder and open index.html which has reference to all the packages and classes.

<b>Astro </b>: - QA Assignment Level 1 and QA Assignment Level 2 test automation codes are committed in this folder. The project framework is developed based on the Page Object Model with Objects, Pages and Test classes keppt seperate. 

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

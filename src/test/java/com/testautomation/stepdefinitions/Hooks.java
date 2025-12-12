package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.testautomation.base.TestBase;
import com.testautomation.extendreport.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Hooks {

    private static ExtentReports extent;
    public static ExtentTest _scenario;

    @Before
    public void setUp(Scenario scenario) {
        extent = ExtentManager.getInstance();

        Hooks._scenario = extent.createTest(scenario.getName());

        if (TestBase.getDriver() == null) {
            TestBase.getDriver();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        byte[] screenshotBytes = null;
        String screenshotPath = "";

        try {
            WebDriver driver = TestBase.getDriver();
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);


                screenshotPath = TestBase.captureScreenshot(scenario.getName());


                if (screenshotBytes != null && screenshotBytes.length > 0) {
                    scenario.attach(screenshotBytes, "image/png", "Screenshot");
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la capture du screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        if (scenario.isFailed()) {
            String errorMessage = "Test Failed - See step details above for error information";
            
            if (!screenshotPath.isEmpty()) {
                try {
                    File screenshotFile = new File(screenshotPath);
                    if (screenshotFile.exists()) {
                        String relativePath = screenshotFile.getName();
                        Hooks._scenario.fail(errorMessage,
                                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                        System.out.println("Screenshot d'échec ajouté au rapport: " + relativePath);
                    } else {
                        Hooks._scenario.fail(errorMessage + "\nScreenshot file not found: " + screenshotPath);
                    }
                } catch (Exception e) {
                    Hooks._scenario.fail(errorMessage + "\nError attaching screenshot: " + e.getMessage());
                }
            } else {
                Hooks._scenario.fail(errorMessage);
            }
        } else {
            if (!screenshotPath.isEmpty()) {
                try {
                    File screenshotFile = new File(screenshotPath);
                    if (screenshotFile.exists()) {
                        String relativePath = screenshotFile.getName();
                        Hooks._scenario.pass("Test Passed",
                                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                    } else {
                        Hooks._scenario.pass("Test Passed - Screenshot file not found");
                    }
                } catch (Exception e) {
                    Hooks._scenario.pass("Test Passed - Error attaching screenshot: " + e.getMessage());
                }
            } else {
                Hooks._scenario.pass("Test Passed");
            }
        }
        
        extent.flush();
        TestBase.closeDriver();
    }
}


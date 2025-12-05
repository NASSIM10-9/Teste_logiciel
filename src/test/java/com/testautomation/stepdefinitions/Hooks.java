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
    public static ExtentTest scenario;

    @Before
    public void setUp(Scenario scenario) {
        extent = ExtentManager.getInstance();

        Hooks.scenario = extent.createTest(scenario.getName());

        if (TestBase.getDriver() == null) {
            TestBase.getDriver();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        // Always capture screenshot regardless of pass/fail
        byte[] screenshotBytes = null;
        String screenshotPath = "";

        try {
            WebDriver driver = TestBase.getDriver();
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

                // Save screenshot to file
                screenshotPath = TestBase.captureScreenshot(scenario.getName());

                // Embed screenshot into Cucumber report
                if (screenshotBytes != null && screenshotBytes.length > 0) {
                    scenario.attach(screenshotBytes, "image/png", "Screenshot");
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la capture du screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        // Attach screenshot to ExtentReports using file path
        if (scenario.isFailed()) {
            String errorMessage = "Test Failed - See step details above for error information";

            if (!screenshotPath.isEmpty()) {
                try {
                    File screenshotFile = new File(screenshotPath);
                    if (screenshotFile.exists()) {
                        // Use relative path from report location (just filename since both are in target/)
                        String relativePath = screenshotFile.getName();
                        Hooks.scenario.fail(errorMessage,
                                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                    } else {
                        Hooks.scenario.fail(errorMessage + "\nScreenshot file not found: " + screenshotPath);
                    }
                } catch (Exception e) {
                    Hooks.scenario
                            .fail(errorMessage + "\nScreenshot path: " + screenshotPath + "\nError: " + e.getMessage());
                    System.err.println("Erreur lors de l'ajout du screenshot: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Hooks.scenario.fail(errorMessage);
            }
        } else {
            if (!screenshotPath.isEmpty()) {
                try {
                    File screenshotFile = new File(screenshotPath);
                    if (screenshotFile.exists()) {
                        // Use relative path from report location (just filename since both are in target/)
                        String relativePath = screenshotFile.getName();
                        Hooks.scenario.pass("Test Passed",
                                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                    } else {
                        Hooks.scenario.pass("Test Passed - Screenshot file not found: " + screenshotPath);
                    }
                } catch (Exception e) {
                    Hooks.scenario
                            .pass("Test Passed - Screenshot path: " + screenshotPath + "\nError: " + e.getMessage());
                    System.err.println("Erreur lors de l'ajout du screenshot: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Hooks.scenario.pass("Test Passed");
            }
        }
        extent.flush();
        TestBase.closeDriver();
    }
}


package com.testautomation.base;

import com.testautomation.utils.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase {

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static void closeDriver() {
        DriverManager.quitDriver();
    }

    public static String captureScreenshot(String scenarioName) {
        WebDriver currentDriver = getDriver();
        if (currentDriver == null) {
            System.err.println("Driver non initialisé, impossible de capturer le screenshot");
            return "";
        }

        try {
            File targetDir = new File("target");
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String fileName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_") + "_" + timeStamp + ".png";
            File screenshotFile = new File(targetDir, fileName);

            TakesScreenshot ts = (TakesScreenshot) currentDriver;
            byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

            FileUtils.writeByteArrayToFile(screenshotFile, screenshotBytes);

            String absolutePath = screenshotFile.getAbsolutePath();
            System.out.println("Screenshot enregistré: " + absolutePath);

            return absolutePath;

        } catch (IOException e) {
            System.err.println("Erreur lors de la capture d'écran: " + e.getMessage());
            return "";
        }
    }
}


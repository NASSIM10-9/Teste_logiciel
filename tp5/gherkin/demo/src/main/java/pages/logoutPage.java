package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class logoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver and WebDriverWait
    public logoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLogoutButton() {
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/logout']")));
        logoutBtn.click();
    }

    public String getLogoutMessage() {
        WebElement logoutMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash.success")));
        return logoutMsg.getText();
    }
}

package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForVisible(By locator, long seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // New common utilities
    protected boolean isVisible(By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean scrollToElement(By locator, int maxScrolls) {
        for (int i = 0; i < maxScrolls; i++) {
            if (isDisplayed(locator)) {
                return true;
            }
            scrollDown();
        }
        return isDisplayed(locator);
    }

    protected void scrollDown() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", 0);
            params.put("top", (int) (driver.manage().window().getSize().height * 0.2));
            params.put("width", driver.manage().window().getSize().width);
            params.put("height", (int) (driver.manage().window().getSize().height * 0.6));
            params.put("direction", "down");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
        } catch (Exception ignored) {}
    }

    protected void ensureAppRunning() {
        try {
            driver.getPageSource();
        } catch (Exception e) {
            try {
                DriverFactory.restartApp(driver);
            } catch (Exception ignored) {}
        }
    }
}



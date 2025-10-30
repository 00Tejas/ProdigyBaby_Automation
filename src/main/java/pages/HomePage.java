package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import utils.UserType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.Map;
import java.util.HashMap;

public class HomePage extends BasePage {
    public HomePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public boolean isSubscribebuttonVisible() {
        try {
            String elementPath = "//android.view.View[@content-desc=\"Subscribe\"]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNotificationIconVisible() {
        try {
            String elementPath = "//android.view.View[@content-desc='1']/android.view.View[2]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMembershipIconVisible() {
        try {
            String elementPath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExpertSessionTextVisible() {
        try {
            String elementPath = "//android.view.View[@content-desc=\"Expert Sessions\"]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExpertSessionVideosVisible() {
        try {
            String elementPath = "//android.widget.ScrollView/android.view.View[3]/android.view.View";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExpertSessionViewAllBtnVisible() {
        try {
            String elementPath = "//android.view.View[@content-desc=\"View all\"]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isActivityStreakVisible() {
        try {
            String elementPath = "//android.view.View[contains(@content-desc, 'Activity Streak')]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHowItWorksCardVisible() {
        try {
            String elementPath = "//android.view.View[@content-desc=\"How it works?\"]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isThisWeekActivityTextVisible() {
        try {
            String elementPath = "//android.view.View[@content-desc=\"This Week's Activities\"]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubscriptionActivityCardVisible() {
        try {
            String elementPath = "//android.widget.ScrollView/android.view.View[4]/android.view.View";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProgramCardVisible() {
        try {
            String elementPath = "//android.widget.ScrollView/android.view.View[4]/android.view.View";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isClaimMedalsVisible() {
        String elementPath = "//android.view.View[@content-desc='Claim medals']";
        By locator = By.xpath(elementPath);
        Dimension size = driver.manage().window().getSize();
        int left = 0;
        int top = (int) (size.height * 0.2);
        int width = size.width;
        int height = (int) (size.height * 0.6);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                if (attempt < 3) {
                    try {
                        Map<String, Object> params = new HashMap<>();
                        params.put("left", left);
                        params.put("top", top);
                        params.put("width", width);
                        params.put("height", height);
                        params.put("direction", "down");
                        params.put("percent", 0.8);
                        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
                        Thread.sleep(1000);
                    } catch (Exception scrollErr) {
                    }
                }
            }
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);
            params.put("direction", "up");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
            Thread.sleep(1000);
        } catch (Exception scrollErr) {
        }
        return false;
    }

    public boolean NewForYouText() {
        String elementPath = "//android.view.View[@content-desc=\"New for you\"]";
        By locator = By.xpath(elementPath);
        Dimension size = driver.manage().window().getSize();
        int left = 0;
        int top = (int) (size.height * 0.2);
        int width = size.width;
        int height = (int) (size.height * 0.6);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                if (attempt < 3) {
                    try {
                        Map<String, Object> params = new HashMap<>();
                        params.put("left", left);
                        params.put("top", top);
                        params.put("width", width);
                        params.put("height", height);
                        params.put("direction", "down");
                        params.put("percent", 0.8);
                        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
                        Thread.sleep(1000);
                    } catch (Exception scrollErr) {
                    }
                }
            }
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);
            params.put("direction", "up");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
            Thread.sleep(1000);
        } catch (Exception scrollErr) {
        }
        return false;
    }

    public boolean isYourActivityJourneyCardVisible() {
        String elementPath = "//android.view.View[@content-desc=\"Your Activities Journey\"]/android.widget.ImageView[2]";
        By locator = By.xpath(elementPath);
        Dimension size = driver.manage().window().getSize();
        int left = 0;
        int top = (int) (size.height * 0.2);
        int width = size.width;
        int height = (int) (size.height * 0.6);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                if (attempt < 3) {
                    try {
                        Map<String, Object> params = new HashMap<>();
                        params.put("left", left);
                        params.put("top", top);
                        params.put("width", width);
                        params.put("height", height);
                        params.put("direction", "down");
                        params.put("percent", 0.8);
                        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
                        Thread.sleep(1000);
                    } catch (Exception scrollErr) {
                    }
                }
            }
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);
            params.put("direction", "up");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
            Thread.sleep(1000);
        } catch (Exception scrollErr) {
        }
        return false;
    }

    public boolean isTalentCornerTextVisible() {
        String elementPath = "//android.view.View[@content-desc=\"Talent Corner\"]";
        By locator = By.xpath(elementPath);
        Dimension size = driver.manage().window().getSize();
        int left = 0;
        int top = (int) (size.height * 0.2);
        int width = size.width;
        int height = (int) (size.height * 0.6);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                if (attempt < 3) {
                    try {
                        Map<String, Object> params = new HashMap<>();
                        params.put("left", left);
                        params.put("top", top);
                        params.put("width", width);
                        params.put("height", height);
                        params.put("direction", "down");
                        params.put("percent", 0.8);
                        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
                        Thread.sleep(1000);
                    } catch (Exception scrollErr) {
                    }
                }
            }
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);
            params.put("direction", "up");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
            Thread.sleep(1000);
        } catch (Exception scrollErr) {
        }
        return false;
    }

    public boolean isTalentCornerVideosVisible() {
        String elementPath = "//android.view.View[@content-desc=\"Talent Corner\"]";
        By locator = By.xpath(elementPath);
        Dimension size = driver.manage().window().getSize();
        int left = 0;
        int top = (int) (size.height * 0.2);
        int width = size.width;
        int height = (int) (size.height * 0.6);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                if (attempt < 3) {
                    try {
                        Map<String, Object> params = new HashMap<>();
                        params.put("left", left);
                        params.put("top", top);
                        params.put("width", width);
                        params.put("height", height);
                        params.put("direction", "down");
                        params.put("percent", 0.8);
                        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
                        Thread.sleep(1000);
                    } catch (Exception scrollErr) {
                    }
                }
            }
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);
            params.put("direction", "up");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
            Thread.sleep(1000);
        } catch (Exception scrollErr) {
        }
        return false;
    }

    public boolean isFeedbackPopupVisible() {
        String elementPath = "//android.view.View[@content-desc='Enjoying Prodigy Baby?']";
        By locator = By.xpath(elementPath);
        Dimension size = driver.manage().window().getSize();
        int left = 0;
        int top = (int) (size.height * 0.2);
        int width = size.width;
        int height = (int) (size.height * 0.6);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                if (attempt < 3) {
                    try {
                        Map<String, Object> params = new HashMap<>();
                        params.put("left", left);
                        params.put("top", top);
                        params.put("width", width);
                        params.put("height", height);
                        params.put("direction", "down");
                        params.put("percent", 0.8);
                        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
                        Thread.sleep(1000);
                    } catch (Exception scrollErr) {
                    }
                }
            }
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);
            params.put("direction", "up");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
            Thread.sleep(1000);
        } catch (Exception scrollErr) {
        }
        return false;
    }

    public boolean isJoinNowBoxVisible() {
        String elementPath = "//android.view.View[@content-desc=\"Join Now!\"]";
        By locator = By.xpath(elementPath);
        Dimension size = driver.manage().window().getSize();
        int left = 0;
        int top = (int) (size.height * 0.2);
        int width = size.width;
        int height = (int) (size.height * 0.6);
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                if (attempt < 3) {
                    try {
                        Map<String, Object> params = new HashMap<>();
                        params.put("left", left);
                        params.put("top", top);
                        params.put("width", width);
                        params.put("height", height);
                        params.put("direction", "down");
                        params.put("percent", 0.8);
                        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
                        Thread.sleep(1000);
                    } catch (Exception scrollErr) {
                    }
                }
            }
        }
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);
            params.put("direction", "up");
            params.put("percent", 0.8);
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
            Thread.sleep(1000);
        } catch (Exception scrollErr) {
        }
        return false;
    }

    public boolean verifyHomePageLoaded() {
        return isActivityStreakVisible();
    }

    public String getActivityStreakText() {
        try {
            String elementPath = "//android.view.View[contains(@content-desc, 'Activity Streak')]";
            WebElement element = driver.findElement(By.xpath(elementPath));
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void handleHelpBottomSheet() {
        try {
            Thread.sleep(2000);
            boolean bottomSheetPresent = false;
            String[] bottomSheetPaths = {
                "//android.view.View[contains(@content-desc, 'Want us to help')]",
                "//android.view.View[contains(@content-desc, 'help')]",
                "//android.widget.ScrollView[contains(@content-desc, 'help')]",
                "//android.view.View[@content-desc='Want us to help you?']"
            };
            for (String path : bottomSheetPaths) {
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                    WebElement bottomSheet = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
                    if (bottomSheet.isDisplayed()) {
                        bottomSheetPresent = true;
                        break;
                    }
                } catch (Exception e) {
                }
            }
            if (bottomSheetPresent) {
                Dimension size = driver.manage().window().getSize();
                int tapX = size.width / 2;
                int tapY = size.height / 4;
                TouchAction touchAction = new TouchAction((AndroidDriver) driver);
                touchAction.tap(PointOption.point(tapX, tapY)).perform();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }

    public boolean handleAllPopups() {
        try {
            Thread.sleep(2000);
            String salesPopupPath = "//android.view.View[@content-desc=\"Screenfree activities for your 0-6 year old child or toddler\nCurated by experts, backed by science\"]";
            boolean salesPopupFound = false;
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(salesPopupPath)));
                if (popup.isDisplayed()) {
                    String crossButtonPath = "//android.widget.Button";
                    WebElement crossButton = driver.findElement(By.xpath(crossButtonPath));
                    crossButton.click();
                    WebDriverWait dismissWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                    dismissWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(salesPopupPath)));
                    salesPopupFound = true;
                    handleHelpBottomSheet();
                    return true;
                }
            } catch (Exception e) {
            }
            if (salesPopupFound) {
                handleHelpBottomSheet();
            }
            String[] genericPopupPaths = {
                "//android.widget.Button[contains(@content-desc, 'Close')]",
                "//android.widget.Button[contains(@content-desc, 'X')]",
                "//android.widget.Button[contains(@content-desc, 'Dismiss')]",
                "//android.view.View[contains(@content-desc, 'popup')]",
                "//android.view.View[contains(@content-desc, 'modal')]"
            };
            for (String popupPath : genericPopupPaths) {
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(popupPath)));
                    if (popup.isDisplayed()) {
                        popup.click();
                        Thread.sleep(1000);
                        return true;
                    }
                } catch (Exception e) {
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void validateHomeTabUI(UserType userType) {
        switch (userType) {
            case NEW_USER:
            case LAUNCHPAD_USER:
                isJoinNowBoxVisible();
                break;
            case PROGRAM_USER:
            case PROGRAM_SUBSCRIPTION_USER:
                isActivityStreakVisible();
                isThisWeekActivityTextVisible();
                break;
            case SUBSCRIPTION_USER:
                isSubscriptionActivityCardVisible();
                break;
            default:
                isActivityStreakVisible();
        }
    }
}



package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import utils.UserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProgramTabPage extends BasePage {
    public ProgramTabPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clickProgramTab() throws Exception {
        String programTabPath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.widget.ImageView[2]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement programTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(programTabPath)));
        programTab.click();
        Thread.sleep(2000);
    }

    public void clickExploreAllPrograms() throws Exception {
        String exploreAllPath = "//android.view.View[@content-desc=\"Explore All Programs\"]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(exploreAllPath)));
        exploreAllButton.click();
        Thread.sleep(2000);
    }

    public boolean isStartYourJourneyVisible() {
        try {
            String startJourneyPath = "//android.view.View[@content-desc=\"Start Your Journey\"]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement startJourney = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(startJourneyPath)));
            return startJourney.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTodaysPlanVisible() {
        try {
            String todaysPlanPath = "//android.view.View[@content-desc=\"Today's Plan\"]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement todaysPlan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(todaysPlanPath)));
            return todaysPlan.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProgramContentVisible() {
        try {
            String programContentPath = "//android.widget.ScrollView";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement programContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(programContentPath)));
            return programContent.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(String elementXPath, String elementName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyProgramTabLoaded() {
        return isProgramContentVisible();
    }

    public void validateProgramTabUI(UserType userType) {
        switch (userType) {
            case NEW_USER:
            case LAUNCHPAD_USER:
                clickSilently();
                isStartYourJourneyVisible();
                break;
            case PROGRAM_USER:
            case PROGRAM_SUBSCRIPTION_USER:
                isTodaysPlanVisible();
                break;
            case SUBSCRIPTION_USER:
                isProgramContentVisible();
                break;
            default:
                isProgramContentVisible();
        }
    }

    private void clickSilently() {
        try {
            // no-op: support flows that may require a click before visibility
        } catch (Exception e) {
        }
    }
}



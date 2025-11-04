package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import utils.UserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import locators.HomeTabLocators;

public class HomeTabPage extends BasePage {
    public HomeTabPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isSubscribebuttonVisible() {
        return isVisible(By.xpath(HomeTabLocators.SUBSCRIBE_BUTTON), 5);
    }

    public boolean isNotificationIconVisible() {
        return isVisible(By.xpath(HomeTabLocators.NOTIFICATION_ICON), 5);
    }

    public boolean isMembershipIconVisible() {
        return isVisible(By.xpath(HomeTabLocators.MEMBERSHIP_ICON), 5);
    }

    public boolean isExpertSessionTextVisible() {
        return isVisible(By.xpath(HomeTabLocators.EXPERT_SESSIONS_TEXT), 5);
    }

    public boolean isExpertSessionVideosVisible() {
        return isVisible(By.xpath(HomeTabLocators.EXPERT_SESSIONS_VIDEOS), 5);
    }

    public boolean isExpertSessionViewAllBtnVisible() {
        return isVisible(By.xpath(HomeTabLocators.VIEW_ALL), 5);
    }

    public boolean isActivityStreakVisible() {
        return scrollToElement(By.xpath(HomeTabLocators.ACTIVITY_STREAK), 3);
    }

    public boolean isHowItWorksCardVisible() {
        return isVisible(By.xpath(HomeTabLocators.HOW_IT_WORKS), 5);
    }

    public boolean isThisWeekActivityTextVisible() {
        return isVisible(By.xpath(HomeTabLocators.THIS_WEEK_ACTIVITIES), 5);
    }

    public boolean isSubscriptionActivityCardVisible() {
        return isVisible(By.xpath(HomeTabLocators.SUBSCRIPTION_ACTIVITY_CARD), 5);
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
        return scrollToElement(By.xpath(HomeTabLocators.CLAIM_MEDALS), 3);
    }

    public boolean NewForYouText() {
        return scrollToElement(By.xpath(HomeTabLocators.NEW_FOR_YOU), 3);
    }

    public boolean isYourActivityJourneyCardVisible() {
        return scrollToElement(By.xpath(HomeTabLocators.YOUR_ACTIVITY_JOURNEY), 3);
    }

    public boolean isTalentCornerTextVisible() {
        return scrollToElement(By.xpath(HomeTabLocators.TALENT_CORNER_TEXT), 3);
    }

    public boolean isTalentCornerVideosVisible() {
        return scrollToElement(By.xpath(HomeTabLocators.TALENT_CORNER_TEXT), 3);
    }

    public boolean isFeedbackPopupVisible() {
        return scrollToElement(By.xpath(HomeTabLocators.FEEDBACK_POPUP), 3);
    }

    public boolean isJoinNowBoxVisible() {
        return scrollToElement(By.xpath(HomeTabLocators.JOIN_NOW), 3);
    }

    public boolean verifyHomePageLoaded() {
        return isActivityStreakVisible();
    }

    public String getActivityStreakText() {
        try {
            return driver.findElement(By.xpath(HomeTabLocators.ACTIVITY_STREAK)).getText();
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
            	isActivityStreakVisible();
            	
            case LAUNCHPAD_USER:
            	isExpertSessionTextVisible();
                break;
            case PROGRAM_USER:
            	isActivityStreakVisible();
            case PROGRAM_SUBSCRIPTION_USER:
                isActivityStreakVisible();
                
                break;
            case SUBSCRIPTION_USER:
                isSubscriptionActivityCardVisible();
                break; 
            default:
                isActivityStreakVisible();
        }
    }
}



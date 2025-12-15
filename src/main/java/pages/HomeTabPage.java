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

    /**
     * Tap the Subscribe CTA if it is present.
     * @return true if tapped or already visible.
     */
    public boolean tapSubscribeButtonIfPresent() {
        try {
            By subscribeButton = By.xpath(HomeTabLocators.SUBSCRIBE_BUTTON);
            if (isVisible(subscribeButton, 5)) {
                click(subscribeButton);
                return true;
            }
        } catch (Exception e) {
            // ignore and return false
        }
        return false;
    }

    /**
     * Dismisses the child details popup that appears after onboarding.
     * This popup has a cross button that needs to be clicked.
     * @return true if popup was found and dismissed, false otherwise
     */
    public boolean dismissChildDetailsPopup() {
        try {
            System.out.println("🔍 Checking for child details popup after onboarding...");
            Thread.sleep(2000); // Wait for popup to appear
            
            By crossButton = By.xpath(HomeTabLocators.CHILD_DETAILS_POPUP_CROSS);
            if (isVisible(crossButton, 5)) {
                click(crossButton);
                Thread.sleep(1000);
                System.out.println("✅ Child details popup dismissed");
                return true;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Child details popup not found or already dismissed: " + e.getMessage());
        }
        return false;
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
            
            // Check for launchpad popup dismiss button
            try {
                WebDriverWait launchpadWait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement launchpadPopup = launchpadWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomeTabLocators.LAUNCHPAD_POPUP_DISMISS)));
                if (launchpadPopup.isDisplayed()) {
                    launchpadPopup.click();
                    Thread.sleep(1000);
                    System.out.println("✅ Launchpad popup dismissed");
                    handleHelpBottomSheet();
                    return true;
                }
            } catch (Exception e) {
                // Launchpad popup not found, continue
            }
            
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
        System.out.println("🔍 Validating Home Tab UI elements for " + userType + "...");
        
        switch (userType) {
            case NEW_USER:
                System.out.println("  ✓ Checking Activity Streak for NEW_USER...");
                boolean streakVisible = isActivityStreakVisible();
                System.out.println(streakVisible ? "  ✅ Activity Streak is visible" : "  ❌ Activity Streak not visible");
                
                System.out.println("  ✓ Checking This Week's Activities Text for NEW_USER...");
                boolean thisWeekVisible = isThisWeekActivityTextVisible();
                System.out.println(thisWeekVisible ? "  ✅ This Week's Activities Text is visible" : "  ❌ This Week's Activities Text not visible");
                break;
                
            case LAUNCHPAD_USER:
                System.out.println("  ✓ Checking Activity Streak for LAUNCHPAD_USER...");
                boolean streakVisibleLaunch = isActivityStreakVisible();
                System.out.println(streakVisibleLaunch ? "  ✅ Activity Streak is visible" : "  ❌ Activity Streak not visible");
                
                System.out.println("  ✓ Checking Expert Sessions Text for LAUNCHPAD_USER...");
                boolean expertVisible = isExpertSessionTextVisible();
                System.out.println(expertVisible ? "  ✅ Expert Sessions Text is visible" : "  ❌ Expert Sessions Text not visible");
                break;
                
            case PROGRAM_USER:
                System.out.println("  ✓ Checking Activity Streak for PROGRAM_USER...");
                boolean streakVisibleProg = isActivityStreakVisible();
                System.out.println(streakVisibleProg ? "  ✅ Activity Streak is visible" : "  ❌ Activity Streak not visible");
                
                System.out.println("  ✓ Checking Program Card for PROGRAM_USER...");
                boolean programCardVisible = isProgramCardVisible();
                System.out.println(programCardVisible ? "  ✅ Program Card is visible" : "  ❌ Program Card not visible");
                break;
                
            case PROGRAM_SUBSCRIPTION_USER:
                System.out.println("  ✓ Checking Activity Streak for PROGRAM_SUBSCRIPTION_USER...");
                boolean streakVisibleProgSub = isActivityStreakVisible();
                System.out.println(streakVisibleProgSub ? "  ✅ Activity Streak is visible" : "  ❌ Activity Streak not visible");
                break;
                
            case SUBSCRIPTION_USER:
                System.out.println("  ✓ Checking Subscription Activity Card for SUBSCRIPTION_USER...");
                boolean subCardVisible = isSubscriptionActivityCardVisible();
                System.out.println(subCardVisible ? "  ✅ Subscription Activity Card is visible" : "  ❌ Subscription Activity Card not visible");
                
                System.out.println("  ✓ Checking Membership Icon for SUBSCRIPTION_USER...");
                boolean membershipIconVisible = isMembershipIconVisible();
                System.out.println(membershipIconVisible ? "  ✅ Membership Icon is visible" : "  ❌ Membership Icon not visible");
                
                System.out.println("  ✓ Checking Activity Streak for SUBSCRIPTION_USER...");
                boolean streakVisibleSub = isActivityStreakVisible();
                System.out.println(streakVisibleSub ? "  ✅ Activity Streak is visible" : "  ❌ Activity Streak not visible");
                break;
        }
        
        System.out.println("✅ Home Tab UI validation completed for " + userType);
    }
}



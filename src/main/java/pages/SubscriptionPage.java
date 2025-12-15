	package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import locators.SubscriptionLocators;
import org.openqa.selenium.By;

/**
 * SubscriptionPage - Handles purchase/paywall validation and interactions.
 */
public class SubscriptionPage extends BasePage {
    public SubscriptionPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Wait for the paywall / plan sheet to become visible.
     * @return true if the sheet is visible.
     */
    public boolean waitForPaywall() {
        try {
            return isVisible(By.xpath(SubscriptionLocators.PAYWALL_DISMISS), 10)
                || isVisible(By.xpath(SubscriptionLocators.JOIN_NOW), 10);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate the paywall benefit text is visible.
     */
    public boolean isBenefitTextVisible() {
        try {
            return isVisible(By.xpath(SubscriptionLocators.PAYWALL_BENEFIT_TEXT), 8);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click the paywall continue button to proceed to the payment sheet.
     */
    public void clickPaywallContinue() {
        click(By.xpath(SubscriptionLocators.PAYWALL_CONTINUE_BUTTON));
    }

    /**
     * Click the Play Store subscribe button on the payment sheet.
     */
    public void clickPlayStoreSubscribe() {
        click(By.xpath(SubscriptionLocators.PLAY_SUBSCRIBE_BUTTON));
    }

    /**
     * Wait for the Play Store confirmation message after subscribing.
     * @return true if confirmation appears within the timeout.
     */
    public boolean waitForPurchaseConfirmation() {
        try {
            return isVisible(By.xpath(SubscriptionLocators.PLAY_CONFIRMATION_MESSAGE), 12);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Dismiss the paywall if it is present.
     */
    public void dismissPaywallIfVisible() {
        try {
            By dismiss = By.xpath(SubscriptionLocators.PAYWALL_DISMISS);
            if (isVisible(dismiss, 2)) {
                click(dismiss);
            }
        } catch (Exception ignored) { }
    }
}


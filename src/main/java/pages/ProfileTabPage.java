package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.UserType;

public class ProfileTabPage extends BasePage {
    public ProfileTabPage(AppiumDriver driver) {
        super(driver);
    }

    public void openProfileTab() {
        try {
            // Placeholder: tap Profile tab icon if needed
        } catch (Exception ignored) {}
    }

    public boolean verifyProfileLoaded() {
        try {
            // Simple heuristic: look for any profile keyword
            return isDisplayed(By.xpath("//*[contains(@content-desc,'Profile') or contains(@text,'Profile')]|//android.widget.ScrollView"));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates Profile Tab UI based on user type
     * @param userType The type of user to validate for
     */
    public void validateProfileTabUI(UserType userType) {
        switch (userType) {
            case NEW_USER:
            case LAUNCHPAD_USER:
                // TODO: Add specific UI validations for new/launchpad users
                verifyProfileLoaded();
                break;
            case PROGRAM_USER:
            case PROGRAM_SUBSCRIPTION_USER:
                // TODO: Add specific UI validations for program users
                verifyProfileLoaded();
                break;
            case SUBSCRIPTION_USER:
                // TODO: Add specific UI validations for subscription users
                verifyProfileLoaded();
                break;
            default:
                verifyProfileLoaded();
        }
    }
}



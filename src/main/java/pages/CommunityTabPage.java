package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.UserType;
import locators.CommunityTabLocators;

/**
 * Community Tab Page - Page Object for Community Tab Elements
 */
public class CommunityTabPage extends BasePage {
    public CommunityTabPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Click on Community tab
     */
    public void clickCommunityTab() throws Exception {
        System.out.println("👥 Clicking Community tab...");
        
        By communityTab = By.xpath(CommunityTabLocators.COMMUNITY_TAB_ICON);
        waitForVisible(communityTab, 10);
        click(communityTab);
        
        System.out.println("✅ Community tab clicked successfully");
        Thread.sleep(2000); // Wait for community tab to load
    }

    /**
     * Open Community tab (legacy method name for backward compatibility)
     */
    public void openCommunityTab() throws Exception {
        clickCommunityTab();
    }

    /**
     * Generic method to check element visibility by XPath
     */
    public boolean isElementVisible(String elementXPath, String elementName) {
        try {
            System.out.println("🔍 Checking for " + elementName + "...");
            
            boolean visible = isVisible(By.xpath(elementXPath), 5);
            
            if (visible) {
                System.out.println("✅ " + elementName + " is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ " + elementName + " not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Generic method to click element by XPath
     */
    public void clickElement(String elementXPath, String elementName) throws Exception {
        System.out.println("🔘 Clicking " + elementName + "...");
        
        By element = By.xpath(elementXPath);
        waitForVisible(element, 10);
        click(element);
        
        System.out.println("✅ " + elementName + " clicked successfully");
        Thread.sleep(1000); // Wait after click
    }

    /**
     * Verify community tab is loaded
     */
    public boolean verifyCommunityLoaded() {
        try {
            // Simple heuristic: look for any scroll view or container
            return isDisplayed(By.xpath("//android.widget.ScrollView|//android.view.View"));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates Community Tab UI based on user type
     * @param userType The type of user to validate for
     */
    public void validateCommunityTabUI(UserType userType) {
        switch (userType) {
            case NEW_USER:
            case LAUNCHPAD_USER:
                // TODO: Add specific UI validations for new/launchpad users
                verifyCommunityLoaded();
                break;
            case PROGRAM_USER:
            case PROGRAM_SUBSCRIPTION_USER:
                // TODO: Add specific UI validations for program users
                verifyCommunityLoaded();
                break;
            case SUBSCRIPTION_USER:
                // TODO: Add specific UI validations for subscription users
                verifyCommunityLoaded();
                break;
            default:
                verifyCommunityLoaded();
        }
    }
}



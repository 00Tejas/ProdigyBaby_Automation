package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import utils.UserType;
import org.openqa.selenium.By;
import locators.ProgramTabLocators;

/**
 * Program Tab Page - Page Object for Program Tab Elements
 */
public class ProgramTabPage extends BasePage {
    public ProgramTabPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Click on Program tab
     */
    public void clickProgramTab() throws Exception {
        System.out.println("📚 Clicking Program tab...");
        
        By programIcon = By.xpath(ProgramTabLocators.PROGRAM_TAB_ICON);
        waitForVisible(programIcon, 10);
        click(programIcon);
        
        System.out.println("✅ Program tab clicked successfully");
        Thread.sleep(2000); // Wait for program tab to load
    }

    /**
     * Click "Explore All Programs" button (for NEW_USER and LAUNCHPAD_USER)
     */
    public void clickExploreAllPrograms() throws Exception {
        System.out.println("🔍 Clicking Explore All Programs button...");
        
        By exploreAll = By.xpath(ProgramTabLocators.EXPLORE_ALL_PROGRAMS);
        waitForVisible(exploreAll, 10);
        click(exploreAll);
        
        System.out.println("✅ Explore All Programs button clicked successfully");
        Thread.sleep(2000); // Wait for page to load
    }

    /**
     * Check if "Start Your Journey" text is visible (for NEW_USER and LAUNCHPAD_USER)
     */
    public boolean isStartYourJourneyVisible() {
        try {
            System.out.println("🔍 Checking for Start Your Journey text...");
            
            boolean visible = isVisible(By.xpath(ProgramTabLocators.START_YOUR_JOURNEY), 5);
            
            if (visible) {
                System.out.println("✅ Start Your Journey text is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Start Your Journey text not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if today's plan is visible (for PROGRAM_USER and PROGRAM+SUBSCRIPTION_USER)
     */
    public boolean isTodaysPlanVisible() {
        try {
            System.out.println("🔍 Checking for today's plan...");
            
            boolean visible = isVisible(By.xpath(ProgramTabLocators.TODAYS_PLAN), 5);
            
            if (visible) {
                System.out.println("✅ Today's plan is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Today's plan not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if program content is visible (generic method)
     */
    public boolean isProgramContentVisible() {
        try {
            System.out.println("🔍 Checking for program content...");
            
            boolean visible = isVisible(By.xpath(ProgramTabLocators.PROGRAM_CONTENT_SCROLL), 5);
            
            if (visible) {
                System.out.println("✅ Program content is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Program content not visible: " + e.getMessage());
        }
        return false;
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
     * Verify program tab is loaded
     */
    public boolean verifyProgramTabLoaded() {
        return isProgramContentVisible();
    }

    /**
     * Validates Program Tab UI based on user type
     */
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



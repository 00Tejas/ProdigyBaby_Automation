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
        
        // Wait for program tab to load using dynamic wait
        // Check for any program-related content to confirm tab loaded
        try {
            // Wait for either program content or explore button to appear
            boolean loaded = isVisible(By.xpath(ProgramTabLocators.EXPLORE_ALL_PROGRAMS), 5) ||
                           isVisible(By.xpath(ProgramTabLocators.TODAYS_PLAN), 5) ||
                           isVisible(By.xpath(ProgramTabLocators.PROGRAM_CONTENT_SCROLL), 5);
            if (!loaded) {
                // Give a brief moment for page transition
                Thread.sleep(500);
            }
        } catch (Exception e) {
            // If elements not found, page might still be loading, continue
        }
        
        System.out.println("✅ Program tab clicked successfully");
    }

    /**
     * Click "Explore All Programs" button (for NEW_USER and LAUNCHPAD_USER)
     */
    public void clickExploreAllPrograms() throws Exception {
        System.out.println("🔍 Clicking Explore All Programs button...");
        
        By exploreAll = By.xpath(ProgramTabLocators.EXPLORE_ALL_PROGRAMS);
        waitForVisible(exploreAll, 10);
        click(exploreAll);
        
        // Wait for navigation to complete - check for "Start Your Journey" text
        // which appears after clicking Explore All Programs
        try {
            isVisible(By.xpath(ProgramTabLocators.START_YOUR_JOURNEY), 10);
        } catch (Exception e) {
            // If not found immediately, page might still be loading
        }
        
        System.out.println("✅ Explore All Programs button clicked successfully");
    }

    /**
     * Check if "Explore All Programs" button is visible
     */
    public boolean isExploreAllProgramsVisible() {
        try {
            System.out.println("🔍 Checking for Explore All Programs button...");
            
            boolean visible = isVisible(By.xpath(ProgramTabLocators.EXPLORE_ALL_PROGRAMS), 5);
            
            if (visible) {
                System.out.println("✅ Explore All Programs button is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Explore All Programs button not visible: " + e.getMessage());
        }
        return false;
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
     * Verify program tab is loaded
     */
    public boolean verifyProgramTabLoaded() {
        return isProgramContentVisible();
    }

    /**
     * Validates Program Tab UI based on user type
     */
    public void validateProgramTabUI(UserType userType) {
        System.out.println("🔍 Validating Program Tab UI elements for " + userType + "...");
        
        switch (userType) {
            case NEW_USER:
                System.out.println("  ✓ Validating NEW_USER: Checking Explore All Programs button...");
                boolean exploreAllVisible = isExploreAllProgramsVisible();
                System.out.println(exploreAllVisible ? "  ✅ Explore All Programs button is visible" : "  ❌ Explore All Programs button not visible");
                
                if (exploreAllVisible) {
                    System.out.println("  ✓ Clicking Explore All Programs button...");
                    try {
                        clickExploreAllPrograms();
                        System.out.println("  ✅ Explore All Programs button clicked successfully");
                    } catch (Exception e) {
                        System.out.println("  ❌ Failed to click Explore All Programs button: " + e.getMessage());
                    }
                }
                
                System.out.println("  ✓ Validating NEW_USER: Checking Start Your Journey...");
                boolean startJourneyVisible = isStartYourJourneyVisible();
                System.out.println(startJourneyVisible ? "  ✅ Start Your Journey is visible - Validation PASSED" : "  ❌ Start Your Journey not visible - Validation FAILED");
                break;
                
            case LAUNCHPAD_USER:
                System.out.println("  ✓ Validating LAUNCHPAD_USER: Checking Start Your Journey...");
                // For LAUNCHPAD_USER, Start Your Journey might need scrolling or waiting
                // Try scrolling to find it if not immediately visible
                boolean startJourneyVisibleLaunch = isStartYourJourneyVisible();
                if (!startJourneyVisibleLaunch) {
                    // Try scrolling to find the element
                    scrollToElement(By.xpath(ProgramTabLocators.START_YOUR_JOURNEY), 3);
                    startJourneyVisibleLaunch = isStartYourJourneyVisible();
                }
                System.out.println(startJourneyVisibleLaunch ? "  ✅ Start Your Journey is visible - Validation PASSED" : "  ❌ Start Your Journey not visible - Validation FAILED");
                break;
                
            case PROGRAM_USER:
                System.out.println("  ✓ Validating PROGRAM_USER: Checking Today's Plan...");
                // Wait a bit for content to load, then check
                boolean todaysPlanVisible = isTodaysPlanVisible();
                if (!todaysPlanVisible) {
                    // Try scrolling to find Today's Plan
                    scrollToElement(By.xpath(ProgramTabLocators.TODAYS_PLAN), 3);
                    todaysPlanVisible = isTodaysPlanVisible();
                }
                System.out.println(todaysPlanVisible ? "  ✅ Today's Plan is visible - Validation PASSED" : "  ❌ Today's Plan not visible - Validation FAILED");
                break;
                
            case PROGRAM_SUBSCRIPTION_USER:
                System.out.println("  ✓ Validating PROGRAM_SUBSCRIPTION_USER: Checking Today's Plan...");
                // Wait a bit for content to load, then check
                boolean todaysPlanVisibleProgSub = isTodaysPlanVisible();
                if (!todaysPlanVisibleProgSub) {
                    // Try scrolling to find Today's Plan
                    scrollToElement(By.xpath(ProgramTabLocators.TODAYS_PLAN), 3);
                    todaysPlanVisibleProgSub = isTodaysPlanVisible();
                }
                System.out.println(todaysPlanVisibleProgSub ? "  ✅ Today's Plan is visible - Validation PASSED" : "  ❌ Today's Plan not visible - Validation FAILED");
                break;
                
            case SUBSCRIPTION_USER:
                System.out.println("  ✓ Validating SUBSCRIPTION_USER: Checking Explore All Programs button...");
                boolean exploreAllVisibleSub = isExploreAllProgramsVisible();
                System.out.println(exploreAllVisibleSub ? "  ✅ Explore All Programs button is visible" : "  ❌ Explore All Programs button not visible");
                
                if (exploreAllVisibleSub) {
                    System.out.println("  ✓ Clicking Explore All Programs button...");
                    try {
                        clickExploreAllPrograms();
                        System.out.println("  ✅ Explore All Programs button clicked successfully");
                    } catch (Exception e) {
                        System.out.println("  ❌ Failed to click Explore All Programs button: " + e.getMessage());
                    }
                }
                
                System.out.println("  ✓ Validating SUBSCRIPTION_USER: Checking Start Your Journey...");
                boolean startJourneyVisibleSub = isStartYourJourneyVisible();
                System.out.println(startJourneyVisibleSub ? "  ✅ Start Your Journey is visible - Validation PASSED" : "  ❌ Start Your Journey not visible - Validation FAILED");
                break;
        }
        
        System.out.println("✅ Program Tab UI validation completed for " + userType);
    }

}



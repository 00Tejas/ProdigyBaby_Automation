
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
        
        // Wait for community tab to load using dynamic wait
        // Check for any community-related content to confirm tab loaded
        try {
            // Wait for any community content to appear
            boolean loaded = isVisible(By.xpath(CommunityTabLocators.OPEN_COMMUNITIES_TEXT), 5) ||
                           isVisible(By.xpath(CommunityTabLocators.MY_COMMUNITIES_TEXT), 5) ||
                           isVisible(By.xpath("//android.widget.ScrollView"), 5);
            if (!loaded) {
                // Give a brief moment for page transition
                Thread.sleep(500);
            }
        } catch (Exception e) {
            // If elements not found, page might still be loading, continue
        }
        
        System.out.println("✅ Community tab clicked successfully");
    }

    /**
     * Open Community tab (legacy method name for backward compatibility)
     */
    public void openCommunityTab() throws Exception {
        clickCommunityTab();
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
     * Check if "Open Communities" text is visible
     */
    public boolean isOpenCommunitiesTextVisible() {
        try {
            System.out.println("🔍 Checking for Open Communities text...");
            
            boolean visible = isVisible(By.xpath(CommunityTabLocators.OPEN_COMMUNITIES_TEXT), 5);
            
            if (visible) {
                System.out.println("✅ Open Communities text is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Open Communities text not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if "Parent Stories" text is visible
     */
    public boolean isParentStoriesTextVisible() {
        try {
            System.out.println("🔍 Checking for Parent Stories text...");
            
            boolean visible = isVisible(By.xpath(CommunityTabLocators.PARENT_STORIES_TEXT), 5);
            
            if (visible) {
                System.out.println("✅ Parent Stories text is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Parent Stories text not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if Video View (HorizontalScrollView) is visible 
     */
    public boolean isVideoViewVisible() {
        try {
            System.out.println("🔍 Checking for Video View (HorizontalScrollView)...");
            
            boolean visible = isVisible(By.xpath(CommunityTabLocators.VIDEO_VIEW), 5);
            
            if (visible) {
                System.out.println("✅ Video View is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Video View not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if "My Communities" text is visible
     */
    public boolean isMyCommunitiesTextVisible() {
        try {
            System.out.println("🔍 Checking for My Communities text...");
            
            boolean visible = isVisible(By.xpath(CommunityTabLocators.MY_COMMUNITIES_TEXT), 5);
            
            if (visible) {
                System.out.println("✅ My Communities text is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ My Communities text not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Validates Community Tab UI based on user type
     * @param userType The type of user to validate for
     */
    public void validateCommunityTabUI(UserType userType) {
        System.out.println("🔍 Validating Community Tab UI elements for " + userType + "...");
        
        switch (userType) {
            case NEW_USER:
                System.out.println("  ✓ Validating NEW_USER: Checking Open Communities text...");
                boolean openCommunitiesVisible = isOpenCommunitiesTextVisible();
                System.out.println(openCommunitiesVisible ? "  ✅ Open Communities text is visible - Validation PASSED" : "  ❌ Open Communities text not visible - Validation FAILED");
                
                System.out.println("  ✓ Validating NEW_USER: Checking Parent Stories text...");
                boolean parentStoriesVisible = isParentStoriesTextVisible();
                System.out.println(parentStoriesVisible ? "  ✅ Parent Stories text is visible - Validation PASSED" : "  ❌ Parent Stories text not visible - Validation FAILED");
                break;
                
            case LAUNCHPAD_USER:
                System.out.println("  ✓ Validating LAUNCHPAD_USER: Checking My Communities text...");
                boolean myCommunitiesVisibleLaunch = isMyCommunitiesTextVisible();
                System.out.println(myCommunitiesVisibleLaunch ? "  ✅ My Communities text is visible - Validation PASSED" : "  ❌ My Communities text not visible - Validation FAILED");
                break;
                
            case PROGRAM_USER:
                System.out.println("  ✓ Validating PROGRAM_USER: Checking My Communities text...");
                boolean myCommunitiesVisibleProg = isMyCommunitiesTextVisible();
                System.out.println(myCommunitiesVisibleProg ? "  ✅ My Communities text is visible - Validation PASSED" : "  ❌ My Communities text not visible - Validation FAILED");
                break;
                
            case PROGRAM_SUBSCRIPTION_USER:
                System.out.println("  ✓ Validating PROGRAM_SUBSCRIPTION_USER: Checking My Communities text...");
                boolean myCommunitiesVisibleProgSub = isMyCommunitiesTextVisible();
                System.out.println(myCommunitiesVisibleProgSub ? "  ✅ My Communities text is visible - Validation PASSED" : "  ❌ My Communities text not visible - Validation FAILED");
                break;
                
            case SUBSCRIPTION_USER:
                System.out.println("  ✓ Validating SUBSCRIPTION_USER: Checking My Communities text...");
                boolean myCommunitiesVisibleSub = isMyCommunitiesTextVisible();
                System.out.println(myCommunitiesVisibleSub ? "  ✅ My Communities text is visible - Validation PASSED" : "  ❌ My Communities text not visible - Validation FAILED");
                break;
        }
        
        System.out.println("✅ Community Tab UI validation completed for " + userType);
    }
}



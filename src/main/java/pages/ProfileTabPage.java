package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.UserType;
import locators.ProfileTabLocators;

public class ProfileTabPage extends BasePage {
    public ProfileTabPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Click on Profile tab
     */
    public void clickProfileTab() throws Exception {
        System.out.println("👤 Clicking Profile tab...");
        
        By profileTab = By.xpath(ProfileTabLocators.PROFILE_TAB_ICON);
        waitForVisible(profileTab, 10);
        click(profileTab);
        
        // Wait for profile tab to load using dynamic wait
        try {
            boolean loaded = isVisible(By.xpath(ProfileTabLocators.PROFILE_PAGE_VIEW), 5) ||
                           isVisible(By.xpath("//android.widget.ScrollView"), 5);
            if (!loaded) {
                Thread.sleep(500);
            }
        } catch (Exception e) {
            // If elements not found, page might still be loading, continue
        }
        
        System.out.println("✅ Profile tab clicked successfully");
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
     * Check if Profile Page View is visible
     */
    public boolean isProfilePageViewVisible() {
        try {
            System.out.println("🔍 Checking for Profile Page View...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.PROFILE_PAGE_VIEW), 5);
            
            if (visible) {
                System.out.println("✅ Profile Page View is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Profile Page View not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if Star Tracker is visible
     */
    public boolean isStarTrackerVisible() {
        try {
            System.out.println("🔍 Checking for Star Tracker...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.STAR_TRACKER), 5);
            
            if (visible) {
                System.out.println("✅ Star Tracker is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Star Tracker not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if Prodigy Trails is visible
     */
    public boolean isProdigyTrailsVisible() {
        try {
            System.out.println("🔍 Checking for Prodigy Trails...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.PRODIGY_TRAILS), 5);
            
            if (visible) {
                System.out.println("✅ Prodigy Trails is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Prodigy Trails not visible: " + e.getMessage());
        }
        return false;
    } 

    /**
     * Check if My Programs is visible
     */
    public boolean isMyProgramsVisible() {
        try {
            System.out.println("🔍 Checking for My Programs...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.MY_PROGRAMS), 5);
            
            if (visible) {
                System.out.println("✅ My Programs is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ My Programs not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if Saved Resources is visible
     */
    public boolean isSavedResourcesVisible() {
        try {
            System.out.println("🔍 Checking for Saved Resources...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.SAVED_RESOURCES), 5);
            
            if (visible) {
                System.out.println("✅ Saved Resources is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Saved Resources not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if My Referrals is visible
     */
    public boolean isMyReferralsVisible() {
        try {
            System.out.println("🔍 Checking for My Referrals...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.MY_REFERRALS), 5);
            
            if (visible) {
                System.out.println("✅ My Referrals is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ My Referrals not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if Progress Snapshots is visible
     */
    public boolean isProgressSnapshotsVisible() {
        try {
            System.out.println("🔍 Checking for Progress Snapshots...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.PROGRESS_SNAPSHOTS), 5);
            
            if (visible) {
                System.out.println("✅ Progress Snapshots is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Progress Snapshots not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Check if Program Parent Profile Card is visible
     */
    public boolean isProgramParentProfileCardVisible() {
        try {
            System.out.println("🔍 Checking for Program Parent Profile Card...");
            
            boolean visible = isVisible(By.xpath(ProfileTabLocators.PROGRAM_PARENT_PROFILE_CARD), 5);
            
            if (visible) {
                System.out.println("✅ Program Parent Profile Card is visible");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Program Parent Profile Card not visible: " + e.getMessage());
        }
        return false;
    }

    /**
     * Validates Profile Tab UI based on user type
     * @param userType The type of user to validate for
     */
    public void validateProfileTabUI(UserType userType) {
        System.out.println("🔍 Validating Profile Tab UI elements for " + userType + "...");
        
        switch (userType) {
            case NEW_USER:
                System.out.println("  ✓ Validating NEW_USER: Checking Profile Page View...");
                boolean profilePageViewVisible = isProfilePageViewVisible();
                System.out.println(profilePageViewVisible ? "  ✅ Profile Page View is visible - Validation PASSED" : "  ❌ Profile Page View not visible - Validation FAILED");
                break;
                
            case LAUNCHPAD_USER:
                System.out.println("  ✓ Validating LAUNCHPAD_USER: Checking Profile Page View...");
                boolean profilePageViewVisibleLaunch = isProfilePageViewVisible();
                System.out.println(profilePageViewVisibleLaunch ? "  ✅ Profile Page View is visible - Validation PASSED" : "  ❌ Profile Page View not visible - Validation FAILED");
                break;
                
            case PROGRAM_USER:
                System.out.println("  ✓ Validating PROGRAM_USER: Checking Profile Page View...");
                boolean profilePageViewVisibleProg = isProfilePageViewVisible();
                System.out.println(profilePageViewVisibleProg ? "  ✅ Profile Page View is visible - Validation PASSED" : "  ❌ Profile Page View not visible - Validation FAILED");
                
                System.out.println("  ✓ Validating PROGRAM_USER: Checking Star Tracker...");
                boolean starTrackerVisibleProg = isStarTrackerVisible();
                System.out.println(starTrackerVisibleProg ? "  ✅ Star Tracker is visible - Validation PASSED" : "  ❌ Star Tracker not visible - Validation FAILED");
                
                System.out.println("  ✓ Validating PROGRAM_USER: Checking Prodigy Trails...");
                boolean prodigyTrailsVisibleProg = isProdigyTrailsVisible();
                System.out.println(prodigyTrailsVisibleProg ? "  ✅ Prodigy Trails is visible - Validation PASSED" : "  ❌ Prodigy Trails not visible - Validation FAILED");
                
                System.out.println("  ✓ Validating PROGRAM_USER: Checking Saved Resources...");
                boolean savedResourcesVisibleProg = isSavedResourcesVisible();
                System.out.println(savedResourcesVisibleProg ? "  ✅ Saved Resources is visible - Validation PASSED" : "  ❌ Saved Resources not visible - Validation FAILED");
                
                System.out.println("  ✓ Validating PROGRAM_USER: Checking Program Parent Profile Card...");
                boolean programParentProfileCardVisible = isProgramParentProfileCardVisible();
                System.out.println(programParentProfileCardVisible ? "  ✅ Program Parent Profile Card is visible - Validation PASSED" : "  ❌ Program Parent Profile Card not visible - Validation FAILED");
                
                System.out.println("  ✓ Validating PROGRAM_USER: Checking My Programs...");
                boolean myProgramsVisibleProg = isMyProgramsVisible();
                System.out.println(myProgramsVisibleProg ? "  ✅ My Programs is visible - Validation PASSED" : "  ❌ My Programs not visible - Validation FAILED");
                break;
                
            case PROGRAM_SUBSCRIPTION_USER:
                System.out.println("  ✓ Validating PROGRAM_SUBSCRIPTION_USER: Checking Profile loaded...");
                boolean profileLoadedProgSub = verifyProfileLoaded();
                System.out.println(profileLoadedProgSub ? "  ✅ Profile Tab loaded - Validation PASSED" : "  ❌ Profile Tab not loaded - Validation FAILED");
                break;
                
            case SUBSCRIPTION_USER:
                System.out.println("  ✓ Validating SUBSCRIPTION_USER: Checking Profile loaded...");
                boolean profileLoadedSub = verifyProfileLoaded();
                System.out.println(profileLoadedSub ? "  ✅ Profile Tab loaded - Validation PASSED" : "  ❌ Profile Tab not loaded - Validation FAILED");
                break;
                
            default:
                System.out.println("  ✓ Validating (default): Checking Profile loaded...");
                boolean profileLoadedDefault = verifyProfileLoaded();
                System.out.println(profileLoadedDefault ? "  ✅ Profile Tab loaded - Validation PASSED" : "  ❌ Profile Tab not loaded - Validation FAILED");
        }
        
        System.out.println("✅ Profile Tab UI validation completed for " + userType);
    }
}



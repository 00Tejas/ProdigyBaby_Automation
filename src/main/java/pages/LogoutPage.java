package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import locators.LogoutPageLocators;

public class LogoutPage extends BasePage {
    
    public LogoutPage(AppiumDriver driver) {
        super(driver);
    }
    
    public void clickProfileTab() throws Exception {
        System.out.println("👤 Clicking on Profile tab...");
        
        By profileTab = By.xpath(LogoutPageLocators.PROFILE_TAB);
        waitForVisible(profileTab, 5);
        click(profileTab);
        
        System.out.println("✅ Profile tab clicked successfully");
        Thread.sleep(2000); // Wait for profile page to load
    }
    
    public boolean isLogoutButtonVisible() {
        try {
            System.out.println("🔍 Checking for Logout button...");
            
            By logoutButton = By.xpath(LogoutPageLocators.LOGOUT_BUTTON);
            
            // Use BasePage scrollToElement utility
            boolean found = scrollToElement(logoutButton, 3);
            
            if (found) {
                System.out.println("✅ Logout button found");
                return true;
            } else {
                System.out.println("❌ Logout button element not found after 3 scroll attempts.");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error checking logout button visibility: " + e.getMessage());
            return false;
        }
    }
    
    public void clickLogoutButton() throws Exception {
        System.out.println("🚪 Clicking Logout button...");
        
        By logoutButton = By.xpath(LogoutPageLocators.LOGOUT_BUTTON);
        waitForVisible(logoutButton, 5);
        click(logoutButton);
        
        System.out.println("✅ Logout button clicked successfully");
        Thread.sleep(2000); // Wait for logout popup to appear
    }
    
    public void confirmLogout() throws Exception {
        System.out.println("✅ Confirming logout...");
        
        By confirmLogout = By.xpath(LogoutPageLocators.CONFIRM_LOGOUT_BUTTON);
        waitForVisible(confirmLogout, 10); // Increased timeout
        click(confirmLogout);
        
        System.out.println("✅ Logout confirmed successfully");
        Thread.sleep(3000); // Wait for logout to complete
    }
    
    public boolean isLogoutStateCardVisible() {
        try {
            System.out.println("🔍 Checking for logout state card...");
            
            By logoutCard = By.xpath(LogoutPageLocators.LOGOUT_STATE_CARD);
            boolean visible = isVisible(logoutCard, 5);
            
            if (visible) {
                System.out.println("✅ Logout state card is visible - logout successful");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Logout state card not visible: " + e.getMessage());
        }
        return false;
    }
    
    public void clickLogoutStateCard() throws Exception {
        System.out.println("🔘 Clicking on logout state card...");
        
        By logoutCard = By.xpath(LogoutPageLocators.LOGOUT_STATE_CARD);
        waitForVisible(logoutCard, 5);
        click(logoutCard);
        
        System.out.println("✅ Logout state card clicked successfully");
        Thread.sleep(2000); // Wait for bottom sheet to appear
    }
    
    public void clickLoginOption() throws Exception {
        System.out.println("🔘 Clicking on Login option in bottom sheet...");
        
        By loginOption = By.xpath(LogoutPageLocators.LOGIN_OPTION);
        waitForVisible(loginOption, 5);
        click(loginOption);
        
        System.out.println("✅ Login option clicked successfully");
        Thread.sleep(2000); // Wait for email page to load
    }
    
    public boolean isOnboardingScreenVisible() {
        try {
            System.out.println("🔍 Checking if we're back to onboarding screen...");
            
            By onboardingElement = By.xpath(LogoutPageLocators.ONBOARDING_SCREEN);
            boolean visible = isVisible(onboardingElement, 5);
            
            if (visible) {
                System.out.println("✅ Successfully returned to onboarding screen");
                return true;
            }
        } catch (Exception e) {
            System.out.println("❌ Not on onboarding screen: " + e.getMessage());
        }
        return false;
    }
}


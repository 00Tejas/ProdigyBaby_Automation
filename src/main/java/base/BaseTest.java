package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import pages.HomeTabPage;
import pages.LogoutPage;
import pages.LoginPage;

public class BaseTest {
    protected AppiumDriver driver;

    // BrowserStack App IDs
    private static final String STAGING_APP_ID = "bs://733f52c656352c3763b7ac49554c3a03e4fbf937";
    private static final String PRODUCTION_APP_ID = "bs://1b4e42b45d1c6f7a3a69bf5f4672520e53759f52";
    
    /**
     * Check if running on BrowserStack
     * @return true if runMode is "browserstack"
     */
    public static boolean isBrowserStack() {
        return utils.RunMode.isBrowserStack();
    }
    
    /**
     * Check if running on local emulator
     * @return true if runMode is "local"
     */
    public static boolean isLocal() {
        return utils.RunMode.isLocal();
    }

    // Configuration flag for app environment
    private static final String appEnvironment = System.getProperty("env", "staging");

    /**
     * Get BrowserStack App ID based on environment
     * @return BrowserStack App ID (bs:// format)
     */
    public static String getBrowserStackAppId() {
        if (appEnvironment.equalsIgnoreCase("prod")) {
            return PRODUCTION_APP_ID;
        } else {
            return STAGING_APP_ID;
        }
    }

    /**
     * Get current app environment
     * @return "staging" or "prod"
     */
    public static String getAppEnvironment() {
        return appEnvironment;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws Exception {
        System.out.println("🧩 Starting test: " + method.getName());
        AppiumDriver current = DriverFactory.getDriver();
        if (current == null) {
            current = DriverFactory.createDriver();
        }
        this.driver = current;
        if (!DriverFactory.isAppActive(driver)) {
            System.out.println("⚠️ App was not active, restarting...");
            DriverFactory.restartApp(driver);
        }
    }

    /**
     * Marks BrowserStack session status (passed/failed)
     * @param status "passed" or "failed"
     * @param reason Reason for the status
     */
    public void markTestStatus(String status, String reason) {
        if (driver != null) {
            try {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                String script = "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" 
                    + status + "\", \"reason\": \"" + reason + "\"}}";
                jse.executeScript(script);
                System.out.println("📊 BrowserStack status marked as: " + status + " - " + reason);
            } catch (Exception e) {
                System.out.println("⚠️ Failed to mark BrowserStack status: " + e.getMessage());
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Mark BrowserStack session status based on test result
        if (result.getStatus() == ITestResult.SUCCESS) {
            markTestStatus("passed", "Test passed successfully");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String failureReason = "Test failed";
            if (result.getThrowable() != null) {
                failureReason = "Test failed: " + result.getThrowable().getMessage();
                System.out.println("❌ Test failed: " + result.getThrowable());
            }
            markTestStatus("failed", failureReason);
        } else {
            markTestStatus("failed", "Test skipped or unknown status");
        }

        // Quit driver after marking status
        if (driver != null) {
            try {
                driver.quit();
                // Clear shared driver reference in DriverFactory
                DriverFactory.setDriver(null);
            } catch (Exception e) {
                System.out.println("⚠️ Error quitting driver: " + e.getMessage());
            }
        }
    }

    public void setupTest() throws Exception {
        AppiumDriver current = DriverFactory.getDriver();
        if (current == null) {
            current = DriverFactory.createDriver();
        }
        this.driver = current;
    }

    public void setupTestWithReset() throws Exception {
        resetAppData();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        setupTest();
    }

    public void cleanupTest() {
        DriverFactory.closeDriver();
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void resetAppData() {
        try {
            ProcessBuilder stopPb = new ProcessBuilder("adb", "shell", "am", "force-stop", "com.raising.prodigy");
            Process stopProcess = stopPb.start();
            stopProcess.waitFor();
            Thread.sleep(3000);

            ProcessBuilder clearPb = new ProcessBuilder("adb", "shell", "pm", "clear", "com.raising.prodigy");
            Process clearProcess = clearPb.start();
            clearProcess.waitFor();
            Thread.sleep(4000);

            ProcessBuilder resetPb = new ProcessBuilder("adb", "shell", "pm", "reset-permissions", "com.raising.prodigy");
            Process resetProcess = resetPb.start();
            resetProcess.waitFor();
            Thread.sleep(3000);

            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    public void waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
        }
    }

    public void waitForElementToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
        }
    }

    public void checkAppStability() {
        try {
            driver.getCurrentUrl();
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//*"));
            } catch (Exception e2) {
            }
        }
    }

    public void loginAs(String email, String password) throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        // Perform complete login flow including all initial screens
        loginPage.performLogin(email, password);
    }

    /**
     * Helper method to get UserType from email address
     * Used for validation methods that require UserType
     */
    protected utils.UserType getUserTypeFromEmail(String email) {
        if (email.contains("proramsub")) {
            return utils.UserType.PROGRAM_SUBSCRIPTION_USER;
        } else if (email.contains("newuser")) {
            return utils.UserType.NEW_USER;
        } else if (email.contains("program")) {
            return utils.UserType.PROGRAM_USER;
        } else if (email.contains("subscription")) {
            return utils.UserType.SUBSCRIPTION_USER;
        } else if (email.contains("launchpad")) {
            return utils.UserType.LAUNCHPAD_USER;
        }
        return utils.UserType.NEW_USER; // default fallback
    }

    /**
     * Performs complete logout flow
     * Handles popups, navigates to profile, clicks logout button, and confirms logout
     */
    public void performLogout() throws Exception {
        System.out.println("🚪 Starting logout flow...");
        
        // Step 1: Handle any popups that might be blocking the profile tab
        System.out.println("🔍 Checking for popups before accessing profile tab...");
        HomeTabPage homeTabPage = new HomeTabPage(getDriver());
        homeTabPage.handleAllPopups();
        
        // Step 2: Click on Profile tab
        LogoutPage logoutPage = new LogoutPage(getDriver());
        logoutPage.clickProfileTab();
        
        // Step 3: Check if logout button is visible (with scrolling)
        boolean logoutButtonVisible = logoutPage.isLogoutButtonVisible();
        if (!logoutButtonVisible) {
            throw new Exception("Logout button not found after scrolling");
        }
        
        // Step 4: Click logout button
        logoutPage.clickLogoutButton();
        
        // Step 5: Confirm logout
        logoutPage.confirmLogout();
        
        System.out.println("✅ Logout flow completed successfully");
    }

    /**
     * Performs logout and then logs in with a different account
     * @param email Email address for the new account
     * @param password Password for the new account
     */
    public void performLogoutAndLoginWithDifferentAccount(String email, String password) throws Exception {
        System.out.println("🔄 Starting logout and login with different account flow...");
        
        // Step 1: Perform logout
        performLogout();
        
        // Step 2: Validate logout state card is visible
        LogoutPage logoutPage = new LogoutPage(getDriver());
        boolean logoutCardVisible = logoutPage.isLogoutStateCardVisible();
        if (!logoutCardVisible) {
            throw new Exception("Logout state card not visible after logout");
        }
        
        // Step 3: Click on logout state card
        logoutPage.clickLogoutStateCard();
        
        // Step 4: Click Login option in bottom sheet
        logoutPage.clickLoginOption();
        
        // Step 5: Enter email and proceed with login
        System.out.println("📧 Entering email for new account login...");
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterEmail(email);
        loginPage.clickSignIn();
        
        // Step 6: Continue with login flow - click "Login with Password" button
        System.out.println("🔐 Continuing with login flow - clicking Login with Password...");
        loginPage.clickLoginWithPassword();
        
        // Step 7: Enter password
        System.out.println("🔑 Entering password...");
        loginPage.enterPassword(password);
        loginPage.clickFinalSignIn();
        
        System.out.println("✅ Logout and login with different account completed successfully");
    }
}
  
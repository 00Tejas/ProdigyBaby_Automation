package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
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

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("❌ Test failed: " + result.getThrowable());
        }
        DriverFactory.closeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void resetAppAfterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("🔁 Resetting app after failure...");
            DriverFactory.restartApp(driver);
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

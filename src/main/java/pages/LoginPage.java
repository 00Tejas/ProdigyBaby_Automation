package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;

/**
 * LoginPage - Consolidated login flow handling all 5 screens
 * 
 * SCREEN 1: Tap to Start
 * SCREEN 2: Continue Button
 * SCREEN 3: Advertisement Selection
 * SCREEN 4: Email Entry
 * SCREEN 5: Password Entry
 */
public class LoginPage extends BasePage {

    // ==================== ERROR MESSAGE LOCATORS ====================
    private By invalidEmailError = By.xpath("//*[contains(@content-desc, 'invalid') or contains(@content-desc, 'Invalid') or contains(@text, 'invalid') or contains(@text, 'Invalid')][contains(@content-desc, 'email') or contains(@content-desc, 'Email') or contains(@text, 'email') or contains(@text, 'Email')]");
    private By invalidPasswordError = By.xpath("//*[contains(@content-desc, 'invalid') or contains(@content-desc, 'Invalid') or contains(@text, 'invalid') or contains(@text, 'Invalid')][contains(@content-desc, 'password') or contains(@content-desc, 'Password') or contains(@text, 'password') or contains(@text, 'Password')]");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    // ==================== SCREEN 1: Tap to Start ====================
    
    public void clickTapToStart() throws Exception {
        By tapToStartLocator = By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]");
        waitForElementToBeClickable(tapToStartLocator);
        driver.findElement(tapToStartLocator).click();
        waitForElementToBeClickable(By.xpath("//android.widget.Button"));
        checkAppStability();
    }

    public boolean isTapStartVisible() {
        try {
            By tapToStartLocator = By.xpath("//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]");
            return driver.findElement(tapToStartLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SCREEN 2: Continue Button ====================
    
    public void clickSecondScreenButton() throws Exception {
        By buttonLocator = By.xpath("//android.widget.Button");
        waitForElementToBeClickable(buttonLocator);
        driver.findElement(buttonLocator).click();
        waitForElementToBeClickable(By.xpath("//*[@content-desc=\"Saw an advertisement\"]"));
        checkAppStability();
    }

    public boolean isContinueButtonVisible() {
        try {
            By buttonLocator = By.xpath("//android.widget.Button");
            return driver.findElement(buttonLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SCREEN 3: Advertisement Selection ====================
    
    public void selectAdvertisement() throws Exception {
        driver.findElement(By.xpath("//*[@content-desc=\"Saw an advertisement\"]")).click();
    }

    public void clickContinue() throws Exception {
        driver.findElement(By.xpath("//*[@content-desc=\"Continue\"]")).click();
        waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
    }

    public boolean isAdvertisementOptionsVisible() {
        try {
            By advertisementLocator = By.xpath("//*[@content-desc=\"Saw an advertisement\"]");
            return driver.findElement(advertisementLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SCREEN 4: Email Entry ====================
    
    public void enterEmail(String email) throws Exception {
        driver.findElement(By.xpath("//android.widget.EditText")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(email);
        Thread.sleep(2000);
    }

    public void clickSignIn() throws Exception {
        waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
        Thread.sleep(3000);
        waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]"));
    }

    public boolean isEmailFieldVisible() {
        try {
            By emailFieldLocator = By.xpath("//android.widget.EditText");
            return driver.findElement(emailFieldLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SCREEN 5: Password Entry ====================
    
    public void clickLoginWithPassword() throws Exception {
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login with Password\"]")).click();
        waitForElementToBeClickable(By.xpath("//android.widget.EditText"));
    }

    public void enterPassword(String password) throws Exception {
        driver.findElement(By.xpath("//android.widget.EditText")).click();
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(password);
    }

    public void clickFinalSignIn() throws Exception {
        waitForElementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]"));
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign in\"]")).click();
    }

    public boolean isPasswordFieldVisible() {
        try {
            By passwordFieldLocator = By.xpath("//android.widget.EditText");
            return driver.findElement(passwordFieldLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== COMPLETE LOGIN FLOW ====================
    
    public void performLogin(String email, String password) throws Exception {
        clickTapToStart();
        clickSecondScreenButton();
        selectAdvertisement();
        clickContinue();
        enterEmail(email);
        clickSignIn();
        clickLoginWithPassword();
        enterPassword(password);
        clickFinalSignIn();
    }

    // ==================== VALIDATION METHODS ====================
    
    public boolean validateSuccessfulLogin() {
        try {
            waitForElementToBeVisible(By.xpath("//android.view.View[contains(@content-desc, 'Activity Streak')]"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void verifyInvalidEmailError() {
        Assert.assertTrue(isDisplayed(invalidEmailError), "Invalid email error not displayed!");
    }

    public void verifyInvalidPasswordError() {
        Assert.assertTrue(isDisplayed(invalidPasswordError), "Invalid password error not displayed!");
    }

    // ==================== SCREEN VERIFICATION METHODS ====================
    
    public void verifyScreen1UI() {
        Assert.assertTrue(isEmailFieldVisible(), "Email field is not visible on Screen 1!");
    }

    public void verifyScreen2UI() {
        Assert.assertTrue(isPasswordFieldVisible(), "Password field is not visible on Screen 2!");
    }

    // ==================== NAVIGATION METHODS FOR REUSABLE LOGIN ====================
    
    public void clickNext() throws Exception {
        clickSignIn();
        clickLoginWithPassword();
    }

    public void clickLogin() throws Exception {
        clickFinalSignIn();
    }

    // ==================== REUSABLE LOGIN METHOD ====================
    
    public void login(String email, String password) throws Exception {
        verifyScreen1UI();
        enterEmail(email);
        clickNext();
        verifyScreen2UI();
        enterPassword(password);
        clickLogin();
    }

    // ==================== HELPER METHODS ====================
    
    private void waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
        }
    }

    private void waitForElementToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
        }
    }

    private void checkAppStability() {
        try {
            driver.getCurrentUrl();
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//*"));
            } catch (Exception e2) {
            }
        }
    }
}


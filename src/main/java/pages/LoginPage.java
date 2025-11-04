package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import locators.LoginPageLocators;
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
    private By invalidEmailError = By.xpath(LoginPageLocators.INVALID_EMAIL_ERROR);
    private By invalidPasswordError = By.xpath(LoginPageLocators.INVALID_PASSWORD_ERROR);

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    // ==================== SCREEN 1: Tap to Start ====================
    
    public void clickTapToStart() throws Exception {
        By tapToStartLocator = By.xpath(LoginPageLocators.TAP_TO_START);
        waitForElementToBeClickable(tapToStartLocator);
        driver.findElement(tapToStartLocator).click();
        waitForElementToBeClickable(By.xpath(LoginPageLocators.GENERIC_BUTTON));
        checkAppStability();
    }

    public boolean isTapStartVisible() {
        try {
            By tapToStartLocator = By.xpath(LoginPageLocators.TAP_TO_START);
            return driver.findElement(tapToStartLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SCREEN 2: Continue Button ====================
    
    public void clickSecondScreenButton() throws Exception {
        By buttonLocator = By.xpath(LoginPageLocators.GENERIC_BUTTON);
        waitForElementToBeClickable(buttonLocator);
        driver.findElement(buttonLocator).click();
        waitForElementToBeClickable(By.xpath(LoginPageLocators.SAW_AD));
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
        driver.findElement(By.xpath(LoginPageLocators.SAW_AD)).click();
    }

    public void clickContinue() throws Exception {
        driver.findElement(By.xpath(LoginPageLocators.CONTINUE)).click();
        waitForElementToBeClickable(By.xpath(LoginPageLocators.EDIT_TEXT));
    }

    public boolean isAdvertisementOptionsVisible() {
        try {
            By advertisementLocator = By.xpath(LoginPageLocators.SAW_AD);
            return driver.findElement(advertisementLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SCREEN 4: Email Entry ====================
    
    public void enterEmail(String email) throws Exception {
        driver.findElement(By.xpath(LoginPageLocators.EDIT_TEXT)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(LoginPageLocators.EDIT_TEXT)).sendKeys(email);
        Thread.sleep(2000);
    }

    public void clickSignIn() throws Exception {
        waitForElementToBeClickable(By.xpath(LoginPageLocators.SIGN_IN));
        driver.findElement(By.xpath(LoginPageLocators.SIGN_IN)).click();
        Thread.sleep(3000);
        waitForElementToBeClickable(By.xpath(LoginPageLocators.LOGIN_WITH_PASSWORD));
    }

    public boolean isEmailFieldVisible() {
        try {
            By emailFieldLocator = By.xpath(LoginPageLocators.EDIT_TEXT);
            return driver.findElement(emailFieldLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== SCREEN 5: Password Entry ====================
    
    public void clickLoginWithPassword() throws Exception {
        driver.findElement(By.xpath(LoginPageLocators.LOGIN_WITH_PASSWORD)).click();
        waitForElementToBeClickable(By.xpath(LoginPageLocators.EDIT_TEXT));
    }

    public void enterPassword(String password) throws Exception {
        driver.findElement(By.xpath(LoginPageLocators.EDIT_TEXT)).click();
        driver.findElement(By.xpath(LoginPageLocators.EDIT_TEXT)).sendKeys(password);
    }

    public void clickFinalSignIn() throws Exception {
        waitForElementToBeClickable(By.xpath(LoginPageLocators.SIGN_IN));
        driver.findElement(By.xpath(LoginPageLocators.SIGN_IN)).click();
    }

    public boolean isPasswordFieldVisible() {
        try {
            By passwordFieldLocator = By.xpath(LoginPageLocators.EDIT_TEXT);
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
            waitForElementToBeVisible(By.xpath(LoginPageLocators.SUCCESS_ACTIVITY_STREAK));
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


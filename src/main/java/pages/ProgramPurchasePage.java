package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import locators.ProgramPurchaseLocators;

/**
 * ProgramPurchasePage - handles program purchase/paywall steps.
 */
public class ProgramPurchasePage extends BasePage {
    public ProgramPurchasePage(AppiumDriver driver) {
        super(driver);
    }

    /** Click the Continue button on the offerings sheet. */
    public void clickOfferingsContinue() {
        click(By.xpath(ProgramPurchaseLocators.OFFERINGS_CONTINUE_BUTTON));
    }

    /** Click Purchase Now on the bottom sheet. */
    public void clickPurchaseNow() {
        click(By.xpath(ProgramPurchaseLocators.PURCHASE_NOW_BUTTON));
    }

    /** Fill address fields. */
    public void fillAddressDetails(String address, String city, String state, String zip, String country) {
        // Tap, clear, and type for each field to ensure the value sticks
        fillField(ProgramPurchaseLocators.ADDRESS_FIELD, address);
        fillField(ProgramPurchaseLocators.CITY_FIELD, city);
        fillField(ProgramPurchaseLocators.STATE_FIELD, state);
        fillField(ProgramPurchaseLocators.ZIP_FIELD, zip);
        fillField(ProgramPurchaseLocators.COUNTRY_FIELD, country);
        System.out.println("✅ Address info filled successfully");
    }

    private void fillField(String xpath, String value) {
        By locator = By.xpath(xpath);
        click(locator);
        type(locator, value);
    }

    /** Click Continue to Pay. */
    public void clickContinueToPay() {
        click(By.xpath(ProgramPurchaseLocators.CONTINUE_TO_PAY_BUTTON));
    }

    /** Click Buy on the Play Store bottom sheet. */
    public void clickBuyButton() {
        click(By.xpath(ProgramPurchaseLocators.BUY_BUTTON));
    }
}

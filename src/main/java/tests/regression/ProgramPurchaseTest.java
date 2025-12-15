package tests.regression;

import base.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.OnboardingPage;
import pages.ProgramPurchasePage;

/**
 * Validates program purchase flow after onboarding, interacting with offerings popup.
 */
public class ProgramPurchaseTest extends BaseTest {

    @Test(description = "Onboard and complete program purchase via offerings flow")
    public void purchaseProgramAfterOnboarding() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");

        Reporter.log("Running program purchase after onboarding", true);

        // Test data
        String babyName = "TestBaby";
        int month = 6;
        int day = 15;
        int year = 2024;
        String phoneNumber = "1234567890";
        String email = utils.EmailGenerator.getUniqueEmail();

        String address = "mumbai";
        String city = "juhu";
        String state = "Maharashtra";
        String zip = "0000";
        String country = "India";

        OnboardingPage onboardingPage = new OnboardingPage(getDriver());

        // Complete onboarding but keep offerings popup visible
        onboardingPage.performCompleteOnboardingPreserveOfferings(babyName, month, day, year, phoneNumber, email);

        ProgramPurchasePage purchasePage = new ProgramPurchasePage(getDriver());

        // Offerings popup -> Continue
        purchasePage.clickOfferingsContinue();

        // Bottom sheet -> Purchase Now
        purchasePage.clickPurchaseNow();

        // Fill address details
        purchasePage.fillAddressDetails(address, city, state, zip, country);

        // Continue to pay
        purchasePage.clickContinueToPay();

        // Buy on Play Store sheet
        purchasePage.clickBuyButton();
    }
}



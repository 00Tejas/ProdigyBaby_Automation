package tests.regression;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.HomeTabPage;
import pages.OnboardingPage;
import pages.SubscriptionPage;
import utils.EmailGenerator;

/**
 * Validates purchase entry immediately after completing onboarding.
 */
public class PurchaseAfterOnboardingTest extends BaseTest {

    @Test(description = "Complete onboarding and open purchase/paywall flow")
    public void completeOnboardingThenOpenPurchase() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");

        Reporter.log("Running onboarding + purchase test", true);

        // Always start clean
        resetAppData();

        // Test data
        String babyName = "TestBaby";
        int month = 6;
        int day = 15;
        int year = 2024;
        String phoneNumber = "1234567890";
        String email = EmailGenerator.getUniqueEmail();
        Reporter.log("Using onboarding email: " + email, true);

        OnboardingPage onboardingPage = new OnboardingPage(getDriver());

        // Complete onboarding (no child profile assertion here)
        onboardingPage.performCompleteOnboarding(babyName, month, day, year, phoneNumber, email);

        // Refresh context via Profile -> Home
        onboardingPage.refreshViaProfileToHome();

       
        HomeTabPage homeTabPage = new HomeTabPage(getDriver());


        // Open purchase flow by clicking Subscribe on Home page
        boolean planOpened = homeTabPage.tapSubscribeButtonIfPresent();

        SubscriptionPage subscriptionPage = new SubscriptionPage(getDriver());

        subscriptionPage.clickPaywallContinue();

        // Click Play Store subscribe and wait for confirmation
        subscriptionPage.clickPlayStoreSubscribe();
        boolean confirmationShown = subscriptionPage.waitForPurchaseConfirmation();
        Assert.assertTrue(confirmationShown, "Purchase confirmation not shown after subscribe");

        // Clean up for next runs
        subscriptionPage.dismissPaywallIfVisible();
        resetAppData();
    }
}



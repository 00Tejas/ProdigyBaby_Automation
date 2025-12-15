package tests.regression;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.OnboardingPage;
import utils.EmailGenerator;

/**
 * OnboardingTest - Test class for onboarding flow validation
 */
public class OnboardingTest extends BaseTest {

    @Test(description = "Complete onboarding flow test")
    public void completeOnboardingFlow() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        Reporter.log("Running test: " + new Object(){}.getClass().getEnclosingMethod().getName(), true);
        
        // Reset app data to start fresh
        resetAppData();
        
        // Test data
        String babyName = "TestBaby";
        int month = 6;  // June
        int day = 15;
        int year = 2024;  // Updated to 2024
        String phoneNumber = "1234567890";
        
        // Generate unique email for this test run
        String email = EmailGenerator.getUniqueEmail();
        System.out.println("Using onboarding email: " + email);
        
        Reporter.log("Executing onboarding flow with baby name: " + babyName, true);
        Reporter.log("Using onboarding email: " + email, true);
        
        // Create OnboardingPage instance
        OnboardingPage onboardingPage = new OnboardingPage(getDriver());
        
        // Perform complete onboarding flow
        onboardingPage.performCompleteOnboarding(babyName, month, day, year, phoneNumber, email);
        
        System.out.println("✅ Onboarding flow completed");
        
        // Validate successful onboarding - check if child profile is visible
        System.out.println("\n🔍 Validating successful onboarding...");
        Reporter.log("Validating successful onboarding by checking child profile visibility", true);
        
        boolean isOnboardingSuccessful = onboardingPage.validateSuccessfulOnboarding();
        
        // Assert that onboarding was successful
        Assert.assertTrue(isOnboardingSuccessful, 
            "Onboarding validation FAILED: Child profile is not visible after completing onboarding flow");
        
        System.out.println("✅ Onboarding validation PASSED - Child profile is visible");
        Reporter.log("Onboarding validation PASSED - Child profile is visible", true);
        
        // Wait a moment before tear down to ensure validation is complete
        Thread.sleep(2000);
        
        // Reset app data after test
        resetAppData();
    }
}


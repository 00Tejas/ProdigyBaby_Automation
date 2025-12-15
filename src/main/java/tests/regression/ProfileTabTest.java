package tests.regression;

import base.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.ProfileTabPage;
import utils.TestUsers;
import utils.User;
import utils.UserType;

/**
 * ProfileTabTest - Focused test class for Profile Tab UI validation
 * Tests Profile Tab UI elements for different user types
 */
public class ProfileTabTest extends BaseTest {

    @Test(description = "Validate Profile Tab UI for New User")
    public void validateProfileTab_NewUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.NEW_USER);
    }

    @Test(description = "Validate Profile Tab UI for Program User")
    public void validateProfileTab_ProgramUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.PROGRAM_USER);
    }

    @Test(description = "Validate Profile Tab UI for Subscription User")
    public void validateProfileTab_SubscriptionUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.SUBSCRIPTION_USER);
    }

    @Test(description = "Validate Profile Tab UI for Launchpad User")
    public void validateProfileTab_LaunchpadUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.LAUNCHPAD_USER);
    }

    @Test(description = "Validate Profile Tab UI for Program Subscription User")
    public void validateProfileTab_ProgramSubscriptionUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.PROGRAM_SUBSCRIPTION_USER);
    }

    private void runTest(UserType type) throws Exception {
        User user = TestUsers.getUser(type);
        Reporter.log("Executing test for user: " + user.getEmail(), true);
        resetAppData();
        loginAs(user.getEmail(), user.getPassword());
        
        // Handle any popups that might appear after login (like HomeTabTest does)
        pages.HomeTabPage home = new pages.HomeTabPage(driver);
        home.handleAllPopups();
        
        // Wait a moment for the app to stabilize after login
        Thread.sleep(2000);
        
        ProfileTabPage profileTab = new ProfileTabPage(driver);
        profileTab.clickProfileTab();
        profileTab.validateProfileTabUI(type);
        
        resetAppData();
    }
}


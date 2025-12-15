package tests.regression;

import base.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.CommunityTabPage;
import utils.TestUsers;
import utils.User;
import utils.UserType;

/**
 * CommunityTabTest - Focused test class for Community Tab UI validation
 * Tests Community Tab UI elements for different user types
 */
public class CommunityTabTest extends BaseTest {

    @Test(description = "Validate Community Tab UI for New User")
    public void validateCommunityTab_NewUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.NEW_USER);
    }

    @Test(description = "Validate Community Tab UI for Program User")
    public void validateCommunityTab_ProgramUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.PROGRAM_USER);
    }

    @Test(description = "Validate Community Tab UI for Subscription User")
    public void validateCommunityTab_SubscriptionUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.SUBSCRIPTION_USER);
    }

    @Test(description = "Validate Community Tab UI for Launchpad User")
    public void validateCommunityTab_LaunchpadUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.LAUNCHPAD_USER);
    }

    @Test(description = "Validate Community Tab UI for Program Subscription User")
    public void validateCommunityTab_ProgramSubscriptionUser() throws Exception {
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
        
        CommunityTabPage communityTab = new CommunityTabPage(driver);
        communityTab.openCommunityTab();
        communityTab.validateCommunityTabUI(type);
        
        resetAppData();
    }
}


package tests.regression;

import base.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.HomeTabPage;
import utils.TestUsers;
import utils.User;
import utils.UserType;

/**
 * HomeTabTest - Focused test class for Home Tab UI validation
 * Tests Home Tab UI elements for different user types
 */
public class HomeTabTest extends BaseTest {

    @Test(description = "Validate Home Tab UI for New User")
    public void validateHomeTab_NewUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.NEW_USER);
    }

    @Test(description = "Validate Home Tab UI for Program User")
    public void validateHomeTab_ProgramUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.PROGRAM_USER);
    }

    @Test(description = "Validate Home Tab UI for Subscription User")
    public void validateHomeTab_SubscriptionUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.SUBSCRIPTION_USER);
    }

    @Test(description = "Validate Home Tab UI for Launchpad User")
    public void validateHomeTab_LaunchpadUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.LAUNCHPAD_USER);
    }

    @Test(description = "Validate Home Tab UI for Program Subscription User")
    public void validateHomeTab_ProgramSubscriptionUser() throws Exception {
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
        
        HomeTabPage home = new HomeTabPage(driver);
        home.handleAllPopups();
        home.validateHomeTabUI(type);
        
        resetAppData();
    }
}


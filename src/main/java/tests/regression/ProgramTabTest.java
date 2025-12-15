package tests.regression;

import base.BaseTest;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.ProgramTabPage;
import utils.TestUsers;
import utils.User;
import utils.UserType;

/**
 * ProgramTabTest - Focused test class for Program Tab UI validation
 * Tests Program Tab UI elements for different user types
 */
public class ProgramTabTest extends BaseTest {

    @Test(description = "Validate Program Tab UI for New User")
    public void validateProgramTab_NewUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.NEW_USER);
    }

    @Test(description = "Validate Program Tab UI for Program User")
    public void validateProgramTab_ProgramUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.PROGRAM_USER);
    }

    @Test(description = "Validate Program Tab UI for Subscription User")
    public void validateProgramTab_SubscriptionUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.SUBSCRIPTION_USER);
    }

    @Test(description = "Validate Program Tab UI for Launchpad User")
    public void validateProgramTab_LaunchpadUser() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        runTest(UserType.LAUNCHPAD_USER);
    }

    @Test(description = "Validate Program Tab UI for Program Subscription User")
    public void validateProgramTab_ProgramSubscriptionUser() throws Exception {
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
        
        ProgramTabPage programTab = new ProgramTabPage(driver);
        programTab.clickProgramTab();
        programTab.validateProgramTabUI(type);
        
        resetAppData();
    }
}


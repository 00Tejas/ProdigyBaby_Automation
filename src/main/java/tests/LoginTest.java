package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.HomePage;
import pages.ProgramTabPage;
import utils.UserType;
import utils.User;
import utils.TestUsers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * LoginTest - Multi-user login flow test using DataProvider
 * 
 * Tests login flow for all user types and validates UI after login
 */
public class LoginTest extends BaseTest {

    @DataProvider(name = "userTypes")
    public Object[][] getUserTypes() {
        return new Object[][] {
            { UserType.NEW_USER },
            { UserType.PROGRAM_USER },
            { UserType.SUBSCRIPTION_USER },
            { UserType.LAUNCHPAD_USER },
            { UserType.PROGRAM_SUBSCRIPTION_USER }
        };
    }

    @Test(dataProvider = "userTypes", groups = {"smoke", "ui"})
    public void testLoginForDifferentUsers(UserType userType) throws Exception {
        System.out.println("🧪 Testing login for user type: " + userType);
        
        // Get user credentials
        User user = TestUsers.getUser(userType);
        
        // Perform complete login flow through all 5 screens
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.performLogin(user.getEmail(), user.getPassword());
        
        // Validate successful login
        boolean loginSuccess = loginPage.validateSuccessfulLogin();
        assert loginSuccess : "Login validation failed for " + userType;
        
        // Handle any popups that appear after login
        HomePage homePage = new HomePage(getDriver());
        homePage.handleAllPopups();
        
        // Validate home tab UI based on user type
        homePage.validateHomeTabUI(userType);
        
        // Validate program tab UI based on user type
        ProgramTabPage programTabPage = new ProgramTabPage(getDriver());
        programTabPage.validateProgramTabUI(userType);
        
        System.out.println("✅ Test completed successfully for " + userType);
    }
}


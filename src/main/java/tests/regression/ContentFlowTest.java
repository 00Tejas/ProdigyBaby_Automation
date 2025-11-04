package tests.regression;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HomeTabPage;
import pages.ProgramTabPage;
import pages.CommunityTabPage;
import pages.ProfileTabPage;
import utils.DataProviderUtil;
import utils.TestUsers;
import utils.User;
import utils.UserType;

public class ContentFlowTest extends BaseTest {

    @Test(dataProvider = "userTypes", dataProviderClass = DataProviderUtil.class,
          description = "Validate core content across tabs for each user type")
    public void validateTabsPerUser(UserType userType) throws Exception {
        System.out.println("🧪 Starting UI validation for user type: " + userType);
        
        try {
            // Step 1: Login as the current user type
            User user = TestUsers.getUser(userType);
            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.performLogin(user.getEmail(), user.getPassword());
            System.out.println("✅ Login successful for " + userType);

            // Step 2: Validate Home Tab UI
            System.out.println("🏠 Validating Home Tab UI for " + userType);
            HomeTabPage homeTab = new HomeTabPage(getDriver());
            homeTab.handleAllPopups();
            homeTab.validateHomeTabUI(userType);
            Assert.assertTrue(homeTab.verifyHomePageLoaded(), 
                "Home tab did not load as expected for " + userType);
            System.out.println("✅ Home Tab UI validation passed for " + userType);

            // Step 3: Validate Program Tab UI
            System.out.println("📚 Validating Program Tab UI for " + userType);
            ProgramTabPage programTab = new ProgramTabPage(getDriver());
            programTab.clickProgramTab();
            programTab.validateProgramTabUI(userType);
            Assert.assertTrue(programTab.verifyProgramTabLoaded(), 
                "Program tab did not load as expected for " + userType);
            System.out.println("✅ Program Tab UI validation passed for " + userType);

            // Step 4: Validate Community Tab UI
            System.out.println("👥 Validating Community Tab UI for " + userType);
            CommunityTabPage communityTab = new CommunityTabPage(getDriver());
            communityTab.openCommunityTab();
            communityTab.validateCommunityTabUI(userType);
            Assert.assertTrue(communityTab.verifyCommunityLoaded(), 
                "Community tab did not load as expected for " + userType);
            System.out.println("✅ Community Tab UI validation passed for " + userType);

            // Step 5: Validate Profile Tab UI
            System.out.println("👤 Validating Profile Tab UI for " + userType);
            ProfileTabPage profileTab = new ProfileTabPage(getDriver());
            profileTab.openProfileTab();
            profileTab.validateProfileTabUI(userType);
            Assert.assertTrue(profileTab.verifyProfileLoaded(), 
                "Profile tab did not load as expected for " + userType);
            System.out.println("✅ Profile Tab UI validation passed for " + userType);

            System.out.println("✅ All tab validations completed successfully for " + userType);
            
        } finally {
            // Step 6: Reset app after validating all tabs for this user
            System.out.println("🔄 Resetting app after validation for " + userType);
            resetAppData();
            System.out.println("✅ App reset completed for " + userType);
        }
    }
}



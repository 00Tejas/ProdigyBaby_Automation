package tests.smoke;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HomeTabPage;

public class SmokeTest extends BaseTest {

    @Test(description = "Basic launch and login smoke check")
    public void appLaunchAndLogin() throws Exception {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.performLogin("program1@prodigy.baby", "123456");

        HomeTabPage home = new HomeTabPage(getDriver());
        home.handleAllPopups();
        Assert.assertTrue(home.verifyHomePageLoaded(), "Home did not load after login");
    }
}



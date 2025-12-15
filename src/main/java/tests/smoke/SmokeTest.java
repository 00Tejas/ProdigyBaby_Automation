package tests.smoke;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HomeTabPage;

public class SmokeTest extends BaseTest {

    @Test(description = "Basic launch and login smoke check")
    public void appLaunchAndLogin() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        Reporter.log("Running test: " + new Object(){}.getClass().getEnclosingMethod().getName(), true);
        Reporter.log("Executing test for user: program1@prodigy.baby", true);
        
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.performLogin("program1@prodigy.baby", "123456");

        HomeTabPage home = new HomeTabPage(getDriver());
        home.handleAllPopups();
        Assert.assertTrue(home.verifyHomePageLoaded(), "Home did not load after login");
    }
}



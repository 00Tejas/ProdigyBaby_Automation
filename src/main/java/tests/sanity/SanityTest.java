package tests.sanity;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProgramTabPage;
import pages.HomeTabPage;

public class SanityTest extends BaseTest {

    @Test(description = "Quick post-deploy health check")
    public void quickHealth() throws Exception {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.performLogin("program1@prodigy.baby", "123456");

        HomeTabPage home = new HomeTabPage(getDriver());
        Assert.assertTrue(home.verifyHomePageLoaded(), "Home failed to load");

        ProgramTabPage program = new ProgramTabPage(getDriver());
        program.clickProgramTab();
        Assert.assertTrue(program.verifyProgramTabLoaded(), "Program tab failed to load");
    }
}



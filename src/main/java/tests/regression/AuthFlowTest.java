 package tests.regression;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CredentialsType;
import utils.DataProviderUtil;

public class AuthFlowTest extends BaseTest {

    @Test(dataProvider = "credentialData", dataProviderClass = DataProviderUtil.class,
          description = "Validate login with valid and invalid credentials")
    public void loginInputDomain(CredentialsType type, String email, String password) throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        Reporter.log("Executing test for user: " + email, true);
        
        LoginPage login = new LoginPage(getDriver());
        login.performLogin(email, password);

        switch (type) {
            case VALID:
                Assert.assertTrue(login.validateSuccessfulLogin(), "Expected successful login for valid credentials");
                break;
            case INVALID_EMAIL:
                login.verifyInvalidEmailError();
                break;
            case INVALID_PASSWORD:
                login.verifyInvalidPasswordError();
                break;
        }
    }
}
    


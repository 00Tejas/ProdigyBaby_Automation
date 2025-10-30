package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CredentialsType;

/**
 * 🔐 Login Input Domain Tests
 * Covers:
 *  ✅ Valid email & password
 *  🚫 Invalid email
 *  🚫 Invalid password
 */
public class LoginInputDomainTest extends BaseTest {

    @Test(dataProvider = "credentialData", description = "Validate login with valid and invalid credentials")
    public void testLoginInputDomain(CredentialsType type, String email, String password) throws Exception {
        LoginPage login = new LoginPage(driver);

        // Perform login flow
        login.login(email, password);

        // Validate based on credential type
        switch (type) {
            case VALID:
                // ✅ Validation: Successful login → validate home indicator
                Assert.assertTrue(login.validateSuccessfulLogin(), "Expected successful login for valid credentials");
                break;

            case INVALID_EMAIL:
                // 🚫 Validation: Invalid email error message should appear
                login.verifyInvalidEmailError();
                break;

            case INVALID_PASSWORD:
                // 🚫 Validation: Invalid password error message should appear
                login.verifyInvalidPasswordError();
                break;
        }
    }

    @DataProvider(name = "credentialData")
    public Object[][] credentialData() {
        return new Object[][] {
            // ✅ Valid credentials
            { CredentialsType.VALID, "program1@prodigy.baby", "123456" },

            // 🚫 Invalid email
            { CredentialsType.INVALID_EMAIL, "invalid@email.com", "123456" },

            // 🚫 Invalid password
            { CredentialsType.INVALID_PASSWORD, "programm12@prodigy.baby", "wrongpassword" }
        };
    }
}



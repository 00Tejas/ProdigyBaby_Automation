package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "userTypes")
    public static Object[][] userTypes() {
        return new Object[][]{
            { "newuser1@p.baby", "123456" },
            { "program1@prodigy.baby", "123456" },
            { "subscription1@p.baby", "123456" },
            { "launchpad1@p.baby", "123456" },
            { "proramsub1@prodigy.baby", "123456" }
        };
    }

    @DataProvider(name = "credentialData")
    public static Object[][] credentialData() {
        return new Object[][]{
            { CredentialsType.VALID, "program1@prodigy.baby", "123456" },
            { CredentialsType.INVALID_EMAIL, "invalid@email.com", "123456" },
            { CredentialsType.INVALID_PASSWORD, "programm12@prodigy.baby", "wrongpassword" }
        };
    }
}



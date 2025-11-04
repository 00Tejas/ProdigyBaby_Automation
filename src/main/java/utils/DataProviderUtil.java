package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "userTypes")
    public static Object[][] userTypes() {
        return new Object[][]{
            { UserType.NEW_USER },
            { UserType.PROGRAM_USER },
            { UserType.SUBSCRIPTION_USER },
            { UserType.LAUNCHPAD_USER },
            { UserType.PROGRAM_SUBSCRIPTION_USER }
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



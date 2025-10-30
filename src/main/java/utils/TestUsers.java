 package utils;

public class TestUsers {
    public static User getUser(UserType type) {
        switch (type) {
            case NEW_USER:
                return new User("newuser1@p.baby", "123456");
            case PROGRAM_USER:
                return new User("program1@prodigy.baby", "123456");
            case SUBSCRIPTION_USER:
                return new User("subscription1@p.baby", "123456");
            case LAUNCHPAD_USER:
                return new User("launchpad1@p.baby", "123456");
            case PROGRAM_SUBSCRIPTION_USER:
                return new User("proramsub1@prodigy.baby", "123456");
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}


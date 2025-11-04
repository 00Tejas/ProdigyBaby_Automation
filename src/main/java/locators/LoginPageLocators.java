package locators;

public class LoginPageLocators {
    public static final String TAP_TO_START = "//android.widget.ImageView[contains(@content-desc, 'Tap to Start')]";
    public static final String GENERIC_BUTTON = "//android.widget.Button";
    public static final String SAW_AD = "//*[@content-desc=\"Saw an advertisement\"]";
    public static final String CONTINUE = "//*[@content-desc=\"Continue\"]";
    public static final String EDIT_TEXT = "//android.widget.EditText";
    public static final String SIGN_IN = "//android.widget.Button[@content-desc=\"Sign in\"]";
    public static final String LOGIN_WITH_PASSWORD = "//android.widget.Button[@content-desc=\"Login with Password\"]";
    public static final String SUCCESS_ACTIVITY_STREAK = "//android.view.View[contains(@content-desc, 'Activity Streak')]";
    public static final String INVALID_EMAIL_ERROR = "//*[contains(@content-desc, 'invalid') or contains(@content-desc, 'Invalid') or contains(@text, 'invalid') or contains(@text, 'Invalid')][contains(@content-desc, 'email') or contains(@content-desc, 'Email') or contains(@text, 'email') or contains(@text, 'Email')]";
    public static final String INVALID_PASSWORD_ERROR = "//*[contains(@content-desc, 'invalid') or contains(@content-desc, 'Invalid') or contains(@text, 'invalid') or contains(@text, 'Invalid')][contains(@content-desc, 'password') or contains(@content-desc, 'Password') or contains(@text, 'password') or contains(@text, 'Password')]";
}



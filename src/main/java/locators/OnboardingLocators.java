package locators;

/**
 * OnboardingLocators - Locators for onboarding flow elements
 */
public class OnboardingLocators { 
    // Tap to Start - reuse from LoginPageLocators
    public static final String TAP_TO_START = LoginPageLocators.TAP_TO_START;
    
    // Explore App Button
    public static final String EXPLORE_APP_BUTTON = "//android.widget.Button[@content-desc=\"Explore App\"]";
    
    // Your baby's name field
    public static final String YOUR_BABYS_NAME_FIELD = "//android.view.View[@content-desc=\"Your baby's name\"]/android.widget.EditText";
    
    // Baby's age field (click to open date picker)
    public static final String BABYS_AGE_FIELD = "//android.view.View[@content-desc=\"Baby's date of birth\"]/android.widget.EditText";
    
    // Phone number field
    public static final String PHONE_NUMBER_FIELD = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]";
    
    // Email field
    public static final String EMAIL_FIELD = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]";
    
    // Date Picker Elements
    public static final String MONTH_SEEKBAR = "(//android.widget.SeekBar)[1]";
    public static final String DAY_SEEKBAR = "(//android.widget.SeekBar)[2]";
    public static final String YEAR_SEEKBAR = "(//android.widget.SeekBar)[3]";
    
    // Gender selection
    public static final String GENDER_BOY = "//android.widget.ImageView[@content-desc=\"Boy\"]";
    
    // Continue button on date picker
    public static final String CONTINUE_BUTTON_DATE_PICKER = "//android.widget.Button[@content-desc=\"Continue\"]";
    
    // What convinced you
    public static final String WHAT_CONVINCED_YOU = "//android.view.View[@content-desc=\"Discovered while searching\"]";
    
    // How aware are you
    public static final String HOW_AWARE_ARE_YOU = "//android.widget.ImageView[@content-desc=\"Intermediate\"]";
    
    // How much time
    public static final String HOW_MUCH_TIME = "//android.widget.ImageView[@content-desc=\"5-10 minutes\"]";
    
    // Dismiss testimonial
    public static final String DISMISS_TESTIMONIAL = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]";
    
    // Choose a Plan button
    public static final String CHOOSE_A_PLAN = "//android.widget.Button[@content-desc=\"Choose a Plan\"]";
    
    // Dismiss plan
    public static final String DISMISS_PLAN = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]";
    
    // Child Profile (successful onboarding validation)
    public static final String CHILD_PROFILE = "//android.view.View[contains(@content-desc, 'TestBaby')]\n"
    		+ "";
}


package locators;

/**
 * Locators for program purchase/paywall flow.
 * Fill/update these as real XPaths are confirmed.
 */
public class ProgramPurchaseLocators {
    // Continue button on offerings sheet
    public static final String OFFERINGS_CONTINUE_BUTTON = "//android.widget.Button[@content-desc=\"Continue\"]";

    // Purchase now button on bottom sheet
    public static final String PURCHASE_NOW_BUTTON = "//android.widget.Button[@content-desc=\"Purchase now\"]";

    // Continue to Pay button
    public static final String CONTINUE_TO_PAY_BUTTON = "//android.widget.Button[@content-desc=\"Continue to Pay\"]";

    // Buy button on the Play Store bottom sheet
    public static final String BUY_BUTTON = "//android.widget.Button[@resource-id=\"com.android.vending:id/0_resource_name_obfuscated\"]";

    // Address field
    public static final String ADDRESS_FIELD = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[3]";

    // City field
    public static final String CITY_FIELD = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[4]";

    // State field
    public static final String STATE_FIELD = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[19]/android.widget.EditText[1]";

    // Zip code field
    public static final String ZIP_FIELD = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[19]/android.widget.EditText[2]";

    // Country field
    public static final String COUNTRY_FIELD = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[5]";
}



package locators;

/**
 * SubscriptionLocators - Locators used for purchase/paywall validation.
 * These reuse existing onboarding paywall hooks so we can open and verify
 * the purchase sheet immediately after onboarding.
 */
public class SubscriptionLocators {
    // CTA shown on the final onboarding screen / home to trigger the paywall
    public static final String CHOOSE_A_PLAN = OnboardingLocators.CHOOSE_A_PLAN;

    // Close button on the plan/paywall bottom sheet
    public static final String PAYWALL_DISMISS = OnboardingLocators.DISMISS_PLAN;

    // Generic CTA that appears on the subscription sheet (if present)
    public static final String JOIN_NOW = HomeTabLocators.JOIN_NOW;

    // Benefit text on the paywall bottom sheet
    public static final String PAYWALL_BENEFIT_TEXT = "//android.widget.TextView[@text=\"Renews every 3 months • Cancel Anytime\"]";

    // Continue button on the paywall bottom sheet
    public static final String PAYWALL_CONTINUE_BUTTON = "//L0.W/android.view.View/android.view.View/android.view.View[4]";

    // Play Store subscribe button on payment sheet
    public static final String PLAY_SUBSCRIBE_BUTTON = "//android.widget.Button[@resource-id=\"com.android.vending:id/0_resource_name_obfuscated\"]";

    // Confirmation message layout after successful purchase
    public static final String PLAY_CONFIRMATION_MESSAGE = "(//android.widget.LinearLayout[@resource-id=\"com.android.vending:id/0_resource_name_obfuscated\"])[4]";
}


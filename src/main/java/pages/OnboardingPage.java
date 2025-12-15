package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import locators.OnboardingLocators;
import locators.ProfileTabLocators;
import locators.HomeTabLocators;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * OnboardingPage - Handles onboarding flow through all screens
 */
public class OnboardingPage extends BasePage {

    public OnboardingPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Refresh context by switching Profile -> Home. No child profile validation here.
     */
    public void refreshViaProfileToHome() {
        try {
            // Navigate to Profile tab
            By profileTab = By.xpath(ProfileTabLocators.PROFILE_TAB_ICON);
            waitForElementToBeClickable(profileTab);
            driver.findElement(profileTab).click();
            Thread.sleep(1000);

            // Navigate back to Home tab
            By homeTab = By.xpath(HomeTabLocators.HOME_TAB_ICON);
            waitForElementToBeClickable(homeTab);
            driver.findElement(homeTab).click();
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("⚠️ Tab refresh (Profile -> Home) failed: " + e.getMessage());
        }
    }

    // ==================== SCREEN 1: Tap to Start ====================
    
    public void clickTapToStart() throws Exception {
        System.out.println("📱 Screen 1: Clicking Tap to Start...");
        By tapToStartLocator = By.xpath(OnboardingLocators.TAP_TO_START);
        waitForElementToBeClickable(tapToStartLocator);
        driver.findElement(tapToStartLocator).click();
        Thread.sleep(2000);
        System.out.println("✅ Screen 1 completed");
    }

    // ==================== SCREEN 2: Explore App Button ====================
    
    public void clickExploreAppButton() throws Exception {
        System.out.println("📱 Screen 2: Clicking Explore App button...");
        By exploreAppButton = By.xpath(OnboardingLocators.EXPLORE_APP_BUTTON);
        waitForElementToBeClickable(exploreAppButton);
        driver.findElement(exploreAppButton).click();
        Thread.sleep(2000);
        System.out.println("✅ Screen 2 completed");
    }

    // ==================== SCREEN 3: Baby's Name, Date of Birth, Gender ====================
    
    public void enterBabyName(String babyName) throws Exception {
        System.out.println("📱 Screen 3: Entering baby's name: " + babyName);
        By babyNameField = By.xpath(OnboardingLocators.YOUR_BABYS_NAME_FIELD);
        waitForElementToBeClickable(babyNameField);
        driver.findElement(babyNameField).click();
        Thread.sleep(500);
        driver.findElement(babyNameField).clear();
        driver.findElement(babyNameField).sendKeys(babyName);
        Thread.sleep(1000);
    }

    public void selectDateOfBirth(int month, int day, int year) throws Exception {
        System.out.println("📱 Screen 3: Selecting date of birth: " + month + "/" + day + "/" + year);
        
        // Click on baby's age field to open da	te picker bottom sheet
        System.out.println("   Step 1: Clicking baby's age field to open date picker...");
        By babysAgeField = By.xpath(OnboardingLocators.BABYS_AGE_FIELD);
        
        // Try multiple locator strategies if the primary one fails
        WebElement ageFieldElement = null;
        try {
            if (isVisible(babysAgeField, 5)) {
                ageFieldElement = driver.findElement(babysAgeField);
                System.out.println("   ✅ Baby's age field found using primary locator");
            }
        } catch (Exception e) {
            System.out.println("   ⚠️ Primary locator failed, trying alternative locators...");
            // Try alternative locators
            String[] alternativeLocators = {
                "//android.view.View[contains(@content-desc, 'age')]/android.widget.EditText",
                "//android.view.View[contains(@content-desc, 'Age')]/android.widget.EditText",
                "//android.widget.EditText[contains(@content-desc, 'age')]",
                "//android.widget.EditText[contains(@content-desc, 'Age')]"
            };
            
            for (String altLocator : alternativeLocators) {
                try {
                    By altBy = By.xpath(altLocator);
                    if (isVisible(altBy, 2)) {
                        ageFieldElement = driver.findElement(altBy);
                        System.out.println("   ✅ Baby's age field found using alternative locator: " + altLocator);
                        break;
                    }
                } catch (Exception ex) {
                    // Continue to next alternative
                }
            }
        }
        
        if (ageFieldElement == null) {
            throw new Exception("Baby's age field not found! Please provide the correct XPath. Tried: " + OnboardingLocators.BABYS_AGE_FIELD);
        }
        
        System.out.println("   ✅ Baby's age field found and visible");
        
        // Wait for element to be clickable and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ageFieldElement));
        ageFieldElement.click();
        System.out.println("   ✅ Clicked on baby's age field");
        Thread.sleep(1500); // Wait for date picker bottom sheet to open
        
        // Verify date picker bottom sheet appeared by checking for month wheel
        System.out.println("   Step 2: Verifying date picker bottom sheet opened...");
        By monthWheel = By.xpath(OnboardingLocators.MONTH_SEEKBAR);
        
        // Wait and verify month wheel is visible (indicates date picker opened)
        int retries = 0;
        boolean datePickerVisible = false;
        while (retries < 5 && !datePickerVisible) {
            try {
                if (isVisible(monthWheel, 2)) {
                    datePickerVisible = true;
                    System.out.println("   ✅ Date picker bottom sheet opened successfully");
                    break;
                }
            } catch (Exception e) {
                retries++;
                Thread.sleep(500);
            }
        }
        
        if (!datePickerVisible) {
            throw new Exception("Date picker bottom sheet did not open after clicking baby's age field!");
        }
        
        // Get month wheel element
        waitForElementToBeClickable(monthWheel);
        WebElement monthElement = driver.findElement(monthWheel);
        
        // Get day wheel element
        By dayWheel = By.xpath(OnboardingLocators.DAY_SEEKBAR);
        waitForElementToBeClickable(dayWheel);
        WebElement dayElement = driver.findElement(dayWheel);
        
        // Get year wheel element
        By yearWheel = By.xpath(OnboardingLocators.YEAR_SEEKBAR);
        waitForElementToBeClickable(yearWheel);
        WebElement yearElement = driver.findElement(yearWheel);
        
        // Read current values from wheels
        String currentMonthText = monthElement.getAttribute("content-desc");
        String currentDayText = dayElement.getAttribute("content-desc");
        String currentYearText = yearElement.getAttribute("content-desc");
        
        System.out.println("   Current values - Month: " + currentMonthText + ", Day: " + currentDayText + ", Year: " + currentYearText);
        
        // Parse and convert current values to numbers
        int currentMonth = parseMonthToNumber(currentMonthText);
        int currentDay = parseDayToNumber(currentDayText);
        int currentYear = parseYearToNumber(currentYearText);
        
        System.out.println("   Parsed current values - Month: " + currentMonth + ", Day: " + currentDay + ", Year: " + currentYear);
        
        // Calculate scroll steps needed
        int monthSteps = calculateMonthSteps(currentMonth, month);
        int daySteps = calculateDaySteps(currentDay, day, currentMonth, month, currentYear, year);
        int yearSteps = calculateYearSteps(currentYear, year);
        
        System.out.println("   Calculated scroll steps - Month: " + monthSteps + ", Day: " + daySteps + ", Year: " + yearSteps);
        
        // Scroll month wheel
        if (monthSteps != 0) {
            scrollPicker(monthElement, monthSteps);
            Thread.sleep(500);
        }
        
        // Scroll day wheel
        if (daySteps != 0) {
            scrollPicker(dayElement, daySteps);
            Thread.sleep(500);
        }
        
        // Scroll year wheel
        // Note: For year wheels, scroll direction might be inverted
        // Swiping down typically goes to earlier years, swiping up goes to later years
        if (yearSteps != 0) {
            // Invert the direction for years (down = earlier year, up = later year)
            int invertedYearSteps = -yearSteps;
            System.out.println("   Year scroll: original steps=" + yearSteps + ", inverted steps=" + invertedYearSteps);
            scrollPicker(yearElement, invertedYearSteps);
            Thread.sleep(500);
            
            // Verify the year after scrolling
            String verifyYearText = yearElement.getAttribute("content-desc");
            int verifyYear = parseYearToNumber(verifyYearText);
            System.out.println("   Verified year after scroll: " + verifyYear + " (target: " + year + ")");
            
            // If still not correct, try one more adjustment
            if (verifyYear != year && Math.abs(verifyYear - year) <= 2) {
                int adjustment = year - verifyYear;
                System.out.println("   Making fine adjustment: " + adjustment + " steps");
                scrollPicker(yearElement, -adjustment); // Invert again
                Thread.sleep(500);
            }
        }
        
        // Click Continue button on date picker
        By continueButtonDatePicker = By.xpath(OnboardingLocators.CONTINUE_BUTTON_DATE_PICKER);
        waitForElementToBeClickable(continueButtonDatePicker);
        driver.findElement(continueButtonDatePicker).click();
        Thread.sleep(2000);
    }
    
    /**
     * Parse month name or number to month number (1-12)
     */
    private int parseMonthToNumber(String monthText) {
        if (monthText == null || monthText.trim().isEmpty()) {
            return 1; // Default to January
        }
        
        monthText = monthText.trim();
        
        // Try to parse as number first
        try {
            int monthNum = Integer.parseInt(monthText);
            if (monthNum >= 1 && monthNum <= 12) {
                return monthNum;
            }
        } catch (NumberFormatException e) {
            // Not a number, try parsing as month name
        }
        
        // Parse month names (case-insensitive)
        String monthLower = monthText.toLowerCase();
        if (monthLower.contains("january") || monthLower.contains("jan")) return 1;
        if (monthLower.contains("february") || monthLower.contains("feb")) return 2;
        if (monthLower.contains("march") || monthLower.contains("mar")) return 3;
        if (monthLower.contains("april") || monthLower.contains("apr")) return 4;
        if (monthLower.contains("may")) return 5;
        if (monthLower.contains("june") || monthLower.contains("jun")) return 6;
        if (monthLower.contains("july") || monthLower.contains("jul")) return 7;
        if (monthLower.contains("august") || monthLower.contains("aug")) return 8;
        if (monthLower.contains("september") || monthLower.contains("sep")) return 9;
        if (monthLower.contains("october") || monthLower.contains("oct")) return 10;
        if (monthLower.contains("november") || monthLower.contains("nov")) return 11;
        if (monthLower.contains("december") || monthLower.contains("dec")) return 12;
        
        // Default to January if cannot parse
        System.out.println("   ⚠️ Could not parse month: " + monthText + ", defaulting to January");
        return 1;
    }
    
    /**
     * Parse day text to day number (1-31)
     */
    private int parseDayToNumber(String dayText) {
        if (dayText == null || dayText.trim().isEmpty()) {
            return 1; // Default to day 1
        }
        
        try {
            int day = Integer.parseInt(dayText.trim());
            if (day >= 1 && day <= 31) {
                return day;
            }
        } catch (NumberFormatException e) {
            // Try to extract number from text
            String digits = dayText.replaceAll("[^0-9]", "");
            if (!digits.isEmpty()) {
                try {
                    int day = Integer.parseInt(digits);
                    if (day >= 1 && day <= 31) {
                        return day;
                    }
                } catch (NumberFormatException ex) {
                    // Ignore
                }
            }
        }
        
        System.out.println("   ⚠️ Could not parse day: " + dayText + ", defaulting to day 1");
        return 1;
    }
    
    /**
     * Parse year text to year number
     */
    private int parseYearToNumber(String yearText) {
        if (yearText == null || yearText.trim().isEmpty()) {
            return 2020; // Default year
        }
        
        try {
            // Extract 4-digit year
            String digits = yearText.replaceAll("[^0-9]", "");
            if (digits.length() >= 4) {
                return Integer.parseInt(digits.substring(0, 4));
            } else if (!digits.isEmpty()) {
                int year = Integer.parseInt(digits);
                // If 2 digits, assume 20xx
                if (year < 100) {
                    year += 2000;
                }
                return year;
            }
        } catch (NumberFormatException e) {
            // Ignore
        }
        
        System.out.println("   ⚠️ Could not parse year: " + yearText + ", defaulting to 2020");
        return 2020;
    }
    
    /**
     * Calculate month scroll steps with bounds checking
     */
    private int calculateMonthSteps(int currentMonth, int targetMonth) {
        // Validate inputs
        if (targetMonth < 1 || targetMonth > 12) {
            System.out.println("   ⚠️ Invalid target month: " + targetMonth + ", using current month");
            return 0;
        }
        
        int steps = targetMonth - currentMonth;
        
        // Handle wrap-around (e.g., from Dec to Jan)
        if (Math.abs(steps) > 6) {
            // Take shorter path
            if (steps > 0) {
                steps = steps - 12; // Go backwards
            } else {
                steps = steps + 12; // Go forwards
            }
        }
        
        return steps;
    }
    
    /**
     * Calculate day scroll steps with bounds checking
     */
    private int calculateDaySteps(int currentDay, int targetDay, int currentMonth, int targetMonth, int currentYear, int targetYear) {
        // Validate inputs
        if (targetDay < 1 || targetDay > 31) {
            System.out.println("   ⚠️ Invalid target day: " + targetDay + ", using current day");
            return 0;
        }
        
        // If month or year changed, we need to account for that
        // For simplicity, calculate based on current day position
        int steps = targetDay - currentDay;
        
        // Handle wrap-around (e.g., from 31 to 1)
        // Get max days in target month
        int maxDays = getDaysInMonth(targetMonth, targetYear);
        if (Math.abs(steps) > maxDays / 2) {
            // Take shorter path
            if (steps > 0) {
                steps = steps - maxDays; // Go backwards
            } else {
                steps = steps + maxDays; // Go forwards
            }
        }
        
        // Ensure we don't exceed bounds
        if (targetDay > maxDays) {
            System.out.println("   ⚠️ Target day " + targetDay + " exceeds max days (" + maxDays + ") in month, using max day");
            steps = maxDays - currentDay;
        }
        
        return steps;
    }
    
    /**
     * Calculate year scroll steps with bounds checking
     */
    private int calculateYearSteps(int currentYear, int targetYear) {
        // Validate inputs (reasonable year range)
        if (targetYear < 1900 || targetYear > 2100) {
            System.out.println("   ⚠️ Invalid target year: " + targetYear + ", using current year");
            return 0;
        }
        
        int steps = targetYear - currentYear;
        
        // Limit maximum scroll steps to prevent excessive scrolling
        int maxSteps = 50; // Reasonable limit
        if (Math.abs(steps) > maxSteps) {
            System.out.println("   ⚠️ Year difference too large (" + steps + "), limiting to " + maxSteps);
            steps = steps > 0 ? maxSteps : -maxSteps;
        }
        
        return steps;
    }
    
    /**
     * Get number of days in a month
     */
    private int getDaysInMonth(int month, int year) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // Check for leap year
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        
        if (month >= 1 && month <= 12) {
            return daysInMonth[month - 1];
        }
        
        return 31; // Default
    }
    
    /**
     * Check if year is a leap year
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void selectGenderBoy() throws Exception {
        System.out.println("📱 Screen 3: Selecting gender: Boy");
        By genderBoy = By.xpath(OnboardingLocators.GENDER_BOY);
        waitForElementToBeClickable(genderBoy);
        driver.findElement(genderBoy).click();
        Thread.sleep(1000);
    }

    public void clickContinueButton() throws Exception {
        System.out.println("📱 Screen 3: Clicking Continue button");
        By continueButton = By.xpath(OnboardingLocators.CONTINUE_BUTTON_DATE_PICKER);
        waitForElementToBeClickable(continueButton);
        driver.findElement(continueButton).click();
        Thread.sleep(2000);
        System.out.println("✅ Screen 3 completed");
    }

    // ==================== SCREEN 4: What Convinced You ====================
    
    public void selectWhatConvincedYou() throws Exception {
        System.out.println("📱 Screen 4: Selecting 'What convinced you' option");
        By whatConvincedYou = By.xpath(OnboardingLocators.WHAT_CONVINCED_YOU);
        waitForElementToBeClickable(whatConvincedYou);
        driver.findElement(whatConvincedYou).click();
        Thread.sleep(1000);
    }

    public void clickContinueScreen4() throws Exception {
        System.out.println("📱 Screen 4: Clicking Continue button");
        By continueButton = By.xpath(OnboardingLocators.CONTINUE_BUTTON_DATE_PICKER);
        waitForElementToBeClickable(continueButton);
        driver.findElement(continueButton).click();
        Thread.sleep(2000);
        System.out.println("✅ Screen 4 completed");
    }

    // ==================== SCREEN 5: How Aware Are You ====================
    
    public void selectHowAwareAreYou() throws Exception {
        System.out.println("📱 Screen 5: Selecting 'How aware are you' option");
        By howAwareAreYou = By.xpath(OnboardingLocators.HOW_AWARE_ARE_YOU);
        waitForElementToBeClickable(howAwareAreYou);
        driver.findElement(howAwareAreYou).click();
        Thread.sleep(1000);
    }

    public void clickContinueScreen5() throws Exception {
        System.out.println("📱 Screen 5: Clicking Continue button");
        By continueButton = By.xpath(OnboardingLocators.CONTINUE_BUTTON_DATE_PICKER);
        waitForElementToBeClickable(continueButton);
        driver.findElement(continueButton).click();
        Thread.sleep(2000);
        System.out.println("✅ Screen 5 completed");
    }

    // ==================== SCREEN 6: How Much Time ====================
    
    public void selectHowMuchTime() throws Exception {
        System.out.println("📱 Screen 6: Selecting 'How much time' option");
        By howMuchTime = By.xpath(OnboardingLocators.HOW_MUCH_TIME);
        waitForElementToBeClickable(howMuchTime);
        driver.findElement(howMuchTime).click();
        Thread.sleep(1000);
    }

    public void clickContinueScreen6() throws Exception {
        System.out.println("📱 Screen 6: Clicking Continue button");
        By continueButton = By.xpath(OnboardingLocators.CONTINUE_BUTTON_DATE_PICKER);
        waitForElementToBeClickable(continueButton);
        driver.findElement(continueButton).click();
        Thread.sleep(6000); // Wait for animation (5-6 seconds)
        System.out.println("✅ Screen 6 completed");
    }

    // ==================== SCREEN 7: Phone Number and Email ====================
    
    public void enterPhoneNumber(String phoneNumber) throws Exception {
        System.out.println("📱 Screen 7: Entering phone number: " + phoneNumber);
        By phoneNumberField = By.xpath(OnboardingLocators.PHONE_NUMBER_FIELD);
        waitForElementToBeClickable(phoneNumberField);
        driver.findElement(phoneNumberField).click();
        Thread.sleep(500);
        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        Thread.sleep(1000);
    }

    public void enterEmail(String email) throws Exception {
        System.out.println("📱 Screen 7: Entering email: " + email);
        By emailField = By.xpath(OnboardingLocators.EMAIL_FIELD);
        waitForElementToBeClickable(emailField);
        driver.findElement(emailField).click();
        Thread.sleep(500);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        Thread.sleep(1000);
    }

    public void clickContinueScreen7() throws Exception {
        System.out.println("📱 Screen 7: Clicking Continue button");
        By continueButton = By.xpath(OnboardingLocators.CONTINUE_BUTTON_DATE_PICKER);
        waitForElementToBeClickable(continueButton);
        driver.findElement(continueButton).click();
        Thread.sleep(2000);
        System.out.println("✅ Screen 7 completed");
    }

    // ==================== Dismiss Popups ====================
    
    public void dismissTestimonial() throws Exception {
        System.out.println("📱 Dismissing testimonial popup...");
        try {
            By dismissTestimonial = By.xpath(OnboardingLocators.DISMISS_TESTIMONIAL);
            waitForElementToBeClickable(dismissTestimonial);
            driver.findElement(dismissTestimonial).click();
            Thread.sleep(2000);
            System.out.println("✅ Testimonial dismissed");
        } catch (Exception e) {
            System.out.println("⚠️ Testimonial popup not found or already dismissed");
        }
    }

    public void dismissPlan() throws Exception {
        System.out.println("📱 Dismissing plan popup...");
        try {
            By dismissPlan = By.xpath(OnboardingLocators.DISMISS_PLAN);
            waitForElementToBeClickable(dismissPlan);
            driver.findElement(dismissPlan).click();
            Thread.sleep(2000);
            System.out.println("✅ Plan popup dismissed");
        } catch (Exception e) {
            System.out.println("⚠️ Plan popup not found or already dismissed");
        }
    }

    /**
     * Opens the plan/paywall sheet after onboarding to validate purchase entry point.
     * @return true if the sheet is visible.
     */
    public boolean openPlanSelection() throws Exception {
        System.out.println("🛒 Opening plan selection/paywall...");
        By choosePlan = By.xpath(OnboardingLocators.CHOOSE_A_PLAN);
        try {
            if (isVisible(choosePlan, 5)) {
                driver.findElement(choosePlan).click();
                Thread.sleep(2000);
                return isPlanSheetVisible();
            }
        } catch (Exception e) {
            System.out.println("⚠️ Unable to tap Choose a Plan: " + e.getMessage());
        }
        return isPlanSheetVisible();
    }

    /**
     * Checks if plan/paywall sheet is visible by looking for its dismiss control.
     */
    public boolean isPlanSheetVisible() {
        try {
            return isVisible(By.xpath(OnboardingLocators.DISMISS_PLAN), 5);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Safe dismissal helper for plan sheet.
     */
    public void dismissPlanIfVisible() {
        try {
            By dismissPlan = By.xpath(OnboardingLocators.DISMISS_PLAN);
            if (isVisible(dismissPlan, 2)) {
                driver.findElement(dismissPlan).click();
                Thread.sleep(1000);
            }
        } catch (Exception ignored) { }
    }

    /**
     * Complete onboarding but preserve the offerings popup (do not dismiss plan).
     */
    public void performCompleteOnboardingPreserveOfferings(String babyName, int month, int day, int year,
                                          String phoneNumber, String email) throws Exception {
        // Screen 1
        clickTapToStart();

        // Screen 2
        clickExploreAppButton();

        // Screen 3
        enterBabyName(babyName);
        selectDateOfBirth(month, day, year);
        selectGenderBoy();
        clickContinueButton();

        // Screen 4
        selectWhatConvincedYou();
        clickContinueScreen4();

        // Screen 5
        selectHowAwareAreYou();
        clickContinueScreen5();

        // Screen 6
        selectHowMuchTime();
        clickContinueScreen6();

        // Screen 7
        enterPhoneNumber(phoneNumber);
        enterEmail(email);
        clickContinueScreen7();

        // Only dismiss testimonial; keep offerings popup visible
        dismissTestimonial();

        System.out.println("✅ Complete onboarding flow finished (offerings preserved)");
    }

    // ==================== Complete Onboarding Flow ====================
    
    public void performCompleteOnboarding(String babyName, int month, int day, int year, 
                                          String phoneNumber, String email) throws Exception {
        // Screen 1
        clickTapToStart();
        
        // Screen 2
        clickExploreAppButton();
        
        // Screen 3
        enterBabyName(babyName);
        selectDateOfBirth(month, day, year);
        selectGenderBoy();
        clickContinueButton();
        
        // Screen 4
        selectWhatConvincedYou();
        clickContinueScreen4();
        
        // Screen 5
        selectHowAwareAreYou();
        clickContinueScreen5();
        
        // Screen 6
        selectHowMuchTime();
        clickContinueScreen6();
        
        // Screen 7
        enterPhoneNumber(phoneNumber);
        enterEmail(email);
        clickContinueScreen7();
        
        // Dismiss popups
        dismissTestimonial();
        dismissPlan();
        
        System.out.println("✅ Complete onboarding flow finished");
    }

    // ==================== Validate Successful Onboarding ====================
    
    /**
     * Validates successful onboarding by refreshing via Profile tab and checking child profile.
     * Flow: wait → open Profile tab → return to Home tab → validate child profile.
     * @return true if child profile is visible, false otherwise
     */
    public boolean validateSuccessfulOnboarding() throws Exception {
        System.out.println("🔍 Validating successful onboarding...");
        System.out.println("   Navigating to Profile tab then back to Home to refresh data...");

        // Small wait for transition into home after onboarding
        Thread.sleep(2000);

        // Navigate to Profile tab
        try {
            By profileTab = By.xpath(ProfileTabLocators.PROFILE_TAB_ICON);
            waitForElementToBeClickable(profileTab);
            driver.findElement(profileTab).click();
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("⚠️ Could not open Profile tab: " + e.getMessage());
        }

        // Navigate back to Home tab to refresh
        try {
            By homeTab = By.xpath(HomeTabLocators.HOME_TAB_ICON);
            waitForElementToBeClickable(homeTab);
            driver.findElement(homeTab).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("⚠️ Could not return to Home tab: " + e.getMessage());
        }

        // Now validate child profile visibility
        System.out.println("   Checking for child profile visibility...");
        By childProfile = By.xpath(OnboardingLocators.CHILD_PROFILE);
        
        boolean isVisible = false;
        int maxRetries = 15;
        int retryCount = 0;
        
        while (retryCount < maxRetries && !isVisible) {
            try {
                if (isVisible(childProfile, 2)) {
                    isVisible = true;
                    System.out.println("   ✅ Child profile is visible - Onboarding SUCCESSFUL!");
                    break;
                }
            } catch (Exception e) {
                // Continue retrying
            }
            
            retryCount++;
            if (retryCount < maxRetries) {
                Thread.sleep(1000); // Wait 1 second before retry
            }
        }
        
        if (!isVisible) {
            System.out.println("   ❌ Child profile not visible after " + maxRetries + " seconds - Onboarding validation FAILED");
        }
        
        return isVisible;
    }

    // ==================== Helper Methods ====================
    
    private void waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("⚠️ Element not clickable: " + locator);
        }
    }

    /**
     * Scrolls a date picker wheel by the specified number of steps
     * Positive steps = scroll down (increase value)
     * Negative steps = scroll up (decrease value)
     * 
     * @param element The date picker wheel element
     * @param steps Number of steps to scroll (positive = down, negative = up)
     * @throws Exception
     */
    private void scrollPicker(WebElement element, int steps) throws Exception {
        if (steps == 0) {
            System.out.println("   No scrolling needed (steps = 0)");
            return;
        }
        
        // Get the center coordinates of the element
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);
        
        // Determine scroll direction
        // Positive steps = scroll down (swipe down gesture)
        // Negative steps = scroll up (swipe up gesture)
        String direction = steps > 0 ? "down" : "up";
        int absoluteSteps = Math.abs(steps);
        
        System.out.println("   Scrolling picker " + direction + " by " + absoluteSteps + " step(s)");
        
        // Perform swipe gesture for each step
        // Each swipe moves exactly one item in the wheel
        for (int i = 0; i < absoluteSteps; i++) {
            // Calculate swipe distance (small distance for one item scroll)
            // Typical date picker item height is around 50-80 pixels
            int swipeDistance = 60; // pixels to move for one item
            
            int startY = centerY;
            int endY;
            
            if (steps > 0) {
                // Scroll down: swipe from center down
                endY = centerY + swipeDistance;
            } else {
                // Scroll up: swipe from center up
                endY = centerY - swipeDistance;
            }
            
            // Perform vertical swipe gesture using mobile: swipeGesture
            Map<String, Object> swipeParams = new HashMap<>();
            swipeParams.put("left", centerX - 10); // Small width around center
            swipeParams.put("top", Math.min(startY, endY) - 20);
            swipeParams.put("width", 20);
            swipeParams.put("height", swipeDistance + 40);
            swipeParams.put("direction", direction);
            swipeParams.put("percent", 1.0); // Full swipe
            
            try {
                ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", swipeParams);
            } catch (Exception e) {
                // Fallback: Use scrollGesture if swipeGesture not available
                Map<String, Object> scrollParams = new HashMap<>();
                scrollParams.put("left", centerX - 10);
                scrollParams.put("top", Math.min(startY, endY) - 20);
                scrollParams.put("width", 20);
                scrollParams.put("height", swipeDistance + 40);
                scrollParams.put("direction", direction);
                scrollParams.put("percent", 1.0);
                ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", scrollParams);
            }
            
            // Small delay between swipes to allow wheel to settle
            Thread.sleep(350);
        }
        
        // Final delay to ensure wheel has stopped scrolling
        Thread.sleep(500);
    }
}


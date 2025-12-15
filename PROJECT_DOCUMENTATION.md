# ProdigyBaby Test Automation Framework - Complete Documentation

## 📋 Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture & Design Patterns](#architecture--design-patterns)
3. [Project Structure](#project-structure)
4. [Package Documentation](#package-documentation)
   - [Base Package](#base-package)
   - [Pages Package](#pages-package)
   - [Locators Package](#locators-package)
   - [Tests Package](#tests-package)
   - [Utils Package](#utils-package)
5. [Class Details](#class-details)
6. [Method Reference](#method-reference)
7. [Test Execution Flow](#test-execution-flow)
8. [BrowserStack Integration](#browserstack-integration)

---

## Project Overview

**Project Name:** ProdigyBaby Test Automation Framework  
**Technology Stack:** Java, Appium, TestNG, Selenium, BrowserStack  
**Application Type:** Android Mobile App (Flutter-based)  
**Testing Framework:** Page Object Model (POM)  
**Total Classes:** 38 Java classes  
**Test Coverage:** Smoke, Sanity, Regression suites

### Purpose
This framework automates testing of the ProdigyBaby Android mobile application using Appium and BrowserStack cloud infrastructure. It validates UI elements, user flows, and functionality across different user types (NEW_USER, PROGRAM_USER, SUBSCRIPTION_USER, etc.).

---

## Architecture & Design Patterns

### Design Patterns Used:
1. **Page Object Model (POM)** - Each screen/feature has a dedicated Page class
2. **Factory Pattern** - DriverFactory manages driver creation
3. **Singleton Pattern** - Shared driver instance across tests
4. **Data Provider Pattern** - TestNG DataProviders for parameterized tests
5. **Builder Pattern** - DesiredCapabilities configuration

### Framework Layers:
```
┌─────────────────────────────────────┐
│      Test Classes (Tests Layer)     │
│  - Smoke, Sanity, Regression Tests  │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│     Page Objects (Pages Layer)      │
│  - HomeTabPage, LoginPage, etc.     │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│   Base Classes (Base Layer)         │
│  - BaseTest, BasePage, DriverFactory│
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│   Utilities (Utils Layer)           │
│  - ConfigReader, TestUsers, etc.    │
└─────────────────────────────────────┘
```

---

## Project Structure

```
src/main/java/
├── base/                    # Base classes for framework foundation
│   ├── BasePage.java        # Base class for all Page Objects
│   ├── BaseTest.java        # Base class for all Test classes
│   └── DriverFactory.java   # Driver creation and management
│
├── pages/                   # Page Object classes (11 classes)
│   ├── CommunityTabPage.java
│   ├── DaybookPage.java
│   ├── HomeTabPage.java
│   ├── LoginPage.java
│   ├── LogoutPage.java
│   ├── MedalPage.java
│   ├── ProfileTabPage.java
│   ├── ProgramTabPage.java
│   ├── RegisterPage.java
│   ├── StreakPage.java
│   └── SubscriptionPage.java
│
├── locators/                # XPath locators (6 classes)
│   ├── CommunityTabLocators.java
│   ├── HomeTabLocators.java
│   ├── LoginPageLocators.java
│   ├── LogoutPageLocators.java
│   ├── ProfileTabLocators.java
│   └── ProgramTabLocators.java
│
├── tests/                   # Test classes (8 classes)
│   ├── regression/          # Regression test suite
│   │   ├── AuthFlowTest.java
│   │   ├── CommunityTabTest.java
│   │   ├── ContentFlowTest.java
│   │   ├── HomeTabTest.java
│   │   ├── ProfileTabTest.java
│   │   └── ProgramTabTest.java
│   ├── sanity/              # Sanity test suite
│   │   └── SanityTest.java
│   └── smoke/               # Smoke test suite
│       ├── BrowserStackSimpleTest.java
│       └── SmokeTest.java
│
└── utils/                   # Utility classes (9 classes)
    ├── BrowserStackOptions.java
    ├── ConfigReader.java
    ├── CredentialsType.java
    ├── DataProviderUtil.java
    ├── ReporterUtil.java
    ├── TestUsers.java
    ├── User.java
    ├── UserType.java
    └── WaitUtil.java
```

**Total Classes Breakdown:**
- Base Classes: 3
- Page Objects: 11
- Locator Classes: 6
- Test Classes: 8
- Utility Classes: 9
- **Grand Total: 37 classes**

---

## Package Documentation

### Base Package

#### Purpose
Contains foundational classes that provide common functionality for all tests and page objects. These classes establish the framework's core infrastructure.

---

## Class Details

### 1. Base Package

#### 1.1 BasePage.java
**Purpose:** Abstract base class for all Page Object classes. Provides common methods for element interaction, waiting, and scrolling.

**Inheritance:** All Page classes extend this class

**Key Responsibilities:**
- Element interaction (click, type, getText)
- Element visibility checks
- Dynamic waits
- Scrolling functionality
- App stability checks

**Methods (11 methods):**

1. **`BasePage(AppiumDriver driver)`** - Constructor
   - **Purpose:** Initializes the page object with driver instance
   - **Logic:** Stores driver reference and creates WebDriverWait with 15-second timeout
   - **Parameters:** `driver` - AppiumDriver instance

2. **`click(By locator)`** - Protected method
   - **Purpose:** Clicks on an element identified by locator
   - **Logic:** Finds element using driver.findElement() and calls click()
   - **Parameters:** `locator` - By locator for the element

3. **`type(By locator, String text)`** - Protected method
   - **Purpose:** Types text into an input field
   - **Logic:** Finds element, clears existing text, then sends new text
   - **Parameters:** 
     - `locator` - By locator for input field
     - `text` - Text to type

4. **`getText(By locator)`** - Protected method
   - **Purpose:** Retrieves text content from an element
   - **Logic:** Finds element and returns its text content
   - **Parameters:** `locator` - By locator for the element
   - **Returns:** String - Element text

5. **`isDisplayed(By locator)`** - Protected method
   - **Purpose:** Checks if element is displayed without throwing exception
   - **Logic:** Tries to find element and check isDisplayed(), returns false on exception
   - **Parameters:** `locator` - By locator
   - **Returns:** boolean - true if displayed, false otherwise

6. **`waitForVisible(By locator, long seconds)`** - Protected method
   - **Purpose:** Waits for element to become visible with specified timeout
   - **Logic:** Creates WebDriverWait and waits for ExpectedConditions.visibilityOfElementLocated()
   - **Parameters:**
     - `locator` - By locator
     - `seconds` - Timeout in seconds

7. **`isVisible(By locator, int timeoutSeconds)`** - Protected method
   - **Purpose:** Checks if element is visible within timeout period
   - **Logic:** Uses WebDriverWait to wait for element visibility, returns true if found, false on timeout
   - **Parameters:**
     - `locator` - By locator
     - `timeoutSeconds` - Maximum wait time
   - **Returns:** boolean - true if visible, false if timeout

8. **`scrollToElement(By locator, int maxScrolls)`** - Protected method
   - **Purpose:** Scrolls page to find an element
   - **Logic:** Loops up to maxScrolls times, checks if element is displayed, scrolls down if not found
   - **Parameters:**
     - `locator` - By locator to find
     - `maxScrolls` - Maximum scroll attempts
   - **Returns:** boolean - true if element found, false otherwise

9. **`scrollDown()`** - Protected method
   - **Purpose:** Performs downward scroll gesture
   - **Logic:** Uses JavaScriptExecutor to execute mobile:scrollGesture with parameters for 80% scroll down
   - **Parameters:** None

10. **`ensureAppRunning()`** - Protected method
    - **Purpose:** Verifies app is running, restarts if needed
    - **Logic:** Tries to get page source, if fails, calls DriverFactory.restartApp()
    - **Parameters:** None

---

#### 1.2 BaseTest.java
**Purpose:** Base class for all test classes. Handles test setup, teardown, BrowserStack status reporting, and provides common test utilities.

**Inheritance:** All test classes extend this class

**Key Responsibilities:**
- Test lifecycle management (@BeforeMethod, @AfterMethod)
- BrowserStack session status reporting
- Driver initialization and cleanup
- App data reset functionality
- Logout flows
- Common wait utilities

**Methods (15 methods):**

1. **`setUp(Method method)`** - @BeforeMethod
   - **Purpose:** Initializes driver before each test
   - **Logic:** 
     - Gets existing driver from DriverFactory or creates new one
     - Checks if app is active, restarts if not
   - **Parameters:** `method` - TestNG Method object

2. **`markTestStatus(String status, String reason)`** - Public method
   - **Purpose:** Marks BrowserStack session status (passed/failed)
   - **Logic:** 
     - Casts driver to JavascriptExecutor
     - Executes browserstack_executor script with status and reason
     - Handles exceptions gracefully
   - **Parameters:**
     - `status` - "passed" or "failed"
     - `reason` - Reason for the status

3. **`tearDown(ITestResult result)`** - @AfterMethod
   - **Purpose:** Cleans up after test execution
   - **Logic:**
     - Checks test result status (SUCCESS/FAILURE)
     - Marks BrowserStack status accordingly
     - Quits driver and clears DriverFactory reference
   - **Parameters:** `result` - TestNG ITestResult object

4. **`setupTest()`** - Public method
   - **Purpose:** Manual test setup (alternative to @BeforeMethod)
   - **Logic:** Gets or creates driver from DriverFactory
   - **Parameters:** None

5. **`setupTestWithReset()`** - Public method
   - **Purpose:** Setup test with app data reset
   - **Logic:** Calls resetAppData(), waits 3 seconds, then setupTest()
   - **Parameters:** None

6. **`cleanupTest()`** - Public method
   - **Purpose:** Manual test cleanup
   - **Logic:** Closes driver via DriverFactory
   - **Parameters:** None

7. **`getDriver()`** - Public method
   - **Purpose:** Returns current driver instance
   - **Logic:** Returns protected driver field
   - **Returns:** AppiumDriver instance

8. **`resetAppData()`** - Public method
   - **Purpose:** Resets app data using ADB commands
   - **Logic:**
     - Force stops app: `adb shell am force-stop com.raising.prodigy`
     - Clears app data: `adb shell pm clear com.raising.prodigy`
     - Resets permissions: `adb shell pm reset-permissions com.raising.prodigy`
     - Includes wait times between operations
   - **Parameters:** None

9. **`waitForElementToBeClickable(By locator)`** - Public method
   - **Purpose:** Waits for element to become clickable
   - **Logic:** Creates WebDriverWait with 15-second timeout, waits for elementToBeClickable
   - **Parameters:** `locator` - By locator

10. **`waitForElementToBeVisible(By locator)`** - Public method
    - **Purpose:** Waits for element to become visible
    - **Logic:** Creates WebDriverWait with 15-second timeout, waits for visibilityOfElementLocated
    - **Parameters:** `locator` - By locator

11. **`checkAppStability()`** - Public method
    - **Purpose:** Checks if app is stable/responsive
    - **Logic:** Tries to get current URL, if fails tries to find any element
    - **Parameters:** None

12. **`performLogout()`** - Public method
    - **Purpose:** Performs complete logout flow
    - **Logic:**
      - Handles popups via HomeTabPage
      - Clicks Profile tab via LogoutPage
      - Scrolls to find logout button
      - Clicks logout button and confirms
    - **Parameters:** None
    - **Throws:** Exception if logout button not found

13. **`performLogoutAndLoginWithDifferentAccount(String email, String password)`** - Public method
    - **Purpose:** Logs out current user and logs in with different account
    - **Logic:**
      - Calls performLogout()
      - Verifies logout state card is visible
      - Clicks logout state card
      - Clicks Login option
      - Performs login with new credentials
    - **Parameters:**
      - `email` - Email for new account
      - `password` - Password for new account
    - **Throws:** Exception if logout state card not visible

---

#### 1.3 DriverFactory.java
**Purpose:** Manages Appium driver lifecycle. Creates, manages, and destroys driver instances. Supports BrowserStack cloud execution only.

**Key Responsibilities:**
- Driver creation (BrowserStack only)
- Driver instance management (singleton pattern)
- App activation/restart
- Driver cleanup

**Methods (7 methods):**

1. **`getDriver()`** - Static method
   - **Purpose:** Returns shared driver instance
   - **Logic:** Returns static sharedDriver field
   - **Returns:** AppiumDriver or null

2. **`createDriver()`** - Static method
   - **Purpose:** Creates new driver instance
   - **Logic:**
     - Checks if BrowserStack automation is enabled
     - Calls createBrowserStackDriver() if enabled
     - Throws exception if BrowserStack not enabled
   - **Returns:** AppiumDriver instance
   - **Throws:** RuntimeException if BrowserStack not configured

3. **`createBrowserStackDriver()`** - Private static method
   - **Purpose:** Creates BrowserStack AndroidDriver
   - **Logic:**
     - Gets capabilities from BrowserStackOptions
     - Gets BrowserStack hub URL
     - Creates AndroidDriver with hub URL and capabilities
     - Sets implicit wait to 15 seconds
   - **Returns:** AndroidDriver instance
   - **Throws:** Exception if driver creation fails

4. **`setDriver(AppiumDriver driver)`** - Static method
   - **Purpose:** Sets shared driver instance
   - **Logic:** Assigns driver to static sharedDriver field
   - **Parameters:** `driver` - AppiumDriver instance

5. **`isAppActive(AppiumDriver driver)`** - Static method
   - **Purpose:** Checks if app is currently active
   - **Logic:** Tries to get page source, returns true if successful, false on exception
   - **Parameters:** `driver` - AppiumDriver instance
   - **Returns:** boolean

6. **`restartApp(AppiumDriver driver)`** - Static method
   - **Purpose:** Restarts the app on device
   - **Logic:**
     - Checks if driver is AndroidDriver
     - Terminates app: `com.raising.prodigy`
     - Activates app: `com.raising.prodigy`
   - **Parameters:** `driver` - AppiumDriver instance

7. **`closeDriver()`** - Static method
   - **Purpose:** Closes and cleans up driver
   - **Logic:**
     - Calls driver.quit() if driver exists
     - Sets sharedDriver to null in finally block
   - **Parameters:** None

---

### 2. Pages Package

#### 2.1 LoginPage.java
**Purpose:** Handles all login-related interactions. Manages the complete 5-screen login flow.

**Screens Handled:**
1. Tap to Start
2. Continue Button
3. Advertisement Selection
4. Email Entry
5. Password Entry

**Methods (20+ methods):**

1. **`clickTapToStart()`** - Public method
   - **Purpose:** Clicks "Tap to Start" button on first screen
   - **Logic:** Waits for button to be clickable, clicks it, waits for next screen button

2. **`isTapStartVisible()`** - Public method
   - **Purpose:** Checks if "Tap to Start" screen is visible
   - **Returns:** boolean

3. **`clickSecondScreenButton()`** - Public method
   - **Purpose:** Clicks Continue button on second screen
   - **Logic:** Waits for button, clicks it, waits for advertisement options

4. **`isContinueButtonVisible()`** - Public method
   - **Purpose:** Checks if Continue button is visible
   - **Returns:** boolean

5. **`selectAdvertisement()`** - Public method
   - **Purpose:** Selects "Saw an advertisement" option
   - **Logic:** Clicks the advertisement option element

6. **`clickContinue()`** - Public method
   - **Purpose:** Clicks Continue after advertisement selection
   - **Logic:** Clicks continue button, waits for email field

7. **`isAdvertisementOptionsVisible()`** - Public method
   - **Purpose:** Checks if advertisement options are visible
   - **Returns:** boolean

8. **`enterEmail(String email)`** - Public method
   - **Purpose:** Enters email address
   - **Logic:** Clicks email field, waits 500ms, types email, waits 2 seconds

9. **`clickSignIn()`** - Public method
   - **Purpose:** Clicks Sign In button after email entry
   - **Logic:** Waits for button, clicks it, waits 3 seconds, waits for "Login with Password" button

10. **`isEmailFieldVisible()`** - Public method
    - **Purpose:** Checks if email field is visible
    - **Returns:** boolean

11. **`clickLoginWithPassword()`** - Public method
    - **Purpose:** Clicks "Login with Password" button
    - **Logic:** Clicks button, waits for password field

12. **`enterPassword(String password)`** - Public method
    - **Purpose:** Enters password
    - **Logic:** Clicks password field, types password

13. **`clickFinalSignIn()`** - Public method
    - **Purpose:** Clicks final Sign In button
    - **Logic:** Waits for button to be clickable, clicks it

14. **`isPasswordFieldVisible()`** - Public method
    - **Purpose:** Checks if password field is visible
    - **Returns:** boolean

15. **`performLogin(String email, String password)`** - Public method
    - **Purpose:** Complete login flow - all 5 screens
    - **Logic:** Calls all screen methods in sequence:
      - clickTapToStart()
      - clickSecondScreenButton()
      - selectAdvertisement()
      - clickContinue()
      - enterEmail(email)
      - clickSignIn()
      - clickLoginWithPassword()
      - enterPassword(password)
      - clickFinalSignIn()

16. **`validateSuccessfulLogin()`** - Public method
    - **Purpose:** Validates successful login
    - **Logic:** Waits for Activity Streak element to appear (indicates home screen loaded)
    - **Returns:** boolean

17. **`verifyInvalidEmailError()`** - Public method
    - **Purpose:** Verifies invalid email error is displayed
    - **Logic:** Uses Assert.assertTrue with invalidEmailError locator

18. **`verifyInvalidPasswordError()`** - Public method
    - **Purpose:** Verifies invalid password error is displayed
    - **Logic:** Uses Assert.assertTrue with invalidPasswordError locator

---

#### 2.2 HomeTabPage.java
**Purpose:** Handles interactions with the Home tab. Validates UI elements specific to different user types.

**Methods (20+ methods):**

1. **`isSubscribebuttonVisible()`** - Public method
   - **Purpose:** Checks if Subscribe button is visible
   - **Returns:** boolean

2. **`isNotificationIconVisible()`** - Public method
   - **Purpose:** Checks if notification icon is visible
   - **Returns:** boolean

3. **`isMembershipIconVisible()`** - Public method
   - **Purpose:** Checks if membership icon is visible
   - **Returns:** boolean

4. **`isExpertSessionTextVisible()`** - Public method
   - **Purpose:** Checks if Expert Sessions text is visible
   - **Returns:** boolean

5. **`isActivityStreakVisible()`** - Public method
   - **Purpose:** Checks if Activity Streak is visible (with scrolling)
   - **Logic:** Uses scrollToElement to find Activity Streak
   - **Returns:** boolean

6. **`isThisWeekActivityTextVisible()`** - Public method
   - **Purpose:** Checks if "This Week's Activities" text is visible
   - **Returns:** boolean

7. **`isSubscriptionActivityCardVisible()`** - Public method
   - **Purpose:** Checks if Subscription Activity Card is visible
   - **Returns:** boolean

8. **`isProgramCardVisible()`** - Public method
   - **Purpose:** Checks if Program Card is visible
   - **Logic:** Uses WebDriverWait to find specific ScrollView element
   - **Returns:** boolean

9. **`verifyHomePageLoaded()`** - Public method
   - **Purpose:** Verifies home page is loaded
   - **Logic:** Checks if Activity Streak is visible
   - **Returns:** boolean

10. **`getActivityStreakText()`** - Public method
    - **Purpose:** Gets Activity Streak text content
    - **Returns:** String - Activity Streak text or empty string

11. **`handleHelpBottomSheet()`** - Public method
    - **Purpose:** Handles help bottom sheet popup
    - **Logic:**
      - Waits 2 seconds
      - Checks multiple XPath patterns for help bottom sheet
      - If found, taps center of screen to dismiss
    - **Parameters:** None

12. **`handleAllPopups()`** - Public method
    - **Purpose:** Handles all types of popups on home screen
    - **Logic:**
      - Waits 2 seconds
      - Checks for sales popup with specific content-desc
      - If found, clicks close button and handles help bottom sheet
      - Also checks for generic popups (Close, X, Dismiss buttons)
      - Returns true if any popup was handled
    - **Returns:** boolean

13. **`validateHomeTabUI(UserType userType)`** - Public method
    - **Purpose:** Validates Home Tab UI elements based on user type
    - **Logic:**
      - **NEW_USER:** Checks Activity Streak and This Week's Activities
      - **LAUNCHPAD_USER:** Checks Activity Streak and Expert Sessions Text
      - **PROGRAM_USER:** Checks Activity Streak and Program Card
      - **PROGRAM_SUBSCRIPTION_USER:** Checks Activity Streak
      - **SUBSCRIPTION_USER:** Checks Subscription Activity Card, Membership Icon, and Activity Streak
    - **Parameters:** `userType` - UserType enum value

---

#### 2.3 ProgramTabPage.java
**Purpose:** Handles interactions with the Program tab. Validates program-related UI elements for different user types.

**Methods (12+ methods):**

1. **`clickProgramTab()`** - Public method
   - **Purpose:** Clicks on Program tab icon
   - **Logic:**
     - Waits for Program tab icon to be visible
     - Clicks it
     - Waits for program content to load (checks for Explore All Programs, Today's Plan, or ScrollView)
   - **Throws:** Exception

2. **`clickExploreAllPrograms()`** - Public method
   - **Purpose:** Clicks "Explore All Programs" button
   - **Logic:**
     - Waits for button to be visible
     - Clicks it
     - Waits for "Start Your Journey" text to appear (navigation complete)
   - **Throws:** Exception

3. **`isExploreAllProgramsVisible()`** - Public method
   - **Purpose:** Checks if "Explore All Programs" button is visible
   - **Returns:** boolean

4. **`isStartYourJourneyVisible()`** - Public method
   - **Purpose:** Checks if "Start Your Journey" text is visible
   - **Returns:** boolean

5. **`isTodaysPlanVisible()`** - Public method
   - **Purpose:** Checks if "Today's Plan" is visible
   - **Returns:** boolean

6. **`isProgramContentVisible()`** - Public method
   - **Purpose:** Checks if program content (ScrollView) is visible
   - **Returns:** boolean

7. **`verifyProgramTabLoaded()`** - Public method
   - **Purpose:** Verifies program tab is loaded
   - **Logic:** Calls isProgramContentVisible()
   - **Returns:** boolean

8. **`validateProgramTabUI(UserType userType)`** - Public method
    - **Purpose:** Validates Program Tab UI based on user type
    - **Logic:**
      - **NEW_USER:** Checks Explore All Programs button, clicks it, checks Start Your Journey
      - **LAUNCHPAD_USER:** Checks Start Your Journey (with scrolling if needed)
      - **PROGRAM_USER:** Checks Today's Plan (with scrolling if needed)
      - **PROGRAM_SUBSCRIPTION_USER:** Checks Today's Plan (with scrolling if needed)
      - **SUBSCRIPTION_USER:** Checks Explore All Programs, clicks it, checks Start Your Journey
    - **Parameters:** `userType` - UserType enum value

---

#### 2.4 CommunityTabPage.java
**Purpose:** Handles interactions with the Community tab. Validates community-related UI elements.

**Methods (8+ methods):**

1. **`clickCommunityTab()`** - Public method
   - **Purpose:** Clicks on Community tab icon
   - **Logic:**
     - Waits for Community tab icon
     - Clicks it
     - Waits for community content to load
   - **Throws:** Exception

2. **`openCommunityTab()`** - Public method
   - **Purpose:** Legacy method name (calls clickCommunityTab)
   - **Logic:** Delegates to clickCommunityTab()

3. **`verifyCommunityLoaded()`** - Public method
   - **Purpose:** Verifies community tab is loaded
   - **Logic:** Checks for ScrollView or View elements
   - **Returns:** boolean

4. **`isOpenCommunitiesTextVisible()`** - Public method
   - **Purpose:** Checks if "Open Communities" text is visible
   - **Returns:** boolean

5. **`isParentStoriesTextVisible()`** - Public method
   - **Purpose:** Checks if "Parent Stories" text is visible
   - **Returns:** boolean

6. **`isMyCommunitiesTextVisible()`** - Public method
   - **Purpose:** Checks if "My Communities" text is visible
   - **Returns:** boolean

7. **`validateCommunityTabUI(UserType userType)`** - Public method
    - **Purpose:** Validates Community Tab UI based on user type
    - **Logic:**
      - **NEW_USER:** Checks Open Communities and Parent Stories text
      - **LAUNCHPAD_USER, PROGRAM_USER, PROGRAM_SUBSCRIPTION_USER, SUBSCRIPTION_USER:** Checks My Communities text
    - **Parameters:** `userType` - UserType enum value

---

#### 2.5 ProfileTabPage.java
**Purpose:** Handles interactions with the Profile tab. Validates profile-related UI elements.

**Methods (12+ methods):**

1. **`clickProfileTab()`** - Public method
   - **Purpose:** Clicks on Profile tab icon
   - **Logic:**
     - Waits for Profile tab (4th ImageView in bottom navigation)
     - Clicks it
     - Waits for profile content to load
   - **Throws:** Exception

2. **`verifyProfileLoaded()`** - Public method
   - **Purpose:** Verifies profile tab is loaded
   - **Logic:** Checks for Profile keyword or ScrollView
   - **Returns:** boolean

3. **`isProfilePageViewVisible()`** - Public method
   - **Purpose:** Checks if Profile Page View is visible
   - **Returns:** boolean

4. **`isStarTrackerVisible()`** - Public method
   - **Purpose:** Checks if Star Tracker is visible
   - **Returns:** boolean

5. **`isProdigyTrailsVisible()`** - Public method
   - **Purpose:** Checks if Prodigy Trails is visible
   - **Returns:** boolean

6. **`isMyProgramsVisible()`** - Public method
   - **Purpose:** Checks if My Programs is visible
   - **Returns:** boolean

7. **`isSavedResourcesVisible()`** - Public method
   - **Purpose:** Checks if Saved Resources is visible
   - **Returns:** boolean

8. **`isProgramParentProfileCardVisible()`** - Public method
   - **Purpose:** Checks if Program Parent Profile Card is visible
   - **Returns:** boolean

9. **`validateProfileTabUI(UserType userType)`** - Public method
    - **Purpose:** Validates Profile Tab UI based on user type
    - **Logic:**
      - **NEW_USER, LAUNCHPAD_USER:** Checks Profile Page View
      - **PROGRAM_USER:** Checks Profile Page View, Star Tracker, Prodigy Trails, Saved Resources, Program Parent Profile Card, My Programs
      - **PROGRAM_SUBSCRIPTION_USER, SUBSCRIPTION_USER:** Checks Profile loaded (generic check)
    - **Parameters:** `userType` - UserType enum value

---

#### 2.6 LogoutPage.java
**Purpose:** Handles logout flow and profile tab navigation.

**Methods (8 methods):**

1. **`clickProfileTab()`** - Public method
   - **Purpose:** Clicks Profile tab to access logout
   - **Logic:** Waits for Profile tab, clicks it, waits 2 seconds
   - **Throws:** Exception

2. **`isLogoutButtonVisible()`** - Public method
   - **Purpose:** Checks if logout button is visible (with scrolling)
   - **Logic:** Uses scrollToElement to find logout button
   - **Returns:** boolean

3. **`clickLogoutButton()`** - Public method
   - **Purpose:** Clicks logout button
   - **Logic:** Waits for button, clicks it, waits 2 seconds for popup
   - **Throws:** Exception

4. **`confirmLogout()`** - Public method
   - **Purpose:** Confirms logout action
   - **Logic:** Waits for confirm button (10 seconds), clicks it, waits 3 seconds
   - **Throws:** Exception

5. **`isLogoutStateCardVisible()`** - Public method
   - **Purpose:** Checks if logout state card is visible (after logout)
   - **Returns:** boolean

6. **`clickLogoutStateCard()`** - Public method
   - **Purpose:** Clicks logout state card to access login options
   - **Logic:** Waits for card, clicks it, waits 2 seconds
   - **Throws:** Exception

7. **`clickLoginOption()`** - Public method
   - **Purpose:** Clicks Login option in bottom sheet
   - **Logic:** Waits for Login option, clicks it, waits 2 seconds
   - **Throws:** Exception

8. **`isOnboardingScreenVisible()`** - Public method
   - **Purpose:** Checks if returned to onboarding screen
   - **Returns:** boolean

---

#### 2.7-2.11 Other Page Classes
**DaybookPage.java, MedalPage.java, RegisterPage.java, StreakPage.java, SubscriptionPage.java**
- **Purpose:** Placeholder classes for future features
- **Status:** Currently contain TODO methods
- **Methods:** `verifyUIElements()`, `performCoreActions()` (stubbed)

---

### 3. Locators Package

**Purpose:** Centralized storage of XPath locators for all UI elements. Separates locators from page logic for easier maintenance.

#### 3.1 HomeTabLocators.java
**Contains:** 14 static final String constants for Home tab elements
- SUBSCRIBE_BUTTON
- NOTIFICATION_ICON
- MEMBERSHIP_ICON
- EXPERT_SESSIONS_TEXT
- ACTIVITY_STREAK
- THIS_WEEK_ACTIVITIES
- SUBSCRIPTION_ACTIVITY_CARD
- etc.

#### 3.2 LoginPageLocators.java
**Contains:** 8 static final String constants for Login flow elements
- TAP_TO_START
- GENERIC_BUTTON
- SAW_AD
- CONTINUE
- EDIT_TEXT
- SIGN_IN
- LOGIN_WITH_PASSWORD
- INVALID_EMAIL_ERROR
- INVALID_PASSWORD_ERROR

#### 3.3 ProgramTabLocators.java
**Contains:** 5 static final String constants
- PROGRAM_TAB_ICON
- EXPLORE_ALL_PROGRAMS
- START_YOUR_JOURNEY
- TODAYS_PLAN
- PROGRAM_CONTENT_SCROLL

#### 3.4 CommunityTabLocators.java
**Contains:** Community tab element locators
- COMMUNITY_TAB_ICON
- OPEN_COMMUNITIES_TEXT
- PARENT_STORIES_TEXT
- MY_COMMUNITIES_TEXT
- VIDEO_VIEW

#### 3.5 ProfileTabLocators.java
**Contains:** Profile tab element locators
- PROFILE_PAGE_VIEW
- STAR_TRACKER
- PRODIGY_TRAILS
- MY_PROGRAMS
- SAVED_RESOURCES
- PROGRAM_PARENT_PROFILE_CARD
- etc.

#### 3.6 LogoutPageLocators.java
**Contains:** Logout flow element locators
- PROFILE_TAB
- LOGOUT_BUTTON
- CONFIRM_LOGOUT_BUTTON
- LOGOUT_STATE_CARD
- LOGIN_OPTION
- ONBOARDING_SCREEN

---

### 4. Tests Package

#### 4.1 Regression Tests

##### 4.1.1 HomeTabTest.java
**Purpose:** Validates Home Tab UI for all user types
**Test Method:** `validateHomeTab(UserType userType)`
- Uses DataProvider for user types
- Logs in with user credentials
- Handles popups
- Validates Home Tab UI
- Resets app data

##### 4.1.2 ProgramTabTest.java
**Purpose:** Validates Program Tab UI for all user types
**Test Method:** `validateProgramTab(UserType userType)`
- Logs in
- Clicks Program tab
- Validates Program Tab UI
- Resets app data

##### 4.1.3 CommunityTabTest.java
**Purpose:** Validates Community Tab UI for all user types
**Test Method:** `validateCommunityTab(UserType userType)`
- Logs in
- Opens Community tab
- Validates Community Tab UI
- Resets app data

##### 4.1.4 ProfileTabTest.java
**Purpose:** Validates Profile Tab UI for all user types
**Test Method:** `validateProfileTab(UserType userType)`
- Logs in
- Clicks Profile tab
- Validates Profile Tab UI
- Resets app data

##### 4.1.5 AuthFlowTest.java
**Purpose:** Validates login with valid and invalid credentials
**Test Method:** `loginInputDomain(CredentialsType type, String email, String password)`
- Uses DataProvider for credential types
- Tests VALID, INVALID_EMAIL, INVALID_PASSWORD scenarios
- Validates error messages

##### 4.1.6 ContentFlowTest.java
**Purpose:** Legacy test - validates all tabs in sequence (can be deprecated)

#### 4.2 Sanity Tests

##### 4.2.1 SanityTest.java
**Purpose:** Quick health check after deployment
**Test Method:** `quickHealth()`
- Logs in
- Verifies Home tab loads
- Verifies Program tab loads

#### 4.3 Smoke Tests

##### 4.3.1 SmokeTest.java
**Purpose:** Basic launch and login verification
**Test Method:** `appLaunchAndLogin()`
- Performs login
- Handles popups
- Verifies home page loads

##### 4.3.2 BrowserStackSimpleTest.java
**Purpose:** Simple BrowserStack connectivity test

---

### 5. Utils Package

#### 5.1 BrowserStackOptions.java
**Purpose:** Manages BrowserStack configuration and capabilities

**Methods (4 methods):**

1. **`loadConfig()`** - Private static method
   - **Purpose:** Loads browserstack.yml configuration file
   - **Logic:** Uses SnakeYAML to parse YAML file from project root

2. **`getHubUrl()`** - Public static method
   - **Purpose:** Returns BrowserStack Hub URL with credentials
   - **Logic:** Constructs URL: `https://userName:accessKey@hub.browserstack.com/wd/hub`
   - **Returns:** URL object
   - **Throws:** RuntimeException if config not loaded

3. **`getCapabilities()`** - Public static method
   - **Purpose:** Builds DesiredCapabilities for BrowserStack
   - **Logic:**
     - Sets credentials (user, key)
     - Sets project and build info
     - Sets platform details (device, os_version)
     - Sets app (bs:// format)
     - Sets Android capabilities (platformName, automationName, appPackage, appActivity)
     - Enables test observability, video, network logs, console logs
   - **Returns:** DesiredCapabilities object

4. **`getBrowserstackAutomation()`** - Public static method
   - **Purpose:** Checks if BrowserStack automation is enabled
   - **Returns:** boolean

---

#### 5.2 ConfigReader.java
**Purpose:** Reads configuration from config.properties file

**Methods (1 method):**

1. **`get(String key, String defaultValue)`** - Public static method
   - **Purpose:** Gets property value from config.properties
   - **Logic:** Loads properties file in static block, returns property or default value
   - **Parameters:**
     - `key` - Property key
     - `defaultValue` - Default value if key not found
   - **Returns:** String - Property value

---

#### 5.3 DataProviderUtil.java
**Purpose:** Provides TestNG DataProviders for parameterized tests

**Methods (2 methods):**

1. **`userTypes()`** - @DataProvider
   - **Purpose:** Provides array of UserType enums for tests
   - **Returns:** Object[][] with 5 user types:
     - NEW_USER
     - PROGRAM_USER
     - SUBSCRIPTION_USER
     - LAUNCHPAD_USER
     - PROGRAM_SUBSCRIPTION_USER

2. **`credentialData()`** - @DataProvider
   - **Purpose:** Provides test data for login validation tests
   - **Returns:** Object[][] with:
     - VALID credentials
     - INVALID_EMAIL
     - INVALID_PASSWORD

---

#### 5.4 TestUsers.java
**Purpose:** Factory class that returns User objects based on UserType

**Methods (1 method):**

1. **`getUser(UserType type)`** - Public static method
   - **Purpose:** Returns User object with email and password for given user type
   - **Logic:** Switch statement mapping UserType to User credentials
   - **Parameters:** `type` - UserType enum
   - **Returns:** User object
   - **Throws:** IllegalArgumentException for unknown user type

---

#### 5.5 User.java
**Purpose:** Data class representing a test user

**Fields:**
- `email` - String
- `password` - String

**Methods:**
- Constructor: `User(String email, String password)`
- `getEmail()` - Returns email
- `getPassword()` - Returns password

---

#### 5.6 UserType.java
**Purpose:** Enum defining different user types in the application

**Values:**
- NEW_USER
- PROGRAM_USER
- SUBSCRIPTION_USER
- LAUNCHPAD_USER
- PROGRAM_SUBSCRIPTION_USER

---

#### 5.7 CredentialsType.java
**Purpose:** Enum for credential validation test types

**Values:**
- VALID
- INVALID_EMAIL
- INVALID_PASSWORD

---

#### 5.8 WaitUtil.java
**Purpose:** Utility class for explicit waits

**Methods (2 methods):**

1. **`untilClickable(AppiumDriver driver, By locator, long seconds)`** - Public method
   - **Purpose:** Waits for element to become clickable
   - **Logic:** Creates WebDriverWait, waits for elementToBeClickable
   - **Parameters:**
     - `driver` - AppiumDriver instance
     - `locator` - By locator
     - `seconds` - Timeout in seconds

2. **`waitForElementVisible(AppiumDriver driver, By locator, int timeoutSeconds)`** - Public static method
   - **Purpose:** Waits for element to become visible
   - **Logic:** Creates WebDriverWait, waits for visibilityOfElementLocated
   - **Returns:** boolean - true if visible, false on timeout

---

#### 5.9 ReporterUtil.java
**Purpose:** Simple logging utility for test reporting

**Methods (3 methods):**

1. **`info(String message)`** - Public static method
   - **Purpose:** Logs info message
   - **Logic:** Prints "[INFO] " + message

2. **`warn(String message)`** - Public static method
   - **Purpose:** Logs warning message
   - **Logic:** Prints "[WARN] " + message

3. **`error(String message)`** - Public static method
   - **Purpose:** Logs error message
   - **Logic:** Prints "[ERROR] " + message to stderr

---

## Test Execution Flow

### Typical Test Execution:

1. **@BeforeMethod (BaseTest.setUp)**
   - Gets or creates driver from DriverFactory
   - Checks if app is active, restarts if needed

2. **Test Method Execution**
   - Test class extends BaseTest
   - Gets driver via getDriver()
   - Performs test actions (login, navigation, validation)
   - Uses Page Objects for interactions

3. **@AfterMethod (BaseTest.tearDown)**
   - Marks BrowserStack status (passed/failed)
   - Quits driver
   - Clears DriverFactory reference

### Example Flow (HomeTabTest):

```
1. setUp() → Creates BrowserStack driver
2. validateHomeTab(NEW_USER) →
   a. LoginPage.performLogin()
   b. HomeTabPage.handleAllPopups()
   c. HomeTabPage.validateHomeTabUI(NEW_USER)
   d. resetAppData()
3. tearDown() → Marks status, quits driver
```

---

## BrowserStack Integration

### Configuration:
- **File:** `browserstack.yml` (project root)
- **Format:** YAML
- **Contains:** Credentials, device info, app ID

### Capabilities Set:
- BrowserStack credentials
- Project and build names
- Device name and OS version
- App (bs:// format)
- Android capabilities
- Test observability
- Video recording
- Network and console logs

### Status Reporting:
- Uses `browserstack_executor` JavaScript API
- Marks session as "passed" or "failed"
- Includes failure reason in status
- Executed in `BaseTest.tearDown()`

---

## Summary Statistics

- **Total Classes:** 37
- **Base Classes:** 3
- **Page Objects:** 11
- **Locator Classes:** 6
- **Test Classes:** 8
- **Utility Classes:** 9
- **Total Methods:** ~150+ methods
- **User Types Supported:** 5
- **Test Suites:** 3 (Smoke, Sanity, Regression)

---

## Best Practices Followed

1. **Page Object Model** - Separation of locators and logic
2. **DRY Principle** - Common methods in BasePage/BaseTest
3. **Data-Driven Testing** - TestNG DataProviders
4. **Dynamic Waits** - WebDriverWait instead of Thread.sleep
5. **Error Handling** - Try-catch blocks for robustness
6. **BrowserStack Integration** - Proper status reporting
7. **Modular Design** - Focused test classes per feature
8. **Maintainability** - Centralized locators

---

**Document Version:** 1.0  
**Last Updated:** 2025  
**Maintained By:** ProdigyBaby QA Team


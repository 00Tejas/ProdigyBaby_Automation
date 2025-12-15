# ProdigyBaby Automation Framework - Comprehensive Project Documentation

## Table of Contents
1. [Project Overview](#project-overview)
2. [Project Structure](#project-structure)
3. [Dependencies](#dependencies)
4. [Package Structure](#package-structure)
5. [Base Classes](#base-classes)
6. [Utility Classes](#utility-classes)
7. [Locator Classes](#locator-classes)
8. [Page Object Classes](#page-object-classes)
9. [Test Classes](#test-classes)
10. [Configuration Files](#configuration-files)
11. [TestNG Suites](#testng-suites)

---

## Project Overview

**Project Name:** ProdigyBaby  
**Type:** Mobile Automation Testing Framework  
**Platform:** Android  
**Testing Tool:** Appium with TestNG  
**Cloud Platform:** BrowserStack  
**Language:** Java 17  
**Build Tool:** Maven  

This is a comprehensive mobile automation testing framework for the Prodigy Baby Android application. The framework uses Page Object Model (POM) design pattern and supports execution on BrowserStack cloud platform.

---

## Project Structure

```
ProdigyBaby/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/                    # Base classes for framework
│   │   │   ├── locators/                # Locator classes (XPath constants)
│   │   │   ├── pages/                   # Page Object Model classes
│   │   │   ├── tests/                   # Test classes
│   │   │   │   ├── regression/          # Regression test suite
│   │   │   │   ├── smoke/               # Smoke test suite
│   │   │   │   └── sanity/              # Sanity test suite
│   │   │   └── utils/                   # Utility classes
│   │   └── resources/
│   │       ├── config.properties         # Configuration properties
│   │       └── logback.xml              # Logging configuration
│   └── test/
│       ├── java/
│       └── resources/                   # TestNG XML suites
│           ├── testng.xml
│           ├── regression-testng.xml
│           ├── login-testng.xml
│           ├── logout-testng.xml
│           ├── home-testng.xml
│           ├── program-tab-testng.xml
│           ├── community-tab-testng.xml
│           └── all-tests-testng.xml
├── browserstack.yml                     # BrowserStack configuration
├── pom.xml                             # Maven project configuration
├── README.md
├── BROWSERSTACK_SETUP.md
└── PROJECT_DOCUMENTATION.md
```

---

## Dependencies

### Core Dependencies (from pom.xml)
- **Selenium:** 4.11.0
- **Appium Java Client:** 8.5.1
- **TestNG:** 7.10.2
- **SLF4J:** 2.0.9
- **Logback:** 1.4.11
- **BrowserStack Java SDK:** 1.2.0
- **SnakeYAML:** 2.2 (for YAML parsing)

---

## Package Structure

### 1. `base` Package
Base classes that provide common functionality for all tests and page objects.

### 2. `locators` Package
Contains locator classes with XPath constants for all UI elements.

### 3. `pages` Package
Page Object Model classes representing different screens/pages of the application.

### 4. `tests` Package
Test classes organized by test suite:
- `regression/` - Comprehensive regression tests
- `smoke/` - Quick smoke tests
- `sanity/` - Sanity checks

### 5. `utils` Package
Utility classes for configuration, data providers, user management, and helper functions.

---

## Base Classes

### `base.BasePage`
**Purpose:** Base class for all Page Object classes. Provides common methods for element interaction.

**Methods:**
- `BasePage(AppiumDriver driver)` - Constructor
- `click(By locator)` - Click on an element
- `type(By locator, String text)` - Type text into an element
- `getText(By locator)` - Get text from an element
- `isDisplayed(By locator)` - Check if element is displayed
- `waitForVisible(By locator, long seconds)` - Wait for element to be visible
- `isVisible(By locator, int timeoutSeconds)` - Check visibility with timeout
- `scrollToElement(By locator, int maxScrolls)` - Scroll to find element
- `scrollDown()` - Scroll down the page
- `ensureAppRunning()` - Ensure app is running

---

### `base.BaseTest`
**Purpose:** Base test class that all test classes extend. Handles driver setup, teardown, and common test utilities.

**Methods:**
- `setUp(Method method)` - @BeforeMethod: Initialize driver before each test
- `tearDown(ITestResult result)` - @AfterMethod: Cleanup after each test
- `markTestStatus(String status, String reason)` - Mark BrowserStack test status
- `setupTest()` - Setup test driver
- `setupTestWithReset()` - Setup test with app data reset
- `cleanupTest()` - Cleanup test resources
- `getDriver()` - Get current driver instance
- `resetAppData()` - Reset app data using ADB commands
- `waitForElementToBeClickable(By locator)` - Wait for element to be clickable
- `waitForElementToBeVisible(By locator)` - Wait for element to be visible
- `checkAppStability()` - Check app stability
- `performLogout()` - Perform complete logout flow
- `performLogoutAndLoginWithDifferentAccount(String email, String password)` - Logout and login with different account

---

### `base.DriverFactory`
**Purpose:** Factory class for creating and managing AppiumDriver instances. Handles BrowserStack driver creation and APK selection based on environment.

**Methods:**
- `getDriver()` - Get shared driver instance
- `createDriver()` - Create new driver instance (BrowserStack only)
- `createBrowserStackDriver()` - Private method to create BrowserStack driver
- `setDriver(AppiumDriver driver)` - Set shared driver instance
- `isAppActive(AppiumDriver driver)` - Check if app is active
- `restartApp(AppiumDriver driver)` - Restart the app
- `closeDriver()` - Close and cleanup driver

**Key Features:**
- Environment-based APK selection (staging/prod)
- System property override support (`-Denv=prod`)
- BrowserStack integration

---

## Utility Classes

### `utils.ConfigReader`
**Purpose:** Reads configuration from config.properties with system property override support.

**Methods:**
- `get(String key, String defaultValue)` - Get property with default value
- `get(String key)` - Get property with system property override support

**Features:**
- System property override (e.g., `-Denv=prod`)
- Case-insensitive system property checking

---

### `utils.BrowserStackOptions`
**Purpose:** Handles BrowserStack configuration and capabilities setup from browserstack.yml.

**Methods:**
- `getHubUrl()` - Get BrowserStack Hub URL with credentials
- `getCapabilities()` - Get DesiredCapabilities for BrowserStack
- `getBrowserstackAutomation()` - Check if BrowserStack automation is enabled

**Static Initialization:**
- Loads browserstack.yml configuration on class load

---

### `utils.WaitUtil`
**Purpose:** Utility class for wait operations.

**Methods:**
- `WaitUtil(AppiumDriver driver)` - Constructor
- `untilClickable(By locator, long seconds)` - Wait until element is clickable
- `waitForElementVisible(AppiumDriver driver, By locator, int timeoutSeconds)` - Static method to wait for element visibility

---

### `utils.ReporterUtil`
**Purpose:** Utility for logging/reporting messages.

**Methods:**
- `info(String message)` - Log info message
- `warn(String message)` - Log warning message
- `error(String message)` - Log error message

---

### `utils.DataProviderUtil`
**Purpose:** TestNG DataProvider utility class.

**Methods:**
- `userTypes()` - @DataProvider: Returns array of UserType enums
- `credentialData()` - @DataProvider: Returns test credentials (valid/invalid)

---

### `utils.TestUsers`
**Purpose:** Provides test user credentials based on user type.

**Methods:**
- `getUser(UserType type)` - Get User object for specified user type

**User Types Supported:**
- NEW_USER
- PROGRAM_USER
- SUBSCRIPTION_USER
- LAUNCHPAD_USER
- PROGRAM_SUBSCRIPTION_USER

---

### `utils.User`
**Purpose:** Model class representing a user with credentials.

**Methods:**
- `User(String email, String password)` - Constructor
- `getEmail()` - Get user email
- `getPassword()` - Get user password

---

### `utils.UserType`
**Purpose:** Enum defining different user types.

**Values:**
- `NEW_USER`
- `PROGRAM_USER`
- `SUBSCRIPTION_USER`
- `LAUNCHPAD_USER`
- `PROGRAM_SUBSCRIPTION_USER`

---

### `utils.CredentialsType`
**Purpose:** Enum for credential validation types.

**Values:**
- `VALID`
- `INVALID_EMAIL`
- `INVALID_PASSWORD`

---

## Locator Classes

### `locators.LoginPageLocators`
**Purpose:** XPath locators for login page elements.

**Constants:**
- `TAP_TO_START` - Tap to Start image view
- `ACCESS_YOUR_PAID_PROGRAM_BUTTON` - Access Your Paid Program button
- `SAW_AD` - Saw an advertisement option
- `CONTINUE` - Continue button
- `EDIT_TEXT` - Edit text field
- `SIGN_IN` - Sign in button
- `LOGIN_WITH_PASSWORD` - Login with Password button
- `SUCCESS_ACTIVITY_STREAK` - Activity Streak success indicator
- `INVALID_EMAIL_ERROR` - Invalid email error message
- `INVALID_PASSWORD_ERROR` - Invalid password error message

---

### `locators.LogoutPageLocators`
**Purpose:** XPath locators for logout page elements.

**Constants:**
- `PROFILE_TAB` - Profile tab icon
- `LOGOUT_BUTTON` - Logout button
- `CONFIRM_LOGOUT_BUTTON` - Confirm logout button
- `LOGOUT_STATE_CARD` - Logout state card
- `LOGIN_OPTION` - Login option in bottom sheet
- `ONBOARDING_SCREEN` - Onboarding screen element

---

### `locators.HomeTabLocators`
**Purpose:** XPath locators for Home tab elements.

**Constants:**
- `SUBSCRIBE_BUTTON` - Subscribe button
- `NOTIFICATION_ICON` - Notification icon
- `MEMBERSHIP_ICON` - Membership icon
- `EXPERT_SESSIONS_TEXT` - Expert Sessions text
- `EXPERT_SESSIONS_VIDEOS` - Expert Sessions videos container
- `VIEW_ALL` - View all button
- `ACTIVITY_STREAK` - Activity Streak element
- `HOW_IT_WORKS` - How it works card
- `THIS_WEEK_ACTIVITIES` - This Week's Activities text
- `SUBSCRIPTION_ACTIVITY_CARD` - Subscription activity card
- `CLAIM_MEDALS` - Claim medals element
- `NEW_FOR_YOU` - New for you section
- `YOUR_ACTIVITY_JOURNEY` - Your Activity Journey card
- `TALENT_CORNER_TEXT` - Talent Corner text
- `FEEDBACK_POPUP` - Feedback popup
- `JOIN_NOW` - Join Now button

---

### `locators.ProfileTabLocators`
**Purpose:** XPath locators for Profile tab elements.

**Constants:**
- `PROFILE_PAGE_VIEW` - Profile page view
- `STAR_TRACKER` - Star Tracker element
- `PRODIGY_TRAILS` - Prodigy Trails element
- `MY_PROGRAMS` - My Programs element
- `SAVED_RESOURCES` - Saved Resources element
- `MY_REFERRALS` - My Referrals element
- `PROGRESS_SNAPSHOTS` - Progress Snapshots element
- `PROGRAM_PARENT_PROFILE_CARD` - Program parent profile card

---

### `locators.CommunityTabLocators`
**Purpose:** XPath locators for Community tab elements.

**Constants:**
- `COMMUNITY_TAB_ICON` - Community tab icon
- `OPEN_COMMUNITIES_TEXT` - Open Communities text
- `PARENT_STORIES_TEXT` - Parent Stories text
- `MY_COMMUNITIES_TEXT` - My Communities text
- `VIDEO_VIEW` - Video view (HorizontalScrollView)

---

### `locators.ProgramTabLocators`
**Purpose:** XPath locators for Program tab elements.

**Constants:**
- `PROGRAM_TAB_ICON` - Program tab icon
- `EXPLORE_ALL_PROGRAMS` - Explore All Programs button
- `START_YOUR_JOURNEY` - Start Your Journey text
- `TODAYS_PLAN` - Today's Plan element
- `PROGRAM_CONTENT_SCROLL` - Program content scroll view

---

## Page Object Classes

### `pages.LoginPage`
**Purpose:** Page Object for login flow handling all 5 screens.

**Methods:**

**Screen 1: Tap to Start**
- `clickTapToStart()` - Click Tap to Start
- `isTapStartVisible()` - Check if Tap to Start is visible

**Screen 2: Continue Button**
- `clickSecondScreenButton()` - Click Access Your Paid Program button
- `isContinueButtonVisible()` - Check if continue button is visible

**Screen 3: Advertisement Selection**
- `selectAdvertisement()` - Select advertisement option
- `clickContinue()` - Click Continue button
- `isAdvertisementOptionsVisible()` - Check if advertisement options are visible

**Screen 4: Email Entry**
- `enterEmail(String email)` - Enter email address
- `clickSignIn()` - Click Sign in button
- `isEmailFieldVisible()` - Check if email field is visible

**Screen 5: Password Entry**
- `clickLoginWithPassword()` - Click Login with Password button
- `enterPassword(String password)` - Enter password
- `clickFinalSignIn()` - Click final Sign in button
- `isPasswordFieldVisible()` - Check if password field is visible

**Complete Login Flow**
- `performLogin(String email, String password)` - Complete login flow

**Validation Methods**
- `validateSuccessfulLogin()` - Validate successful login
- `verifyInvalidEmailError()` - Verify invalid email error
- `verifyInvalidPasswordError()` - Verify invalid password error

**Screen Verification**
- `verifyScreen1UI()` - Verify Screen 1 UI
- `verifyScreen2UI()` - Verify Screen 2 UI

**Navigation Methods**
- `clickNext()` - Click next button
- `clickLogin()` - Click login button

**Reusable Login Method**
- `login(String email, String password)` - Reusable login method

**Helper Methods**
- `waitForElementToBeClickable(By locator)` - Private wait method
- `waitForElementToBeVisible(By locator)` - Private wait method
- `checkAppStability()` - Private stability check

---

### `pages.LogoutPage`
**Purpose:** Page Object for logout flow.

**Methods:**
- `clickProfileTab()` - Click on Profile tab
- `isLogoutButtonVisible()` - Check if logout button is visible (with scrolling)
- `clickLogoutButton()` - Click logout button
- `confirmLogout()` - Confirm logout action
- `isLogoutStateCardVisible()` - Check if logout state card is visible
- `clickLogoutStateCard()` - Click on logout state card
- `clickLoginOption()` - Click Login option in bottom sheet
- `isOnboardingScreenVisible()` - Check if onboarding screen is visible

---

### `pages.HomeTabPage`
**Purpose:** Page Object for Home tab functionality.

**Methods:**

**Visibility Checks**
- `isSubscribebuttonVisible()` - Check if Subscribe button is visible
- `isNotificationIconVisible()` - Check if notification icon is visible
- `isMembershipIconVisible()` - Check if membership icon is visible
- `isExpertSessionTextVisible()` - Check if Expert Sessions text is visible
- `isExpertSessionVideosVisible()` - Check if Expert Sessions videos are visible
- `isExpertSessionViewAllBtnVisible()` - Check if View All button is visible
- `isActivityStreakVisible()` - Check if Activity Streak is visible
- `isHowItWorksCardVisible()` - Check if How it works card is visible
- `isThisWeekActivityTextVisible()` - Check if This Week's Activities text is visible
- `isSubscriptionActivityCardVisible()` - Check if Subscription Activity Card is visible
- `isProgramCardVisible()` - Check if Program Card is visible
- `isClaimMedalsVisible()` - Check if Claim Medals is visible
- `NewForYouText()` - Check if New for you text is visible
- `isYourActivityJourneyCardVisible()` - Check if Your Activity Journey card is visible
- `isTalentCornerTextVisible()` - Check if Talent Corner text is visible
- `isTalentCornerVideosVisible()` - Check if Talent Corner videos are visible
- `isFeedbackPopupVisible()` - Check if feedback popup is visible
- `isJoinNowBoxVisible()` - Check if Join Now box is visible

**Verification Methods**
- `verifyHomePageLoaded()` - Verify home page is loaded
- `getActivityStreakText()` - Get Activity Streak text

**Popup Handling**
- `handleHelpBottomSheet()` - Handle help bottom sheet popup
- `handleAllPopups()` - Handle all popups (sales popup, help bottom sheet, generic popups)

**UI Validation**
- `validateHomeTabUI(UserType userType)` - Validate Home Tab UI based on user type

---

### `pages.ProfileTabPage`
**Purpose:** Page Object for Profile tab functionality.

**Methods:**
- `clickProfileTab()` - Click on Profile tab
- `verifyProfileLoaded()` - Verify profile tab is loaded
- `isProfilePageViewVisible()` - Check if Profile Page View is visible
- `isStarTrackerVisible()` - Check if Star Tracker is visible
- `isProdigyTrailsVisible()` - Check if Prodigy Trails is visible
- `isMyProgramsVisible()` - Check if My Programs is visible
- `isSavedResourcesVisible()` - Check if Saved Resources is visible
- `isMyReferralsVisible()` - Check if My Referrals is visible
- `isProgressSnapshotsVisible()` - Check if Progress Snapshots is visible
- `isProgramParentProfileCardVisible()` - Check if Program Parent Profile Card is visible
- `validateProfileTabUI(UserType userType)` - Validate Profile Tab UI based on user type

---

### `pages.CommunityTabPage`
**Purpose:** Page Object for Community tab functionality.

**Methods:**
- `clickCommunityTab()` - Click on Community tab
- `openCommunityTab()` - Open Community tab (legacy method name)
- `verifyCommunityLoaded()` - Verify community tab is loaded
- `isOpenCommunitiesTextVisible()` - Check if Open Communities text is visible
- `isParentStoriesTextVisible()` - Check if Parent Stories text is visible
- `isVideoViewVisible()` - Check if Video View is visible
- `isMyCommunitiesTextVisible()` - Check if My Communities text is visible
- `validateCommunityTabUI(UserType userType)` - Validate Community Tab UI based on user type

---

### `pages.ProgramTabPage`
**Purpose:** Page Object for Program tab functionality.

**Methods:**
- `clickProgramTab()` - Click on Program tab
- `clickExploreAllPrograms()` - Click "Explore All Programs" button
- `isExploreAllProgramsVisible()` - Check if Explore All Programs button is visible
- `isStartYourJourneyVisible()` - Check if Start Your Journey text is visible
- `isTodaysPlanVisible()` - Check if today's plan is visible
- `isProgramContentVisible()` - Check if program content is visible
- `verifyProgramTabLoaded()` - Verify program tab is loaded
- `validateProgramTabUI(UserType userType)` - Validate Program Tab UI based on user type

---

### `pages.DaybookPage`
**Purpose:** Placeholder Page Object for daybook/journal features.

**Methods:**
- `verifyUIElements()` - TODO: Add assertions for daybook UI
- `performCoreActions()` - TODO: Add core actions like add journal entry

---

### `pages.MedalPage`
**Purpose:** Placeholder Page Object for medals/achievements.

**Methods:**
- `verifyUIElements()` - TODO: Add assertions for medals UI
- `performCoreActions()` - TODO: Add core actions like claim/view medals

---

### `pages.StreakPage`
**Purpose:** Placeholder Page Object for streak features.

**Methods:**
- `verifyUIElements()` - TODO: Add assertions for streak UI
- `performCoreActions()` - TODO: Add core actions like view streak details

---

### `pages.SubscriptionPage`
**Purpose:** Placeholder Page Object for purchase/subscription flows.

**Methods:**
- `verifyUIElements()` - TODO: Add assertions for subscription UI
- `performCoreActions()` - TODO: Add core actions like select plan, proceed to pay

---

### `pages.RegisterPage`
**Purpose:** Placeholder Page Object for registration flow.

**Methods:**
- (No methods currently implemented)

---

## Test Classes

### Regression Tests (`tests.regression`)

#### `tests.regression.AuthFlowTest`
**Purpose:** Tests authentication flow with valid and invalid credentials.

**Test Methods:**
- `loginInputDomain(CredentialsType type, String email, String password)` - @Test with @DataProvider
  - Validates login with valid and invalid credentials
  - Uses `credentialData` data provider
  - Verifies successful login or error messages

---

#### `tests.regression.HomeTabTest`
**Purpose:** Focused test for Home Tab UI validation.

**Test Methods:**
- `validateHomeTab(UserType userType)` - @Test with @DataProvider
  - Validates Home Tab UI for each user type
  - Uses `userTypes` data provider
  - Handles popups and validates user-specific elements

---

#### `tests.regression.ProfileTabTest`
**Purpose:** Focused test for Profile Tab UI validation.

**Test Methods:**
- `validateProfileTab(UserType userType)` - @Test with @DataProvider
  - Validates Profile Tab UI for each user type
  - Uses `userTypes` data provider
  - Validates user-specific profile elements

---

#### `tests.regression.CommunityTabTest`
**Purpose:** Focused test for Community Tab UI validation.

**Test Methods:**
- `validateCommunityTab(UserType userType)` - @Test with @DataProvider
  - Validates Community Tab UI for each user type
  - Uses `userTypes` data provider
  - Validates user-specific community elements

---

#### `tests.regression.ProgramTabTest`
**Purpose:** Focused test for Program Tab UI validation.

**Test Methods:**
- `validateProgramTab(UserType userType)` - @Test with @DataProvider
  - Validates Program Tab UI for each user type
  - Uses `userTypes` data provider
  - Validates user-specific program elements

---

### Smoke Tests (`tests.smoke`)

#### `tests.smoke.SmokeTest`
**Purpose:** Basic smoke test for app launch and login.

**Test Methods:**
- `appLaunchAndLogin()` - @Test
  - Basic launch and login smoke check
  - Verifies home page loads after login

---

#### `tests.smoke.BrowserStackSimpleTest`
**Purpose:** Simple test to verify app launch and basic element interaction.

**Test Methods:**
- `simpleAndroidTest()` - @Test
  - Simple test to verify app launch
  - Checks if elements are accessible

---

### Sanity Tests (`tests.sanity`)

#### `tests.sanity.SanityTest`
**Purpose:** Quick post-deploy health check.

**Test Methods:**
- `quickHealth()` - @Test
  - Quick health check after deployment
  - Verifies login, home tab, and program tab loading

---

## Configuration Files

### `config.properties`
**Location:** `src/main/resources/config.properties`

**Properties:**
- `runMode` - Run mode: local or browserstack
- `appium.server.url` - Local Appium server URL
- `platformName` - Platform name (Android)
- `deviceName` - Device name
- `platformVersion` - Platform version
- `appPackage` - Application package name
- `appActivity` - Main activity name
- `ENV` - Environment: staging or prod
- `STAGING_APP` - Path to staging APK
- `PROD_APP` - Path to production APK

**Usage:**
- System property override: `mvn test -Denv=prod`
- Default: Uses staging environment

---

### `browserstack.yml`
**Location:** Project root

**Configuration:**
- `userName` - BrowserStack username
- `accessKey` - BrowserStack access key
- `projectName` - Project name
- `buildName` - Build name
- `testObservability` - Enable/disable test observability
- `browserstackAutomation` - Enable/disable BrowserStack automation
- `platforms` - Platform configuration (device, OS version, app)
- `appiumVersion` - Appium version
- `parallelsPerPlatform` - Parallel executions per platform

---

### `logback.xml`
**Location:** `src/main/resources/logback.xml`

**Purpose:** Logging configuration using Logback framework.

---

## TestNG Suites

### TestNG XML Files (in `src/test/resources/`)

1. **testng.xml** - Main test suite configuration
2. **regression-testng.xml** - Regression test suite
3. **login-testng.xml** - Login-specific tests
4. **logout-testng.xml** - Logout-specific tests
5. **home-testng.xml** - Home tab tests
6. **program-tab-testng.xml** - Program tab tests
7. **community-tab-testng.xml** - Community tab tests
8. **all-tests-testng.xml** - All tests suite

### TestNG Suite Files (in `testng/`)

1. **regressionSuite.xml** - Regression suite
2. **sanitySuite.xml** - Sanity suite
3. **smokeSuite.xml** - Smoke suite

---

## Execution Commands

### Run All Tests
```bash
mvn test
```

### Run with Production Environment
```bash
mvn test -Denv=prod
```

### Run Specific TestNG Suite
```bash
mvn test -DsuiteXmlFile=src/test/resources/regression-testng.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=AuthFlowTest
```

---

## Key Features

1. **Environment-Based Configuration**
   - Supports staging and production environments
   - System property override support
   - Dynamic APK selection based on environment

2. **BrowserStack Integration**
   - Cloud-based test execution
   - Test observability
   - Video recording and network logs

3. **Page Object Model**
   - Clean separation of locators and page logic
   - Reusable page methods
   - User type-based validation

4. **Data-Driven Testing**
   - TestNG DataProviders
   - Multiple user types support
   - Credential validation testing

5. **Comprehensive Test Coverage**
   - Regression tests
   - Smoke tests
   - Sanity tests
   - Tab-specific validation tests

---

## User Types

The framework supports testing with 5 different user types:

1. **NEW_USER** - New user without any programs or subscriptions
2. **PROGRAM_USER** - User with active program
3. **SUBSCRIPTION_USER** - User with active subscription
4. **LAUNCHPAD_USER** - Launchpad user
5. **PROGRAM_SUBSCRIPTION_USER** - User with both program and subscription

Each user type has different UI elements and features available, which are validated in the tests.

---

## Notes

- All tests extend `BaseTest` which handles driver lifecycle
- Page Objects extend `BasePage` for common functionality
- Locators are centralized in locator classes
- Environment configuration supports staging/prod via system properties
- BrowserStack is the primary execution platform
- Local execution is disabled (BrowserStack only)

---

**Document Generated:** January 2025  
**Framework Version:** 1.0.0-SNAPSHOT  
**Last Updated:** Comprehensive documentation covering all packages, classes, and methods


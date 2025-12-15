package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.BrowserStackOptions;
import utils.ConfigReader;
import utils.RunMode;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private static AppiumDriver sharedDriver;

    public static AppiumDriver getDriver() {
        return sharedDriver;
    }

    /**
     * Creates driver - Supports both Local and BrowserStack
     * Mode is determined by runMode in config.properties
     */
    public static AppiumDriver createDriver() {
        try {
            if (RunMode.isLocal()) {
                System.out.println("🖥️  Running on LOCAL emulator");
                System.out.println("📱 Creating local Appium driver...");
                return createLocalDriver();
            } else if (RunMode.isBrowserStack()) {
                System.out.println("🌐 Running on BROWSERSTACK");
                System.out.println("🌐 Creating BrowserStack driver...");
                System.out.println("🔗 Using BrowserStack hub: https://hub.browserstack.com/wd/hub");
                return createBrowserStackDriver();
            } else {
                throw new RuntimeException("Invalid runMode. Must be 'local' or 'browserstack'. Current: " + RunMode.getRunMode());
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to create driver: " + e.getMessage());
            e.printStackTrace();
            sharedDriver = null;
        }
        return sharedDriver;
    }
    
    /**
     * Creates local Appium driver
     * Uses local Appium server and local APK file
     */
    private static AppiumDriver createLocalDriver() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        // Get configuration from config.properties
        String appiumServerUrl = ConfigReader.get("appium.server.url", "http://127.0.0.1:4723/wd/hub");
        String platformName = ConfigReader.get("platformName", "Android");
        String deviceName = ConfigReader.get("deviceName", "Android Emulator");
        String platformVersion = ConfigReader.get("platformVersion", "11");
        String appPackage = ConfigReader.get("appPackage", "com.raising.prodigy");
        String appActivity = ConfigReader.get("appActivity", "com.raising.prodigy.MainActivity");
        
        // Get app environment from BaseTest
        String appEnvironment = BaseTest.getAppEnvironment();
        
        // Set app path based on environment
        String appPath;
        if (appEnvironment.equalsIgnoreCase("prod")) {
            appPath = ConfigReader.get("PROD_APP");
            System.out.println("🏭 Using Production APK (Local)");
        } else {
            appPath = ConfigReader.get("STAGING_APP");
            System.out.println("🧪 Using Staging APK (Local)");
        }
        
        // Validate APK file exists
        if (appPath == null || appPath.isEmpty()) {
            throw new RuntimeException("APK path not configured. Set STAGING_APP or PROD_APP in config.properties");
        }
        
        File appFile = new File(appPath);
        if (!appFile.exists()) {
            throw new RuntimeException("APK file not found at: " + appPath);
        }
        
        // Set local Appium capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("app", appFile.getAbsolutePath());
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("autoGrantPermissions", true);
        
        System.out.println("📱 Device: " + deviceName);
        System.out.println("📦 App: " + appFile.getAbsolutePath());
        System.out.println("🌍 Environment: " + appEnvironment);
        System.out.println("🔗 Appium Server: " + appiumServerUrl);
        
        // Create AndroidDriver for local Appium server
        try {
            URL appiumUrl = new URL(appiumServerUrl);
            sharedDriver = new AndroidDriver(appiumUrl, capabilities);
            sharedDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            System.out.println("✅ Session created on local emulator");
            return sharedDriver;
        } catch (Exception e) {
            System.err.println("❌ Local Appium session creation failed: " + e.getMessage());
            System.err.println("   Make sure Appium server is running at: " + appiumServerUrl);
            if (e.getCause() != null) {
                System.err.println("   Cause: " + e.getCause().getMessage());
            }
            throw e;
        }
    }
    
    /**
     * Creates BrowserStack driver
     * Uses BrowserStack hub URL and DesiredCapabilities (NOT local Appium)
     */
    private static AppiumDriver createBrowserStackDriver() throws Exception {
        // Reduce BrowserStack logs
        System.setProperty("webdriver.remote.sessionid.enabled", "false");
        
        DesiredCapabilities capabilities = BrowserStackOptions.getCapabilities();
        URL hubUrl = BrowserStackOptions.getHubUrl();
        
        // Get app environment from BaseTest
        String appEnvironment = BaseTest.getAppEnvironment();
        
        // Set BrowserStack app ID based on environment
        String appId = BaseTest.getBrowserStackAppId();
        capabilities.setCapability("app", appId);
        if (appEnvironment.equalsIgnoreCase("prod")) {
            System.out.println("🏭 Using Production APK (BrowserStack)");
        } else {
            System.out.println("🧪 Using Staging APK (BrowserStack)");
        }
        
        System.out.println("📱 Device: " + capabilities.getCapability("device"));
        System.out.println("📦 App: " + capabilities.getCapability("app"));
        System.out.println("🌍 Environment: " + appEnvironment);
        
        // Create AndroidDriver for BrowserStack using BrowserStack hub
        // NOT local Appium server (http://127.0.0.1:4723)
        try {
            sharedDriver = new AndroidDriver(hubUrl, capabilities);
            sharedDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            System.out.println("✅ Session created on BrowserStack device");
            return sharedDriver;
        } catch (Exception e) {
            System.err.println("❌ BrowserStack session creation failed: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("   Cause: " + e.getCause().getMessage());
            }
            throw e;
        }
    }

    public static void setDriver(AppiumDriver driver) {
        sharedDriver = driver;
    }

    public static boolean isAppActive(AppiumDriver driver) {
        try {
            return driver != null && driver.getPageSource() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static void restartApp(AppiumDriver driver) {
        if (driver != null) {
            try {
                if (driver instanceof AndroidDriver) {
                    AndroidDriver android = (AndroidDriver) driver;
                    try { android.terminateApp("com.raising.prodigy"); } catch (Exception ignore) {}
                    android.activateApp("com.raising.prodigy");
                }
            } catch (Exception e) {
            }
        }
    }

    public static void closeDriver() {
        if (sharedDriver != null) {
            try {
                sharedDriver.quit();
            } catch (Exception e) {
            } finally {
                sharedDriver = null;
            }
        }
    }
    
    /**
     * Check if running on BrowserStack
     * @return true if runMode is "browserstack"
     */
    public static boolean isBrowserStack() {
        return RunMode.isBrowserStack();
    }
    
    /**
     * Check if running on local emulator
     * @return true if runMode is "local"
     */
    public static boolean isLocal() {
        return RunMode.isLocal();
    }
}



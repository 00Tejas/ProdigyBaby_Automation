package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private static AppiumDriver sharedDriver;

    public static AppiumDriver getDriver() {
        return sharedDriver;
    }

    public static AppiumDriver createDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                .setPlatformVersion("16")
                .setAppPackage("com.raising.prodigy")
                .setAppActivity("com.raising.prodigy.MainActivity")
                .setAutomationName("UiAutomator2")
                .setNoReset(false);
            options.amend("autoGrantPermissions", true);

            URL server = new URL("http://127.0.0.1:4723/wd/hub");
            sharedDriver = new AndroidDriver(server, options);
            sharedDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        } catch (Exception e) {
            sharedDriver = null;
        }
        return sharedDriver;
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
}



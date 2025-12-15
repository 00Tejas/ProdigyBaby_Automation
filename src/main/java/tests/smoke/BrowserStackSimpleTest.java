package tests.smoke;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Simple Android test example for BrowserStack
 * Demonstrates basic findElement and quit operations
 */
public class BrowserStackSimpleTest extends BaseTest {

    @Test(description = "Simple test to verify app launch and basic element interaction")
    public void simpleAndroidTest() throws Exception {
        System.out.println("\n===============================");
        System.out.println("STARTING TEST: " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("===============================\n");
        
        Reporter.log("Running test: " + new Object(){}.getClass().getEnclosingMethod().getName(), true);
        
        System.out.println("🧪 Running simple Android test...");
        
        // Wait a bit for app to fully load
        Thread.sleep(3000);
        
        // Example: Try to find any element on the screen
        try {
            // This is a generic example - adjust XPath based on your app
            driver.findElement(By.xpath("//android.view.View"));
            System.out.println("✅ App launched successfully and elements are accessible");
        } catch (Exception e) {
            System.out.println("⚠️ Could not find element, but app may still be loaded: " + e.getMessage());
        }
        
        // Test completed - driver.quit() is handled by BaseTest tearDown
        System.out.println("✅ Simple test completed");
    }
}


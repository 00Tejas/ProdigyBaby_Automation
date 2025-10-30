package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

public class BaseTest {
    protected AppiumDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws Exception {
        System.out.println("🧩 Starting test: " + method.getName());
        AppiumDriver current = DriverFactory.getDriver();
        if (current == null) {
            current = DriverFactory.createDriver();
        }
        this.driver = current;
        if (!DriverFactory.isAppActive(driver)) {
            System.out.println("⚠️ App was not active, restarting...");
            DriverFactory.restartApp(driver);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("❌ Test failed: " + result.getThrowable());
        }
        DriverFactory.closeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void resetAppAfterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("🔁 Resetting app after failure...");
            DriverFactory.restartApp(driver);
        }
    }

    public void setupTest() throws Exception {
        AppiumDriver current = DriverFactory.getDriver();
        if (current == null) {
            current = DriverFactory.createDriver();
        }
        this.driver = current;
    }

    public void setupTestWithReset() throws Exception {
        resetAppData();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        setupTest();
    }

    public void cleanupTest() {
        DriverFactory.closeDriver();
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void resetAppData() {
        try {
            ProcessBuilder stopPb = new ProcessBuilder("adb", "shell", "am", "force-stop", "com.raising.prodigy");
            Process stopProcess = stopPb.start();
            stopProcess.waitFor();
            Thread.sleep(3000);

            ProcessBuilder clearPb = new ProcessBuilder("adb", "shell", "pm", "clear", "com.raising.prodigy");
            Process clearProcess = clearPb.start();
            clearProcess.waitFor();
            Thread.sleep(4000);

            ProcessBuilder resetPb = new ProcessBuilder("adb", "shell", "pm", "reset-permissions", "com.raising.prodigy");
            Process resetProcess = resetPb.start();
            resetProcess.waitFor();
            Thread.sleep(3000);

            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    public void waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
        }
    }

    public void waitForElementToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
        }
    }

    public void checkAppStability() {
        try {
            driver.getCurrentUrl();
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//*"));
            } catch (Exception e2) {
            }
        }
    }
}

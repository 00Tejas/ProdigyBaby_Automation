package utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * BrowserStack Options Utility
 * Handles BrowserStack configuration and capabilities setup
 * Credentials are read from browserstack.yml only - never logged or displayed
 */
public class BrowserStackOptions {
    private static final String BROWSERSTACK_YML = "browserstack.yml";
    private static Map<String, Object> config;
    
    static {
        loadConfig();
    }
    
    private static void loadConfig() {
        try {
            Yaml yaml = new Yaml();
            // Try project root first
            File ymlFile = new File(BROWSERSTACK_YML);
            if (!ymlFile.exists()) {
                // Try in current directory
                ymlFile = new File(System.getProperty("user.dir"), BROWSERSTACK_YML);
            }
            if (ymlFile.exists()) {
                FileInputStream inputStream = new FileInputStream(ymlFile);
                config = yaml.load(inputStream);
                inputStream.close();
            } else {
                System.err.println("⚠️ browserstack.yml not found. BrowserStack mode will not work.");
                config = null;
            }
        } catch (Exception e) {
            System.err.println("⚠️ Failed to load browserstack.yml: " + e.getMessage());
            config = null;
        }
    }
    
    /**
     * Get BrowserStack Hub URL
     * Credentials are embedded in URL but never logged
     * @return BrowserStack Hub URL
     */
    public static URL getHubUrl() {
        try {
            if (config == null) {
                throw new RuntimeException("BrowserStack configuration not loaded. Check browserstack.yml file.");
            }
            String userName = (String) config.get("userName");
            String accessKey = (String) config.get("accessKey");
            return new URL("https://" + userName + ":" + accessKey + "@hub.browserstack.com/wd/hub");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create BrowserStack Hub URL", e);
        }
    }
    
    /**
     * Get BrowserStack Capabilities
     * Only BrowserStack-specific capabilities - NO local config references
     * @return DesiredCapabilities configured for BrowserStack
     */
    @SuppressWarnings("unchecked")
    public static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        if (config == null) {
            throw new RuntimeException("BrowserStack configuration not loaded. Check browserstack.yml file.");
        }
        
        // Set credentials (from browserstack.yml - never logged)
        String userName = (String) config.get("userName");
        String accessKey = (String) config.get("accessKey");
        capabilities.setCapability("browserstack.user", userName);
        capabilities.setCapability("browserstack.key", accessKey);
        
        // Set project and build info
        capabilities.setCapability("project", config.get("projectName"));
        capabilities.setCapability("build", config.get("buildName"));
        
        // Set platform details from browserstack.yml ONLY
        if (config.containsKey("platforms")) {
            List<Map<String, Object>> platforms = (List<Map<String, Object>>) config.get("platforms");
            if (platforms != null && !platforms.isEmpty()) {
                Map<String, Object> platform = platforms.get(0);
                String deviceName = (String) platform.get("deviceName");
                String osVersion = (String) platform.get("osVersion");
                
                // BrowserStack requires both deviceName and device
                capabilities.setCapability("deviceName", deviceName);
                capabilities.setCapability("device", deviceName);
                capabilities.setCapability("os_version", osVersion);
                
                // App capability is now set in DriverFactory based on ENV configuration
                // No longer reading from browserstack.yml
            }
        }
        
        // Android specific capabilities (BrowserStack format)
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "com.raising.prodigy");
        capabilities.setCapability("appActivity", "com.raising.prodigy.MainActivity");
        
        // Set test observability
        if (config.containsKey("testObservability") && Boolean.TRUE.equals(config.get("testObservability"))) {
            capabilities.setCapability("browserstack.testObservability", true);
        }
        
        // Enable video recording (like demo test)
        capabilities.setCapability("browserstack.video", true);
        
        // Enable network logs for debugging
        capabilities.setCapability("browserstack.networkLogs", true);
        
        // Reduce BrowserStack logs - only errors
        capabilities.setCapability("browserstack.debug", false);
        capabilities.setCapability("browserstack.consoleLogs", "errors");
        
        return capabilities;
    }
    
    /**
     * Check if BrowserStack automation is enabled
     * @return true if browserstackAutomation is true in browserstack.yml
     */
    public static boolean getBrowserstackAutomation() {
        if (config == null) {
            return false;
        }
        return Boolean.TRUE.equals(config.get("browserstackAutomation"));
    }
}


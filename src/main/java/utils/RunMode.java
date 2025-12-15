package utils;

/**
 * RunMode Utility
 * Handles run mode configuration (local or browserstack)
 */
public class RunMode {
    private static final String RUN_MODE_KEY = "runMode";
    
    /**
     * Get current run mode from config
     * @return "local" or "browserstack" (defaults to "browserstack" if not set)
     */
    public static String getRunMode() {
        String runMode = ConfigReader.get(RUN_MODE_KEY, "browserstack");
        return runMode != null ? runMode.toLowerCase().trim() : "browserstack";
    }
    
    /**
     * Check if running on BrowserStack
     * @return true if runMode is "browserstack"
     */
    public static boolean isBrowserStack() {
        return "browserstack".equals(getRunMode());
    }
    
    /**
     * Check if running on local emulator
     * @return true if runMode is "local"
     */
    public static boolean isLocal() {
        return "local".equals(getRunMode());
    }
}


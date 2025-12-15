package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
        }
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get property value with system property override support
     * System property takes precedence over config.properties
     * Checks both lowercase and uppercase system property keys
     * @param key Property key
     * @return Property value from system property if set, otherwise from config.properties
     */
    public static String get(String key) {
        // Check system property first (e.g., -Denv=prod or -DENV=prod)
        String systemValue = System.getProperty(key.toLowerCase());
        if (systemValue == null || systemValue.isEmpty()) {
            systemValue = System.getProperty(key.toUpperCase());
        }
        if (systemValue != null && !systemValue.isEmpty()) {
            return systemValue;
        }
        // Fall back to config.properties
        return properties.getProperty(key);
    }
}



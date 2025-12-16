package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * EmailGenerator - Generates unique emails for test automation
 * Uses a persistent counter stored in emailCounter.txt
 */
public class EmailGenerator {
    private static final String COUNTER_FILE_PATH = "src/test/resources/emailCounter.txt";
    private static final String EMAIL_PATTERN = "automationpurchase%d@p.baby";
    private static final Object lock = new Object(); // For thread safety
    
    /**
     * Get a unique email address with auto-incrementing counter
     * @return Unique email in format: automationpurchase{counter}@p.baby
     */
    public static String getUniqueEmail() {
        synchronized (lock) {
            File counterFile = resolveCounterFile();
            int counter = readCounter(counterFile);
            String email = String.format(EMAIL_PATTERN, counter);
            writeCounter(counterFile, counter + 1);
            return email;
        }
    }
    
    /**
     * Resolve the counter file path consistently
     * @return File object pointing to the counter file
     */
    private static File resolveCounterFile() {
        // Try to get file from project root
        File counterFile = new File(COUNTER_FILE_PATH);
        
        // If not found, try relative to current directory
        if (!counterFile.exists()) {
            String currentDir = System.getProperty("user.dir");
            counterFile = new File(currentDir, COUNTER_FILE_PATH);
        }
        
        // If still not found, try absolute path from resources
        if (!counterFile.exists()) {
            Path resourcePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "emailCounter.txt");
            counterFile = resourcePath.toFile();
        }
        
        // If file doesn't exist, ensure we use the standard location for creation
        if (!counterFile.exists()) {
            Path resourcePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources");
            counterFile = new File(resourcePath.toFile(), "emailCounter.txt");
        }
        
        return counterFile;
    }
    
    /**
     * Read the current counter value from file
     * @param counterFile The file to read from
     * @return Current counter value (defaults to 1 if file doesn't exist)
     */
    private static int readCounter(File counterFile) {
        try {
            if (counterFile.exists() && counterFile.length() > 0) {
                try (FileReader reader = new FileReader(counterFile)) {
                    StringBuilder content = new StringBuilder();
                    int ch;
                    while ((ch = reader.read()) != -1) {
                        content.append((char) ch);
                    }
                    String counterStr = content.toString().trim();
                    if (!counterStr.isEmpty()) {
                        int counter = Integer.parseInt(counterStr);
                        System.out.println("📧 Read email counter: " + counter);
                        return counter;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not read email counter file, starting from 1: " + e.getMessage());
        }
        
        // Default to 1 if file doesn't exist or can't be read
        return 1;
    }
    
    /**
     * Write the counter value to file
     * @param counterFile The file to write to
     * @param counter Counter value to write
     */
    private static void writeCounter(File counterFile, int counter) {
        try {
            // Ensure parent directory exists
            if (counterFile.getParentFile() != null && !counterFile.getParentFile().exists()) {
                counterFile.getParentFile().mkdirs();
            }
            
            // Write counter to file
            try (FileWriter writer = new FileWriter(counterFile, false)) {
                writer.write(String.valueOf(counter));
                writer.flush();
                System.out.println("📧 Updated email counter to: " + counter);
            }
        } catch (IOException e) {
            System.err.println("❌ Failed to write email counter file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


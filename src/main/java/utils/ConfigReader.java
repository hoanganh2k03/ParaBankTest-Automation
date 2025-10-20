package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            // Load secrets first (base credentials)
            FileInputStream secretFis = new FileInputStream("src/main/resources/secrets.properties");
            properties.load(secretFis);

            // Then load config (may override keys)
            FileInputStream configFis = new FileInputStream("src/main/resources/config.properties");
            properties.load(configFis);

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load configuration files: " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("⚠️ Missing key in properties: " + key);
        }
        return value.trim();
    }

}
 

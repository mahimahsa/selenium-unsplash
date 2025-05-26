package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (input != null) props.load(input);
            else throw new RuntimeException("test.properties not found");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load test.properties", ex);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}

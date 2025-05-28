package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String HOME = ConfigReader.get("base.url");
    public static final String LOGIN = ConfigReader.get("login.url");
    public static final String ACCOUNT = ConfigReader.get("account.url");
    public static final String SEARCH = ConfigReader.get("search.url");
    public static final String EMAIL = ConfigReader.get("valid.email");
    public static final String PASSWORD = ConfigReader.get("valid.password");
    public static final String COOKIE_CONSENT = ConfigReader.get("cookie.consent");
    public static final String COOKIE_TOKEN = ConfigReader.get("cookie.user.token");

}

 class ConfigReader {
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


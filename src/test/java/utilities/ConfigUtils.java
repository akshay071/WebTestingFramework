package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private static Properties properties = new Properties();

    // Load properties from the config file
    static {
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/properties/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the property value by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}


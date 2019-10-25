package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties getProperties() {

        Properties properties = new Properties();

        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream("dao.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static String getUrl() {
        return getProperties().getProperty("url");
    }

    public static String getDriverClass() {
        return getProperties().getProperty("driver_class");
    }

    public static String getUser() {
        return getProperties().getProperty("username");
    }

    public static String getPassword() {
        return getProperties().getProperty("password");
    }
}
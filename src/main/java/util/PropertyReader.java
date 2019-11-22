package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader propertyReader;
    private static Properties properties;

    private PropertyReader() { }

    public static PropertyReader getInstance() {

        if (propertyReader == null) {
            propertyReader = new PropertyReader();

            if (properties == null) {

                properties = new Properties();

                try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream("dao.properties")) {
                    properties.load(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propertyReader;
    }

    public Properties getProperties() {

        return properties;
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }

    public String getUrl() {
        return getProperty("url");
    }

    public String getDriverClass() {
        return getProperty("driver_class");
    }

    public String getUsername() {
        return getProperty("username");
    }

    public String getPassword() {
        return getProperty("password");
    }
}
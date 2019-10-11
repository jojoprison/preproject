package util;

import factory.UserDaoFactory;
import factory.UserDaoFactoryHibernate;
import factory.UserDaoFactoryJDBC;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO PropertyReader
public class DaoProperties {

    static Properties getProperties() {

        Properties properties = new Properties();

        try (InputStream inputStream = DaoProperties.class.getClassLoader().getResourceAsStream("dao.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static UserDaoFactory getUserDaoFactory() {

        String dbName = getProperties().getProperty("db_name");
        UserDaoFactory factory = null;

        switch (dbName.toLowerCase()) {
            case "jdbc":
                factory = UserDaoFactoryJDBC.getInstance();
                break;
            case "hibernate":
                factory = UserDaoFactoryHibernate.getInstance();
                break;
        }

        return factory;
    }
}
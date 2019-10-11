package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static DBHelper dbHelper;
    private Properties properties;
    private Configuration configuration;
    private Connection connection;

    private DBHelper() { }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Connection getConnection() {

        if (connection == null) {

            try {

                properties = DaoProperties.getProperties();
                DriverManager.registerDriver((Driver) Class.forName(properties.getProperty("driver_class")).newInstance());

                // TODO использовать существующие методы для установок конфигурации
                String url = properties.getProperty("url") +
                        "?user=" +
                        properties.getProperty("username") +
                        "&password=" +
                        properties.getProperty("password");

                connection = DriverManager.getConnection(url);

            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new IllegalStateException();
            }
        }

        return connection;
    }

    public Configuration getConfiguration() {

        properties = DaoProperties.getProperties();

        if (configuration == null) {

            configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);

            configuration.setProperty("hibernate.dialect", properties.getProperty("dialect"));
            configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("driver_class"));
            configuration.setProperty("hibernate.connection.url", properties.getProperty("url"));
            configuration.setProperty("hibernate.connection.username", properties.getProperty("username"));
            configuration.setProperty("hibernate.connection.password", properties.getProperty("password"));
            configuration.setProperty("hibernate.show_sql", properties.getProperty("show_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hbm2ddl.auto"));
        }

        return configuration;
    }
}
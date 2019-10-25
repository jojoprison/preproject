package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static DBHelper dbHelper;
    private Configuration configuration;
    private Connection connection;
    private SessionFactory sessionFactory;

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

                DriverManager.registerDriver((Driver) Class.forName(PropertyReader.getDriverClass()).newInstance());

                String url = PropertyReader.getUrl();

                Properties properties = new Properties();
                properties.setProperty("user", PropertyReader.getUser());
                properties.setProperty("password",PropertyReader.getPassword());

                connection = DriverManager.getConnection(url, properties);

            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new IllegalStateException();
            }
        }

        return connection;
    }

    public Configuration getConfiguration() {

        Properties properties = PropertyReader.getProperties();

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

    public SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            Configuration configuration = getConfiguration();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            sessionFactory =  configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
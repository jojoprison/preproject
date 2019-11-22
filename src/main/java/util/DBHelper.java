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

                PropertyReader propertyReader = PropertyReader.getInstance();

                DriverManager.registerDriver((Driver) Class.forName(propertyReader.getDriverClass()).newInstance());

                String url = propertyReader.getUrl();

                Properties properties = new Properties();
                properties.setProperty("user", propertyReader.getUsername());
                properties.setProperty("password",propertyReader.getPassword());

                connection = DriverManager.getConnection(url, properties);

            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new IllegalStateException();
            }
        }

        return connection;
    }

    public Configuration getConfiguration() {

        PropertyReader propertyReader = PropertyReader.getInstance();

        if (configuration == null) {

            configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);

            configuration.setProperty("hibernate.dialect", propertyReader.getProperty("dialect"));
            configuration.setProperty("hibernate.connection.driver_class", propertyReader.getDriverClass());
            configuration.setProperty("hibernate.connection.url", propertyReader.getUrl());
            configuration.setProperty("hibernate.connection.username", propertyReader.getUsername());
            configuration.setProperty("hibernate.connection.password", propertyReader.getPassword());
            configuration.setProperty("hibernate.show_sql", propertyReader.getProperty("show_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", propertyReader.getProperty("hbm2ddl.auto"));
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
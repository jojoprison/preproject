package factory;

import DAO.UserDao;
import DAO.UserDaoHibernateImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

public class UserDaoFactoryHibernate implements UserDaoFactory {

    private static UserDaoFactoryHibernate userDaoFactoryHibernate;
    private SessionFactory sessionFactory;

    private UserDaoFactoryHibernate() { }

    public static UserDaoFactoryHibernate getInstance() {
        if (userDaoFactoryHibernate == null) {
            userDaoFactoryHibernate = new UserDaoFactoryHibernate();
        }
        return userDaoFactoryHibernate;
    }

    @Override
    public UserDao getUserDao() {

        return new UserDaoHibernateImpl(getSessionFactory());
    }

    // TODO Засунуть в класс с конфигурациями или в DBHelper
    private SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            Configuration configuration = DBHelper.getInstance().getConfiguration();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            sessionFactory =  configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
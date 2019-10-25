package factory;

import DAO.UserDao;
import DAO.UserDaoHibernateImpl;
import DAO.UserDaoJDBCimpl;
import util.DBHelper;
import util.PropertyReader;

public class UserDaoFactory {

    public static UserDao getUserDao() {

        String dbName = PropertyReader.getProperties().getProperty("db_name");
        UserDao userDao = null;

        switch (dbName.toLowerCase()) {
            case "jdbc":
                userDao = new UserDaoJDBCimpl(DBHelper.getInstance().getConnection());
                break;
            case "hibernate":
                userDao = new UserDaoHibernateImpl(DBHelper.getInstance().getSessionFactory());
                break;
        }

        return userDao;
    }
}
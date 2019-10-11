package factory;

import DAO.UserDao;
import DAO.UserDaoJDBCimpl;
import util.DBHelper;

public class UserDaoFactoryJDBC implements UserDaoFactory {

    private static UserDaoFactoryJDBC userDaoFactoryJDBC;

    private UserDaoFactoryJDBC() { }

    public static UserDaoFactoryJDBC getInstance() {
        if (userDaoFactoryJDBC == null) {
            userDaoFactoryJDBC = new UserDaoFactoryJDBC();
        }
        return userDaoFactoryJDBC;
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoJDBCimpl(DBHelper.getInstance().getConnection());
    }
}
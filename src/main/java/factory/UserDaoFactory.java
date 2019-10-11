package factory;

import DAO.UserDao;

// TODO сделать общую фарбику
public interface UserDaoFactory {
    UserDao getUserDao();
}
package service;

import DAO.UserDao;
import factory.UserDaoFactory;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements Service {

    private static UserService userService;
    private static UserDao userDao;

    // Закрываем конструктор по умолчанию
    private UserService() { }

    public static UserService getInstance() {

        if (userService == null) {
            userService = new UserService();
        }

        userDao = UserDaoFactory.getUserDao();

        return userService;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByEmail(String email) throws SQLException {

        try {
            return userDao.getByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean add(User user) {

        String email = user.getEmail();

        try {
            if (userDao.getByEmail(email) == null) {
                return userDao.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(long id) {

        try {
            return userDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean validate(String email, String password) {

        return userDao.validate(email, password);
    }

    @Override
    public void createTable() {
        userDao.createTable();
    }

    @Override
    public void dropTable() {
        userDao.dropTable();
    }
}
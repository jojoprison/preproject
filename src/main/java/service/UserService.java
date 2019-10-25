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
    public User get(long id) {
        return userDao.get(id);
    }

    @Override
    public User get(String email) throws SQLException {

        try {
            return userDao.get(email);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean add(User user) {

        String email = user.getEmail();

        try {
            if (userDao.get(email) == null) {
                return userDao.add(email, user.getPassword(), user.getName(), user.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getAge());
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
    public void createTable() {
        userDao.createTable();
    }

    @Override
    public void dropTable() {
        userDao.dropTable();
    }
}
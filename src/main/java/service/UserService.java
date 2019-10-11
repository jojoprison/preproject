package service;

import DAO.UserDao;
import model.User;
import util.DaoProperties;

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

        userDao = DaoProperties.getUserDaoFactory().getUserDao();

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
    public User get(String email) {
        return userDao.get(email);
    }

    @Override
    public boolean add(User user) {

        String email = user.getEmail();

        if (userDao.get(email) == null) {
            return userDao.add(email, user.getPassword(), user.getName(), user.getAge());
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        // TODO Можно сделать проверки с помощью user.equals(), если понадобится
        return userDao.update(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getAge());
    }

    @Override
    public boolean delete(long id) {
        return userDao.delete(id);
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
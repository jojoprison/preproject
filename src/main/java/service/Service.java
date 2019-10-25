package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {

    List<User> getAll();

    User get(long id);

    User get(String email) throws SQLException;

    boolean add(User user);

    boolean update(User user);

    boolean delete(long id);

    void createTable();

    void dropTable();
}

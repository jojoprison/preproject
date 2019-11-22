package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {

    List<User> getAll();

    User getById(long id);

    User getByEmail(String email) throws SQLException;

    boolean add(User user);

    boolean update(User user);

    boolean delete(long id);

    boolean validate(String email,  String password);

    void createTable();

    void dropTable();
}

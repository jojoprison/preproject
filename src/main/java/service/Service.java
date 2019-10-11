package service;

import model.User;

import java.util.List;

public interface Service {

    List<User> getAll();

    User get(long id);

    User get(String email);

    boolean add(User user);

    boolean update(User user);

    boolean delete(long id);

    void createTable();

    void dropTable();
}

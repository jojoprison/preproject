package DAO;

import model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User get(long id);

    User get(String email);

    boolean add(String email, String password, String name, int age);

    boolean update(long id, String email, String password, String name, int age);

    boolean delete(long id);

    void createTable();

    void dropTable();
}
package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAll();

    User get(long id);

    User get(String email) throws SQLException;

    boolean add(String email, String password, String name, int age);

    boolean update(long id, String email, String password, String name, int age);

    boolean delete(long id);

    void createTable();

    void dropTable();
}
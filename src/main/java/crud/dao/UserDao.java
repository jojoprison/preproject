package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getById(long id);

    User getByEmail(String email);

    void add(User user);

    void update(User user);

    void delete(User user);

    boolean validate(String email, String password);
}
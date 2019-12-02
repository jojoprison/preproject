package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getById(long id);

    User getByEmail(String email);

    void add(User user);

    void update(User user);

    void delete(User user);

    boolean validate(String email, String password);
}

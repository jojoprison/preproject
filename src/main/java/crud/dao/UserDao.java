package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {

    User getById(long id);

    User getBySSO(String SSO);

    List<User> getAll();

    void add(User user);

    void deleteBySSO(String SSO);
}
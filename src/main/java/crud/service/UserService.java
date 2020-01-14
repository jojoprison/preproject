package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {

    User getById(long id);

    User getBySSO(String sso);

    List<User> getAll();

    void add(User user);

    void update(User user);

    void deleteBySSO(String sso);

    boolean isSSOUnique(Long id, String sso);
}

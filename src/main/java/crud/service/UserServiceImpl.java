package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getById(long id) {
        return dao.getById(id);
    }

    @Override
    public User getBySSO(String SSO) {
        return dao.getBySSO(SSO);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void add(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        dao.add(user);
    }

    @Override
    public void update(User user) {

        User dbUser = dao.getById(user.getId());

        if (dbUser != null) {

            dbUser.setSsoId(user.getSsoId());

            if (!user.getPassword().equals(dbUser.getPassword())) {
                dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            dbUser.setEmail(user.getEmail());
            dbUser.setName(user.getName());
            dbUser.setAge(user.getAge());
            dbUser.setUserProfiles(user.getUserProfiles());
        }
    }

    @Override
    public void deleteBySSO(String SSO) {
        dao.deleteBySSO(SSO);
    }

    @Override
    public boolean isSSOUnique(Long id, String SSO) {

        User user = getBySSO(SSO);

        return (user == null || ((user.getId().equals(id))));
    }
}
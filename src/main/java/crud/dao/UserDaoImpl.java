package crud.dao;

import crud.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getById(long id) {

        User user = getByKey(id);

        if (user != null) {
            // lazy loading collection of types in other table
            Hibernate.initialize(user.getUserProfiles());
        }

        return user;
    }

    // TODO Доделать
    @Override
    public User getBySSO(String SSO) {

        logger.info("SSO : {}", SSO);

        Query<User> query = getSession().createQuery("FROM User WHERE ssoId = :SSO", User.class);
        query.setParameter("SSO", SSO);

        User user = query.uniqueResult();

        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }

        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {

        List<User> users = getSession().createQuery("FROM User user ORDER BY user.email").list();

        // we need to eagerly fetching of User Profiles to display on list page
        for (User user : users) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return users;
    }

    @Override
    public void add(User user) {
        persist(user);
    }

    @Override
    public void deleteBySSO(String SSO) {

        Query<User> query = getSession().createQuery("FROM User WHERE ssoId = :SSO", User.class);
        query.setParameter("SSO", SSO);

        delete(query.uniqueResult());
    }
}
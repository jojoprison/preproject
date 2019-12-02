package crud.dao;

import crud.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM User").list();
    }

    @Override
    public User getById(long id) {

        Session session = sessionFactory.getCurrentSession();

        return session.get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);

        return (User) query.uniqueResult();
    }

    @Override
    public void add(User user) {

        Session session = sessionFactory.getCurrentSession();

        session.persist(user);
    }

    @Override
    public void update(User user) {

        Session session = sessionFactory.getCurrentSession();

        session.update(user);
    }

    @Override
    public void delete(User user) {

        Session session = sessionFactory.getCurrentSession();

        session.delete(user);
    }

    @Override
    public boolean validate(String email, String password) {

        User user = getByEmail(email);

        boolean isValidated = false;

        if (user != null && user.getPassword().equals(password)) {
            isValidated = true;
        }

        return isValidated;
    }
}
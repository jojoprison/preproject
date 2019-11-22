package DAO;

import model.User;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {

        Session session = sessionFactory.openSession();

        List<User> users = session.createQuery("FROM User").list();

        session.close();

        return users;
    }

    @Override
    public User getById(long id) {

        Session session = sessionFactory.openSession();

        User user = (User) session.get(User.class, id);

        session.close();

        return user;
    }

    @Override
    public User getByEmail(String email) {

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);

        User user = (User) query.uniqueResult();

        session.close();

        return user;
    }

    @Override
    public boolean add(User user) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }  finally {
            session.close();
        }

        return true;
    }

    @Override
    public boolean update(User user) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }  finally {
            session.close();
        }

        return true;
    }

    @Override
    public boolean delete(long id) {

        Session session = sessionFactory.openSession();
        User user = (User) session.load(User.class, id);

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }  finally {
            session.close();
        }

        return true;
    }

    @Override
    public boolean validate(String email, String password) {
        User user = getByEmail(email);

        boolean isValidated = false;

        if (user.getPassword().equals(password)) {
            isValidated = true;
        }

        return isValidated;
    }

    // Задано в properties
    @Override
    public void createTable() { }

    // Задано в properties
    @Override
    public void dropTable() { }
}
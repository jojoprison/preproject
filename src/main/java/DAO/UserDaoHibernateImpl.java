package DAO;

import model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

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
    public User get(long id) {

        Session session = sessionFactory.openSession();

        User user = (User) session.get(User.class, id);

        session.close();

        return user;
    }

    @Override
    public User get(String email) {

        Session session = sessionFactory.openSession();
        // TODO не использовать критерии
        Criteria criteria = session.createCriteria(User.class);

        User user = (User) criteria
                .add(Restrictions.eq("email", email))
                .uniqueResult();

        session.close();

        return user;
    }

    @Override
    public boolean add(String email, String password, String name, int age) {

        User newUser = new User(email, password, name, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newUser);

        // TODO сделать try-блок с rollback
        transaction.commit();
        boolean isAdded = transaction.wasCommitted();
        session.close();

        return isAdded;
    }

    @Override
    public boolean update(long id, String email, String password, String name, int age) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = (User) session.load(User.class, id);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setAge(age);

        session.update(user);

        transaction.commit();
        boolean isUpdated = transaction.wasCommitted();
        session.close();

        return isUpdated;
    }

    // TODO пробрасывать исключение и обрабатывать в сервисе
    @Override
    public boolean delete(long id) {

        Session session = sessionFactory.openSession();
        User user = (User) session.load(User.class, id);
        Transaction transaction = session.beginTransaction();

        session.delete(user);

        transaction.commit();
        boolean isDeleted = transaction.wasCommitted();
        session.close();

        return isDeleted;
    }

    // Задано в properties
    @Override
    public void createTable() { }

    // Задано в properties
    @Override
    public void dropTable() { }
}
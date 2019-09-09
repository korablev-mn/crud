package dao.Impl;

import dao.UserDAO;
import model.User;
import org.hibernate.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDAOHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Collection<User> users = session.createQuery("FROM User").list();
        if (users == null) {
            session.close();
            return new ArrayList<User>();
        }
        session.close();
        return users;
    }

    @Override
    public boolean addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
        } catch (HibernateException e){
            session.close();
            return false;
        }
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
        } catch (HibernateException e){
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserFromID(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = getUserById(id);
            session.delete(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public boolean isUserExist(User user) {
        Session session = sessionFactory.openSession();
        User userParam = null;
        Query query = session.createQuery("SELECT o FROM User o WHERE login=:login AND password=:password");
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());
        try {
            userParam = (User) query.uniqueResult();
        } catch (NonUniqueResultException e){
            session.close();
            return true;
        }
        if (userParam != null) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT o FROM User o WHERE name=:name");
        query.setParameter("name", name);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User getUserByLoginAndPass(String login, String pass) {
        Session session = sessionFactory.openSession();
        User user = null;
        try {
            Query query = session.createQuery("FROM User o WHERE o.login=:login AND o.password=:password");
            query.setParameter("login", login);
            query.setParameter("password", pass);
            user = (User) query.uniqueResult();
        } catch (HibernateException e){
            session.close();
            return user;
        }
        session.close();
        return user;
    }
}
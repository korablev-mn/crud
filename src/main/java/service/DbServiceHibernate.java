package service;

import dao.Impl.UserDAOHibernateImpl;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.Collection;

public class DbServiceHibernate {

    private static DbServiceHibernate dbServiceHibernate;
    private SessionFactory sessionFactory;

    public DbServiceHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DbServiceHibernate getInstance() {
        if (dbServiceHibernate == null) {
            dbServiceHibernate = new DbServiceHibernate(DBHelper.getSessionFactory());
        }
        return dbServiceHibernate;
    }

    public Collection<User> getAllUser() {
        return new UserDAOHibernateImpl(sessionFactory.openSession()).getAllUsers();
    }

    public boolean addUser(User user) {
        new UserDAOHibernateImpl(sessionFactory.openSession()).addUser(user);
        if (new UserDAOHibernateImpl(sessionFactory.openSession()).isUserExist(user.getName(), user.getPassword())) {
            return true;
        }
        return false;
    }

    public void updateUser(User user) {
        new UserDAOHibernateImpl(sessionFactory.openSession()).updateUser(user);
    }

    public void deleteUserFromID(Long id) {
        new UserDAOHibernateImpl(sessionFactory.openSession()).deleteUserFromID(id);
    }
}
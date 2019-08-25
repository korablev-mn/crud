package dao.Impl;

import dao.UserDAO;
import model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;

public class UserDAOHibernateImpl implements UserDAO {

    private Session session;

    public UserDAOHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public Collection<User> getAllUsers() {
        Collection<User> users = session.createQuery("FROM User").list();
        if (users == null) {
            return new ArrayList<>();
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserFromID(Long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM User WHERE id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public boolean isUserExist(String name, String password) {
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("name", name))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
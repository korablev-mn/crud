package dao.Impl;

import dao.UserDAO;
import model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.Collection;

public class UserDAOHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public UserDAOHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection<User> getAllUsers() {
        this.session = sessionFactory.openSession();
        Collection<User> users = session.createQuery("FROM User").list();
        if (users == null) {
            session.close();
            return new ArrayList<User>();
        }
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        this.session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        this.session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserFromID(Long id) {
        this.session = sessionFactory.openSession();
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
        this.session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("name", name))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        if (user != null) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }
}
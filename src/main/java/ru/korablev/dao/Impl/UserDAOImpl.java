package ru.korablev.dao.Impl;

import ru.korablev.dao.UserDAO;
import ru.korablev.model.Role;
import ru.korablev.model.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        List<User> users;
        //noinspection JpaQlInspection
        Query query = (Query) entityManager.createQuery("select u from User u");
        users = query.getResultList();
        return users;
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByLogin(String login) {
        //noinspection JpaQlInspection
        Query query = (Query) entityManager.createQuery("select u from User u where u.login = :login");
        query.setParameter("login", login);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public void createNewUser(String login, String name, String pass, Date date, Set<Role> roles) {
        entityManager.persist(new User(login, name, pass, date, roles));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public boolean deleteUserFromID(Long id) {
        try {
            User user = findUserById(id);
            entityManager.remove(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public boolean isUserExist(User user) {
        if (getUserByLoginAndPass(user.getLogin(), user.getPassword()) != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByName(String name) {
        //noinspection JpaQlInspection
        Query query = (Query) entityManager.createQuery("select u from User u where u.name = :name");
        query.setParameter("name", name);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public User getUserById(Long id) {
        //noinspection JpaQlInspection
        Query query = (Query) entityManager.createQuery("select u from User u where u.id = :id");
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public User getUserByLoginAndPass(String login, String pass) {
        //noinspection JpaQlInspection
        Query query = (Query) entityManager.createQuery("select u from User u where u.login = :login and u.password= :password ");
        query.setParameter("login", login);
        query.setParameter("password", pass);
        List<User> users = query.getResultList();
        return users.size() == 0 ? null : users.get(0);
    }
}
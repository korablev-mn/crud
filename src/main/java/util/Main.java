package util;

import dao.Impl.UserDAOHibernateImpl;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.internal.SessionFactoryImpl;
import service.DbServiceHibernate;

import javax.security.auth.login.Configuration;
import java.sql.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        DbServiceHibernate dbs = DbServiceHibernate.getInstance();
        try {
            User user = new User("user","user",Date.valueOf("1985-02-02"));
           // boolean userId = dbs.addUser(user);
            new UserDAOHibernateImpl(DBHelper.getSessionFactory().openSession()).addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

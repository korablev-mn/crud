package dao;

import dao.Impl.UserDAOHibernateImpl;
import dao.Impl.UserDAOJDBCImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;
import java.io.*;
import java.net.URL;
import java.util.Properties;

public class UserDaoFactory {

    private static UserDaoFactory userDaoFactory;
    Properties property = new Properties();
    UserDAO userDAO;

    public static UserDaoFactory getInstance() {
        if (userDaoFactory == null) {
            userDaoFactory = new UserDaoFactory();
        }
        return userDaoFactory;
    }

    private UserDaoFactory()  {
        try{
            URL url = getClass().getResource("/db.properties");
            property.load(new FileInputStream(url.getPath()));
            String type = property.getProperty("db.type");
            if(type.equals("jdbc")){
                userDAO = new UserDAOJDBCImpl(DBHelper.getConnection());
            } else if(type.equals("hibernate")) {
                userDAO = new UserDAOHibernateImpl(DBHelper.getSessionFactory().openSession());
            } else {
             throw new IOException("Ошибка подключения к базе!");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public UserDAO getUserDao(){
        return userDAO;
    }
}
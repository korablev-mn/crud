package util;

import com.mysql.jdbc.Connection;
import dao.Impl.UserDAOJDBCImpl;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static DBHelper dbHelper;
    private static SessionFactory sessionFactory;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
               // проблема в этой строчке // The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.
                // Исходный сервер не нашел текущего представления для целевого ресурса или не хочет раскрыть, что он существует.
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").                //db type
                    append("localhost:").                   //host name
                    append("3306/").                        //port
                    append(UserDAOJDBCImpl.DB + "?").      //db name
                    append("user=root&").                   //login
                    append("password=root");                //password

            Connection connection = (Connection) DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
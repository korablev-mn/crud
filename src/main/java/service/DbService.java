package service;

import dao.DbDAO;
import dao.Impl.DbDAOImpl;
import dao.Impl.UserDAOImpl;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbService {

    private static DbService dbService;

    private DbService() {
    }

    public static DbService getInstance() {
        if (dbService == null) {
            dbService = new DbService();
        }
        return dbService;
    }

    public void createTable() {
        DbDAO dao = getDbDAO();
        dao.createTable();
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").                //db type
                    append("localhost:").                   //host name
                    append("3306/").                        //port
                    append(UserDAOImpl.DB + "?").      //db name
                    append("user=root&").                   //login
                    append("password=root");                //password

            Connection connection = DriverManager.getConnection(url.toString());

            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static DbDAO getDbDAO() {
        return new DbDAOImpl(getMysqlConnection());
    }
}
package dao.Impl;

import com.mysql.jdbc.Connection;
import dao.UserDAO;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDAOJDBCImpl implements UserDAO{

    private Connection connection;
    public final static String DB = "dbuser";
    public final static String DB_TABLE = "user";

    public UserDAOJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<User> getAllUsers() {
       Collection<User> list = new ArrayList<User>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from " + DB + "." + DB_TABLE);
            ResultSet result = ((Statement) stmt).getResultSet();
            while (result.next()) {
                Long id = result.getLong("id");
                String login = result.getString("login");
                String name = result.getString("name");
                String password = result.getString("password");
                Date date = result.getDate("date");
                String role = result.getString("role");
                list.add(new User(id, login, name, password, date, role));
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addUser(User user) {

        try {
            PreparedStatement stm = connection.prepareStatement("insert into " + DB + "." + DB_TABLE + " (login, name, password, date, role) values (?, ?, ?, ?, ?)");
            stm.setString(1, user.getLogin());
            stm.setString(2, user.getName());
            stm.setString(3, user.getPassword());
            stm.setDate(4, user.getBirthday());
            stm.setString(5, user.getRole());
            stm.executeUpdate();
            stm.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("update " + DB + "." + DB_TABLE + " set login=?, name=?, password=?, date=?, role=? where id=?");
            stm.setString(1, user.getLogin());
            stm.setString(2, user.getName());
            stm.setString(3, user.getPassword());
            stm.setDate(4, user.getBirthday());
            stm.setString(5, user.getRole());
            stm.setLong(6, user.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserFromID(Long id) {
        try {
            PreparedStatement stm = connection.prepareStatement("delete from " + DB + "." + DB_TABLE + " where id=?");
            stm.setLong(1, id);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("delete from " + DB + "." + DB_TABLE + " where id=? AND login=?");
            stm.setLong(1, user.getId());
            stm.setString(2, user.getLogin());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isUserExist(User user) {
        String query = "select * from " + DB + "." + DB_TABLE + " where login=? and  password=?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, user.getLogin());
            stm.setString(2, user.getPassword());
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                result.close();
                stm.close();
                return true;
            }
            result.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByName(String name) {
        String query = "select * from " + DB + "." + DB_TABLE + " where name=?";
        User user = null;
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                user = new User(result.getLong("id"),result.getString("login"), name, result.getString("password"), result.getDate("date"), result.getString("role"));
            }
            result.close();
            stm.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        String query = "select * from " + DB + "." + DB_TABLE + " where id=?";
        User user = null;
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, String.valueOf(id));
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                user = new User(id,result.getString("login"), result.getString("name"), result.getString("password"), result.getDate("date"), result.getString("role"));
            }
            result.close();
            stm.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByLoginAndPass(String login, String pass) {
        return null;
    }
}
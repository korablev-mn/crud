package service;

import dao.Impl.UserDAOImpl;
import dao.UserDAO;
import model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public Collection<User> getAllUser() {
        Collection<User> list;
        list = getUserDAO().getAllUsers();
        return list;
    }

    public boolean addUser(User user) {
        getUserDAO().addUser(user);
        if (getUserDAO().isUserExist(user.getName(), user.getPassword())) {
            return true;
        }
        return false;
    }

    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public void deleteUserFromID(Long id) {
        getUserDAO().deleteUserFromID(id);
    }

    private static UserDAO getUserDAO() {
        return new UserDAOImpl(DbService.getMysqlConnection());
    }
}
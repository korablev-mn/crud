package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.Collection;

public class UserService {

    private static UserService userService;
    private static UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();

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
        if(getUserDAO().addUser(user)){
            if (getUserDAO().isUserExist(user)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isUserExist(User user) {
        return getUserDAO().isUserExist(user);
    }

    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public void deleteUserFromID(Long id) {
        User user = getUserDAO().getUserById(id);
        getUserDAO().deleteUser(user);
    }

    public User getUserByLoginAndPass(String login, String pass) {
        return getUserDAO().getUserByLoginAndPass(login, pass);
    }

    private static UserDAO getUserDAO() {
        return userDaoFactory.getUserDao();
    }


}
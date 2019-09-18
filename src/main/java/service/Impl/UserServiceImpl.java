package service.Impl;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) {
        return userDAO.getUserByLoginAndPass(login, pass);
    }

    @Override
    public List<User> findAllUser() {
        return userDAO.findAllUsers();
    }

    @Override
    public boolean isExistUser(User user) {
        return userDAO.isUserExist(user);
    }

    @Override
    public boolean updateUserById(User user) {
        userDAO.updateUser(user);
        return true;
    }
}
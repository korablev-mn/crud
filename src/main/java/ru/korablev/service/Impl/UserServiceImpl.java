package ru.korablev.service.Impl;

import org.springframework.transaction.annotation.Transactional;
import ru.korablev.dao.UserDAO;
import ru.korablev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.korablev.service.UserService;

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
    public boolean deleteUserFromID(Long id) {
        return userDAO.deleteUserFromID(id);
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
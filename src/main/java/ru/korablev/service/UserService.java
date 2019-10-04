package ru.korablev.service;

import ru.korablev.model.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    User getUserById(Long id);

    User getUserByLogin(String login);

    User getUserByLoginAndPassword(String login, String pass);

    List<User> findAllUser();

    boolean isExistUser(User user);

    void updateUserById(User user);

    boolean deleteUserFromID(Long id);
}
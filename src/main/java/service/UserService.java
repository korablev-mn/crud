package service;

import model.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    User getUserById(Long id);

    User getUserByLoginAndPassword(String login, String pass);

    List<User> findAllUser();

    boolean isExistUser(User user);

    boolean updateUserById(User user);

    boolean deleteUserFromID(Long id);
}
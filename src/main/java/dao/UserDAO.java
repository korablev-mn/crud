package dao;

import model.User;

import java.util.Collection;

public interface UserDAO {
    /**
     * method for receiving all users
     *
     * @return list
     */
    Collection<User> getAllUsers();

    /**
     * @param user
     */
    void addUser(User user);

    /**
     * @param user
     */
    void updateUser(User user);

    /**
     * @param id - id of user
     */
    void deleteUserFromID(Long id);

    /**
     * @param user
     */
    void deleteUser(User user);

    /**
     * @param name     - name of user
     * @param password - password of user
     * @return boolean
     */
    boolean isUserExist(String name, String password);

    /**
     * @param name     - name of user
     * @return User
     */
    User getUserByName(String name);

    /**
     * @param id - id of user
     * @return User
     */
    User getUserById(Long id);
}
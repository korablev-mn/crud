package dao;

import model.User;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserDAO  {
//extends CrudRepository<User, Long>
    void deleteUser(Long id);
    User findUserById(Long id);
    User findUserByLogin(String login);
    void createNewUser(String login, String pass, String name, Date date, String role);
    /**
     * method for receiving all users
     *
     * @return list
     */
    List<User> findAllUsers();

    /**
     * @param user
     * @return boolean
     */
    boolean addUser(User user);

    /**
     * @param user
     * @return
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
     * @param user
     * @return boolean
     */
    boolean isUserExist(User user);

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
    /**
     * @param login - login of user
     * @param pass - password of user
     * @return User
     */
    User getUserByLoginAndPass(String login, String pass);

}
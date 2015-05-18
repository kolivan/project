package base.daos;

import base.entities.User;

import java.util.List;

/**
 * Created by Vadim on 28.03.2015.
 */
public interface UserDAO {
    User createUser(String userName, String userPassword, String userEmail);
    void deleteUser(Long id);
    void updateUser(User userDetached);
    User getUserById(Long id);
    User getUserByName(String name);
    List<User> getUserNameLike(String name);
    List<User> getUsersByRole(Long roleId);
    List<User> getAllUsers();

}

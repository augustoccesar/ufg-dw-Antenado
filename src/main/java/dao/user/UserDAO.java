package dao.user;

import model.User;

/**
 * Created by augustoccesar on 7/27/16.
 */
public interface UserDAO {
    User login(String username, String passwordMD5);
    User save(User user);
}

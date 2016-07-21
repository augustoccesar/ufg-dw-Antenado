package mapper;

import model.Marker;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class UserMapper {
    public static User readResultSet(String prefix, ResultSet rs) throws SQLException {
        prefix = prefix == null ? "" : prefix + "_";

        User user = new User();
        if (checkIfColumnExists(prefix + "id", rs))
            user.setId(rs.getLong(prefix + "id"));
        if (checkIfColumnExists(prefix + "first_name", rs))
            user.setFirstName(rs.getString(prefix + "first_name"));
        if (checkIfColumnExists(prefix + "last_name", rs))
            user.setLastName(rs.getString(prefix + "last_name"));
        if (checkIfColumnExists(prefix + "username", rs))
            user.setUsername(rs.getString(prefix + "username"));
        if (checkIfColumnExists(prefix + "email", rs))
            user.setEmail(rs.getString(prefix + "email"));
        if (checkIfColumnExists(prefix + "password", rs))
            user.setPassword(rs.getString(prefix + "password"));
        if (checkIfColumnExists(prefix + "gender", rs))
            user.setGender(rs.getString(prefix + "password"));
        if (checkIfColumnExists(prefix + "created_at", rs))
            user.setCreatedAt(rs.getTimestamp(prefix + "created_at").getTime());
        if (checkIfColumnExists(prefix + "deleted_at", rs))
            user.setDeletedAt(rs.getTimestamp(prefix + "deleted_at") != null ? rs.getTimestamp(prefix + "deleted_at").getTime() : null);

        return user;
    }

    static boolean checkIfColumnExists(String column, ResultSet rs) {
        try {
            rs.findColumn(column);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}

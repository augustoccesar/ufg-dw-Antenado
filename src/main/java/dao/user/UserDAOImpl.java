package dao.user;

import config.database.DatabaseConfig;
import mapper.UserMapper;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by augustoccesar on 7/27/16.
 */
public class UserDAOImpl implements UserDAO {

    private DataSource dataSource;

    public UserDAOImpl() {
        this.dataSource = DatabaseConfig.getDataSource();
    }

    @Override
    public User login(String username, String passwordMD5) {
        String sql = "SELECT " +
                "   u.id AS u_id, " +
                "   u.first_name AS u_first_name, " +
                "   u.last_name AS u_last_name, " +
                "   u.username AS u_username, " +
                "   u.email AS u_email, " +
                "   u.password AS u_passord " +
                "FROM users u " +
                "WHERE " +
                "   u.username = ? AND u.password = ?";
        User user = null;
        try(Connection con = this.dataSource.getConnection()){
            try(PreparedStatement stmt = con.prepareStatement(sql)){
                stmt.setString(1, username);
                stmt.setString(2, passwordMD5);
                ResultSet rs = stmt.executeQuery();
                if(rs.next())
                    user = UserMapper.readResultSet("u", rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}

package dao.user;

import config.database.DatabaseConfig;
import mapper.UserMapper;
import model.User;

import javax.sql.DataSource;
import java.sql.*;

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

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, password, gender) VALUES (?,?,?,?,?,?)";
        try(Connection con = this.dataSource.getConnection()){
            try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getUsername());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPassword());
                stmt.setString(6, user.getGender());
                System.out.println("EXECUTING SQL: " + sql.replaceAll(" +", " "));
                if(stmt.executeUpdate() > 0){
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    user.setId(rs.getLong(1));
                    return user;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

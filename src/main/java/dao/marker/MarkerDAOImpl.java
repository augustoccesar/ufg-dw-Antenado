package dao.marker;

import config.database.DatabaseConfig;
import mapper.MarkerMapper;
import mapper.UserMapper;
import model.Marker;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class MarkerDAOImpl implements MarkerDAO {

    private DataSource dataSource;

    public MarkerDAOImpl() {
        this.dataSource = DatabaseConfig.getDataSource();
    }

    @Override
    public List<Marker> all() {
        ArrayList<Marker> markers = new ArrayList<>();
        String sql = "SELECT " +
                "   m.id AS m_id, " +
                "   m.title AS m_title, " +
                "   m.message AS m_message, " +
                "   m.priority AS m_priority, " +
                "   m.latitude AS m_latitude, " +
                "   m.longitude AS m_longitude, " +
                "   m.created_at AS m_created_at, " +
                "   u.id AS u_id, " +
                "   u.username AS u_username " +
                "FROM markers m " +
                "LEFT JOIN users u ON u.id = m.user_id " +
                "WHERE m.deleted_at IS NULL";

        try(Connection con = this.dataSource.getConnection()){
            try(PreparedStatement stmt = con.prepareStatement(sql)){
                System.out.println("EXECUTING SQL: " + sql.replaceAll(" +", " "));
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    Marker marker = MarkerMapper.readResultSet("m", rs);
                    marker.setUser(UserMapper.readResultSet("u", rs));
                    markers.add(marker);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return markers;
    }

    @Override
    public Marker save(Marker marker) {
        String sql = "INSERT INTO markers (user_id, title, message, priority, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = this.dataSource.getConnection()){
            try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                if(marker.getUser() != null) {
                    stmt.setLong(1, marker.getUser().getId());
                }else{
                    stmt.setNull(1, Types.NULL);
                }
                stmt.setString(2, marker.getTitle());
                stmt.setString(3, marker.getMessage());
                stmt.setInt(4, marker.getPriority());
                stmt.setDouble(5, marker.getLatitude());
                stmt.setDouble(6, marker.getLongitude());
                System.out.println("EXECUTING SQL: " + sql.replaceAll(" +", " "));
                if(stmt.executeUpdate() > 0){
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    marker.setId(rs.getLong(1));
                    return marker;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

package mapper;

import model.Marker;
import org.ocpsoft.prettytime.PrettyTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class MarkerMapper {
    public static Marker readResultSet(String prefix, ResultSet rs) throws SQLException {
        prefix = prefix == null ? "" : prefix + "_";

        Marker marker = new Marker();
        if (checkIfColumnExists(prefix + "id", rs))
            marker.setId(rs.getLong(prefix + "id"));
        if (checkIfColumnExists(prefix + "title", rs))
            marker.setTitle(rs.getString(prefix + "title"));
        if (checkIfColumnExists(prefix + "message", rs))
            marker.setMessage(rs.getString(prefix + "message"));
        if (checkIfColumnExists(prefix + "priority", rs))
            marker.setPriority(rs.getInt(prefix + "priority"));
        if (checkIfColumnExists(prefix + "latitude", rs))
            marker.setLatitude(rs.getDouble(prefix + "latitude"));
        if (checkIfColumnExists(prefix + "longitude", rs))
            marker.setLongitude(rs.getDouble(prefix + "longitude"));
        if (checkIfColumnExists(prefix + "created_at", rs)) {
            marker.setCreatedAt(rs.getTimestamp(prefix + "created_at").getTime());
            marker.setTimeAgo(new PrettyTime(new Locale("pt")).format(marker.getCreatedAtAsTimestamp()));
        }
        if (checkIfColumnExists(prefix + "deleted_at", rs))
            marker.setDeletedAt(rs.getTimestamp(prefix + "deleted_at") != null ? rs.getTimestamp(prefix + "deleted_at").getTime() : null);

        return marker;
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

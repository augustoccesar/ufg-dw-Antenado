package dao.marker;

import model.Marker;

import java.util.List;

/**
 * Created by augustoccesar on 7/4/16.
 */
public interface MarkerDAO {
    List<Marker> all();
    boolean userOwnMarker(Long markerId, Long userId);

    Marker save(Marker marker);
    boolean delete(Long markerId, Long userId);
}

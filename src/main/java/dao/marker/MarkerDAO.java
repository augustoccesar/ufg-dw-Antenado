package dao.marker;

import model.Marker;

import java.util.List;

/**
 * Created by augustoccesar on 7/4/16.
 */
public interface MarkerDAO {
    // Read
    List<Marker> all();

    // Write
    Marker save(Marker marker);
}

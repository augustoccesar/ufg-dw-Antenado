package controller;

import config.render.json.JsonEngine;
import dao.marker.MarkerDAO;
import dao.marker.MarkerDAOImpl;
import model.Marker;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class MarkerController {
    public MarkerController(String baseUrl) {
        MarkerDAO markerDAO = new MarkerDAOImpl();

        get(baseUrl, (req, res) -> new JsonEngine().render(markerDAO.all()));

        post(baseUrl, (req, res) -> {
            Marker marker = JsonEngine.fromJson(req.body(), Marker.class);
            return markerDAO.save(marker);
        }, new JsonEngine());
    }
}

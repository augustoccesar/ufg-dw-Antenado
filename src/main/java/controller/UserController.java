package controller;

import config.render.json.JsonEngine;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import model.User;

import java.util.HashMap;

import static spark.Spark.post;

/**
 * Created by augustoccesar on 7/27/16.
 */
public class UserController {
    public UserController(String baseUrl) {
        UserDAO userDAO = new UserDAOImpl();

        post(baseUrl + "/login", (req, res) -> {
            HashMap map = JsonEngine.fromJson(req.body(), HashMap.class);
            return userDAO.login(map.get("username").toString(), map.get("password").toString());
        }, new JsonEngine());

        post(baseUrl, (req, res) -> {
            User user = JsonEngine.fromJson(req.body(), User.class);
            return userDAO.save(user);
        }, new JsonEngine());
    }
}

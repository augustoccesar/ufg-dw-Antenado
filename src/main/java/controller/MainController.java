package controller;

import config.render.jade.JadeTemplateEngine;
import spark.ModelAndView;

import java.util.HashMap;

import static spark.Spark.get;

/**
 * Created by augustoccesar on 6/27/16.
 */
public class MainController {
    public MainController(String baseUrl) {
        get(baseUrl, (rq, rs) -> {
            rs.header("Content-Type", "text/html");
            return new JadeTemplateEngine().render(new ModelAndView(new HashMap<>(), "index"));
        });
    }
}

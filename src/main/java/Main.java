import controller.MainController;
import controller.MarkerController;

import static spark.Spark.before;
import static spark.Spark.staticFiles;

/**
 * Created by augustoccesar on 6/27/16.
 */
public class Main {
    public static void main(String[] args){
        staticFiles.location("/public");

        before((request, response) -> response.header("Content-Type", "application/json; charset=utf-8"));

        new MainController("/");
        new MarkerController("/markers");
    }
}

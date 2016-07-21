package config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.constants.Database;

import javax.sql.DataSource;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class DatabaseConfig {
    private static DataSource dataSource;

    private DatabaseConfig(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Database.JDBC_URL);
        config.setUsername(Database.JDBC_USERNAME);
        config.setPassword(Database.JDBC_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource(){
        if(dataSource == null)
            new DatabaseConfig();
        return dataSource;
    }
}

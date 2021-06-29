package ua.training.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import ua.training.model.dao.DBPropertyReader;

import javax.sql.DataSource;
import java.util.Properties;

public class ConnectionPoolHolder {


    private static volatile DataSource dataSource;
    private static final String CONNECTION_URL = "connection.url";
    private static final String USERNAME = "dbUser";
    private static final String PASSWORD = "dbPassword";
    private static final String DRIVER = "dbDriver";

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    Properties properties = DBPropertyReader.getProperties();
                    ds.setDriverClassName(properties.getProperty(DRIVER));
                    ds.setUrl(properties.getProperty(CONNECTION_URL));
                    ds.setUsername(properties.getProperty(USERNAME));
                    ds.setPassword(properties.getProperty(PASSWORD));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}

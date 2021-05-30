package ua.training.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private static final String PROPERTIES_PATH = "src/main/resources/dp.properties";

    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    Properties properties = new Properties();
                    try(InputStream in = Files.newInputStream(Paths.get(PROPERTIES_PATH))){
                        properties.load(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ds.setUrl(properties.getProperty("connection.url"));
                    ds.setUsername(properties.getProperty("dbUser"));
                    ds.setPassword(properties.getProperty("dbPassword"));
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

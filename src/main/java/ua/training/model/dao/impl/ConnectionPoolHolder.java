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
    private static final String PROPERTIES = "dp.properties";
    private static final String CONNECTION_URL = "connection.url";
    private static final String USERNAME = "dbUser";
    private static final String PASSWORD = "dbPassword";

    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    Properties properties = new Properties();
                    try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES)){
                        properties.load(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    public static void main(String[] args) {
        Properties properties = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get(PROPERTIES))){
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.getProperty("connection.url")+"?");
    }
}

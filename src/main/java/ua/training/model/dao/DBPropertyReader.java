package ua.training.model.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyReader {
    private static final Logger logger = LogManager.getLogger();
    private static final String PROPERTIES = "db/db.properties";

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES)) {
            properties.load(in);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return properties;
    }
}

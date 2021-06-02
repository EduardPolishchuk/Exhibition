package ua.training.model.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    Logger logger = LogManager.getLogger();

    boolean create (T entity);
    T findById(int id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(int id);
    void close();

    default  void rollback(Connection connection){
        if (connection != null){
            try {
                connection.rollback();
            }catch (SQLException e){
                logger.log(Level.ERROR, e.getMessage());
            }
        }
    }
}

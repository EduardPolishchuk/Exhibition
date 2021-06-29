package ua.training.model.dao;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    Logger logger = LogManager.getLogger(GenericDao.class);

    boolean create (T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    boolean update(T entity);
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

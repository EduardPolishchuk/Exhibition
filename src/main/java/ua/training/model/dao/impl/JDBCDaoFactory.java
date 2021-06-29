package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.ExhibitionDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(JDBCDaoFactory.class);
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public ExhibitionDao createExpositionDao() {
        return new JDBCExhibitionDao(getConnection());
    }
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }


}

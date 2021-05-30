package ua.training.model.dao.impl;

import ua.training.model.dao.ExpositionDao;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.dao.mapper.HallMapper;
import ua.training.model.dao.mapper.ObjectMapper;
import ua.training.model.entity.Exposition;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.enums.Hall;

public class JDBCExpositionDao implements ExpositionDao {

    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public JDBCExpositionDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(Exposition exposition) {
        try {
            connection.setAutoCommit(false);
            int genExpositionID;
            Map<Hall, Integer> halls = getHalls();
            PreparedStatement ps1 = connection.prepareCall("INSERT INTO exposition (theme,`date`) VALUES (?,?)");
            ps1.setString(1, exposition.getTheme());
            ps1.setDate(2, Date.valueOf(exposition.getDate()));
            ps1.executeUpdate();
            ResultSet resultSet = ps1.getGeneratedKeys();
            resultSet.next();
            genExpositionID = resultSet.getInt(1);
            for (Hall hall : exposition.getHalls()) {
                ps1 = connection.prepareCall("INSERT INTO exposition_has_hall (exposition_id, hall_id,`date`) VALUES (?,?,?)");
                ps1.setInt(1, genExpositionID);
                ps1.setInt(2, halls.get(hall));
                ps1.setDate(3, Date.valueOf(exposition.getDate()));
                ps1.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, ex.getMessage());
            rollback(connection);
            return false;
        } finally {
            close();
        }
        return true;
    }

    private Map<Hall, Integer> getHalls() {
        Map<Hall, Integer> halls = new HashMap<>();
        HallMapper mapper = new HallMapper();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM hall");
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                halls.put(mapper.extractFromResultSet(resultSet)
                        , resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return halls;
    }

    @Override
    public Exposition findById(int id) {
        return null;
    }


    @Override
    public List<Exposition> findAll() {
        return null;
    }

    @Override
    public void update(Exposition entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Exposition> findByName(String name) {

        Optional<Exposition> result = Optional.empty();
        try (PreparedStatement ps = connection.prepareCall("SELECT * FROM teacher WHERE name = ?")) {
            ps.setString(1, name);
            ResultSet rs;
            rs = ps.executeQuery();
            ExpositionMapper mapper = new ExpositionMapper();
            if (rs.next()) {
                result = Optional.of(mapper.extractFromResultSet(rs));
            }//TODO : ask question how avoid two teachers with the same name
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

}

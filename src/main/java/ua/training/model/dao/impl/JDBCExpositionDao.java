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
    private final Connection connection;

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

    public Map<Hall, Integer> getHalls() {
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
        List<Exposition> list = new ArrayList<>();
        ExpositionMapper expoMapper = new ExpositionMapper();
        HallMapper hallMapper = new HallMapper();
        Set<Hall> halls;
        try (
                PreparedStatement ps = connection.prepareCall("SELECT  hall.name FROM hall join exposition_has_hall on hall.id = exposition_has_hall.hall_id where exposition_id =?");
                Statement statement = connection.createStatement();
                ResultSet expoResultSet = statement.executeQuery("SELECT * FROM exposition")) {
            while (expoResultSet.next()) {
                Exposition ex = expoMapper.extractFromResultSet(expoResultSet);
                ps.setInt(1, ex.getId());
                ResultSet hallResultSet = ps.executeQuery();
                halls = new HashSet<>();
                while (hallResultSet.next()) {
                    halls.add(hallMapper.extractFromResultSet(hallResultSet));
                }
                ex.setHalls(halls);
                list.add(ex);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return list;
    }

    @Override
    public boolean update(Exposition exposition) {
        try {
            connection.setAutoCommit(false);
            Map<Hall, Integer> halls = getHalls();
            PreparedStatement ps1 = connection.prepareCall("INSERT INTO exposition (theme,`date`) VALUES (?,?)");
            ps1.setString(1, exposition.getTheme());
            ps1.setDate(2, Date.valueOf(exposition.getDate()));
            ps1.executeUpdate();
            ResultSet resultSet = ps1.getGeneratedKeys();
            resultSet.next();
            for (Hall hall : exposition.getHalls()) {
                ps1 = connection.prepareCall("INSERT INTO exposition_has_hall (exposition_id, hall_id,`date`) VALUES (?,?,?)");
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

    @Override
    public boolean delete(int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareCall("INSERT INTO canceled_exposition SELECT * FROM exposition WHERE id =?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareCall("DELETE FROM exposition WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            ps = connection.prepareCall("UPDATE exposition_has_hall SET date = null WHERE exposition_id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            rollback(connection);
            return false;
        }finally {
            close();
        }
        return true;
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

//        Optional<Exposition> result = Optional.empty();
//        try (PreparedStatement ps = connection.prepareCall("SELECT * FROM teacher WHERE name = ?")) {
//            ps.setString(1, name);
//            ResultSet rs;
//            rs = ps.executeQuery();
//            ExpositionMapper mapper = new ExpositionMapper();
//            if (rs.next()) {
//                result = Optional.of(mapper.extractFromResultSet(rs));
//            }//TODO : ask question how avoid two teachers with the same name
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
        return Optional.empty();
    }

}

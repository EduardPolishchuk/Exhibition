package ua.training.model.dao.impl;

import ua.training.model.dao.ExpositionDao;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.dao.mapper.HallMapper;
import ua.training.model.entity.Exhibition;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Hall;

public class JDBCExhibitionDao implements ExpositionDao {

    private static final Logger logger = LogManager.getLogger();
    private final Connection connection;

    public JDBCExhibitionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Exhibition exhibition) {
        try {
            connection.setAutoCommit(false);
            int genExpositionID;
            PreparedStatement ps1 = connection.prepareCall("INSERT INTO exposition (theme,`date`) VALUES (?,?)");
            ps1.setString(1, exhibition.getTheme());
            ps1.setDate(2, Date.valueOf(exhibition.getDate()));
            ps1.executeUpdate();
            ResultSet resultSet = ps1.getGeneratedKeys();
            resultSet.next();
            genExpositionID = resultSet.getInt(1);
            for (Hall hall : exhibition.getHalls()) {
                ps1 = connection.prepareCall("INSERT INTO exposition_has_hall (exposition_id, hall_id,`date`) VALUES (?,?,?)");
                ps1.setInt(1, genExpositionID);
                ps1.setInt(2, hall.getId());
                ps1.setDate(3, Date.valueOf(exhibition.getDate()));
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
    public Optional<Exhibition> findById(int id) {
        return Optional.empty();
    }


    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> list = new ArrayList<>();
        ExpositionMapper expoMapper = new ExpositionMapper();
        HallMapper hallMapper = new HallMapper();
        Set<Hall> halls;
        try (
                PreparedStatement ps = connection.prepareCall("SELECT  hall.name FROM hall join exposition_has_hall on hall.id = exposition_has_hall.hall_id where exposition_id =?");
                Statement statement = connection.createStatement();
                ResultSet expoResultSet = statement.executeQuery("SELECT * FROM exposition")) {
            while (expoResultSet.next()) {
                Exhibition ex = expoMapper.extractFromResultSet(expoResultSet);
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
        }finally {
            close();
        }
        return list;
    }

    @Override
    public boolean update(Exhibition exhibition) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareCall("UPDATE exposition, exposition_description SET date=?,theme=?,price=?,max_places=?,description=?," +
                    "image_url=? where exposition.id = exposition_description.exposition_id AND exposition_id=?");
            ps.setDate(1, Date.valueOf(exhibition.getDate()));
            ps.setString(2, exhibition.getTheme());
            ps.setInt(3, exhibition.getPrice());
            ps.setInt(4, exhibition.getMax());
            ps.setString(5, exhibition.getDescription());
            ps.setString(6, exhibition.getImageUrl());
            ps.setInt(7, exhibition.getId());
            ps.executeUpdate();
            ps = connection.prepareCall("DELETE FROM exposition_has_hall WHERE exposition_id=?");
            ps.setInt(1, exhibition.getId());
            ps.executeUpdate();
            for (Hall hall : exhibition.getHalls()) {
                ps = connection.prepareCall("INSERT INTO exposition_has_hall (exposition_id, hall_id, date) VALUES (?,?,?)");
                ps.setInt(1, exhibition.getId());
                ps.setInt(2,hall.getId());
                ps.setDate(3,Date.valueOf(exhibition.getDate()));
                ps.executeUpdate();
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
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareCall("UPDATE exposition_has_hall SET date = null WHERE exposition_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            rollback(connection);
            return false;
        } finally {
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
    public Optional<Exhibition> findByTheme(String theme) {

        Optional<Exhibition> result = Optional.empty();
        try (PreparedStatement ps = connection.prepareCall("SELECT * FROM exposition WHERE theme = ?")) {
            ps.setString(1, theme);
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

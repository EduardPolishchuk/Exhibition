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
            PreparedStatement ps1 = connection.prepareCall("INSERT INTO exposition (theme,`date`,price,current_places,max_places) VALUES (?,?,?,?,?)");
            ps1.setString(1, exhibition.getTheme());
            ps1.setDate(2, Date.valueOf(exhibition.getDate()));
            ps1.setInt(3, exhibition.getPrice());
            ps1.setInt(4, exhibition.getCurrent());
            ps1.setInt(5, exhibition.getMax());
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
        try (
                Statement statement = connection.createStatement();
                ResultSet expoResultSet = statement.executeQuery("SELECT * FROM exposition LEFT JOIN exposition_description ed on exposition.id = ed.exposition_id")) {
            while (expoResultSet.next()) {
                Exhibition ex = expoMapper.extractFromResultSet(expoResultSet);
                ex.setHalls(getHalls(ex));
                list.add(ex);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return list;
    }

    public List<Exhibition> findFrom(String sortBy ,int start, int itemsPer) {
        List<Exhibition> list = new ArrayList<>();
        ExpositionMapper expoMapper = new ExpositionMapper();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM exposition LEFT JOIN exposition_description ed on exposition.id = ed.exposition_id ORDER BY ? LIMIT ? offset ?");
            ps.setString(1, sortBy.toLowerCase());
            ps.setInt(2, itemsPer);
            ps.setInt(3, start);
            ResultSet expoResultSet = ps.executeQuery();
            while (expoResultSet.next()) {
                Exhibition ex = expoMapper.extractFromResultSet(expoResultSet);
                ex.setHalls(getHalls(ex));
                list.add(ex);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
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
                ps.setInt(2, hall.getId());
                ps.setDate(3, Date.valueOf(exhibition.getDate()));
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

    public Optional<Map<Exhibition, Integer>> getUserExhibitions(User user) {
        ExpositionMapper expositionMapper = new ExpositionMapper();
        Map<Exhibition, Integer> map = new HashMap<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_has_exposition join exposition e on user_has_exposition.exposition_id = e.id join exposition_description ed on e.id = ed.exposition_id WHERE user_id=?");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exhibition ex = expositionMapper.extractFromResultSet(rs);
                ex.setHalls(getHalls(ex));
                map.put(ex, rs.getInt("tickets_count"));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return Optional.of(map);
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
    public List<Exhibition> findByTheme(String theme) {
        List<Exhibition> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareCall("SELECT * FROM exposition LEFT JOIN exposition_description ed on exposition.id = ed.exposition_id WHERE theme = ? or theme_uk=?")) {
            ps.setString(1, theme);
            ps.setString(2, theme);
            ResultSet rs;
            rs = ps.executeQuery();
            ExpositionMapper mapper = new ExpositionMapper();
            while (rs.next()) {
                Exhibition ex = mapper.extractFromResultSet(rs);
                ex.setHalls(getHalls(ex));
                result.add(ex);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    public int getRowsNumber() {
        int rows = 0;
        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT COUNT(1) from exposition")) {
            if (rs.next()) {
                rows = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return rows;
    }

    private Set<Hall> getHalls(Exhibition exhibition) {
        HallMapper hallMapper = new HallMapper();
        Set<Hall> halls = new HashSet<>();
        try {
            PreparedStatement ps = connection.prepareCall("SELECT  hall.name FROM hall join exposition_has_hall on hall.id = exposition_has_hall.hall_id where exposition_id =?");
            ps.setInt(1, exhibition.getId());
            ResultSet hallResultSet = ps.executeQuery();
            while (hallResultSet.next()) {
                halls.add(hallMapper.extractFromResultSet(hallResultSet));
            }
            exhibition.setHalls(halls);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return halls;
    }

}
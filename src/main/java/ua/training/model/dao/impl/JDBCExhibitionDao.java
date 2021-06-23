package ua.training.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.ExhibitionDao;
import ua.training.model.dao.mapper.ExhibitionMapper;
import ua.training.model.dao.mapper.HallMapper;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.Hall;
import ua.training.model.entity.User;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class JDBCExhibitionDao implements ExhibitionDao {

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
            PreparedStatement ps1 = connection.prepareCall("INSERT INTO exposition (theme,theme_uk,`date`,price,current_places,max_places,description,description_uk,image_url) VALUES (?,?,?,?,?,?,?,?,?)");
            int k = 1;
            ps1.setString(k++, exhibition.getTheme());
            ps1.setString(k++, exhibition.getThemeUk());
            ps1.setDate(k++, Date.valueOf(exhibition.getDate()));
            ps1.setInt(k++, exhibition.getPrice());
            ps1.setInt(k++, exhibition.getCurrent());
            ps1.setInt(k++, exhibition.getMax());
            ps1.setString(k++, exhibition.getDescription());
            ps1.setString(k++, exhibition.getDescriptionUk());
            ps1.setString(k, exhibition.getImageUrl());
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
        Exhibition exhibition = null;
        ExhibitionMapper expoMapper = new ExhibitionMapper();
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM exposition where id=?")) {
            ps.setInt(1, id);
            ResultSet expoResultSet = ps.executeQuery();
            if (expoResultSet.next()) {
                exhibition = expoMapper.extractFromResultSet(expoResultSet);
                exhibition.setHalls(getHalls(exhibition));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return Optional.ofNullable(exhibition);
    }


    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> list = new ArrayList<>();
        ExhibitionMapper expoMapper = new ExhibitionMapper();
        try (
                Statement statement = connection.createStatement();
                ResultSet expoResultSet = statement.executeQuery("SELECT * FROM exposition ")) {
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

    public List<Exhibition> findFrom(int sortBy, int start, int itemsPer) {
        List<Exhibition> list = new ArrayList<>();
        ExhibitionMapper expoMapper = new ExhibitionMapper();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM exposition ORDER BY ? LIMIT ? offset ?");
            ps.setInt(1, sortBy);
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
            PreparedStatement ps = connection.prepareStatement("UPDATE exposition SET date=?,theme=?,theme_uk=?,price=?,description=?,description_uk=?," +
                    "image_url=? where id=?");
            int k = 1;
            ps.setDate(k++, Date.valueOf(exhibition.getDate()));
            ps.setString(k++, exhibition.getTheme());
            ps.setString(k++, exhibition.getThemeUk());
            ps.setInt(k++, exhibition.getPrice());
            ps.setString(k++, exhibition.getDescription());
            ps.setString(k++, exhibition.getDescriptionUk());
            ps.setString(k++, exhibition.getImageUrl());
            ps.setInt(k, exhibition.getId());
            ps.executeUpdate();
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
//            PreparedStatement ps = connection.prepareCall("INSERT INTO canceled_exposition SELECT * FROM exposition WHERE id =?");
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            ps = connection.prepareCall("DELETE FROM exposition WHERE id=?");
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            ps = connection.prepareCall("UPDATE exposition_has_hall SET date = null WHERE exposition_id=?");
//            ps.setInt(1, id);
//            ps.executeUpdate();
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
        ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
        Map<Exhibition, Integer> map = new HashMap<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_has_exposition left join exposition e on user_has_exposition.exposition_id = e.id left join canceled_exposition ce on e.current_places = ce.current_places WHERE user_id=?");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exhibition ex = exhibitionMapper.extractFromResultSet(rs);
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
        try (PreparedStatement ps = connection.prepareCall("SELECT * FROM exposition WHERE theme = ? or theme_uk=?")) {
            ps.setString(1, theme);
            ps.setString(2, theme);
            ResultSet rs;
            rs = ps.executeQuery();
            ExhibitionMapper mapper = new ExhibitionMapper();
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
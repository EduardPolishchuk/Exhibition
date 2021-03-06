package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ExhibitionDao;
import ua.training.model.dao.DBPropertyReader;
import ua.training.model.dao.mapper.ExhibitionMapper;
import ua.training.model.dao.mapper.HallMapper;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.Hall;
import ua.training.model.entity.User;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class JDBCExhibitionDao implements ExhibitionDao {

    private static final String FIND_BY_ID = "findByIdExhibition";
    private static final String CREATE_EXHIBITION = "createExhibition";
    private static final String ADD_EXHIBITION_HALL = "addExhibitionHall";
    private static final String GET_EXHIBITION_HALLS = "getExhibitionHalls";
    private static final String GET_ROWS_NUMBER = "getRowsNumber";
    private static final String FIND_BY_THEME = "findByTheme";
    private static final String TICKETS_COUNT = "tickets_count";
    private static final String GET_USER_EXHIBITIONS = "getUserExhibitions";
    private static final String CANCEL_EXHIBITION = "cancelExhibition";
    private static final String UPDATE_EXHIBITION = "updateExhibition";
    private static final String FIND_FROM_EXHIBITION = "findFromExhibition";
    private static final String FIND_ALL_EXHIBITIONS = "findAllExhibitions";

    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger(JDBCExhibitionDao.class);
    private final Connection connection;

    public JDBCExhibitionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Exhibition exhibition) {
        try (PreparedStatement ps1 = connection.prepareCall(properties.getProperty(CREATE_EXHIBITION))) {
            connection.setAutoCommit(false);
            int genExpositionID;
            int k = 1;
            ps1.setString(k++, exhibition.getTheme());
            ps1.setString(k++, exhibition.getThemeUk());
            ps1.setDate(k++, Date.valueOf(exhibition.getDate()));
            ps1.setBigDecimal(k++, exhibition.getPrice());
            ps1.setInt(k++, exhibition.getCurrentPlaces());
            ps1.setInt(k++, exhibition.getMaxPlaces());
            ps1.setString(k++, exhibition.getDescription());
            ps1.setString(k++, exhibition.getDescriptionUk());
            ps1.setString(k, exhibition.getImageUrl());
            ps1.executeUpdate();
            ResultSet resultSet = ps1.getGeneratedKeys();
            resultSet.next();
            genExpositionID = resultSet.getInt(1);
            for (Hall hall : exhibition.getHalls()) {
                PreparedStatement ps2 = connection.prepareCall(properties.getProperty(ADD_EXHIBITION_HALL));
                ps2.setInt(1, genExpositionID);
                ps2.setInt(2, hall.getId());
                ps2.setDate(3, Date.valueOf(exhibition.getDate()));
                ps2.executeUpdate();
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
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(FIND_BY_ID))) {
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
        try (Statement statement = connection.createStatement();
             ResultSet expoResultSet = statement.executeQuery(properties.getProperty(FIND_ALL_EXHIBITIONS))) {
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

    public List<Exhibition> findFrom(int sortBy, int startIndex, int rowsCount, boolean findCanceled) {
        List<Exhibition> list = new ArrayList<>();
        ExhibitionMapper expoMapper = new ExhibitionMapper();
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(FIND_FROM_EXHIBITION))) {
            int k = 1;
            ps.setBoolean(k++, findCanceled);
            ps.setInt(k++, sortBy);
            ps.setInt(k++, rowsCount);
            ps.setInt(k, startIndex);
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
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(UPDATE_EXHIBITION))) {
            connection.setAutoCommit(false);
            int k = 1;
            ps.setDate(k++, Date.valueOf(exhibition.getDate()));
            ps.setString(k++, exhibition.getTheme());
            ps.setString(k++, exhibition.getThemeUk());
            ps.setBigDecimal(k++, exhibition.getPrice());
            ps.setString(k++, exhibition.getDescription());
            ps.setString(k++, exhibition.getDescriptionUk());
            ps.setString(k++, exhibition.getImageUrl());
            ps.setDate(k++, Date.valueOf(exhibition.getDate()));
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
    public boolean cancel(int id) {
        try (PreparedStatement ps = connection.prepareCall(properties.getProperty(CANCEL_EXHIBITION))) {
            connection.setAutoCommit(false);
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
        ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
        Map<Exhibition, Integer> map = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(GET_USER_EXHIBITIONS))) {
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exhibition ex = exhibitionMapper.extractFromResultSet(rs);
                ex.setHalls(getHalls(ex));
                map.put(ex, rs.getInt(TICKETS_COUNT));
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
        try (PreparedStatement ps = connection.prepareCall(properties.getProperty(FIND_BY_THEME))) {
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

    public int getRowsNumber(boolean canceled) {
        int rows = 0;
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(GET_ROWS_NUMBER))) {
            ps.setBoolean(1, canceled);
            ResultSet rs = ps.executeQuery();
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
            PreparedStatement ps = connection.prepareCall(properties.getProperty(GET_EXHIBITION_HALLS));
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
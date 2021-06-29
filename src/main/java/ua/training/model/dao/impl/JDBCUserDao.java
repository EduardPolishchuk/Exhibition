package ua.training.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.DBPropertyReader;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JDBCUserDao implements UserDao {

    private static final String GET_EXHIBITION_USERS = "getExhibitionUsers";
    private static final String BALANCE_REPLENISHMENT = "balanceReplenishment";
    private static final String GET_USER_BALANCE = "getUserBalance";
    private static final String USER_IS_VALID = "userIsValid";
    private static final String UPDATE_USER = "updateUser";
    private static final String CREATE_USER = "createUser";
    private static final String USER = "user";
    private static final String FIND_USER_BY_ID = "findUserByID";
    private static final String FIND_ALL_USERS = "findAllUsers";
    private static final String INSERT_INTO_USER_EXHIBITIONS = "insertIntoUserExhibitions";
    private static final String UPDATE_USER_EXHIBITIONS = "updateUserExhibitions";
    private static final String SELECT_FROM_USER_EXHIBITION = "selectFromUserExhibition";
    private static final String UPDATE_BALANCE_AND_PLACES = "updateBalanceAndPlaces";
    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger();
    private final Connection connection;


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User user) {
        try {
            int i = 1;
            PreparedStatement ps = connection.prepareStatement(properties.getProperty(CREATE_USER));
            ps.setString(i++, user.getLogin());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getFirstName());
            ps.setString(i++, user.getLastName());
            ps.setString(i, USER);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        } finally {
            close();
        }
        return true;
    }

    @Override
    public Optional<User> findById(int id) {
        User user = null;
        try {

            UserMapper userMapper = new UserMapper();
            PreparedStatement ps = connection.prepareStatement(properties.getProperty(FIND_USER_BY_ID));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(properties.getProperty(FIND_ALL_USERS));
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return users;
    }


    @Override
    public boolean update(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(properties.getProperty(UPDATE_USER));
            int i = 0;
            ps.setString(++i, user.getLogin());
            ps.setString(++i, user.getEmail());
            ps.setString(++i, user.getPassword());
            ps.setString(++i, user.getFirstName());
            ps.setString(++i, user.getLastName());
            ps.setInt(i, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        } finally {
            close();
        }
        return true;
    }

    public Optional<User> isValid(String login, String password) {
        Optional<User> result = Optional.empty();
        UserMapper userMapper = new UserMapper();
        try {
            PreparedStatement ps = connection.prepareStatement(properties.getProperty(USER_IS_VALID));
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return result;
    }

    public boolean buyTicket(User user, int exhibitionId, int amount) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps2 = connection.prepareStatement(properties.getProperty(UPDATE_BALANCE_AND_PLACES));
            ps2.setInt(1, amount);
            ps2.setInt(2, amount);
            ps2.setInt(3, user.getId());
            ps2.setInt(4, exhibitionId);
            ps2.executeUpdate();
            PreparedStatement ps = connection.prepareStatement(properties.getProperty(SELECT_FROM_USER_EXHIBITION));
            ps.setInt(1, user.getId());
            ps.setInt(2, exhibitionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps = connection.prepareStatement(properties.getProperty(UPDATE_USER_EXHIBITIONS));
            } else {
                ps = connection.prepareStatement(properties.getProperty(INSERT_INTO_USER_EXHIBITIONS));
            }
            ps.setInt(1, amount);
            ps.setInt(2, user.getId());
            ps.setInt(3, exhibitionId);
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

    public BigDecimal getUserBalance(User user) {
        BigDecimal balance = BigDecimal.valueOf(0);
        try {
            PreparedStatement ps = connection.prepareStatement(properties.getProperty(GET_USER_BALANCE));
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                balance = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }finally {
            close();
        }
        return balance;
    }

    @Override
    public BigDecimal balanceReplenishment(BigDecimal amount, User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(properties.getProperty(BALANCE_REPLENISHMENT));
            ps.setBigDecimal(1, amount);
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return getUserBalance(user);
    }

    @Override
    public List<User> getExhibitionUsers(Exhibition exhibition) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(GET_EXHIBITION_USERS))){
            ps.setInt(1,exhibition.getId());
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return users;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}

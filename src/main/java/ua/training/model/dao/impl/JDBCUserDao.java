package ua.training.model.dao.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user (login,email,password,first_name,last_name,role_id) VALUES (?,?,?,?,?,1)");
            int i = 1;
            ps.setString(i++, user.getLogin());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getFirstName());
            ps.setString(i, user.getLastName());
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
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user JOIN role r on r.id = user.role_id WHERE user.id=?");
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
        final String query = "" +
                " select * from user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
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
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET login=?,email=?,password=?,first_name=?,last_name=? WHERE id =?");
            int i = 0;
            ps.setString(i++, user.getLogin());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getFirstName());
            ps.setString(i++, user.getLastName());
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

    @Override
    public boolean delete(int id) {
        return false;
    }

    public Optional<User> isValid(String login, String password) {
        Optional<User> result = Optional.empty();
        UserMapper userMapper = new UserMapper();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user join role r on r.id = user.role_id WHERE login=? AND password=?");
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

    public boolean buyTicket(User user, int exhibitionId, int amount){



        return true;
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

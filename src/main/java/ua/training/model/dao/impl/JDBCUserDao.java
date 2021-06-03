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
    public boolean create(User entity) {
        return false;
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
        }finally {
            close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, Exhibition> teachers = new HashMap<>();

        final String query = "" +
                " select * from user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            ExpositionMapper teacherMapper = new ExpositionMapper();
            UserMapper studentMapper = new UserMapper();

            while (rs.next()) {
                User user = studentMapper
                        .extractFromResultSet(rs);
                Exhibition exhibition = teacherMapper
                        .extractFromResultSet(rs);
//                user = studentMapper
//                        .makeUnique(users, user);
//                exhibition = teacherMapper
//                        .makeUnique(teachers, exhibition);
                user.getExhibitions().add(exhibition);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return null;
        }
    }


    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
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

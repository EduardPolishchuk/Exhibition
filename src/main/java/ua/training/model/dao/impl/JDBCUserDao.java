package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUserDao implements UserDao {
    private Connection connection;


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User findById(int id) {
        User user = null;
        final String query = "" +
                "select * from user where id=" + id;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
//                user = new User();
//                user.setId(rs.getInt("id"));
//                user.setLogin(rs.getString("login"));
//                user.setPassword(rs.getString("password"));
//                if(rs.getString("role").equals("admin")){
//                    user.setRole(User.ROLE.ADMIN);
//                }else {
//                    user.setRole(User.ROLE.USER);
//                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, Exposition> teachers = new HashMap<>();

        final String query = "" +
                " select * from user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            ExpositionMapper teacherMapper = new ExpositionMapper();
            UserMapper studentMapper = new UserMapper();

            while (rs.next()) {
                User user = studentMapper
                        .extractFromResultSet(rs);
                Exposition exposition = teacherMapper
                        .extractFromResultSet(rs);
//                user = studentMapper
//                        .makeUnique(users, user);
//                exposition = teacherMapper
//                        .makeUnique(teachers, exposition);
                user.getExpositions().add(exposition);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void update(User entity) {

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
            throw new RuntimeException(e);
        }
    }
}

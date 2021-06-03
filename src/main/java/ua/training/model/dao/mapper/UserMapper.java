package ua.training.model.dao.mapper;


import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        System.out.println(rs.getString("name"));
        User user = User.builder()
                .id(rs.getInt("id"))
                .login(rs.getString("login"))
                .role(User.ROLE.valueOf(rs.getString("name").toUpperCase()))
                .balance(rs.getBigDecimal("balance"))
                .email(rs.getString("email"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .build();
        return user;
    }
}

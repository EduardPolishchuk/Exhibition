package ua.training.model.dao.mapper;

import ua.training.model.entity.enums.Hall;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HallMapper implements ObjectMapper<Hall>{
    @Override
    public Hall extractFromResultSet(ResultSet rs) throws SQLException {
        Hall hall = Hall.valueOf(rs.getString("name"));
        return hall;
    }

  }

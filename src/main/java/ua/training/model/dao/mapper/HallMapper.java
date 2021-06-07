package ua.training.model.dao.mapper;

import ua.training.model.entity.Hall;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HallMapper implements ObjectMapper<Hall>{
    @Override
    public Hall extractFromResultSet(ResultSet rs) throws SQLException {
        return Hall.valueOf(rs.getString("name"));
    }
  }

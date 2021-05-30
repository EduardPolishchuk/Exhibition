package ua.training.model.dao.mapper;


import ua.training.model.entity.Exposition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExpositionMapper implements ObjectMapper<Exposition> {

    @Override
    public Exposition extractFromResultSet(ResultSet rs) throws SQLException {
        Exposition teacher = new Exposition();
//        teacher.setId(rs.getInt("idteacher"));
//        teacher.setName(rs.getString("teacher.name"));
//        teacher.setCourse(rs.getString("course"));
//        teacher.setRoom(rs.getInt("room"));
//        teacher.setPassHash(rs.getInt("pass_hash"));
        return teacher;
    }

    public Exposition makeUnique(Map<Integer, Exposition> cache,
                              Exposition exposition) {
        cache.putIfAbsent(exposition.getId(), exposition);
        return cache.get(exposition.getId());
    }
}

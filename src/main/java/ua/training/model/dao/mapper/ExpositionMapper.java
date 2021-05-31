package ua.training.model.dao.mapper;


import ua.training.model.entity.Exposition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class ExpositionMapper implements ObjectMapper<Exposition> {

    @Override
    public Exposition extractFromResultSet(ResultSet rs) throws SQLException {
        Exposition exposition = Exposition.builder()
                .date(toLocalDate(rs.getDate("date")))
                .id(rs.getInt("id"))
                .theme(rs.getString("theme"))
                .build();


//        teacher.setId(rs.getInt("idteacher"));
//        teacher.setName(rs.getString("teacher.name"));
//        teacher.setCourse(rs.getString("course"));
//        teacher.setRoom(rs.getInt("room"));
//        teacher.setPassHash(rs.getInt("pass_hash"));
        return exposition;
    }

    public static LocalDate toLocalDate(Date date) {
        return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
    }
}

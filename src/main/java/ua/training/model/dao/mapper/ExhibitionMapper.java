package ua.training.model.dao.mapper;


import ua.training.model.entity.Exhibition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ExhibitionMapper implements ObjectMapper<Exhibition> {

    @Override
    public Exhibition extractFromResultSet(ResultSet rs) throws SQLException {
        return  Exhibition.builder()
                .date(toLocalDate(rs.getDate("date")))
                .id(rs.getInt("id"))
                .price(rs.getBigDecimal("price"))
                .currentPlaces(rs.getInt("current_places"))
                .description(rs.getString("description"))
                .imageUrl(rs.getString("image_url"))
                .theme(rs.getString("theme"))
                .isCanceled(rs.getBoolean("is_canceled"))
                .descriptionUk(rs.getString("description_uk"))
                .themeUk(rs.getString("theme_uk"))
                .build();
          }

    private static LocalDate toLocalDate(Date date) {
        return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
    }
}

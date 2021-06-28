package ua.training.model.dao.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.Exhibition;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExhibitionMapperTest {
    private ExhibitionMapper exhibitionMapper = new ExhibitionMapper();

    @Mock
    private ResultSet resultSet;

    @Test
    public void shouldReturnExhibition() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(resultSet.getDate("date")).thenReturn(Date.valueOf("2020-11-11"));
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(200));
        Mockito.when(resultSet.getInt("current_places")).thenReturn(3);
        Mockito.when(resultSet.getString("description")).thenReturn("Description");
        Mockito.when(resultSet.getString("image_url")).thenReturn("image_url");
        Mockito.when(resultSet.getBoolean("is_canceled")).thenReturn(false);
        Mockito.when(resultSet.getString("description_uk")).thenReturn("DescriptionUk");
        Mockito.when(resultSet.getString("theme_uk")).thenReturn("ThemeUk");
        Mockito.when(resultSet.getString("theme")).thenReturn("Theme");

        Exhibition ex = exhibitionMapper.extractFromResultSet(resultSet);
        Assertions.assertEquals(1,ex.getId());
        Assertions.assertEquals(LocalDate.parse("2020-11-11"),ex.getDate());
        Assertions.assertEquals(BigDecimal.valueOf(200),ex.getPrice());
        Assertions.assertEquals(3,ex.getCurrentPlaces());
        Assertions.assertEquals("Description",ex.getDescription());
        Assertions.assertEquals("DescriptionUk",ex.getDescriptionUk());
        Assertions.assertEquals("image_url",ex.getImageUrl());
        Assertions.assertEquals("Theme",ex.getTheme());
        Assertions.assertEquals("ThemeUk",ex.getThemeUk());
        Assertions.assertFalse(ex.getIsCanceled());
    }
}

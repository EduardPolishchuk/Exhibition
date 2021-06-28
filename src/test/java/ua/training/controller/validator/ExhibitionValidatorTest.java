package ua.training.controller.validator;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.Exhibition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ExhibitionValidatorTest {
    private final ExhibitionValidator validator = new ExhibitionValidator();
    private final Exhibition exhibition = Exhibition.builder()
            .date(LocalDate.now())
            .theme("Theme")
            .imageUrl("https://media.timeout.com/images/105775729/750/422/image.jpg")
            .themeUk("Тема")
            .description("Description")
            .descriptionUk("Опис")
            .maxPlaces(200)
            .currentPlaces(2)
            .price(BigDecimal.valueOf(100))
            .build();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Test
    public void shouldReturnExhibitionOptional() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(request.getParameter("theme")).thenReturn(exhibition.getTheme());
        Mockito.when(request.getParameter("themeUk")).thenReturn(exhibition.getThemeUk());
        Mockito.when(request.getParameter("imageUrl")).thenReturn(exhibition.getImageUrl());
        Mockito.when(request.getParameter("description")).thenReturn(exhibition.getDescription());
        Mockito.when(request.getParameter("descriptionUk")).thenReturn(exhibition.getDescriptionUk());
        Mockito.when(request.getParameter("date")).thenReturn(exhibition.getDate().toString());
        Mockito.when(request.getParameter("price")).thenReturn(exhibition.getPrice().toString());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<Exhibition> optional = validator.validate(request);
        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptional() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(request.getParameter("theme")).thenReturn("");
        Mockito.when(request.getParameter("themeUk")).thenReturn("");
        Mockito.when(request.getParameter("imageUrl")).thenReturn("");
        Mockito.when(request.getParameter("description")).thenReturn("");
        Mockito.when(request.getParameter("descriptionUk")).thenReturn("");
        Mockito.when(request.getParameter("date")).thenReturn("");
        Mockito.when(request.getParameter("price")).thenReturn("");
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<Exhibition> optional = validator.validate(request);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptional2() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(request.getParameter("theme")).thenReturn(exhibition.getTheme());
        Mockito.when(request.getParameter("themeUk")).thenReturn(exhibition.getThemeUk());
        Mockito.when(request.getParameter("imageUrl")).thenReturn(exhibition.getImageUrl());
        Mockito.when(request.getParameter("description")).thenReturn(exhibition.getDescription());
        Mockito.when(request.getParameter("descriptionUk")).thenReturn(exhibition.getDescriptionUk());
        Mockito.when(request.getParameter("date")).thenReturn(exhibition.getDate().toString());
        Mockito.when(request.getParameter("price"))
                .thenReturn(LocalDate.of(1999, 1, 1).toString());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<Exhibition> optional = validator.validate(request);
        Assertions.assertFalse(optional.isPresent());
    }
}

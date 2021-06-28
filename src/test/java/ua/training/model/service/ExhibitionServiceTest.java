package ua.training.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.dao.impl.JDBCExhibitionDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ExhibitionServiceTest {
    @Mock
    private JDBCDaoFactory daoFactoryMock;
    @Mock
    private JDBCExhibitionDao exhibitionDaoMock;
    @InjectMocks
    private ExhibitionService exhibitionServiceInstance;

    @Test
    public void shouldReturnExhibitionByID() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.findById(1)).thenReturn(Optional.of(Exhibition.builder().id(1).build()));
        Exhibition exhibition = exhibitionServiceInstance.findById(1).get();
        Assertions.assertEquals(1, exhibition.getId());
    }

    @Test
    public void shouldCancelExhibitionByID() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.cancel(2)).thenReturn(true);
        Assertions.assertTrue(exhibitionServiceInstance.cancel(2));
    }

    @Test
    public void shouldReturnExhibitionList() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.findFrom(1, 1, 0, true)).thenReturn(new ArrayList<>());
        List<Exhibition> list = exhibitionServiceInstance.getFrom(1, 1, 0, true);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void shouldCreateExhibition() {
        Exhibition ex = Exhibition.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.create(ex)).thenReturn(true);
        Assertions.assertTrue(exhibitionServiceInstance.create(ex));
    }

    @Test
    public void shouldUpdateExhibition() {
        Exhibition ex = Exhibition.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.update(ex)).thenReturn(false);
        Assertions.assertFalse(exhibitionServiceInstance.update(ex));
    }

    @Test
    public void shouldReturnMapUserExhibition() {
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.getUserExhibitions(user)).thenReturn(Optional.of(new HashMap<Exhibition, Integer>()));
        Assertions.assertTrue(exhibitionServiceInstance.getUserExhibitions(user).get().isEmpty());
    }

    @Test
    public void shouldReturnRowsNumber() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.getRowsNumber(true)).thenReturn(4);
        Assertions.assertEquals(4, exhibitionServiceInstance.getRowsNumber(true));
    }

    @Test
    public void shouldFindExhibitionByTheme() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createExpositionDao()).thenReturn(exhibitionDaoMock);
        Mockito.when(exhibitionDaoMock.findByTheme("Theme")).thenReturn(new ArrayList<>());
        Assertions.assertTrue(exhibitionServiceInstance.findByTheme("Theme").isEmpty());
    }
}

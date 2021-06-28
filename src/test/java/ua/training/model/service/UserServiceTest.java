package ua.training.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.dao.impl.JDBCUserDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

public class UserServiceTest {
    @Mock
    private JDBCDaoFactory daoFactoryMock;
    @Mock
    private JDBCUserDao userDaoMock;
    @InjectMocks
    private UserService userServiceInstance;

    @Test
    public void shouldReturnUserByID() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.findById(1)).thenReturn(Optional.of(User.builder().id(1).build()));
        User user = userServiceInstance.findById(1).get();
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    public void shouldReturnUserList() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertTrue(userServiceInstance.findAllUsers().isEmpty());
    }

    @Test
    public void shouldReturnUserListByExhibition() {
        Exhibition ex = Exhibition.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.getExhibitionUsers(ex)).thenReturn(new ArrayList<>());
        Assertions.assertTrue(userServiceInstance.findExhibitionUsers(ex).isEmpty());
    }

    @Test
    public void shouldReturnUserIfValid() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.isValid("login", "password"))
                .thenReturn(Optional.of(User.builder().login("login").password("password").build()));
        User user = userServiceInstance.isValid("login", "password").get();
        Assertions.assertEquals("login", user.getLogin());
        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    public void shouldReturnTrueIsCreate() {
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.create(user)).thenReturn(true);
        Assertions.assertTrue( userServiceInstance.createUser(user));
    }

    @Test
    public void shouldReturnTrueIsUpdate() {
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.update(user)).thenReturn(true);
        Assertions.assertTrue( userServiceInstance.updateUser(user));
    }

    @Test
    public void shouldReturnTrueIsBuyTicket() {
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.buyTicket(user,1,1)).thenReturn(true);
        Assertions.assertTrue( userServiceInstance.buyTicket(user,1,1));
    }

    @Test
    public void shouldReturnBalanceBigDecimal() {
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.getUserBalance(user)).thenReturn(BigDecimal.valueOf(6));
        BigDecimal bigDecimal = userServiceInstance.getUserBalance(user);
        Assertions.assertEquals(BigDecimal.valueOf(6),bigDecimal);
    }

    @Test
    public void shouldReturnBalanceBigDecimalAfterReplenishment() {
        BigDecimal bigDecimal1 = new BigDecimal(6);
        User user = User.builder().build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createUserDao()).thenReturn(userDaoMock);
        Mockito.when(userDaoMock.balanceReplenishment(bigDecimal1,user)).thenReturn(bigDecimal1);
        BigDecimal bigDecimal2 = userServiceInstance.balanceReplenishment(bigDecimal1,user);
        Assertions.assertEquals(bigDecimal1,bigDecimal2);
    }
}

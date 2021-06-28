package ua.training.model.dao.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapperTest {
    private UserMapper userMapper = new UserMapper();

    @Mock
    private ResultSet resultSet;

    @Test
    public void shouldReturnExhibition() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(resultSet.getString("password")).thenReturn("pass");
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getBigDecimal("balance")).thenReturn(BigDecimal.valueOf(200));
        Mockito.when(resultSet.getString("login")).thenReturn("Login");
        Mockito.when(resultSet.getString("role")).thenReturn("user");
        Mockito.when(resultSet.getString("email")).thenReturn("email");
        Mockito.when(resultSet.getString("first_name")).thenReturn("Name");
        Mockito.when(resultSet.getString("last_name")).thenReturn("LastName");

        User user = userMapper.extractFromResultSet(resultSet);
        Assertions.assertEquals(1,user.getId());
        Assertions.assertEquals("pass",user.getPassword());
        Assertions.assertEquals(BigDecimal.valueOf(200),user.getBalance());
        Assertions.assertEquals("Login",user.getLogin());
        Assertions.assertEquals(User.ROLE.USER,user.getRole());
        Assertions.assertEquals("Name",user.getFirstName());
        Assertions.assertEquals("LastName",user.getLastName());
        Assertions.assertEquals("email",user.getEmail());
    }
}

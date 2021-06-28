package ua.training.controller.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class UserValidatorTest {
    private UserValidator validator = new UserValidator();
    private User user = User.builder()
            .id(1)
            .password("password")
            .login("login")
            .email("email@gmail.com")
            .firstName("FirstName")
            .lastName("LastName")
            .balance(BigDecimal.valueOf(300))
            .role(User.ROLE.USER)
            .build();


    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Test
    public void shouldReturnUserOptional() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(request.getParameter("login")).thenReturn(user.getLogin());
        Mockito.when(request.getParameter("password")).thenReturn(user.getPassword());
        Mockito.when(request.getParameter("firstName")).thenReturn(user.getFirstName());
        Mockito.when(request.getParameter("lastName")).thenReturn(user.getLastName());
        Mockito.when(request.getParameter("balance")).thenReturn(user.getBalance().toString());
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        Mockito.when(request.getParameter("email")).thenReturn(user.getEmail());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<User> optionalUpdate = validator.updateValidation(request);
        Optional<User> optionalSingUp = validator.singUpValidation(request);
        Assertions.assertTrue(optionalUpdate.isPresent());
        Assertions.assertTrue(optionalSingUp.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptional() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(request.getParameter("login")).thenReturn("");
        Mockito.when(request.getParameter("password")).thenReturn("");
        Mockito.when(request.getParameter("firstName")).thenReturn(user.getFirstName());
        Mockito.when(request.getParameter("lastName")).thenReturn(user.getLastName());
        Mockito.when(request.getParameter("balance")).thenReturn(user.getBalance().toString());
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        Mockito.when(request.getParameter("email")).thenReturn(user.getEmail());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<User> optionalUpdate = validator.updateValidation(request);
        Optional<User> optionalSingUp = validator.singUpValidation(request);
        Assertions.assertFalse(optionalUpdate.isPresent());
        Assertions.assertFalse(optionalSingUp.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptional2() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(request.getParameter("login")).thenReturn("");
        Mockito.when(request.getParameter("password")).thenReturn(user.getPassword());
        Mockito.when(request.getParameter("firstName")).thenReturn(user.getFirstName());
        Mockito.when(request.getParameter("lastName")).thenReturn(user.getLastName());
        Mockito.when(request.getParameter("balance")).thenReturn(user.getBalance().toString());
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        Mockito.when(request.getParameter("email")).thenReturn(user.getEmail());
        Mockito.when(request.getSession()).thenReturn(session);

        Optional<User> optionalUpdate = validator.updateValidation(request);
        Optional<User> optionalSingUp = validator.singUpValidation(request);
        Assertions.assertTrue(optionalUpdate.isPresent());
        Assertions.assertFalse(optionalSingUp.isPresent());
    }
}

package ua.training.controller.validator;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserValidator {
    private static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$";
    private static final String EMAIL_REG = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
    private static final String FIRST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String LAST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]{1,}";


    public Optional<User> updateValidation(HttpServletRequest request){
        return Optional.ofNullable(validate(request));

    }

    public Optional<User> singUpValidation(HttpServletRequest request){
        String login = request.getParameter("login");
        User user = validate(request);
        if(user == null){
            return Optional.empty();
        }else if (login == null || !login.matches(LOGIN_REG)){
            request.getSession().setAttribute("error",login);
            return Optional.empty();
        }
        user.setLogin(login);
        return Optional.of(user);
    }

    private User validate(HttpServletRequest request) {
        String error;
        Map<String, String> map = new HashMap<>();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        map.put(email, EMAIL_REG);
        map.put(firstName, FIRST_NAME_REG);
        map.put(lastName, LAST_NAME_REG);
        map.put(password, PASSWORD_REG);
        for (String str : map.keySet()) {
            if (!str.matches(map.get(str))) {
                error = str.equals(password) ? "passwordInvalid" : str;
                request.getSession().setAttribute("error", error);
                return null;
            }
        }
        return User.builder()
                .lastName(lastName)
                .password(password)
                .firstName(firstName)
                .email(email)
                .build();
    }
}

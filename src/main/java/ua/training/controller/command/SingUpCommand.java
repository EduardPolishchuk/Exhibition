package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SingUpCommand implements Command {
    private static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$";
    private static final String EMAIL_REG = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
    private static final String FIRST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String LAST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]{1,}";

    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String result = "redirect:/success.jsp";
        String error = null;
        Map<String, String> map = new HashMap<>();
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        map.put(login, LOGIN_REG);
        map.put(email, EMAIL_REG);
        map.put(firstName, FIRST_NAME_REG);
        map.put(lastName, LAST_NAME_REG);
        map.put(password, PASSWORD_REG);
//todo save user data in fields if one of them incorrect
        for (String str : map.keySet()) {
            if (!str.matches(map.get(str))) {
                error = str.equals(password) ? "passwordInvalid" : str;
                request.getSession().setAttribute("error",error);
                return "/singUp.jsp";
            }
        }
        User user = User.builder()
                .login(login)
                .lastName(lastName)
                .password(password)
                .firstName(firstName)
                .email(email)
                .build();
        if(!userService.createUser(user)){
            error="loginInvalid";
            result = "/singUp.jsp";
        }
        request.getSession().setAttribute("error",error);
        return result;
    }
}

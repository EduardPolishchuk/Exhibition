package ua.training.controller.command;

import ua.training.controller.util.ContextUtility;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$";
    private static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]+";
    public static final String REDIRECT = "redirect:/";
    public static final String REDIRECT_LOGIN_JSP = "redirect:/login.jsp";

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        User.ROLE role = (User.ROLE) request.getSession().getAttribute("role");
        Optional<User> result;
        String userName = request.getParameter("login");
        String password = request.getParameter("password");

        if(User.ROLE.ADMIN.equals(role) || User.ROLE.USER.equals(role)){
            ContextUtility.logOutUser(request.getSession());
        }
        if (!userName.matches(LOGIN_REG) || !password.matches(PASSWORD_REG)) {
            request.getSession().setAttribute("incorrect", "passError");
            return REDIRECT_LOGIN_JSP;
        }
        result = userService.isValid(userName, password);
        if (result.isPresent() && !ContextUtility.checkUserIsLogged(request,userName)) {
            user = result.get();
            request.getSession().setAttribute("userProfile", user);
            request.getSession().setAttribute("role", user.getRole());
            return REDIRECT;
        } else {
            String error = !result.isPresent() ? "passError" : "loginedError" ;
            request.getSession().setAttribute("incorrect", error);
            return REDIRECT_LOGIN_JSP;
        }
    }
}

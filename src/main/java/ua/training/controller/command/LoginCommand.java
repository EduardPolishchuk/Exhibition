package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$";
    private static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]+";

    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("incorrect", null);
        User user;
        Optional<User> result;
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        boolean loginValid = userName.matches(LOGIN_REG);
        boolean passwordValid = password.matches(PASSWORD_REG);
//        boolean userLoggedIn = request.getSession().getAttribute("userLoggedIn") == null;

        if (!loginValid || !passwordValid) {
            request.getSession().setAttribute("incorrect", "passError");
            return "redirect:/login.jsp";
        }
        result = userService.isValid(userName, password);
        if (result.isPresent() && !CommandUtility.checkUserIsLogged(request,userName)) {//todo isPresent in model & exception
            user = result.get();
            request.getSession().setAttribute("userProfile", user);
//            request.getSession().setAttribute("userLoggedIn", true);
            request.getSession().setAttribute("role", user.getRole());
            return "redirect:/";
        } else {
            String error = !result.isPresent() ? "passError" : "loginedError" ;
            request.getSession().setAttribute("incorrect", error);
            return "redirect:/login.jsp";
        }
    }
}

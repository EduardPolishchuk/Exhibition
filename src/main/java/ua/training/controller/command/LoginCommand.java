package ua.training.controller.command;

import ua.training.controller.util.ContextUtility;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import static ua.training.constants.Constants.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import static ua.training.constants.RegularExpressions.LOGIN_REG;
import static ua.training.constants.RegularExpressions.PASSWORD_REG;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        User.ROLE role = (User.ROLE) request.getSession().getAttribute(ROLE);
        Optional<User> result;
        String userName = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if(User.ROLE.ADMIN.equals(role) || User.ROLE.USER.equals(role)){
            ContextUtility.logOutUser(request.getSession());
        }
        if (!userName.matches(LOGIN_REG) || !password.matches(PASSWORD_REG)) {
            request.getSession().setAttribute(INCORRECT, PASS_ERROR);
            return REDIRECT_LOGIN_JSP;
        }
        result = userService.isValid(userName, password);
        if (result.isPresent() && !ContextUtility.checkUserIsLogged(request,userName)) {
            user = result.get();
            request.getSession().setAttribute(USER_PROFILE, user);
            request.getSession().setAttribute(ROLE, user.getRole());
            return REDIRECT_HOME;
        } else {
            String error = !result.isPresent() ? PASS_ERROR : LOGINED_ERROR;
            request.getSession().setAttribute(INCORRECT, error);
            return REDIRECT_LOGIN_JSP;
        }
    }
}

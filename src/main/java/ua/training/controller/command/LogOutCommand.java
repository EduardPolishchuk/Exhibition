package ua.training.controller.command;

import ua.training.controller.util.ContextUtility;
import ua.training.model.entity.User;
import static ua.training.constants.Constants.*;
import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User.ROLE role = (User.ROLE) request.getSession().getAttribute(ROLE);
        if (User.ROLE.USER.equals(role) || User.ROLE.ADMIN.equals(role)) {
            ContextUtility.logOutUser(request.getSession());
        }
        return REDIRECT_HOME;
    }
}

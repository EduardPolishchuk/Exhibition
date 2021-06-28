package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static ua.training.constants.Constants.*;

public class BalanceReplenishmentCommand implements Command {
    private final UserService userService;

    public BalanceReplenishmentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_PROFILE);
        try {
            user.setBalance(userService.balanceReplenishment
                    (new BigDecimal(request.getParameter(AMOUNT)), user));
        } catch (NumberFormatException e) {
            return REDIRECT_HOME;
        }

        return REDIRECT_SUCCESS_JSP;
    }
}

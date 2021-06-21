package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class BalanceReplenishmentCommand implements Command {
    private final UserService userService;

    public BalanceReplenishmentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userProfile");
        user.setBalance(userService.balanceReplenishment
                (new BigDecimal(request.getParameter("amount")), user));
        return "redirect:/success.jsp";
    }
}

package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import static ua.training.constants.Constants.*;
import javax.servlet.http.HttpServletRequest;

public class BuyTicketCommand implements Command {
    private final UserService userService;

    public BuyTicketCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int exId = Integer.parseInt(request.getParameter(EX_ID));
        int amount =Integer.parseInt(request.getParameter(AMOUNT));
        double maxAmount =Double.parseDouble(request.getParameter(MAX_AMOUNT));
        if(amount > maxAmount){
            return INSUFFICIENT_FUNDS_ERROR_JSP;
        }
        User user = (User) request.getSession().getAttribute(USER_PROFILE);
        if (userService.buyTicket(user,exId,amount)){
            user.setBalance(userService.getUserBalance(user));
            request.getSession().setAttribute(USER_PROFILE,user);
            return REDIRECT_SUCCESS_JSP;
        }
        return ERROR_JSP;
    }
}

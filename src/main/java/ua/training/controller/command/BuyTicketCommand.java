package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BuyTicketCommand implements Command {
    private final UserService userService;

    public BuyTicketCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int exId = Integer.parseInt(request.getParameter("exEx"));
        int amount =Integer.parseInt(request.getParameter("amount"));
        User user = (User) request.getSession().getAttribute("userProfile");
        if (userService.buyTicket(user,exId,amount)){
            user.setBalance(userService.getUserBalance(user));
            request.getSession().setAttribute("userProfile",user);
            return "redirect:/success.jsp";
        }
        return "/WEB-INF/error.jsp";
    }
}

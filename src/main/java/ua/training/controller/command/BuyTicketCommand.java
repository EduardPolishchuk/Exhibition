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
//        User user = (User) request.getSession().getAttribute("userProfile");
//        int id = Integer.parseInt(request.getParameter("exhibitionId"));
//        if (userService.buyTicket(user,id,1)){
//            return "redirect:/success.jsp";
//        }

        String sr = (String) request.getParameter("exEx");
        String srt = (String) request.getParameter("amount");
        System.out.println("Ex Id: "+ sr);
        System.out.println("Amount: "+ srt);
        return "redirect:/success.jsp";
//        return "/WEB-INF/error.jsp";
    }
}

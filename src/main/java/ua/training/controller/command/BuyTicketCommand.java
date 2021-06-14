package ua.training.controller.command;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class BuyTicketCommand implements  Command{
    @Override
    public String execute(HttpServletRequest request) {

        System.out.println("BUY");
        System.out.println(request.getParameter("id"));
        return "redirect:/success.jsp";
    }
}

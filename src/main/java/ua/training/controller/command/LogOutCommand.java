package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("unknown")) {
            return "/WEB-INF/error.jsp";
        }
        System.out.println(request.getSession().getServletContext().getAttribute("loggedUsers").toString());
        CommandUtility.logOutUser(request);
        System.out.println(request.getSession().getServletContext().getAttribute("loggedUsers").toString());
        return "redirect:/";
    }
}

package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("role").toString().equalsIgnoreCase("unknown")) {
            return "/WEB-INF/error.jsp";
        }
        CommandUtility.logOutUser(request);
        return "redirect:/";
    }
}

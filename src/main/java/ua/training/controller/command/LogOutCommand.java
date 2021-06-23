package ua.training.controller.command;

import ua.training.controller.util.ContextUtility;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equalsIgnoreCase("unknown")) {
            ContextUtility.logOutUser(request.getSession());
        }
        return "redirect:/";
    }
}

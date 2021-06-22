package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equalsIgnoreCase("unknown")) {
            CommandUtility.logOutUser(request);
        }
        return "redirect:/";
    }
}

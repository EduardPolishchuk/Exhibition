package ua.training.controller.command;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.logOutUser(request);
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, null);
        return "redirect:/";
    }
}

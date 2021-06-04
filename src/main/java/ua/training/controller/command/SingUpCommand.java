package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class SingUpCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/success.jsp";
    }
}

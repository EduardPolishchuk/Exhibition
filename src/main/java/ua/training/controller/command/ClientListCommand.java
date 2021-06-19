package ua.training.controller.command;

import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ClientListCommand implements Command{
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("userList", userService.findAllUsers());
        return "/admin/adminClientList.jsp";
    }
}

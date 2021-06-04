package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ClientListCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {


        return "/homepage/adminClientList.jsp";
    }
}

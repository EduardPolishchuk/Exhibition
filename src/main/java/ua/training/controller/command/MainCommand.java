package ua.training.controller.command;

import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class MainCommand implements Command {
    private ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("expoList", exhibitionService.getAllExpositions());
        return "/user/usermain.jsp";
    }
}

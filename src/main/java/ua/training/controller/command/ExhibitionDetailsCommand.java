package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ExhibitionDetailsCommand implements Command {
    private final UserService userService;
    private final ExhibitionService exhibitionService;

    public ExhibitionDetailsCommand(UserService userService, ExhibitionService exhibitionService) {
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Exhibition exhibition;
        try {
            exhibition = exhibitionService.findById(Integer.parseInt(request.getParameter("exId")));
            request.getSession().setAttribute("exhibition", exhibition);
        } catch (Exception e) {
            return "/WEB-INF/error.jsp";
        }
        request.getSession().setAttribute("userList", userService.findExhibitionUsers(exhibition));
        return "/admin/adminExhibitionView.jsp";
    }
}

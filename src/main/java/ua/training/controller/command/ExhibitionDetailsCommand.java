package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ExhibitionDetailsCommand implements Command {
    private final ExhibitionService exhibitionService;

    public ExhibitionDetailsCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Exhibition exhibition;
        try {
            exhibition = exhibitionService.findById(Integer.parseInt(request.getParameter("exId")));
            request.getSession().setAttribute("exhibition", exhibition);
        } catch (Exception e) {
            return "/WEB-INF/error.jsp";
        }
        System.out.println(request.getSession().getAttribute("role"));
        page = User.ROLE.ADMIN.equals(request.getSession().getAttribute("role")) ?
                "/admin/adminExhibitionView.jsp" : "/WEB-INF/exhibitionView.jsp";
        return page;
    }
}

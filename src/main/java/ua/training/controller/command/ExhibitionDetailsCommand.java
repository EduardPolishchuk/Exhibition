package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ExhibitionDetailsCommand implements Command {
    private final String NOT_FOUND = " Exhibition not found";
    private final ExhibitionService exhibitionService;

    public ExhibitionDetailsCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<Exhibition> optional;
        String page;
        try {
            optional = exhibitionService.findById(Integer.parseInt(request.getParameter("exId")));
            request.getSession().setAttribute("exhibition", optional.orElseThrow(()->new Exception(NOT_FOUND)));
        } catch (Exception e) {
            return "/WEB-INF/error.jsp";
        }
        page = User.ROLE.ADMIN.equals(request.getSession().getAttribute("role")) ?
                "/admin/adminExhibitionView.jsp" : "/WEB-INF/exhibitionView.jsp";
        return page;
    }
}

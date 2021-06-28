package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.constants.Constants.*;

public class ExhibitionDetailsCommand implements Command {

    private final ExhibitionService exhibitionService;

    public ExhibitionDetailsCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<Exhibition> optional;
        String page;
        try {
            optional = exhibitionService.findById(Integer.parseInt(request.getParameter(EX_ID)));
            request.getSession().setAttribute(EXHIBITION, optional.orElseThrow(()->new Exception(NOT_FOUND)));
        } catch (Exception e) {
            return WEB_INF_ERROR_JSP;
        }
        page = User.ROLE.ADMIN.equals(request.getSession().getAttribute("role")) ?
                ADMIN_ADMIN_EXHIBITION_VIEW_JSP : WEB_INF_EXHIBITION_VIEW_JSP;
        return page;
    }
}

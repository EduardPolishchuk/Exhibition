package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.constants.Constants.*;

public class SearchCommand implements Command {
    private final ExhibitionService exhibitionService;

    public SearchCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String search = request.getParameter(SEARCH);
        request.getSession().setAttribute(SEARCH, search);
        List<Exhibition> list = exhibitionService.findByTheme(search);
        request.getSession().setAttribute(EXPO_LIST, list);
        return INDEX_JSP;
    }
}

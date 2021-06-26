package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchCommand implements Command {
    private final ExhibitionService exhibitionService;

    public SearchCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String search = request.getParameter("search");
        request.getSession().setAttribute("search", search);
        List<Exhibition> list = exhibitionService.findByTheme(search);
        request.getSession().setAttribute("expoList", list);
        return "/index.jsp";
    }
}

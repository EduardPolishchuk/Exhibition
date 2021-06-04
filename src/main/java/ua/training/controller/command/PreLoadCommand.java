package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PreLoadCommand implements Command {
    ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public String execute(HttpServletRequest request) {
        List<Exhibition> exhibitions = exhibitionService.getAllExpositions();
        request.getSession().setAttribute("expoList",exhibitions);
        System.out.println("WORKS EMPTY");
        return "/index33.jsp";
    }
}

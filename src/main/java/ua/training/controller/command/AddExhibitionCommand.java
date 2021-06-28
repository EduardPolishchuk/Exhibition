package ua.training.controller.command;

import ua.training.controller.validator.ExhibitionValidator;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.Hall;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AddExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService;
    private final ExhibitionValidator validator;

    public AddExhibitionCommand(ExhibitionService exhibitionService, ExhibitionValidator validator) {
        this.exhibitionService = exhibitionService;
        this.validator= validator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String result = "/admin/adminbasis.jsp";
        String error;
        Set<Hall> exhibitionHalls = getHallsFromRequest(request);
        Optional<Exhibition> optional = validator.validate(request);
        Exhibition exhibition;
        if (!optional.isPresent()) {
            return result;
        }
        if (exhibitionHalls.size() < 1) {
            error = "invalidHalls";
            request.getSession().setAttribute("error", error);
            return result;
        }
        exhibition = optional.get();
        exhibition.setHalls(exhibitionHalls);
        if (!exhibitionService.create(exhibition)) {
            error = "invalidDate";
            request.getSession().setAttribute("error", error);
            return result;
        }
        result = "redirect:/success.jsp";
        return result;
    }

    private Set<Hall> getHallsFromRequest(HttpServletRequest request) {
        Set<Hall> exhibitionHalls = new HashSet<>();
        Hall[] allHalls = Hall.values();
        for (Hall h : allHalls) {
            if (request.getParameter(h.toString()) != null) {
                exhibitionHalls.add(Hall.valueOf(request.getParameter(h.toString())));
            }
        }
        return exhibitionHalls;
    }
}

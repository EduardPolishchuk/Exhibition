package ua.training.controller.command;

import ua.training.constants.Constants;
import ua.training.controller.validator.ExhibitionValidator;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.Hall;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ua.training.constants.Constants.*;

public class AddExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService;
    private final ExhibitionValidator validator;

    public AddExhibitionCommand(ExhibitionService exhibitionService, ExhibitionValidator validator) {
        this.exhibitionService = exhibitionService;
        this.validator= validator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String result = Constants.ADMIN_ADMINBASIS_JSP;
        String error;
        Set<Hall> exhibitionHalls = getHallsFromRequest(request);
        Optional<Exhibition> optional = validator.validate(request);
        Exhibition exhibition;
        if (!optional.isPresent()) {
            return result;
        }
        if (exhibitionHalls.size() < 1) {
            error = INVALID_HALLS;
            request.getSession().setAttribute(ERROR, error);
            return result;
        }
        exhibition = optional.get();
        exhibition.setHalls(exhibitionHalls);
        if (!exhibitionService.create(exhibition)) {
            error = INVALID_DATE;
            request.getSession().setAttribute(ERROR, error);
            return result;
        }
        result = REDIRECT_SUCCESS_JSP;
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

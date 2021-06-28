package ua.training.controller.command;

import ua.training.controller.validator.ExhibitionValidator;
import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.constants.Constants.*;

public class UpdateExhibitionCommand implements Command {
    public static final String REDIRECT_ADMIN_ADMIN_EXHIBITION_VIEW_EX_ID = "redirect:/admin/adminExhibitionView?exId=";
    private final ExhibitionService exhibitionService;
    private final ExhibitionValidator validator;

    public UpdateExhibitionCommand(ExhibitionService exhibitionService, ExhibitionValidator validator) {
        this.exhibitionService = exhibitionService;
        this.validator = validator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String ifError = ADMIN_EXHIBITION_VIEW_JSP;
        String error;
        int id = Integer.parseInt(request.getParameter(EX_ID));
        Optional<Exhibition> optional = validator.validate(request);
        if (!optional.isPresent()) {
            return ifError;
        }
        if (!exhibitionService.update(optional.get())) {
            error = INVALID_DATE;
            request.getSession().setAttribute(ERROR, error);
            return ifError;
        }
        return REDIRECT_ADMIN_ADMIN_EXHIBITION_VIEW_EX_ID + id;
    }
}

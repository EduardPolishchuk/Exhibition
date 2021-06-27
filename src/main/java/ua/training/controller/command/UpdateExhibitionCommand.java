package ua.training.controller.command;

import ua.training.controller.validator.ExhibitionValidator;
import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UpdateExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService ;
    private final ExhibitionValidator validator;

    public UpdateExhibitionCommand(ExhibitionService exhibitionService, ExhibitionValidator validator) {
        this.exhibitionService = exhibitionService;
        this.validator = validator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String ifError = "/admin/adminExhibitionView.jsp";
        String error ;
        int id = Integer.parseInt(request.getParameter("exId"));
        Optional<Exhibition> optional = validator.validate(request);
        if (!optional.isPresent()) {
            return ifError;
        }
        if (!exhibitionService.update(optional.get())) {
            error = "invalidDate";
            request.getSession().setAttribute("error", error);
            return ifError;
        }
        return "redirect:/admin/adminExhibitionView?exId="+id;
    }
}

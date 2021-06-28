package ua.training.controller.command;

import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.constants.Constants.*;

public class CancelExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService;

    public CancelExhibitionCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = REDIRECT_SUCCESS_JSP;
        try {
            if (!exhibitionService.cancel(Integer.parseInt(request.getParameter(EX_ID))))
                page = ERROR_JSP;
        } catch (NumberFormatException e) {
            return ERROR_JSP;
        }
        return page;
    }
}

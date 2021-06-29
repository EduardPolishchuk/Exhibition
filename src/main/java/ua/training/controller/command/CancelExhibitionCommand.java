package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.constants.Constants.*;

public class CancelExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService;
    private static final Logger logger = LogManager.getLogger(CancelExhibitionCommand.class);

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
            logger.log(Level.ERROR, e);
            return ERROR_JSP;
        }
        return page;
    }
}

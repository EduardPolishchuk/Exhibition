package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;
import static ua.training.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ClientListCommand implements Command{
    private static final Logger logger = LogManager.getLogger(ClientListCommand.class);
    private final UserService userService ;
    private final ExhibitionService exhibitionService;

    public ClientListCommand(UserService userService, ExhibitionService exhibitionService) {
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter(EX_ID) != null){
            Optional<Exhibition> optional;
            try {
                optional = exhibitionService.findById(Integer.parseInt(request.getParameter(EX_ID)));
                request.getSession().setAttribute(EXHIBITION, optional.orElseThrow(()->new NumberFormatException(NOT_FOUND)));
            } catch (NumberFormatException e) {
                logger.log(Level.ERROR, e.getMessage());
                return ERROR_JSP;
            }
            request.getSession().setAttribute(USER_LIST, userService.findExhibitionUsers(optional.get()));
        }else {
            request.getSession().setAttribute(USER_LIST, userService.findAllUsers());
        }
        return CLIENT_LIST_JSP;
    }
}

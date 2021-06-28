package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;
import static ua.training.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ClientListCommand implements Command{
    private UserService userService ;
    private ExhibitionService exhibitionService;

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
                request.getSession().setAttribute(EXHIBITION, optional.orElseThrow(()->new Exception(NOT_FOUND)));
            } catch (Exception e) {
                return ERROR_JSP;
            }
            request.getSession().setAttribute(USER_LIST, userService.findExhibitionUsers(optional.get()));
        }else {
            request.getSession().setAttribute(USER_LIST, userService.findAllUsers());
        }
        return CLIENT_LIST_JSP;
    }
}

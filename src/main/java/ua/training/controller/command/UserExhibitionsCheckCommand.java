package ua.training.controller.command;

import ua.training.constants.Constants;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.constants.Constants.*;

public class UserExhibitionsCheckCommand implements Command{
    private final UserService userService;
    private final ExhibitionService exhibitionService ;

    public UserExhibitionsCheckCommand(UserService userService, ExhibitionService exhibitionService) {
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter(USER_ID));
        User user;
        Optional<User> optional = userService.findById(userID);
        if(optional.isPresent()){
            user = optional.get();
            request.getSession().setAttribute(EX_USER_NAME,user.getLogin());
        }else {
            return ERROR_JSP;
        }
        request.getSession().setAttribute(USER_EXHIBITIONS, exhibitionService.getUserExhibitions(user).get());
        return ADMIN_USER_EXHIBITIONS_JSP;
    }
}

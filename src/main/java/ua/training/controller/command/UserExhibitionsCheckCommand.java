package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserExhibitionsCheckCommand implements Command{
    private final UserService userService = new UserService();
    private final ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public String execute(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("userID"));
        User user;
        Optional<User> optional = userService.findById(userID);
        if(optional.isPresent()){
            user = optional.get();
            request.getSession().setAttribute("exUserName",user.getLogin());
        }else {
            return "/WEB-INF/error/error.jsp";
        }
         request.getSession().setAttribute("userExhib", exhibitionService.getUserExhibitions(user).get());
        return "/admin/adminUserExhibitions.jsp";
    }
}

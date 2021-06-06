package ua.training.controller.command;

import ua.training.Test;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class LoginCommand implements Command {
    UserService userService = new UserService();
    ExhibitionService exhibitionService = new ExhibitionService();


    @Override
    public String execute(HttpServletRequest request) {
        User user;
        Optional<User> result;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = null;
        boolean loginValid = !(login == null || login.equals(""));
        boolean passwordValid = !(password == null || password.equals(""));

        if (!loginValid || !passwordValid || CommandUtility.checkUserIsLogged(request, login)) {
            return "/WEB-INF/error.jsp";
        }

        result = userService.isValid(login, password);
        if (result.isPresent()){
            user = result.get();
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("userExhib",exhibitionService.getUserExhibitions(user).get());
        }else {
//            request.getSession().setAttribute("incorect",null);
            return "redirect:/login2.jsp?incorect=1";
        }

        if ("admin".equalsIgnoreCase(user.getRole().toString())) {
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, login);
            return "redirect:/homepage/adminbasis.jsp";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.USER, login);
            return "redirect:/homepage/userbasis.jsp";
        }
    }

}

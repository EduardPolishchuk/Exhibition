package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;

public class LoginCommand implements Command {
    UserService userService = new UserService();
    ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("incorrect",null);
        User user;
        Optional<User> result;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean loginValid = !(login == null || login.equals(""));
        boolean passwordValid = !(password == null || password.equals(""));
        boolean userLoggedIn = request.getSession().getAttribute("userLoggedIn") == null;

        if (!loginValid || !passwordValid ) {
            return "/WEB-INF/error.jsp";
        }
        result = userService.isValid(login, password);
        if (result.isPresent() && userLoggedIn){
            user = result.get();
            request.getSession().setAttribute("userProfile",user);
            request.getSession().setAttribute("userLoggedIn",true);
            request.getSession().setAttribute("role",user.getRole());
            request.getSession().setAttribute("userExhib",exhibitionService.getUserExhibitions(user).get());
            return "redirect:/";
        }else {
            String error = userLoggedIn? "loginedError": "passError";
            request.getSession().setAttribute("incorrect",error);
            return "redirect:/login.jsp";
        }
    }

    static synchronized boolean checkUserInSystem(HttpServletRequest request, String login){
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(login::equals)){
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}

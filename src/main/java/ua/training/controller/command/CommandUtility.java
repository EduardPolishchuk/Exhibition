package ua.training.controller.command;

import ua.training.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            User.ROLE role, String login) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("login", login);
        session.setAttribute("role", role);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String login){
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

    static boolean logOutUser(HttpServletRequest request) {
        String login = (String) request.getServletContext()
                .getAttribute("login");
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
       if (!loggedUsers.remove(login)){
           return false;
       }
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return true;
    }
}

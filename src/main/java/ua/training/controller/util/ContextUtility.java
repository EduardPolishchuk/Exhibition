package ua.training.controller.util;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class ContextUtility {

   public static synchronized boolean checkUserIsLogged(HttpServletRequest request, String login) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(login::equals)) {
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public static synchronized void logOutUser(HttpSession session) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext()
                .getAttribute("loggedUsers");
        User user = (User) session.getAttribute("userProfile");
        loggedUsers.remove(user.getLogin());
        session.removeAttribute("error");
        session.removeAttribute("userProfile");
        session.setAttribute("role", User.ROLE.UNKNOWN);
        session.getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }
}

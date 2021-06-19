package ua.training.controller.command;

import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {

    static void setUserRole(HttpServletRequest request,
                            User.ROLE role) {
        HttpSession session = request.getSession();
        session.setAttribute("role", role);
    }

    static synchronized boolean checkUserIsLogged(HttpServletRequest request, String login) {
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

    static synchronized void logOutUser(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        User user = (User) request.getSession()
                .getAttribute("userProfile");
        loggedUsers.remove(user.getLogin());
        setUserRole(request, User.ROLE.UNKNOWN);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }
}

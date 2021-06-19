package ua.training.controller.listener;

import ua.training.model.entity.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent.getSession().getServletContext()
                .getAttribute("loggedUsers");
        User user = (User) httpSessionEvent.getSession()
                .getAttribute("userProfile");
        loggedUsers.remove(user.getLogin());
        httpSessionEvent.getSession().setAttribute("role", User.ROLE.UNKNOWN);
        httpSessionEvent.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }
}

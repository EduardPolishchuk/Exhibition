package ua.training.controller.listener;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExpositionDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.List;


public class SessionListener implements HttpSessionListener {
    ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//        @SuppressWarnings("unchecked")
//        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
//                .getSession().getServletContext()
//                .getAttribute("loggedUsers");
//        String login = (String) httpSessionEvent.getSession()
//                .getAttribute("login");
////        httpSessionEvent.getSession().setAttribute("role", User.ROLE.UNKNOWN);
//        loggedUsers.remove(login);
//        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}

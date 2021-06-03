package ua.training.controller.listener;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExpositionDao;
import ua.training.model.entity.Exposition;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;


public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        ExpositionDao exp = daoFactory.createExpositionDao();
        httpSessionEvent.getSession().setAttribute("expoList", exp.findAll());
        System.out.println("Works");
        exp.close();
        for (Exposition e : exp.findAll()) {
            System.out.println(e);
        }
        System.out.println("SESSION CREATED====>");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String login = (String) httpSessionEvent.getSession()
                .getAttribute("login");
        loggedUsers.remove(login);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
        System.out.println("SESSION DESTROYED====>");
    }
}

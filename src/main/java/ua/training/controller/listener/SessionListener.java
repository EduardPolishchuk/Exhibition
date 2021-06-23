package ua.training.controller.listener;

import ua.training.controller.util.ContextUtility;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.time.LocalDate;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("currentDate", LocalDate.now());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ContextUtility.logOutUser(httpSessionEvent.getSession());
    }
}

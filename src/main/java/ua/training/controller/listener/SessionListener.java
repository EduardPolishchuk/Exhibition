package ua.training.controller.listener;

import ua.training.controller.util.ContextUtility;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ContextUtility.logOutUser(httpSessionEvent.getSession());
    }
}

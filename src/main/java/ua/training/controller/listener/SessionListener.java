package ua.training.controller.listener;

import ua.training.controller.command.CommandUtility;
import ua.training.model.service.ExhibitionService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {
    ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpServletRequest servletRequest = (HttpServletRequest) httpSessionEvent;
        CommandUtility.logOutUser(servletRequest);
    }
}

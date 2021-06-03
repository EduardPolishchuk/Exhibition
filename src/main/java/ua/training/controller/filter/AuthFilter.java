package ua.training.controller.filter;

import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    private ExhibitionService exhibitionService = new ExhibitionService();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
           filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean access(User user, String page) {
        return user != null && page.contains(user.getRole().toString().toLowerCase());
    }
}

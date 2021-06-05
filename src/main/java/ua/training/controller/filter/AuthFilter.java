package ua.training.controller.filter;

import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    private ExhibitionService exhibitionService = new ExhibitionService();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getSession().getAttribute("role"));
        User.ROLE role1 = (User.ROLE) request.getSession().getAttribute("role");
        String[] uri = request.getRequestURI().split("/");
//        if(access(role1, uri[uri.length - 1])) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
        if (User.ROLE.UNKNOWN.equals(role1) || role1 == null) {
            request.getRequestDispatcher("/WEB-INF/noAccess.jsp").forward(request, response);

        }
        filterChain.doFilter(servletRequest, servletResponse);
//        response.sendRedirect("/WEB-INF/noAccess.jsp");

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean access(User.ROLE role, String page) {
        return role != null && page.contains(role.toString().toLowerCase());
    }
}

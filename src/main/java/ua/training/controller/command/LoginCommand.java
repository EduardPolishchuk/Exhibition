package ua.training.controller.command;

import ua.training.Test;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = null;
        boolean loginValid = !(login == null || login.equals(""));
        boolean passwordValid = !(password == null || password.equals(""));

        try {
            if (!loginValid || !passwordValid || !Test.dbCheck(login, password)) {
                return "/login.jsp";
            }
            role = Test.getRole(login);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if (CommandUtility.checkUserIsLogged(request, login)) {
            return "/WEB-INF/error.jsp";
        }

        if ("admin".equals(role)) {
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, login);
            return "redirect:/admin/adminbasis.jsp";
        } else if ("user".equals(role)) {
            CommandUtility.setUserRole(request, User.ROLE.USER, login);
            return "redirect:/user/userbasis.jsp";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, login);
            return "/login.jsp";
        }
    }

}

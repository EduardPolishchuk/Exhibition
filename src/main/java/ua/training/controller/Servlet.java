package ua.training.controller;


import ua.training.controller.command.*;
import ua.training.controller.validator.ExhibitionValidator;
import ua.training.controller.validator.UserValidator;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("logout",
                new LogOutCommand());
        commands.put("login",
                new LoginCommand(new UserService()));
        commands.put("user/userevents" , new UserEventsCommand(new ExhibitionService()));
        commands.put("admin/adminClientList" , new ClientListCommand(new UserService(),
                new ExhibitionService()));
        commands.put("start" , new PreLoadCommand(new ExhibitionService()));
        commands.put("singUp" , new SingUpCommand(new UserService(), new UserValidator()));
        commands.put("search" , new SearchCommand(new ExhibitionService()));
        commands.put("user/userupdate" ,
                new UpdateUserCommand(new UserService(),new UserValidator()));
        commands.put("user/userbuy" , new BuyTicketCommand(new UserService()));
        commands.put("admin/adminExhibitionView" ,
                new ExhibitionDetailsCommand(new ExhibitionService()));
        commands.put("admin/adminExhibitionUpdate" ,
                new UpdateExhibitionCommand(new ExhibitionService(), new ExhibitionValidator()));
        commands.put("admin/adminCancelExhibition" ,
                new CancelExhibitionCommand(new ExhibitionService()));
        commands.put("exhibitionView" ,
                new ExhibitionDetailsCommand(new ExhibitionService()));
        commands.put("changeBalance" , new BalanceReplenishmentCommand(new UserService()));
        commands.put("admin/adminAddExhibition" ,
                new AddExhibitionCommand(new ExhibitionService(), new ExhibitionValidator()));
        commands.put("admin/adminUserExhibitions" ,
                new UserExhibitionsCheckCommand(new UserService(),new ExhibitionService()));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().replaceAll(".*/Exhibition/" , "");
        Command command = commands.getOrDefault(path , new PreLoadCommand(new ExhibitionService()));
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/Exhibition"));
        }
        else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}

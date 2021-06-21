package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ClientListCommand implements Command{
    private UserService userService ;
    private ExhibitionService exhibitionService;

    public ClientListCommand(UserService userService, ExhibitionService exhibitionService) {
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter("exId") != null){
            Exhibition exhibition;
            try {
                exhibition = exhibitionService.findById(Integer.parseInt(request.getParameter("exId")));
                request.getSession().setAttribute("exhibition", exhibition);
            } catch (Exception e) {
                return "/WEB-INF/error.jsp";
            }
            request.getSession().setAttribute("userList", userService.findExhibitionUsers(exhibition));
        }else {
            request.getSession().setAttribute("userList", userService.findAllUsers());
        }
        return "/admin/adminClientList.jsp";
    }
}

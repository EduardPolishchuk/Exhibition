package ua.training.controller.command;

import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ExhibitionDetailsCommand implements Command{
    private final UserService userService;
    private final ExhibitionService exhibitionService;

    public ExhibitionDetailsCommand(UserService userService, ExhibitionService exhibitionService) {
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        System.out.println(request.getParameter("exId"));
        request.getSession().setAttribute("userList",userService.findAllUsers());
        try {
            request.getSession().setAttribute("exhibition",
                    exhibitionService.findById(Integer.parseInt(request.getParameter("exId"))));
        }catch (Exception e){
            return "/WEB-INF/error.jsp";
        }
        System.out.println("HERE ---> 2");
        return "/admin/adminExhibitionView.jsp";
    }
}

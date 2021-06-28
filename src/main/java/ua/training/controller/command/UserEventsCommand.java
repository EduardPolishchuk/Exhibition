package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public class UserEventsCommand implements Command {
    private final ExhibitionService exhibitionService ;

    public UserEventsCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userProfile");
        Optional<Map<Exhibition, Integer>> ops = exhibitionService.getUserExhibitions(user);
        ops.ifPresent(exhibitionIntegerMap -> request.getSession().setAttribute("userExhib", exhibitionIntegerMap));
        return "/user/userbasis.jsp";
    }
}

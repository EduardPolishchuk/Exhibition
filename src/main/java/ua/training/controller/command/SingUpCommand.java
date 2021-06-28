package ua.training.controller.command;

import ua.training.controller.validator.UserValidator;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class SingUpCommand implements Command {
    private final UserService userService;
    private final UserValidator validator;

    public SingUpCommand(UserService userService, UserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String result = "redirect:/success.jsp";
        Optional<User> optional = validator.singUpValidation(request);
        if (!optional.isPresent()) {
            return "redirect:/user/userprofile.jsp";
        }
        System.out.println(optional.get());
        if (!userService.createUser(optional.get())) {
            result = "/singUp.jsp";
            request.getSession().setAttribute("error", "loginInvalid");
        }
        return result;
    }
}

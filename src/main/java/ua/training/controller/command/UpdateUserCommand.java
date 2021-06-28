package ua.training.controller.command;

import ua.training.controller.validator.UserValidator;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UpdateUserCommand implements Command {
    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserValidator validator = new UserValidator();
        Optional<User> optional = validator.updateValidation(request);
        User user = (User) request.getSession().getAttribute("userProfile");
        if (!optional.isPresent()) {
            return "redirect:/user/userprofile.jsp";
        }
        user.setFirstName(optional.get().getFirstName());
        user.setLastName(optional.get().getLastName());
        user.setPassword(optional.get().getPassword());
        user.setEmail(optional.get().getEmail());
        if (!userService.updateUser(user)) {
            request.getSession().setAttribute("error", "loginInvalid");
        }
        return "redirect:/user/userprofile.jsp";
    }
}

package ua.training.controller.command;

import ua.training.controller.validator.UserValidator;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import static ua.training.constants.Constants.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UpdateUserCommand implements Command {
    private final UserService userService;
    private final UserValidator validator;

    public UpdateUserCommand(UserService userService,UserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<User> optional = validator.updateValidation(request);
        User user = (User) request.getSession().getAttribute(USER_PROFILE);
        if (!optional.isPresent()) {
            return REDIRECT_USERPROFILE_JSP;
        }
        user.setFirstName(optional.get().getFirstName());
        user.setLastName(optional.get().getLastName());
        user.setPassword(optional.get().getPassword());
        user.setEmail(optional.get().getEmail());
        if (!userService.updateUser(user)) {
            request.getSession().setAttribute(ERROR, LOGIN_INVALID);
        }
        return REDIRECT_USERPROFILE_JSP;
    }
}

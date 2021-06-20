package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserCommand implements Command {
    private final UserService userService;
    private static final String EMAIL_REG = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
    private static final String FIRST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String LAST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]{1,}";

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String error = "loginInvalid";
        Map<String, String> map = new HashMap<>();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        map.put(email, EMAIL_REG);
        map.put(firstName, FIRST_NAME_REG);
        map.put(lastName, LAST_NAME_REG);
        map.put(password, PASSWORD_REG);
        for (String str : map.keySet()) {
            if (!str.matches(map.get(str))) {
                error = str.equals(password) ? "passwordInvalid" : str;
                request.getSession().setAttribute("error",error);
                return "redirect:/user/userprofile.jsp";
            }
        }
        User user = (User) request.getSession().getAttribute("userProfile");
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPassword(password);
        if(!userService.updateUser(user)){
            request.getSession().setAttribute("error",error);
        }
        return "redirect:/user/userprofile.jsp";
    }
}

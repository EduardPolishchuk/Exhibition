package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements Command{
    private static final String EMAIL_REG = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
    private static final String FIRST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String LAST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    private static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]{1,}";

    @Override
    public String execute(HttpServletRequest request) {

        return "redirect:/user/userprofile.jsp";
    }
}

package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CancelExhibitionCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}

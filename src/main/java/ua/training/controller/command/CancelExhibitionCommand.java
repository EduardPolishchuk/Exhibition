package ua.training.controller.command;

import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class CancelExhibitionCommand implements Command {
    private final ExhibitionService exhibitionService;

    public CancelExhibitionCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = "redirect:/success.jsp";
        try {
            if (!exhibitionService.cancel(Integer.parseInt(request.getParameter("exID"))))
                page = "/WEB-INF/error/error.jsp";
        } catch (NumberFormatException e) {
            return "/WEB-INF/error/error.jsp";
        }
        return page;
    }
}

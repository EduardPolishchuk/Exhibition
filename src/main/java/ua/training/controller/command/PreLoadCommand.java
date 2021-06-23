package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PreLoadCommand implements Command {
    public static final int RECORDS_PER_PAGE = 3;
    private final ExhibitionService exhibitionService;

    public PreLoadCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        int sortBy = 1;
        if (request.getParameter("sortBy") != null) {
            sortBy = Integer.parseInt(request.getParameter("sortBy"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Exhibition> list = exhibitionService.getFrom(sortBy, (page - 1) * RECORDS_PER_PAGE,
                RECORDS_PER_PAGE);
        if(list.isEmpty()){
            return "/index.jsp";
        }
        int noOfRecords = exhibitionService.getRowsNumber();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
        request.setAttribute("expoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("sortBy", sortBy);

        return "/index.jsp";
    }
}

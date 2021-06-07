package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PreLoadCommand implements Command {
    public static final int RECORDS_PER_PAGE = 3;
    ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public String execute(HttpServletRequest request) {
        String sortBy = request.getParameter("sortBy");
        sortBy = sortBy == null ? "id" : sortBy;
        System.out.println(sortBy);
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Exhibition> list = exhibitionService.getFrom(sortBy,(page - 1) * RECORDS_PER_PAGE,
                RECORDS_PER_PAGE);
        int noOfRecords = exhibitionService.getRowsNumber();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
        request.setAttribute("expoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "/index.jsp";
    }
}

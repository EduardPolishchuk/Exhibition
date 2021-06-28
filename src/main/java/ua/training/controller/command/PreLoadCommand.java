package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;
import static ua.training.constants.Constants.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PreLoadCommand implements Command {
    public static final int RECORDS_PER_PAGE = 3;
    public static final String CURRENT_PAGE_NUMBER = "currentPage";
    private final ExhibitionService exhibitionService;

    public PreLoadCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String canceledParam = request.getParameter(CANCELED);
        boolean findCanceled = canceledParam != null && !canceledParam.isEmpty();
        int page = 1;
        int sortBy = 1;
        if (request.getParameter(SORT_BY) != null && !request.getParameter(SORT_BY).isEmpty()) {
            sortBy = Integer.parseInt(request.getParameter(SORT_BY));
        }
        if (request.getParameter(PAGE) != null && !request.getParameter(SORT_BY).isEmpty()) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }
        List<Exhibition> list = exhibitionService.getFrom(sortBy, (page - 1) * RECORDS_PER_PAGE,
                RECORDS_PER_PAGE, findCanceled);
        if (list.isEmpty()) {
            return INDEX_JSP;
        }
        int noOfRecords = exhibitionService.getRowsNumber(findCanceled);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
        request.setAttribute(EXPO_LIST, list);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE_NUMBER, page);
        request.setAttribute(SORT_BY, sortBy);

        return INDEX_JSP;
    }
}

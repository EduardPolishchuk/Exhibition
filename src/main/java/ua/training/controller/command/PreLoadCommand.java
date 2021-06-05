package ua.training.controller.command;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PreLoadCommand implements Command {
    ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 3;
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Exhibition> list = exhibitionService.getFrom((page-1)*recordsPerPage,
                recordsPerPage);
//        List<Exhibition> list = dao.viewAllEmployees((page-1)*recordsPerPage,
//                recordsPerPage);
        int noOfRecords = exhibitionService.getRowsNumber();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("expoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);







        //        List<Exhibition> exhibitions = exhibitionService.getAllExpositions();
//        request.getSession().setAttribute("expoList",exhibitions);
//        System.out.println("WORKS");
        return "/index.jsp";
    }
}

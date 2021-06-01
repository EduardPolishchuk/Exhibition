package ua.training.controller.command;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExpositionDao;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.enums.Hall;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

public class MainCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
//       DaoFactory daoFactory = DaoFactory.getInstance();
//        JDBCExpositionDao expositionDao = (JDBCExpositionDao) daoFactory.createExpositionDao();
//        Map<Hall, Integer> halls = expositionDao.getHalls();
        Set<Hall> halls = new HashSet<>();
        halls.add(Hall.BLUE);
        halls.add(Hall.RED);
//        LocalDate calendar = LocalDate.of(2020, 2, 26);
//        Exposition exposition = new Exposition("Rocks", 120, halls, calendar);
//        Exposition exposition2 = new Exposition("Space", 40, halls, calendar);
//        Exposition exposition3 = new Exposition("Times", 50, halls, calendar);
//        List<Exposition> list = new ArrayList<>();
//        list.add(exposition);
//        list.add(exposition2);
//        list.add(exposition3);
        request.getSession().setAttribute("expo", halls);
        return "redirect:/user/main.jsp";
    }
}

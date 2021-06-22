package ua.training;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.Hall;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Set<Hall> halls = new HashSet<>();
        halls.add(Hall.GREEN);
        halls.add(Hall.BLUE);
        halls.add(Hall.RED);
        ExhibitionService exhibitionService = new ExhibitionService();
//        List<Exhibition> list = exhibitionService.getFrom(1, 1, 13);
//        for (Exhibition ex : list) {
//            System.out.println(ex);
//            ex.setHalls(halls);
//            exhibitionService.update(ex);
//        }
        exhibitionService.cancel(6);
    }
}

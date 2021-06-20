package ua.training;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import java.math.BigDecimal;
import java.sql.*;

public class Test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ExhibitionService exhibitionService = new ExhibitionService();
        UserService userService = new UserService();
        Exhibition exhibition;
        try {
            exhibition = exhibitionService.findById(1);
        } catch (Exception e) {
            return ;
        }
         userService.findExhibitionUsers(exhibition).forEach(System.out::println);
    }
}
